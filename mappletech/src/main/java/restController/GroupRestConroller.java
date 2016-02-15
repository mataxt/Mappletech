package restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.GroupDAO;
import model.Group;
import model.User;
import dao.UserDAO;
import vm.GroupVM;

@RestController
public class GroupRestConroller {

	@RequestMapping(value = "/group/add")
	public Boolean createReservation(@RequestBody(required = true) GroupVM groupVM) {
		Group group = new Group();
		group.setGroupName(groupVM.getGroupName());
		group.setHost(UserDAO.fetchUser(groupVM.getHost()));
		if (groupVM.getDescription().length() > 0) {
			group.setDescription(groupVM.getDescription());
		}
//		List<User> a = new ArrayList<User>();
//		a.add(UserDAO.fetchUser(groupVM.getHost()));
//		group.setUsers(a);

		return GroupDAO.addGroup(group);
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
			groupVMList.add(new GroupVM(group.getGroupName(), group.getDescription(), group.getHost().getUsername()));
		}
		return groupVMList;
	}

	@RequestMapping(value = "/group/getAll/{user}")
	public List<GroupVM> getUserReservations(@PathVariable("user") String username) {
		List<Group> groupList = GroupDAO.getAllGroups(username);
		List<GroupVM> groupVMList = new ArrayList<GroupVM>();
		for (Group group : groupList) {
			groupVMList.add( new GroupVM(group.getGroupName(), group.getDescription(), group.getHost().getUsername()));
		}
		return groupVMList;
	}

}
