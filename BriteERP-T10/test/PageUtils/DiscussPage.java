package test.PageUtils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.GeneralUtils.Driver;
import test.GeneralUtils.FailMessages;

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

//	@FindBy(xpath = "//div[@class = 'o_mail_chat_channel_item o_active']")
//	private static WebElement activeChannel;

	@FindAll({ @FindBy(className = "o_mail_chat_channel_item ") })
	private static List<WebElement> readChannels;

	@FindAll({ @FindBy(xpath = "//div[@class = 'o_mail_chat_channel_item  o_unread_message ']") })
	private static List<WebElement> unreadChannels;

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
		try {
			System.out.println("Clicking channels tab");
			channelsTab.click();
			Thread.sleep(2000);
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
				System.out.println("Adding channel");
				channelAdd.click();
				channelAddName.sendKeys(name);
				channelHiddenCreate.click();

				Driver.wait(2);
			} catch (Exception e) {
				FailMessages.fail(e);
			}
		}
	}

}
