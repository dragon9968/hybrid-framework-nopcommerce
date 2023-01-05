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
import com.nopcommerce.data.UserData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_20_Manage_Data_Part_I extends BaseTest {
	private WebDriver driver;
	private String Email;
	//private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;

	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
	
        driver = getBrowserName(browserName);
        System.out.println("Driver is" + driver );
		//homePage = new HomePageObject(driver);
        homePage = PageGeneratorManager.getHomePage(driver);
		

		
		Email = UserData.Register.EMAIL + generateRandomNumber() + "@qa.team";
		
	

		System.out.println("Pre-Condition - Step 01: Click to Register link");

	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to 'Register' Page");
		registerPage = homePage.openRegisterPage();

		log.info("Register - Step 02: Enter to Firstname textbox with value is '" + UserData.Register.FIRST_NAME + "'");
		registerPage.inputToTextboxByID(driver,"FirstName",  UserData.Register.FIRST_NAME);
		
		log.info("Register - Step 03: Enter to Lastname textbox with value is '" + UserData.Register.LAST_NAME + "'");
		registerPage.inputToTextboxByID(driver,"LastName", UserData.Register.LAST_NAME);
		
		log.info("Register - Step 04: Enter to Email textbox with value is '" + Email + "'");
		registerPage.inputToTextboxByID(driver,"Email", Email);

		log.info("Register - Step 05: Enter to Password textbox with value is '" + UserData.Register.PASSSWORD + "'");
		registerPage.inputToTextboxByID(driver,"Password", UserData.Register.PASSSWORD);

		log.info("Register - Step 06: Enter to Confirm Password textbox with value is '" + UserData.Register.PASSSWORD + "'");
		registerPage.inputToTextboxByID(driver,"ConfirmPassword", UserData.Register.PASSSWORD);

		log.info("Register - Step 07: Click to 'Register' button");
		//registerPage.clickToRegisterButton();
		registerPage.clickToButtonByText(driver, "Register");
		
		log.info("Register - Step 08: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Register - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
		
		
	}
	
	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Navigate to 'Login' Page");
		loginPage = homePage.openLoginPage();
		
		log.info("Login - Step 02: Enter to Email textbox with value is '" +Email + "'");
		//loginPage.inputToEmailTexbox(existingEmail);
		loginPage.inputToTextboxByID(driver, "Email", Email);
		
		log.info("Login - Step 03: Enter to Password textbox with value is '" + UserData.Register.PASSSWORD + "'");
		//loginPage.inputToPasswordTexbox(password);
		loginPage.inputToTextboxByID(driver, "Password", UserData.Register.PASSSWORD);
		
		log.info("Login - Step 04: Click to 'Login' button");
		//homePage = loginPage.clickToLoginButton();
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		log.info("Login - Step 05: Verify 'My Account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplay());	

		log.info("Login - Step 06: Navigate to 'My Account' Page");
		customerInfoPage = homePage.openMyAccountPage();
		customerInfoPage.scrollToBottomPage(driver);
		
		log.info("Login - Step 07: Verify 'Customer Info' title is displayed");
		verifyTrue(customerInfoPage.isCustomerInfoTitleDisplay());
		
		
		
	}
		
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
