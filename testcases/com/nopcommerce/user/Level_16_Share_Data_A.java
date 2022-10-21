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

public class Level_16_Share_Data_A extends BaseTest {
	private WebDriver driver;
	private String Email , password;
	//private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
        driver = getBrowserName(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);
        Email = Common_01_Register_New_Account.Email;
        password = Common_01_Register_New_Account.Password;
        
        log.info("Login - Step 01: Navigate to 'Login' Page");
		loginPage = homePage.openLoginPage();
		
		log.info("Login - Step 02: Enter to Email textbox with value is '" + Email + "'");
		loginPage.inputToEmailTexbox(Email);
		
		log.info("Login - Step 03: Enter to Password textbox with value is '" + password + "'");
		loginPage.inputToPasswordTexbox(password);
		
		log.info("Login - Step 04: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();
		
	}

	@Test
	public void Verify_01_My_Account() {
		
		log.info("Login - Step 05: Verify 'My Account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplay());	

		log.info("Login - Step 06: Navigate to 'My Account' Page");
		customerInfoPage = homePage.openMyAccountPage();
		
		log.info("Login - Step 07: Verify 'Customer Info' title is displayed");
		verifyTrue(customerInfoPage.isCustomerInfoTitleDisplay());
		
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
