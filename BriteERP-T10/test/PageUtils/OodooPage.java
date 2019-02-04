package test.PageUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.GeneralUtils.Constants;
import test.GeneralUtils.Driver;
import test.GeneralUtils.FailMessages;

public class OodooPage {
	private static WebDriver driver;

	/*
	 * THIS IS THE STARTING PAGE FOR THE ENTIRE WEBSITE YOU MUST START HERE TO GO
	 * FARTHER
	 */

	static {
		driver = Driver.setUp();
		PageFactory.initElements(driver, OodooPage.class);
	}

	@FindBy(xpath = "//a[@href = \"/web?db=BriteErpDemo\"]")
	static WebElement briteErpDemo;

	public static void clickBriteErpDemo() {

		try {
			briteErpDemo.click();
		} catch (Exception e) {
			FailMessages.fail(e);

		}
	}
}
