package org.springframework.samples.petclinic.logro;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class logroServiceTests {
    @Autowired
    protected LogroService logroService;

    @Test
	void shouldFindLogroById() {
		Optional<Logro> logro = this.logroService.getLogroById(1);
        Logro logro2=logro.get();
		assertThat(logro2.getCondicion()).isEqualTo("Prueba");
		
	}
}
