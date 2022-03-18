package com.study.springmvc.lab.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Fund {
	private Integer fid;
	private String fname;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createtime;
	
	private List<Fundstock> fundstocks;	

	public Fund() {}

	public Fund(Integer fid, String fname, Date createtime, List<Fundstock> fundstocks) {
		super();
		this.fid = fid;
		this.fname = fname;
		this.createtime = createtime;
		this.fundstocks = fundstocks;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public List<Fundstock> getFundstocks() {
		return fundstocks;
	}

	public void setFundstocks(List<Fundstock> fundstocks) {
		this.fundstocks = fundstocks;
	}

	@Override
	public String toString() {
		return "Fund [fid=" + fid + ", fname=" + fname + ", createtime=" + createtime + ", fundstocks=" + fundstocks
				+ "]";
	}	
}