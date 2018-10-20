package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class BasePage {

	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentTest extentTest;
	
	public BasePage(WebDriver driver, ExtentTest extentTest)
	{
		this.driver = driver;
		this.extentTest = extentTest;
	}
	
	public void waitUntilElementInvisible(String locator)
	{
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
	}
	
	
}
