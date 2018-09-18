package test;

import static org.testng.Assert.assertTrue;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;



public class DefaultTest {

	protected WebDriver driver;


	@BeforeTest
	public void beforeTest () throws InterruptedException {

		String geckoDriver = System.getProperty ( "user.dir" ) + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "geckodriver.exe";

		System.setProperty ( "webdriver.gecko.driver" , geckoDriver );

		driver = new FirefoxDriver ();

	}


	@AfterTest
	public void afterTest () throws InterruptedException {
		//driver.quit ();
	}



	protected void goTo ( String url ) {
		driver.get ( url );
	}

}
