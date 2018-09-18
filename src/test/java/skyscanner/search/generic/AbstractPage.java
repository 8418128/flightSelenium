package skyscanner.search.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AbstractPage {

	public WebDriver driver;
	protected Integer timeout = 10; // seconds
	protected Integer timeoutWe = 20;
	
	private static String ACCEPT_COOKIES = ".bpk-button-3T6BX";
	protected static String REPLACE_TAG1 = "99991";
	protected static String REPLACE_TAG2 = "99992";

	public AbstractPage ( WebDriver driver ) {
		this.driver = driver;
		driver.manage ().timeouts ().implicitlyWait ( timeout , TimeUnit.SECONDS );
	}


	public boolean isElementVisible ( String xpath ) {
		boolean visible = false;
		WebElement element = this.findElementByXPath ( xpath );
		visible = element != null && element.isDisplayed ();
		return visible;
	}


	public WebElement findElementByXPath ( String xpath ) {
		WebElement element = null;
		element = driver.findElement ( By.xpath ( xpath ) );
		return element;
	}


	public List <WebElement> findElementsByXPath ( String xpath ) {
		List <WebElement> elements = new ArrayList <WebElement> ();
		elements = driver.findElements ( By.xpath ( xpath ) );
		return elements;
	}


	public WebElement findElementById ( String id ) {
		return findDynamicElement ( By.id ( id ) , 20 );
	}


	public WebElement findElementByName ( String name ) {
		return driver.findElement ( By.name ( name ) );
	}


	public WebElement findElementByCss ( String elem , String attr , String value ) {
		return driver.findElement ( By.cssSelector ( elem + "[" + attr + "='" + value + "']" ) );
	}


	public WebElement findElementByCss ( String selector ) {		
		return findDynamicElement ( By.cssSelector ( selector ) , 5 );
	}


	public WebDriver getDriver () {
		return this.driver;
	}
	
	public WebElement findDynamicElementByCss ( String css ) {
		return findDynamicElement(By.cssSelector(css),timeoutWe);
	}
	
	public WebElement findDynamicElementByCss ( String css,int time ) {
		return findDynamicElement(By.cssSelector(css),time);
	}
	
	public List<WebElement> findDynamicElementsByCss ( String css ) {
		return driver.findElements(By.cssSelector(css));
	}

	public WebElement findDynamicElement ( By by , int timeOut ) {
		//System.out.println ( "trying to find " + by.toString () );
		WebDriverWait wait = new WebDriverWait ( driver , timeOut );
		WebElement element = wait.until ( ExpectedConditions.visibilityOfElementLocated ( by ) );
		return element;
	}
	
	public String normalizeIndex ( String replacement, String selector , int index ) {
		String normalicedInt = Integer.toString ( index );
		return selector.replace ( "(" + replacement + ")" , "(" + normalicedInt + ")" );
	}

	public String getText ( String selector ) {
		WebElement we = this.findDynamicElementByCss( selector );
		return we.getText();
	}
	
	public boolean checkPrice(int treshold,String selector) {
		return getIntegers(getText(selector))<=treshold;
	}
	
	public Integer getIntegers(String text) {
		return Integer.parseInt(text.replaceAll("\\D+","").trim());
	}
	
	public void acceptCookies() {
		try {
			this.findDynamicElementByCss(ACCEPT_COOKIES).click();
		}catch(Exception e) {
			
		}
	}
	
	public abstract boolean isReady ();
	
	public void openTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
	}
	public void closeTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"w");
	}
}
