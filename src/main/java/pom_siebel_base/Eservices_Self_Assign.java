package pom_siebel_base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.Elements;
import utilities.ReadData_DubaiTrade;
import utilities.ReadData_Environment;
import utilities.ReadData_Siebel;
import utilities.Reports;
import utilities.ScreenShot;

public class Eservices_Self_Assign {

	public WebDriver driver;
	public ExtentTest test;
	public ExtentReports reports;
	public Reports report_Generator;
	public ScreenShot Take_Screenshot;
	public String Result;
	public WebDriverWait wait;
	public Elements Element;
	public ReadData_DubaiTrade Read_Exceldata_Hashmap;
	public ReadData_Environment ReadData_Environment_Details;
	public ReadData_Siebel ReadData_Siebel_Hasmap;
	
	public HashMap<String, String> map_Environment_Details;
	public HashMap<String, String> map_Inspection_Siebel;
	public HashMap<String, String> map_Inspection;
	
	public WebDriverWait waitMethod(WebDriver driver )  {
		
		this.driver = driver;
		
		return wait = new WebDriverWait(driver, 60);	
		
	}
	
	public String Self_Assign(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet,String Environment ,String Booking_Req_No ) throws Exception {
		
		this.driver = driver;

		this.test = test;

		this.reports = reports;

		Take_Screenshot = new ScreenShot();

		Element = new Elements();
		
		ReadData_Environment_Details = new ReadData_Environment();

		Read_Exceldata_Hashmap = new ReadData_DubaiTrade();
		
		ReadData_Siebel_Hasmap = new ReadData_Siebel();

		// Create Local Hash Map for Environment sheet
		map_Environment_Details = ReadData_Environment_Details.HashMap_Data_Environment_Details(Environment, Test_Data_Path);
		
		if (Sheet.equals("Inspection_DubaiTrade")) {
			// Create Local Hash Map for data sheet
			map_Inspection = Read_Exceldata_Hashmap.HashMap_Data_Inspection_DubaiTrade(j, Test_Data_Path,
					Sheet);
		}
		else if(Sheet.equals("Inspection_Siebel")) {
			// Create Local Hash Map for data sheet
			map_Inspection = ReadData_Siebel_Hasmap.HashMap_Data_Inspection_Siebel(j, Test_Data_Path, Sheet);
		}
		
		
		List<String> numbers = Arrays.asList(map_Inspection.get("Declaration_No").split(","));
		
		int Dec_Count = numbers.size();
		
		try {
			

			
			//Assigned Requests
			Result = Element.Click_Event(driver, test, reports, "//a[contains(text(), 'Create Inspection Requests')]", "Create Inspection Requests");

			//Work Queue:Query
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Task Queue:Query']", "Task Queue:Query");
			
			//Booking request no Text box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Booking_Reference_Number']" , "Booking request no Text box", Booking_Req_No);
			
			//Work Queue:Query -> Go
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Task Queue:Go']", "Task Queue:Go");
			
			//Contact Number text box
			Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Status']", "Statuc text box");

			//DrillDown Booking Request No
			//DC INS Booking Request Number
			Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS Booking Reference Number']", "DrillDown Booking Request No");
			
			Thread.sleep(5000);
			
			//Self Assign
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Self Assign']", "Self Assign");
			
			Thread.sleep(5000);
			
			Result = "Pass";
			test.log(Status.PASS, "Inspection is self assigned successfully");		
			reports.flush();
			driver.quit();

		}
		catch(WebDriverException e) {
			
			Result = "Fail";
			test.log(Status.FAIL, "Inspection is not self assigned successfully");		
			reports.flush();
			
		}
		
		return Result;
		
	}
		
}
	

