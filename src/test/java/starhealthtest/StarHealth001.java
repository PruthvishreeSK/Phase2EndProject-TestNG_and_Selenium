package starhealthtest;

import static org.testng.Assert.*;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class StarHealth001 extends Extentreportgeneration{

	WebDriver driver;
	
	//Use @BeforeClass/@BeforeSuite to launch https://www.starhealth.in/
	@BeforeClass
	public void openBrowser()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.starhealth.in/");
	}

	//Validate the alt value of the star health logo is 'Star Health' using the TestNG assertion
	@Test(priority='1')
	public void Validate_logo()
	{
		String Expected_alt = "Star Health Logo";
		System.out.println("Expected Logo alt text= "+Expected_alt);
		String Actual_alt = driver.findElement(By.xpath("//img[@class='cursor-pointer']")).getAttribute("alt");
		System.out.println("Actual Logo alt text= "+Actual_alt);
		assertEquals(Actual_alt, Expected_alt , "The alt text is not valid");
	}
	
	//Hover the cursor on the header Plans menu option 
	//Click on the For My Family option
	@Test(priority='2')
	public void Header_plan() throws InterruptedException
	{
		WebElement hover = driver.findElement(By.xpath("(//p[@class='font-500 text-16 mb-0 pt-10 pb-10'])[1]"));
		Actions a = new Actions(driver);
		a.moveToElement(hover).build().perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("(//a[@class='font-500 text-capitalize text-14'])[2]")).click();
		
	}
	
	//Type name, mobile, and pincode. Now, click Get Quote [data should be parameterized from the testNG.xml file]
	@Parameters({"name1","mobile","pincode"})
	@Test(priority='3')
	public void inputdata(String name1, String mobile, String pincode) throws InterruptedException
	{
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='get-quote-form_input-quote__zRlL3 w-full'])[1]")).sendKeys(name1);
		driver.findElement(By.xpath("(//input[@class='get-quote-form_input-quote__zRlL3 w-full'])[2]")).sendKeys(mobile);
		driver.findElement(By.xpath("(//input[@class='get-quote-form_input-quote__zRlL3 w-full'])[3]")).sendKeys(pincode);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[@class='btn-text'])[2]")).click();
		Thread.sleep(3000);	
	}
	
	//Validate Authorize checkbox is checked by default using TestNG assertion
	@Test(priority='4')
	public void validate_checkbox() throws InterruptedException
	{
		driver.get("https://web.starhealth.in/comprehensive-health-insurance-plan?");
		Thread.sleep(3000);
		assertTrue(driver.findElement(By.xpath("//input[@class='jss1030']")).isSelected());
		Thread.sleep(3000);
	
	}
	
	//Click on the Back button
	@Test(priority='5')
	public void navigateback() throws InterruptedException
	{
		driver.navigate().back();
		Thread.sleep(3000);
	
	}
	
	/*Keep social media details for Twitter, Facebook, LinkedIn, YouTube, and 
	Instagram in an Excel Sheet. Read these details during execution and validate 
	using the TestNG assertion, checking that these social media options are 
	available in the Star Health footer section*/

	@Test(priority='6',dataProvider = "testdata")
	public void validatedatafromExcel(String facebook, String youtube, String LinkedIn, String Twitter, String Instagram )
	{
		
		assertEquals(driver.findElement(By.xpath("(//a[@class='mr-4 mr-10-xs'])[1]")).getAttribute("href"),facebook);
		assertEquals(driver.findElement(By.xpath("(//a[@class='mr-4 mr-10-xs'])[2]")).getAttribute("href"),youtube);
		assertEquals(driver.findElement(By.xpath("(//a[@class='mr-4 mr-10-xs'])[3]")).getAttribute("href"),LinkedIn);
		assertEquals(driver.findElement(By.xpath("(//a[@class='mr-4 mr-10-xs'])[4]")).getAttribute("href"),Twitter);
		assertEquals(driver.findElement(By.xpath("(//a[@class='mr-4 mr-10-xs'])[5]")).getAttribute("href"),Instagram);
			
	}
	
	
	@DataProvider(name="testdata")
	public Object[][] datasupplier() throws EncryptedDocumentException, IOException
	{
		
		Object[][] inputdata = XLS_DataProvider.getTestData("Sheet1");
		
		return inputdata;
	}
	
	/*Scroll down on the same page and click on the Twitter logo present in the footer 
	of the social media section
	Validate the Twitter page opens in a new TAB and that the starhealthins text is 
	present in the Twitter page URL*/
	
	@Test(priority='7')
	public void validatetwitter() throws InterruptedException
	{
		driver.findElement(By.xpath("(//a[@class='mr-4 mr-10-xs'])[4]")).click();
		
		Thread.sleep(3000);
		System.out.println(driver.getCurrentUrl());
		assertTrue(driver.getCurrentUrl().contains("starhealthins"));
	}
	
	//Close the child’s Twitter TAB
	@Test(priority='8')
	public void closechild()
	{
		driver.close();
	}
	
	//Use @AfterClass/@AfterSuite to quit the driver session
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	
}
