package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import vm.UserVM;

@Controller
@SessionAttributes("sessUser")
public class FrontController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView login(Model model) {
		System.out.println("In GET Main...");
		if (model.containsAttribute("sessUser"))
			return new ModelAndView("index");
		else
			return new ModelAndView("login/index","uservm",new UserVM());
	}
}
