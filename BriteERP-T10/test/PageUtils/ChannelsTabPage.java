package PageUtils;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GeneralUtils.Constants;
import GeneralUtils.Driver;
import GeneralUtils.FailMessages;

public class ChannelsTabPage {
	private static WebDriver driver;

	static {
		driver = Driver.setUp();
		PageFactory.initElements(driver, ChannelsTabPage.class);
	}

	@FindBy(xpath = "//button[@accesskey = 'c']")
	private static WebElement channelAdd;

	@FindAll({ @FindBy(xpath = "//h4[@class = 'o_kanban_record_title']//span") })
	private static List<WebElement> allChannelsName;

	@FindBy(xpath = "//input[@placeholder = 'Name']")
	private static WebElement channelName;

	@FindBy(xpath = "//button[@accesskey = 's']")
	private static WebElement saveChannel;

	@FindBy(xpath = "//a[text() = 'Public Channels']")
	private static WebElement publicChannelsLink;

	@FindAll({ @FindBy(xpath = "//div[@class = 'oe_module_vignette oe_kanban_global_click o_kanban_record']") })
	private static List<WebElement> channels;

	public static boolean containsChannel(String channel) {
		for (WebElement each : allChannelsName) {
			if (each.getText().equalsIgnoreCase(channel)) {
				System.out.println("\"" + channel + "\" is an existing channel.");
				return true;
			}
		}
		return false;
	}

	public static void addChannel(String name) {
		try {
			if (!containsChannel(name)) {
				System.out.println("Adding channel");
				channelAdd.click();
				Thread.sleep(2000);
				channelName.sendKeys(name);
				saveChannel.click();

			}
		} catch (Exception e) {
			FailMessages.fail(e);
		}
	}

	public static boolean hasAllChannels() {

		if (getUrl().equals(Constants.CHANNELTAB_ENVI)) {
			int count = 0;
			for (WebElement each : channels) {
				if (each.isDisplayed())
					count++;
				if (count == channels.size()) {
					return true;
				}
			}
		}
		return false;
	}

	public static void goToChannelsHome() {
		try {
			publicChannelsLink.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			FailMessages.fail(e);
		}
	}

	public static String getUrl() {
		return driver.getCurrentUrl();
	}
}
