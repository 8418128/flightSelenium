package skyscanner.search.pages;

import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import skyscanner.Parameters;
import skyscanner.search.generic.AbstractPage;
import util.CalendarUtils;


public class SearchGapInRange implements IDateSearcher{

	ProcessCalendarDates pc;
	Parameters p;
	public SearchGapInRange (ProcessCalendarDates pc,Parameters p) {
		this.p=p;
		this.pc = pc;
	}


	public void search() {
		pc.checkOnlyDirectFlights();
		Date nextDate = CalendarUtils.getFirstDayOfMonth(p.getDate());
		Date lastDate = CalendarUtils.getCalendar(p.getDateEnd()).getTime();
		
		String url = pc.driver.getCurrentUrl();
		while(nextDate!=null && nextDate.before(lastDate)) {
			nextDate = pc.processGap(nextDate);
		}
	}
	
	
	
	
	
	




	

}
