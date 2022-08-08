package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class RegisterPageObjectFactory extends BasePageFactory{
	private WebDriver driver;

	public RegisterPageObjectFactory(WebDriver driver) {
		//super();
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement FIRST_NAME_TEXTBOX;
	
	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement LAST_NAME_TEXTBOX;
	
	@FindBy(xpath = "//input[@id='Email']")
	private WebElement EMAIL_TEXTBOX;
	
	@FindBy(xpath = "//input[@id='Password']")
	private WebElement PASSWORD_TEXTBOX;
	
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	private WebElement CONFIRM_PASSWORD_TEXTBOX;
	
	@CacheLookup //sử dụng Annotation này để tìm 1 lần rồi sử dụng lại thay vì fai tìm nhiều lần
	@FindBy(xpath = "//button[@id='register-button']")
	private WebElement REGISTER_BUTTON;
	
	@FindBy(xpath = "//span[@id='FirstName-error']")
	private WebElement FIRST_NAME_ERROR_MESSAGE;
	
	@FindBy(xpath = "//span[@id='LastName-error']")
	private WebElement LAST_NAME_ERROR_MESSAGE;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement EMAIL_ERROR_MESSAGE;
	
	@FindBy(xpath = "//span[@id='Password-error']")
	private WebElement PASSWORD_ERROR_MESSAGE;
	
	@FindBy(xpath = "//span[@id='ConfirmPassword-error']")
	private WebElement CONFIRM_PASSWORD_ERROR_MESSAGE;
	
	@FindBy(xpath = "//div[@class='result']")
	private WebElement REGISTER_SUCCESS_MESSAGE;
	
	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement LOGOUT_BUTTON;
	
	@FindBy(xpath = "//div[contains(@class,'message-error')]//li")
	private WebElement EXISTING_EMAIL_ERROR_MESSAGE;
	
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, REGISTER_BUTTON);
		clickToElement(driver, REGISTER_BUTTON);
 		
	}

	public String getErrorMessageAtFirstnameTextbox() {
        waitForElementVisible(driver, FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, FIRST_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtLastnameTextbox() {
        waitForElementVisible(driver, LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, LAST_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtEmailTextbox() {
        waitForElementVisible(driver, EMAIL_ERROR_MESSAGE);
		return getElementText(driver, EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageAtPasswordTextbox() {
        waitForElementVisible(driver, PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
        waitForElementVisible(driver, CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	public void inputToFirstnameTextbox(String firstName) {
		waitForElementVisible(driver, FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, FIRST_NAME_TEXTBOX, firstName);
	}

	public void inputToLastnameTextbox(String lastName) {
		waitForElementVisible(driver, LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, LAST_NAME_TEXTBOX, lastName);		
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, EMAIL_TEXTBOX);
		sendkeyToElement(driver, EMAIL_TEXTBOX, emailAddress);				
	}

	public void inputToPasswordTextbox(String Password) {
		waitForElementVisible(driver, PASSWORD_TEXTBOX);
		sendkeyToElement(driver, PASSWORD_TEXTBOX, Password);					
	}

	public void inputToConfirmPasswordTextbox(String ConfirmPassword) {
		waitForElementVisible(driver, CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, CONFIRM_PASSWORD_TEXTBOX, ConfirmPassword);			
	}

	public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, REGISTER_SUCCESS_MESSAGE);
	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, LOGOUT_BUTTON);
		clickToElement(driver, LOGOUT_BUTTON);
	}

	public String getErrorExistingEmailMessage() {
        waitForElementVisible(driver, EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, EXISTING_EMAIL_ERROR_MESSAGE);
	}



}
