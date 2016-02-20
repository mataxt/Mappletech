package restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.UserDAO;
import model.Event;
import dao.EventDAO;
import vm.EventVM;

@RestController
public class EventRestController {
	
	@RequestMapping(value = "/event/add", method = RequestMethod.POST)
	public Boolean createEvent(@RequestBody(required=true) EventVM eventVM)
	{
		
		Event event = new Event(eventVM.getTitle(),eventVM.getDescription(),
				UserDAO.fetchUser(eventVM.getCreator()),eventVM.getDate());
		return EventDAO.addEvent(event);		
	}
	
	@RequestMapping(value="/event/getAll",method = RequestMethod.GET)
	public List<EventVM> getAllEvents()
	{
		List<Event> eventList = EventDAO.getAllEvents();
		List<EventVM> eventVMList = new ArrayList<>();
		for(Event event:eventList)
		{
			eventVMList.add(new EventVM(event.getEventID(),event.getTitle(),
					event.getDescription(),event.getCreator().getUsername(),
					event.getDate()));
		}
		return eventVMList;
	}
	
	@RequestMapping(value="/event/getAllFromToday",method = RequestMethod.GET)
	public List<EventVM> getAllEventsFromToday()
	{
		List<Event> eventList = EventDAO.getAllEventsFromTodayDate();
		List<EventVM> eventVMList = new ArrayList<>();
		for(Event event:eventList)
		{
			eventVMList.add(new EventVM(event.getEventID(),event.getTitle(),
					event.getDescription(),event.getCreator().getUsername(),
					event.getDate()));
		}
		return eventVMList;
	}
	
	
	@RequestMapping(value="/event/getLatest",method = RequestMethod.GET)
	public List<EventVM> getLatestEvents()
	{
		List<Event> eventList = EventDAO.getLatestEvents();
		List<EventVM> eventVMList = new ArrayList<>();
		for(Event event:eventList)
		{
			eventVMList.add(new EventVM(event.getEventID(),event.getTitle(),
					event.getDescription(),event.getCreator().getUsername(),
					event.getDate()));
		}
		return eventVMList;
	}
	
	@RequestMapping(value="/event/get",method = RequestMethod.POST)
	public Event getEvent(@RequestBody(required=true)int eventId)
	{
		return EventDAO.fetchEvent(eventId);
	}
	
	@RequestMapping(value="/event/delete",method = RequestMethod.POST)
	public Boolean deleteEvent(@RequestBody(required=true)EventVM eventVm)
	{
		
		return EventDAO.removeEvent(eventVm.getEventID());
	}

	@RequestMapping(value="/event/update",method = RequestMethod.POST)
	public Boolean updateEvent(@RequestBody(required=true)EventVM eventVM)
	{
		Event event = EventDAO.fetchEvent(eventVM.getEventID());
		if(event != null)
		{
			event.setCreator(UserDAO.fetchUser(eventVM.getCreator()));
			event.setDate(eventVM.getDate());
			event.setDescription(eventVM.getDescription());
			event.setEventID(eventVM.getEventID());
			event.setTitle(eventVM.getTitle());
			return EventDAO.changeEvent(event);
		}
		return false;
	}
}
