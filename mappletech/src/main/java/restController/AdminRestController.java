package restController;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.ReservationDAO;
import dao.UserDAO;
import model.User;
import vm.UserVM;
import vm.ReservationVM;

@RestController
public class AdminRestController {

	@RequestMapping(value="/administrator/anvandare/nyanvandare")
	public boolean addUsers(@RequestBody(required=true) UserVM user)
	{
		User newUser = new User();

		System.out.println("-------------------" + user.getUsername() +  "--------------------");
		
		newUser.setUsername(user.getUsername());
		newUser.setFullName(user.getFullName());
		newUser.setPassword(user.getPassword());
		newUser.setPrivilege(user.getPrivilege());
			
		return UserDAO.addUser(newUser);
	}
	
	
}
