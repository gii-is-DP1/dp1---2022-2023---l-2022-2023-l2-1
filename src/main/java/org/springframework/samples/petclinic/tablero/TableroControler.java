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

        private final TableroService boardService;

        private final PartidaService partidaService;


        @Autowired
        public TableroControler(TableroService boardService, PartidaService partidaService){
            this.boardService = boardService;
            this.partidaService = partidaService;
        }

        @InitBinder
        public void initBoardBinder(WebDataBinder dataBinder){
                dataBinder.setDisallowedFields("id");        
        }
        @ModelAttribute("dificultades")
        public List<Dificultad> getDifs() {
            return this.partidaService.getAllDifs();
        }
        
    @GetMapping(value = { "/partidas/{partida_id}/{tablero_id}" })
    public ModelAndView tableroView(@PathVariable("tablero_id") Integer id) {
        ModelAndView res = new ModelAndView("tablero/tablero");
        Tablero tablero = boardService.getBoardById(id);
        res.addObject("tablero", tablero);
        return res;
    }
}
