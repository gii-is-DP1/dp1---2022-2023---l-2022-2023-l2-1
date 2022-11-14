package org.springframework.samples.petclinic.historico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/historicos")
public class HistoricoController {

    private HistoricoService historicoService;

    @Autowired
    public HistoricoController(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @GetMapping()
    public ModelAndView muestraHistoricoDeUsuario(@PathVariable("id") Integer id) {
        ModelAndView result = new ModelAndView("HistoricoDeUsuario");
        result.addObject("historico", historicoService.findHistoricoByUserId(id));
        return result;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView saveHistorico(Historico historico, BindingResult br, @PathVariable("id") Integer id) {
        ModelAndView result = new ModelAndView();

        return result;
    }
}
