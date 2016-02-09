package controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import vm.UserVM;

@Controller
public class AdminController {

	private final String URI = "http://130.237.84.211:8080/mappletech/rest/admin";
	
	
	@RequestMapping(value = { "/administrator/anvandare" }, method = RequestMethod.GET)
	public ModelAndView addUsersGet() {
		System.out.println("In GET anvandare...");
		ModelAndView mv = new ModelAndView("administrator/anvandare");
		mv.addObject("uservm", new UserVM());
		return mv;
	}

	@RequestMapping(value = "/administrator/anvandare", method = RequestMethod.POST)
	public String addUsersPost(@ModelAttribute("uservm") UserVM userVm, Model model) {

		System.out.println("In POST anvandare...");
		UserVM newUser = new UserVM(userVm.getUsername(), generatePassword(), userVm.getFullName(), userVm.getPrivilege());
		RestTemplate restTemplate = new RestTemplate();
		boolean userExists = restTemplate.postForObject(URI, newUser, Boolean.class);
		
		if (userExists) {
			System.out.println("Failed");
			return "redirect:/administrator/anvandare";
		}
		System.out.println("Success!");
		return "redirect:/administrator";
	}

	private String generatePassword() {
		final String charset = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}
}
