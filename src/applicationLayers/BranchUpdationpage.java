package applicationLayers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BranchUpdationpage {
WebDriver driver;
public BranchUpdationpage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//tr//tr[2]//td[7]//a[1]//img[1]")
WebElement clickonedit;
@FindBy(xpath="//input[@name='txtbnameU']")
WebElement enterbname;
@FindBy(xpath="//input[@name='txtadd1u']")
WebElement enteradd1;
@FindBy(xpath="//input[@name='txtzipu']")
WebElement enterzcode;
@FindBy(xpath="//input[@name='btnupdate']")
WebElement clickonupdate;
public String verifyBranchUpdationpage(String bname,String add1,String zcode) throws Throwable
{
	String res=null;
	clickonedit.click();
	enterbname.sendKeys(bname);
	enteradd1.sendKeys(add1);
	enterzcode.sendKeys(zcode);
	clickonupdate.click();
	Thread.sleep(2000);
	String message=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	Thread.sleep(2000);
	if(message.contains("Branch updated Sucessfully"))
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
