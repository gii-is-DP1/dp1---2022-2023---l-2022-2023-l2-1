package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		List<Person> persons = new ArrayList<>();
		Person David = new Person();
			David.setFirstName("David");
			David.setLastName("Gavira");
			persons.add(David);
		Person Inma = new Person();
			Inma.setFirstName("Inma");
			Inma.setLastName("Mayo");
			persons.add(Inma);
		Person Juanlu = new Person();
			Juanlu.setFirstName("Juanlu");
			Juanlu.setLastName("Ruano");
			persons.add(Juanlu);
		Person Ale = new Person();
			Ale.setFirstName("Alejandro");
			Ale.setLastName("Ortiz");
			persons.add(Ale);
		Person Juan = new Person();

		model.put("persons", persons);
		model.put("title", "ElBuscaminas");
		model.put("group", "L2-1");
	
	    return "welcome";
	  }
}
