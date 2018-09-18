package skyscanner.search.generic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import skyscanner.Parameters;
import skyscanner.search.pages.IDateSearcher;
import util.ExcelWriter;

public class SkyScannerSearch extends AbstractPage{
	
	protected static String COUNTRY_LIST = ".browse-list-category";
	protected String COUNTRY_LIST_R = "li.browse-list-category:nth-child("+REPLACE_TAG1+") > a:nth-child(1)";	
	protected static String LINK = "li.browse-list-category:nth-child("+REPLACE_TAG1+") > ul:nth-child(2) > li:nth-child("+REPLACE_TAG2+") > a:nth-child(1)";	
	protected static String ONLY_DIRECT = "#filter-direct-stops-input";
	private Parameters p;
	protected IDateSearcher ri;
	public SkyScannerSearch(WebDriver driver ,IDateSearcher ri,Parameters p) {
		super(driver);
		this.ri = ri;
		this.p=p;;
		// TODO Auto-generated constructor stub
	}

	public void doWork() {
		checkOnlyDirectFlights();
		List<String> result = iterateCountriesAndCities();
		iterateCitiesUrl(result);
	}
	
	public void checkOnlyDirectFlights() {
		WebElement cb = this.findDynamicElementByCss(ONLY_DIRECT);
		if(!cb.isSelected()) {
			try {
				cb.click();
			}catch(ElementClickInterceptedException e) {
				acceptCookies();
				cb.click();
			}
			
		}
	}
	
	public void generateReport() {
		try {
			ExcelWriter.generateExcel(p.getResults(), p.getReportfile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String> iterateCountriesAndCities() {
		int resultCount = 0;
		List<String> result = new ArrayList<String>();
		int countryIndex = 1;
		List<WebElement> list = this.findDynamicElementsByCss(COUNTRY_LIST);
		for(WebElement we : list) {
			if(resultCount<p.getMaxCountries()) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String[] split = we.getText().split("\\n");				
				String country = split[0];
				try {
					int price = getIntegers(split[1]);
					if(p.checkCountryFilter(country) && price<=p.getTreshold()) {
						try {
							we.click();
						}catch(ElementClickInterceptedException e) {
							acceptCookies();
							we.click();
						}
						result.addAll(getCitiesList(countryIndex));
						WebElement c = this.findDynamicElementByCss(normalizeIndex(REPLACE_TAG1, COUNTRY_LIST_R, countryIndex));
						c.click();
						resultCount++;
					}
				}catch(Exception e) {
					
				}
				
				countryIndex++;	
			}else {
				break;
			}
		}
		System.out.println("OK");
		return result;
	
		
	}
	
	public void iterateCitiesUrl(List<String> result) {
		for(String r : result) {
			driver.get(r);
			ri.search();
		}
	}

	public List<String> getCitiesList(int countrIndex) {
		List<String> result = new ArrayList<String>();
		int cityIndex = 1;
		while(cityIndex<=p.getMaxCities()) {
			try {
				String norm1 = normalizeIndex(REPLACE_TAG1, LINK, countrIndex);
				String norm2 = normalizeIndex(REPLACE_TAG2, norm1, cityIndex);
				System.out.println(norm2);
				WebElement we = this.findElementByCss(norm2);
				Thread.sleep(300);
				String[] split = we.getText().split("\\n");
				String city = split[0];
				int price = getIntegers(split[2]);
				if(p.checkCityFilter(city) && price<=p.getTreshold()) {
					String cityUrl = we.getAttribute("href");
					result.add(cityUrl);
				}
				cityIndex++;
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("OUTTT");
				break;
			}
			
		}
		return result;
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}
}
