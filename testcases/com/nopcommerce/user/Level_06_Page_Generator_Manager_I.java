package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_06_Page_Generator_Manager_I extends BaseTest{
	private WebDriver driver_Test_Case;
	private String emailAddress;
	//private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserame) {
		System.out.println("Run on " + browserame);
		driver_Test_Case = getBrowserName(browserame);
	    
		// Home Page
        homePage = new UserHomePageObject(driver_Test_Case);
		emailAddress = "long" + generateRandomNumber() + "@qa.team";

	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01 - Step 01: Click to Register link");
		homePage.openRegisterPage();

		// Click Register Link nhảy qa trang Register
        registerPage = new UserRegisterPageObject(driver_Test_Case);

		System.out.println("Register_01 - Step 02: Click Register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		System.out.println("Register_02 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		// Click Register Link nhảy qa trang Register
        registerPage = new UserRegisterPageObject(driver_Test_Case);

		System.out.println("Register_02 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("Automation");
		registerPage.inputToLastnameTextbox("FC");
		registerPage.inputToEmailTextbox("123@123#@#$");
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");

		System.out.println("RRegister_02 - Step 03: Click Register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_02 - Step 04: Verify error message displayed ");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	//@Test
	public void Register_03_Success() {
		System.out.println("Register_03 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		// Click Register Link nhảy qa trang Register
        registerPage = new UserRegisterPageObject(driver_Test_Case);

		System.out.println("Register_03 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("Automation");
		registerPage.inputToLastnameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");

		System.out.println("Register_03 - Step 03: Click Register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_03 - Step 04: Verified success message displayed ");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Register_03 - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();

	}

	//@Test
	public void Register_04_Existing_Email() {
		System.out.println("Register_04 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		// Click Register Link nhảy qa trang Register
        registerPage = new UserRegisterPageObject(driver_Test_Case);

		System.out.println("Register_04 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("Automation");
		registerPage.inputToLastnameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");

		System.out.println("Register_04 - Step 03: Click Register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_04 - Step 04: Verify error message displayed ");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	//@Test
	public void Register_05_Password_Less_Than_6_Chars() {
		System.out.println("Register_05 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		// Click Register Link nhảy qa trang Register
        registerPage = new UserRegisterPageObject(driver_Test_Case);

		System.out.println("Register_05 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("Automation");
		registerPage.inputToLastnameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");

		System.out.println("Register_05 - Step 03: Click Register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_05 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");

	}

	//@Test
	public void Register_06_Invalid_Confirm_Password() {
		System.out.println("Register_06 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		// Click Register Link nhảy qa trang Register
        registerPage = new UserRegisterPageObject(driver_Test_Case);

		System.out.println("Register_06 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("Automation");
		registerPage.inputToLastnameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123457");

		System.out.println("Register_06 - Step 03: Click Register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_06 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),
				"The password and confirmation password do not match.");

	}

	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver_Test_Case.quit();
	}

}
