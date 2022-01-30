package pom_siebel_base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class Siebel_Service_Request {

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
	
	public String Service_Request(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet,String Environment ,String Booking_Req_No ) throws Exception {
		
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
			
			if (map_Inspection.get("Document_Reference").equals("Export Declaration")) {
				
				//Unassigned Request
				Result = Element.Click_Event(driver, test, reports, "//a[contains(text(), 'Unassigned Requests')]", "Unassigned Requests");
				
				//Search Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Task Queue:Query']", "Task Queue:Query");
				
				//Booking request no Text box
				Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Booking_Reference_Number']" , "Booking request no Text box", Booking_Req_No);
				
				//Go Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Task Queue:Go']", "Go Button");
				
				//Status text box
				Result = Element.Click_Event(driver, test, reports, "//td[@title='Submitted']", "Status text box");
				
				//Self Assign Button
				Result = Element.Click_Event(driver, test, reports, "//button[@title='Self Assign']", "Self Assign Button");
				
				Thread.sleep(5000);
				
				//Edit button
				Result = Element.Click_Event(driver, test, reports, "//button[@title='Edit']", "Edit button");
				
			}else {
		//Assigned Service Requests
		Result = Element.Click_Event(driver, test, reports, "//a[contains(text(), 'Assigned Service Requests')]", "Assigned Service Requests");
		
		//Work Queue:Query
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Work Queue:Query']", "Work Queue:Query");
		
		//Booking request no Text box
		Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Booking_Request_Number']" , "Booking request no Text box", Booking_Req_No);
		
		//Work Queue:Query -> Go
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Work Queue:Go']", "Work Queue:Query -> Go");
		
		//Document Reference Number text box
		Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_BR_Document_Reference_Number']", "Document Reference Number text box");

		//DrillDown Booking Request No
		//DC INS Booking Request Number
		Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS Booking Request Number']", "DrillDown Booking Request No");
		
		//Edit button
		Result = Element.Click_Event(driver, test, reports, "//button[@title='Edit']", "Edit button");
			
			}
		
		Thread.sleep(5000);
		if (Environment.equals("UAT1")) {	
			//wait.until(ExpectedConditions.alertIsPresent());
			if (ExpectedConditions.alertIsPresent()!=null) {
				driver.switchTo().alert().accept();
			}
		}
		Thread.sleep(5000);
		
		//Findings
		Result = Element.TextBox_Value(driver, test, reports, "//textarea[@aria-label='Findings']" , "Findings Text Area", "Completed");
		
		//Save Button
		Result = Element.Click_Event(driver, test, reports, "//button[@title='Save']", "Save button");
		
		//Close Button
		Result = Element.Click_Event(driver, test, reports, "//button[@title='Close']", "Close button");
		
		if (driver.findElements(By.xpath("//button[@title='Go To Home Page']")).size()!=0) {
			
			Result = "Pass";		
			reports.flush();
			driver.quit();
			
		}
		}
		catch(WebDriverException e) {
			Result = "Fail";
			test.log(Status.FAIL, "Service Request function Failed");			
			reports.flush();
			driver.quit();
		}
		return Result;
	}
	
}
