package com.study.springmvc.case02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.study.springmvc.case02.service.TimeService;

@Controller
@RequestMapping(path= {"/case02/time"})
public class TimeController {
	@Autowired
	private TimeService timeService;
	
	@GetMapping(path={"/now"})
	public ModelAndView getCurrentDateAndTime() {
		String time=timeService.getDateAndTimeStamp();
		String view="case02/show_time";//folder:views/case02;don't add .jsp
		ModelAndView md=new ModelAndView();
		md.addObject("time",time);
		md.setViewName(view);
		return md;
	}
	
	@GetMapping(path={"/now2"})
	public ModelAndView getCurrentDateAndTime2() {
		String time=timeService.getDateAndTimeStamp();
		String view="case02/show_time";//folder:views/case02;don't add .jsp
		return new ModelAndView(view, "time", time);
	}
	
	@GetMapping(path={"/now3"})
	public ModelAndView getCurrentDateAndTime3() {
		String time=timeService.getDateAndTimeStamp();
		String view="case02/show_time";//folder:views/case02;don't add .jsp
		return new ModelAndView(view).addObject("time", time);
	}
	
	@GetMapping(path={"/now4"})
	public String getCurrentDateAndTime4(Model model) {
		String time=timeService.getDateAndTimeStamp();
		model.addAttribute("time", time);
		return "case02/show_time";
		//don't add @ResponseBody
	}
}