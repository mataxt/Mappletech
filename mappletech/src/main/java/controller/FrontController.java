package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes("sessUser")
public class FrontController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("In GET Main...");
		return new ModelAndView("index");
	}
}
