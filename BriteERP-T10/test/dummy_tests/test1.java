package test.dummy_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.GeneralUtils.TestBase;
import utilities.PageUtils.DiscussPage;

import java.util.List;

public class test1 extends TestBase{

	@Test
	public void test() throws Exception {
        Thread.sleep(2000);
        DiscussPage.removeChannel("Product Applications Liaison");
        
    }


}
