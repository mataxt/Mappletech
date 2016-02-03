package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDAO;
import model.User;
import vm.UserVM;

@Controller
public class LoginController {

	private UserVM userVm = new UserVM();
	private ObjectMapper mapper = new ObjectMapper();
	private final String URI="http://localhost:8080/tech2/rest/login"; //http://130.237.84.211:8080/tech2/rest/login

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
		
		model.addAttribute("user", this.userVm);
		ModelAndView mv = new ModelAndView("login/login");
		mv.addObject("user", this.userVm);
		return mv;
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") UserVM userVm, Model model) {
		
		try {
			
			// Skapar ett userVM-objekt med de användarnamnet + lösenordet som matades in i login.jsp, lösenordet hashas
			UserVM loggedInUser = new UserVM(userVm.getUsername(), passwordHash(userVm.getPassword()));
			
			RestTemplate restTemplate = new RestTemplate();
			
/************************************************************************************************/
// Följde guiden: http://howtodoinjava.com/spring/spring-restful/spring-restful-client-resttemplate-example/
// kollade specifikt på följande exempel: "HTTP POST Method Example"
// Jag testade att ändra Fernandos metod (se längst ner i klassen).

// FEL: HTTP Status 500 - Request processing failed; nested exception is org.springframework.web.client.HttpServerErrorException: 500 Internal Server Error

/************************************************************************************************/
			
			// URI = http://localhost:8080/tech2/rest/login
			UserVM result = restTemplate.postForObject( URI, loggedInUser, UserVM.class);
			
			System.out.println("Address: "+result.getAddress()+" mail: "+result.getEmail());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: "+e.getStackTrace());
		}
		return "redirect:/";
	}
	
	
	private String passwordHash(String pwd) throws NoSuchAlgorithmException{
		
		MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
		msgDigest.update(pwd.getBytes());
		byte[] digest = msgDigest.digest();
		return digest.toString();
	}
	
	
	/* FERNANDOS METOD FRÅN KLASSEN loginRestController.java, SOM JAG ÄNDRADE ENLIGT GUIDEN (Ej pushad).
	 * 
	@RequestMapping(value="/login",  method= RequestMethod.POST)
	public ResponseEntity<UserVM> login(@RequestBody UserVM user)
	{
		System.out.println("START");
		User u = UserDAO.confirmUser(user.getUsername(),user.getPassword());
		System.out.println(u.getUserName()+" END");
		UserVM userVM = null;
		if(u != null)
		{
			userVM = new UserVM(u.getUserName(),u.getFullName(),u.getEmail(),
				u.getPhoneNumber(),u.getAddress(),u.getPrivilege());
		}
		return new ResponseEntity<>(userVM,HttpStatus.CREATED);
	}
	 * */

}
