package org.springframework.samples.petclinic.logro;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.historico.HistoricoService;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogroController {
    private LogroService logroService;
    private HistoricoService historicoService;
    private RegisteredUserService registeredService;

    @Autowired
    public LogroController(LogroService logroService, HistoricoService historicoService,
            RegisteredUserService registeredService) {
        this.historicoService = historicoService;
        this.registeredService = registeredService;
        this.logroService = logroService;
    }

    @GetMapping("/registeredUser/{registeredUserId}/myLogros")
    public ModelAndView showEstadisticasByUserId(@PathVariable("registeredUserId") int id) {
        ModelAndView res = new ModelAndView("estadisticas/logrosDeUsuario");
        Set<Logro> logros = historicoService.getHistoricoByRegisteredUserId(id).getLogros();
        res.addObject("user", registeredService.findRegisteredUserById(id));
        res.addObject("logros", logros);
        return res;
    }

    @GetMapping("/logros")
    public ModelAndView showAllLogros() {
        ModelAndView res = new ModelAndView("estadisticas/listaLogros");
        Set<Logro> logros = logroService.findAllLogros();
        res.addObject("logros", logros);
        return res;
    }

    @GetMapping("/logros/{logroId}/delete")
    public String eliminaLogro(@PathVariable("logroId") Integer id) {
        logroService.deleteLogroById(id);
        return "redirect:/logros";
    }

    @GetMapping("/logros/{logroId}/edit")
    public String editaLogro(@PathVariable("logroId") Integer id, Model model) {
        Optional<Logro> logro = logroService.getLogroById(id);
        model.addAttribute("logro", logro);
        return "estadisticas/logroUpdate";
    }

    @PostMapping("/logros/{logroId}/edit")
    public String editaLogro(@ModelAttribute Logro l) {
        logroService.save(l);
        return "redirect:/logros";
    }

    @GetMapping("/logros/new")
    public String creaLogReadOnlyProperty(Model model) {
        Logro logro = new Logro();
        model.addAttribute("logro", logro);
        return "estadisticas/logroCreate";
    }

    @PostMapping("/logros/new")
    public String creaLogro(@ModelAttribute Logro l) {
        logroService.save(l);
        return "redirect:/logros";
    }

}
