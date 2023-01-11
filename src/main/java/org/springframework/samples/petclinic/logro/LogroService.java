package org.springframework.samples.petclinic.logro;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogroService {
    private LogroRepository logroRepository;

    @Autowired
    public LogroService(LogroRepository logroRepository){
        this.logroRepository = logroRepository;
    }

    @Transactional(readOnly= true)        
    public Set<Logro> findAllLogros() {
        return logroRepository.findAllLogros();
    }
    @Transactional
    @Modifying   
    public void deleteLogroById(Integer id) {
        logroRepository.deleteLogroById(id);
    }
    @Transactional(readOnly= true)
    public Optional<Logro> getLogroById(Integer id) {
        return logroRepository.findById(id);
    }

    @Transactional(rollbackFor = SQLException.class)
    @Modifying  
    public void save(Logro log) throws DataAccessException{
        logroRepository.save(log);
    }


}