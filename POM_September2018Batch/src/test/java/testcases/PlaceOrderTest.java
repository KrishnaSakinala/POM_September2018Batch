package testcases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import basetest.BaseTest;
import pages.*;
import repo.DataUtil;


public class PlaceOrderTest extends BaseTest
{
	@Test(description = "This test script tests the placing order using COD.")
	public void placeOrderByCOD()
	{
		extentTest = extentReports.createTest("placeOrderByCOD","This test script tests the placing order using cash on delivery.");
		
		if(!DataUtil.isTestExecutable(eat, "TestCases", "placeOrderByCOD"))
		{
			throw new SkipException("Skipping this test case as run mode is not Y");
		}
		
		HomePage homePage = new HomePage(driver, extentTest);
		SearchResultsPage searchResultsPage = homePage.searchBook();
		ProductDisplayPage productDisplayPage = searchResultsPage.clickSearchedBook();
		BasketPage basketPage = productDisplayPage.navigateToBasketPage();
		CheckoutPage checkoutPage = basketPage.proceedToCheckout();
		checkoutPage.placeOrderByCOD();
	}
	
	@Test
	public void placeOrderUsingNB()
	{
		extentTest = extentReports.createTest("placeOrderUsingNB");
		
		if(DataUtil.isTestExecutable(eat, "TestCases", "placeOrderUsingNB"))
		{
			HomePage homePage = new HomePage(driver, extentTest);
			SearchResultsPage searchResultsPage = homePage.searchBook();
			ProductDisplayPage productDisplayPage = searchResultsPage.clickSearchedBook();
			BasketPage basketPage = productDisplayPage.navigateToBasketPage();
			CheckoutPage checkoutPage = basketPage.proceedToCheckout();
			checkoutPage.placeOrderByNB();
		}
		else
		{
			throw new SkipException("Skipping this test case as run mode is not Y");
		}
	}
	
	@Test
	public void placeOrderUsingPayapl()
	{
		extentTest = extentReports.createTest("placeOrderUsingNB");
		
		if(DataUtil.isTestExecutable(eat, "TestCases", "placeOrderUsingNB"))
		{
			HomePage homePage = new HomePage(driver, extentTest);
			SearchResultsPage searchResultsPage = homePage.searchBook();
			ProductDisplayPage productDisplayPage = searchResultsPage.clickSearchedBook();
			BasketPage basketPage = productDisplayPage.navigateToBasketPage();
			CheckoutPage checkoutPage = basketPage.proceedToCheckout();
			checkoutPage.placeOrderByNB();
		}
		else
		{
			throw new SkipException("Skipping this test case as run mode is not Y");
		}
	}

}
