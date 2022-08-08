package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;
import pageUIs.nopCommerce.admin.DashBoardPageUI;
import pageUIs.nopCommerce.user.HomePageUI;

public class AdminDashBoardPageObject extends BasePage{

	private WebDriver driver;

	public AdminDashBoardPageObject(WebDriver driver) {
		//super();
		this.driver = driver;
	}
	
	public boolean isDashBoardHeaderDisplay() {
		waitForElementVisible(driver, DashBoardPageUI.DASHBOARD_HEADER);
		return isElementDisplayed(driver,  DashBoardPageUI.DASHBOARD_HEADER);
	}
}
