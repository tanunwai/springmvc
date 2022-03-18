package com.study.springmvc.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springmvc.lab.entity.Fund;
import com.study.springmvc.lab.entity.Fundstock;
import com.study.springmvc.lab.repository.FundDao;
import com.study.springmvc.lab.repository.FundstockDao;
import com.study.springmvc.lab.validator.FundstockValidator;

@Controller
@RequestMapping(path = { "/lab/fundstock" })
public class FundstockController {
	@Autowired
	private FundstockDao fundstockDao;

	@Autowired
	private FundDao fundDao;

	@Autowired
	private FundstockValidator fundstockValidator;

	private int pageNum = -1;

	/*
	 * @GetMapping(path= {"/test/query"})
	 * 
	 * @ResponseBody public List<Fundstock> index() { return
	 * fundstockDao.queryAll(); }
	 */

	@GetMapping(path = { "/" })
	public String index(@ModelAttribute Fundstock fundstock, Model model) {
		return "redirect:./page/" + pageNum;
	}

	@GetMapping(path = { "/page/{pageNum}" })
	public String page(@PathVariable(value = "pageNum") int pageNum, @ModelAttribute Fundstock fundstock, Model model) {
		// page->1:0->1:5->2:10->¨C­¶5µ§­pºâ
		this.pageNum = pageNum;
		int offset = (pageNum - 1) * FundstockDao.LIMIT;
		List<Fundstock> fundstocks = fundstockDao.queryPage(offset);
		List<Fund> funds = fundDao.queryAll();
		int pageTotalCount = fundstockDao.count() / FundstockDao.LIMIT;

		model.addAttribute("_method", "POST");
		model.addAttribute("fundstocks", fundstocks);
		model.addAttribute("funds", funds);
		model.addAttribute("pageTotalCount", pageTotalCount);
		model.addAttribute("groupMap", fundstockDao.getGroupMap());
		return "lab/fundstock";
	}

	/*
	 * @GetMapping(path= {"/{sid}"}) public Fundstock get(@PathVariable(value =
	 * "sid") int sid) { return fundstockDao.get(sid); }
	 */
	@GetMapping(path = { "/{sid}" })
	public String get(@PathVariable(value = "sid") Integer sid, Model model) {
		Fundstock fundstock = fundstockDao.get(sid);
		model.addAttribute("_method", "PUT");
		model.addAttribute("fundstock", fundstock);
		model.addAttribute("funds", fundDao.queryAll());
		model.addAttribute("groupMap", fundstockDao.getGroupMap());
		return "lab/fundstock";
	}

	@PostMapping(path = { "/" })
	public String add(@ModelAttribute Fundstock fundstock, BindingResult bindingResult, Model model) {
		fundstockValidator.validate(fundstock, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("fundstocks", fundstockDao.queryAll());
			model.addAttribute("funds", fundDao.queryAll());
			model.addAttribute("groupMap", fundstockDao.getGroupMap());
			return "lab/fundstock";
		}
		fundstockDao.add(fundstock);
		return "redirect:./";
	}

	@PutMapping(path = { "/{sid}" })
	public String update(@PathVariable(value = "sid") Integer sid, @ModelAttribute Fundstock fundstock,
			BindingResult bindingResult, Model model) {
		fundstockValidator.validate(fundstock, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("fundstocks", fundstockDao.queryAll());
			model.addAttribute("funds", fundDao.queryAll());
			model.addAttribute("groupMap", fundstockDao.getGroupMap());
			return "lab/fundstock";
		}
		fundstockDao.update(fundstock);
		return "redirect:./";
	}

	@DeleteMapping(path = { "/{sid}" })
	public String delete(@PathVariable(value = "sid") Integer sid) {
		fundstockDao.delete(sid);
		return "redirect:./";
	}
}