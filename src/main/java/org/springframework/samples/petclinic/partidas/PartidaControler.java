package org.springframework.samples.petclinic.partidas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/partidas")
public class PartidaControler {
    @GetMapping()
    public ModelAndView showAllPartidas(){
        ModelAndView res = new ModelAndView("partida/partidas");
        return res;
    }
}
