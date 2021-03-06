package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Query;

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
	public static boolean addEvent(Event event) {
		boolean registered = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
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
	
	public static List<Event> getAllEventsFromTodayDate(String userName) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<Event> events = new ArrayList<Event>();
		try {
			em.getTransaction().begin();
			TypedQuery<Event> query = em.createQuery("from Event e where e.date >=?1 and e.creator = ?2", Event.class);
			query.setParameter(1, new Date(System.currentTimeMillis()));
			query.setParameter(2, UserDAO.fetchUser(userName));
			events = query.getResultList();
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
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
	
	public static List<Event> getLatestEvents() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<Event> events = new ArrayList<Event>();
		try {
			em.getTransaction().begin();
			TypedQuery<Event> q = em.createQuery("from Event order by Date desc", Event.class);
			q.setFirstResult(0);
			q.setMaxResults(5);
			events = q.getResultList();
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
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
	
	/**
	 * Removes the Event.
	 *
	 * @param int
	 *            eventId
	 * @return true, if successful
	 */
	public static boolean removeEvent(int eventId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Event e = em.find(Event.class, eventId);
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