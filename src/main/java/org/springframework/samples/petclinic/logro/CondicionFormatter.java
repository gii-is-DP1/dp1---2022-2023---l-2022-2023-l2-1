package org.springframework.samples.petclinic.logro;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class CondicionFormatter implements Formatter<Condicion>{
    private LogroService logroService;

    @Autowired
	public CondicionFormatter(LogroService logroService) {
		this.logroService = logroService;
	}

    @Override
    public String print(Condicion condicion, Locale locale) {
        return condicion.getName();
    }

    @Override
    public Condicion parse(String text, Locale locale) throws ParseException {
        List<Condicion> condicions = logroService.getAllCondiciones();
		for (Condicion dif : condicions) {
			if (dif.getName().equals(text)) {
				return dif;
			}
		}
		throw new ParseException("Condicion not found: " + text, 0);
    }
    
}

