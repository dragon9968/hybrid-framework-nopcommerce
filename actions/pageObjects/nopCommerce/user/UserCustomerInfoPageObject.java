package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.BasePageUI;
import pageUIs.nopCommerce.user.CustomerInfoPageUI;
import pageUIs.nopCommerce.user.HomePageUI;
import pageUIs.nopCommerce.user.LoginPageUI;

public class UserCustomerInfoPageObject extends BasePage {

	private WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		//super();
		this.driver = driver;
	}

	public boolean isCustomerInfoTitleDisplay() {
		waitForElementVisible(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		return isElementDisplayed(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
	}
	
	
}
