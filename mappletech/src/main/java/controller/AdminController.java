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
	
	@RequestMapping(value = { "/administrator/lagg-till-anvandare" }, method = RequestMethod.GET)
	public ModelAndView addUsersGet() {
		System.out.println("In GET anvandare hantera");
		ModelAndView mv = new ModelAndView("administrator/lagg-till-anvandare/index");
		mv.addObject("uservm", new UserVM());
		return mv;
	}

	@RequestMapping(value = "/administrator/lagg-till-anvandare", method = RequestMethod.POST)
	public String addUsersPost(@ModelAttribute("uservm") UserVM userVm, Model model) {
		
		System.out.println("In POST anvandare hantera");
		UserVM newUser = new UserVM(userVm.getUsername(), generatePassword(), userVm.getFullName(), userVm.getPrivilege());
		RestTemplate restTemplate = new RestTemplate();
		boolean userExists = restTemplate.postForObject(URI, newUser, Boolean.class);
		
		if (userExists) {
			System.out.println("Failed");
			return "redirect:/administrator/lagg-till-anvandare";
		}
		System.out.println("Success!");
		return "redirect:/administrator/anvandare";
	}
	
	
	@RequestMapping(value = { "/administrator/anvandare" }, method = RequestMethod.GET)
	public ModelAndView editUsersGet() {
		System.out.println("In GET anvandare Edit...");
		ModelAndView mv = new ModelAndView("administrator/anvandare/index");
		mv.addObject("uservm", new UserVM());
		return mv;
	}

	@RequestMapping(value = "/administrator/anvandare", method = RequestMethod.POST)
	public String editUsersPost(@ModelAttribute("uservm") UserVM userVm, Model model) {

		System.out.println("In POST anvandare/editUser...");
		UserVM newUser = new UserVM();
		
		newUser.setUsername(userVm.getUsername());
		newUser.setFullName(userVm.getFullName());
		newUser.setPhoneNumber(userVm.getPhoneNumber());
		newUser.setMobileNumber(userVm.getMobileNumber());
		newUser.setAddress(userVm.getAddress());
		newUser.setPrivilege(userVm.getPrivilege());
		
		System.out.println("Edit Success!");
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
