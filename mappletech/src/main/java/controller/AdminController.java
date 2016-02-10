package controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import vm.ReportVM;
import vm.ReservationVM;
import vm.UserVM;

@Controller
public class AdminController {

	private final String URI = "http://130.237.84.211:8080/mappletech/rest/admin";
	
	// ===================== bokningar =================================
	
	@RequestMapping(value = { "/mappletech/administrator/bokningar" }, method = RequestMethod.GET)
	public ModelAndView editBookingGet() {
		
		ModelAndView mv = new ModelAndView("mappletech/administrator/bokningar/index");
		ReservationVM reservationVm = new ReservationVM();
		reservationVm.setHost("username");
		mv.addObject("reservationVm", reservationVm);
		
		return mv;
	}

	@RequestMapping(value = "/mappletech/administrator/bokningar", method = RequestMethod.POST)
	public String editBookingPost(@ModelAttribute("reservationVm") ReservationVM reservationVm, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		//send modified reservation -->
		boolean success = restTemplate.postForObject(URI, reservationVm, Boolean.class);
		
		if (success) {
			return "redirect:/mappletech/administrator/bokningar";
		}
		return "redirect:/mappletech/administrator/bokningar";
	}
	// ======================================================================
	
	
	// ===================== Edit anvandare =================================
	
	@RequestMapping(value = { "/mappletech/administrator/anvandare" }, method = RequestMethod.GET)
	public ModelAndView editUsersGet() {
		ModelAndView mv = new ModelAndView("mappletech/administrator/anvandare/index");
		mv.addObject("uservm", new UserVM());
		return mv;
	}

	@RequestMapping(value = "/mappletech/administrator/anvandare", method = RequestMethod.POST)
	public String editUsersPost(@ModelAttribute("uservm") UserVM userVm, Model model) {

		UserVM newUser = new UserVM();
		
		newUser.setUsername(userVm.getUsername());
		newUser.setFullName(userVm.getFullName());
		newUser.setPhoneNumber(userVm.getPhoneNumber());
		newUser.setMobileNumber(userVm.getMobileNumber());
		newUser.setAddress(userVm.getAddress());
		newUser.setPrivilege(userVm.getPrivilege());

		return "redirect:/mappletech/administrator/anvandare";
	}

	// ======================================================================
	// ======================== felanmalan ================================
	
	@RequestMapping(value = { "/mappletech/administrator/felanmalan" }, method = RequestMethod.GET)
	public ModelAndView errorReportGet() {
		ModelAndView mv = new ModelAndView("mappletech/administrator/felanmalan/index");
		mv.addObject("uservm", new UserVM());
		return mv;
	}

	@RequestMapping(value = "/mappletech/administrator/felanmalan", method = RequestMethod.POST)
	public String errorReportPost(@ModelAttribute("reportVm") ReportVM reportVm, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		boolean success = restTemplate.postForObject(URI, reportVm, Boolean.class);
		
		if (success) {
			return "redirect:/mappletech/administrator/felanmalan/index";
		}
		return "redirect:/mappletech/administrator/felanmalan/index";
	}
	
	// ======================================================================
	
	// ======================== ny anvandare ================================
	
			@RequestMapping(value = { "/mappletech/administrator/anvandare/nyanvandare" }, method = RequestMethod.GET)
			public ModelAndView addUsersGet() {
				System.out.println("In GET anvandare hantera");
				ModelAndView mv = new ModelAndView("mappletech/administrator/anvandare/nyanvandare/index");
				mv.addObject("uservm", new UserVM());
				return mv;
			}

			@RequestMapping(value = "/mappletech/administrator/anvandare/nyanvandare", method = RequestMethod.POST)
			public String addUsersPost(@ModelAttribute("uservm") UserVM userVm, Model model) {
				
				UserVM newUser = new UserVM(userVm.getUsername(), generatePassword(), userVm.getFullName(), userVm.getPrivilege());
				RestTemplate restTemplate = new RestTemplate();
				boolean userExists = restTemplate.postForObject(URI, newUser, Boolean.class);
				
				if (!userExists) {
					return "redirect:/mappletech/administrator/anvandare/nyanvandare/index";
				}
				return "redirect:/mappletech/administrator/anvandare";
			}
	// ======================================================================
			
			// Generate a password for the new user created by admin
			
			private String generatePassword() {
				final String charset = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
				Random rand = new Random(System.currentTimeMillis());
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < 6; i++) {
					int pos = rand.nextInt(charset.length());
					sb.append(charset.charAt(pos));
				}
				return sb.toString();
			}
			
	// ======================================================================	
			
}
