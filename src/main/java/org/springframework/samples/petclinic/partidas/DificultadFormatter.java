package org.springframework.samples.petclinic.partidas;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class DificultadFormatter implements Formatter<Dificultad>{

	private final PartidaService partidaService;

	@Autowired
	public DificultadFormatter(PartidaService partidaService) {
		this.partidaService = partidaService;
	}

	@Override
	public String print(Dificultad dif, Locale locale) {
		return dif.getName();
	}
    
    @Override
	public Dificultad parse(String text, Locale locale) throws ParseException {
		List<Dificultad> diffs = partidaService.getAllDifs();
		for (Dificultad dif : diffs) {
			if (dif.getName().equals(text)) {
				return dif;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
  