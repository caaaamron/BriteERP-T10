package smoke;

import org.testng.Assert;
import org.testng.annotations.Test;

import GeneralUtils.Constants;
import GeneralUtils.TestBase;
import PageUtils.ChannelsTabPage;
import PageUtils.DiscussPage;

public class ChannelTabTest extends TestBase{
	
	@Test
	public void clickingTest() {
		
		//Clicking the channels tab which brings itno new environment
		DiscussPage.clickChannelsTab();
		
		//Verifying clicking action brings to correct environment
		System.out.println("Verifying correct url is brought");
		Assert.assertEquals(ChannelsTabPage.getUrl(), Constants.CHANNELTAB_ENVI);
		
		//Verifying that all channels are displayed to the user
		System.out.println("Verifying all channels are present");
		Assert.assertTrue(ChannelsTabPage.hasAllChannels());
	
	}
}
