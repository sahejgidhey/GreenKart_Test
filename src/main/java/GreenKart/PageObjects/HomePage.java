package GreenKart.PageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GreenKart.AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent
{
	
	WebDriver driver ; 
	
	public HomePage(WebDriver driver)
	{
		
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(driver , this); 
		
	}
	
	@FindBy(css = ".products-wrapper div.product")
	List<WebElement> product ;
	
	@FindBy(css = ".cart-icon")
	WebElement cartIcon ; 
	
	@FindBy(css = "li.cart-item")
	List<WebElement> items ; 
	
	@FindBy(xpath = "//button[text() = \"PROCEED TO CHECKOUT\"]")
	WebElement CheckOutButton ;
	
	@FindBy(css = ".search-keyword")
	WebElement searchBox ; 
	
	@FindBy(css = ".cart-header-navlink")
	List<WebElement> links ; 
	
	@FindBy(css = ".no-results")
	WebElement noItemFound ; 
	
	By productPrice = By.cssSelector("p.product-price");
	By amount = By.cssSelector("p.amount");	
	By NameCart = By.cssSelector("p.product-name") ;
	By cartButton = By.cssSelector("div button");
	By incrementButton = By.cssSelector(".increment");
	By product_Name = By.cssSelector(".product-name");
	
	public void goTo()
	{
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		
	}
	
	public int findProduct(String productName)
	{
		
		int count = 0 ; 
		for(int i = 0 ; i < product.size(); i++)
		{
			String name = product.get(i).findElement(By.tagName("h4")).getText(); 
			
			if(name.contains(productName))
			{
				
				count  = i ; 
				break ; 
				
			}
			
		}
		
		return count;
		
	}
	
	public void addToCart(String productName)
	{
		
		int count = findProduct(productName);
		
		product.get(count).findElement(cartButton).click();
		
	}
	
	public void clickOnCart()
	{
		
		cartIcon.click();
		
	}
	
	public boolean cartItemsCheck(String productname)
	{ 
		
		return items.stream().anyMatch(s->s.findElement(NameCart).getText().contains(productname));
		
	}
	
	public void increaseItemswithButton(int count , String productName)
	{
		
		int a = findProduct(productName);
		
		WebElement increment = product.get(a).findElement(incrementButton);
		
		for(int i = 0 ; i < count ; i++)
		{
			
			increment.click();
			
		}
				
	}
	
	public void increaseItemswithTextField(String count , String productName)
	{
		
		int a = findProduct(productName);
		
		product.get(a).findElement(By.cssSelector("input.quantity")).clear();
		product.get(a).findElement(By.cssSelector("input.quantity")).sendKeys(count);
				
	}
	
	public boolean itemCountCheck(String name , String count)
	{
		
		int num = 0 ; 
		
		for(int i = 0 ; i < items.size() ; i++)
		{
			
			if(items.get(i).findElement(NameCart).getText().contains(name))
			{
				
				num = i ; 
				break ; 
				
			}
			
		}
		
		return items.get(num).findElement(By.cssSelector("p.quantity")).getText().contains(count);
		
	}
	
	public void clickCheckoutButton()
	{
		
		CheckOutButton.click();
		
	}
	
	public int getAmount(String productName)
	{
		
		String amount = product.get(findProduct(productName)).findElement(productPrice).getText();
		return Integer.parseInt(amount);
		
	}
	
	public boolean amountCheck(String productName , int count)
	{
		
		int amt = 0 ; 
		
		for(int i = 0 ; i < items.size() ; i++)
		{
			
			if(items.get(i).findElement(NameCart).getText().contains(productName))
			{
				
				amt = Integer.parseInt(items.get(i).findElement(amount).getText());
				break ;
				
			}
			
		}
		
		int price = getAmount(productName);
		
		boolean a = false ;
		
		if(price*count == amt)
		{
			
			a =  true ; 
			
		}
		
		return a ; 
		
	}
	
	public String getHeaderLinksTitle(String LinkName)
	{
		
		links.stream().filter(s->s.getText().contains(LinkName)).forEach(s->s.click());
		Set<String> windowId = driver.getWindowHandles();
		Iterator<String> it = windowId.iterator();
		
		String parentId = it.next();
		String childId = it.next();
		
		driver.switchTo().window(childId);
		
		String windowTitle = driver.getTitle();
		driver.close();
		
		driver.switchTo().window(parentId);
		
		return windowTitle ;
		
	}
	
	public void search(String text)
	{
		
		searchBox.clear();
		searchBox.sendKeys(text);
		
	}
	
	public boolean itemNotFoundError()
	{
		
		return noItemFound.isDisplayed();
		
	}
	
	public String getTextFromAddToCart(String productName)
	{
		
		int count = findProduct(productName);
		
		return product.get(count).findElement(cartButton).getText(); 
		
	}
	
	public boolean itemsDisplayed() throws InterruptedException
	{
		
		boolean a = false ; 
		
		Thread.sleep(500);
		
		if(product.size() >= 1)
		{
		
			a = true ;
			
		}
		
		
		return a ; 
		
	}
	
}
