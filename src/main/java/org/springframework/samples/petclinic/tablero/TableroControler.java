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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/partidas/{partida_id}") // "/{user_id}/{partida_id}"
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
        public List<Dificultad> getDifs(){
            return this.partidaService.getAllDifs();
        }

        @GetMapping(value = "/partidas/{partida_id}/{tablero_id}")
        public ModelAndView showTablero(@PathVariable("tablero_id") int id){
            ModelAndView mav = new ModelAndView("/tableroEnJuego");
            Tablero tab = this.boardService.getBoardById(id);
            mav.addObject(tab);
            return mav;
        }
        //Post mapping para cuando termine la partida?

        //A partir de partida se crean los tableros
        public Tablero createNewBoard(Partida partida) {
            Tablero tablero = new Tablero();
            Dificultad dif =  partida.getDificultad();

            if(dif.getId()==1){
                tablero.setColumnas(10);
                tablero.setFilas(8);
            }else if(dif.getId()==2){
                tablero.setColumnas(18);
                tablero.setFilas(14);
            }else{
                tablero.setColumnas(24);
                tablero.setFilas(20);
            }
            
            TableroRepository.save(tablero);
         
            return tablero;
        }




    
}
