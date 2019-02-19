package utilities.PageUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GeneralUtils.Constants;
import utilities.GeneralUtils.Driver;
import utilities.GeneralUtils.FailMessages;

import java.util.List;

public class DirectMessagesPage {
    private static WebDriver driver;

    static {
        driver = Driver.setUp();
        PageFactory.initElements(driver, DirectMessagesPage.class); //<----ask question about this
    }

    @FindBy(xpath="//span[@data-type='dm']")  //DONE
    public static WebElement dmAdd;

    @FindBy(xpath = "//span[@data-channel-id='398']")
    public static WebElement dmDelete;

    @FindBy(xpath = "//textarea[@style='height: 28px;']")
    public static WebElement dmField;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[2]/div[2]/div[1]/div[1]/div[2]/div/button[1]")
    public static WebElement dmEmoji;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[2]/div[2]/div[1]/div[1]/div[2]/div/button[2]")
    public static WebElement dmAttachment;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[2]/div[2]/div[2]/button")
    public static WebElement dmSend;



    @FindAll({ @FindBy(xpath = "//h4[@class = 'o_kanban_record_title']//span") })
    private static List<WebElement> allChannelsName;

    @FindBy(xpath = "//input[@placeholder = 'Name']")
    private static WebElement directMessageName;

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
                dmAdd.click();
                Thread.sleep(2000);
                //channelName.sendKeys(name);
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

    public static void goToChannelsEnvironment() {
        try {
            publicChannelsLink.click();
            Thread.sleep(2000);
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
}
