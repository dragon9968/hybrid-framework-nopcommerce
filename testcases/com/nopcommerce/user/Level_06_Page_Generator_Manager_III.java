package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_06_Page_Generator_Manager_III extends BaseTest {
	private WebDriver driver;
	private String existingEmail, invalidEmail, notFoundEmail, firstName, lastName;
	//private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	
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
		

		registerPage = homePage.openRegisterPage();

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

	@Test
	public void Login_01_Empty_Data() {
		
		// Từ trang Home - Click Login link -> Qa trang Login
		loginPage = homePage.openLoginPage();
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTexbox(invalidEmail);

     	loginPage.clickToLoginButton();

    	Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@Test
	public void Login_03_Email_Not_Found() {
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailTexbox(notFoundEmail);
		
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getUnsuccessfullErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailTexbox(existingEmail);
		loginPage.inputToPasswordTexbox("");
		
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getUnsuccessfullErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailTexbox(existingEmail);
		loginPage.inputToPasswordTexbox("123457");
		
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getUnsuccessfullErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

    @Test
	public void Login_06_Valid_Login() {
		
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailTexbox(existingEmail);
		loginPage.inputToPasswordTexbox("123456");
		
		//Login thành công -> Home Page
		homePage = loginPage.clickToLoginButton();;
        Assert.assertTrue(homePage.isMyAccountLinkDisplay());
        
       
        //homePage.clickToMyAccountLink();
        //myAcountPage = new MyAccountPageObject(driver);
        
        customerInfoPage = homePage.openMyAccountPage();
        
        
        Assert.assertTrue(customerInfoPage.isCustomerInfoTitleDisplay());	

	}
	


	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
