package org.springframework.samples.petclinic.tablero;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TableroService {

        private TableroRepository boardRepo;
    
        @Autowired
        public TableroService(TableroRepository boardRepo) {
            this.boardRepo = boardRepo;
        }
        
        @Transactional(readOnly = true)
        public Tablero getBoardById(Integer id) throws DataAccessException {
            return boardRepo.findById(id).get();
        }
        @Transactional(readOnly = true)
        public List<Tablero> getAllBoards() throws DataAccessException {  
            return boardRepo.findAll();
        }
        @Transactional
        public void saveBoard(Tablero tab) throws DataAccessException {
            boardRepo.save(tab);
        }


    
}
