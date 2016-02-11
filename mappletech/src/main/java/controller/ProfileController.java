package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import vm.UserVM;

@Controller
@RequestMapping("/profil")
@SessionAttributes("sessUser")
public class ProfileController {
	//Local RUN
	//private final String URI = "http://localhost:8080/tech2/rest/profil/";
	//Deployment RUN
	private final String URI = "http://130.237.84.211:8080/mappletech/rest/profil";
	
	
	@RequestMapping("/")
	public ModelAndView getUserProfile(@ModelAttribute("sessUser")UserVM userVM)
	{
		System.out.println("UserVM: " + userVM.getUsername());
		RestTemplate restTemplate = new RestTemplate();
		UserVM u = restTemplate.postForObject(URI + "/getUser", userVM.getUsername(), UserVM.class);
		System.out.println("From database: " + u.getUsername());
		ModelAndView modelView = new ModelAndView("/profil/index");
		return modelView;
	}
	
	@RequestMapping("/uppdatera-profil/")
	public ModelAndView updateUserProfile()
	{
		return new ModelAndView("/profil/uppdatera-profil/index");
	}

}
