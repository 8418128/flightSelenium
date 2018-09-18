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


public class SearchWithinDay implements IDateSearcher{
	private ProcessCalendarDates pc;
	private int gap;
	private int iterations;
	private Date day;
	public SearchWithinDay ( WebDriver driver,int gap,int iterations,Date day,ProcessCalendarDates pc) {
		this.gap=gap;
		this.day=day;
		this.iterations=iterations;
	}



	public void search() {
		Calendar dateWithin = CalendarUtils.getCalendar(day);
		Calendar firstDate = CalendarUtils.addDays(dateWithin, -(gap));
		Date nextDate = firstDate.getTime();
		for(int i=0;i<=iterations;i++) {			
			nextDate = pc.processGap(nextDate);
		}
	}
	
	
	




	

}
