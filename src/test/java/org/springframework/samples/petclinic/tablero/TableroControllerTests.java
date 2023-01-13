package org.springframework.samples.petclinic.tablero;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.historico.HistoricoService;
import org.springframework.samples.petclinic.logro.LogroService;
import org.springframework.samples.petclinic.partidas.Dificultad;
import org.springframework.samples.petclinic.partidas.Partida;
import org.springframework.samples.petclinic.partidas.PartidaService;
import org.springframework.samples.petclinic.partidas.TipoDePartida;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = TableroControler.class,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
public class TableroControllerTests {
    @Autowired
    private TableroControler tableroControler;

    @MockBean
    private TableroService tableroService;

    @MockBean
    private PartidaService partidaService;

    @MockBean
    private AuthoritiesService authoritiesService;

    @MockBean
    private HistoricoService historicoService;

    @MockBean
    private UserService userService;

    @MockBean
    private LogroService logroService;

    @MockBean
    private RegisteredUserService registeredUserService;

    @Autowired
    private MockMvc mockMvc;

    private Tablero tablero;
    private Partida partida;
    private Dificultad dificultad;
    private TipoDePartida tipoDePartida;

    @BeforeEach
    void setup(){
        dificultad=new Dificultad();
        dificultad.setId(1);
        dificultad.setName("Facil");

        tipoDePartida=new TipoDePartida();
        tipoDePartida.setId(1);
        tipoDePartida.setName("Individual");

        partida= new Partida();
        partida.setId(3);
        partida.setRegisteredUserId(1);
        partida.setTiempoDeJuego(null);
        partida.setTiempoDeJuegoInvitado(null);
        partida.setDificultad(dificultad);
        partida.setTipo(tipoDePartida);
        partida.setIdInvitado(2);
        partida.setPrivada(false);
        partida.setContrasenia(null);
        partida.setResultado(null);

        tablero= new Tablero();
        tablero.setId(10);
        tablero.setColumnas(10);
        tablero.setFilas(8);
        tablero.setMinas(10);
        tablero.setPartida(partida);

        given(this.tableroService.getBoardById(10)).willReturn(tablero);
    }

    @WithMockUser(value = "spring")
	@Test
    @Disabled
	void testTableroView() throws Exception {
		mockMvc.perform(get("/tablero/{partida_id}", 3)).andExpect(status().isOk())
				//.andExpect(model().attribute("tablero", hasProperty("columnas", is(10))))
				.andExpect(view().name("tablero/tablero"));
	}
    
    @WithMockUser(value = "spring")
    @Test
    @Disabled
	void testPostTableros() throws Exception {
		mockMvc.perform(get("/postTablero/{minasEncontradas}/{tiempoEmpleado}/{esVictoria}/{partidaId}"
        ,5,LocalTime.ofSecondOfDay(60),false,3)).andExpect(status().isOk())
				.andExpect(view().name("redirect:/partida/new"));
                //.andExpect(model().attributeExists(""));
    }   
    

}
