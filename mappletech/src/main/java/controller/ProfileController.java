package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/Profil")
public class ProfileController {
	private final String URI = "http://130.237.84.211:8080/mappletech/rest/profile/";
	
	
	@RequestMapping("/")
	public ModelAndView getUserProfile()
	{
		return new ModelAndView("/profil/index");
	}

}
