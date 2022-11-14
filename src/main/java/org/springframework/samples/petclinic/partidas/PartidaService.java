package org.springframework.samples.petclinic.partidas;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    private PartidaRepository partidaRepository;

    @Autowired
    public PartidaService (PartidaRepository partidaRepository){
        this.partidaRepository = partidaRepository;
    }
    @Transactional    
    public List<Partida> getAll(){
        return partidaRepository.findAll();
    }
    @Transactional
    public List<Partida> getAllById(Integer id){
        return partidaRepository.findAllByUserId(id);
    }
    @Transactional
    public Partida savePartida(Partida partida){
        return partidaRepository.save(partida);
    }
    @Transactional
    public List<Dificultad> getAllDifs(){
        return partidaRepository.findDificultades();
    }
    


    
}
