package org.springframework.samples.petclinic.logros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/registeredUser/{registeredUserId}/logros")
public class LogroController {

    private final LogroService logroService;
    private final RegisteredUserService registeredUserService;

    @Autowired
    public LogroController(LogroService logroService, RegisteredUserService registeredUserService) {
        this.logroService = logroService;
        this.registeredUserService = registeredUserService;
    }

    @GetMapping()
    public ModelAndView showEstadisticasByUserId(@PathVariable("registeredUserId") int id) {
        ModelAndView res = new ModelAndView("estadisticas/logrosDeUsuario");
        res.addObject("logros", logroService.getAllByRegisteredUserId(id));
        res.addObject("user", registeredUserService.findRegisteredUserById(id));
        return res;
    }
}
