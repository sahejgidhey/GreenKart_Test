package GreenKart.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import GreenKart.TestComponents.BaseTest;

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
	
	@Test
	public void headerLink()
	{
		
		String a = homepage.getHeaderLinksTitle("Free Access to InterviewQues/ResumeAssistance/Material");
		Assert.assertTrue(a.equalsIgnoreCase("RS Academy"));
		
	}
	
}
