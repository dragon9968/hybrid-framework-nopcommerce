package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserChangePassPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserDownloadProductsPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserOrdersPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPageObject;
import pageObjects.nopCommerce.user.UserStockPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_09_Dynamic_Locator extends BaseTest {
	private WebDriver driver;
	private String existingEmail, invalidEmail, notFoundEmail, firstName, lastName;
	//private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressesPageObject addressPage;
	private UserOrdersPageObject orderPage;
	private UserDownloadProductsPageObject downloadProductPage;
	private UserStockPageObject stockPage;
	private UserRewardPageObject rewardPage;
	private UserChangePassPageObject changePassPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
	
        driver = getBrowserName(browserName);
		//homePage = new HomePageObject(driver);
        homePage = PageGeneratorManager.getHomePage(driver);
		

		firstName = "long";
		lastName = "qa";
		existingEmail = "long" + generateRandomNumber() + "@qa.team";
		invalidEmail = "long@123@";
		notFoundEmail = "longdaica@qa.team";

		System.out.println("Pre-Condition - Step 01: Click to Register link");

	}

	@Test
	public void User_01_Register_Login() {
		registerPage = homePage.openRegisterPage();

		registerPage.inputToFirstnameTextbox("firstName");
		registerPage.inputToLastnameTextbox("lastName");
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		homePage = registerPage.clickToLogoutLink();
	
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTexbox(existingEmail);
		loginPage.inputToPasswordTexbox("123456");
		homePage = loginPage.clickToLoginButton();
        Assert.assertTrue(homePage.isMyAccountLinkDisplay());	

		customerInfoPage = homePage.openMyAccountPage();
		Assert.assertTrue(customerInfoPage.isCustomerInfoTitleDisplay());	
	}
	

	@Test
	public void User_02_Switch_Page() {
		// Customer Info -> Address
		addressPage = customerInfoPage.openAddressesPage(driver);
		sleepInSecond(1);
		
		// Address -> Rewards
		rewardPage = addressPage.openRewardPointPage(driver);
		sleepInSecond(1);
		
		// Rewards -> Customer Info
		customerInfoPage = rewardPage.openCustomerInfoPage(driver);
		sleepInSecond(1);
		
		// Customer Info -> Change Pass
		changePassPage = customerInfoPage.openChangePassPage(driver);
		sleepInSecond(1);
		
		// Change Pass -> My Product
		myProductReviewPage = changePassPage.openMyProductPage(driver);
		sleepInSecond(1);
		
		// My Product -> Download
		downloadProductPage = myProductReviewPage.openDownLoadProductPage(driver);
		sleepInSecond(1);
	}
	
	
	@Test
	public void User_03_Dynamic_Page_01() {
		// Download-> Change Pass
		changePassPage = (UserChangePassPageObject) downloadProductPage.openPagesAtMyAccountByName(driver, "Change password");
		
		// Change Pass -> Rewards
		rewardPage = (UserRewardPageObject) changePassPage.openPagesAtMyAccountByName(driver, "Reward points");
		sleepInSecond(1);
	}
	
	@Test
	public void User_04_Dynamic_Page_02() {
		// Rewards -> Stock
		 rewardPage.openDynamicMorePages(driver, "Back in stock subscriptions");
		 stockPage = PageGeneratorManager.getStockPage(driver);	
        
		// Stock -> Orders
		 stockPage.openDynamicMorePages(driver, "Orders");
		 orderPage = PageGeneratorManager.getOrderPage(driver);
		
	}

	


	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
