package com.study.springmvc.case03.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import com.study.springmvc.case03.entity.Exam;
import com.study.springmvc.case03.entity.ExamPay;
import com.study.springmvc.case03.entity.ExamSlot;
import com.study.springmvc.case03.entity.ExamSubject;

@Service
public class ExamService {
	//多執行緒安全;註冊考試的集合
	private List<Exam> exams=new CopyOnWriteArrayList<>();
	private List<ExamSubject> examSubjects=new CopyOnWriteArrayList<>();
	private List<ExamSlot> examSlots=new CopyOnWriteArrayList<>();
	private List<ExamPay> examPay=new CopyOnWriteArrayList<>();
	
	{
		//Constructed sub-injection for subject
		examSubjects.add(new ExamSubject("808", "JavaSE 8 OCP Ⅰ"));
		examSubjects.add(new ExamSubject("809", "JavaSE 8 OCP Ⅱ"));
		examSubjects.add(new ExamSubject("819", "JavaSE 11 OCP"));
		examSubjects.add(new ExamSubject("900", "JavaWeb OCE"));
		
		//Constructed sub-injection for slot
		examSlots.add(new ExamSlot("A", "In the morning"));
		examSlots.add(new ExamSlot("B", "In the afternoon"));
		examSlots.add(new ExamSlot("C", "At night"));
		
		//Constructed sub-injection for slot
		examPay.add(new ExamPay("true", "已繳"));
		examPay.add(new ExamPay("false", "未繳"));
	}
	
	//查詢多筆exam pay
		public List<ExamPay> queryExamPayList(){
			return examPay;
		}
	
	//查詢多筆exam slot
	public List<ExamSlot> queryExamSlotList(){
		return examSlots;
	}
	
	//查詢多筆exam subjects
	public List<ExamSubject> queryExamSubjectList(){
		return examSubjects;
	}
	
	//多筆查詢
	public List<Exam> query(){
		return exams;
	}
	
	//單筆查詢
	public Optional<Exam> get(int index) {
		if(index <0 || exams.size()==0 || index >= exams.size()) {
			return Optional.ofNullable(null);
		}
		return Optional.of(exams.get(index));
	}
	
	//新增
	public synchronized boolean add(Exam exam) {
		int previousSize=exams.size();
		exams.add(exam);
		int nextSize=exams.size();
		return nextSize > previousSize;
	}
	
	//修改
	//判斷index及exams的長度
	public synchronized boolean update(int index, Exam exam) {
		if(index <0 || exams.size()==0 || index >= exams.size()) {
			return false;
		}
		exams.set(index, exam);
		return true;
	}
	
	//修改
		//判斷index及exams的長度
		public synchronized boolean updateExamNote(int index, String examNote) {
			if(index <0 || exams.size()==0 || index >= exams.size()) {
				return false;
			}
			Exam exam=exams.get(index);
			exam.setExamNote(examNote);
			//exams.set(index, exam);
			return true;
		}
	
	//刪除
	public synchronized boolean delete(int index) {
		if(index <0 || exams.size()==0 || index >= exams.size()) {
			return false;
		}
		exams.remove(index);
		return true;
	}
}