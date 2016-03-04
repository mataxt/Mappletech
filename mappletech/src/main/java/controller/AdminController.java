package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import vm.EventVM;
import vm.GroupVM;
import vm.ReportVM;
import vm.ReservationVM;
import vm.UserVM;

@Controller
@SessionAttributes("sessUser")
public class AdminController {

	private final String URI = "http://130.237.84.211:8080/mappletech/rest";

	// ===================== bokningar =================================

	@RequestMapping(value = { "/administrator/bokningar" }, method = RequestMethod.GET)
	public ModelAndView removeBookingGet(Model model, HttpServletRequest request) {
		
		UserVM us = (UserVM) request.getSession().getAttribute("sessUser");
		
		
		if(request.getSession().getAttribute("sessUser")==null){
			return new ModelAndView("redirect:login","uservm", new UserVM());
		}

		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		ArrayList<ReservationVM> reservationList = restTemplate.getForObject(URI + "/reservation/getReservations", ArrayList.class);

		ModelAndView mv = new ModelAndView("administrator/bokningar/index");
		mv.addObject("list", reservationList);

		return mv;
	}

	@RequestMapping(value = "/administrator/bokningar", method = RequestMethod.POST)
	public ModelAndView removeBookingPost(HttpServletRequest request) {

		ReservationVM r = new ReservationVM();
		r.setReservationId(Integer.parseInt(request.getParameter("remove")));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(URI + "/reservation/remove", r, Boolean.class);

		return new ModelAndView("/administrator/bokningar/index");
	}

	// ===================== ta bort anvandare =================================

	@RequestMapping(value = { "/administrator/anvandare" }, method = RequestMethod.GET)
	public ModelAndView allUsersGet() {
		
		RestTemplate rest = new RestTemplate();
		@SuppressWarnings("unchecked")
		ArrayList<UserVM> list = rest.getForObject(URI+"/administrator/getAllUsers", ArrayList.class);
		
		ModelAndView mv = new ModelAndView("administrator/anvandare/index");
		mv.addObject("list",list);
		
		return mv;
	}

	@RequestMapping(value = "/administrator/anvandare", method = RequestMethod.POST)
	public ModelAndView allUsersPost(HttpServletRequest request) {

		UserVM newUser = new UserVM();
		newUser.setUsername(request.getParameter("remove"));
		RestTemplate rest = new RestTemplate();
		System.out.println(rest.postForObject(URI+"/administrator/removeUser",newUser, Boolean.class));
		
		return new ModelAndView("redirect: /administrator/anvandare");
	}

	// ======================== felanmalan ================================

	@RequestMapping(value = { "/administrator/felanmalan" }, method = RequestMethod.GET)
	public ModelAndView errorReportGet() {

		RestTemplate rest = new RestTemplate();
		@SuppressWarnings("unchecked")
		ArrayList<ReportVM> list = rest.getForObject(URI + "/report/getAllReports", ArrayList.class);

		ModelAndView mv = new ModelAndView("administrator/felanmalan/index");
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(value = "/administrator/felanmalan", method = RequestMethod.POST)
	public ModelAndView errorReportPost(HttpServletRequest request) {

		ReportVM reportVm = new ReportVM();
		reportVm.setReportId(Integer.parseInt(request.getParameter("remove")));

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(URI + "/report/remove", reportVm, Boolean.class);

		return new ModelAndView("/administrator/felanmalan/index");
	}

	// ======================== ny anvandare ================================

	@RequestMapping(value = { "/administrator/anvandare/lagg-till-anvandare" }, method = RequestMethod.GET)
	public ModelAndView addUsersGet() {
		ModelAndView mv = new ModelAndView("administrator/anvandare/lagg-till-anvandare/index");
		mv.addObject("uservm", new UserVM());
		
		return mv;
	}

	@RequestMapping(value = "/administrator/anvandare/lagg-till-anvandare", method = RequestMethod.POST)
	public ModelAndView addUsersPost(@ModelAttribute("uservm") UserVM newUser) {
		
		ModelAndView mv = new ModelAndView("/administrator/anvandare/bekraftelse-ny-anvandare/index","uservm", newUser);
		mv.addObject("pwd",newUser.getPassword());
		mv.addObject("newUser",newUser);
		
		newUser.setPassword(passwordHash(newUser.getPassword()));
		
		RestTemplate restTemplate = new RestTemplate();
		boolean success = restTemplate.postForObject(URI + "/administrator/lagg-till-anvandare", newUser, Boolean.class);

		if (!success) { //probable cause: duplicate entry in db, username already registered
			return new ModelAndView("/administrator/anvandare/lagg-till-anvandare/index");
		}
		
		return mv;
	}
	
	// ======================== Groups ================================

	@RequestMapping(value = { "/administrator/grupper" }, method = RequestMethod.GET)
	public ModelAndView allGroupsGet() {

		RestTemplate rest = new RestTemplate();
		@SuppressWarnings("unchecked")
		ArrayList<GroupVM> list = rest.getForObject(URI + "/group/getAll", ArrayList.class);
		
		ModelAndView mv = new ModelAndView("administrator/grupper/index");
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping(value = "/administrator/grupper", method = RequestMethod.POST)
	public ModelAndView removeGroupPost(HttpServletRequest request) {

		GroupVM groupVm = new GroupVM();
		groupVm.setGroupName(request.getParameter("remove"));
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(URI + "/group/remove", groupVm, Boolean.class);

		return new ModelAndView("/administrator/grupper/index");
	}
	
	// ======================== Events ================================

	@RequestMapping(value = { "/administrator/handelser" }, method = RequestMethod.GET)
	public ModelAndView allEventsGet() {

		RestTemplate rest = new RestTemplate();
		@SuppressWarnings("unchecked")
		ArrayList<EventVM> list = rest.getForObject(URI + "/event/getAll", ArrayList.class);
		
		ModelAndView mv = new ModelAndView("administrator/handelser/index");
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping(value = "/administrator/handelser", method = RequestMethod.POST)
	public ModelAndView removeEventPost(HttpServletRequest request) {

		EventVM eventVm = new EventVM();
		eventVm.setEventID(Integer.parseInt(request.getParameter("remove")));
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(URI + "/event/delete", eventVm, Boolean.class);

		return new ModelAndView("/administrator/handelser/index");
	}

	// ======================================================================

	// Generate a password for the new user created by admin
	@ModelAttribute("generatedPwd")
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
	
	private String passwordHash(String pwd) {

		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		msgDigest.update(pwd.getBytes());
		byte[] digest = msgDigest.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	


	// ======================================================================

}
