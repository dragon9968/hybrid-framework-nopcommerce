package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminDashBoardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
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
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_08_Switch_Role extends BaseTest {
	private WebDriver driver;
	private String userEmail , userPassword , adminEmail , adminPassword;
	//private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashBoardPageObject adminDashBoardPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressesPageObject addressPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
	
        driver = getBrowserName(browserName);
		//homePage = new HomePageObject(driver);
        userHomePage = PageGeneratorManager.getHomePage(driver);
		
		userEmail = "long" + generateRandomNumber() + "@qa.team";
		userPassword = "123456";
		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
	
		userRegisterPage = userHomePage.openRegisterPage();

		System.out.println("Pre-Condition - Step 02: Input to required fields");
		userRegisterPage.inputToFirstnameTextbox("firstName");
		userRegisterPage.inputToLastnameTextbox("lastName");
		userRegisterPage.inputToEmailTextbox(userEmail);
		userRegisterPage.inputToPasswordTextbox(userPassword);
		userRegisterPage.inputToConfirmPasswordTextbox(userPassword);
		System.out.println("Email registered is " + userEmail);
		userRegisterPage.clickToRegisterButton();
		userHomePage = userRegisterPage.clickToLogoutLink();
	}

	@Test
	public void Role_01_User_To_Admin() {
		userLoginPage = userHomePage.openLoginPage();
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplay());
	    userHomePage.clickToUserLogoutLink(driver);
	    userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
	    
	    //Login As Admin Role
	    adminDashBoardPage= adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
	    
	    // AdminDashBoardPage = PageGeneratorManager.getAdminDashboardPage(driver);   
	    Assert.assertTrue(adminDashBoardPage.isDashBoardHeaderDisplay());
	    
	    // Dashboard Page -> Logout -> Login Page
	    adminDashBoardPage.clickToAdminLogoutLink(driver);

	}
	
	@Test
	public void Role_02_Admin_To_User() {
		//Login Page (Admin) -> Open User url -> Home Page (User)
		adminLoginPage.openPageUrl(driver, GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getHomePage(driver);
		
		//Home Pagê -> Login Page (User)
		userLoginPage = userHomePage.openLoginPage();
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

		Assert.assertTrue(userHomePage.isMyAccountLinkDisplay());
		
		customerInfoPage = userHomePage.openMyAccountPage();
		sleepInSecond(2);
		addressPage = customerInfoPage.openAddressesPage(driver);


		
		
	}
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
