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


public class SearchWeekends implements IDateSearcher {
	
	ProcessCalendarDates pc;
	Parameters p;
	public SearchWeekends (ProcessCalendarDates pc,Parameters p) {
		this.pc = pc;
		this.p=p;
	}
	
	public void calculateFromTo(Date day) {
		List<Date[]> list = new ArrayList<Date[]>();
		int realGap = p.getGap()-2;
		Date from = CalendarUtils.addDays(day, -realGap).getTime();
		Calendar c = CalendarUtils.getCalendar(from);
		for(int i=0;i<=realGap;i++) {
			Calendar toC = CalendarUtils.addDays(from, p.getGap());
			Date[] dates = new Date[2];
			dates[0]=from;
			dates[1]=toC.getTime();
			from=CalendarUtils.addDays(from, 1).getTime();
			list.add(dates);
			pc.selectDates(dates[0], dates[1], p.getTreshold());
		}
		
	}

	public void search() {		
		pc.checkOnlyDirectFlights();	
		Date from = CalendarUtils.getCalendar(p.getDate()).getTime();//CalendarUtils.getFirstDayOfMonth(p.getDate());
		Date to = CalendarUtils.getCalendar(p.getDateEnd()).getTime();//CalendarUtils.getLastDayOfMonth(p.getDateEnd());
		List<Date> fridays = CalendarUtils.getDayOfWeekList(Calendar.FRIDAY,from,to);
		for(Date day : fridays) {
			calculateFromTo(day);
		}
	}
	
	
	
	
	
	
	
	




	

}
