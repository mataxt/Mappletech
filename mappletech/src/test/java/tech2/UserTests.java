package tech2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;
import model.User;
import testdao.UserDAO;

public class UserTests extends TestCase {

	User user;

	// assigning the values
	protected void setUp() {
		user = new User();
		user.setUsername("username");
		user.setPassword("password");
		user.setFullName("fullname");
		user.setEmail("email");
		user.setAddress("address");
		user.setPrivilege(0);
		user.setPhoneNumber("phonenumber");

	}

	public void testAddRemove() {
		user.setUsername("testAddRemove");
		try {
			assertTrue(UserDAO.addUser(user));
			assertTrue(UserDAO.removeUser(user));
			System.out.println("Success");
		} catch (AssertionError e) {
			System.out.println("Failed at testAddRemove");
			throw e;
		}
	}

	public void testConfirmUser() {
		user.setUsername("testConfirmUser");
		if (UserDAO.addUser(user)) {
			try {
				assertTrue(UserDAO.confirmUser(user.getUsername(), user.getPassword()) != null);
				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at testConfirmUser");
				throw e;
			} finally {
				UserDAO.removeUser(user);
			}
		}
	}

	public void testFetchUser() {
		user.setUsername("testFetchUser");
		if (UserDAO.addUser(user)) {
			try {
				User tmpUser=new User();
				tmpUser = UserDAO.fetchUser(user.getUsername());
				
				assertTrue(tmpUser != null);
				assertEquals(user.getUsername(), tmpUser.getUsername());
				assertEquals(user.getAddress(), tmpUser.getAddress());
				assertEquals(user.getPassword(), tmpUser.getPassword());
				assertEquals(user.getEmail(), tmpUser.getEmail());
				assertEquals(user.getFullName(), tmpUser.getFullName());
				assertEquals(user.getMobileNumber(), tmpUser.getMobileNumber());
				assertEquals(user.getPhoneNumber(), tmpUser.getPhoneNumber());
				assertEquals(user.getPrivilege(), tmpUser.getPrivilege());
				
				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at testFetchUser");
				throw e;
			} finally {
				UserDAO.removeUser(user);
			}
		}
	}
	
	public void testGetAllUsers() {
		user.setUsername("testFetchUser");
		if (UserDAO.addUser(user)) {
			try {
				assertTrue(UserDAO.fetchUser(user.getUsername()) != null);
				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at testFetchUser");
				throw e;
			} finally {
				UserDAO.removeUser(user);
			}
		}
	}
	
	public void testChangeUser() {
		user.setUsername("testChangeUser");
		user.setPassword("123");
		if (UserDAO.addUser(user)) {
			try {
				User tmpUser=new User();
				
				assertTrue(UserDAO.changeUser(user));
				tmpUser = UserDAO.fetchUser(user.getUsername());
				assertTrue(tmpUser != null);
				assertEquals("123", tmpUser.getPassword());
				assertEquals(user.getUsername(), tmpUser.getUsername());
				assertEquals(user.getAddress(), tmpUser.getAddress());
				assertEquals(user.getEmail(), tmpUser.getEmail());
				assertEquals(user.getFullName(), tmpUser.getFullName());
				assertEquals(user.getMobileNumber(), tmpUser.getMobileNumber());
				assertEquals(user.getPhoneNumber(), tmpUser.getPhoneNumber());
				assertEquals(user.getPrivilege(), tmpUser.getPrivilege());
				
				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at testChangeUser");
				throw e;
			} finally {
				System.out.println("finally testChangeUser");
				System.out.println(user.getUsername());
				UserDAO.removeUser(user);
			}
		}
	}

}
