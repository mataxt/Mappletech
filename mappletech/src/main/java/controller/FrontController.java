package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("sessUser")
public class FrontController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String main(Model model) {
		System.out.println("In GET Main...");
		if (model.containsAttribute("sessUser"))
			return "index";
		else
			return "redirect:login";
	}
}
