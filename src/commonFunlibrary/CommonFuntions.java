package commonFunlibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.PBconstant;

public class CommonFuntions extends PBconstant {
/*Project Name: 
 * Module Name:
 * Tester Name:
 * Date of Creation:
 */
public static boolean verifylogin(String username, String password)
{
driver.findElement(By.xpath(p.getProperty("enterusername"))).sendKeys(username);
driver.findElement(By.xpath(p.getProperty("enterpassword"))).sendKeys(password);
driver.findElement(By.xpath(p.getProperty("login"))).click();
if(driver.getCurrentUrl().contains(""))
{
	Reporter.log("login is successful",true);
	return true;
}
else
{
	Reporter.log("login fail",true);
	return false;
}
}
public static boolean verifylogout()
{
	driver.findElement(By.xpath(p.getProperty("clicklogout"))).click();
	if(driver.findElement(By.xpath(p.getProperty("login"))).isDisplayed())
	{
		Reporter.log("logout is successfull",true);
		return true;
	}
	else
	{
		Reporter.log("logout is fail",true);
		return false;
	}
}
public static boolean navigatetobranches()
{
	driver.findElement(By.xpath(p.getProperty("clickbranches"))).click();
	if(driver.findElement(By.xpath("//span[contains(text(),'Branch Details')]")).isDisplayed())
	{
		Reporter.log("Branch Details page loaded successfully",true);
		return true;
	}
	else
	{
		Reporter.log("Branch Details page failed to loaded",true);
		return false;
	}
}
public static boolean verifyNewBranchCreation(String bname,String add1,String add2,String add3,String area,String zcode,String country,String state,String city)throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("clicknewbranch"))).click();
	driver.findElement(By.xpath(p.getProperty("enterbanme"))).sendKeys(bname);
	driver.findElement(By.xpath(p.getProperty("enteradd1"))).sendKeys(add1);
	driver.findElement(By.xpath(p.getProperty("enteradd2"))).sendKeys(add2);
	driver.findElement(By.xpath(p.getProperty("enteradd3"))).sendKeys(add3);
	driver.findElement(By.xpath(p.getProperty("enterarea"))).sendKeys(area);
	driver.findElement(By.xpath(p.getProperty("enterzcode"))).sendKeys(zcode);
	new Select(driver.findElement(By.xpath(p.getProperty("selectcountry")))).selectByVisibleText(country);
	Thread.sleep(5000);
	new Select(driver.findElement(By.xpath(p.getProperty("selectstate")))).selectByVisibleText(state);
	new Select(driver.findElement(By.xpath(p.getProperty("selectcity")))).selectByVisibleText(city);
	driver.findElement(By.xpath(p.getProperty("clicksubmit"))).click();
	Thread.sleep(5000);
	String message=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	if(message.toLowerCase().contains("New Branch with id".toLowerCase()))
	{
		Reporter.log(message,true);
		return true;
	}
	else
	{
		Reporter.log("failed to create new branch",true);
		return false;
	}
}
public static boolean verfyBranchUpdation(String bname,String add1)
{
	driver.findElement(By.xpath(p.getProperty("clickedit"))).click();
	driver.findElement(By.xpath(p.getProperty("updatebname"))).sendKeys(bname);
	driver.findElement(By.xpath(p.getProperty("updatebname"))).sendKeys(add1);
	driver.findElement(By.xpath(p.getProperty("clickupdate"))).click();
	String message=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	if(message.toLowerCase().contains("Branch updated Sucessfully".toLowerCase()))
	{
		Reporter.log(message,true);
		return true;
	}
	else
	{
		Reporter.log("failed to update",true);
		return false;
	}
}
}
