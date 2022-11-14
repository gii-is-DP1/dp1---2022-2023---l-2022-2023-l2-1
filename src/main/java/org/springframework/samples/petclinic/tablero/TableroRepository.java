package org.springframework.samples.petclinic.tablero;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

public interface TableroRepository extends Repository<Tablero,Integer> {

    static void save(Tablero tablero) throws DataAccessException {
      
    }

    List<Tablero> findAll() throws DataAccessException;

    Optional<Tablero> findById(int id) throws DataAccessException;



}