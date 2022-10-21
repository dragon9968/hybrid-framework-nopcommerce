package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Cookie;
import com.nopcommerce.common.Common_01_Register_New_Account;

import commons.BaseTest;
import commons.PageGeneratorManager;

import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;

public class Level_16_Share_Data_C extends BaseTest {
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
		
		log.info("Login - Step 02: Set cookie and reload page");
		loginPage.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
     		
		loginPage.refreshCurrentPage(driver);
	}

	@Test
	public void Verify_01_My_Account() {
		
		log.info("Verify My Account - Step 01: Verify 'My Account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplay());	

		log.info("Verify My Account - Step 02: Navigate to 'My Account' Page");
		customerInfoPage = homePage.openMyAccountPage();
		
		log.info("Verify My Account - Step 03: Verify 'Customer Info' title is displayed");
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
