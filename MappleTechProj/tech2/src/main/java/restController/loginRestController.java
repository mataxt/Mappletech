package restController;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dao.UserDAO;
import model.User;
import vm.UserVM;

@RestController
public class loginRestController{

	@RequestMapping(value = "/hello", method= RequestMethod.GET,produces = "application/json")
	public String helloWorld()
	{
		System.out.println("Hello Rest World");
		return "Hello World!";
	}
	
	@RequestMapping(value="/login")
	public UserVM login(@RequestBody(required=true) UserVM user)
	{
		System.out.println(user.getUsername());
		User u = UserDAO.confirmUser(user.getUsername(),user.getPassword());
		UserVM userVM = null;
		if(u != null)
		{
			userVM = new UserVM(u.getUserName(),u.getFullName(),u.getEmail(),
				u.getPhoneNumber(),u.getAddress(),u.getPrivilege());
		}
		return userVM;
	}	
	
}
