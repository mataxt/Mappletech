package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import model.User;
import testdao.UserDAO;
import vm.UserVM;

@Controller
@RequestMapping("/profil")
@SessionAttributes("sessUser")
public class ProfileController {
	//Local RUN
	private final String URI = "http://localhost:8080/tech2/rest/profil/";
	//Deployment RUN
	//private final String URI = "http://130.237.84.211:8080/mappletech/rest/profil";
	
	
	@RequestMapping("/")
	public ModelAndView getUserProfile(@ModelAttribute("sessUser")UserVM userVM)
	{
		System.out.println("UserVM: " + userVM.getUsername());
		RestTemplate restTemplate = new RestTemplate();
		UserVM u = restTemplate.postForObject(URI + "/getUser", userVM.getUsername(), UserVM.class);
		System.out.println("From database: " + u.getUsername());
		ModelAndView modelView = new ModelAndView("/profil/index");
		modelView.addObject("uservm", u);
		return modelView;
	}
	
	@RequestMapping(value="/uppdatera-profil/",method = RequestMethod.GET)
	public ModelAndView updateUserProfile()
	{
		return new ModelAndView("/profil/uppdatera-profil/index","uservm",new UserVM());
	}
	
	@RequestMapping(value="/uppdatera-profil/",method = RequestMethod.POST)
	public ModelAndView updateUserProfile(
			@ModelAttribute("sessUser")UserVM userVM,
			@ModelAttribute("uservm")UserVM userChange,
			@RequestParam(required = false, value="name") String nameButton,
			@RequestParam(required = false, value="emailButton") String emailButton,
			@RequestParam(required = false, value="phone") String phoneButton,
			@RequestParam(required = false, value="mobile") String mobileButton,
			@RequestParam(required = false, value="passwordButton") String passwordButton	
			)
	{
		RestTemplate restTemplate = new RestTemplate();
		UserVM sessionUser = restTemplate.postForObject(URI + "/getUser", userVM.getUsername(), UserVM.class);
		if(nameButton !=null)
		{
			sessionUser.setFullName(userChange.getFullName());
			System.out.println("nameButton");
		}else if(emailButton != null)
		{
			sessionUser.setEmail(userChange.getEmail());
			System.out.println("emailButton");
		}else if(phoneButton != null)
		{
			sessionUser.setPhoneNumber(userChange.getPhoneNumber());
			System.out.println("phoneButton");
		}else if(mobileButton != null)
		{
			sessionUser.setMobileNumber(userChange.getMobileNumber());
			System.out.println("mobileButton");
		}else if(passwordButton != null)
		{
			sessionUser.setPassword(passwordHash(sessionUser.getPassword()));
			System.out.println("passwordButton");
		}
		restTemplate.postForObject(URI + "/updateProfil", sessionUser, Boolean.class);
		return new ModelAndView("/profil/uppdatera-profil/index");
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
