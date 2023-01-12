package org.springframework.samples.petclinic.historico;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HistoricoServiceTests {
    
    @Autowired
    protected HistoricoService historicoService;

    @Test
    void shouldHistoricoByRegisteredUserId(){
        Historico historico=this.historicoService.getHistoricoByRegisteredUserId(1);
        assertThat(historico.getId()).isEqualTo(1);
    }
    @Test
	@Transactional
	public void shouldInsertHistorico() {
		List<Historico> historicos = this.historicoService.findAll();
		int found = historicos.size();

		Historico historico = new Historico();
        historico.setRegisteredUserId(4);
		historico.setPartidasTotales(0);
		historico.setPartidasGanadas(0);
		historico.setMinasEncontradas(0);
		historico.setPuntuacion(0);
		historico.setTiempoTotalJuego(LocalTime.MIN);
		historico.setTiempoMedioPartida(LocalTime.MIN);

		this.historicoService.saveHistorico(historico);
		assertThat(historico.getId().longValue()).isNotEqualTo(0);

		historicos = this.historicoService.findAll();
		assertThat(historicos.size()).isEqualTo(found + 1);
	}

    @Test
	@Transactional
	void shouldUpdateHistorico() {
		Historico historico = this.historicoService.getHistoricoByRegisteredUserId(1);
		Integer oldMinasEnc=historico.getMinasEncontradas();
		Integer newMinasEnc=5;

		historico.setMinasEncontradas(newMinasEnc);

		this.historicoService.saveHistorico(historico);

		// retrieving new name from database
		historico = this.historicoService.getHistoricoByRegisteredUserId(1);
		assertThat(historico.getMinasEncontradas()).isEqualTo(newMinasEnc);
	}


}
