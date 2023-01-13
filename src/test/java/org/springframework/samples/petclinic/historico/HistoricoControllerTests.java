package org.springframework.samples.petclinic.historico;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.logro.Condicion;
import org.springframework.samples.petclinic.logro.Logro;
import org.springframework.samples.petclinic.logro.LogroService;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.partidas.PartidaService;
import org.springframework.samples.petclinic.registeredUser.RegisteredUser;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = HistoricoController.class,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
public class HistoricoControllerTests {

    // @Autowired
    // private HistoricoController historicoController;

    

    @MockBean
    private HistoricoService historicoService;

    @MockBean
    private LogroService logroService;

    @MockBean
    private PartidaService partidaService;

    @MockBean
    private RegisteredUserService registeredUserService;

    @Autowired
	private MockMvc mockMvc;

    private Historico historico;
    @BeforeEach
    void setup(){
        User user=new User();
        user.setUsername("prueba");

        RegisteredUser registeredUser=new RegisteredUser();
        registeredUser.setId(4);
        registeredUser.setName("prueba");
        registeredUser.setEmail("asdfgh@gmail.com");
        registeredUser.setDescription("prueba");
        registeredUser.setUser(user);

        historico= new Historico();
        
        historico.setId(4);
        historico.setRegisteredUserId(4);
		historico.setPartidasTotales(0);
		historico.setPartidasGanadas(0);
		historico.setMinasEncontradas(0);
		historico.setPuntuacion(0);
		historico.setTiempoTotalJuego(LocalTime.MIN);
		historico.setTiempoMedioPartida(LocalTime.MIN);

        given(this.historicoService.getHistoricoByRegisteredUserId(4)).willReturn(historico);
    
    
    }

    @WithMockUser(value = "spring")
	@Test
	void testMuestraHistoricoDeUsuario() throws Exception {
		mockMvc.perform(get("/registeredUser/{registeredUserId}/estadisticas", 4)).andExpect(status().isOk())
				.andExpect(model().attribute("historico", hasProperty("puntuacion", is(0))))
				.andExpect(view().name("estadisticas/estadisticasDeUsuario"));
	}

    @WithMockUser(value = "spring")
	@Test
    @Disabled
	void testMuestraPartidasDeUsuario() throws Exception {
		mockMvc.perform(get("/registeredUser/{registeredUserId}/partidasJugadas", 4)).andExpect(status().isOk())
				.andExpect(model().attribute("registered_user", hasProperty("puntuacion", is(0))))
				.andExpect(view().name("partida/listaDePartidas"));
	}

    
}
