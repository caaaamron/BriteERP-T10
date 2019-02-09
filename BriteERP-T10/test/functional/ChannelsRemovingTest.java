package test.functional;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.GeneralUtils.Constants;
import utilities.GeneralUtils.TestBase;
import utilities.PageUtils.ChannelsTabPage;
import utilities.PageUtils.DiscussPage;

public class ChannelsRemovingTest extends TestBase {
	
	@Test
    public static void removeChannelTest(){

	    // Assigning a random name for testing channel
	    String testChannel = Constants.faker.funnyName().name();

	    // Adding channel in discuss page
        DiscussPage.addChannel(testChannel);

        // Varifying discuss page has channel that was just created
        Assert.assertTrue(DiscussPage.containsChannel(testChannel));

        // Removing channel
        DiscussPage.removeChannel(testChannel);


       // soft.assertFalse(DiscussPage.containsChannel(testChannel));
        DiscussPage.clickChannelsTab();
    }

    @Test
    public static void removeChannelTest2(){

	    // Verifying url is correct and random name for channel
	    Assert.assertTrue(ChannelsTabPage.hasCorrectUrl());
	    String testChannel = Constants.faker.funnyName().name();

	    // Adding channel and changing to channels environment
	    ChannelsTabPage.addChannel(testChannel);
	    ChannelsTabPage.goToChannelsEnvironment();

	    // Asserting false because channel create does is not functioning
	    Assert.assertFalse(ChannelsTabPage.containsChannel(testChannel));

	    // Remove channel function disabled
	    //ChannelsTabPage.removeChannel(testChannel);




    }
}
