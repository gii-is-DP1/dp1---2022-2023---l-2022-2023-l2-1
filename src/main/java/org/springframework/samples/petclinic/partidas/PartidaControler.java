package org.springframework.samples.petclinic.partidas;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartidaControler {
    private PartidaService partidaService;
    private RegisteredUserService registerableService;

    @Autowired
    public PartidaControler(PartidaService partidaService, RegisteredUserService registerableService) {
        this.partidaService = partidaService;
        this.registerableService = registerableService;
    }


    @InitBinder("partida")
    public void initOwnerBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("tiempo_de_juego");
    
    }
    /*
     * @GetMapping(value ={ "/partidas"})
     * public String showPartidas(Map<String, Object> model) {
     * // Here we are returning an object of type 'partidas' rather than a
     * collection of Vet
     * // objects
     * // so it is simpler for Object-Xml mapping
     * List<Partida> partidas = new ArrayList<Partida>();
     * partidas.addAll(this.partidaService.getAll());
     * Map<String, Object> res = new HashMap<>();
     * for(Partida p : partidas){
     * RegisteredUser u = registerableService.findRegisteredUserById(p.getUserId());
     * String name = u.getName();
     * res.put(name, p);
     * }
     * model.put("partidas", partidas);
     * return "/partida/partidas";
     * }
     */
    /*
     * @GetMapping()
     * public ModelAndView showAllPartidas() {
     * ModelAndView res = new ModelAndView("partida/partidas");
     * List<Partida> partidas = partidaService.getAll();
     * Map<String, Partida> map = new HashMap<>();
     * for (Partida p : partidas) {
     * String nombre =
     * registerableService.findRegisteredUserById(p.getUserId()).getName().toString(
     * );
     * map.put(nombre, p);
     * }
     * res.addObject("partidas", map);
     * return res;
     * }
     */

    @GetMapping(value = "/partidas")
    public ModelAndView showAllPartidas() {
        ModelAndView res = new ModelAndView("partida/partidas");
        res.addObject("partidas", partidaService.getAllPartidasActuales());

        return res;
    }

    @GetMapping(value = "/registeredUser/{registeredUserId}/partidas/new")
    public String crearNuevaPartida(@PathVariable("registeredUserId") int id, Map<String, Object> model) {
       
        Partida partida = new Partida();
        partida.setRegisteredUserId(id);
        
		model.put("partida", partida);
    
		return "partida/nuevaPartida";
    }

    @PostMapping(value = "/registeredUser/{registeredUserId}/partidas/new")
	public String processCreationForm(@Valid Partida partida, BindingResult result) {
		if (result.hasErrors()) {
			return "partida/nuevaPartida";
		} else {        
            
			//"/registeredUser/"+partida.getRegisteredUserId()+"/partidas/"+partida.getId()+"/new"
			return "redirect:/partidas" ;
		}
	}


    @GetMapping(value = "/registeredUser/{registeredUserId}/partidas")
    public ModelAndView showPartidasByUserId(@PathVariable("registeredUserId") int id) {
        ModelAndView res = new ModelAndView("partida/listaDePartidas");
        res.addObject("partidas", partidaService.getAllById(id));
        return res;
    }

}

