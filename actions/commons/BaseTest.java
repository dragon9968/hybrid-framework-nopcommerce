package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driverBaseTest;
	protected final Log log;
	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
		
	}
	
	public WebDriver getBrowserName(String browserName) {
		if (browserName.equalsIgnoreCase("fireFox")) {
			//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			
			//Dùng thư viện WebDriverManager để tải browser Driver về một cách tự động
			WebDriverManager.firefoxdriver().setup();
			driverBaseTest = new FirefoxDriver();
		} else if(browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			
			WebDriverManager.chromedriver().setup();
			driverBaseTest = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("edge")) {
			//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			
			WebDriverManager.edgedriver().setup();
			driverBaseTest = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name is invalid");
		}
		
		driverBaseTest.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driverBaseTest.get(GlobalConstants.PORTAL_PAGE_URL);
		
		return driverBaseTest;
	}

	public WebDriver getBrowserName(String browserName , String appUrl) {
		if (browserName.equalsIgnoreCase("fireFox")) {
			//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

            //Dùng thư viện WebDriverManager để tải browser Driver về một cách tự động
			WebDriverManager.firefoxdriver().setup();
			driverBaseTest = new FirefoxDriver();
		} else if(browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			
			WebDriverManager.chromedriver().setup();
			driverBaseTest = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("edge")) {
			//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			
			WebDriverManager.edgedriver().setup();
			driverBaseTest = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name is invalid");
		}
		
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driverBaseTest.get(appUrl);

		return driverBaseTest;
	}
	
	public boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");

		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}


	public boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
			
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	

	public boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			System.out.println(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}


	
	
	protected int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
