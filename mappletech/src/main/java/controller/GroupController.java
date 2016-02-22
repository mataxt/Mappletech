package controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import vm.GroupVM;
import vm.UserVM;

@Controller
@SessionAttributes("sessUser")
public class GroupController {

	private final String URI = "http://130.237.84.211:8080/mappletech/rest/";
	
	@RequestMapping(value = { "/grupper/skapa-grupper/" }, method = RequestMethod.GET)
	public ModelAndView add() {
		System.out.println("In GET mygrp...");
		ModelAndView mv = new ModelAndView("grupper/skapa-grupper/index");
		mv.addObject("newgroupview", new GroupVM());
		return mv;
	}
	
	@RequestMapping(value = { "/grupper/skapa-grupper/" }, method = RequestMethod.POST)
	public ModelAndView addPost(@ModelAttribute("newgroupview") GroupVM grpVm,
			@ModelAttribute("sessUser") UserVM sessUser) {
		System.out.println("In POST mygrp...");
		RestTemplate restTemplate = new RestTemplate();
		grpVm.setHost(sessUser.getUsername());
		Boolean success = restTemplate.postForObject(URI + "group/add",grpVm, Boolean.class);
		if (success) {
			System.out.println("Success");
		} else {
			System.out.println("Failure");
		}
		ModelAndView mv = new ModelAndView("grupper/skapa-grupper/index");
		mv.addObject("newgroupview", new GroupVM());
		return mv;
	}
	
	@RequestMapping(value = { "/grupper/visa-alla-grupper/" }, method = RequestMethod.GET)
	public ModelAndView viewAll() {
		System.out.println("In GET mygrp...");
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		ArrayList<GroupVM> grpVm = restTemplate.getForObject(URI + "group/getAll", ArrayList.class);
		System.out.println(grpVm.toString());
		ModelAndView mv = new ModelAndView("grupper/visa-alla-grupper/index");
		mv.addObject("mygroups", grpVm);
		return mv;
	}
	
	@RequestMapping(value = { "/grupper/mina-grupper/" }, method = RequestMethod.GET)
	public ModelAndView viewMy(@ModelAttribute("sessUser") UserVM sessUser) {
		System.out.println("In GET mygrp...");
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		ArrayList<GroupVM> grpVm = restTemplate.getForObject(URI + "group/getAll/{user}", ArrayList.class, sessUser.getUsername());
		System.out.println(grpVm.toString());
		ModelAndView mv = new ModelAndView("grupper/mina-grupper/index");
		mv.addObject("mygroups", grpVm);
		return mv;
	}
	
	@RequestMapping(value = { "/grupper/{groupName}" }, method = RequestMethod.GET)
	public ModelAndView viewGroup(@PathVariable("groupName") String groupName) {
		System.out.println("In GET mygrp...");
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		GroupVM grpVm = restTemplate.getForObject(URI + "group/{groupName}", GroupVM.class);
		System.out.println(grpVm.toString());
		ModelAndView mv = new ModelAndView("grupper/index");
		mv.addObject("mygroups", grpVm);
		return mv;
	}
}
