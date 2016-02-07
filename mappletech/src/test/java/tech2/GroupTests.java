package tech2;

import testdao.GroupDAO;
import testdao.UserDAO;
import model.Group;
import model.User;
import junit.framework.TestCase;

public class GroupTests extends TestCase {

	User user, user2, user3, host;
	Group group;

	// assigning the values
	protected void setUp() {
		host = new User();
		host.setUsername("grp_host");
		host.setPassword("password");
		host.setFullName("fullname");
		host.setEmail("email");
		host.setAddress("address");
		host.setPrivilege(0);
		host.setPhoneNumber("phonenumber");
		user = new User();
		user.setUsername("grp_username");
		user.setPassword("password");
		user.setFullName("fullname");
		user.setEmail("email");
		user.setAddress("address");
		user.setPrivilege(0);
		user.setPhoneNumber("phonenumber");
		user2 = new User();
		user2.setUsername("grp_username2");
		user2.setPassword("password");
		user2.setFullName("fullname");
		user2.setEmail("email");
		user2.setAddress("address");
		user2.setPrivilege(0);
		user2.setPhoneNumber("phonenumber");
		user3 = new User();
		user3.setUsername("grp_username3");
		user3.setPassword("password");
		user3.setFullName("fullname");
		user3.setEmail("email");
		user3.setAddress("address");
		user3.setPrivilege(0);
		user3.setPhoneNumber("phonenumber");

		UserDAO.addUser(host);
		UserDAO.addUser(user);
		UserDAO.addUser(user2);
		UserDAO.addUser(user3);

		group = new Group();
		group.setGroupName("groupName");
		group.setHost(host);
		group.setDescription("description");

	}

	public void tearDown() {
		UserDAO.removeUser(host);
		UserDAO.removeUser(user);
		UserDAO.removeUser(user2);
		UserDAO.removeUser(user3);
	}

	public void testAddRemove() throws Exception {
		try {
			assertTrue(GroupDAO.addGroup(group));
			assertTrue(GroupDAO.removeGroup(group));
			System.out.println("Success");
		} catch (AssertionError e) {
			System.out.println("Failed at testAddRemove");
			throw e;
		}
	}

	public void testAddRemoveMembers() throws Exception {
		group.setGroupName("groupName2");
		if (GroupDAO.addGroup(group))
			try {
				assertTrue(GroupDAO.changeGroup(group, user.getUsername(), "add"));
				assertTrue(GroupDAO.changeGroup(group, user2.getUsername(), "add"));
				assertTrue(GroupDAO.changeGroup(group, user3.getUsername(), "add"));
				Group tmp = GroupDAO.fetchGroup(group.getGroupName());
				assertEquals(3, tmp.getUsers().size());
				assertTrue(GroupDAO.changeGroup(group, user.getUsername(), "remove"));
				assertTrue(GroupDAO.changeGroup(group, user2.getUsername(), "remove"));
				assertTrue(GroupDAO.changeGroup(group, user3.getUsername(), "remove"));
				tmp = GroupDAO.fetchGroup(group.getGroupName());
				assertEquals(0, tmp.getUsers().size());
				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at testAddRemoveMembers");
				throw e;
			} finally {
				GroupDAO.removeGroup(group);
			}
	}

	public void testFetch() throws Exception {
		group.setGroupName("groupName3");
		if (GroupDAO.addGroup(group)) {
			try {
				Group tmp = GroupDAO.fetchGroup(group.getGroupName());
				assertTrue(tmp != null);
				assertEquals(group.getGroupName(), tmp.getGroupName());
				assertEquals(group.getDescription(), tmp.getDescription());
				assertEquals(group.getHost().getUsername(), tmp.getHost().getUsername());
				assertEquals(group.getUsers().size(), tmp.getUsers().size());
				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at group testFetch");
				throw e;
			} finally {
				GroupDAO.removeGroup(group);
			}
		} else {
			throw new Exception("Failed to add Group in group fetch test");
		}
	}

	public void testGetAllGroups() {
		group.setGroupName("testGetAllGroups");
		Group group2 = new Group();
		group2.setGroupName("Group2Test");
		group2.setDescription("desc2");
		group2.setHost(user);
		if (GroupDAO.addGroup(group) && GroupDAO.addGroup(group2)) {
			try {
				assertEquals(2, GroupDAO.getAllGroups().size());
				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at testGettAllGroups");
				throw e;
			} finally {
				GroupDAO.removeGroup(group);
				GroupDAO.removeGroup(group2);
			}
		}
	}

	public void testChangeGroup() {
		group.setGroupName("groupName4");
		String newHost = user.getUsername();
		String newDesc = "newDesc";
		if (GroupDAO.addGroup(group)) {
			try {
				Group tmp;
				assertTrue(GroupDAO.changeGroup(group, newHost, "host"));
				tmp = GroupDAO.fetchGroup(group.getGroupName());
				assertTrue(tmp != null);
				assertEquals(newHost, tmp.getHost().getUsername());

				tmp = null;
				assertTrue(GroupDAO.changeGroup(group, newDesc, "description"));
				tmp = GroupDAO.fetchGroup(group.getGroupName());
				assertTrue(tmp != null);
				assertEquals(newDesc, tmp.getDescription());

				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at testChangeGroup");
				throw e;
			} finally {
				GroupDAO.removeGroup(group);
			}
		}
	}

}
