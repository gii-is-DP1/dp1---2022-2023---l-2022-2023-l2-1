package org.springframework.samples.petclinic.user;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.registeredUser.RegisteredUserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.HashSet;
import java.util.Set;

@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @MockBean
    private RegisteredUserService registeredUserService;

    @MockBean
    private AuthoritiesService authoritiesService;

    @Autowired
    private MockMvc mockMvc;

    private User user;

    private static final int TEST_AUTH_ID = 20;

    @BeforeEach
    void setup() {
        user = new User();
        user.setUsername("george");
        user.setPassword("g3org3");
        user.setEnabled(true);

        Authorities aut = new Authorities();
        aut.setId(TEST_AUTH_ID);
        aut.setAuthority("registeredUser");
        aut.setUser(user);

        Set<Authorities> setAut = new HashSet<>();
        setAut.add(aut);

        user.setAuthorities(setAut);

    }

    @WithMockUser(value = "spring")
    @Test
    void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/users/new")).andExpect(status().isOk())
                .andExpect(model().attributeExists("registeredUser")).andExpect(view()
                        .name("users/createOwnerForm"));
    }

    @WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrorsSucces() throws Exception {
        mockMvc.perform(post("/users/new")
                .with(csrf())
                .param("user.username", "george22")
                .param("name", "PaaUkk"))
                .andExpect(view().name("users/createOwnerForm"));
    }

    @WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
        mockMvc.perform(post("/users/new")
                .with(csrf())
                .param("user.username", "george22")
                .param("name", "PaaUkk"))
                .andExpect(status().isOk()).andExpect(model().attributeHasErrors("registeredUser"))
                .andExpect(view().name("users/createOwnerForm"));
    }

}
