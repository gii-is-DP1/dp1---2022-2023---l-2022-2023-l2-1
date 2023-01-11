package org.springframework.samples.petclinic.historico;

import java.util.List;
import java.util.Set;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

@Repository
public interface HistoricoRepository extends CrudRepository<Historico, Integer> {
    List<Historico> findAll();
    Historico findHistoricoByRegisteredUserId(Integer id);

}

