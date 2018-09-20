package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.minidev.json.JSONArray;
import skyscanner.Parameters;
import skyscanner.search.generic.SkyScannerSearch;
import skyscanner.search.pages.IDateSearcher;
import skyscanner.search.pages.PosibleResultCheck;
import skyscanner.search.pages.ProcessCalendarDates;
import skyscanner.search.pages.SearchWeekends;
import util.CalendarUtils;
import util.JsonHelper;


public abstract class SkyScannerTest extends DefaultTest {

	private SkyScannerSearch searcher;
	protected IDateSearcher sg;
	protected Parameters p;
	
	public abstract Parameters getParameters();
	public abstract IDateSearcher getSearcher(Parameters p);
	
	@BeforeTest
	public void init() {
		p = getParameters();
		sg = getSearcher(p);
	}
	
	@AfterTest
	public void generateReport() {
		try {
			searcher.generateReport();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void performTest () {
		searcher = new SkyScannerSearch(driver,sg,p);
		for(String from : p.getFlightFrom()) {
			driver.get(p.getUrl(from));
			searcher.doWork();
		}
	}
	
	
	

	

}
