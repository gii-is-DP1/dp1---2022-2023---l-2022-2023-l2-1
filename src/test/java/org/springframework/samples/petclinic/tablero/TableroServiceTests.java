package org.springframework.samples.petclinic.tablero;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.partidas.PartidaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TableroServiceTests {
    @Autowired
    protected TableroService tableroService;

    @Autowired
    protected PartidaService partidaService;

    @Test
    void shouldFindBoardById(){
        Tablero tablero=this.tableroService.getBoardById(1);
        assertThat(tablero.getPartida()).isEqualTo(partidaService.getById(1));
        //assertThat(tablero.getColumnas()).isEqualTo(10);
    }
    
    @Test
	@Transactional
	public void shouldInsertTablero() {
		List<Tablero> tableros = this.tableroService.getAllBoards();
		int found = tableros.size();

     
		Tablero tablero = new Tablero();

        tablero.setColumnas(10);
        tablero.setFilas(8);;
        tablero.setMinas(10);;
        tablero.setPartida(partidaService.getById(2));

		this.tableroService.saveBoard(tablero);
		assertThat(tablero.getId().longValue()).isNotEqualTo(0);

		tableros = this.tableroService.getAllBoards();
		assertThat(tableros.size()).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	void shouldUpdateTablero() {
		Tablero tablero = this.tableroService.getBoardById(1);
		Integer oldNumMinas = tablero.getMinas();
		Integer newNumMinas = 12;

		tablero.setMinas(newNumMinas);
		this.tableroService.saveBoard(tablero);

		// retrieving new name from database
		tablero = this.tableroService.getBoardById(1);
		assertThat(tablero.getMinas()).isEqualTo(newNumMinas);
	}
}
