package org.springframework.samples.petclinic.tablero;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.historico.Historico;
import org.springframework.samples.petclinic.historico.HistoricoService;
import org.springframework.samples.petclinic.partidas.Dificultad;
import org.springframework.samples.petclinic.partidas.Partida;
import org.springframework.samples.petclinic.partidas.PartidaService;
import org.springframework.samples.petclinic.registeredUser.RegisteredUser;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TableroControler {

    private final TableroService tableroService;

    private final UserService userService;

    private final RegisteredUserService registeredUserService;

    private final HistoricoService historicoService;

    private final PartidaService partidaService;

    @Autowired
    public TableroControler(TableroService tableroService, PartidaService partidaService,
            HistoricoService historicoService, UserService userService, RegisteredUserService registeredUserService) {
        this.tableroService = tableroService;
        this.partidaService = partidaService;
        this.historicoService = historicoService;
        this.userService = userService;
        this.registeredUserService = registeredUserService;
    }

    @InitBinder
    public void initTableroBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("dificultades")
    public List<Dificultad> getDifs() {
        return this.partidaService.getAllDifs();
    }

    @GetMapping(value = { "/tablero/{partida_id}" })
    public ModelAndView tableroView(@PathVariable("partida_id") Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        RegisteredUser ru = this.registeredUserService
                .findRegisteredUserByUsername(this.userService.findUser(username).orElse(null));
        ModelAndView res = new ModelAndView("tablero/tablero");
        // Crear un tablero.
        Tablero tablero = new Tablero();
        // Encontrar partida por el id.
        Partida partida = partidaService.getById(id);
        Dificultad diff = partida.getDificultad();
        // Asignarle dificultad
        // facil
        if (diff.getId().equals(1)) {
            tablero.setColumnas(10);
            tablero.setFilas(8);
            tablero.setMinas(10);
            // medio
        } else if (diff.getId().equals(2)) {
            tablero.setColumnas(21);// 18
            tablero.setFilas(12);// 14
            tablero.setMinas(40);
            // dificil
        } else {
            tablero.setColumnas(30);// 24
            tablero.setFilas(16);// 20
            tablero.setMinas(99);
        }
        tablero.setPartida(partida);
        tableroService.saveBoard(tablero);
        res.addObject("registeredUser", ru);
        res.addObject("tablero", tablero);
        return res;
    }

    @GetMapping(value = "/postTablero/{minasEncontradas}/{tiempoEmpleado}/{esVictoria}/{partidaId}")
    public String postTablero(@PathVariable("minasEncontradas") Integer minasEncontradas,
            @PathVariable("tiempoEmpleado") String tiempoEmpleado, @PathVariable("esVictoria") String esVictoria,
            @PathVariable("partidaId") Integer partidaId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        RegisteredUser ru = this.registeredUserService
                .findRegisteredUserByUsername(this.userService.findUser(username).orElse(null));
        if (ru != null) {
            Historico nuevoHistorico = historicoService.getHistoricoByRegisteredUserId(ru.getId());
            Partida partida = partidaService.getById(partidaId);
            if (esVictoria.equals("true")) {
                nuevoHistorico.setPartidasGanadas(nuevoHistorico.getPartidasGanadas() + 1);
                if(partida.getRegisteredUserId()==ru.getId()){
                    partida.setResultado(true);
                }
            }else{
                partida.setResultado(false);
            }
            partida.setTiempoDeJuego(LocalTime.parse(tiempoEmpleado));
            nuevoHistorico.setPartidasTotales(nuevoHistorico.getPartidasTotales() + 1);
            nuevoHistorico.setMinasEncontradas(nuevoHistorico.getMinasEncontradas() + minasEncontradas);
            LocalTime nuevoTiempo = LocalTime.parse(tiempoEmpleado);
            nuevoHistorico.setTiempoTotalJuego(nuevoHistorico.getTiempoTotalJuego().plusMinutes(nuevoTiempo.getHour())
                    .plusSeconds(nuevoTiempo.getMinute()));
            nuevoHistorico.setTiempoMedioPartida(calculaTiempoMedioPartida(nuevoHistorico.getTiempoTotalJuego(),
                    nuevoHistorico.getPartidasTotales()));
            nuevoHistorico.setPuntuacion(nuevoHistorico.getPuntuacion()
                    + calculaPuntuacion(nuevoHistorico.getMinasEncontradas(), nuevoTiempo, esVictoria));
            historicoService.saveHistorico(nuevoHistorico);
        }
        return "redirect:/partida/new";
    }

    public LocalTime calculaTiempoMedioPartida(LocalTime tiempoTotal, Integer nPartidas) {
        Integer segundos = tiempoTotal.getHour() * 60 * 60 + tiempoTotal.getMinute() * 60 + tiempoTotal.getSecond();
        Integer c = segundos / nPartidas;

        Integer hh = c / 60 / 60;
        Integer mm = (c / 60) % 60;
        Integer ss = c % 60;
        String res = String.format("%02d:%02d:%02d", hh, mm, ss);
        return LocalTime.parse(res);
    }

    public Integer calculaPuntuacion(Integer minasEncontradas, LocalTime tiempoPartida, String esVictoria) {
        Double segundos = tiempoPartida.getMinute() * 60. + tiempoPartida.getSecond();
        Double res = (minasEncontradas / segundos) * 1000;
        if (esVictoria.equals("true")) {
            res *= 2;
        }
        return res.intValue();
    }
}
