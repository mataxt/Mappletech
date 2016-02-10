package testdao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Event;


/**
 * The Class EventDAO.
 */
public class EventDAO {

	/**
	 * Adds the Event.
	 *
	 * @param Event
	 *            model.Event
	 * @return true, if successful
	 */
	public static Integer addEvent(Event event) {
		Integer id = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(event);
			em.getTransaction().commit();
			id = event.getEventID();
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
		return id;
	}

	public static List<Event> getAllEvents() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		List<Event> events = new ArrayList<Event>();
		try {
			em.getTransaction().begin();
			events = em.createQuery("from Event", Event.class).getResultList();
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

		return events;
	}

	/**
	 * Gets the Event info.
	 *
	 * @param Eventname
	 *            of the Event
	 * @return model.Event
	 */
	public static Event fetchEvent(Integer eventId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		Event event = null;
		try {
			event = em.find(Event.class, eventId);
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
		return event;
	}

	/**
	 * Change Event.
	 *
	 * @param Event
	 *            to be changed
	 * @param value
	 *            Username of user or description string
	 * @param operation
	 *            {"title", "description", "date", "creator"}
	 * @return true, if successful
	 */
	public static boolean changeEvent(Event event) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Event e = em.find(Event.class, event.getEventID());
			if (e != null) {
				e.setDescription(event.getDescription());
				e.setDate(event.getDate());
				e.setTitle(event.getTitle());
				em.merge(e);
				success = true;
			}
			em.getTransaction().commit();
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
	 * Removes the Event.
	 *
	 * @param model
	 *            .Event
	 * @return true, if successful
	 */
	public static boolean removeEvent(Event event) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Event e = em.find(Event.class, event.getEventID());
			if (e != null) {
				em.remove(e);
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