package skyscanner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import skyscanner.dao.Result;
import util.CalendarUtils;

public class Parameters {
	
	private String date;
	private String specificGo;
	private String specificBack;
	private String dateEnd;
	
	private int gap;
	private int maxCities;
	private int maxCountries;
	private int treshold;
	
	private boolean exactDates = false;
	
	public final static String reportFile = "C:\\Users\\pepinaco\\Documents\\SeleniumReport\\";
	
	private List<String> whiteListCountries;
	private List<String> whiteListCities;	
	private List<String> blackListCountries;	
	private List<String> blackListCities;	
	
	private List<String> flightFrom;
	private List<Result> results;	
	
	private static final String MONTH_FORMAT = "yyyy-MM";
	private static final String EXACT_DATE_FORMAT = "yy-MM-dd";
	
	public Parameters() {
		initLists();
	}
	
	private void initLists() {
		this.whiteListCountries = new ArrayList<String>();
		this.whiteListCities = new ArrayList<String>();	
		this.blackListCountries = new ArrayList<String>();	
		this.blackListCities = new ArrayList<String>();
		this.flightFrom = new ArrayList<String>();
		this.results = new ArrayList<Result>();
	}
	
	public String getUrl(String flightFrom) {
		String url = null;
		if(exactDates) {
			String dateGo = getDateFormatForMonthUrl(this.date,EXACT_DATE_FORMAT);
			String dateBack = getDateFormatForMonthUrl(this.dateEnd,EXACT_DATE_FORMAT);
			url = "https://www.skyscanner.es/transport/vuelos-desde/"+flightFrom+"/"+dateGo+"/"+dateBack+"/?adults=1&children=0&adultsv2=1&childrenv2=&infants=0&cabinclass=economy&rtn=1&preferdirects=true&outboundaltsenabled=false&inboundaltsenabled=false&ref=home";
		}else {
			String date = getDateFormatForMonthUrl(this.date,MONTH_FORMAT);
			url = "https://www.skyscanner.es/transporte/vuelos-desde/"+flightFrom+"/?adults=1&children=0&adultsv2=1&childrenv2=&infants=0&cabinclass=economy&rtn=1&preferdirects=true&outboundaltsenabled=false&inboundaltsenabled=false&oym="+date+"&iym="+date+"&ref=home";
		}
		return url;
	}
	
	private String getDateFormatForMonthUrl(String d,String format) {
		return CalendarUtils.formatDate(CalendarUtils.getCalendar(d).getTime(), format).replace("-", "");
	}
	
	public boolean checkCityFilter(String city) {
		return !getBlackListCities().contains(city) && (getWhiteListCities().size()==0 || getWhiteListCities().contains(city));
	}
	
	public boolean checkCountryFilter(String country) {
		return !getBlackListCountries().contains(country) && (getWhiteListCountries().size()==0 || getWhiteListCountries().contains(country));
	}
		
	public void addToFlightFrom(String s){
		this.flightFrom.add(s);
	}
	
	public void addToBlackListCountries(String s){
		this.blackListCountries.add(s);
	}
	public void addToWhiteListCountries(String s){
		this.whiteListCountries.add(s);
	}
	public void addToBlackListCities(String s){
		this.blackListCities.add(s);
	}
	public void addToWhiteListCities(String s){
		this.whiteListCities.add(s);
	}
	
	
	
	public boolean isExactDates() {
		return exactDates;
	}

	public void setExactDates(boolean exactDates) {
		this.exactDates = exactDates;
	}

	public String getReportfile() {
		return reportFile;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void addResult(Result r) {
		results.add(r);
	}
	
	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public int getMaxCities() {
		return maxCities;
	}
	public void setMaxCities(int maxCities) {
		this.maxCities = maxCities;
	}
	public int getTreshold() {
		return treshold;
	}
	public String getDate() {
		return date;
	}
	public int getGap() {
		return gap;
	}	

	public List<String> getFlightFrom() {
		return flightFrom;
	}

	public void setFlightFrom(List<String> flightFrom) {
		this.flightFrom = flightFrom;
	}

	public String getSpecificGo() {
		return specificGo;
	}

	public void setSpecificGo(String specificGo) {
		this.specificGo = specificGo;
	}

	public String getSpecificBack() {
		return specificBack;
	}

	public void setSpecificBack(String specificBack) {
		this.specificBack = specificBack;
	}

	public void setTreshold(int treshold) {
		this.treshold = treshold;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}

	public int getMaxCountries() {
		return maxCountries;
	}

	public void setMaxCountries(int maxCountries) {
		this.maxCountries = maxCountries;
	}

	public List<String> getWhiteListCountries() {
		return whiteListCountries;
	}

	public void setWhiteListCountries(List<String> whiteListCountries) {
		this.whiteListCountries = whiteListCountries;
	}

	public List<String> getWhiteListCities() {
		return whiteListCities;
	}

	public void setWhiteListCities(List<String> whiteListCities) {
		this.whiteListCities = whiteListCities;
	}

	public List<String> getBlackListCountries() {
		return blackListCountries;
	}

	public void setBlackListCountries(List<String> blackListCountries) {
		this.blackListCountries = blackListCountries;
	}

	public List<String> getBlackListCities() {
		return blackListCities;
	}

	public void setBlackListCities(List<String> blackListCities) {
		this.blackListCities = blackListCities;
	}
	
	
	
	
	
}
