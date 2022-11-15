package org.springframework.samples.petclinic.registeredUser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class registeredUserServiceTest {

	@Autowired
	protected RegisteredUserService registeredUserService;

	@Test
	void shouldFindRegisteredUserByName() {
		Collection<RegisteredUser> registeredUsers = this.registeredUserService.findRegisteredUserByName("Pepito");
		assertThat(registeredUsers.size()).isEqualTo(1);

		registeredUsers = this.registeredUserService.findRegisteredUserByName("Pepitos");
		assertThat(registeredUsers.isEmpty()).isTrue();
	}

	@Test
	@Transactional
	public void shouldInsertRegisteredUser() {
		Collection<RegisteredUser> registeredUsers = this.registeredUserService.findRegisteredUserByName("Sam");
		int found = registeredUsers.size();

		RegisteredUser registeredUser = new RegisteredUser();
		registeredUser.setName("Sam");
		registeredUser.setEmail("email@email.com");
		User user = new User();
		user.setUsername("Sam");
		user.setPassword("SecretPassword#1");
		user.setEnabled(true);
		registeredUser.setUser(user);

		this.registeredUserService.saveRegisteredUser(registeredUser);
		assertThat(registeredUser.getId().longValue()).isNotEqualTo(0);

		registeredUsers = this.registeredUserService.findRegisteredUserByName("Sam");
		assertThat(registeredUsers.size()).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	void shouldUpdateRegisteredUser() {
		RegisteredUser registeredUser = this.registeredUserService.findRegisteredUserById(1);
		String oldName = registeredUser.getName();
		String newName = oldName + "X";

		registeredUser.setName(newName);
		this.registeredUserService.saveRegisteredUser(registeredUser);

		// retrieving new name from database
		registeredUser = this.registeredUserService.findRegisteredUserById(1);
		assertThat(registeredUser.getName()).isEqualTo(newName);
	}
}