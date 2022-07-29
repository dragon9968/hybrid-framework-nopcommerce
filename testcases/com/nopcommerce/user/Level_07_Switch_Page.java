package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_07_Switch_Page extends BaseTest {
	private WebDriver driver;
	private String existingEmail, invalidEmail, notFoundEmail, firstName, lastName;
	//private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	
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
		

		registerPage = homePage.clickToRegisterLink();

		System.out.println("Pre-Condition - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("firstName");
		registerPage.inputToLastnameTextbox("lastName");
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");
		System.out.println("Email registered is " + existingEmail);
		
		System.out.println("Pre-Condition - Step 03: Click Register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-Condition - Step 04: Verified success message displayed ");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-Condition - Step 05: Click to Logout link");
		
		// Click logout thì bussiness nó sẽ quay về trang Home Page
		homePage = registerPage.clickToLogoutLink();


	}

	//@Test
	public void User_01_Register() {
		registerPage = homePage.clickToRegisterLink();

		registerPage.inputToFirstnameTextbox("firstName");
		registerPage.inputToLastnameTextbox("lastName");
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void User_01_Login() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTexbox(existingEmail);
		loginPage.inputToPasswordTexbox("123456");
		homePage = loginPage.clickToLoginButton();;
        Assert.assertTrue(homePage.isMyAccountLinkDisplay());	

	}

	//@Test
	public void Login_03_My_Account() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isCustomerInfoTitleDisplay());	
	}
	

	//@Test
	public void Login_04_Switch_User() {
		// Customer Info -> Address

		// Address -> My Product Review

		// My Product Review -> Reward Point

		// Reward Point -> Address
		
		// Address -> Reward Point
		
		// Reward Point -> My Product Review
	}

	


	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
