package org.springframework.samples.petclinic.user;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTest {

    @Autowired
    protected UserService userService;

    @Test
    @Transactional
    public void shouldFindUser(){
        User u=new User();
        u.setUsername("Pepito");
        u.setPassword("supersecretpassword");
        u.setEnabled(true);

        Optional<User> user = this.userService.findUser("Pepito");
        assertThat(user.equals(u));
    }

    @Test
    @Transactional
    public void shouldInsertUser(){

        User u=new User();
        u.setUsername("Pepito");
        u.setPassword("supersecretpassword");
        u.setEnabled(true);


        this.userService.saveUser(u);
        assertThat(u.getUsername()).isEqualTo("Pepito");
    }
}