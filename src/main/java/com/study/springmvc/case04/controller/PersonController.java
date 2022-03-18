package com.study.springmvc.case04.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springmvc.case04.entity.Person;

@Controller
@RequestMapping(path= {"/case04/person"})
public class PersonController {

	private List<Person> people=new CopyOnWriteArrayList<>();
	
	@GetMapping(path= {"/"})
	public String index(@ModelAttribute Person person, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("people", people);
		return "case04/person";
	}
	
	@GetMapping(path= {"/{index}"})
	public String get(@PathVariable(value = "index")int index, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("people", people);
		model.addAttribute("person", people.get(index));
		return "case04/person";
	}
	
	@PostMapping(path= {"/"})
	public String add(@Valid Person person, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("people",people);
			return "case04/person";
		}
		people.add(person);
		return "redirect:./";
	}
	
	@PutMapping(path= {"/{index}"})
	public String update(@PathVariable(value = "index")int index,@Valid Person person, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("people",people);
			return "case04/person";
		}
		people.set(index, person);
		return "redirect:./";
	}
}