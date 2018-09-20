package test;

import org.testng.annotations.BeforeTest;

import skyscanner.Parameters;
import skyscanner.search.pages.IDateSearcher;
import skyscanner.search.pages.ProcessCalendarDates;
import skyscanner.search.pages.SearchWeekends;

public class WeekendTest extends SkyScannerTest{
	
	@Override
	public Parameters getParameters() {
		int maxCities = 5;
		int maxCountries = 8;
		int treshold = 150;
		String date = "2018-10-01";
		String dateEnd = "2019-02-30";
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
		//p.addToBlackListCountries("España");
		p.addToWhiteListCities("París");
		p.addToWhiteListCountries("Francia");
		return p;
	}

	@Override
	public IDateSearcher getSearcher(Parameters p) {
		ProcessCalendarDates pc = new ProcessCalendarDates(driver,p);
		return new SearchWeekends(pc,p);
	}

	
	
}
