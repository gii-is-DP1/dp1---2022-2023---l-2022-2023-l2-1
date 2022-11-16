package org.springframework.samples.petclinic.logros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogroService {

    private final LogroRepository logroRepository;

    @Autowired
    public LogroService(LogroRepository logroRepository) {
        this.logroRepository = logroRepository;
    }

    @Transactional(readOnly = true)
    public List<Logro> getAll() {
        return logroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Logro> getAllByRegisteredUserId(Integer id) {
        return logroRepository.findAllByRegisteredUserId(id);
    }

    @Transactional(readOnly = true)
    public List<Condicion> getAllCondiciones() {
        return logroRepository.findCondiciones();
    }

    @Transactional(readOnly = true)
    public Condicion getCondicionByLogroId(Integer id) {
        return logroRepository.findCondicionById(id);
    }
}
