package com.study.springmvc.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springmvc.lab.entity.Fund;
import com.study.springmvc.lab.repository.FundDao;

@RestController
@RequestMapping(path = { "/lab/fund" })
public class FundController {
	@Autowired
	private FundDao fundDao;
	
	private int pageNumber;

	@GetMapping(path = { "/" })
	public List<Fund> index() {
		List<Fund> funds = fundDao.queryAll();
		// 將沒有成分股的基金其fundstock屬性設定成null
		funds.stream().filter(f -> f.getFundstocks() != null)
						.filter(f -> f.getFundstocks().get(0).getFid() == null)
						.forEach(f -> f.setFundstocks(null));
		return funds;
	}
	
	@GetMapping(path= {"/{fid}"})
	public Fund get(@PathVariable(value = "fid") Integer fid) {
		return fundDao.get(fid);
	}
	
	@GetMapping(path= {"/totalPagecount"})
	public int totalPagecount() {
		return (int)Math.ceil(fundDao.count()/(double)FundDao.LIMIT);//使用無條件進位
	}
	
	@GetMapping(path= {"/page/{pageNumber}"})
	public List<Fund> page(@PathVariable("pageNumber") int pageNumber){
		this.pageNumber=pageNumber;
		int offset=(pageNumber-1)*FundDao.LIMIT;
		return fundDao.queryPage(offset);
	}
	
	@GetMapping(path= {"/pages/{pageNum}"})
	public List<Fund> pages(@PathVariable(value = "pageNum") Integer pageNum){
		this.pageNumber=pageNum;
		int offset=(pageNum-1)*FundDao.LIMIT;
		return fundDao.queryPage2(offset);
	}
	
	@PostMapping(path= {"/"})
	public int add(@RequestBody Fund fund) {
		return fundDao.add(fund);
	}
	
	@PutMapping(path= {"/"})
	public int update(@RequestBody Fund fund) {
		return fundDao.update(fund);
	}
	
	@DeleteMapping(path= {"/{fid}"})
	public int delete(@PathVariable(value = "fid") Integer fid) {
		return fundDao.delete(fid);
	}
}