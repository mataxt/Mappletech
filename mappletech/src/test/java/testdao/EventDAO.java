package testdao;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Event;
import model.User;

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
	public static boolean addEvent(Event event) {
		boolean registered = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(event);
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
	public static boolean changeEvent(Event Event, String value, String operation) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Event e = em.find(Event.class, Event.getEventID());
			if (e != null) {
				switch (operation) {
				case "creator":
					e.setCreator(new User(value));
					success = true;
					break;
				case "description":
					e.setDescription(value);
					success = true;
					break;
				case "date":
					e.setDate(Date.valueOf(value));
					success = true;
					break;
				case "title":
					e.setTitle(value);
				default:
					break;
				}
				em.merge(e);
				em.getTransaction().commit();

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