package restController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.UserDAO;
import model.User;
import vm.UserVM;

@RestController
public class AdminRestController {

	@RequestMapping(value="/administrator/anvandare")
	public boolean addUsers(@RequestBody(required=true) UserVM user)
	{
		System.out.println("Password: \n"+user.getPassword()+"\n");
		User u = UserDAO.fetchUser(user.getUsername());
		
		if(u == null) {
			User newUser = new User();
			
			newUser.setUsername(user.getUsername());
			newUser.setFullName(user.getFullName());
			newUser.setPassword(user.getPassword());
			newUser.setPrivilege(user.getPrivilege());
			
			UserDAO.addUser(newUser);
			
			return false;
		}
		return true;
	}
	
}
