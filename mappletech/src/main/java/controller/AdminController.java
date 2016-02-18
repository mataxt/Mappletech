package controller;

import java.util.ArrayList;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import vm.GroupVM;
import vm.ReportVM;
import vm.ReservationVM;
import vm.UserVM;

@Controller
public class AdminController {

	private final String URI = "http://130.237.84.211:8080/mappletech/rest";

	// ===================== bokningar =================================

	@RequestMapping(value = { "/administrator/bokningar" }, method = RequestMethod.GET)
	public ModelAndView removeBookingGet(ModelMap model) {

		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		ArrayList<ReservationVM> reservationList = restTemplate.getForObject(URI + "/reservation/getReservations",
				ArrayList.class);

		ModelAndView mv = new ModelAndView("administrator/bokningar/index");
		mv.addObject("list", reservationList);

		return mv;
	}

	@RequestMapping(value = "/administrator/bokningar", method = RequestMethod.POST)
	public String removeBookingPost(HttpServletRequest request) {

		ReservationVM r = new ReservationVM();
		r.setReservationId(Integer.parseInt(request.getParameter("remove")));
		System.out.println("REsID: " + r.getReservationId());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(URI + "/reservation/remove", r, Boolean.class);

		return "redirect:/";
	}

	// ===================== ta bort anvandare =================================

	@RequestMapping(value = { "/administrator/anvandare" }, method = RequestMethod.GET)
	public ModelAndView allUsersGet() {
		
		RestTemplate rest = new RestTemplate();
		ArrayList<UserVM> list = rest.getForObject(URI+"/administrator/getAllUsers", ArrayList.class);
		
		ModelAndView mv = new ModelAndView("administrator/anvandare/index");
		mv.addObject("list",list);
		
		return mv;
	}

	@RequestMapping(value = "/administrator/anvandare", method = RequestMethod.POST)
	public String allUsersPost(HttpServletRequest request) {

		UserVM newUser = new UserVM();
		newUser.setUsername(request.getParameter("remove"));
		RestTemplate rest = new RestTemplate();
		rest.postForObject(URI+"/administrator/removeUser",newUser, Boolean.class);

		return "redirect:/";
	}

	// ======================== felanmalan ================================

	@RequestMapping(value = { "/administrator/felanmalan" }, method = RequestMethod.GET)
	public ModelAndView errorReportGet() {

		RestTemplate rest = new RestTemplate();
		ArrayList<ReportVM> list = rest.getForObject(URI + "/report/getAllReports", ArrayList.class);

		ModelAndView mv = new ModelAndView("administrator/felanmalan/index");
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(value = "/administrator/felanmalan", method = RequestMethod.POST)
	public String errorReportPost(HttpServletRequest request) {

		ReportVM reportVm = new ReportVM();
		reportVm.setReportId(Integer.parseInt(request.getParameter("remove")));

		RestTemplate restTemplate = new RestTemplate();
		boolean success = restTemplate.postForObject(URI + "/report/remove", reportVm, Boolean.class);

		if (success) {
			return "redirect:/administrator";
		}
		return "redirect:/administrator/felanmalan/index";
	}

	// ======================== ny anvandare ================================

	@RequestMapping(value = { "/administrator/anvandare/lagg-till-anvandare" }, method = RequestMethod.GET)
	public ModelAndView addUsersGet() {
		ModelAndView mv = new ModelAndView("administrator/anvandare/lagg-till-anvandare/index");
		mv.addObject("uservm", new UserVM());
		return mv;
	}

	@RequestMapping(value = "/administrator/anvandare/lagg-till-anvandare", method = RequestMethod.POST)
	public String addUsersPost(@ModelAttribute("uservm") UserVM userVm, Model model) {

		UserVM newUser = new UserVM(userVm.getUsername(), generatePassword(), userVm.getFullName(),
				userVm.getPrivilege());
		RestTemplate restTemplate = new RestTemplate();
		boolean userExists = restTemplate.postForObject(URI + "/lagg-till-anvandare", newUser, Boolean.class);

		if (!userExists) {
			return "redirect:/administrator/anvandare/lagg-till-anvandare/index";
		}
		return "redirect:/administrator/anvandare";
	}

	// ======================== Groups ================================

	@RequestMapping(value = { "/administrator/grupper" }, method = RequestMethod.GET)
	public ModelAndView removeGroupGet() {

		ModelAndView mv = new ModelAndView("administrator/grupper/index");
		mv.addObject("groupVm", new GroupVM());
		return mv;
	}

	@RequestMapping(value = "/administrator/grupper", method = RequestMethod.POST)
	public String removeGroupPost(@ModelAttribute GroupVM groupVm, Model model) {

		RestTemplate restTemplate = new RestTemplate();
		boolean success = restTemplate.postForObject(URI + "/grupper", groupVm, Boolean.class);

		if (!success) {
			return "redirect:/administrator/";
		}
		return "redirect:/administrator/grupper/index";
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
