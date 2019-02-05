package test.smoke;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import utilities.GeneralUtils.Constants;
import utilities.GeneralUtils.TestBase;
import utilities.PageUtils.ChannelsTabPage;
import utilities.PageUtils.DiscussPage;

public class ChannelsTest extends TestBase{
	private static Faker faker;
	private static String randomName;


	@Test (priority = 1)
	public void clickingTest() {

		//Clicking the channels tab which brings itno new environment
		DiscussPage.clickChannelsTab();

		//Verifying clicking action brings to correct environment
		System.out.println("Verifying correct url is brought");
		Assert.assertEquals(ChannelsTabPage.getUrl(), Constants.CHANNELTAB_ENVI);

		//Verifying that all channels are displayed to the user
		System.out.println("Verifying all channels are present");
		Assert.assertTrue(ChannelsTabPage.hasAllChannels());

		//Redirecting to discuss environment to continue next tests
		ChannelsTabPage.goToDiscussEnvironment();

	}

	@Test(priority = 2, dependsOnMethods = { "clickingTest" })
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
		ChannelsTabPage.goToChannelsEnvironment();
		
		//Verifying channel environemnt is current url
		Assert.assertEquals(ChannelsTabPage.getUrl(), Constants.CHANNELTAB_ENVI);
		
		//Verifying channel was created
		ChannelsTabPage.containsChannel(randomName);
	}
}
