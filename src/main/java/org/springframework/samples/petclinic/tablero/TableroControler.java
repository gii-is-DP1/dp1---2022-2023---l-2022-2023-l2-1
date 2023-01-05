package org.springframework.samples.petclinic.tablero;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.partidas.Dificultad;
import org.springframework.samples.petclinic.partidas.Partida;
import org.springframework.samples.petclinic.partidas.PartidaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TableroControler {

        private final TableroService tableroService;

        private final PartidaService partidaService;


        @Autowired
        public TableroControler(TableroService tableroService, PartidaService partidaService){
            this.tableroService = tableroService;
            this.partidaService = partidaService;
        }

        @InitBinder
        public void initTableroBinder(WebDataBinder dataBinder){
                dataBinder.setDisallowedFields("id");        
        }
        @ModelAttribute("dificultades")
        public List<Dificultad> getDifs() {
            return this.partidaService.getAllDifs();
        }
        
    @GetMapping(value = { "/tablero/{partida_id}" })
    public ModelAndView tableroView(@PathVariable("partida_id") Integer id) {
        ModelAndView res = new ModelAndView("tablero/tablero");
        //Crear un tablero.
        Tablero tablero = new Tablero();
        //Encontrar partida por el id.
        Partida partida = partidaService.getById(id);
        Dificultad diff = partida.getDificultad();
        //Asignarle dificultad
            //facil
        if(diff.getId().equals(1)){
            tablero.setColumnas(10); tablero.setFilas(8);tablero.setMinas(10);tablero.setPartidaId(partida);
            tableroService.saveBoard(tablero);
            //medio
        }else if(diff.getId().equals(2)){
            tablero.setColumnas(18); tablero.setFilas(14);tablero.setMinas(40);tablero.setPartidaId(partida);
            tableroService.saveBoard(tablero);
            //dificil
        }else{
            tablero.setColumnas(24); tablero.setFilas(20);tablero.setMinas(99);tablero.setPartidaId(partida);
            tableroService.saveBoard(tablero);
        }
        res.addObject("tablero", tablero);
        return res;
    }
}
