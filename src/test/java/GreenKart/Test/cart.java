package GreenKart.Test;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import GreenKart.TestComponents.BaseTest;

public class cart extends BaseTest
{

	@Test
	public void itemCheck()
	{
		
		String[] vege = {"Cucumber" , "Beetroot"};
		List<String> vegetables = Arrays.asList(vege);
		
		//homepage.increaseItemswithTextField("5" , "Cucumber");
		vegetables.stream().forEach(s->homepage.addToCart(s));
		
		homepage.clickOnCart();
		vegetables.stream().forEach(s->Assert.assertTrue(homepage.cartItemsCheck(s)));
		
	}
	
	@Test(dependsOnMethods = "itemCheck")
	public void countCheck()
	{
		
		Assert.assertTrue(homepage.itemCountCheck("Beetroot", "1"));
		
	}
	
	@Test(dependsOnMethods = "AddToCartLabelCheck")
	public void refershCheck()
	{
		
		homepage.pagerefresh();
		Assert.assertTrue(homepage.cartItemsCheck("Beetroot"));
		
	}
	
	@Test(dependsOnMethods = "itemCheck")
	public void amountCheck()
	{
		
		//homepage.clickOnCart();
		Assert.assertTrue(homepage.amountCheck("Beetroot", 1));
		
	}
	
	@Test(dependsOnMethods = "amountCheck")
	public void AddToCartLabelCheck()
	{
		
		homepage.pagerefresh();
		String name = "Beetroot" ; 
		homepage.addToCart(name);
		Assert.assertTrue(homepage.getTextFromAddToCart(name).contains("ADDED"));
		
	}
	
}
