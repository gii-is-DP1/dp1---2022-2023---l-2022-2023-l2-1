package org.springframework.samples.petclinic.registeredUser;

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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for {@link GameRoomController}
 *
 * @author Minus pocus
 */

@WebMvcTest(controllers = RegisteredUserController.class, 
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
	excludeAutoConfiguration = SecurityConfiguration.class)
class registeredUserControllerTest {

	private static final int TEST_REGISTERED_USER_ID = 1;

	@Autowired
	private RegisteredUserController registeredUserController;

	@MockBean
	private RegisteredUserService registeredUserService;

	@MockBean
	private UserService userService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@Autowired
	private MockMvc mockMvc;

	private RegisteredUser pepito;

	@BeforeEach
	void setup() {

		pepito = new RegisteredUser();
		pepito.setId(TEST_REGISTERED_USER_ID);
		pepito.setName("pepito");
		given(this.registeredUserService.findRegisteredUserById(TEST_REGISTERED_USER_ID)).willReturn(pepito);

	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/registeredUser/new")).andExpect(status().isOk()).andExpect(model().attributeExists("registeredUser"))
				.andExpect(view().name("registeredUser/createOrUpdateRegisteredUserForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/registeredUser/new").param("name", "Joe").param("username", "CottonEyeJoe")
            .param("email", "CottonEyeJoe@gmail.com").param("descripcion", "Where did he come from, where did he go")
				.with(csrf()))
				.andExpect(status().is3xxRedirection());
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/registeredUser/new").with(csrf()).param("name", "Joe"))
				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("registeredUser"))
				.andExpect(model().attributeHasFieldErrors("registeredUser", "email"))
				.andExpect(view().name("registeredUser/createOrUpdateRegisteredUserForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitFindForm() throws Exception {
		mockMvc.perform(get("/find")).andExpect(status().isOk()).andExpect(model().attributeExists("registeredUser"))
				.andExpect(view().name("registeredUser/findRegisteredUser"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormSuccess() throws Exception {
		given(this.registeredUserService.findRegisteredUserByName("")).willReturn(Lists.newArrayList(pepito, new RegisteredUser()));

		mockMvc.perform(get("/registeredUser")).andExpect(status().isOk()).andExpect(view().name("registeredUser/registeredUserList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormByName() throws Exception {
		given(this.registeredUserService.findRegisteredUserByName(pepito.getName())).willReturn(Lists.newArrayList(pepito));

		mockMvc.perform(get("/registeredUser").param("name", "pepito")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/registeredUser/" + TEST_REGISTERED_USER_ID));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormNoRegisteredUsersFound() throws Exception {
		mockMvc.perform(get("/registeredUser").param("name", "Unknown Name")).andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrors("registeredUser", "name"))
				.andExpect(model().attributeHasFieldErrorCode("registeredUser", "name", "notFound"))
				.andExpect(view().name("registeredUser/findRegisteredUser"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateRegisteredUserForm() throws Exception {
		mockMvc.perform(get("/registeredUser/{registeredUserId}/edit", TEST_REGISTERED_USER_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("registeredUser"))
				.andExpect(model().attribute("registeredUser", hasProperty("name", is("pepito"))))
				.andExpect(view().name("registeredUser/createOrUpdateRegisteredUserForm"));
	}

	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateRegisteredUserFormSuccess() throws Exception {
		mockMvc.perform(post("/registeredUser/{registeredUserId}/edit", TEST_REGISTERED_USER_ID).with(csrf()).param("firstName", "Joe")
				.param("name", "Pepito").param("username", "pepito").param("email", "pepito@gmail.com"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/registeredUser/{registeredUserId}"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateRegisteredUserFormHasErrors() throws Exception {
		mockMvc.perform(post("/registeredUser/{registeredUserId}/edit", TEST_REGISTERED_USER_ID).with(csrf()).param("name", "Joe")
				.param("username", "CottonEyeJoe")).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("registeredUser"))
				.andExpect(view().name("registeredUser/createOrUpdateRegisteredUserForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowRegisteredUser() throws Exception {
		mockMvc.perform(get("/registeredUser/{registeredUserId}", TEST_REGISTERED_USER_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("registeredUser", hasProperty("name", is("pepito"))))
				.andExpect(view().name("registeredUser/registeredUserDetails"));
	}
}

