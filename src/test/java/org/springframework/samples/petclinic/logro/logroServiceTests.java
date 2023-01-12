package org.springframework.samples.petclinic.logro;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.historico.Historico;
import org.springframework.samples.petclinic.historico.HistoricoService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LogroServiceTests {
    @Autowired
    protected LogroService logroService;

    @Autowired
    protected HistoricoService historicoService;

    @Test
	void shouldFindLogroById() {
		Logro logro = this.logroService.getLogroById(1);
       
		assertThat(logro.getTitulo()).isEqualTo("Prueba");
		
	}

    @Test
	@Transactional
	public void shouldInsertlogro() {
		Set<Logro> logros = this.logroService.findAllLogros();
		int found = logros.size();

		Logro logro = new Logro();
        Set<Historico> historicos=new HashSet<>();
        historicos.add(historicoService.getHistoricoByRegisteredUserId(1));

        logro.setTitulo("HackerMan");
		logro.setDescripcion("hacker-skills");
		logro.setCondicion(logroService.getAllCondiciones().get(0));
		logro.setValor(2);
		logro.setHistoricos(historicos);


		this.logroService.save(logro);
		assertThat(logro.getId().longValue()).isNotEqualTo(0);

		logros = this.logroService.findAllLogros();
		assertThat(logros.size()).isEqualTo(found + 1);
	}

	 @Test
	 @Transactional
	 void shouldUpdatelogro() {
	 	Logro logro = this.logroService.getLogroById(1);
	 	String oldTitulo=logro.getTitulo();

	 	String newTitulo="Prueba";
	 	logro.setTitulo(newTitulo);

	 	this.logroService.save(logro);

	 	// retrieving new name from database
	 	logro = this.logroService.getLogroById(1);
	 	assertThat(logro.getTitulo()).isEqualTo(newTitulo);
	}

    @Test
	@Transactional
	public void shouldDeletelogro() {
		Set<Logro> logros = this.logroService.findAllLogros();
		int found = logros.size();

		this.logroService.deleteLogroById(1);

		logros = this.logroService.findAllLogros();
		assertThat(logros.size()).isEqualTo(found - 1);
	}

}
