package test.smoke;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.GeneralUtils.TestBase;
import utilities.PageUtils.DiscussPage;

public class DirectMessageFieldDropDownTest extends TestBase {

//    @FindBy(xpath="//input[@placeholder='User name']")
//    WebElement userNameField;

    @Test
    public void test() {
        DiscussPage.addDm();

    }
}
