package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driverBaseTest;
	//private String projectPath = System.getProperty("user.dir");

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
		driverBaseTest.get("https://demo.nopcommerce.com/");

		return driverBaseTest;
	}
	
	protected int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}
