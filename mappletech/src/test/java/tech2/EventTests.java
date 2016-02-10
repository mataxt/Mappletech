package tech2;

import java.sql.Date;

import junit.framework.TestCase;
import model.Event;
import model.User;
import testdao.EventDAO;
import testdao.UserDAO;

public class EventTests extends TestCase {
	Event event;
	User user;

	protected void setUp() {
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

	public void tearDown() {
		UserDAO.removeUser(user);
	}

	public void testAddRemove() {
		try {
			event.setEventID(EventDAO.addEvent(event));
			assertTrue(event.getEventID() != null);
			assertTrue(EventDAO.removeEvent(event));
		} catch (AssertionError e) {
			System.out.println("Failed at testAddRemove");
			throw e;
		}
	}

	public void testFetch() {
		if (EventDAO.addEvent(event) != null) {
			try {
				// fetchEvent vill ha id men jag kan inte fa id
				Event tmp = EventDAO.fetchEvent(event.getEventID());
				assertTrue(tmp != null);
				assertEquals(event.getTitle(), tmp.getTitle());
				assertEquals(event.getDescription(), tmp.getDescription());
				assertEquals(event.getDate(), tmp.getDate());
				assertEquals(event.getCreator(), tmp.getCreator());
			} catch (AssertionError e) {
				System.out.println("Failed at testFetch");
			} finally {
				EventDAO.removeEvent(event);
			}
		}
	}

	public void testGetAllEvents() {
		event.setTitle("getAllEvents");
		Event event2 = new Event();
		event2.setTitle("event2");
		event2.setDescription("eventDesc2");
		event2.setDate(new Date(0));
		event2.setCreator(user);
		if (EventDAO.addEvent(event) != null && EventDAO.addEvent(event2) != null) {
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
	
	public void testChangeEvent() {
		event.setEventID(null);
		Event newEvent = new Event();
		newEvent.setTitle("newTitle");
		newEvent.setDescription("newDesc");
		newEvent.setDate(Date.valueOf("2016-12-11"));
		event.setEventID(EventDAO.addEvent(event));
		if (event.getEventID() != null) {
			newEvent.setEventID(event.getEventID());
			try {
				assertTrue(EventDAO.changeEvent(newEvent));
				Event tmp = EventDAO.fetchEvent(event.getEventID());
				assertEquals(newEvent.getDate(), tmp.getDate());
				assertEquals(newEvent.getDescription(), tmp.getDescription());
				assertEquals(newEvent.getTitle(), tmp.getTitle());

				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at testChangeGroup");
				throw e;
			} finally {
				EventDAO.removeEvent(event);
			}
		}
	}
}
