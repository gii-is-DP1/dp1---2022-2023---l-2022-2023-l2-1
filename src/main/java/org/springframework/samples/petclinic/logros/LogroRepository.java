package org.springframework.samples.petclinic.logros;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogroRepository extends CrudRepository<Logro, Integer> {
    List<Logro> findAll();

    @Query("FROM Condicion condicion ORDER BY condicion.id")
    List<Condicion> findCondiciones() throws DataAccessException;

    @Query("FROM Condicion condicion WHERE condicion.id=:id")
    Condicion findCondicionById(Integer id) throws DataAccessException;
}
