package org.springframework.samples.petclinic.registeredUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.user.AuthoritiesService;
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

    // @Transactional(readOnly = true)
	// public RegisteredUser findByUsername(String username) throws DataAccessException {
	// 	return registeredUserRepository.findByUsername(username);
	// }

    @Transactional
	public void saveRegisteredUser(RegisteredUser registeredUser) throws DataAccessException {
		//creating owner
		registeredUserRepository.save(registeredUser);		
		//creating user
		userService.saveUser(registeredUser.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(registeredUser.getUser().getUsername(), "registeredUser");
	}	
    
}
