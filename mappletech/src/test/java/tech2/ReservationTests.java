package tech2;

import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
















import testdao.FacilityDAO;
import testdao.ReservationDAO;
import testdao.UserDAO;
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
		
		UserDAO.addUser(user); 
		FacilityDAO.addFacility(fac);
		
		reservation = new Reservation();
		
		reservation.setHost(user);
		reservation.setFacility(fac);
		
		reservation.setTimeTo(Date.valueOf("2015-02-01"));
		reservation.setTimeFrom(Date.valueOf("2014-02-01"));
	}
	
	protected void tearDown() {
		UserDAO.removeUser(user);
		FacilityDAO.removeFacility(fac);
		
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

	public void testAddDelete() throws Exception {

		try {
			System.out.println("\n------Running Add_Delete Registration----\n");
			assertTrue(ReservationDAO.addReservation(reservation));
			assertTrue(ReservationDAO.removeReservation(reservation));
			System.out.println("Success");
		} catch (AssertionError e) {
			System.out.println("Failed at testAddDelete");
			throw e;
		}
	}
		

	public void testfetchReservation() throws Exception {
			if (ReservationDAO.addReservation(reservation)) {
				try {
					Reservation tmpRes = new Reservation();
					tmpRes = ReservationDAO.fetchReservation(reservation
							.getReservationId());
		
					assertTrue(tmpRes != null);
					assertEquals(reservation.getReservationId(),
							tmpRes.getReservationId());
					assertEquals(reservation.getHost().getUsername(), tmpRes.getHost().getUsername());
					assertEquals(reservation.getFacility().getFacilityId(), tmpRes.getFacility().getFacilityId());
					assertEquals(reservation.getTimeFrom(), tmpRes.getTimeFrom());
					assertEquals(reservation.getTimeTo(), tmpRes.getTimeTo());
		
					System.out.println("Success");
				} catch (AssertionError e) {
					System.out.println("Failed at testFetchReservation");
					throw e;
				} finally {
	                ReservationDAO.removeReservation(reservation);
	            }
			} else {
	            throw new Exception("Failed to fetch reservation in Reservation fetch test");
	        }
	}
		
	public void testChangeReservation() throws Exception {		
		
			Date TimeTo = Date.valueOf("2015-02-05");
			if (ReservationDAO.addReservation(reservation)) {
				try {
					reservation.setTimeTo(TimeTo);
					Reservation tmpRes = new Reservation();
					assertTrue(ReservationDAO.changeReservation(reservation));
					tmpRes = ReservationDAO.fetchReservation(reservation
							.getReservationId());
					
					assertTrue(tmpRes != null);
					assertEquals(TimeTo, tmpRes.getTimeTo());
					
					assertEquals(reservation.getReservationId(),
							tmpRes.getReservationId());
					
					assertEquals(reservation.getHost().getUsername(), tmpRes.getHost().getUsername());
					assertEquals(reservation.getFacility().getFacilityId(), tmpRes.getFacility().getFacilityId());
					assertEquals(reservation.getTimeFrom(), tmpRes.getTimeFrom());

					System.out.println("Success");
				} catch (AssertionError e) {
					System.out.println("Failed at testChangeReservation");
					throw e;
				} finally {
	                ReservationDAO.removeReservation(reservation);
				}
			} else {
	            throw new Exception("Failed to change reservation in Reservation change test");
	        }
		}
	
	public void testGetAllREservations() throws Exception {
		
			if (ReservationDAO.addReservation(reservation)) {
				try {
					// if null no reservations found! impossible considering we just
					// added one
					assertTrue(ReservationDAO.getAllReservations() != null);
					System.out.println("Success");
				} catch (AssertionError e) {
					System.out.println("Failed at testGetAllReservations");
					throw e;
				} finally {
	                ReservationDAO.removeReservation(reservation);
				}
			}
			 else {
	            throw new Exception("Failed to get all reservation in Reservation GetAll test");
	        }
		}
	}
