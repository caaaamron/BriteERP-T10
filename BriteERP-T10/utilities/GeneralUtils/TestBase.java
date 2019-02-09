package utilities.GeneralUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.asserts.SoftAssert;
import utilities.PageUtils.OodooPage;
import utilities.PageUtils.SignInPage;

public class TestBase{
	protected static WebDriver driver;
	private static final Logger log = LogManager.getLogger();
	protected static SoftAssert soft = new SoftAssert();

	@BeforeClass
	public void beforeClass() {
		
		log.info("Test Base === Driver Set Up");
		driver = Driver.setUp();
		
		log.info("Test Base === Getting BaseURL");
		driver.get(PropertyConfig.getProperty("baseUrl"));
		
		log.info("Test Base === Clicking BriteERPDemo");
		OodooPage.clickBriteErpDemo();
		
		log.info("Test Base === Clicking login");
		SignInPage.login();
		try {
            Thread.sleep(3000);
        }catch(Exception e){
		    e.printStackTrace();
        }
	}
	
	@AfterClass
	public void afterClass() throws InterruptedException {
		log.info("Test Base === Closing driver");
		soft.assertAll();
		Thread.sleep(5000);
//		Driver.close();
	}
	
	
}
