package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class SearchResultsPage extends BasePage{

	@FindBy(xpath = "//a[@title='Selenium Ruby']")
	private WebElement searchResultsBookTitle;
	
	
	public SearchResultsPage(WebDriver driver,ExtentTest extentTest) 
	{
		super(driver,extentTest);
		PageFactory.initElements(driver, this);
	}
	
	public ProductDisplayPage clickSearchedBook()
	{
		searchResultsBookTitle.click();
		extentTest.info("Clicking on the searched book.");
		ProductDisplayPage ProductDisplayPage = new ProductDisplayPage(driver,extentTest);
		return ProductDisplayPage;
	}

}
