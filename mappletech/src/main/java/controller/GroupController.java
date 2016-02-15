package controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import vm.ReservationVM;
import vm.UserVM;

@Controller
@SessionAttributes("sessUser")
public class GroupController {

	private final String URI = "http://130.237.84.211:8080/mappletech/rest/login";
	
	@RequestMapping(value = { "/grupper/visa-alla-grupper/" }, method = RequestMethod.GET)
	public ModelAndView viewAll(@ModelAttribute("sessUser") UserVM sessUser) {
		System.out.println("In GET mygrp...");
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		ArrayList<ReservationVM> grpVm = restTemplate.getForObject(URI + "group/getAll/{user}", ArrayList.class, sessUser.getUsername());
		System.out.println(grpVm.toString());
		ModelAndView mv = new ModelAndView("grupper/mina-grupper/index");
//		mv.addObject("resRem", new ReservationVM());
		mv.addObject("mygroups", grpVm);
		return mv;
	}
}
