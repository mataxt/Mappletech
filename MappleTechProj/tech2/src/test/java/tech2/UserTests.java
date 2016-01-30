package tech2;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.DependsOn;

import dao.UserDAO;
import junit.framework.TestCase;
import model.User;

public class UserTests extends TestCase {

	User user;


	// assigning the values
	protected void setUp() {
		user = new User();
		user.setUserName("username");
		user.setPassWord("password");
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
