package com.facebook.register;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_13_Element_Undisplayed extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserName(browserName,appUrl);
        loginPage = PageGeneratorManager.getLoginPage(driver);   
	}

	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		//Verify True - mong đợi Confirm Email hiển thị (true)
	    loginPage.enterToEmailAddressTextbox("longnguyen@qa.team");
	    sleepInSecond(1);
	    verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());	}
	
	@Test
	public void TC_02_Verify_Element_Undisplayed_In_DOM() {

		// Verify False - mong đợi Confirm Email ko hiển thị (false)
		loginPage.enterToEmailAddressTextbox("");
		sleepInSecond(1);
		// verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());

	}
	
	@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(1);
		
		// Khi close cái form register thì confirm email ko còn trong DOM nữa
		// Nên hàm findElements sẽ bị fail vì ko tìm thấy Element
		// 1 - Nó sẽ chờ hết timeout của implicit :20s
		// 2 - Nó sẽ đánh failed test case tại đúng step này lun
		// 3 - Không chạy các step còn lại nữa
		// isDisplayed - bản chất ko kiểm tra 1 element ko có trong DOM dc

	    //verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
        verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	}
	


	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
