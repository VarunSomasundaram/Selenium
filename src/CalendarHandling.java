import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalendarHandling {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

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
		String expectedMonth = "October";
		String expectedDay = "16";
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

	}

}
