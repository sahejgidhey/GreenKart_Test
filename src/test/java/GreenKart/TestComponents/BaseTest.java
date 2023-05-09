package GreenKart.TestComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	
	public String getScreenshot(String testname , WebDriver driver) throws IOException
	{
		
		String path = System.getProperty("user.dir") + "\\screenshots\\" + testname + ".png";
		
		TakesScreenshot ts = (TakesScreenshot)driver ;
		File screenshot = ts.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshot , new File(path));
		
		return path ; 
		
	}
	
	@AfterTest	
	public void terminate()
	{
		
		driver.close();
		
	}
	
}
