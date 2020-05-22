package constant;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PBconstant {
public static Properties p;
public static WebDriver driver;
public static FileInputStream fi;
@BeforeMethod
public void setup()throws Throwable
{
	p=new Properties();
	fi=new FileInputStream("D:\\Selenium_evengBatch\\Framework_project\\Properties\\Environment.properties");
	p.load(fi);
	if(p.getProperty("browser").equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium_evengBatch\\Framework_project\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(p.getProperty("Url"));
		driver.manage().window().maximize();
	}
	else if(p.getProperty("browser").equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", "D:\\Selenium_evengBatch\\Framework_project\\Drivers\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get(p.getProperty("Url"));
	}
	else
	{
		Reporter.log("browsers are not matching",true);
	}
}
@AfterMethod
public void teardown()
{
	driver.close();
}
}
