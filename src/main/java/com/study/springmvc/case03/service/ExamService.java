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
	//�h������w��;���U�Ҹժ����X
	private List<Exam> exams=new CopyOnWriteArrayList<>();
	private List<ExamSubject> examSubjects=new CopyOnWriteArrayList<>();
	private List<ExamSlot> examSlots=new CopyOnWriteArrayList<>();
	private List<ExamPay> examPay=new CopyOnWriteArrayList<>();
	
	{
		//Constructed sub-injection for subject
		examSubjects.add(new ExamSubject("808", "JavaSE 8 OCP ��"));
		examSubjects.add(new ExamSubject("809", "JavaSE 8 OCP ��"));
		examSubjects.add(new ExamSubject("819", "JavaSE 11 OCP"));
		examSubjects.add(new ExamSubject("900", "JavaWeb OCE"));
		
		//Constructed sub-injection for slot
		examSlots.add(new ExamSlot("A", "In the morning"));
		examSlots.add(new ExamSlot("B", "In the afternoon"));
		examSlots.add(new ExamSlot("C", "At night"));
		
		//Constructed sub-injection for slot
		examPay.add(new ExamPay("true", "�wú"));
		examPay.add(new ExamPay("false", "��ú"));
	}
	
	//�d�ߦh��exam pay
		public List<ExamPay> queryExamPayList(){
			return examPay;
		}
	
	//�d�ߦh��exam slot
	public List<ExamSlot> queryExamSlotList(){
		return examSlots;
	}
	
	//�d�ߦh��exam subjects
	public List<ExamSubject> queryExamSubjectList(){
		return examSubjects;
	}
	
	//�h���d��
	public List<Exam> query(){
		return exams;
	}
	
	//�浧�d��
	public Optional<Exam> get(int index) {
		if(index <0 || exams.size()==0 || index >= exams.size()) {
			return Optional.ofNullable(null);
		}
		return Optional.of(exams.get(index));
	}
	
	//�s�W
	public synchronized boolean add(Exam exam) {
		int previousSize=exams.size();
		exams.add(exam);
		int nextSize=exams.size();
		return nextSize > previousSize;
	}
	
	//�ק�
	//�P�_index��exams������
	public synchronized boolean update(int index, Exam exam) {
		if(index <0 || exams.size()==0 || index >= exams.size()) {
			return false;
		}
		exams.set(index, exam);
		return true;
	}
	
	//�ק�
		//�P�_index��exams������
		public synchronized boolean updateExamNote(int index, String examNote) {
			if(index <0 || exams.size()==0 || index >= exams.size()) {
				return false;
			}
			Exam exam=exams.get(index);
			exam.setExamNote(examNote);
			//exams.set(index, exam);
			return true;
		}
	
	//�R��
	public synchronized boolean delete(int index) {
		if(index <0 || exams.size()==0 || index >= exams.size()) {
			return false;
		}
		exams.remove(index);
		return true;
	}
}