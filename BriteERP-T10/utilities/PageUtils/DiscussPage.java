package utilities.PageUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.GeneralUtils.Driver;
import utilities.GeneralUtils.FailMessages;

import java.util.Arrays;
import java.util.List;

public class DiscussPage {
	private static WebDriver driver;
	private static List<String> allChannelsName;

	static {
		driver = Driver.setUp();
		PageFactory.initElements(driver, DiscussPage.class);
	}

	@FindBy(className = "o_searchview_input")
	private static WebElement searchBox;

	@FindBy(xpath = "//span[@title = 'Advanced Search...']")
	private static WebElement searchBoxAdvanceSearch;

	@FindBy(id = "//div[@data-channel-id = 'channel_inbox']")
	private static WebElement inboxTab;

	@FindBy(xpath = "//div[@data-channel-id = 'channel_starred']")
	private static WebElement starredTab;

	@FindBy(xpath = "//b[text() = 'Channels']")
	private static WebElement channelsTab;

	@FindBy(xpath = "//h4[@class = 'o_mail_open_channels']//following-sibling::span")
	private static WebElement channelAdd;

	@FindBy(xpath = "//input[@placeholder = 'Add a channel']")
	private static WebElement channelAddName;

	@FindBy(className = "ui-menu-item")
	private static WebElement channelHiddenCreate;


	@FindAll( {@FindBy(className = "o_channel_name")})
	private static List<WebElement> channelNames;

	@FindAll({ @FindBy(className = "o_mail_chat_channel_item ") })
	private static List<WebElement> readChannels;

	@FindAll({ @FindBy(xpath = "//div[@class = 'o_mail_chat_channel_item  o_unread_message ']") })
	private static List<WebElement> unreadChannels;

	@FindBy(xpath="//span[@data-type='dm']")
	private static WebElement dmAdd;

	public static void search(String text) {
		searchBox.sendKeys(text);
		searchBox.submit();
	}

	public static void advancedSearchClick() {
		try {
			starredTab.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			FailMessages.fail(e);
		}
	}

	public static void clickInboxTab() {
		try {
			inboxTab.click();
		} catch (Exception e) {
			FailMessages.fail(e);
		}

	}

	public static void clickStarredTab() {
		try {
			starredTab.click();
		} catch (Exception e) {
			FailMessages.fail(e);
		}
	}

	public static void clickChannelsTab() {
	    WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			System.out.println("Clicking channels tab");
			channelsTab.click();
			wait.until(ExpectedConditions.urlContains("mail.channel"));
		} catch (Exception e) {
			FailMessages.fail(e);
		}
	}

	public static boolean containsChannel(String channel) {
		boolean contains = false;
		String name = "";

		try {
			WebElement activeChannel = driver
					.findElement(By.xpath("//div[@class = 'o_mail_chat_channel_item o_active']"));
			name = activeChannel.getAttribute("title").trim();
			if (name.equalsIgnoreCase(channel)) {
				contains = true;
				return true;
			}
		} catch (Exception e) {
		
		}
		if (contains == false) {
			for (WebElement each : readChannels) {
				name = each.getAttribute("title").trim();
				if (name.equalsIgnoreCase(channel)) {
					return true;
				}
			}
		}

		if (contains == false) {
			for (WebElement each2 : unreadChannels) {
				name = each2.getAttribute("title").trim();
				if (name.equalsIgnoreCase(channel)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void addChannel(String name) {
		if (!(containsChannel(name))) {
			try {
				System.out.println("Adding channel: " + name);
				channelAdd.click();
				channelAddName.sendKeys(name);
				channelHiddenCreate.click();

				Driver.wait(2);
			} catch (Exception e) {
				FailMessages.fail(e);
			}
		}else{
			System.out.println("Channel: " + name + " not added.");
		}
	}

	public static void addDm(){
		dmAdd.click();
	}

    public static void removeChannel(String channel){

	    try {
            System.out.println("Removing channel: " + channel);
            String html = driver.findElement(By.xpath("//div[@title = '" + channel + "']")).getAttribute("innerHTML");
            int id = html.indexOf("data-channel-id=");

            String id2 = html.substring(id);
            int id3 = id2.indexOf(" ");

            id2 = id2.replace(id2.substring(id3), "").trim();
            int id4 = id2.indexOf("\"") + 1;

            String id5 = id2.substring(id4, id2.length() - 1);
            Integer finalId = Integer.valueOf(id5);

            WebElement remove = driver.findElement(By.xpath("//span[@data-channel-id = '" + finalId + "']"));
            remove.click();
        }catch(Exception e){
            System.out.println("Channel not removed");
	        e.printStackTrace();
        }
    }
}
