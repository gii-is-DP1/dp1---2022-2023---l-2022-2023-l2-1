package org.springframework.samples.petclinic.historico;


import java.lang.ProcessBuilder.Redirect;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.security.auth.message.config.AuthConfig;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.registeredUser.RegisteredUser;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.samples.petclinic.partidas.PartidaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        Set<RegisteredUser> compis = partidaService.getAllById(id).stream().filter(c->c.getIdInvitado()!=null).map(c->registeredService.findRegisteredUserById(Integer.valueOf(c.getIdInvitado()))).collect(Collectors.toSet());

        result.addObject("partidas", partidaService.getAllById(id));
        result.addObject("compis", compis);

        return result;
    }


    @PostMapping("/registeredUser/{registeredUserId}/edit/{id}")
    public ModelAndView saveHistorico(Historico historico, BindingResult br, @PathVariable("id") int id) {
        ModelAndView result = new ModelAndView();

        return result;
    }

    @GetMapping("/registeredUser/{registeredUserId}/myLogros")
    public ModelAndView showEstadisticasByUserId(@PathVariable("registeredUserId") int id) {
        ModelAndView res = new ModelAndView("estadisticas/logrosDeUsuario");
        Set<Logro> logros =  historicoService.getHistoricoByRegisteredUserId(id).getLogros();
        res.addObject("user", registeredService.findRegisteredUserById(id));
        res.addObject("logros",logros);
        return res;
    }

    @GetMapping("/logros")
    public ModelAndView showAllLogros() {
        ModelAndView res = new ModelAndView("estadisticas/listaLogros");
        Set<Logro> logros =  historicoService.findAllLogros();
        res.addObject("logros",logros);
        return res;
    }

    @GetMapping("/logros/{logroId}/delete")
    public String eliminaLogro(@PathVariable("logroId") Integer id) {
        historicoService.deleteLogroById(id);
        return "redirect:/logros";
    }

    @GetMapping("/logros/{logroId}/edit")
    public String editaLogro(@PathVariable("logroId") Integer id, Model model) {
        Logro l = historicoService.getLogroById(id);
        model.addAttribute(l);
        return "estadisticas/logroUpdate";
    }

    @PostMapping("/logros/{logroId}/edit")
    public String editaLogro(@Valid Logro logro, BindingResult result,
    @PathVariable("logroId") int logroId) {
        logro.setId(logroId);
		this.historicoService.save(logro);
		return "redirect:/logros";
    }

}
