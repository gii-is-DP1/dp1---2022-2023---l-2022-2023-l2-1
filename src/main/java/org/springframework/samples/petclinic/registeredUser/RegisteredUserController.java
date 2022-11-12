package org.springframework.samples.petclinic.registeredUser;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisteredUserController {

    private static final String VIEWS_REGISTERUSER_DETAILS = "registeredUser/registeredUserDetails";

    private static final String VIEWS_REGISTERUSER_CREATE_EDIT = "registeredUser/createOrUpdateRegisteredUserForm";


    private final RegisteredUserService registeredUserService;

	 
	private final UserService userService;

	@Autowired
	public RegisteredUserController(RegisteredUserService registeredUserService, UserService userService, AuthoritiesService authoritiesService) {
		this.registeredUserService = registeredUserService;
		this.userService = userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/myProfile")
    public String showRegisteredUserById(RegisteredUser registeredUser,  BindingResult result, Map<String, Object> model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        RegisteredUser ru = this.registeredUserService.findRegisteredUserByUsername(this.userService.findUser(username).orElse(null));
        return "redirect:/registeredUser/" + ru.getId();
	}

	
	@GetMapping("/registeredUser/{registeredUserId}")
	public ModelAndView showRegisteredUser(@PathVariable("registeredUserId") int registeredUserId) {
		ModelAndView mav = new ModelAndView(VIEWS_REGISTERUSER_DETAILS);
		mav.addObject(registeredUserService.findRegisteredUserById(registeredUserId));
		return mav;
	}

	@GetMapping("/registeredUser/{registeredUserId}/edit")
	public String initUpdateRegisteredUserForm(@PathVariable("registeredUserId") int registeredUserId, Model model) {
		RegisteredUser registeredUser = this.registeredUserService.findRegisteredUserById(registeredUserId);
		model.addAttribute(registeredUser);
		return VIEWS_REGISTERUSER_CREATE_EDIT;
	}

	@PostMapping(value = "/registeredUser/{registeredUserId}/edit")
	public String processUpdateRegisteredUserForm(@Valid RegisteredUser registeredUser, BindingResult result,
			@PathVariable("registeredUserId") int registeredUserId) {
		if (result.hasErrors()) {
			return VIEWS_REGISTERUSER_CREATE_EDIT;
			
		}
		else {
			registeredUser.setId(registeredUserId);
			this.registeredUserService.saveRegisteredUser(registeredUser);
			return "redirect:/registeredUser/{registeredUserId}";
		}
	}

}
