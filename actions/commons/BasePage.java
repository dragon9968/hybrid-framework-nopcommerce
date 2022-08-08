package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.Advice.Return;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserChangePassPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserDownloadProductsPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserOrdersPageObject;
import pageObjects.nopCommerce.user.UserRewardPageObject;
import pageObjects.nopCommerce.user.UserStockPageObject;
import pageUIs.nopCommerce.user.BasePageUI;

public class BasePage {
	
	
	public static BasePage getBasePage() {
		return new BasePage();
		
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backtoPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardtoPage(WebDriver driver, String pageUrl) {
		driver.navigate().forward();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptToAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
		// driver.switchTo().alert().accept();
	}

	public void cancelToAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(tabTitle)) {
				break;
			}
		}
	}
	
	public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}
	
	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	
	
	
	private WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	
	private List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}
	
	public void clickToElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver,xpathLocator).click();
		
	}
	
	public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver,xpathLocator);
		element.clear();
		element.sendKeys(textValue);
		
	}
	
	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver,xpathLocator).getText();

	}
	
	public void selectItemInDefaultDropDown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		select.selectByValue(textItem);
	}
	
	public String getSelectItemDefaultDropDown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropDownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		return select.isMultiple();
	}
	
	
	public void selectItemInCustomDropDown(WebDriver driver , String parentXpath , String childXpath , String expectedTextItem){
		getWebElement(driver,parentXpath).click();
        sleepInSecond(1);
        WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
    	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    	
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));  
        List<WebElement> alldropdownItems = driver.findElements(getByXpath(childXpath));
        for (WebElement item : alldropdownItems) {
        	if(item.getText().trim().equals(expectedTextItem)) 
        	{
        	   jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
               sleepInSecond(1);
        	   item.click();
        	   break;
        	}
		}
	}
	
	public String getElementAttributeValue(WebDriver driver, String xpathLocator , String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String xpathLocator , String cssValueName) {
		return getWebElement(driver, xpathLocator).getCssValue(cssValueName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
		
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}
	
	
	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}
	
	
	public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}
	
	
	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}
	
 	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action =  new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}
	
	

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	

	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}



	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,xpathLocator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}
	
	
	private long longtimeout = 30;
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
		return new UserCustomerInfoPageObject(driver);
		
	}
	public UserAddressesPageObject openAddressesPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESSES_LINK);
		clickToElement(driver, BasePageUI.ADDRESSES_LINK);
		return new UserAddressesPageObject(driver);
		//return PageGeneratorManager.getAddressesPagePage(driver);
	}


	public UserOrdersPageObject openOrderPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ORDERS_LINK);
		clickToElement(driver, BasePageUI.ORDERS_LINK);
		return new UserOrdersPageObject(driver);
		
	}
	
	public UserDownloadProductsPageObject openDownLoadProductPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.DOWNLOAD_PRODUCTS_LINK);
		clickToElement(driver, BasePageUI.DOWNLOAD_PRODUCTS_LINK);
		return new UserDownloadProductsPageObject(driver);
		
	}
	
	public UserStockPageObject openStockPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.STOCK_LINK);
		clickToElement(driver, BasePageUI.STOCK_LINK);
		return new UserStockPageObject(driver);
		
	}
	
	public UserRewardPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
		return new UserRewardPageObject(driver);
	}
	
	public UserChangePassPageObject openChangePassPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CHANGE_PASS_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
		return new UserChangePassPageObject(driver);
	}
	
	public UserMyProductReviewPageObject openMyProductPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		return new UserMyProductReviewPageObject(driver);
	}
	
}

