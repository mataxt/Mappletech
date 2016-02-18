package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import vm.EventVM;
import vm.UserVM;

@Controller
@SessionAttributes("sessUser")
public class EventController {
	//Deployment
	private final String URI = "http://130.237.84.211:8080/mappletech/rest/event";
	//Local
	//private final String URI = "http://localhost:8080/tech2/rest/event";
	
	@RequestMapping(value="/handelser/mina-handelser", method = RequestMethod.GET)
	public ModelAndView getEvent()
	{
		System.out.println("get mina handelser");
		RestTemplate restTemplate = new RestTemplate();
		EventVM[] eventVMArray = restTemplate.getForObject(URI + "/getAllFromToday", EventVM[].class);
		List<EventVM> eventVMList = Arrays.asList(eventVMArray);
		System.out.println(eventVMList.size());
		ModelAndView modelAndView = new ModelAndView("handelser/mina-handelser/index");
		modelAndView.addObject("eventlist",eventVMList);
		return modelAndView;
	}
	
	@RequestMapping(value="/handelser/skapa-ny-handelse",method = RequestMethod.GET)
	public ModelAndView createEvent()
	{
		System.out.println("create event GET");
		return new ModelAndView("/handelser/skapa-handelser/index","eventvm",new EventVM());
	}
	
	@RequestMapping(value="/handelser/skapa-ny-handelse",method = RequestMethod.POST)
	public ModelAndView createEventPost(@ModelAttribute("eventvm")EventVM eventVM,
			@ModelAttribute("sessUser")UserVM user)
	{
		System.out.println("Post Handelse");
		System.out.println(eventVM.getTitle() + " " + eventVM.getDescription());
		eventVM.setCreator(user.getUsername());
		RestTemplate restTemplate = new RestTemplate();
		if(restTemplate.postForObject(URI + "/add", eventVM, Boolean.class))
			System.out.println("True");
		else
			System.out.println("False");
		
		return new ModelAndView("/handelser/mina-handelser/index");
	}

}