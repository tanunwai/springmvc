package com.study.springmvc.case02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springmvc.case02.service.LottoService;

@Controller
@RequestMapping("/case02/lotto")
public class LottoController {

	@Autowired
	private LottoService lottoService;

	@RequestMapping("/showlotto")
	public String index(Model model) {
		model.addAttribute("lottos", lottoService.getLottos());
		return "case02/show_lottos";
	}

	@RequestMapping("/add")
	public String add() {
		lottoService.addLottos();		
		return "redirect:./showlotto";
	}

	@RequestMapping("/update/{index}")
	public String updateLotto(@PathVariable(value = "index") int index) {
		lottoService.updateLottos(index);
		return "redirect:../showlotto";
	}

	@RequestMapping("/remove/{index}")
	public String deleteLotto(@PathVariable(value = "index") int index) {
		lottoService.removeLottos(index);
		return "redirect:../showlotto";
	}

	@RequestMapping(path = { "/statistics" })
	public String statisticsLotto(Model model) {
		System.out.println(lottoService.getStatistics().toString());
		model.addAttribute("numerical", lottoService.getStatistics());
		return "case02/show_lottos";
	}
}