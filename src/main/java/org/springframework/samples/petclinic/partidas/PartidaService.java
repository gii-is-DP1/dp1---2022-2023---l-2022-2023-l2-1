package org.springframework.samples.petclinic.partidas;


import java.util.List;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    private PartidaRepository partidaRepository;

    @Autowired
    public PartidaService (PartidaRepository partidaRepository) throws DataAccessException{
        this.partidaRepository = partidaRepository;
    }
    @Transactional    
    public List<Partida> getAll(){
        return partidaRepository.findAll();
    }
    
    @Transactional
    public List<Partida> getAllById(Integer id) throws DataAccessException{
        return partidaRepository.findAllByRegisteredUserId(id);
    }
    @Transactional
    public  Partida getById(Integer id) throws DataAccessException{
        return partidaRepository.findPartidaById(id);
    }
    @Transactional
    public Partida savePartida(Partida partida) throws DataAccessException{
        return partidaRepository.save(partida);
    }

    @Transactional
    public Dificultad getDiffById(Integer id) throws DataAccessException{
        return partidaRepository.finDificultadById(id);
    }

    @Transactional
    public List<Dificultad> getAllDifs() throws DataAccessException{
        return partidaRepository.findDificultades();

    }        

    @Transactional(readOnly = true)
    public List<TipoDePartida> getAllTiposDePartidas(){
        return partidaRepository.findTiposDePartida();
    }
    @Transactional
    public List<Partida> getAllPartidasActuales() throws DataAccessException{
        return partidaRepository.findPartidasActuales();
    }

    

    
    


    
}
