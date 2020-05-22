package drivers_Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationLayers.BranchUpdationpage;
import applicationLayers.Loginpage;
import applicationLayers.Logoutpage;
import applicationLayers.NavigatetoBranchespage;
import applicationLayers.NewBranchCreationpage;
import utilities.ExcelUtilies;

public class TestingScript {
String inputexcel="D:\\Selenium_evengBatch\\Framework_project\\Fileinputs(Excel)\\Controller.xlsx";
String outputpath="D:\\Selenium_evengBatch\\Framework_project\\FileOutputs(Excel)\\ajay.xlsx";
WebDriver driver;
Loginpage login;
Logoutpage logout;
NavigatetoBranchespage branches;
NewBranchCreationpage newbranch;
BranchUpdationpage branchupdate;
String TCsheet="TestCases";
String TSsheet="TestSteps";
@BeforeTest
public void setup()
{
	System.setProperty("webdriver.gecko.driver", "D:\\Selenium_evengBatch\\Framework_project\\Drivers\\geckodriver.exe");
	driver=new FirefoxDriver();
	driver.get("http://primusbank.qedgetech.com/");
}
@Test
public void startingtest()throws Throwable
{
	String res=null;
	String tcres=null;
	ExcelUtilies xl=new ExcelUtilies(inputexcel);
	int tccount=xl.rowCount(TCsheet);
	int tscount=xl.rowCount(TSsheet);
	for(int i=1;i<=tccount; i++)
	{
		if(xl.getCellData(TCsheet, i, 2).equalsIgnoreCase("y"))
		{
			for(int j=1;j<=tscount; j++)
			{
				if(xl.getCellData(TCsheet, i, 0).equalsIgnoreCase(xl.getCellData(TSsheet, j, 0)))
				{
					String keyword=xl.getCellData(TSsheet, j, 3);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
						login=PageFactory.initElements(driver, Loginpage.class);
						res=login.verifyloginpage("Admin", "Admin");
					}
					else if(keyword.equalsIgnoreCase("GoToBranches"))
					{
						branches=PageFactory.initElements(driver, NavigatetoBranchespage.class);
						res=branches.clickonBranches();
					}
					else if(keyword.equalsIgnoreCase("NewBranchCreation"))
					{
						newbranch=PageFactory.initElements(driver, NewBranchCreationpage.class);
						res=newbranch.verifyNewBranchCreationpage("Vishnuvardhan", "Ramreddy Nagar", "123456", "INDIA", "Andhra Pradesh", "Hyderabad");
					}
					else if(keyword.equalsIgnoreCase("UpdateBranch"))
					{
						branchupdate=PageFactory.initElements(driver, BranchUpdationpage.class);
						res=branchupdate.verifyBranchUpdationpage("Vishnuvardhan", "ramanthapur hyd", "123456");
					}
					else if(keyword.equalsIgnoreCase("AdminLogout"))
					{
						logout=PageFactory.initElements(driver, Logoutpage.class);
						res=logout.verifylogoutpage();
					}
					String tsres=null;
					if(res.equalsIgnoreCase("pass"))
					{
						tsres="pass";
						xl.setCellData(TSsheet, j, 4, tsres, outputpath);
					}
					else
					{
						tsres="fail";
						xl.setCellData(TSsheet, j, 4, tsres, outputpath);
					}
					if(!tsres.equalsIgnoreCase("fail"))
					{
						tcres=tsres;
					}
				}
			}
			xl.setCellData(TCsheet, i, 3, tcres, outputpath);
		}
		else
		{
			xl.setCellData(TCsheet, i, 3, "blocked", outputpath);
		}
	}
	
}
@AfterTest
public void teardown()
{
	driver.close();
}
}
