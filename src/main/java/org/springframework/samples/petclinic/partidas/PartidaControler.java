package org.springframework.samples.petclinic.partidas;

import org.springframework.samples.petclinic.registeredUser.RegisteredUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/partidas")
public class PartidaControler {

    @GetMapping()
    public ModelAndView showAllPartidas(){
        ModelAndView res = new ModelAndView("Partidas") ;
        return res;

    }

    @RequestMapping(value = "{user_id}/partidas/create")
    public String createNewSoloGame(@PathVariable("user_id") int user_id, Dificultad diff) {

    Partida part = new Partida();
    part.setDificultad(diff);
    part.setPrivada(null);
    part.setUserId(user_id);
    part.setIdInvitado(null);
    part.setContrasenia(null);
    part.setTiempoDeJuego(0);
    part.setIdInvitado(null);

    PartidaService.savePartida(part);

    return "/partidas/" + part.getId();
}

}

