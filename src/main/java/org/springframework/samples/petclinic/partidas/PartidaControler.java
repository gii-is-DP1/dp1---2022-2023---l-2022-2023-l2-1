package org.springframework.samples.petclinic.partidas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.registeredUser.RegisteredUser;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/registeredUser/{registeredUserId}/partidas")
    public ModelAndView showPartidasByUserId(@PathVariable("registeredUserId") int id) {
        ModelAndView res = new ModelAndView("partida/listaDePartidas");
        res.addObject("partidas", partidaService.getAllById(id));
        return res;
    }

}

