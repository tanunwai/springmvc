package com.study.springmvc.case03.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Exam {
	private String studentId;
	private String examId;
	private String[] examSlot;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date examDate;
	private boolean examPay;
	private String examNote;
	
	public Exam() {}

	public Exam(String studentId, String examId, String[] examSlot, Date examDate, boolean examPay, String examNote) {
		super();
		this.studentId = studentId;
		this.examId = examId;
		this.examSlot = examSlot;
		this.examDate = examDate;
		this.examPay = examPay;
		this.examNote = examNote;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String[] getExamSlot() {
		return examSlot;
	}

	public void setExamSlot(String[] examSlot) {
		this.examSlot = examSlot;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public boolean isExamPay() {
		return examPay;
	}

	public void setExamPay(boolean examPay) {
		this.examPay = examPay;
	}

	public String getExamNote() {
		return examNote;
	}

	public void setExamNote(String examNote) {
		this.examNote = examNote;
	}

	@Override
	public String toString() {
		return "Exam [studentId=" + studentId + ", examId=" + examId + ", examSlot=" + Arrays.toString(examSlot)
				+ ", examDate=" + examDate + ", examPay=" + examPay + ", examNote=" + examNote + "]";
	}	
}