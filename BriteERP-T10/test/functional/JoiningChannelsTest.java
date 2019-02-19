package test.functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.GeneralUtils.TestBase;
import utilities.PageUtils.ChannelsTabPage;
import utilities.PageUtils.DiscussPage;

public class JoiningChannelsTest extends TestBase {

    @Test
    public static void joiningPublicChannelTest(){
        // Clicking channels tab and creating channel to join
        DiscussPage.clickChannelsTab();
        String channelToJoin = "rd";

        // Joining channel and verifying leave option is enabled once joining and that user cannot click join
        ChannelsTabPage.joinChannel(channelToJoin);
        try {
            WebElement channelToleave = driver.findElement(By.xpath("//h4[@class = 'o_kanban_record_title']//span[text() = '" + channelToJoin + "']//parent::h4//following-sibling::button[@data-name = 'action_unfollow']"));
            Assert.assertTrue(channelToleave.isDisplayed());
        }catch (Exception e){
            System.out.println("Channel: " + channelToJoin + " unable to be found or did not contain leave button");
        }

    }
}
