package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_New_Account;

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

public class Level_16_Share_Data_B extends BaseTest {
	private WebDriver driver;
	private String existingEmail,firstName, lastName , password;
	//private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserRegisterPageObject registerPage;

	

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
        driver = getBrowserName(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);
        
        firstName = "long";
		lastName = "qa";
		existingEmail = "long" + generateRandomNumber() + "@qa.team";
		password = "123456";
		
        log.info("Pre-Conditon - Step 01: Navigate to 'Register' Page");
		registerPage = homePage.openRegisterPage();

		log.info("Pre-Conditon - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Pre-Conditon - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Pre-Conditon - Step 04: Enter to Email textbox with value is '" + existingEmail + "'");
		registerPage.inputToEmailTextbox(existingEmail);
		
		log.info("Pre-Conditon - Step 05: Enter to Password textbox with value is '" + password + "'");
        registerPage.inputToPasswordTextbox(password);
        
		log.info("Pre-Conditon - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);
		
		log.info("Pre-Conditon - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();
		
		log.info("Pre-Conditon - Step 08: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Pre-Conditon - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
		
		log.info("Pre-Conditon - Step 10: Navigate to 'Login' Page");
		loginPage = homePage.openLoginPage();
		
		log.info("Pre-Conditon - Step 11: Enter to Email textbox with value is '" + existingEmail + "'");
		loginPage.inputToEmailTexbox(existingEmail);
		
		log.info("Pre-Conditon - Step 12: Enter to Password textbox with value is '" + password + "'");
		loginPage.inputToPasswordTexbox(password);
		
		log.info("Pre-Conditon - Step 13: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();
	
	}
	
	@Test
	public void Search_01_Empty_Data() {
		
	}
	@Test
	public void Search_02_Relative_Product_Name() {
		
	}
	@Test
	public void Search_03_Absolute_Product_Name() {
		
	}
	@Test
	public void Search_04_Parent_Category() {
		
	}
		
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
