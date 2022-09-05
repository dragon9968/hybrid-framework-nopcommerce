package pageUIs.jQuery;

public class HomePageUI {
	public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String PAGINATION_PAGE_ACTIVED_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text() ='%s']";
	public static final String HEADER_TEXT_BOX_BY_LABEL = "xpath=//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
	public static final String TOTAL_PAGINATION = "css=ul.qgrd-pagination-ul>li.qgrd-pagination-page";
	public static final String PAGINATION_PAGE_BY_INDEX = "xpath=//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page'][%s]/a";
	public static final String ALL_ROW_EACH_PAGE = "xpath=//tbody/tr";
	
	public static final String COLUMN_INDEX_BY_NAME = "//tr/td[text()='%s']//preceding-sibling::td";
	public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody/tr['%s']/td['%s']/input";

	//tbody/tr[1]/td[2]/input
}
