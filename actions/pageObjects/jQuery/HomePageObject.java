package pageObjects.jQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		// super();
		this.driver = driver;
	}

	public void openPagingByPageNumber(String pageNumber) {
		waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);

	}

	public void enterToHeaderTextboxByLabel(String headerLabel, String value) {
		waitForElementVisible(driver, HomePageUI.HEADER_TEXT_BOX_BY_LABEL, headerLabel);
		sendkeyToElement(driver, HomePageUI.HEADER_TEXT_BOX_BY_LABEL, value, headerLabel);
		// pressKeyToElement_Action(driver, HomePageUI.HEADER_TEXT_BOX_BY_LABEL,
		// Keys.ENTER, headerLabel);
		pressKeyToElement(driver, HomePageUI.HEADER_TEXT_BOX_BY_LABEL, Keys.ENTER, headerLabel);

	}

	public boolean isPageNumberActive(String pageNumber) {
		waitForElementVisible(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
		return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
	}

	public List<String> getValueEachRowAtAllPage() {
		int totalPage = getElementSize(driver, HomePageUI.TOTAL_PAGINATION);
		System.out.println("Total size = " + totalPage);
		List<String> allRowValueAllPage = new ArrayList<String>();

		// Duyệt qa tất cả các Page Number (paging)
		for (int i = 1; i <= totalPage; i++) {

			clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_INDEX, String.valueOf(i));
			System.out.println("Click to Page" + i);

			// Cách 1
			/*
			 * List<WebElement> allRowElementEachPage =
			 * getListWebElement(driver,HomePageUI.ALL_ROW_EACH_PAGE); int eachRow =
			 * getElementSize(driver,HomePageUI.ALL_ROW_EACH_PAGE); for (int j = 0; j <
			 * eachRow; j++) { String valueEachRow = allRowElementEachPage.get(j).getText();
			 * System.out.println("---------------------------------------------");
			 * System.out.println("Row value is " +valueEachRow); }
			 */

			List<WebElement> allRowElementEachPage = getListWebElement(driver, HomePageUI.ALL_ROW_EACH_PAGE);
			for (WebElement eachRow : allRowElementEachPage) {
				
				allRowValueAllPage.add(eachRow.getText());
			}

		}
		
		for (String valueEachRow : allRowValueAllPage) {
			//System.out.println("---------------------------------------------");
			//System.out.println("Row value is " + valueEachRow);

		}
		
		return allRowValueAllPage;
		
	
	}

	public void enterToTextboxByColumnNameAtRowNumber(String columnName, String rowNumber, String textValue) {
		// Column index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName)+1;
		System.out.println(columnIndex);
		
		// Sendkey vào dòng nâò
		waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,String.valueOf(columnIndex));
		sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, textValue, rowNumber,String.valueOf(columnIndex));
	}
	
	public void selectDropDownByColumnNameAtRowNumber(String columnName, String rowNumber, String dropdownValue) {
		// Column index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName)+1;
		System.out.println(columnIndex);
		
		waitForElementClickable(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,String.valueOf(columnIndex));
		selectItemInDefaultDropDown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, dropdownValue, rowNumber,String.valueOf(columnIndex));
	}
	
	public void checkToCheckboxByColumnNameAtRowNumber(String columnName, String rowNumber) {
		// Column index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName)+1;
		System.out.println(columnIndex);
		
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,String.valueOf(columnIndex));
		checkToDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,String.valueOf(columnIndex));
	}
	
	public void uncheckToCheckboxByColumnNameAtRowNumber(String columnName, String rowNumber) {
		// Column index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName)+1;
		System.out.println(columnIndex);
	
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,String.valueOf(columnIndex));
		uncheckToDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,String.valueOf(columnIndex));
	}

	public void clickToLoadButton() {
        waitForElementClickable(driver, HomePageUI.LOAD_DATA_BUTTON);
        clickToElement(driver,  HomePageUI.LOAD_DATA_BUTTON);
	}

	public void clickToIconByRowNumber(String rowNumber, String iconName) {
        waitForElementClickable(driver, HomePageUI.ICON_NAME_BY_ROW_NUMBER, rowNumber,iconName);
        clickToElement(driver, HomePageUI.ICON_NAME_BY_ROW_NUMBER, rowNumber,iconName);
	}
}
