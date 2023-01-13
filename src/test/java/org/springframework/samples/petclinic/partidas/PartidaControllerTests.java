package org.springframework.samples.petclinic.partidas;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.samples.petclinic.registeredUser.RegisteredUser;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PartidaControler.class, 
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
	excludeAutoConfiguration = SecurityConfiguration.class)
public class PartidaControllerTests {
    @MockBean 
    private PartidaService partidaService;

    @MockBean
	private RegisteredUserService registeredUserService;

	@MockBean
	private UserService userService;

    @Autowired
	private MockMvc mockMvc;

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

        Dificultad dificultad=new Dificultad();
        dificultad.setId(1);
        dificultad.setName("Facil");

        TipoDePartida tipoDePartida=new TipoDePartida();
        tipoDePartida.setId(1);
        tipoDePartida.setName("Individual");;
        
        Partida partida=new Partida();
        partida.setId(1);
        partida.setRegisteredUserId(4);
		partida.setTiempoDeJuego(null);
		partida.setDificultad(dificultad);
		partida.setTipo(tipoDePartida);
		partida.setIdInvitado(null);
		partida.setPrivada(null);
		partida.setContrasenia(null);
		partida.setResultado(null);
       
        given(this.partidaService.getById(1)).willReturn(partida);
    }

    @WithMockUser(value = "spring")
    @Test
    @Disabled
	void testShowAllPartidas() throws Exception {
		mockMvc.perform(get("partidas")).andExpect(status().isOk())
				.andExpect(view().name("partida/partidas")).andExpect(model().attributeExists("pet"));
	}
    
}
