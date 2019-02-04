package dummy_tests;

import org.testng.annotations.Test;

import GeneralUtils.PropertyConfig;
import GeneralUtils.TestBase;
import PageUtils.DiscussPage;
import PageUtils.Homepage;
import PageUtils.OodooPage;
import PageUtils.SignInPage;

public class test1 extends TestBase {
	
	@Test
	public void test() {
		DiscussPage.addChannel("general");
	
	}
}
