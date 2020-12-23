import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Intro {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		// driver.quit();

		driver.findElement(By.id("divpaxinfo")).click();
		Thread.sleep(1000);
		int adultCount = 4;
		for (int i = 1; i < adultCount; i++) {
			driver.findElement(By.id("hrefIncAdt")).click();
		}

		int childCount = 4;
		for (int i = 0; i < childCount; i++) {
			driver.findElement(By.id("hrefIncChd")).click();
		}

		int infantCount = 2;
		for (int i = 0; i < infantCount; i++) {
			driver.findElement(By.id("hrefIncInf")).click();
		}

		driver.findElement(By.id("btnclosepaxoption")).click();

		System.out.println(driver.findElement(By.id("divpaxinfo")).getText());

		Select currency = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
		currency.selectByValue("AED");

		Thread.sleep(1000);
		driver.findElement(By.id("autosuggest")).sendKeys("Au");
		Thread.sleep(1000);
		List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));

		for (WebElement option : options) {

			if (option.getText().equalsIgnoreCase("Macau")) {
				option.click();
				break;
			}
		}

		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_originStation1_CTNR'] //a[@value='ATQ']"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='COK']"))
				.click();

		// "//div[@id='ctl00_mainContent_ddl_originStation1_CTNR'] //a[@value='ATQ']"
		/*
		 * driver.findElement(By.linkText("Practice Projects")).click();
		 * driver.findElement(By.id("name")).sendKeys("Tester");
		 * driver.findElement(By.id("email")).sendKeys("Tester@gmail.com");
		 * driver.findElement(By.xpath("//input[@id='agreeTerms']")).click();
		 * driver.findElement(By.cssSelector("#form-submit")).click();
		 */

		driver.quit();

	}

}
