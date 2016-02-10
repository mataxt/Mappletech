package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Facility;

/**
 * The Class FacilityDAO.
 */
public class FacilityDAO {

	/**
	 * Adds the Facility.
	 *
	 * @param Facility
	 *            model.Facility
	 * @return true, if successful
	 */
	public static boolean addFacility(Facility facility) {
		boolean registered = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(facility);
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
	 * @param model.Facility
	 * @return List<Facility>
	 * 
	 */
	public static List<Facility> getAllFacilities() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<Facility> facilities = new ArrayList<Facility>();
		try {
			em.getTransaction().begin();
			facilities = em.createQuery("from Facility", Facility.class).getResultList();
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

		return facilities;
	}

	/**
	 * Gets the Facility info.
	 *
	 * @param String
	 *            facilityName of the Facility
	 * @return model.Facility
	 */
	public static Facility fetchFacility(Integer facilityId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		Facility facility = null;
		try {
			facility = em.find(Facility.class, facilityId);
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
		return facility;
	}

	/**
	 * Change Facility.
	 *
	 * @param Facility
	 *            to be changed
	 * @return true, if successful
	 */
	public static boolean changeEvent(Facility facility) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Facility f = em.find(Facility.class, facility.getFacilityId());
			if (f != null) {
				f.setFacilityName(facility.getFacilityName());
				f.setDescription(facility.getDescription());
				f.setLocation(facility.getDescription());
				f.setAvailable(facility.getAvailable());
				f.setReservations(facility.getReservations());
				em.merge(f);
				success = true;
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	 * Removes the Facility.
	 *
	 * @param model
	 *            .Facility
	 * @return true, if successful
	 */
	public static boolean removeFacility(Facility facility) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Facility f = em.find(Facility.class, facility.getFacilityId());
			if (f != null) {
				em.remove(f);
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