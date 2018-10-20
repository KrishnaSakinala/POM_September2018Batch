package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class ProductDisplayPage extends BasePage
{
	@FindBy(xpath = "//*[@id='product-160']/div[2]/form/button")
	private WebElement addtoBasketButton;
	
	@FindBy(xpath = "//*[@id='content']/div[1]/a")
	private WebElement viewBasketButton;
	
	
	public ProductDisplayPage(WebDriver driver,ExtentTest extentTest) {
		super(driver,extentTest);
		PageFactory.initElements(driver, this);
	}
	
	public BasketPage navigateToBasketPage()
	{
		addtoBasketButton.click();
		extentTest.info("Clicking on add to basket button.");
		viewBasketButton.click();
		extentTest.info("Clicking on view basket button.");
		
		//BasketPage basketPage = new BasketPage(driver);
		//return basketPage;
		
		return new BasketPage(driver,extentTest);
	}

}
