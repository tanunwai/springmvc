package com.study.springmvc.case03.entity;

public class ExamPay {
	private String id;
	private String name;
	
	public ExamPay() {}

	public ExamPay(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}