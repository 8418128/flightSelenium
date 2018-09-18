package IATA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import skyscanner.search.generic.AbstractPage;

public class IataCodeSearcher extends AbstractPage{

	private static String URL_IATA = "https://www.iata.org/publications/Pages/code-search.aspx";
	
	
	public IataCodeSearcher(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getIataCodeFromCityName(String city) {
		String code = checkCache(city);
		if(code==null) {
			driver.get(URL_IATA);
			//Select dropdown = new Select(this.findDynamicElementByCss());
		}
		
		return null;
	}
	
	public String checkCache(String city) {
		return null;
	}
	
	
	

}
