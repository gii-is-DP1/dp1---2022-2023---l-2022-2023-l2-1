package org.springframework.samples.petclinic.partidas;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends CrudRepository<Partida, Integer> {
    List<Partida> findAll();
    List<Partida> findAllByRegisteredUserId(Integer Id);
    Partida findPartidaByRegisteredUserId(Integer Id);
    Partida findPartidaById(Integer Id);

           
    @Query("SELECT diff FROM Dificultad diff ORDER BY diff.id")
    List<Dificultad> findDificultades() throws DataAccessException;

    @Query("FROM Partida WHERE tiempo_de_juego IS NULL")
    List<Partida> findPartidasActuales() throws DataAccessException;
  
   // @Query("SELECT tipo FROM TipoDePartida tipo ORDER BY tipo.id")
    //List<TipoDePartida> findTipos() throws DataAccessException;
}
