package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


import vm.UserVM;

@Controller
public class LoginController {
	private final String URI = "http://localhost:8080/tech2/rest/login"; // http://130.237.84.211:8080/tech2/rest/login

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("In GET Login...");
		ModelAndView mv = new ModelAndView("login/index");
		mv.addObject("uservm", new UserVM());
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(@ModelAttribute("uservm") UserVM userVm, Model model) {
		try {
			System.out.println("In POST Login...");
			UserVM loggedInUser = new UserVM(userVm.getUsername(), passwordHash(userVm.getPassword()));
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.postForLocation(URI, loggedInUser, UserVM.class);
	        System.out.println("SUCCESS");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: " + e.getStackTrace());
		}
		return "redirect:/";
	}


	private String passwordHash(String pwd) throws NoSuchAlgorithmException {

		MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
		msgDigest.update(pwd.getBytes());
		byte[] digest = msgDigest.digest();
		return digest.toString();
	}

}
