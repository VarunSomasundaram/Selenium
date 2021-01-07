import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCart {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Implicit Wait
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get("https://rahulshettyacademy.com/seleniumPractise");

		// Explicit Wait
		WebDriverWait pageLoad = new WebDriverWait(driver, 5);
		pageLoad.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-name")));
		String[] requiredVeg = { "Beetroot", "Apple", "Banana", "Peach" };
		int counter = 0;
		List<String> vegList = Arrays.asList(requiredVeg);

		List<WebElement> vegetableName = driver.findElements(By.cssSelector(".product-name"));
		List<String> availableVeg = new ArrayList<String>();

		for (int i = 0; i < vegetableName.size(); i++) {
			String[] itemName = vegetableName.get(i).getText().split("-");
			String fItemName = itemName[0].trim();
			availableVeg.add(fItemName);

			if (vegList.contains(fItemName)) {
				counter++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				System.out.println(fItemName + " is added to the cart");
				// driver.findElements(By.xpath("//button[text()='ADD TO
				// CART']")).get(i).click();
				// Thread.sleep(3000);
				if (counter == requiredVeg.length)
					break;
			}

		}

		for (String obj : vegList) {
			if (!(availableVeg.contains(obj)))
				System.out.println(obj + " is NOT present in the store");
		}

		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		driver.findElement(By.xpath("//div[@class='action-block']/button")).click();

		pageLoad.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));

		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("Rahul Shetty");
		driver.findElement(By.cssSelector("button.promoBtn")).click();

		pageLoad.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoInfo")));
		System.out.println(driver.findElement(By.cssSelector(".promoInfo")).getText());

		driver.findElement(By.xpath("//button[text()='Place Order']")).click();

		pageLoad.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='wrapperTwo']/div/select")));
		Select country = new Select(driver.findElement(By.xpath("//div[@class='wrapperTwo']/div/select")));
		country.selectByValue("United States");

		driver.findElement(By.xpath("//*[@class='chkAgree']")).click();
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();

		driver.quit();
	}

}


