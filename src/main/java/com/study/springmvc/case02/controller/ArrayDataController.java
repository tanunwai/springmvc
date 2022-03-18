package com.study.springmvc.case02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springmvc.case02.service.ArrayDataService;

@Controller
@RequestMapping(path= {"/case02/arraydata"})
public class ArrayDataController {

	@Autowired
	private ArrayDataService arrayDataService;
	
	@GetMapping(path = {"/showdata"})
	public String getIndex(Model model) {
		model.addAttribute("names",arrayDataService.getName());
		model.addAttribute("fruits", arrayDataService.getFruits());
		return "case02/show_data";
		//http://localhost:8080/springmvc/mvc/case02/arraydata/showdata/
	}
}
