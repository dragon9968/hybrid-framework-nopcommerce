package com.nopcommerce.common;



import commons.BaseTest;
import commons.PageGeneratorManager;

import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;

public class Common_01_Register_Cookie extends BaseTest {
	private WebDriver driver;
	private String  firstName, lastName ;
	public static String Email, Password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	public static Set<Cookie> loggedCookies;


	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all Classes")
	public void User_01_Register(String browserName) {
        driver = getBrowserName(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);
        
		firstName = "long";
		lastName = "qa";
		Email = "long" + generateRandomNumber() + "@qa.team";
		Password = "123456";
		
		log.info("Pre-Condition - Step 01: Navigate to 'Register' Page");
		registerPage = homePage.openRegisterPage();

		log.info("Pre-Condition - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Pre-Condition - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Pre-Condition - Step 04: Enter to Email textbox with value is '" + Email + "'");
		registerPage.inputToEmailTextbox(Email);
		
		log.info("Pre-Condition - Step 05: Enter to Password textbox with value is '" + Password + "'");
        registerPage.inputToPasswordTextbox(Password);
        
		log.info("Pre-Condition - Step 06: Enter to Confirm Password textbox with value is '" + Password + "'");
		registerPage.inputToConfirmPasswordTextbox(Password);
		
		log.info("Pre-Condition - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();
		
		log.info("Pre-Condition - Step 08: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Pre-Condition - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
		
		log.info("Pre-Condition - Step 10: Navigate to 'Login' Page");
		loginPage = homePage.openLoginPage();
		
		log.info("Pre-Condition - Step 11: Enter to Email textbox with value is '" + Email + "'");
		loginPage.inputToEmailTexbox(Email);
		
		log.info("Pre-Condition - Step 12: Enter to Password textbox with value is '" + Password + "'");
		loginPage.inputToPasswordTexbox(Password);
		
		log.info("Pre-Condition - Step 13: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();
		
		loggedCookies = homePage.getAllCookies(driver);
		
	}
	

		
	@AfterTest
	public void afterClass() {
		 //driver.quit();
	}

}
