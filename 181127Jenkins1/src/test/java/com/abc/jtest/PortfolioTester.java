package com.abc.jtest;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.abc.stocks.Portfolio;
import com.abc.stocks.Stock;
import com.abc.stocks.StockService;

public class PortfolioTester {
	Portfolio portfolio;
	StockService stockService;
	
	public static void main(String[] args) {
		PortfolioTester tester = new PortfolioTester();
		tester.setUp();
		System.out.println(tester.testMarketValue() ? "pass" : "fail");
	}

	private boolean testMarketValue() {
		List<Stock> stocks = new ArrayList<Stock>();
		
		Stock googleStock = new Stock("1", "Google", 10);
		Stock microsoftStock = new Stock("2", "Mocrosoft", 100);
		
		stocks.add(googleStock);
		stocks.add(microsoftStock);
		
		portfolio.setStocks(stocks);
		
		//mock the behavior of stocj servce to return the value of
		//various stocks
		when(stockService.getPrice(googleStock)).thenReturn(50.00);
		when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);
		
		double marketValue = portfolio.getMarketValue();
		return marketValue == 100500.0;
	}

	private void setUp() {
		portfolio = new Portfolio();
		
		stockService = mock(StockService.class);
		
		portfolio.setStockService(stockService);
	}
}
