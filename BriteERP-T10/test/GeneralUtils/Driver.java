package GeneralUtils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	protected static WebDriver driver;

	public static WebDriver setUp() {

		String browserType = PropertyConfig.getProperty("browser");
		
		if (driver == null) {
			try {
				switch (browserType) {

				case "chrome":
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					break;

				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					break;

				case "ie":
					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					break;
				}
			} catch (Exception e) {
				System.out.println("Driver setup error");
				e.printStackTrace();
			}
		}
		return driver;
	}
	
	public static void wait(int time) {
		try {
			Thread.sleep(time);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close() {
		if(driver != null) {
			driver.close();
			driver = null;
		}
	}
	
	public static void quit() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
