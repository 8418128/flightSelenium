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
		/*try {
			driver.get("https://www.skyscanner.es/transporte/vuelos/svq/wars/181127/181204/?adultsv2=1&childrenv2=&cabinclass=economy&rtn=1&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false&qp_prevProvider=ins_month&qp_prevCurrency=EUR&qp_prevPrice=61&priceSourceId=taps-taps&priceTrace=201809162027*D*SVQ*WMI*20181127*ryan*FR%7C201809162027*D*WMI*SVQ*20181204*ryan*FR#results");
			
			PosibleResultCheck p = new PosibleResultCheck(driver, 1);
			if(p.isReady()) {
				p.getFromArrival();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		searcher = new SkyScannerSearch(driver,sg,p);
		for(String from : p.getFlightFrom()) {
			driver.get(getUrl(from));
			searcher.doWork();
		}
	}
	
	public String getUrl(String flightFrom) {
		String go = CalendarUtils.formatDate(CalendarUtils.getCalendar(p.getDate()).getTime(), "yyyy-MM").replace("-", "");
		String back = go;
		return "https://www.skyscanner.es/transporte/vuelos-desde/"+flightFrom+"/?adults=1&children=0&adultsv2=1&childrenv2=&infants=0&cabinclass=economy&rtn=1&preferdirects=true&outboundaltsenabled=false&inboundaltsenabled=false&oym="+go+"&iym="+back+"&ref=home";
	}
	
	

	

}
