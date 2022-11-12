package org.springframework.samples.petclinic.historico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistoricoService {

    private HistoricoRepository historicoRepository;

    @Autowired
    public HistoricoService(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    @Transactional(readOnly = true)
    public List<Historico> getAllHistoricos() throws DataAccessException {
        return historicoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Historico getHistoricoById(Integer id) throws DataAccessException {
        return historicoRepository.findHistoricoByUserId(id);
    }

    @Transactional
    public void saveHistorico(Historico hist) throws DataAccessException {
        historicoRepository.save(hist);
    }

}
