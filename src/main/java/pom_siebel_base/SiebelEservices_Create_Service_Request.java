package pom_siebel_base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

public class SiebelEservices_Create_Service_Request {

	public WebDriver driver;
	public ExtentTest test;
	public ExtentReports reports;
	public Reports report_Generator;
	public ScreenShot Take_Screenshot;
	public String Result;
	public String Demand_NoticeNo; 
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
	
	public String EServices_Service_Request(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet,String Environment ) throws Exception {
		
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
			
			//Unassigned Request
			Result = Element.Click_Event(driver, test, reports, "//a[contains(text(), 'Create Inspection Requests')]", "Unassigned Requests");
			
			//New Service Request
			Result = Element.Click_Event(driver, test, reports, "//button[@title='New Service Request']", "New Service Request");
			
			//Document Reference
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Document_Reference_Label']" , "Document Reference", map_Inspection.get("Document_Reference"));
			
			//Document Reference Number
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Document_Reference_Number_Label']" , "DC_INS_Document_Reference_Number_Label", map_Inspection.get("Document_Reference_Number"));
			
			//Location
			Result = Element.TextBox_Value(driver, test, reports, "//textarea[@aria-labelledby='DCINSAddress_Label']" , "Location", map_Inspection.get("Location"));
			
			//Representative Name
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSRepresentativeName_Label']" , "DCINSRepresentativeName_Label", map_Inspection.get("Representative_Name"));
			
			//Contact Number
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactNumber_Label']" , "DCINSContactNumber_Label", map_Inspection.get("Contact_Number"));
			
			//Contact E-mail
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactEmailId_Label']" , "DCINSContactEmailId_Label",map_Inspection.get("Contact_Email") );
			
			//Save
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Save']", "Save Button");
			
			//Generate Payment Details
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Generate Payment Details']", "Generate Payment Details");
			
			//Confirm button
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Confirm']", "Confirm button");
			
			Thread.sleep(5000);
			
			//Demand Notice No
			Demand_NoticeNo = Element.Get_Text(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Demamd_Notice_Number']", "Demand Notice No");
			
			System.out.println(Demand_NoticeNo);
			
			Thread.sleep(5000);
			
			Result = "Pass";
			test.log(Status.INFO, "Demand Notice No : " + Demand_NoticeNo);
			reports.flush();
			driver.quit();
			
			
		}
		catch(WebDriverException e) {
			
			Result = "Fail";
			test.log(Status.FAIL, "EService_Service Request function Failed");			
			reports.flush();
			driver.quit();
			
		}
		return Demand_NoticeNo;
		
	}
	
}
