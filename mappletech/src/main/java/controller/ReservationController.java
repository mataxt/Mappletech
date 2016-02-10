package controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import vm.ReservationVM;
import vm.UserVM;

public class ReservationController {
	
	private final String URI = "http://130.237.84.211:8080/mappletech/rest/reservation/add";
	
	@RequestMapping(value = { "/bokning/boka" }, method = RequestMethod.GET)
	public ModelAndView reserve() {
		System.out.println("In GET Res...");
		return new ModelAndView("bokning/index","resvm", new ReservationVM());
	}
	
	@RequestMapping(value = { "/bokning/mina-bokningar" }, method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("In GET Res...");
		return new ModelAndView("bokning/index","resvm", new ReservationVM());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(@ModelAttribute("uservm") UserVM userVm, Model model) {

		System.out.println("In POST Res...");
		UserVM loggedInUser = new UserVM(userVm.getUsername(), passwordHash(userVm.getPassword()));
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(loggedInUser.getUsername());
		UserVM u = restTemplate.postForObject(URI, loggedInUser, UserVM.class);
		if (u != null) {
			return new ModelAndView("index","sessUser", u);
		} else {
			return new ModelAndView("login/index","uservm", new UserVM());
		}
	}
}
