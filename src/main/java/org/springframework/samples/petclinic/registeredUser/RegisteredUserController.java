package org.springframework.samples.petclinic.registeredUser;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisteredUserController {

	private static final String VIEWS_REGISTERUSER_DETAILS = "registeredUser/registeredUserDetails";

	private static final String VIEWS_REGISTERUSER_CREATE_EDIT = "registeredUser/createOrUpdateRegisteredUserForm";

	private final RegisteredUserService registeredUserService;

	private final UserService userService;

	@Autowired
		public RegisteredUserController(RegisteredUserService registeredUserService, UserService userService,
				AuthoritiesService authoritiesService) {
			this.registeredUserService = registeredUserService;
			this.userService = userService;
			
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/myProfile")
	public String showRegisteredUserById(RegisteredUser registeredUser, BindingResult result,
			Map<String, Object> model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		RegisteredUser ru = this.registeredUserService
				.findRegisteredUserByUsername(this.userService.findUser(username).orElse(null));
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

		} else {
			registeredUser.setId(registeredUserId);
			this.registeredUserService.saveRegisteredUser(registeredUser);
			return "redirect:/registeredUser/{registeredUserId}";
		}
	}

	// Hacer listado para que el admin pueda ver todos los usurios
	@GetMapping(value = "/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("registeredUser", new RegisteredUser());
		return "registeredUser/findRegisteredUser";
	}

	@GetMapping(value = "/registeredUser")
	public String processFindForm(RegisteredUser registeredUser, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request to return all records
		if (registeredUser.getName() == "" || registeredUser.getName() == null) {
			Pageable paginable = PageRequest.of(0, 5, Sort.by("name"));
			Page<RegisteredUser> paginas = registeredUserService.findAllpageablePage(paginable);
			model.put("number", paginas.getNumber());
			model.put("totalPages", paginas.getTotalPages());
			model.put("totalElements", paginas.getTotalElements());
			model.put("search", registeredUser.getName());
			model.put("data",paginas.getContent());
			return "registeredUser/registeredUserList";
			
		}
		// find RegisteredUsers by Name
		Collection<RegisteredUser> results = this.registeredUserService
				.findRegisteredUserByName(registeredUser.getName().trim());
		if (results.isEmpty()) {
			// no users found
			result.rejectValue("name", "notFound", "not found");
			return "registeredUser/findRegisteredUser";
		} else if (results.size() == 1) {
			// 1 result found
			//registeredUser = results.iterator().next();
			return "redirect:/registeredUser/" + results.stream().findFirst().get().getId();
		} else {
			// multiple results found
			Pageable paginable = PageRequest.of(0, 5, Sort.by("name"));
			Page<RegisteredUser> paginas = registeredUserService.findByNameFromSubstring(registeredUser.getName(), paginable);
			model.put("number", paginas.getNumber());
			model.put("totalPages", paginas.getTotalPages());
			model.put("totalElements", paginas.getTotalElements());
			model.put("search", registeredUser.getName());
			model.put("data",paginas.getContent());
			return "registeredUser/registeredUserList";
		}

	}

	@GetMapping(value= "/registeredUser/userPages/{page}")
	public String showSearchPages(@RequestParam(name="search", required = false) String search, @PathVariable(name = "page") int pagN, Map<String, Object> model){
		
		Pageable paginable = PageRequest.of(pagN, 5, Sort.by("name"));
		Page<RegisteredUser> paginas = null;

		if(search==null||search==""){
			paginas = registeredUserService.findAllpageablePage(paginable);
		}else{
			paginas = registeredUserService.findByNameFromSubstring(search, paginable);
		}

		model.put("number", paginas.getNumber());
		model.put("totalPages", paginas.getTotalPages());
		model.put("totalElements", paginas.getTotalElements());
		model.put("search", search);
		model.put("data",paginas.getContent());
		return "registeredUser/registeredUserList";
	}


	// En la pestaña Find RegisteredUSers que funcione el add registeredUser
	@GetMapping(value = "/registeredUser/new")
	public String initCreationForm(Map<String, Object> model) {
		RegisteredUser registeredUser = new RegisteredUser();
		model.put("registeredUser", registeredUser);
		return VIEWS_REGISTERUSER_CREATE_EDIT;
	}

	@PostMapping(value = "/registeredUser/new")
	public String processCreationForm(@Valid RegisteredUser registeredUser, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_REGISTERUSER_CREATE_EDIT;
		} else {
		
			// creating owner, user and authorities
			this.registeredUserService.saveRegisteredUser(registeredUser);
			return "redirect:/registeredUser";
		}
	}

	@GetMapping(value = "/registeredUser/{registeredUserId}/delete")
	public String deletePlayerAdmin(@PathVariable("registeredUserId") int registeredUserId) {
		RegisteredUser user = registeredUserService.findRegisteredUserById(registeredUserId);
		registeredUserService.deleteUser(user);
		
		return "redirect:/registeredUser";
	}
	@GetMapping(value = "/registeredUser/auditoria")
	public String auditoriaUsuarios(Map<String, Object> model) {

		Collection<RegisteredUser> usuarios = null;
		if(registeredUserService.findRegisteredUser().stream().anyMatch(c->c.getUser().getLastModifiedDate()!=null)){
			 usuarios =  registeredUserService.findRegisteredUser().stream().filter(c->c.getUser().getLastModifiedDate()!=null).collect(Collectors.toSet());
		}

		model.put("registeredUsers", usuarios);
		
		return "registeredUser/listaAuditoria";
	}



}
