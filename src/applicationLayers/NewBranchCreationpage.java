package applicationLayers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class NewBranchCreationpage {
WebDriver driver;
public NewBranchCreationpage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//input[@name='BtnNewBR']")
WebElement clickonNewBranch;
@FindBy(xpath="//input[@name='txtbName']")
WebElement enterbname;
@FindBy(xpath="//input[@name='txtAdd1']")
WebElement enteradd1;
@FindBy(xpath="//input[@name='txtZip']")
WebElement enterzcode;
@FindBy(xpath="//select[@id='lst_counrtyU']")
WebElement selectcountry;
@FindBy(xpath="//select[@id='lst_stateI']")
WebElement selectstate;
@FindBy(xpath="//select[@id='lst_cityI']")
WebElement selectcity;
@FindBy(xpath="//input[@name='btn_insert']")
WebElement clickonsubmit;
public String verifyNewBranchCreationpage(String bname,String add1,String zcode,String country,String state,String city) throws Throwable
{
	String res=null;
	clickonNewBranch.click();
	enterbname.sendKeys(bname);
	enteradd1.sendKeys(add1);
	enterzcode.sendKeys(zcode);
	new Select(selectcountry).selectByVisibleText(country);
	new Select(selectstate).selectByVisibleText(state);
	new Select(selectcity).selectByVisibleText(city);
	clickonsubmit.click();
	Thread.sleep(2000);
	String message=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	Thread.sleep(2000);
	if(message.contains("New Branch with id "))
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
