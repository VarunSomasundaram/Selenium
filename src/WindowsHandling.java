import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowsHandling {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Implicit Wait
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		String inputText = "Varun";

		// Accept Btn Alert Validation

		driver.findElement(By.id("name")).sendKeys(inputText);
		driver.findElement(By.id("alertbtn")).click();

		String alertText = driver.switchTo().alert().getText();

		if (inputText.equalsIgnoreCase(alertText.split("Hello")[1].trim().split(",")[0])) {
			System.out.println("Alert Message Matched");
		} else {
			System.out.println("Alert Message NOT Matched");
		}

		driver.switchTo().alert().accept();

		// Confirm Btn Alert Validation
		Thread.sleep(3000);

		driver.findElement(By.id("name")).sendKeys(inputText);
		driver.findElement(By.id("confirmbtn")).click();

		alertText = driver.switchTo().alert().getText();

		if (inputText.equalsIgnoreCase(alertText.split("Hello")[1].trim().split(",")[0])) {
			System.out.println("Alert Message Matched");
		} else {
			System.out.println("Alert Message NOT Matched");
		}

		driver.switchTo().alert().dismiss();

		WebElement linkColumn = driver.findElement(By.xpath("//table[@class='gf-t']/tbody/tr/td/ul"));
		int linkCount = linkColumn.findElements(By.tagName("a")).size();
		String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);

		for (int i = 0; i < linkCount - 1; i++) {
			// System.out.println(linkColumn.findElements(By.xpath("//li[@class='gf-li']/a")).get(i).getText());
			linkColumn.findElements(By.xpath("//li[@class='gf-li']/a")).get(i).sendKeys(newTab);
		}
		Thread.sleep(3000);

		Set<String> windows = driver.getWindowHandles();
		Iterator<String> a = windows.iterator();

		while (a.hasNext()) {
			driver.switchTo().window(a.next());
			System.out.println(driver.getTitle());
		}

		driver.quit();
	}

}
