package com.study.springmvc.case04.entity;

public class Stock {
	private String symbol; // �Ѳ��N���G�Ѳ��N�������Ѳ��N���n�s�b�B���i����
	private Double price; // �R�i����G�R�i���楲���O�Q�馬�L������10%����
	private Integer amount; // �R�i�ѼơG�R�i�Ѽƥ����O1000������(1000�� = 1�i)
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", price=" + price + ", amount=" + amount + "]";
	}	
}