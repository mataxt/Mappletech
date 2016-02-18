package restController;

import java.util.ArrayList;
import java.util.List;

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
	
	@RequestMapping(value="/administrator/getAllUsers")
	public List<UserVM> getAllUsers()
	{
		List<User> list = UserDAO.getAllUsers();
		List<UserVM> vmList = new ArrayList<>();
		for(User u:list){
			vmList.add(new UserVM(u.getUsername(),"****",u.getFullName(), u.getEmail(), u.getPhoneNumber(),u.getMobileNumber(),u.getAddress(),u.getPrivilege()));
		}
		
		return vmList;
	}
	
	
	@RequestMapping(value="/administrator/removeUser")
	public Boolean removeUser(@RequestBody(required=true) UserVM userVm)
	{
		User u = new User();
		u.setUsername(userVm.getUsername());
		return UserDAO.removeUser(u);
	}
	
	
}
