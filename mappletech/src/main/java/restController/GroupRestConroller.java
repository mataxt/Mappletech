package restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.GroupDAO;
import model.Group;
import dao.UserDAO;
import vm.GroupVM;
import vm.UserVM;

@RestController
public class GroupRestConroller {

	@RequestMapping(value = "/group/add")
	public Boolean createReservation(@RequestBody(required = true) GroupVM groupVM) {
		Group group = new Group();
		group.setGroupName(groupVM.getGroupName());
		group.setHost(UserDAO.fetchUser(groupVM.getHost().getUsername()));
		if (groupVM.getDescription().length() > 0) {
			group.setDescription(groupVM.getDescription());
		}
		Boolean success = GroupDAO.addGroup(group);
		GroupDAO.changeGroup(group, groupVM.getHost().getUsername(), "add");
		

		return success;
	}

	@RequestMapping(value = "/group/remove")
	public Boolean deleteReservation(@RequestBody(required = true) GroupVM groupVM) {
		Group group = new Group();
		group.setGroupName(groupVM.getGroupName());
		return GroupDAO.removeGroup(group);
	}

	@RequestMapping(value = "/group/getAll")
	public List<GroupVM> getAllReservations() {
		List<Group> groupList = GroupDAO.getAllGroups();
		List<GroupVM> groupVMList = new ArrayList<GroupVM>();
		for (Group group : groupList) {
			UserVM u = new UserVM(group.getHost().getUsername(), group.getHost().getPassword(), group.getHost().getFullName(), group.getHost().getEmail(), group.getHost().getPhoneNumber(), group.getHost().getMobileNumber(), group.getHost().getAddress(), group.getHost().getPrivilege());
			groupVMList.add(new GroupVM(group.getGroupName(), group.getDescription(), u, new ArrayList<UserVM>()));
		}
		return groupVMList;
	}

	@RequestMapping(value = "/group/getAll/{user}")
	public List<GroupVM> getUserReservations(@PathVariable("user") String username) {
		List<Group> groupList = GroupDAO.getAllGroups(username);
		List<GroupVM> groupVMList = new ArrayList<GroupVM>();
		for (Group group : groupList) {
			UserVM u = new UserVM(group.getHost().getUsername(), group.getHost().getPassword(), group.getHost().getFullName(), group.getHost().getEmail(), group.getHost().getPhoneNumber(), group.getHost().getMobileNumber(), group.getHost().getAddress(), group.getHost().getPrivilege());
			groupVMList.add(new GroupVM(group.getGroupName(), group.getDescription(), u, new ArrayList<UserVM>()));
		}
		return groupVMList;
	}
	
	@RequestMapping(value = "/group/{groupName}")
	public GroupVM getGroup(@PathVariable("groupName") String groupName) {
		Group group = GroupDAO.fetchGroup(groupName);
		UserVM u = new UserVM(group.getHost().getUsername(), group.getHost().getPassword(), group.getHost().getFullName(), group.getHost().getEmail(), group.getHost().getPhoneNumber(), group.getHost().getMobileNumber(), group.getHost().getAddress(), group.getHost().getPrivilege());
		List<UserVM> list = new ArrayList<UserVM>();
		for(model.User user: group.getUsers()){
			UserVM uvm = new UserVM();
			uvm.setUsername(user.getUsername());
			uvm.setEmail(user.getEmail());
			list.add(uvm);
		}
		GroupVM groupVM = new GroupVM(group.getGroupName(), group.getDescription(), u,list);
		return groupVM;
	}

}
