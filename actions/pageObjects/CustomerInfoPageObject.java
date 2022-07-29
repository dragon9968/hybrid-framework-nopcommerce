package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;
import pageUIs.LoginPageUI;
import pageUIs.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {

	private WebDriver driver;

	public CustomerInfoPageObject(WebDriver driver) {
		//super();
		this.driver = driver;
	}

	public boolean isCustomerInfoTitleDisplay() {
		waitForElementVisible(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		return isElementDisplayed(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
	}
	
	
	
}
