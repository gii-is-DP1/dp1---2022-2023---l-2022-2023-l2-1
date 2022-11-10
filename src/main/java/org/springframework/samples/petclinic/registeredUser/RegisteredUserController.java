package org.springframework.samples.petclinic.registeredUser;

import java.security.Principal;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisteredUserController {

    private static final String VIEWS_REGISTERUSER_DETAILS = "registeredUser/registeredUserDetails";

    private static final String VIEWS_REGISTERUSER_CREATE_EDIT = "registeredUser/createOrUpdateRegisteredUserForm";


    private final RegisteredUserService registeredUserService;

	@Autowired
	public RegisteredUserController(RegisteredUserService registeredUserService, UserService userService, AuthoritiesService authoritiesService) {
		this.registeredUserService = registeredUserService;
	}


	@GetMapping("/registeredUser/myProfile")
	public ModelAndView showRegisteredUser(Principal principal) {
		ModelAndView mav = new ModelAndView(VIEWS_REGISTERUSER_DETAILS);
		mav.addObject(registeredUserService.findRegisteredUserByUsername(principal.getName()));
		return mav;
	}
	//Crear un método Post-Mapping para que me guarde el username y se pueda mostrar

	@GetMapping("/registeredUser/myProfile/edit")
	public String createInitForm(Principal principal, Model model) {
		RegisteredUser registeredUser = this.registeredUserService.findRegisteredUserByUsername(principal.getName());
		model.addAttribute(registeredUser);
		return VIEWS_REGISTERUSER_CREATE_EDIT;
	}

	
	// @GetMapping(value = "/owners/{ownerId}/edit")
	// public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
	// 	Owner owner = this.ownerService.findOwnerById(ownerId);
	// 	model.addAttribute(owner);
	// 	return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	// }



	

}
