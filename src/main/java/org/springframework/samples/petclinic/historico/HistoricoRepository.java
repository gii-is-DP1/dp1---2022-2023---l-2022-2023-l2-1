package org.springframework.samples.petclinic.historico;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.logros.Logro;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends CrudRepository<Historico, Integer> {

    List<Historico> findAll();

    Historico findHistoricoByUserId(Integer id);
    Set<Logro> findLogrosByUserId(Integer id);
}
