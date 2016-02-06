package tech2;

import java.sql.Date;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import junit.framework.TestCase;
import model.Facility;
import model.Reservation;
import model.User;

public class ReservationTests extends TestCase {

	User user;
	Facility fac;
	Reservation reservation;
	int id;

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
		
		fac = new Facility();
		fac.setFacilityName("tvatt");
		reservation = new Reservation();
		reservation.setTitle("party");
		
		reservation.setHost(user);
		reservation.setFacilityID(fac);
		
		reservation.setTimeTo(Date.valueOf("2015-02-01"));
		reservation.setTimeFrom(Date.valueOf("2014-02-01"));
	}

	public void testConnect() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			//em.persist(user);
			//em.persist(fac);
		//	em.persist(reservation);
			//em.flush();
			id = reservation.getReservationId();
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

//	public void testDelete() {
//		
//		System.out.println("\n------Running delete Registration----\n");
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
//		EntityManager em = emf.createEntityManager();
//		try {
//			
//			em.getTransaction().begin();
//			reservation.setReservationId(15);
//			if(!ReservationDAO.removeReservation(reservation)) {
//				System.out.println("\nFailed to remove");
//			}
//			em.getTransaction().commit();
//			
//		}catch (Exception e) {
//			if (em.getTransaction().isActive()) {
//				em.getTransaction().rollback();
//			}
//			System.out.println("Failed");
//		} finally {
//			if (em != null) {
//				em.close();
//			}
//			if (emf != null) {
//				emf.close();
//			}
//		
//		}
//	}
	
	
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
