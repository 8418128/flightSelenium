package skyscanner.search.pages;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import skyscanner.PageConstants;
import skyscanner.dao.Result;
import skyscanner.search.generic.AbstractPage;
import util.CalendarUtils;

public class PosibleResultCheck extends AbstractPage{
	
	private static final String RESULTS_ELEMENT = "#header-list-count > div > span > a";
	private static final String HOURS_GO = ".LegInfo__leg-depart-3GMlb";
	private static final String HOURS_BACK = ".LegInfo__leg-arrive-1spXx leg";
	private static final String CLOSE_ALERT = "#price-alerts-modal button";
	private static final String PRICES = ".CTASection__price-2bc7h.price";
	private static final String URLS = ".bpk-button CTASection__cta-button-JozPr";
	
	private List<WebElement> hours_depart;
	private List<WebElement> hours_arrival;
	private List<WebElement> prices;
	
	public PosibleResultCheck(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String getDepart(int index) {
		return hours_depart.get(index).getText();
	}
	public String getArrival(int index) {
		return hours_arrival.get(index).getText();
	}
	
	public String getFromDepart() {
		return getDepart(0).split("\\n")[0];
	}
	public String getFromArrival() {
		return getArrival(0).split("\\n")[0];
	}
	public String getToDepart() {
		return getDepart(1).split("\\n")[0];
	}
	public String getToArrival() {
		return getArrival(1).split("\\n")[0];
	}
	
	public int getPrice(int i) {
		try {
			return getIntegers(prices.get(i).getText());
		}catch(Exception e) {
			System.out.println("Error getting price");
			return -1;
		}
		
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	
	@Override
	public boolean isReady() {
		try {
			this.findDynamicElementByCss(RESULTS_ELEMENT, 50);
			try {
				this.findDynamicElementByCss(CLOSE_ALERT,5).click();
			}catch(Exception e) {
				
			}
			hours_depart = this.findDynamicElementsByCss(HOURS_GO);
			hours_arrival = this.findDynamicElementsByCss(HOURS_GO);
			prices = this.findDynamicElementsByCss(PRICES);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;		
	}
	
	public Result getResult(Date go,Date back) {
		String[] fromto = this.findDynamicElementByCss(PageConstants.FROM_TO).getText().split("-");
		String cityFrom = fromto[0].split("\\n")[0].trim();
		String cityGo = fromto[1].trim();
		String format = "dd/MM/yyyy";
		String datego = CalendarUtils.formatDate(go, format);
		String dateback = CalendarUtils.formatDate(back, format);
		Result r = new Result(cityFrom, cityGo, getPrice(0)+"", datego, dateback, getUrl());
		r.setHourgo(getFromDepart());
		r.setHourback(getToDepart());
		return r;
		
	}
	
	
	
}
