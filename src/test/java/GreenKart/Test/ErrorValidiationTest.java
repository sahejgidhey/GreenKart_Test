package GreenKart.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import GreenKart.TestComponents.BaseTest;

public class ErrorValidiationTest extends BaseTest
{
	
	@Test
	public void searchBox()
	{
		
		homepage.search("@");
		Assert.assertTrue(homepage.itemNotFoundError());
		
	}
	
	@Test(dependsOnMethods = "searchBox")
	public void checkoutButton() throws InterruptedException
	{
		
		//homepage.pagerefresh();
		homepage.clickOnCart();
		homepage.clickCheckoutButton();
		
		Thread.sleep(500);
		String[] url = driver.getCurrentUrl().split("/");
		
		if(url[url.length-1].equals("cart"))
		{
			
			Assert.assertTrue(false);
			
		}
		
	}
	
}
