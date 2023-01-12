package org.springframework.samples.petclinic.partidas;


import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class PartidasServiceTests {

    @Autowired 
    protected PartidaService partidaService;

    @Test
	void shouldFindPartidaById() {
		Partida partida = this.partidaService.getById(1);
     
		assertThat(partida.getRegisteredUserId()).isEqualTo(1);
		

	}
    

	@Test
	@Transactional
	public void shouldInsertpartida() {
		List<Partida> partidas = this.partidaService.getAll();
		int found = partidas.size();

		Partida partida = new Partida();

        partida.setRegisteredUserId(1);
		partida.setTiempoDeJuego(null);
		partida.setDificultad(partidaService.getAllDifs().get(0));
		partida.setTipo(partidaService.getAllTiposDePartidas().get(0));
		partida.setIdInvitado(null);
		partida.setPrivada(null);
		partida.setContrasenia(null);
		partida.setResultado(null);



		this.partidaService.savePartida(partida);
		assertThat(partida.getId().longValue()).isNotEqualTo(0);

		partidas = this.partidaService.getAll();
		assertThat(partidas.size()).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	void shouldUpdatePartida() {
		Partida partida = this.partidaService.getById(1);
		Dificultad oldDif=partida.getDificultad();

		Dificultad newDif=partidaService.getAllDifs().get(1);
		partida.setDificultad(newDif);

		this.partidaService.savePartida(partida);

		// retrieving new name from database
		partida = this.partidaService.getById(1);
		assertThat(partida.getDificultad()).isEqualTo(newDif);
	}


}
