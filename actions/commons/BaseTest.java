package commons;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driverBaseTest;
	protected final Log log;
	
	@BeforeSuite
	public void deleteAllFilesInReportNGScreenshot() {
		System.out.println("---------- START delete file in folder ----------");
		deleteAllFileInFolder();
		System.out.println("---------- END delete file in folder ----------");
	}

	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
		
	}
	
	public WebDriver getBrowserName(String browserName) {
		if (browserName.equalsIgnoreCase("fireFox")) {
			System.setProperty("webdriver.gecko.driver", GlobalConstants.PROJECT_PATH + "\\browserDrivers\\geckodriver.exe");
			
			//Dùng thư viện WebDriverManager để tải browser Driver về một cách tự động
			//WebDriverManager.firefoxdriver().setup();
			driverBaseTest = new FirefoxDriver();
		} else if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", GlobalConstants.PROJECT_PATH + "\\browserDrivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			
			//WebDriverManager.chromedriver().setup();
			driverBaseTest = new ChromeDriver(options);
		} else if(browserName.equalsIgnoreCase("edge")) {
			//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			
			WebDriverManager.edgedriver().setup();
			driverBaseTest = new EdgeDriver();

	    } else if(browserName.equalsIgnoreCase("ie")) {
		//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		
		WebDriverManager.iedriver().arch32().setup();
		driverBaseTest = new InternetExplorerDriver();
	    } 
		
		
		else {
			throw new RuntimeException("Browser name is invalid");
		}
		
		driverBaseTest.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driverBaseTest.get(GlobalConstants.TECHPANDA_PAGE_URL);
		driverBaseTest.manage().window().maximize();

		
		return driverBaseTest;
	}

	public WebDriver getBrowserName(String browserName , String environmentName) {
		if (browserName.equalsIgnoreCase("fireFox")) {
			System.setProperty("webdriver.gecko.driver", GlobalConstants.PROJECT_PATH + "\\browserDrivers\\geckodriver.exe");
			
			//Dùng thư viện WebDriverManager để tải browser Driver về một cách tự động
			//WebDriverManager.firefoxdriver().setup();
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
		driverBaseTest.get(getEnvironmentUrl(environmentName));
		
		return driverBaseTest;
	}
	
	public WebDriver getBrowserName(String browserName , String environmentName, String osName, String ipAddress, String portNumber) {
		DesiredCapabilities capability = null;
		Platform platform = null;
		
		if (osName.contains("windows")) {
			platform = Platform.WINDOWS;
		} else {
			platform = Platform.MAC;
		}
		
		switch (browserName) {
		case "firefox" :
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(platform);
			
			FirefoxOptions fOptions = new FirefoxOptions();
			fOptions.merge(capability);
			break;
		case "chrome" :
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(platform);
			
			ChromeOptions cOptions = new ChromeOptions();
			cOptions.merge(capability);
			break;
		case "edge" :
			capability = DesiredCapabilities.edge();
			capability.setBrowserName("edge");
			capability.setPlatform(platform);
			
			EdgeOptions eOptions = new EdgeOptions();
			eOptions.merge(capability);
			break;
		default :
			throw new RuntimeException("Browser is not valid!");
		}
		
		try {
			driverBaseTest = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driverBaseTest.get(getEnvironmentUrl(environmentName));
		
		return driverBaseTest;
	}
	
	
	public WebDriver getBrowserName_CloudTesting(String url, String osName, String osVersion , String browserName) {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("os", osName);
		caps.setCapability("os_version", osVersion);
		caps.setCapability("browser", browserName);
		caps.setCapability("browser_version", "latest");
		caps.setCapability("resolution", "1920x1080");
		caps.setCapability("browserstack.debug", "true");
		caps.setCapability("project", "NoCommerce");
		caps.setCapability("name","Run on" + osName + "|" + browserName );

		try {
			driverBaseTest = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), caps);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driverBaseTest.get(url);
		
		return driverBaseTest;
	}
	
	public WebDriver getDriverInstance() {
		return this.driverBaseTest;
	}
	
	protected String getEnvironmentUrl(String envName) {
		String envUrl = null ;
		EnvironmentList environment = EnvironmentList.valueOf(envName.toUpperCase());
		if(environment == EnvironmentList.DEV)
		{
			envUrl = "https://demo.nopcommerce.com/";
		}
		else if (environment == EnvironmentList.TESTING) {
			envUrl = "https://admin-demo.nopcommerce.com/";	
		}
		else if (environment == EnvironmentList.STAGING) {
			envUrl = "https://vnexpress.net/";	
		}
		else if (environment == EnvironmentList.PRODUCTION) {
			envUrl = "https://production.orangehrmlive.com/";	
		}	
		return envUrl;	
	}
	
	public boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");

		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
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
			log.info(" -------------------------- PASSED -------------------------- ");
			
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
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
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public void deleteAllFileInFolder() {
		try {
			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
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
	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driverBaseTest.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driverBaseTest != null) {
				//IE: lưu lại các phiên đăng nhập trước đó (Khác Thread)
				driverBaseTest.manage().deleteAllCookies();
				driverBaseTest.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
