package skyscanner.search.pages;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import skyscanner.Parameters;
import skyscanner.dao.Result;
import skyscanner.search.generic.AbstractPage;
import util.CalendarUtils;

public class ProcessCalendarDates extends AbstractPage{
	
	private static final String MONTH_FROM = "#outbound__bpk_calendar_nav_select";
	private static final String MONTH_TO = "#inbound__bpk_calendar_nav_select";	
	protected static final String ONLY_DIRECT = ".bpk-checkbox__input-270PP";
	protected static final String NEXT_MONTH = "html.bpk-no-touch-support body div#pagewrap div#app-root div.App-TPVhj div.App__contentWrapper-2s3zB div div.month-view div div.month-view__card div.month-view__tabbed-panels div div div.clearfix.month-view__calendar-area div div.month-view-calendar.inbound-calendar div.bpk-calendar-2LUwd div.month-selector.clearfix div.month-selector__nav-wrapper div.bpk-calendar-nav-2l_9b div.bpk-calendar-nav__nudger-1VP-5 button#inbound__bpk_calendar_nav_month_nudger_next.bpk-calendar-nav__button-3Bcsr";
	protected static final String PRICE = "#app-root > div > div > div:nth-child(2) > div > div:nth-child(2) > div.month-view__card > div.month-view__trip-summary > div.month-view__trip-summary-selection--with-price > div > div.month-view__trip-summary-price-display";
	protected static final String DAY_GO = "#app-root > div > div > div:nth-child(2) > div > div:nth-child(2) > div.month-view__card > div.month-view__tabbed-panels > div:nth-child(2) > div > div > div > div.month-view-calendar.outbound-calendar > div > div.month-view-grid-wrapper > table > tbody > tr:nth-child("+REPLACE_TAG1+") > td:nth-child("+REPLACE_TAG2+") > button";
	protected static final String DAY_BACK = "#app-root > div > div > div:nth-child(2) > div > div:nth-child(2) > div.month-view__card > div.month-view__tabbed-panels > div:nth-child(2) > div > div > div > div.month-view-calendar.inbound-calendar > div > div.month-view-grid-wrapper > table > tbody > tr:nth-child("+REPLACE_TAG1+") > td:nth-child("+REPLACE_TAG2+") > button";
	protected static final String SEE_FLIGHT = "#app-root > div > div > div:nth-child(2) > div > div:nth-child(2) > div.month-view__card > div.month-view__trip-summary > div.month-view__trip-summary-selection--with-price > a > span:nth-child(1)";
	protected static final String COMPARE_PRICES = "a.bpk-button-zZ0K5 > span:nth-child(1)";
	protected static final String COMPARE_PRICES_A = "a.bpk-button-zZ0K5";
	protected static final String FROM_TO = "#js-search-summary-bar > div > div > h2";
	
	protected static final String PRICE_CHECK = "#app-root > div > div > div:nth-child(2) > div > div:nth-child(2) > div.month-view__card > div.month-view__tabbed-panels > div:nth-child(2) > div > div > div > div.month-view-calendar.outbound-calendar > div > div.month-view-grid-wrapper > table > tbody > tr:nth-child("+REPLACE_TAG1+") > td:nth-child("+REPLACE_TAG1+") > button > div.price";
	protected static final String PRICE_CHECK2 = "#app-root > div > div > div:nth-child(2) > div > div:nth-child(2) > div.month-view__card > div.month-view__tabbed-panels > div:nth-child(2) > div > div > div > div.month-view-calendar.inbound-calendar > div > div.month-view-grid-wrapper > table > tbody > tr:nth-child("+REPLACE_TAG1+") > td:nth-child("+REPLACE_TAG1+") > button > div.price";
	Parameters p;
	
	public ProcessCalendarDates(WebDriver driver,Parameters p) {
		super(driver);
		this.p=p;
	}

	public Date processGap(Date date) {
		Date ret = null;
		Calendar aux = CalendarUtils.getCalendar(date);
		Calendar backC = CalendarUtils.addDays(aux, p.getGap());
		selectDates(aux.getTime(),backC.getTime(),p.getTreshold());
		aux = CalendarUtils.addDays(aux, 1);
		ret = aux.getTime();
		return ret;
	}
	
	public void selectDates(Date from,Date to,int treshold) {
		int month1 = CalendarUtils.getCalendar(from).get(Calendar.MONTH);
		int month2 = CalendarUtils.getCalendar(to).get(Calendar.MONTH);
		
		int[] go = CalendarUtils.getCalIndex(from);
		int[] back = CalendarUtils.getCalIndex(to);
		
		checkDateInCalendar(from, true,0);
		checkDateInCalendar(to, false,0);
		boolean check = selectDatesAndCheck(go, back, treshold);
		if(check) {
			PosibleResultCheck prc = new PosibleResultCheck(driver);
			String backUrl = driver.getCurrentUrl();
			String url = this.findDynamicElementByCss(COMPARE_PRICES_A).getAttribute("href");
			driver.get(url);
			if(prc.isReady()) {	
				p.addResult(prc.getResult(from, to));
			}
			driver.get(backUrl);
		}
	}
	
	
	protected void checkDateInCalendar(Date date,boolean from,int times) {
		String selector = from?MONTH_FROM:MONTH_TO;
		try {
			Select select = new Select(this.findDynamicElementByCss(selector));
			WebElement option = select.getFirstSelectedOption();		
			String monthValue = option.getAttribute("value");
			String monthFromDate = CalendarUtils.formatDate(date,"yyyy-MM");
			if(monthValue!=monthFromDate) {
				select.selectByValue(monthFromDate);
			}
		}catch(Exception e) {
			if(times<3) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("ESPERANDO checkDateInCalendar");
				checkDateInCalendar(date, from,times++);
			}
			
		}
		
	}
	
	public void checkOnlyDirectFlights() {
		WebElement cb = this.findDynamicElementByCss(ONLY_DIRECT);
		if(!cb.isSelected()) {
			cb.click();
		}
	}


	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean selectDatesAndCheck(int[] go,int[] back,int treshold) {
		try {
			boolean sel1 = select(go,true);
			boolean sel2 = select(back,false);
			if(sel1 && sel2) {
				try {
					return checkPriceForTreshold(treshold);
				}catch(ElementClickInterceptedException e) {
					acceptCookies();
					return  checkPriceForTreshold(treshold);
				}
			}
		}catch(Exception e) {
			
		}	
		return false;
	}
	
	public boolean checkPriceForTreshold(int treshold) {
		String text = this.findDynamicElementByCss(COMPARE_PRICES).getText();
		if(text.contains("Mostrar")) {
			if(checkPrice(treshold, PRICE)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean select(int[] indexes,boolean depart) {
		String aux = depart?DAY_GO:DAY_BACK;
		aux = normalizeIndex(REPLACE_TAG1, aux, indexes[0]);		
		aux = normalizeIndex(REPLACE_TAG2, aux, indexes[1]);
		WebElement button = this.findDynamicElementByCss(aux,5);
		if(button.getAttribute("class").contains("--blocked")) {
			return false;
		}
		button.click();
		return true;
	}
		
}
