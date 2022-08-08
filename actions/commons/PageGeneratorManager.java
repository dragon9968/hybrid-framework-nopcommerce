package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.nopCommerce.admin.AdminDashBoardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserChangePassPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserDownloadProductsPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserOrdersPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPageObject;
import pageObjects.nopCommerce.user.UserStockPageObject;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;

public class PageGeneratorManager {

	public static UserHomePageObject getHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	
	public static UserRegisterPageObject getRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
	public static UserLoginPageObject getLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	
	public static UserCustomerInfoPageObject getMyAccountPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}
	
	public static UserAddressesPageObject getAddressesPagePage(WebDriver driver) {
		return new UserAddressesPageObject(driver);
	}
	
	public static UserOrdersPageObject getOrderPage(WebDriver driver) {
		return new UserOrdersPageObject(driver);
	}
	
	public static UserDownloadProductsPageObject getDownloadProductsPage(WebDriver driver) {
		return new UserDownloadProductsPageObject(driver);
	}
	
	public static UserStockPageObject getStockPage(WebDriver driver) {
		return new UserStockPageObject(driver);
	}
	
	public static UserRewardPageObject getRewardPage(WebDriver driver) {
		return new UserRewardPageObject(driver);
	}

	public static UserChangePassPageObject getChangePassPage(WebDriver driver) {
		return new UserChangePassPageObject(driver);
	}

	public static UserMyProductReviewPageObject getMyProductReviewPage(WebDriver driver) {
		return new UserMyProductReviewPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	public static AdminDashBoardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashBoardPageObject(driver);
	}

}
