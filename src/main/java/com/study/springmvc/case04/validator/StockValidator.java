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
		// 驗証		
		return Stock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Stock stock=(Stock) target;
		//基礎驗正->Stock entity & errorMessage.properties
		ValidationUtils.rejectIfEmpty(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "stock.price.empty");
		ValidationUtils.rejectIfEmpty(errors, "amount", "stock.amount.empty");
		//進階驗証->使用https://financequotes-api.com/ api->pom.xml依賴
		yahoofinance.Stock yStock=null;
		try {
			yStock=YahooFinance.get(stock.getSymbol());
			//作收->上一個收盤價
			double previousClose=yStock.getQuote().getPreviousClose().doubleValue();
			//要驗正的欄位
			double price=stock.getPrice();
			int amount=stock.getAmount();
			//1.買進的價格必須是昨天收盤價的±10%之間
			if(price < previousClose*0.9 || price > previousClose*1.1) {
				/*errors.rejectValue("price", "stock.price.range");*/
				//default message
				errors.rejectValue("price", "stock.price.range", 
											new Object[] {(previousClose*0.9), 
													(previousClose*1.1)}, "買進的價格必須是昨天收盤價的±10%之間");
				//告知用戶該股的作收與目前的價格
				double currentPrice=yStock.getQuote().getPrice().doubleValue();
				errors.reject("price_info", String.format("作收: %.2f\n 最新成交價: %.2f", previousClose, currentPrice));
			}
			//2.買進的數量必須>=1000
			if(amount<1000) {
				errors.rejectValue("amount", "stock.amount.notenough");
			}
			//3.買進的股數必須是1000的倍數(1000股=1張
			if(amount % 1000 != 0) {
				errors.rejectValue("amount", "stock.amount.range");
			}
		}catch(Exception e) {
			e.printStackTrace();
			if(yStock==null) {
				//rejectValue->error code->*.properties->自訂錯誤訊息
				errors.rejectValue("symbol", "stock.symbol.notfound");
			}
		}
	}
}