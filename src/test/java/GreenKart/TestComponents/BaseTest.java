package GreenKart.TestComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import GreenKart.PageObjects.HomePage;

public class BaseTest
{

	public WebDriver driver ;
	protected HomePage homepage ; 
	
	public WebDriver intializingDriver()
	{
		
		WebDriver driver = new ChromeDriver();
		return driver ; 
		
	}
	
	@BeforeTest
	public HomePage launchApplication()
	{
		
		driver = intializingDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		homepage = new HomePage(driver);
		homepage.goTo();
		
		return homepage ; 
		
	}
	
	@AfterTest	
	public void terminate()
	{
		
		driver.close();
		
	}
	
}
