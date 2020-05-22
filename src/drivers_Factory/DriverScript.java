package drivers_Factory;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunlibrary.CommonFuntions;
import constant.PBconstant;
import utilities.ExcelUtilies;

public class DriverScript extends PBconstant {
String excelpath="D:\\Selenium_evengBatch\\Framework_project\\Fileinputs(Excel)\\Controller.xlsx";
String outputexcel="D:\\Selenium_evengBatch\\Framework_project\\FileOutputs(Excel)\\kkk.xlsx";
String TCsheet="TestCases";
String TSsheet="TestSteps";
ExtentReports report;
ExtentTest test;
@Test
public void starttest()throws Throwable
{
	boolean res=false;
	String tcres=null;
	ExcelUtilies xl=new ExcelUtilies(excelpath);
	int TCcount=xl.rowCount(TCsheet);
	int TScount=xl.rowCount(TSsheet);
	for(int i=1;i<=TCcount;i++)
	{
		report=new ExtentReports("D:\\Selenium_evengBatch\\Framework_project\\Reports(ExtentReports)\\starttest.html",false);
		test=report.startTest("starttest");
		String execute=xl.getCellData(TCsheet, i, 2); 
		if(execute.equalsIgnoreCase("y"))
		{
			for(int j=1;j<=TScount;j++)
			{
				String discription=xl.getCellData(TSsheet, j, 2);
				if(xl.getCellData(TCsheet, i, 0).equalsIgnoreCase(xl.getCellData(TSsheet, j, 0)))
				{
					String keyword=xl.getCellData(TSsheet, j, 3);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
						res=CommonFuntions.verifylogin("Admin", "Admin");
						test.log(LogStatus.INFO, discription);
					}
					else if(keyword.equalsIgnoreCase("GoToBranches"))
					{
						res=CommonFuntions.navigatetobranches();
						test.log(LogStatus.INFO, discription);
					}
					else if(keyword.equalsIgnoreCase("NewBranchCreation"))
					{
						res=CommonFuntions.verifyNewBranchCreation("Amaravathi", "ramanthapurgvvhghv", "hydhfhfyhvy", "telangana", "hyderabad", "500027", "INDIA", "Andhra Pradesh", "Hyderabad");
						test.log(LogStatus.INFO, discription);
					}
					else if(keyword.equalsIgnoreCase("UpdateBranch"))
					{
						res=CommonFuntions.verfyBranchUpdation("BalaBala", "ameerpet23");
						test.log(LogStatus.INFO, discription);
					}
					else if(keyword.equalsIgnoreCase("AdminLogout"))
					{
						res=CommonFuntions.verifylogout();
						test.log(LogStatus.INFO, discription);
					}
					String tsres=null;
					if(res)
					{
						tsres="pass";
						xl.setCellData(TSsheet, j, 4, tsres, outputexcel);
						test.log(LogStatus.PASS, discription);
					}
					else
					{
						tsres="fail";
						xl.setCellData(TSsheet, j, 4, tsres, outputexcel);
						test.log(LogStatus.FAIL, discription);
					}
					if(!tsres.equalsIgnoreCase("fail"))
					{
						tcres=tsres;
					}
				}
				report.endTest(test);
				report.flush();
			}
			xl.setCellData(TCsheet, i, 3, tcres, outputexcel);
		}
		else
		{
			xl.setCellData(TCsheet, i, 3, "blocked", outputexcel);
		}
	}
}

}
