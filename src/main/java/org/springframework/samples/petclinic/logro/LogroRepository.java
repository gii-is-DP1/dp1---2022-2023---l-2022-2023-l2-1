package org.springframework.samples.petclinic.logro;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogroRepository extends CrudRepository<Logro, Integer> {
    @Query("SELECT l FROM Logro l")
    Set<Logro> findAllLogros();

    @Modifying
    @Query("DELETE Logro l WHERE l.id = :id")
    void deleteLogroById(int id);

    @Query("SELECT cond FROM Condicion cond ORDER BY cond.id")
    List<Condicion> findAllCondiciones() throws DataAccessException;

}
