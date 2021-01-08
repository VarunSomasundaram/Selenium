import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CalendarHandling {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		//SSL & Insecure Certification Accept
		DesiredCapabilities c = DesiredCapabilities.chrome();
		c.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		c.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		
		ChromeOptions ch = new ChromeOptions();
		ch.merge(c);
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(ch);

		driver.get("https://www.spicejet.com/");

		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR'] //a[@value='ATQ']"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='COK']"))
				.click();
		Thread.sleep(1000);
		// Calendar Handling for Given Date
		String expectedMonth = "July";
		String expectedDay = "4";
		String expectedYear = "2021";
		/*WebElement calendarMonth = driver.findElement(By.cssSelector(
				"div.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-left span.ui-datepicker-month"));
		//WebElement nextMonth = driver.findElement(By.cssSelector(
				"div.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-right span.ui-icon.ui-icon-circle-triangle-e"));*/
		
		//Year Selection
		while (!driver.findElement(By.cssSelector(
				"div.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-left span.ui-datepicker-year")).getText().equalsIgnoreCase(expectedYear)) {
			driver.findElement(By.cssSelector(
					"div.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-right span.ui-icon.ui-icon-circle-triangle-e")).click();
			//Thread.sleep(1000);
		}
		
		//Month Selection
		while (!driver.findElement(By.cssSelector(
				"div.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-left span.ui-datepicker-month")).getText().equalsIgnoreCase(expectedMonth)) {
			driver.findElement(By.cssSelector(
					"div.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-right span.ui-icon.ui-icon-circle-triangle-e")).click();
			//Thread.sleep(1000);
		}
		
		//Date Selection
		List<WebElement> datePicker = driver.findElements(By.cssSelector("div.ui-datepicker-group.ui-datepicker-group-first a.ui-state-default"));
		int dateCount = datePicker.size();
		
		for (int i=0; i<dateCount; i++) {
			if (datePicker.get(i).getText().equalsIgnoreCase(expectedDay)) {
				datePicker.get(i).click();
				break;
			}
		}

		//Screenshot 
	File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE );
	FileUtils.copyFile(screenshot, new File("C:\\Users\\Somas\\Selenium Screenshots\\Pass.jpeg"));
	
	}

}
