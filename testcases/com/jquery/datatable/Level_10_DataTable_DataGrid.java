package com.jquery.datatable;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_10_DataTable_DataGrid extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
    List<String> actualEachRowAtAllPage;

	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName,String appUrl) {
	
        driver = getBrowserName(browserName,appUrl);
		//homePage = new HomePageObject(driver);
        homePage = PageGeneratorManager.getHomePage(driver);

	}

	//@Test
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("10"));
		
		homePage.openPagingByPageNumber("15");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("15"));
		
		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("20"));
		
		homePage.openPagingByPageNumber("5");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("5"));
	}
	
	
	//@Test
	public void Table_02_Enter_To_Header() {
		//homePage.refreshCurrentPage(driver);
		
		homePage.enterToHeaderTextboxByLabel("Country","Chile");
		
		homePage.sleepInSecond(2);
		
		homePage.enterToHeaderTextboxByLabel("Country","Congo");
		
	}
	

	@Test
	public void Table_03_Get_Value_Of_Each_Page() {
		actualEachRowAtAllPage = homePage.getValueEachRowAtAllPage();
		for (String valueEachRow : actualEachRowAtAllPage) {
			System.out.println(valueEachRow);
		}
		
		
	}
	
	public void Table_04_Enter_To_Textbox_At_Any_Row() {
		homePage.enterToTextboxByColumnNameAtRowNumber("Album","1","Michales97");
	}
	

	


	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
