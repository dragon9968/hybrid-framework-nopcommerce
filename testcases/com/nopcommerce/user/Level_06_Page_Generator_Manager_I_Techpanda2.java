package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserHomePageObject_Techpanda;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject_Techpanda;
import pageUIs.nopCommerce.user.RegisterPageUI_Techpanda;
import pageObjects.nopCommerce.user.UserLoginPageObject_Techpanda;

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

public class Level_06_Page_Generator_Manager_I_Techpanda2 extends BaseTest{
	private WebDriver driver_Test_Case;
	//private String emailAddress, password;
	//private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject_Techpanda homePage;
	private UserRegisterPageObject_Techpanda registerPage;
	private UserLoginPageObject_Techpanda loginPage;
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
		//emailAddress = "long" + generateRandomNumber() + "@qa.team";

	}

	@Test
	public void Login_06_Valid_Login() {
		//System.out.println("Register_01 - Step 01: Click to Register link");
		homePage.openLoginPage();

		// Click Register Link nhảy qa trang Register
        loginPage = new UserLoginPageObject_Techpanda(driver_Test_Case);
        //registerPage = PageGeneratorManager.getRegisterPageTechPanda(driver_Test_Case);
		//System.out.println("Register_01 - Step 02: Click Register button ");
        loginPage.inputToEmailTexbox(Level_06_Page_Generator_Manager_I_Techpanda1.emailAddress);
        loginPage.inputToPasswordTexbox("123456");
        loginPage.clickToLoginButton();
		//System.out.println("Register_01 - Step 03: Verify error message displayed");
		//Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "This is a required field.");
		//Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "This is a required field.");
		//Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "This is a required field.");
		//Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "This is a required field.");
		//Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "This is a required field.");
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
