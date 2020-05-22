package applicationLayers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigatetoBranchespage {
WebDriver driver;
public NavigatetoBranchespage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//tr//tr//tr//tr//tr[2]//td[1]//a[1]//img[1]")
WebElement clickBranches;
@FindBy(xpath="//span[contains(text(),'Branch Details')]")
WebElement verify;
public String clickonBranches() throws Throwable
{
	String res=null;
	clickBranches.click();
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
