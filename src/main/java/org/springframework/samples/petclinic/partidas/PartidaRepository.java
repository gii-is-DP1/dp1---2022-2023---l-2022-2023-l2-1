package org.springframework.samples.petclinic.partidas;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends CrudRepository<Partida, Integer> {
    List<Partida> findAll();
    List<Partida> findAllByUserId(Integer Id);
    Partida findPartidaByUserId(Integer Id);
    Partida save(Partida partida) throws DataAccessException;
    
}
