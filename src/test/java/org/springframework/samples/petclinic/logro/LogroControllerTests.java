package org.springframework.samples.petclinic.logro;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.samples.petclinic.historico.Historico;
import org.springframework.samples.petclinic.historico.HistoricoService;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.registeredUser.RegisteredUser;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = LogroController.class,
		includeFilters = @ComponentScan.Filter(value = LogroFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
public class LogroControllerTests {
    @Autowired
    protected LogroController logroController;

    @MockBean 
    private LogroService logroService;

    @MockBean 
    private HistoricoService historicoService;

    @MockBean 
    private RegisteredUserService registeredUserService;

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

        Condicion condicion=new Condicion();
        condicion.setId(1);
        condicion.setName("Name");
        condicion.setPredicado("Minas");
        condicion.setComparador(">");
        
        Historico historico=new Historico();
        historico.setId(1);
        historico.setRegisteredUserId(4);
		historico.setPartidasTotales(0);
		historico.setPartidasGanadas(0);
		historico.setMinasEncontradas(0);
		historico.setPuntuacion(0);
		historico.setTiempoTotalJuego(LocalTime.MIN);
		historico.setTiempoMedioPartida(LocalTime.MIN);

        Set<Historico> historicos=new HashSet<>();
        historicos.add(historicoService.getHistoricoByRegisteredUserId(1));
       
        Logro logro = new Logro();
        logro.setId(1);
        logro.setTitulo("HackerMan");
		logro.setDescripcion("hacker-skills");
		logro.setCondicion(condicion);
		logro.setValor(2);
		logro.setHistoricos(historicos);

        given(this.logroService.getLogroById(1)).willReturn(logro);
    }

    @WithMockUser(value = "spring")
	@Test
    @Disabled
	void testShowEstadisticasByUserId() throws Exception {
        mockMvc.perform(get("/registeredUser/{registeredUserId}/myLogros",4)).andExpect(status().isOk()).andExpect(model().attributeExists("registeredUser"))
        .andExpect(view().name("estadisticas/logrosDeUsuario"));

	}

    @WithMockUser(value = "spring")
	@Test
    @Disabled
	void testShowAllLogros() throws Exception {
        mockMvc.perform(get("/logros")).andExpect(status().isOk()).andExpect(model().attributeExists("registeredUser"))
        .andExpect(view().name("estadisticas/listaLogros"));
	}

    
}
