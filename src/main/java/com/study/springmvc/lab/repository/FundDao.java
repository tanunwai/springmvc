package com.study.springmvc.lab.repository;

import java.util.List;

import com.study.springmvc.lab.entity.Fund;

public interface FundDao {
	
	int LIMIT=5;//�C��5��
	
	List<Fund> queryAll();//�d�ߥ���
	
	List<Fund> queryPage(int offset);//�����d��
	
	List<Fund> queryPage2(int offset);//�����d��sql���~��
	
	Fund get(Integer fid);//���o�浧
	
	int count();//�d�ߩҦ�����
	
	int add(Fund fund);//�s�W
	
	int update(Fund fund);//�ק�
	
	int delete(Integer fid);//�R��
}
