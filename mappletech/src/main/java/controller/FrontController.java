package controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import vm.ReportVM;
import vm.UserVM;


@Controller
@SessionAttributes("sessUser")
public class FrontController {

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
	public ModelAndView report(@ModelAttribute UserVM sessUser,@ModelAttribute("repVm") ReportVM repVm) {
		System.out.println("In POST report...");
		repVm.setStatus("Pending");
		repVm.setDate(new Date(System.currentTimeMillis()));
		repVm.setReporter(sessUser.getUsername());
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = { "contact" }, method = RequestMethod.POST)
	public String contact(Model model) {
		System.out.println("In POST contact...");
		return "index";
	}
}
