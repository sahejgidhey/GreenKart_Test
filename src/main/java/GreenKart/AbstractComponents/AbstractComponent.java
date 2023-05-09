package GreenKart.AbstractComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AbstractComponent
{
	
	WebDriver driver ; 
	
	protected AbstractComponent(WebDriver driver)
	{
		
		this.driver = driver ; 
		PageFactory.initElements(driver , this);
	}
	
	@FindBy(css = ".greenLogo")
	WebElement logo ; 
	
	public String appLogo()
	{
		
		return logo.getText();
		
	}

	
	public void pagerefresh()
	{
		
		driver.navigate().refresh();
		
	}
	
}
