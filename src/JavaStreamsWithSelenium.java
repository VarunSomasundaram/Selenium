import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaStreamsWithSelenium {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		String[] required = {"Cheese", "Rice", "Wheat"};
		String filter = "C";
		List<String> reqList = Arrays.asList(required);
		reqList = reqList.stream().sorted().collect(Collectors.toList());
		
		driver.findElement(By.xpath("//tr/th[1]")).click();
		
		reqList.forEach(item-> {
			
			List<WebElement> reqVeg = new ArrayList<WebElement>();
			do {
				List<WebElement> vege = driver.findElements(By.xpath("//tr/td[1]"));
				reqVeg = vege.stream().filter(s -> s.getText().equalsIgnoreCase(item)).collect(Collectors.toList());
				if (reqVeg.size() < 1) {			
					driver.findElement(By.xpath("//a[@aria-label='Next']")).click();					
				}

			} while (reqVeg.size() < 1);
			reqVeg.stream().map(s -> getPrice(s)).forEach(s -> System.out.println("The Price of " + item + " is " + s));
			driver.findElement(By.xpath("//a[@aria-label='First']")).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		});
		
		
		driver.findElement(By.id("search-field")).sendKeys(filter);
		List<WebElement> vege = driver.findElements(By.xpath("//tr/td[1]"));
		List<WebElement> filterList = vege.stream().filter(s-> s.getText().contains(filter)).collect(Collectors.toList());

		if (vege.size()==filterList.size())
		{
			System.out.println("Filter is working Fine");
		}else
			System.out.println("Filter is NOT working");
	}

	private static String getPrice(WebElement s) {
		// TODO Auto-generated method stub

		return s.findElement(By.xpath("following-sibling::td[1]")).getText();

	}

}
