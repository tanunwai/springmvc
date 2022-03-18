package com.study.springmvc.lab.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@RestController
@RequestMapping(path = { "/lab/price" })
public class PriceController {

		// 範例：symbol = ^TWII、2330.TW->配合.+語法
		@GetMapping(path = { "/histquotes/{symbol:.+}" })
		public List<HistoricalQuote> queryHistoricalQuote(@PathVariable(value = "symbol") String symbol) {
			Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			from.add(Calendar.MONTH, -1);// 過去一年的資料
			List<HistoricalQuote> googleHistQuotes = null;
			try {
				Stock google = YahooFinance.get(symbol);
				googleHistQuotes = google.getHistory(from, to, Interval.DAILY);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return googleHistQuotes;
		}
}