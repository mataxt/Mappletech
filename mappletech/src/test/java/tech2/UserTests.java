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

	public void testAddRemove() {
		try {
			user.setUsername("testAddRemove");
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
		String password ="123";
		if (UserDAO.addUser(user)) {
			try {
				User tmpUser=new User();
				
				assertTrue(UserDAO.changeUser(user, password,"password"));
				tmpUser = UserDAO.fetchUser(user.getUsername());
				assertTrue(tmpUser != null);
				assertEquals(password, tmpUser.getPassword());
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
				UserDAO.removeUser(user);
			}
		}
	}

}
