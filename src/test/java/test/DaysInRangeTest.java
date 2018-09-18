package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeTest;

import skyscanner.Parameters;
import skyscanner.dao.Result;
import skyscanner.search.pages.IDateSearcher;
import skyscanner.search.pages.ProcessCalendarDates;
import skyscanner.search.pages.SearchGapInRange;
import skyscanner.search.pages.SearchWeekends;
import util.ExcelWriter;

public class DaysInRangeTest extends SkyScannerTest{
	
	@BeforeTest	
	public void initSearcher() {
		
		int maxCities = 5;
		int maxCountries = 10;
		int treshold = 50;
		String date = "2018-10-01";
		String dateEnd = "2018-11-30";
		int gap = 7;	
		
		Parameters p = new Parameters();
		p.setMaxCountries(maxCountries);
		p.setMaxCities(maxCities);
		p.setTreshold(treshold);
		p.setDate(date);
		p.setGap(gap);
		p.setDateEnd(dateEnd);
		
		p.addToFlightFrom("svq");
		p.addToFlightFrom("agp");
		p.addToBlackListCountries("España");
		
		
		ProcessCalendarDates pc = new ProcessCalendarDates(driver,p);
		super.p=p;
		super.sg = new SearchGapInRange(pc,p);
		
		
	}

	
	
}
