package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class BasketPage extends BasePage{

	@FindBy(xpath = "//*[@id='coupon_code']")
	private WebElement couponCodeTextbox;
	
	@FindBy(xpath = "//*[@id='page-34']/div/div[1]/form/table/tbody/tr[2]/td/div/input[2]")
	private WebElement applyCouponButton;
	
	@FindBy(xpath = "//*[@id='page-34']/div/div[1]/div[2]/div/div/a")
	private WebElement proceedToCheckboutButton;
	
	private String loadinSpinner = "//div[@class='blockUI blockOverlay']";
	
	public BasketPage(WebDriver driver,ExtentTest extentTest) {
		super(driver,extentTest);
		PageFactory.initElements(driver, this);
	}
	
	public CheckoutPage proceedToCheckout()
	{
		couponCodeTextbox.sendKeys("krishnasakinala");
		applyCouponButton.click();
		waitUntilElementInvisible(loadinSpinner);
		proceedToCheckboutButton.click();
		
		return new CheckoutPage(driver,extentTest);
	}

}
