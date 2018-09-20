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

	@Override
	public Parameters getParameters() {
		// TODO Auto-generated method stub
		int maxCities = 5;
		int maxCountries = 10;
		int treshold = 70;
		String date = "2018-11-18";
		String dateEnd = "2018-12-19";
		int gap = 4;	
		
		Parameters p = new Parameters();
		p.setMaxCountries(maxCountries);
		p.setMaxCities(maxCities);
		p.setTreshold(treshold);
		p.setDate(date);
		p.setGap(gap);
		p.setDateEnd(dateEnd);
		
		p.addToFlightFrom("svq");
		//p.addToFlightFrom("agp");
		p.addToBlackListCountries("España");
		p.addToBlackListCountries("Marruecos");
		return p;
	}

	@Override
	public IDateSearcher getSearcher(Parameters p) {
		ProcessCalendarDates pc = new ProcessCalendarDates(driver,p);
		return new SearchGapInRange(pc,p);
	}

	
	
}
