package testdao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Facility;
import model.Reservation;
import model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class ReservationDAO.
 */
public class ReservationDAO {

	/**
	 * Adds the Reservation.
	 *
	 * @param reservation
	 *            model.Reservation
	 * @return true, if successful
	 */
	public static boolean addReservation(Reservation reservation) {
		boolean registered = false;
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(reservation);
			em.getTransaction().commit();
			registered = true;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			registered = false;
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		return registered;
	}

	/**
	 * Retrieves all the Reservations.
	 *
	 * @param
	 * @return List<Reservation>
	 */
	public static List<Reservation> getAllReservations() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			em.getTransaction().begin();
			reservations = em
					.createQuery("from Reservation", Reservation.class)
					.getResultList();
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}

		return reservations;
	}

	/**
	 * Gets the Reservation info.
	 *
	 * @param reservationID
	 *            of the Reservation
	 * @return model.Reservation
	 */
	public static Reservation fetchReservation(Integer reservationID) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		Reservation reservation = null;
		try {
			reservation = em.find(Reservation.class, reservationID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		return reservation;
	}

	/**
	 * Change reservation.
	 *
	 * @param reservation
	 *            to be changed
	 * @param value
	 *            to be changed into OBS: for "facilityid" operation use integers
	 *            as String e.g. "1" or "3"
	 * @param operation
	 *            {"title", "host", "facilityid", "timefrom","timeto",
	 *            "privilege"}
	 * @return true, if successful
	 */
	public static boolean changeReservation(Reservation reservation,
			String value, String operation) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Reservation r = em.find(Reservation.class,
					reservation.getReservationId());
			if (r != null) {
				switch (operation) {
				case "title":
					r.setTitle(value);
					break;
				case "host":
					r.setHost(new User(value));
					break;
				case "facilityid":
					Facility fac = new Facility();
					fac.setFacilityId(Integer.parseInt(value));
					r.setFacilityID(fac);
					break;
				case "timefrom":
					r.setTimeFrom(Date.valueOf(value));
					break;
				case "timeto":
					r.setTimeTo(Date.valueOf(value));
					break;
				default:
					break;
				}
				em.merge(r);
				em.getTransaction().commit();
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		return success;
	}

	/**
	 * Removes the Reservation.
	 *
	 * @param model
	 *            .Reservation
	 * @return true, if successful
	 */
	public static boolean removeReservation(Reservation reservation) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Reservation r = em.find(Reservation.class,
					reservation.getReservationId());
			if (r != null) {
				em.remove(r);
				success = true;
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		return success;
	}

}