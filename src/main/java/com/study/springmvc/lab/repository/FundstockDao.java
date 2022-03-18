package com.study.springmvc.lab.repository;

import java.util.List;
import java.util.Map;

import com.study.springmvc.lab.entity.Fundstock;

public interface FundstockDao {

	int LIMIT=5;//每頁5筆
	
	List<Fundstock> queryAll();//查詢全部
	
	List<Fundstock> queryPage(int offset);//分頁查詢
	
	Fundstock get(Integer sid);//取得單筆
	
	int count();//取得所有筆數
	
	int add(Fundstock fundstock);//新增
	
	int update(Fundstock fundstock);//修改
	
	int delete(Integer sid);//刪除
	
	Map<String, Integer> getGroupMap();
}
