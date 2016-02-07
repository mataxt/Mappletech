package tech2;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import testdao.GroupDAO;
import testdao.UserDAO;
import model.Group;
import model.User;
import junit.framework.TestCase;

public class GroupTests extends TestCase {

	User user, user2, user3, host;
	ArrayList<User> members = new ArrayList<User>();
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

		members.add(user);
		members.add(user2);
		members.add(user3);

		group = new Group();
		group.setGroupName("groupName");
		group.setHost(host);
		group.setUsers(members);
		group.setDescription("description");

	}
	
	public void tearDown(){
		UserDAO.removeUser(host);
		UserDAO.removeUser(user);
		UserDAO.removeUser(user2);
		UserDAO.removeUser(user3);
	}

	public void testConnect() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Failed at testConnect");
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
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

	public void testFetch() throws Exception {

		if (GroupDAO.addGroup(group)) {
			try {
				Group tmp = GroupDAO.fetchGroup(group.getGroupName());
				assertTrue(tmp != null);
				assertEquals(group.getGroupName(), tmp.getGroupName());
				assertEquals(group.getDescription(), tmp.getDescription());
				assertEquals(group.getHost(), tmp.getHost());
				assertEquals(group.getUsers(), tmp.getUsers());
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

	// public void testFetchUser() {
	// user.setUsername("testFetchUser");
	// if (UserDAO.addUser(user)) {
	// try {
	// User tmpUser = new User();
	// tmpUser = UserDAO.fetchUser(user.getUsername());
	// assertTrue(tmpUser != null);
	//
	// assertEquals(user.getUsername(), tmpUser.getUsername());
	// assertEquals(user.getAddress(), tmpUser.getAddress());
	// assertEquals(user.getPassword(), tmpUser.getPassword());
	//
	// System.out.println("Success");
	// } catch (AssertionError e) {
	// System.out.println("Failed at testFetchUser");
	// throw e;
	// } finally {
	// UserDAO.removeUser(user);
	// }
	// }
	// }
	//
	// public void testGetAllUsers() {
	// user.setUsername("testFetchUser");
	// if (UserDAO.addUser(user)) {
	// try {
	// assertTrue(UserDAO.fetchUser(user.getUsername()) != null);
	// System.out.println("Success");
	// } catch (AssertionError e) {
	// System.out.println("Failed at testFetchUser");
	// throw e;
	// } finally {
	// UserDAO.removeUser(user);
	// }
	// }
	// }
	//
	// public void testChangeUser() {
	// user.setUsername("testChangeUser");
	// String password = "123";
	// if (UserDAO.addUser(user)) {
	// try {
	// User tmpUser = new User();
	//
	// assertTrue(UserDAO.changeUser(user, password, "password"));
	// tmpUser = UserDAO.fetchUser(user.getUsername());
	// assertTrue(tmpUser != null);
	// assertEquals(password, tmpUser.getPassword());
	// assertEquals(user.getUsername(), tmpUser.getUsername());
	// assertEquals(user.getAddress(), tmpUser.getAddress());
	//
	// System.out.println("Success");
	// } catch (AssertionError e) {
	// System.out.println("Failed at testChangeUser");
	// throw e;
	// } finally {
	// UserDAO.removeUser(user);
	// }
	// }
	// }

}
