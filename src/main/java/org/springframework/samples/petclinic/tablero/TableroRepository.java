package org.springframework.samples.petclinic.tablero;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;


public interface TableroRepository extends CrudRepository<Tablero,Integer> {


    List<Tablero> findAll() throws DataAccessException;

    Optional<Tablero> findById(int id) throws DataAccessException;



}