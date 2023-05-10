package GreenKart.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import GreenKart.PageObjects.HomePage;

public class BaseTest
{
	
	public WebDriver driver ;
	protected HomePage homepage ; 
	
	public WebDriver intializingDriver() throws IOException
	{
		
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\GreenKart\\resources\\Global.properties");
		
		prop.load(file);
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			
			ChromeOptions options = new ChromeOptions();
			
			if(browserName.contains("headless"))
			{
				
				options.addArguments("headless");
				
			}
			
			driver = new ChromeDriver(options);;
			
		}
		else if(browserName.contains("firefox"))
		{
			
			driver = new FirefoxDriver();
			
		}
		else
		{
			
			driver = new ChromeDriver();
			
		}
		
		driver.manage().window().setSize(new Dimension(1920,1080));
		
		return driver ; 
		
	}
	
	@BeforeTest
	public HomePage launchApplication() throws IOException
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
	
	@DataProvider
	public String[][] getData()
	{
		
		String product[][] = {{"Cucumber" , "Beetroot"} , {"Brocolli" , "Cauliflower"}};
		
		return product ; 
		
	}	
	
}
