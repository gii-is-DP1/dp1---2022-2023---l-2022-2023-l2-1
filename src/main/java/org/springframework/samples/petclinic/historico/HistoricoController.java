package org.springframework.samples.petclinic.historico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistoricoController {

    private final HistoricoService historicoService;
    private final RegisteredUserService registeredUserService;

    @Autowired
    public HistoricoController(HistoricoService historicoService, RegisteredUserService registeredUserService) {
        this.historicoService = historicoService;
        this.registeredUserService = registeredUserService;
    }

    @GetMapping(value = "/registeredUser/{registeredUserId}/estadisticas")
    public ModelAndView showEstadisticasByUserId(@PathVariable("registeredUserId") int id) {
        ModelAndView res = new ModelAndView("estadisticas/estadisticasDeUsuario");
        res.addObject("estadistica", historicoService.getHistoricoByRegisteredUserId(id));
        res.addObject("user", registeredUserService.findRegisteredUserById(id));
        return res;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView saveHistorico(Historico historico, BindingResult br, @PathVariable("id") Integer id) {
        ModelAndView result = new ModelAndView();

        return result;
    }
}
