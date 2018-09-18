package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CalendarUtils {
	
	public static Calendar getCalendar(String dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	public static Calendar addDays(Calendar c,int days) {
		Calendar aux = c.getInstance();
		aux.setTime(c.getTime());
		aux.add(Calendar.DAY_OF_WEEK, days);
		return aux;
	}
	
	
	
	public static Calendar addDays(Date d,int days) {
		Calendar aux = Calendar.getInstance(); 
		aux.setTime(d);
		aux.add(Calendar.DAY_OF_WEEK, days);
		return aux;
	}
	
	public static Calendar addMinutes(Date d,int minutes) {
		Calendar aux = Calendar.getInstance(); 
		aux.setTime(d);
		aux.add(Calendar.MINUTE, minutes);
		return aux;
	}
	
	public static Calendar subtractMonth(Date d) {
		Calendar aux = getCalendar(d);
		aux.add(Calendar.MONTH, -1);
		return aux;
	}
	
	public static Calendar getCalendar(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}
	
	public static int[] getCalIndex(Date d) {
		Calendar c = getCalendar(d);
		System.out.print("GETTING INDEX FROM: "+c.getTime());
		int dayOfWeek = getDayIndex(c.get(Calendar.DAY_OF_WEEK));
		int[] calIndex = new int[2];
		calIndex[0]=getWeekIndex(d);
		calIndex[1]=dayOfWeek;
		System.out.println(calIndex[0]+":"+calIndex[1]);
		return calIndex;
	}
	
	public static int getWeekIndex(Date d){
		Calendar c = subtractMonth(d);
		int lastDate = c.getActualMaximum(Calendar.DAY_OF_MONTH);		
	    c.set(Calendar.DATE, lastDate);
	    int lastDay = getDayIndex(c.get(Calendar.DAY_OF_WEEK));
	    if(lastDay==7) {
	    	lastDay=0;
		}
	    Calendar aux = getCalendar(d);
	    int daysToAdd = aux.get(Calendar.DAY_OF_MONTH);
	    double calc = (lastDay+daysToAdd)/7.;
	    int result = (int) calc;
	    double rest = calc-Math.floor(calc);
	    if(rest>0) {
	    	result++;
	    }
		return result;
	}
	
	public static int getDayIndex(int index) {
		int ret = 0;
		switch(index) {
		case Calendar.MONDAY: ret = 1;
			break;
		case Calendar.TUESDAY: ret = 2;
			break;
		case Calendar.WEDNESDAY: ret = 3;
			break;
		case Calendar.THURSDAY: ret = 4;
			break;
		case Calendar.FRIDAY: ret = 5;
			break;
		case Calendar.SATURDAY: ret = 6;
			break;
		case Calendar.SUNDAY: ret = 7;
			break;
		}
		return ret;
	}
	
	
	public static String formatDate(Date d,String formatt) {
		SimpleDateFormat format = new SimpleDateFormat(formatt);
		return format.format(d);
	}
	
	public static String formatDate(Date d) {
		return formatDate(d,"dd/MM/yyyy");
	}
	
	public static String formatTime(Date d) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh-mm");
		return format.format(d);
	}

	public static Date getFirstDayOfMonth(String month) {
		Calendar fromC = CalendarUtils.getCalendar(month);
		fromC.set(Calendar.DAY_OF_MONTH,fromC.getActualMinimum(Calendar.DAY_OF_MONTH));
		return fromC.getTime();
	}
	
	public static Date getLastDayOfMonth(String month) {
		Calendar fromC = CalendarUtils.getCalendar(month);
		fromC.set(Calendar.DAY_OF_MONTH,fromC.getActualMaximum(Calendar.DAY_OF_MONTH));
		return fromC.getTime();
	}
	
	public static List<Date> getDayOfWeekList(int dayOfWeek,Date firstDate,Date lastDate){
		List<Date> days = new ArrayList<Date>();
		Calendar nextDate = getCalendar(firstDate);
		Date lastDatePlusSencond;
		lastDatePlusSencond = addMinutes(lastDate, 1).getTime();
		boolean out = false;
		while(nextDate.getTime().before(lastDatePlusSencond)) {			
			if(nextDate.get(Calendar.DAY_OF_WEEK)==dayOfWeek) {
				days.add(nextDate.getTime());
			}
			nextDate.add(Calendar.DAY_OF_WEEK, 1);
		}
		return days;
	}

}
