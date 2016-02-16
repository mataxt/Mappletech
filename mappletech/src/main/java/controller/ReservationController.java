package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import vm.FacilityVM;
import vm.ReservationVM;
import vm.UserVM;

@Controller
@SessionAttributes("sessUser")
public class ReservationController {

	private final String URI = "http://130.237.84.211:8080/mappletech/rest/";
	
	@RequestMapping(value = "/bokning/boka", method = RequestMethod.GET)
	public ModelAndView reserve() {
		System.out.println("In GET Res...");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<FacilityVM[]> facVm = restTemplate.getForEntity(URI + "facility/getFacilities",
				FacilityVM[].class);
		Map<Integer, String> hmap = new HashMap<>();
		for (FacilityVM facilityVM : Arrays.asList(facVm.getBody())) {
			hmap.put(facilityVM.getFacilityId(), facilityVM.getFacilityName());
		}
		ModelAndView mv = new ModelAndView("bokning/boka/index");
		mv.addObject("facilities", hmap);
		mv.addObject("resvm", new ReservationVM());
		return mv;

	}

	@RequestMapping(value = "/bokning/boka", method = RequestMethod.POST)
	public ModelAndView doReserve(@ModelAttribute("resvm") ReservationVM resVm,
			@ModelAttribute("sessUser") UserVM sessUser) {
		System.out.println("In POST Res...");
		resVm.setHost(sessUser.getUsername());
		RestTemplate restTemplate = new RestTemplate();
		if (restTemplate.postForObject(URI + "reservation/add", resVm, Boolean.class)) {
			System.out.println("sucess");
		} else {
			System.out.println("failure");
		}

		return new ModelAndView("bokning/boka/index", "resvm", new ReservationVM());
	}


	@RequestMapping(value = "/bokning/mina-bokningar", method = RequestMethod.GET)
	public ModelAndView viewAll(@ModelAttribute("sessUser") UserVM sessUser) {
		System.out.println("In GET RVes...");
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		ArrayList<ReservationVM> resVm = restTemplate.getForObject(URI + "reservation/getReservations/{user}", ArrayList.class, sessUser.getUsername());
		System.out.println(resVm.toString());
		ModelAndView mv = new ModelAndView("bokning/mina-bokningar/index");
		mv.addObject("resRem", new ReservationVM());
		mv.addObject("reservs", resVm);
		return mv;
	}
	
	@RequestMapping(value = "/bokning/mina-bokningar", method = RequestMethod.POST)
	public String removeRes(HttpServletRequest request) {
		System.out.println("In POST RVes...");
		RestTemplate restTemplate = new RestTemplate();
		ReservationVM r = new ReservationVM();
		r.setReservationId(Integer.parseInt(request.getParameter("remove")));
		restTemplate.postForObject(URI + "reservation/remove", r, Boolean.class);
		return "redirect:/bokning/mina-bokningar";

	}
}
