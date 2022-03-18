package com.study.springmvc.case02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path= {"/case02/redirect"})
public class RedirectController {
	
	@RequestMapping(path= {"/demo1"}, method= {RequestMethod.GET})
	public String getRedirect() {
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(path= {"/demo2"}, method= {RequestMethod.GET})
	public String getRedirect2() {
		return "redirect:../time/now";
		//ª`·N¥Ø¿ýºô§}
	}
	
	@RequestMapping(path= {"/demo3"}, method= {RequestMethod.GET})
	public String getRedirect3() {
		return "redirect:https://tw.yahoo.com/";
	}
	
	@RequestMapping(path= {"/demo4"}, method= {RequestMethod.GET})
	public String getRedirect4() {
		return "redirect:/show_request_param.jsp?username=Vincent&age=18";
	}
	
	@RequestMapping(path= {"/demo5"}, method= {RequestMethod.GET})
	public String getRedirect5(RedirectAttributes attr) {
		attr.addAttribute("username", "Anita");
		attr.addAttribute("age", "19");
		return "redirect:/show_request_param.jsp";
	}
	
	@RequestMapping(path= {"/saveorder"}, method= {RequestMethod.GET})
	public String getRedirect6(@RequestParam(value = "name") String name,
							   @RequestParam(value = "price") Integer price,
							   @RequestParam(value = "qty") Integer qty,
							   RedirectAttributes attr) {
		attr.addFlashAttribute("name", name);
		attr.addFlashAttribute("price", price);
		attr.addFlashAttribute("qty", qty);
		return "redirect:./success";
		//return "case02/order_ok";<<can't show anything
	}
	
	@RequestMapping(path= {"/success"}, method= {RequestMethod.GET})	
	public String getRedirect6() {		
		return "case02/order_ok";
		//return "case02/show_lottos";
	}
}
