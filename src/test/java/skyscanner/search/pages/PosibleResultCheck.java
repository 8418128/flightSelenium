package skyscanner.search.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import skyscanner.search.generic.AbstractPage;

public class PosibleResultCheck extends AbstractPage{
	
	private static final String RESULTS_ELEMENT = "#header-list-count > div > span > a";
	private static final String HOURS_GO = ".LegInfo__leg-depart-3GMlb";
	private static final String HOURS_BACK = ".LegInfo__leg-arrive-1spXx leg";
	private static final String CLOSE_ALERT = ".bpk-close-button-3piCr";
	
	private List<WebElement> hours_depart;
	private List<WebElement> hours_arrival;
	
	public PosibleResultCheck(WebDriver driver,int price) {
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
		}catch(Exception e) {
			return false;
		}
		return true;		
	}
	
	
	
}
