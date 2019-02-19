package utilities.PageUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.GeneralUtils.Constants;
import utilities.GeneralUtils.Driver;
import utilities.GeneralUtils.FailMessages;

import java.util.List;

public class ChannelsTabPage {
	private static WebDriver driver;
	private static WebDriverWait wait;

	static {
		driver = Driver.setUp();
		wait = new WebDriverWait(driver, 10);
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

	public static void removeChannel(String name){
        try {
            WebElement channel =
                    driver.findElement(By.xpath("//span[text() = '" + name + "']//parent::h4//parent::div//parent::div[@class = \"oe_module_vignette oe_kanban_global_click o_kanban_record\"]"));
                    channel.click();
        }catch (Exception e){
            FailMessages.fail(e);
            e.printStackTrace();
        }
	}

	public static void joinChannel(String name) {
        try {
            WebElement temp = driver.findElement(By.xpath("//h4[@class = 'o_kanban_record_title']//span[text() = '" + name + "']//parent::h4//following-sibling::button"));
            temp.click();
            System.out.println("Channel: " + name + " clicking");
        }catch (Exception e){
            FailMessages.fail(e);
        }
    }



	public static boolean hasAllChannels() {

		if (getUrl().equalsIgnoreCase(Constants.CHANNELTAB_ENVI) || getUrl().equalsIgnoreCase(Constants.CHANNELTAB_ENVI2)) {
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

	public static void goToChannelsEnvironment() {
		try {
			publicChannelsLink.click();
			wait.until(ExpectedConditions.urlContains("mail.channel"));
		} catch (Exception e) {
			FailMessages.fail(e);
		}
	}

	public static void goToDiscussEnvironment(){
		driver.navigate().to(Constants.DISCUSS_ENVI);
	}

	public static String getUrl() {
		return driver.getCurrentUrl();
	}

	public static String getTitle(){
		return driver.getTitle();
	}

	public static boolean hasCorrectUrl(){
        System.out.println("ChannelTabPage Current URL: "  + driver.getCurrentUrl());
		if(driver.getCurrentUrl().contains("mail.channel")){
		    return true;
        }
		return false;
	}
}
