package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserHomePageObject_Techpanda;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject_Techpanda;
import pageUIs.nopCommerce.user.RegisterPageUI_Techpanda;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_06_Page_Generator_Manager_I_Techpanda1 extends BaseTest{
	private WebDriver driver_Test_Case;
	public static String emailAddress, password;
	//private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject_Techpanda homePage;
	private UserRegisterPageObject_Techpanda registerPage;
	  JavascriptExecutor jsExecutor;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserame) {
		System.out.println("Run on " + browserame);
		driver_Test_Case = getBrowserName(browserame);
		jsExecutor = (JavascriptExecutor) driver_Test_Case;

		// Home Page
        // homePage = new UserHomePageObject_Techpanda(driver_Test_Case);
		homePage = PageGeneratorManager.getHomePageTechPanda(driver_Test_Case);
		emailAddress = "long" + generateRandomNumber() + "@qa.team";

	}

	//@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01 - Step 01: Click to Register link");
		homePage.openRegisterPage();

		// Click Register Link nhảy qa trang Register
        //registerPage = new UserRegisterPageObject_Techpanda(driver_Test_Case);
        registerPage = PageGeneratorManager.getRegisterPageTechPanda(driver_Test_Case);
		System.out.println("Register_01 - Step 02: Click Register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "This is a required field.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "This is a required field.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "This is a required field.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "This is a required field.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "This is a required field.");
	}

	//@Test
	public void Register_02_Invalid_Email() {
		System.out.println("Register_02 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		// Click Register Link nhảy qa trang Register
        // registerPage = new UserRegisterPageObject_Techpanda(driver_Test_Case);
        registerPage = PageGeneratorManager.getRegisterPageTechPanda(driver_Test_Case);

		System.out.println("Register_02 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("Long");
		registerPage.inputToLastnameTextbox("Nguyen");
		registerPage.inputToEmailTextbox("longmail");
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");
        registerPage.checkToSignUpForNewsletterCheckbox();
		System.out.println("RRegister_02 - Step 03: Click Register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_02 - Step 04: Verify error message displayed ");
	//	Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;",registerPage.getInvalidMessageAtEmailTextbox()), "Vui lòng bao gồm '@' trong địa chỉ email. 'longmail' bị thiếu '@'.");
	    Assert.assertEquals(registerPage.getInvalidMessageAtEmailTextbox(), "Please include an '@' in the email address. 'longmail' is missing an '@'.");
	}

	@Test
	public void Register_03_Success() {
		System.out.println("Register_03 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		// Click Register Link nhảy qa trang Register
        //registerPage = new UserRegisterPageObject_Techpanda(driver_Test_Case);
        registerPage = PageGeneratorManager.getRegisterPageTechPanda(driver_Test_Case);

		System.out.println("Register_03 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("long");
		registerPage.inputToMiddlenameTextbox("dinh");
		registerPage.inputToLastnameTextbox("nguyen");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");
        registerPage.checkToSignUpForNewsletterCheckbox();

		System.out.println("Register_03 - Step 03: Click Register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_03 - Step 04: Verified success message displayed ");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Thank you for registering with Main Website Store.");

		//System.out.println("Register_03 - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();

	}

	//@Test
	public void Register_04_Existing_Email() {
		System.out.println("Register_04 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		// Click Register Link nhảy qa trang Register
        //registerPage = new UserRegisterPageObject_Techpanda(driver_Test_Case);
        registerPage = PageGeneratorManager.getRegisterPageTechPanda(driver_Test_Case);

		System.out.println("Register_04 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("long");
		registerPage.inputToMiddlenameTextbox("dinh");
		registerPage.inputToLastnameTextbox("nguyen");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");
        registerPage.checkToSignUpForNewsletterCheckbox();

		System.out.println("Register_04 - Step 03: Click Register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_04 - Step 04: Verify error message displayed ");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), 
				"There is already an account with this email address. If you are sure that it is your email address,"
				+ " click here to get your password and access your account.");

	}



	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	//@AfterClass
	public void afterClass() {
		driver_Test_Case.quit();
	}

}
