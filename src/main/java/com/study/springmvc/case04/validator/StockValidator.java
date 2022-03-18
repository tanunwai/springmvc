package com.study.springmvc.case04.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.study.springmvc.case04.entity.Stock;

import yahoofinance.YahooFinance;

@Component
public class StockValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// ���		
		return Stock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Stock stock=(Stock) target;
		//��¦�祿->Stock entity & errorMessage.properties
		ValidationUtils.rejectIfEmpty(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "stock.price.empty");
		ValidationUtils.rejectIfEmpty(errors, "amount", "stock.amount.empty");
		//�i�����->�ϥ�https://financequotes-api.com/ api->pom.xml�̿�
		yahoofinance.Stock yStock=null;
		try {
			yStock=YahooFinance.get(stock.getSymbol());
			//�@��->�W�@�Ӧ��L��
			double previousClose=yStock.getQuote().getPreviousClose().doubleValue();
			//�n�祿�����
			double price=stock.getPrice();
			int amount=stock.getAmount();
			//1.�R�i�����楲���O�Q�Ѧ��L������10%����
			if(price < previousClose*0.9 || price > previousClose*1.1) {
				/*errors.rejectValue("price", "stock.price.range");*/
				//default message
				errors.rejectValue("price", "stock.price.range", 
											new Object[] {(previousClose*0.9), 
													(previousClose*1.1)}, "�R�i�����楲���O�Q�Ѧ��L������10%����");
				//�i���Τ�ӪѪ��@���P�ثe������
				double currentPrice=yStock.getQuote().getPrice().doubleValue();
				errors.reject("price_info", String.format("�@��: %.2f\n �̷s�����: %.2f", previousClose, currentPrice));
			}
			//2.�R�i���ƶq����>=1000
			if(amount<1000) {
				errors.rejectValue("amount", "stock.amount.notenough");
			}
			//3.�R�i���Ѽƥ����O1000������(1000��=1�i
			if(amount % 1000 != 0) {
				errors.rejectValue("amount", "stock.amount.range");
			}
		}catch(Exception e) {
			e.printStackTrace();
			if(yStock==null) {
				//rejectValue->error code->*.properties->�ۭq���~�T��
				errors.rejectValue("symbol", "stock.symbol.notfound");
			}
		}
	}
}