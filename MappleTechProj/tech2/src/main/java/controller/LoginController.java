package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import forms.UserForm;

@Controller
public class LoginController {

	private String name, password;
	private UserForm userForm = new UserForm();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
		System.out.println("HEEEEEEJ");
		
		model.addAttribute("user", userForm);
        //return "login/login";
		ModelAndView mv = new ModelAndView("login/login");
		mv.addObject("user", userForm);
		return mv;
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") UserForm userForm, Model model) {
		System.out.println("Username: " + userForm.getUsername());
		return "redirect:/";
	}

}
