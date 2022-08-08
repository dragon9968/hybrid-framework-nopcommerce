package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{

	private WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		//super();
		this.driver = driver;
	}
	
    public void inputToEmailTextbox(String emailAddress) {
    	waitForElementVisible(driver, AdminLoginPageUI.ADMIN_EMAIL_TEXTBOX);
    	sendkeyToElement(driver, AdminLoginPageUI.ADMIN_EMAIL_TEXTBOX, emailAddress);
	}
	
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.ADMIN_PASSWORD_TEXTBOX);
    	sendkeyToElement(driver, AdminLoginPageUI.ADMIN_PASSWORD_TEXTBOX, password);
	}
	
	public void clickToLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUI.ADMIN_LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.ADMIN_LOGIN_BUTTON);
	}
	
	public AdminDashBoardPageObject loginAsAdmin(String emailAddress , String Password) {
		inputToEmailTextbox(emailAddress);
		inputToPasswordTextbox(Password);
		clickToLoginButton();
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}
}
