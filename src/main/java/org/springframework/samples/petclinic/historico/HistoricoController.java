package org.springframework.samples.petclinic.historico;


import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.registeredUser.RegisteredUser;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.samples.petclinic.partidas.PartidaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HistoricoController {

    private final HistoricoService historicoService;
    private final PartidaService partidaService;
    private final RegisteredUserService registeredService;

    @Autowired
    public HistoricoController(HistoricoService historicoService, PartidaService partidaService, RegisteredUserService registeredService) {
        this.historicoService = historicoService;
        this.partidaService = partidaService;
        this.registeredService = registeredService;
    }

    @GetMapping(value = "/registeredUser/{registeredUserId}/estadisticas")
    public ModelAndView muestraHistoricoDeUsuario(@PathVariable("registeredUserId") int id) {
        ModelAndView result = new ModelAndView("estadisticas/estadisticasDeUsuario");
        result.addObject("historico", historicoService.getHistoricoByRegisteredUserId(id));
        result.addObject("user", registeredService.findRegisteredUserById(id));
        return result;
    }
    
    @GetMapping(value = "/registeredUser/{registeredUserId}/partidasJugadas")
    public ModelAndView muestraPartidasDeUsuario(@PathVariable("registeredUserId") int id) {
        ModelAndView result = new ModelAndView("partida/listaDePartidas");
        Collection<RegisteredUser> compis = registeredService.findRegisteredUser();
        RegisteredUser usuario = registeredService.findRegisteredUserById(id);
        result.addObject("partidas", partidaService.getAllById(id));
        result.addObject("compis", compis);
        result.addObject("registeredUser", usuario);

        return result;
    }


    @PostMapping("/registeredUser/{registeredUserId}/edit/{id}")
    public ModelAndView saveHistorico(Historico historico, BindingResult br, @PathVariable("id") int id) {
        ModelAndView result = new ModelAndView();

        return result;
    }
    @GetMapping(value = "/registeredUser/{registeredUserId}/ranking")
    public ModelAndView muestraRankingDeUsuarios() {
        ModelAndView result = new ModelAndView("estadisticas/ranking");
        Collection<RegisteredUser> users = registeredService.findRegisteredUser();
        List<Historico> ranking = historicoService.findAll();
        ranking = ranking.stream().sorted(Comparator.reverseOrder()).limit(10).collect(Collectors.toList());
        result.addObject("historicos", ranking);
        result.addObject("users", users);
        return result;
    }


}
