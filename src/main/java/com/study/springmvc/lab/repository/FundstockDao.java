package com.study.springmvc.lab.repository;

import java.util.List;
import java.util.Map;

import com.study.springmvc.lab.entity.Fundstock;

public interface FundstockDao {

	int LIMIT=5;//�C��5��
	
	List<Fundstock> queryAll();//�d�ߥ���
	
	List<Fundstock> queryPage(int offset);//�����d��
	
	Fundstock get(Integer sid);//���o�浧
	
	int count();//���o�Ҧ�����
	
	int add(Fundstock fundstock);//�s�W
	
	int update(Fundstock fundstock);//�ק�
	
	int delete(Integer sid);//�R��
	
	Map<String, Integer> getGroupMap();
}
