package org.springframework.samples.petclinic.historico;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.samples.petclinic.partidas.PartidaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/registeredUser/{registeredUserId}")
public class HistoricoController {

    private final HistoricoService historicoService;
    private final PartidaService partidaService;

    @Autowired
    public HistoricoController(HistoricoService historicoService, PartidaService partidaService) {
        this.historicoService = historicoService;
        this.partidaService = partidaService;
    }

    @GetMapping(value = "/estadisticas")
    public ModelAndView muestraHistoricoDeUsuario(@PathVariable("registeredUserId") Integer id) {
        ModelAndView result = new ModelAndView("estadisticas/estadisticasDeUsuario");
        result.addObject("historico", historicoService.getHistoricoById(id));
        return result;
    }
    @GetMapping(value = "/partidasJugadas")
    public ModelAndView muestraPartidasDeUsuario(@PathVariable("registeredUserId") Integer id) {
        ModelAndView result = new ModelAndView("partida/partidas");
        result.addObject("partidas", partidaService.getAllByRegistredUserId(id));

        return result;
    }


    @PostMapping("/edit/{id}")
    public ModelAndView saveHistorico(Historico historico, BindingResult br, @PathVariable("id") Integer id) {
        ModelAndView result = new ModelAndView();

        return result;
    }


}
