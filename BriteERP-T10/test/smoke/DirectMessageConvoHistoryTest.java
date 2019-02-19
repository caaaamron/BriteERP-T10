package test.smoke;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.GeneralUtils.TestBase;
import utilities.PageUtils.DirectMessagesPage;

public class DirectMessageConvoHistoryTest extends TestBase {


 @Test
    public void test(){

     String targetMessage = "I LOVE CUCUMBER";
     String targetName = "InventoryManager 3";

     WebElement targetElementName = driver.findElement(By.xpath("//div[@title ='" + targetName + "']"));
     targetElementName.click();

        DirectMessagesPage.dmField.sendKeys(targetMessage);
        DirectMessagesPage.dmSend.click();
        WebElement targetElement = driver.findElement(By.xpath("//div[@class = 'o_thread_message_content']//p[text() = '" + targetMessage + "']"));
        Assert.assertTrue(targetElement.isDisplayed());
        //Assert.assertEquals(targetMessage, targetElementName);
    }



}
