package GreenKart.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import GreenKart.TestComponents.BaseTest;
import GreenKart.TestComponents.Retry;

public class HeaderTest extends BaseTest
{

	@Test
	public void LogoCheck()
	{
		
		Assert.assertEquals(homepage.appLogo() , "GREENKART");
		
	}
	
	@Test
	public void textBox() throws InterruptedException
	{
		
		homepage.search("ca");      
		Assert.assertTrue(homepage.itemsDisplayed());
		
	}
	
	@Test(retryAnalyzer = Retry.class)
	public void headerLink()
	{
		
		String a = homepage.getHeaderLinksTitle("Free Access to InterviewQues/ResumeAssistance/Material");
		Assert.assertEquals(a , "RS Academy");
		
	}
	
}
