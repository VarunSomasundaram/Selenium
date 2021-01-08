import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class WindowsHandling {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Implicit Wait
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		String inputText = "Varun";

		driver.manage().window().maximize();
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
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,500)");

		
		List<WebElement> amount = driver.findElements(By.xpath("//*[@class='tableFixHead'] //td[4]"));
		int sum = 0;
		for (int i=0;i<amount.size();i++) {
			sum = sum + Integer.parseInt(amount.get(i).getText());
		}
		
		int total =Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim());
		
		Assert.assertEquals(sum, total);
		
		List<WebElement> price = driver.findElements(By.xpath("//*[@class='table-display'] //td[3]"));
		int priceTotal = 0;
		for (int i=0;i<price.size();i++) {
			priceTotal = priceTotal + Integer.parseInt(price.get(i).getText());
		}
		System.out.println(sum + " , " + priceTotal);
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
