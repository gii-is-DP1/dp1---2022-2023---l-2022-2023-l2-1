package org.springframework.samples.petclinic.registeredUser;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisteredUserController {

    private static final String VIEWS_REGISTERUSER_DETAILS = "registeredUser/registeredUserDetails";

    private final RegisteredUserService registeredUserService;

	@Autowired
	public RegisteredUserController(RegisteredUserService registeredUserService, UserService userService, AuthoritiesService authoritiesService) {
		this.registeredUserService = registeredUserService;
	}


	@GetMapping("/registeredUser/{registeredUserId}")
	public ModelAndView showRegisteredUser(@PathVariable("registeredUserId") int registeredUserId) {
		ModelAndView mav = new ModelAndView(VIEWS_REGISTERUSER_DETAILS);
		mav.addObject(this.registeredUserService.findRegisteredUserById(registeredUserId));
		return mav;
	}

	//Crear un método Post-Mapping para que me guarde el username y se pueda mostrar



	

}
