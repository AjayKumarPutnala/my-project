package applicationLayers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Logoutpage {
WebDriver driver;
public Logoutpage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//td//td//td//td[3]//a[1]//img[1]")
WebElement clickonlogout;
@FindBy(xpath="//input[@id='login']")
WebElement verify;
public String verifylogoutpage() throws Throwable
{
	String res=null;
	clickonlogout.click();
	Thread.sleep(2000);	
	if(verify.isDisplayed())
	{
		res="pass";
	}
	else
	{
		res="fail";
	}
	return res;
}
}
