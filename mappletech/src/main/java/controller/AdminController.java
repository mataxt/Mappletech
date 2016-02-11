package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

	private final String URI = "http://130.237.84.211:8080/mappletech/rest";
	// ===================== bokningar =================================
	
	@RequestMapping(value = { "/administrator/bokningar" }, method = RequestMethod.GET)
	public ModelAndView editBookingGet(ModelMap model) {
		
		ModelAndView mv = new ModelAndView("administrator/bokningar/index");
		ReservationVM reservationVm = new ReservationVM();

		List<ReservationVM> reservationList = new ArrayList<>();
		
		RestTemplate restTemplate = new RestTemplate();
		reservationList = restTemplate.postForObject( URI + "/reservation/getReservations",null, List.class);
	
		model.addAttribute("reservationVm", reservationVm);
		model.addAttribute("reservationList", reservationList);
		
		mv.addObject("reservationVm", reservationVm);
		
		return mv;
	}

	@RequestMapping(value = "/administrator/bokningar", method = RequestMethod.POST)
	public String editBookingPost(@ModelAttribute ReservationVM reservationVm, Model model) {
		
		if(reservationVm==null){
			return "redirect:/administrator/bokningar/index";
		}
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(URI + "/reservation/removeReservation", reservationVm, Boolean.class);
		
		return "redirect:/administrator/bokningar/index";
	}
	// ======================================================================
	
	
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
	// ======================== felanmalan ================================
	
	@RequestMapping(value = { "/administrator/felanmalan" }, method = RequestMethod.GET)
	public ModelAndView errorReportGet() {
		ModelAndView mv = new ModelAndView("administrator/felanmalan/index");
		mv.addObject("uservm", new UserVM());
		return mv;
	}

	@RequestMapping(value = "/administrator/felanmalan", method = RequestMethod.POST)
	public String errorReportPost(@ModelAttribute("reportVm") ReportVM reportVm, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		boolean success = restTemplate.postForObject(URI, reportVm, Boolean.class);
		
		if (success) {
			return "redirect:/administrator/felanmalan/index";
		}
		return "redirect:/administrator/felanmalan/index";
	}
	
	// ======================================================================
	
	// ======================== ny anvandare ================================
	
			@RequestMapping(value = { "/administrator/anvandare/lagg-till-anvandare" }, method = RequestMethod.GET)
			public ModelAndView addUsersGet() {
				System.out.println("In GET anvandare hantera");
				ModelAndView mv = new ModelAndView("administrator/anvandare/lagg-till-anvandare/index");
				mv.addObject("uservm", new UserVM());
				return mv;
			}

			@RequestMapping(value = "/administrator/anvandare/lagg-till-anvandare", method = RequestMethod.POST)
			public String addUsersPost(@ModelAttribute("uservm") UserVM userVm, Model model) {
				
				UserVM newUser = new UserVM(userVm.getUsername(), generatePassword(), userVm.getFullName(), userVm.getPrivilege());
				RestTemplate restTemplate = new RestTemplate();
				boolean userExists = restTemplate.postForObject(URI, newUser, Boolean.class);
				
				if (!userExists) {
					return "redirect:/administrator/anvandare/lagg-till-anvandare/index";
				}
				return "redirect:/administrator/anvandare";
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
