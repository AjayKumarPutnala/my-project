package applicationLayers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage {
WebDriver driver;
public Loginpage(WebDriver driver)throws Throwable
{
	this.driver=driver;
}
@FindBy(xpath="//input[@placeholder='User Name']")
WebElement enterusername;
@FindBy(xpath="//input[@placeholder='Password']")
WebElement enterpassword;
@FindBy(xpath="//input[@name='login']")
WebElement clicklogin;
public String verifyloginpage(String username,String password) throws Throwable
{
	String res=null;
	enterusername.sendKeys(username);
	enterpassword.sendKeys(password);
	clicklogin.click();
	Thread.sleep(2000);
	if(driver.getCurrentUrl().contains("minflow.aspx"))
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
