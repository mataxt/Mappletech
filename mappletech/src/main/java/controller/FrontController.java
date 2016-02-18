package controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import vm.ReportVM;
import vm.UserVM;


@Controller
@SessionAttributes("sessUser")
public class FrontController {

//	private static final String URI = "http://130.237.84.211:8080/mappletech/rest/";
	private static final String URI = "http://localhost:8080/tech2/rest/";
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView front(Model model) {
		System.out.println("In GET Main...");
		if (model.containsAttribute("sessUser")){
			ModelAndView mv = new ModelAndView("index");
			mv.addObject("repVm", new ReportVM());
			return mv;
		}else
			return new ModelAndView("redirect:login");
	}
	
	@RequestMapping(value = { "report" }, method = RequestMethod.POST)
	public ModelAndView report(@ModelAttribute("sessUser") UserVM sessUser,@ModelAttribute("repVm") ReportVM repVm) {
		System.out.println("In POST report...");
		repVm.setStatus("Pending");
		repVm.setDate(new Date(System.currentTimeMillis()));
		repVm.setReporter(sessUser.getUsername());
		RestTemplate restTemplate = new RestTemplate();
		
		if(restTemplate.postForObject(URI + "report/add", repVm, Boolean.class))
			System.out.println("Success");
		else
			System.out.println("Failed");
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = { "contact" }, method = RequestMethod.POST)
	public String contact(Model model) {
		System.out.println("In POST contact...");
		return "index";
	}
}
