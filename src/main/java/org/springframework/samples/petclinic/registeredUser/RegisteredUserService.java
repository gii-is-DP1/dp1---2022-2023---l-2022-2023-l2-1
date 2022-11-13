package org.springframework.samples.petclinic.registeredUser;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisteredUserService {


    private RegisteredUserRepository registeredUserRepository;	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
		this.registeredUserRepository = registeredUserRepository;
    }

	@Transactional(readOnly = true)
	public RegisteredUser findRegisteredUserById(int id) throws DataAccessException {
		return registeredUserRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public RegisteredUser findRegisteredUserByUsername(User user) throws DataAccessException {
		return registeredUserRepository.findByUsername(user);
	}


	@Transactional(readOnly = true)
	public Collection<RegisteredUser> findRegisteredUser() throws DataAccessException {
		return registeredUserRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Collection<RegisteredUser> findRegisteredUserByName(String name) throws DataAccessException {
		return registeredUserRepository.findByName(name);
	}


    @Transactional
	public void saveRegisteredUser(RegisteredUser registeredUser) throws DataAccessException {
		//creating registeredUser
		registeredUserRepository.save(registeredUser);		
		//creating user
		userService.saveUser(registeredUser.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(registeredUser.getUser().getUsername(), "registeredUser");
	}	
    
	@Transactional
	public void deleteRegisteredUser(int id) throws DataAccessException {
		registeredUserRepository.deleteById(id);
	}

}
