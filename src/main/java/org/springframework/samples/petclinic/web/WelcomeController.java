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
		Person Juanlu = new Person();
			Juanlu.setFirstName("Juan Luis");
			Juanlu.setLastName("Ruano");
			persons.add(Juanlu);
		Person Ale = new Person();
			Ale.setFirstName("Alejandro");
			Ale.setLastName("Ortiz");
			persons.add(Ale);
		Person JuanCa = new Person();
			JuanCa.setFirstName("JuanCa");
			JuanCa.setLastName("Lopez");
			persons.add(JuanCa);
		model.put("persons", persons);
		model.put("title", "El Buscaminas");
		model.put("group", "L2-1");
	
	    return "welcome";
	  }
}
