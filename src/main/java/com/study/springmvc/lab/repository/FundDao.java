package com.study.springmvc.lab.repository;

import java.util.List;

import com.study.springmvc.lab.entity.Fund;

public interface FundDao {
	
	int LIMIT=5;//–5掸
	
	List<Fund> queryAll();//琩高场
	
	List<Fund> queryPage(int offset);//だ琩高
	
	List<Fund> queryPage2(int offset);//だ琩高sqlΤ粇
	
	Fund get(Integer fid);//眔虫掸
	
	int count();//琩高┮Τ掸计
	
	int add(Fund fund);//穝糤
	
	int update(Fund fund);//э
	
	int delete(Integer fid);//埃
}
