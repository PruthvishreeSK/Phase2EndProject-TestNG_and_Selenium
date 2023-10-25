package starhealthtest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//launch https://www.starhealth.in/ and print all links present on the Star Health home page

public class Starhealth002 extends Extentreportgeneration {

	WebDriver driver;
	
	@BeforeClass
	public void openBrowser()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.starhealth.in/");
	}

	
	@Test
	public void links()
	{
		 //Get list of web-elements with tagName  - a
		 List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		 
		 //Traversing through the list and printing its text along with link address
		 for(WebElement link:allLinks)
			 System.out.println(link.getText() + " - " + link.getAttribute("href"));
	}
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	
}
