package Steps;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.shop.browser.Driver;
import com.shop.browser.DriverManager;
import com.shop.constants.Constants;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseTest {
	
	@Before
	public void setUp() {
		try {
			Driver.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	@After
	public void wrapUp(Scenario scenario) {
		
			try {
				scenario.embed(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES), "image/png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		DriverManager.getDriver().quit();
	}

}
