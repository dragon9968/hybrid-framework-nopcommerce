package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUIs.nopCommerce.user.HomePageUI;
import pageUIs.nopCommerce.user.LoginPageUI;

public class LoginPageObjectFactory extends BasePageFactory {
	private WebDriver driver;

	public LoginPageObjectFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='Email']")
	private WebElement EMAIL_TEXTBOX;
	
	@FindBy(xpath = "//input[@id='Password']")
	private WebElement PASSWORD_TEXTBOX;
	
	@FindBy(xpath = "//button[contains(@class,'login-button')]")
	private WebElement LOGIN_BUTTON;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement EMAIL_ERROR_MESSAGE;
	
	@FindBy(xpath = "//div[contains(@class,'validation-summary-errors')]")
	private WebElement LOGIN_UNSUCCESSFULL_ERROR_MESSAGE;
	
	
	public void clickToLoginButton() {
		waitForElementClickable(driver, LOGIN_BUTTON);
		clickToElement(driver, LOGIN_BUTTON);
	}

	public void inputToEmailTexbox(String Email) {
		waitForElementVisible(driver, EMAIL_TEXTBOX);
		sendkeyToElement(driver, EMAIL_TEXTBOX, Email);
	}

	public void inputToPasswordTexbox(String Password) {
		waitForElementVisible(driver, PASSWORD_TEXTBOX);
		sendkeyToElement(driver, PASSWORD_TEXTBOX, Password);
	}

	public String getUnsuccessfullErrorMessage() {
		waitForElementVisible(driver, LOGIN_UNSUCCESSFULL_ERROR_MESSAGE);
		return getElementText(driver, LOGIN_UNSUCCESSFULL_ERROR_MESSAGE);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, EMAIL_ERROR_MESSAGE);
		return getElementText(driver, EMAIL_ERROR_MESSAGE);
	}

}
