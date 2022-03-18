package com.study.springmvc.lab.validator;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.study.springmvc.lab.entity.Fundstock;

import yahoofinance.YahooFinance;

@Component
public class FundstockValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// ���
		return Fundstock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Fundstock fundstock=(Fundstock)target;
		//���祿
		ValidationUtils.rejectIfEmpty(errors, "sid", "stock.sid.empty");
		ValidationUtils.rejectIfEmpty(errors, "fid", "stock.fid.empty");
		ValidationUtils.rejectIfEmpty(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "share", "stock.amount.empty");
		//�i�����->�ϥ�https://financequotes-api.com/ api->pom.xml�̿�
		yahoofinance.Stock yStock=null;
		try {
			yStock=YahooFinance.get(fundstock.getSymbol());
			int amount=fundstock.getShare();
			//�R�i�ƶq�����j��1000
			if(amount<1000) {
				errors.rejectValue("share", "stock.amount.notenough");
			}
			//�R�i���ƶq�����O1000�����q
			if(amount % 1000 != 0) {
				errors.rejectValue("share", "stock.amount.range");
			}
		} catch (IOException e) {			
			e.printStackTrace();
			if(yStock==null) {
				errors.rejectValue("symbol", "stock.symbol.notfound");
			}
		}
	}

}
