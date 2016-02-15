package restController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.GroupDAO;
import dao.UserDAO;
import model.Group;
import model.User;
import vm.GroupVM;
import vm.UserVM;

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
	
	@RequestMapping(value="/administrator/grupper")
	public boolean removeGroup(@RequestBody(required=true) GroupVM groupVm)
	{
		Group group = new Group();

		group.setDescription(groupVm.getDescription());
		group.setGroupName(groupVm.getGroupName());
		group.setHost(new User(groupVm.getHost()));
		group.setUsers(GroupDAO.fetchGroup(groupVm.getGroupName()).getUsers());
		
		
		return GroupDAO.removeGroup(group);
	}
	
	
}
