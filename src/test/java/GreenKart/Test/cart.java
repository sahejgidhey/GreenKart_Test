package GreenKart.Test;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GreenKart.TestComponents.BaseTest;

public class cart extends BaseTest
{

	@Test(dataProvider = "getData")
	public void itemCheck(String[] vege)
	{
		
		List<String> vegetables = Arrays.asList(vege);
		
		//homepage.increaseItemswithTextField("5" , "Cucumber");
		vegetables.stream().forEach(s->homepage.addToCart(s));
		
		homepage.clickOnCart();
		vegetables.stream().forEach(s->Assert.assertTrue(homepage.cartItemsCheck(s)));
		homepage.clickOnCart();
		
	}
	
	@Test(dependsOnMethods = "itemCheck" , dataProvider = "getData")
	public void countCheck(String[] vege)
	{
		
		homepage.clickOnCart();
		
		for(int i = 0 ; i < vege.length ; i++)
		{
			
			Assert.assertTrue(homepage.itemCountCheck(vege[i] , "1"));
			
		}
		
		homepage.clickOnCart();
		
	}
	
	@Test(dependsOnMethods = "AddToCartLabelCheck" , dataProvider = "getData")
	public void refershCheck(String[] vege)
	{
		
		homepage.pagerefresh();
		
		for(int i = 0 ; i < vege.length ; i++)
		{
			
			Assert.assertTrue(homepage.cartItemsCheck(vege[i]));
			
		}
		
	}
	
	@Test(dependsOnMethods = "itemCheck" , dataProvider = "getData")
	public void amountCheck(String[] vege)
	{
		
		homepage.clickOnCart();
		
		for(int i = 0 ; i < vege.length ; i++)
		{
			
			Assert.assertTrue(homepage.amountCheck(vege[i] , 1));
			
		}
		
		homepage.clickOnCart();
		
	}
	
	@Test(dependsOnMethods = "amountCheck" , dataProvider = "getData")
	public void AddToCartLabelCheck(String[] vege)
	{
		
		homepage.pagerefresh();
		for(int i = 0 ; i < vege.length ; i++)
		{
			
			homepage.addToCart(vege[i]);
			Assert.assertTrue(homepage.getTextFromAddToCart(vege[i]).contains("ADDED"));
			
		}
		
		
	}
	
	@Test(dependsOnMethods = "AddToCartLabelCheck")
	public void itemCountCheck()
	{
		
		String product[][] = {{"Cucumber" , "5"} , {"Beetroot" , "1"}};
		
		SoftAssert sa = new SoftAssert();
		
		homepage.pagerefresh();
		
		for(int i = 0 ; i < product.length ; i++)
		{
			
			if(Integer.parseInt(product[i][1].trim()) > 1)
			{
				
				homepage.increaseItemswithTextField(product[i][1], product[i][0]);
				
			}
			
			homepage.addToCart(product[i][0]);
			
		}
		
		homepage.clickOnCart();
		
		for(int i = 0 ; i < product.length ; i++)
		{
			
			sa.assertTrue(homepage.itemCountCheck(product[i][0], product[i][1]));
			
		}
		
		sa.assertAll();
		
	}
	
}
