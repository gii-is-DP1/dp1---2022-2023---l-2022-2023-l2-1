package org.springframework.samples.petclinic.partidas;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.registeredUser.RegisteredUser;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
    public RegisteredUser getUsuarioActual(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
        return this.registerableService.findRegisteredUserByUsername(this.userService.findUser(username).orElse(null));
    }

    //Metodo para la visualizacion de partidas como admin o las partidas actuales como usuario normal
    @GetMapping(value = "/partidas")
    public ModelAndView showAllPartidas() {
        ModelAndView res = new ModelAndView("partida/partidas");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))) {
            res.addObject("partidas", partidaService.getAllPartidasActuales());
            Set<RegisteredUser> users = registerableService.findRegisteredUser().stream().collect(Collectors.toSet());
            res.addObject("usuarios", users);
            return res;
        }else{

        res.addObject("partidas", partidaService.getAllPartidasActuales().stream().filter(c->c.getTipo()==tiposDePartida().get(1)).collect(Collectors.toList()) );
        Set<RegisteredUser> usuarios = registerableService.findRegisteredUser().stream().collect(Collectors.toSet());

        res.addObject("usuarios", usuarios);
        return res;
    }
    }
    
    //metodo para unirse a una partida competitiva ya existente
    @GetMapping(value= "/partida/{partidaId}/join")
    public String joinPartida(@PathVariable("partidaId") int id){
       Partida part = partidaService.getById(id);    
       RegisteredUser ru = getUsuarioActual();
        if(ru==null || part.getIdInvitado()!=null || part.getRegisteredUserId()==ru.getId()){
            return "redirect:/exception";
        }else{
      part.setIdInvitado(ru.getId());
      partidaService.savePartida(part);

      return "redirect:/tablero/"+part.getId();
        }
    }
    //metodo para la creacion de una partida
    @GetMapping(value = "/partida/new")
    public String nuevaPartida(Map<String, Object> model) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		RegisteredUser ru = getUsuarioActual();
        if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("registeredUser"))){
                model.put("registeredUser", ru);
                return "partida/elegirModo";
        }else{
        Partida partida = new Partida();
        model.put("partida", partida);
        return "partida/nuevaIndividual"; 
        }
    }
    //metodo para jugar una partida sin estar registrado
    @GetMapping(value = "/partida/noUser/{dificultad}")
    public String noUserNewPartida(@PathVariable("dificultad") String difi,Map<String, Object> model){
        Dificultad dificultad = null;
        for (Dificultad dif : dificultades()) {
			if (dif.getName().toLowerCase().trim().equals(difi.toLowerCase().trim())) {
				dificultad=dif;
			}
		}
        Partida part = new Partida();
        part.setDificultad(dificultad);
        part.setTipo(tiposDePartida().get(0));
        part.setRegisteredUserId(null);
        part.setPrivada(false);
        partidaService.savePartida(part);
        model.put("partida", part);

        return "redirect:/tablero/"+part.getId();
    }


    //metodo para jugar una partida individual
    @GetMapping(value = "/registeredUser/{registeredUserId}/partidas/nuevaIndividual")
    public String userNewPartida(@PathVariable("registeredUserId") int id, Map<String, Object> model){
        Partida partida = new Partida();
        model.put("partida", partida);
        RegisteredUser ru = registerableService.findRegisteredUserById(id);
        model.put("registeredUser", ru);
        return "partida/nuevaIndividual";
    }
    //post mapping del anterior
    @PostMapping(value = "/registeredUser/{registeredUserId}/partidas/nuevaIndividual")
    public String userPostPartida(@ModelAttribute Partida partida, Map<String, Object> model){
        partidaService.savePartida(partida);
        if(partida.getPrivada() == null){
            partida.setPrivada(false);
            partida.setContrasenia(null);
        }if(partida.getContrasenia() == ""){
            partida.setContrasenia(null);
        }
        partidaService.savePartida(partida);
            return "redirect:/tablero/"+partida.getId();
        }

    //metodo para jugar una partida competitivo
    @GetMapping(value = "/registeredUser/{registeredUserId}/partidas/new")
    public String newPartida(@PathVariable("registeredUserId") int id,Map<String, Object> model){
        Partida partida = new Partida();
        model.put("partida", partida);
        return "partida/nuevaPartida";
    }
    //post mapping del anterior
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

            return "redirect:/tablero/"+partida.getId();
        }
    }



