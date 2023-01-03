package org.springframework.samples.petclinic.partidas;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class TipoDePartidaFormatter implements Formatter<TipoDePartida>{

    
	private final PartidaService partidaService;

	@Autowired
	public TipoDePartidaFormatter(PartidaService partidaService) {
		this.partidaService = partidaService;
	}

	@Override
	public String print(TipoDePartida tipo, Locale locale) {
		return tipo.getName();
	}
    
    @Override
	public TipoDePartida parse(String text, Locale locale) throws ParseException {
		List<TipoDePartida> tipos = partidaService.getAllTiposDePartidas();
		for (TipoDePartida tipo : tipos) {
			if (tipo.getName().equals(text)) {
				return tipo;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}
    
}
