package org.springframework.samples.petclinic.logro;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class LogroFormatter implements Formatter<Logro>{
    private LogroService logroService;

    @Autowired
	public LogroFormatter(LogroService logroService) {
		this.logroService = logroService;
	}

    @Override
    public String print(Logro log, Locale locale) {
        return log.getTitulo();
    }

    @Override
    public Logro parse(String text, Locale locale) throws ParseException {
        Set<Logro> logros = logroService.findAllLogros();
		for (Logro dif : logros) {
			if (dif.getTitulo().equals(text)) {
				return dif;
			}
		}
		throw new ParseException("type not found: " + text, 0);
    }
    
}
