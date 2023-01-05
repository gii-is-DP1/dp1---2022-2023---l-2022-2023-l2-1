package org.springframework.samples.petclinic.tablero;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PostMapping;
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
            tablero.setPartidaId(partida);
            tableroService.saveBoard(tablero);
            // medio
        } else if (diff.getId().equals(2)) {
            tablero.setColumnas(18);
            tablero.setFilas(14);
            tablero.setMinas(40);
            tablero.setPartidaId(partida);
            tableroService.saveBoard(tablero);
            // dificil
        } else {
            tablero.setColumnas(24);
            tablero.setFilas(20);
            tablero.setMinas(99);
            tablero.setPartidaId(partida);
            tableroService.saveBoard(tablero);
        }
        res.addObject("tablero", tablero);
        return res;
    }

    @PostMapping(value = "/registeredUser/{registeredUserId}/postTablero")
    public String postTablero(@ModelAttribute Historico historico, Map<String, Object> model) {
        String res = "";

        return res;
    }
}
