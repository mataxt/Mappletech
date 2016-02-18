package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import vm.EventVM;
import vm.UserVM;

@Controller
@SessionAttributes("sessUser")
public class LoginController {
	//Local
	private final String URI = "http://localhost:8080/tech2/rest/login";
	//Deployment
	//private final String URI = "http://130.237.84.211:8080/mappletech/rest/login";
	
//	// Omdirigerar just nu till login
//	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
//	public String start() {
//		return "redirect:/login";
//	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("In GET Login...");
		RestTemplate restTemplate = new RestTemplate();
		EventVM[] eventVMArray = restTemplate.getForObject("http://localhost:8080/tech2/rest/event/getLatest", EventVM[].class);
		List<EventVM> eventVMList = Arrays.asList(eventVMArray);
		ModelAndView modelAndView = new ModelAndView("login/index","uservm", new UserVM());
		modelAndView.addObject("eventlist", eventVMList);
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(@ModelAttribute("uservm") UserVM userVm) {

		System.out.println("In POST Login...");
		UserVM loggedInUser = new UserVM(userVm.getUsername(), passwordHash(userVm.getPassword()));
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(loggedInUser.getUsername());
		UserVM u = restTemplate.postForObject(URI, loggedInUser, UserVM.class);
		EventVM[] eventVMArray = restTemplate.getForObject("http://localhost:8080/tech2/rest/event/getAll", EventVM[].class);
		List<EventVM> eventVMList = Arrays.asList(eventVMArray);
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("eventlist", eventVMList);
		if (u != null) {
			modelAndView.addObject("sessUser", u);
			return modelAndView;
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
