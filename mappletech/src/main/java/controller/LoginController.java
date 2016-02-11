package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import vm.UserVM;

@Controller
@SessionAttributes("sessUser")
public class LoginController {
	private final String URI = "http://130.237.84.211:8080/mappletech/rest/login";

//	// Omdirigerar just nu till login
//	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
//	public String start() {
//		return "redirect:/login";
//	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("In GET Login...");
		return new ModelAndView("login/index","uservm", new UserVM());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(@ModelAttribute("uservm") UserVM userVm) {

		System.out.println("In POST Login...");
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

	private String passwordHash(String pwd) {

		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		msgDigest.update(pwd.getBytes());
		byte[] digest = msgDigest.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

}
