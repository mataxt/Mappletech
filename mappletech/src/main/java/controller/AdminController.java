package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vm.UserVM;

@Controller
public class AdminController {

	private final String URI = "http://130.237.84.211:8080/mappletech/rest/admin";
	
	// ===================== Edit anvandare =================================
	@RequestMapping(value = { "/administrator/anvandare" }, method = RequestMethod.GET)
	public ModelAndView editUsersGet() {
		ModelAndView mv = new ModelAndView("administrator/anvandare/index");
		mv.addObject("uservm", new UserVM());
		return mv;
	}

	@RequestMapping(value = "/administrator/anvandare", method = RequestMethod.POST)
	public String editUsersPost(@ModelAttribute("uservm") UserVM userVm, Model model) {

		UserVM newUser = new UserVM();
		
		newUser.setUsername(userVm.getUsername());
		newUser.setFullName(userVm.getFullName());
		newUser.setPhoneNumber(userVm.getPhoneNumber());
		newUser.setMobileNumber(userVm.getMobileNumber());
		newUser.setAddress(userVm.getAddress());
		newUser.setPrivilege(userVm.getPrivilege());

		return "redirect:/administrator/anvandare";
	}

	// ======================================================================
	
	
}
