package tech2;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dao.UserDAO;
import junit.framework.TestCase;
import model.User;

public class UserTests extends TestCase {

	User user;
	User user2;


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
		
		user2 = new User();
		user2.setUsername("username2");
		user2.setPassword("password");
		user2.setFullName("fullname");
		user2.setEmail("email");
		user2.setAddress("address");
		user2.setPrivilege(0);
		user2.setPhoneNumber("phonenumber");
	}

	public void testConnect() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
//			em.getTransaction().begin();
//			em.persist(user);
//			em.persist(user2);
//			em.getTransaction().commit();
//			
			em.getTransaction().begin();
			List<User> u = new ArrayList<User>();
			u = UserDAO.getAllUsers();
			System.out.println(u.get(0).getUsername());
			System.out.println(u.get(1).getUsername());
			
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Failed");
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}

	// public void connect() {
	// System.out.print("Running Add user test...");
	// EntityManagerFactory emf =
	// Persistence.createEntityManagerFactory("UserPU");
	// EntityManager em = emf.createEntityManager();
	// try {
	// assertTrue(emf.isOpen());
	// System.out.println("Success");
	// } catch (AssertionError e) {
	// System.out.println("Failed");
	// throw e;
	// } finally {
	// if (em != null) {
	// em.close();
	// }
	// if (emf != null) {
	// emf.close();
	// }
	// }
	// }

	// @DependsOn("#testConnect")
	// public void add() {
	// System.out.print("Running Add user test...");
	// try {
	// assertTrue(UserDAO.addUser(user));
	// System.out.println("Success");
	// } catch (AssertionError e) {
	// System.out.println("Failed");
	// throw e;
	// }
	// }
	//
	//
	//
	//
	// @DependsOn("#testAdd")
	// public void remove() {
	// System.out.print("Running Remove user test...");
	// try {
	// assertTrue(UserDAO.removeUser(user));
	// System.out.println("Success");
	// } catch (AssertionError e) {
	// System.out.println("Failed");
	// throw e;
	// }
	// }
}
