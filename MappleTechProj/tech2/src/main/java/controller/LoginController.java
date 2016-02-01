package controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import vm.UserVM;

@Controller
public class LoginController {

	private UserVM userVm = new UserVM();
	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
		
		model.addAttribute("user", this.userVm);
		ModelAndView mv = new ModelAndView("login/login");
		mv.addObject("user", this.userVm);
		return mv;
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") UserVM userVm, Model model) {
		
		System.out.println("Username: "+userVm.getUsername()+"pwd: "+userVm.getPassword());
		try {
			UserVM loggedInUser = new UserVM(userVm.getUsername(), passwordHash(userVm.getPassword()));
			String jsonString = mapper.writeValueAsString(loggedInUser);
			
		} catch (JsonProcessingException | NoSuchAlgorithmException e) {
			System.out.println("Error: "+e.getStackTrace());
		}finally{
			
			
		}
		return "redirect:/";
	}
	
	
	private String passwordHash(String pwd) throws NoSuchAlgorithmException{
		
		MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
		msgDigest.update(pwd.getBytes());
		byte[] digest = msgDigest.digest();
		return digest.toString();
		
	}

}
