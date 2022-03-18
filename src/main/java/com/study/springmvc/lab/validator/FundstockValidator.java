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
		// 驗証
		return Fundstock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Fundstock fundstock=(Fundstock)target;
		//基本驗正
		ValidationUtils.rejectIfEmpty(errors, "sid", "stock.sid.empty");
		ValidationUtils.rejectIfEmpty(errors, "fid", "stock.fid.empty");
		ValidationUtils.rejectIfEmpty(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "share", "stock.amount.empty");
		//進階驗証->使用https://financequotes-api.com/ api->pom.xml依賴
		yahoofinance.Stock yStock=null;
		try {
			yStock=YahooFinance.get(fundstock.getSymbol());
			int amount=fundstock.getShare();
			//買進數量必須大於1000
			if(amount<1000) {
				errors.rejectValue("share", "stock.amount.notenough");
			}
			//買進的數量必須是1000的倍量
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
