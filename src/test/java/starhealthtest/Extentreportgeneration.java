package starhealthtest;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extentreportgeneration {

	public static ExtentReports er;
	public static ExtentTest et;
	
	@BeforeSuite
	public void IntialiseExtentReport()
	{
		
		er = new ExtentReports();
		File file = new File("ExtentReport.html");
		ExtentSparkReporter esr = new ExtentSparkReporter(file);
		er.attachReporter(esr);
		//To display additional info like OS, Java version, use-- er.setSystemInfo() with System.getProperty
		er.setSystemInfo("OS", System.getProperty("os.name"));
		er.setSystemInfo("JAVA", System.getProperty("java.version"));
	}
	
	@AfterSuite
	public void GenerateReport() throws IOException
	{
		
		er.flush();
		Desktop.getDesktop().browse(new File("ExtentReport.html").toURI());
		
	}
	
	@BeforeTest
	public void openbrowser(ITestContext context)
	{
		
		et = er.createTest(context.getName());
		
	}
	
	public void GenerateTestStatus(Method m, ITestResult result)
	{
		
		if(result.getStatus() == ITestResult.FAILURE)
		{
			System.out.println("Test Failed");
			et.fail(result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
			et.pass(m.getName()+" is passed");
	}
	
	
}
