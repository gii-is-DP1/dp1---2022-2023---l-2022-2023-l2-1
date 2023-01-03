package org.springframework.samples.petclinic.partidas;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.registeredUser.RegisteredUser;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartidaControler {
    private PartidaService partidaService;
    private RegisteredUserService registerableService;
    private UserService userService;

    @Autowired
    public PartidaControler(PartidaService partidaService, RegisteredUserService registerableService, UserService userService) {
        this.partidaService = partidaService;
        this.registerableService = registerableService;
        this.userService = userService;
    }


    @InitBinder("partida")
    public void initOwnerBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("tiempo_de_juego");
    
    }
    @ModelAttribute("tipoDePartidas")
	public List<TipoDePartida> tiposDePartida() {
		return this.partidaService.getAllTiposDePartidas();
	}
    @ModelAttribute("dificultades")
	public List<Dificultad> dificultades() {
		return this.partidaService.getAllDifs();
	}

    @GetMapping(value = "/partidas")
    public ModelAndView showAllPartidas() {
        ModelAndView res = new ModelAndView("partida/partidas");
        res.addObject("partidas", partidaService.getAllPartidasActuales());
        Set<RegisteredUser> usuarios = partidaService.getAllPartidasActuales().stream().map(c->c.getRegisteredUserId()).map(c->registerableService.findRegisteredUserById(c)).collect(Collectors.toSet());

        res.addObject("usuarios", usuarios);
        return res;
    }
    
    @PostMapping(value = "/partidas")
    public String entrarPartidaPrivada(@Valid Partida partida, BindingResult result, Map<String, Object> model) {
        Partida part = partidaService.getById(partida.getId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        RegisteredUser ru = this.registerableService
                .findRegisteredUserByUsername(this.userService.findUser(username).orElse(null));
         if(ru==null || part.getIdInvitado()!=null || part.getContrasenia() != partida.getContrasenia()){
             return "redirect:/exception";
        }else{
       part.setIdInvitado(ru.getId());
       partidaService.savePartida(part);
 
       //"/registeredUser/"+partida.getRegisteredUserId()+"/partidas/"+partida.getId()+"/join"
       return "redirect:/partidas";
       
    }
}

    @GetMapping(value= "/partida/{partidaId}/join")
    public String joinPartida(@PathVariable("partidaId") int id){
       Partida part = partidaService.getById(id);
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String username = authentication.getName();
       RegisteredUser ru = this.registerableService
               .findRegisteredUserByUsername(this.userService.findUser(username).orElse(null));
        if(ru==null || part.getIdInvitado()!=null){
            return "redirect:/exception";
        }else{
      part.setIdInvitado(ru.getId());
      partidaService.savePartida(part);

      //"/registeredUser/"+partida.getRegisteredUserId()+"/partidas/"+partida.getId()+"/join"
      return "redirect:/partidas";
        }
    }
    /*
    @PostMapping(value = "/partida/{partidaId}/joinPrivate")
    public String joinPartidaPrivada(@PathVariable("partidaId") int id,Partida partida, BindingResult result, RedirectAttributes redirectAttributes, Map<String, Object> model){
        Partida part = partidaService.getById(id);
        String contraInput = partida.getContrasenia();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        RegisteredUser ru = this.registerableService
                .findRegisteredUserByUsername(this.userService.findUser(username).orElse(null));
         if(ru==null || part.getIdInvitado()!=null || part.getContrasenia()!=contraInput){
             return "redirect:/exception";
         }else{
       part.setIdInvitado(ru.getId());
       partidaService.savePartida(part);
 
       //"/registeredUser/"+partida.getRegisteredUserId()+"/partidas/"+partida.getId()+"/join"
       return "redirect:/partidas";
         }

    }
 */

    @GetMapping(value = "/partida/new")
    public String nuevaPartida(Map<String, Object> model) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		RegisteredUser ru = this.registerableService
				.findRegisteredUserByUsername(this.userService.findUser(username).orElse(null));

        if(!(ru==null)){
                return "redirect:/registeredUser/" + ru.getId()+"/partidas/new";
        }else{
        Partida partida = new Partida();
        model.put("partida", partida);
        return "partida/nuevaPartida"; 
        }
    }

    @GetMapping(value = "/registeredUser/{registeredUserId}/partidas/new")
    public String newPartida(@PathVariable("registeredUserId") int id,Map<String, Object> model){
        Partida partida = new Partida();
        model.put("partida", partida);
        return "partida/nuevaPartida";
    }

    @PostMapping(value = "/registeredUser/{registeredUserId}/partidas/new" )
    public String postPartida(@ModelAttribute Partida partida, Map<String, Object> model){
        partidaService.savePartida(partida);
        if(partida.getPrivada() == null){
            partida.setPrivada(false);
            partida.setContrasenia(null);
        }if(partida.getContrasenia() == ""){
            partida.setContrasenia(null);
        }
        partidaService.savePartida(partida);
        //"/registeredUser/"+partida.getRegisteredUserId()+"/partidas/"+partida.getId()+"/new"
        return "redirect:/partidas";
    }


}

