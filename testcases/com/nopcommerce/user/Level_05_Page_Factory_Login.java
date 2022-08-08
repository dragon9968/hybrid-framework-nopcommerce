package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.nopCommerce.HomePageObjectFactory;
import pageFactory.nopCommerce.LoginPageObjectFactory;
import pageFactory.nopCommerce.RegisterPageObjectFactory;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_05_Page_Factory_Login extends BaseTest {
	private WebDriver driver;
	private String existingEmail, invalidEmail, notFoundEmail, firstName, lastName;
	//private String projectPath = System.getProperty("user.dir");
	private HomePageObjectFactory homePageFactory;
	private RegisterPageObjectFactory registerPageFactory;
	private LoginPageObjectFactory loginPageFactory;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver = getBrowserName(browserName);
		// Home Page
		homePageFactory = new HomePageObjectFactory(driver);

		firstName = "long";
		lastName = "qa";
		existingEmail = "long" + generateRandomNumber() + "@qa.team";
		invalidEmail = "long@123@";
		notFoundEmail = "longdaica@qa.team";

		System.out.println("Pre-Condition - Step 01: Click to Register link");
		homePageFactory.clickToRegisterLink();

		registerPageFactory = new RegisterPageObjectFactory(driver);

		System.out.println("Pre-Condition - Step 02: Input to required fields");
		registerPageFactory.inputToFirstnameTextbox("firstName");
		registerPageFactory.inputToLastnameTextbox("lastName");
		registerPageFactory.inputToEmailTextbox(existingEmail);
		registerPageFactory.inputToPasswordTextbox("123456");
		registerPageFactory.inputToConfirmPasswordTextbox("123456");
		System.out.println("Email registered is " + existingEmail);
		
		System.out.println("Pre-Condition - Step 03: Click Register button ");
		registerPageFactory.clickToRegisterButton();

		System.out.println("Pre-Condition - Step 04: Verified success message displayed ");
		Assert.assertEquals(registerPageFactory.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-Condition - Step 05: Click to Logout link");
		registerPageFactory.clickToLogoutLink();

		// Click logout thì bussiness nó sẽ quay về trang Home Page
		homePageFactory = new HomePageObjectFactory(driver);

	}

	@Test
	public void Login_01_Empty_Data() {
		homePageFactory.clickToLoginLink();
		
		// Từ trang Home - Click Login link -> Qa trang Login
		loginPageFactory = new LoginPageObjectFactory(driver);
		loginPageFactory.clickToLoginButton();
		
		Assert.assertEquals(loginPageFactory.getErrorMessageAtEmailTextbox(), "Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email() {
		homePageFactory.clickToLoginLink();

		loginPageFactory = new LoginPageObjectFactory(driver);
		loginPageFactory.inputToEmailTexbox(invalidEmail);

     	loginPageFactory.clickToLoginButton();

    	Assert.assertEquals(loginPageFactory.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	//@Test
	public void Login_03_Email_Not_Found() {
		homePageFactory.clickToLoginLink();

		loginPageFactory = new LoginPageObjectFactory(driver);

		loginPageFactory.inputToEmailTexbox(notFoundEmail);
		
		loginPageFactory.clickToLoginButton();

		Assert.assertEquals(loginPageFactory.getUnsuccessfullErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	//@Test
	public void Login_04_Existing_Email_Empty_Password() {
		homePageFactory.clickToLoginLink();

		loginPageFactory = new LoginPageObjectFactory(driver);

		loginPageFactory.inputToEmailTexbox(existingEmail);
		loginPageFactory.inputToPasswordTexbox("");
		
		loginPageFactory.clickToLoginButton();

		Assert.assertEquals(loginPageFactory.getUnsuccessfullErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	//@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		homePageFactory.clickToLoginLink();

		loginPageFactory = new LoginPageObjectFactory(driver);

		loginPageFactory.inputToEmailTexbox(existingEmail);
		loginPageFactory.inputToPasswordTexbox("123457");
		
		loginPageFactory.clickToLoginButton();

		Assert.assertEquals(loginPageFactory.getUnsuccessfullErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	//@Test
	public void Login_06_Valid_Login() {
		homePageFactory.clickToLoginLink();

		loginPageFactory = new LoginPageObjectFactory(driver);

		loginPageFactory.inputToEmailTexbox(existingEmail);
		loginPageFactory.inputToPasswordTexbox("123456");
		
		loginPageFactory.clickToLoginButton();
		
		//Login thành công -> Home Page
		homePageFactory = new HomePageObjectFactory(driver);
        Assert.assertTrue(homePageFactory.isMyAccountLinkDisplay());		

	}
	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
