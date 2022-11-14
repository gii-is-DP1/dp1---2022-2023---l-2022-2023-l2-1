package org.springframework.samples.petclinic.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AuthoritiesServiceTest {

    @Autowired
    protected AuthoritiesService authoritiesService;

    @Autowired
    protected UserService userService;

    @Test
    @Transactional
    public void shouldSaveAutorities() {

        User u = new User();
        u.setUsername("Pepito");
        u.setPassword("super#S3cretp@ssword");
        u.setEnabled(true);

        this.userService.saveUser(u);

        Authorities authorities = new Authorities();
        authorities.setAuthority("admin");
        authorities.setId(1);
        authorities.setUser(u);

        this.authoritiesService.saveAuthorities(authorities);
        assertThat(authorities.getId()).isEqualTo(1);

        this.authoritiesService.saveAuthorities(u.getUsername(), authorities.getAuthority());
        assertThat(authorities.getAuthority()).isEqualTo("admin");

    }

}