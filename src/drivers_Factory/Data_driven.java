package drivers_Factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelUtilies;

public class Data_driven {
WebDriver driver;
ExtentReports report;
ExtentTest test;
ExcelUtilies ex;
String outputexcel="D:\\Selenium_evengBatch\\Framework_project\\FileOutputs(Excel)\\results.xlsx";
String inputexcel="D:\\Selenium_evengBatch\\Framework_project\\Fileinputs(Excel)\\Logindata.xlsx";
@BeforeTest
public void setup()
{
	System.setProperty("webdriver.gecko.driver", "D:\\Selenium_evengBatch\\Framework_project\\Drivers\\geckodriver.exe");
	driver=new FirefoxDriver();
	report=new ExtentReports("D:\\Selenium_evengBatch\\Framework_project\\Reports(ExtentReports)\\reports.html");
	
}
@Test
public void logintest()throws Throwable
{
	ex=new ExcelUtilies(inputexcel);
	int rows=ex.rowCount("Login");
	int cc=ex.colcount("Login");
	for(int i=1; i<=rows; i++)
	{
		
		driver.get("http://orangehrm.qedgetech.com/symfony/web/index.php/auth/login");
		test=report.startTest("validation");
		String username=ex.getCellData("Login",i, 0);
		String password=ex.getCellData("Login", i, 1);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		
		if(driver.getCurrentUrl().contains("dashboard"))
		{
			ex.setCellData("Login", i, 2, "login sucessfull",outputexcel);
			test.log(LogStatus.PASS, "login successfull");
			ex.setCellData("Login", i, 3, "pass",outputexcel );
			Reporter.log("login success",true);
		}
		else
		{
			String message=driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
			ex.setCellData("Login", i, 2, message, outputexcel);
			ex.setCellData("Login", i, 3, "fail", outputexcel);
			test.log(LogStatus.FAIL, message);
			Reporter.log(message,true);
		}
		report.endTest(test);
		report.flush();
	}
}
@AfterTest
public void teardown()
{
	driver.close();
}
}
