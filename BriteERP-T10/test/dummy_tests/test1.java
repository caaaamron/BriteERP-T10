package test.dummy_tests;

import org.testng.annotations.Test;

import test.GeneralUtils.PropertyConfig;
import test.GeneralUtils.TestBase;
import test.PageUtils.DiscussPage;
import test.PageUtils.Homepage;
import test.PageUtils.OodooPage;
import test.PageUtils.SignInPage;

public class test1 extends TestBase{
	public void test() {
		DiscussPage.addChannel("general");
	
	}
}
