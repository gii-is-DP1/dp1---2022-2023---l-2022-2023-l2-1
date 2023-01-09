package org.springframework.samples.petclinic.historico;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends CrudRepository<Historico, Integer> {

    List<Historico> findAll();
    Historico findHistoricoByRegisteredUserId(Integer id);

}
