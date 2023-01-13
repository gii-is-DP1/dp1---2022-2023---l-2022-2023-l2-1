package org.springframework.samples.petclinic.logro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

public class LogroFormatterTests {
    @Mock
	private LogroService logroService;

	private LogroFormatter logroFormatter;

	@BeforeEach
	void setup() {
		logroFormatter = new LogroFormatter(logroService);
	}

	@Test
	void testPrint() {
		Logro logro = new Logro();
		logro.setTitulo("Hola");;
		String logroName = logroFormatter.print(logro, Locale.ENGLISH);
		assertEquals("Hola", logroName);
	}

	@Test
    @Disabled
	void shouldParse() throws ParseException {
		Mockito.when(logroService.findAllLogros()).thenReturn(makeLogros());
		Logro logro = logroFormatter.parse("Bird", Locale.ENGLISH);
		assertEquals("Bird", logro.getTitulo());
	}

	@Test
    @Disabled
	void shouldThrowParseException() throws ParseException {
		//Mockito.when(logroService.findAllLogros()).thenReturn(makelogros());
		Assertions.assertThrows(ParseException.class, () -> {
			logroFormatter.parse("Fish", Locale.ENGLISH);
		});
	}

    private Set<Logro> makeLogros() {
		Set<Logro> diff= new HashSet<>();
		diff.add(new Logro() {
			{
				setTitulo("Bird");
                setDescripcion("getDescripcion()");
                setCondicion(getCondicion());
                setValor(1);
			}
		});
		
		return diff;
	}
}
