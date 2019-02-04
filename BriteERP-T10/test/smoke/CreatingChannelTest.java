package smoke;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import GeneralUtils.Constants;
import GeneralUtils.TestBase;
import PageUtils.ChannelsTabPage;
import PageUtils.DiscussPage;

public class CreatingChannelTest extends TestBase{
	private static Faker faker;
	private static String randomName;
	
	@Test(priority = 1)
	public void discussEnvironmentTest() {
		
		//Initializing faker and making random fake name
		faker = new Faker();
		randomName = faker.name().title();
		System.out.println("Random name:  " + randomName);
		
		//Testing adding channel functionality
		DiscussPage.addChannel(randomName);
		
		//Verifying created channel was actually created
		System.out.println("Veryfiying discuss page contains created channel");
		Assert.assertTrue(DiscussPage.containsChannel(randomName));
		
	}
	
	@Test(dependsOnMethods = { "discussEnvironmentTest" })
	public void channelEnvironmentTest() {
		
		//Faker to make random names
		randomName = faker.funnyName().name();
		System.out.println("Random funny name:  " + randomName);
		//Clicking channels tab which brings new environment
		DiscussPage.clickChannelsTab();
		
		//Verifying the correct url is brought
		System.out.println("Verifying channels tab url is correct");
		Assert.assertEquals(ChannelsTabPage.getUrl(), Constants.CHANNELTAB_ENVI);
		
		//Testing adding a new channel
		ChannelsTabPage.addChannel(randomName);
		
		//Navigating back to channel home environment
		ChannelsTabPage.goToChannelsHome();
		
		//Verifying channel environemnt is current url
		Assert.assertEquals(ChannelsTabPage.getUrl(), Constants.CHANNELTAB_ENVI);
		
		//Verifying channel was created
		ChannelsTabPage.containsChannel(randomName);
	}
}
