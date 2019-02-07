package test.dummy_tests;

import org.testng.annotations.Test;
import utilities.GeneralUtils.TestBase;
import utilities.PageUtils.DiscussPage;

public class test1 extends TestBase{

	@Test
	public void test() throws Exception{
	    Thread.sleep(2000);
		DiscussPage.removeChannel("Product Applications Liaison");

	}


}
