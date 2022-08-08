package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUIs.nopCommerce.user.HomePageUI;
import pageUIs.nopCommerce.user.LoginPageUI;

public class HomePageObjectFactory extends BasePageFactory {

	private WebDriver driver;

	public HomePageObjectFactory(WebDriver driver) {
		// super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='ico-register']")
	private WebElement REGISTER_LINK;

	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement LOGIN_LINK;

	@FindBy(xpath = "//a[@class='ico-account']")
	private WebElement MY_ACCOUNT_LINK;

	public void clickToRegisterLink() {
		waitForElementClickable(driver, REGISTER_LINK);
		clickToElement(driver, REGISTER_LINK);
	}

	public void clickToLoginLink() {
		waitForElementClickable(driver, LOGIN_LINK);
		clickToElement(driver, LOGIN_LINK);

	}

	public boolean isMyAccountLinkDisplay() {
		waitForElementVisible(driver, MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, MY_ACCOUNT_LINK);
	}

}