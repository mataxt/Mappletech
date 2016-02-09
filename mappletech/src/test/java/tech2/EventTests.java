package tech2;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import model.Event;
import model.Group;
import model.User;
import testdao.EventDAO;
import testdao.GroupDAO;
import testdao.UserDAO;

public class EventTests extends TestCase{
	Event event;
	User user;
	protected void setUp()
	{
		user = new User();
		user.setUsername("username");
		user.setPassword("password");
		user.setFullName("fullname");
		user.setEmail("email");
		user.setAddress("address");
		user.setPrivilege(0);
		user.setPhoneNumber("phonenumber");
		UserDAO.addUser(user);
		event = new Event();
		event.setTitle("EventName");
		event.setDate(new Date(23131231));
		event.setDescription("Grillning");
		event.setCreator(user);
	}
	
	public void tearDown()
	{
		UserDAO.removeUser(user);
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
	
	public void testAddRemove(){
		try{
			assertTrue(EventDAO.addEvent(event));
			assertTrue(EventDAO.removeEvent(event));
		}catch (AssertionError e) {
			System.out.println("Failed at testAddRemove");
			throw e;
		}
	}
	
	/*public void testFetch(){
		if(EventDAO.addEvent(event))
		{
			try{
				//fetchEvent vill ha id men jag kan inte fa id
				Event tmp = EventDAO.fetchEvent(event.getTitle());
				assertTrue(tmp != null);
				assertEquals(event.getTitle(),tmp.getTitle());
				assertEquals(event.getDescription(),tmp.getDescription());
				assertEquals(event.getDate(),tmp.getDate());
				assertEquals(event.getCreator(),tmp.getCreator());
			}catch(AssertionError e)
			{
				System.out.println("Failed at testFetch");
			}finally{
				EventDAO.removeEvent(event);
			}
		}
	}*/
	
	public void testGetAllEvents()
	{
		event.setTitle("getAllEvents");
		Event event2 = new Event();
		event2.setTitle("event2");
		event2.setDescription("eventDesc2");
		event2.setDate(new Date(0));
		event2.setCreator(user);
		if (EventDAO.addEvent(event) && EventDAO.addEvent(event2)) {
			try {
				assertEquals(2, EventDAO.getAllEvents().size());
				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at testGettAllGroups");
				throw e;
			} finally {
				EventDAO.removeEvent(event);
				EventDAO.removeEvent(event2);
			}
		}
	}
}
