package restController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.UserDAO;
import model.User;
import vm.UserVM;

@RestController
@RequestMapping("/profil")
public class ProfileRestController {
	
	@RequestMapping(value="/getUser", method = RequestMethod.POST)
	public UserVM getUser(@RequestBody(required=true) String userName)
	{
		User u = UserDAO.fetchUser(userName);
		UserVM userVM = null;
		if(u != null)
		{
			userVM = new UserVM(u.getUsername(),u.getPassword(),u.getFullName(),u.getEmail(),
				u.getPhoneNumber(),u.getMobileNumber(),u.getAddress(),u.getPrivilege());
		}
		return userVM;
	}
	
	@RequestMapping(value="/updateProfil", method = RequestMethod.POST)
	public Boolean updateUser(@RequestBody(required=true) UserVM user)
	{
		System.out.println(user.getEmail());
		User u = UserDAO.fetchUser(user.getUsername());
		if(u != null)
		{
			u.setFullName(user.getFullName());
			u.setEmail(user.getEmail());
			u.setPhoneNumber(user.getPhoneNumber());
			u.setMobileNumber(user.getMobileNumber());
			u.setPassword(user.getPassword());
		}
		return UserDAO.changeUser(u);
	}

}
