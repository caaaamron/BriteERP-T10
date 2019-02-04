package PageUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GeneralUtils.Driver;
import GeneralUtils.FailMessages;



public class Homepage{
	private static WebDriver driver;
	
	public Homepage() {
		driver = Driver.setUp();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@href = '/web/login']")
	static WebElement signInButton;

	public static void clickSignIn() {
		try {
			signInButton.click();
		} catch (Exception e) {
			FailMessages.fail(e);
		}
	}
}
