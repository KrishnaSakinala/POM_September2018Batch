package basetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import repo.ExcelApiTest;

public class BaseTest {
	
	public WebDriver driver;
	
	public ExtentHtmlReporter extentHtmlReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	public FileInputStream fis = null;
	public Properties config = null;
	
	public ExcelApiTest eat = null;
	
	@BeforeSuite
	public void init() throws Exception
	{
		eat = new ExcelApiTest("/Users/krishnasakinala/git/POM_September2018Batch/POM_September2018Batch/src/main/resources/TestData.xlsx");
		
		fis = new FileInputStream("/Users/krishnasakinala/git/POM_September2018Batch/POM_September2018Batch/src/main/resources/config.properties");
		
		config = new Properties();
		config.load(fis);
		
		if(extentReports == null)
		{
			extentHtmlReporter = new ExtentHtmlReporter("/Users/krishnasakinala/git/POM_September2018Batch/POM_September2018Batch/src/main/resources/ATReport.html");
			extentReports = new ExtentReports();
			extentReports.attachReporter(extentHtmlReporter);
						
			extentReports.setSystemInfo("OS", "Mac OS");
			extentReports.setSystemInfo("Environment", "QA");
			extentReports.setSystemInfo("UserName", "Krishna Sakinala");
			
			extentHtmlReporter.config().setChartVisibilityOnOpen(false);
			extentHtmlReporter.config().setDocumentTitle("Automation Report");
			extentHtmlReporter.config().setReportName("My Own Report");
			extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
			extentHtmlReporter.config().setTheme(Theme.DARK);
		}
		
		if(driver == null)
		{
			if(config.getProperty("browser").equalsIgnoreCase("chrome"))
			{
				if(config.getProperty("os").equals("mac"))
				{
					System.setProperty("webdriver.chrome.driver", "/KRISHNA VOLUME/Selenium/drivers/chromedriver");
				}
				else
				{
					System.setProperty("webdriver.chrome.driver", "C:/KRISHNA VOLUME/Selenium/drivers/chromedriver.exe");
				}
				driver = new ChromeDriver(); 
			}
			else
			{
				System.setProperty("webdriver.gecko.driver", "/KRISHNA VOLUME/Selenium/drivers/geckodriver");
				driver = new FirefoxDriver(); 
			}
		}
		
		driver.get(config.getProperty("url"));
	}
	
	public static String capture(WebDriver driver, String screenshotName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = "/Users/krishnasakinala/git/POM_September2018Batch/POM_September2018Batch/src/main/resources/screenshots/"+screenshotName+".png";
		File destination = new File(dest);
		FileHandler.copy(source, destination);
		
		return dest;
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			extentTest.fail("Test Failed");
			extentTest.fail(result.getThrowable());
			String path = capture(driver, "screenshot");
			extentTest.fail("Find screenshot below: ",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			extentTest.pass("Test Passed");
			
		}
		else
		{
			extentTest.skip("Test Skipped");
			extentTest.skip(result.getThrowable());
		}
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException
	{
		extentReports.flush();
		
		if(driver != null)
		{
			Thread.sleep(5000);
			
			driver.quit();
		}
	}

}
