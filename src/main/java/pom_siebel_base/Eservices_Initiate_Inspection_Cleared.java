package pom_siebel_base;

import java.util.ArrayList;
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

public class Eservices_Initiate_Inspection_Cleared {
	
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
	public String Demand_NoticeNo;
	
	public HashMap<String, String> map_Environment_Details;
	public HashMap<String, String> map_Inspection_Siebel;
	public HashMap<String, String> map_Inspection;
	public List<String> Demand_NoticeNo1;
	
	public WebDriverWait waitMethod(WebDriver driver )  {
		
		this.driver = driver;
		
		return wait = new WebDriverWait(driver, 60);	
		
	}
	
	public List<String> EService_Cleared(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j ,String Test_Data_Path, String Sheet,String Environment ) throws Exception {
		
		this.driver = driver;

		this.test = test;

		this.reports = reports;

		Take_Screenshot = new ScreenShot();

		Element = new Elements();
		
		ReadData_Environment_Details = new ReadData_Environment();

		Read_Exceldata_Hashmap = new ReadData_DubaiTrade();
		
		ReadData_Siebel_Hasmap = new ReadData_Siebel();
		
		List<String> Demand_NoticeNo1 = new ArrayList<String>();

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
			
			if (map_Inspection.get("Multiple_Declaration_Flag").equals("ON")) {
				
				//Create Inspection Requests
				Result = Element.Click_Event(driver, test, reports, "//a[contains(text(), 'Create Inspection Requests')]", "Create Inspection Requests");
				
				//New Booking Request(Multiple)
				Result = Element.Click_Event(driver, test, reports, "//button[@title='New Booking Request(Multiple)']", "New Booking Request(Multiple)");
				
				//Representative Name
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSRepresentativeName_Label']", "Representative Name", map_Inspection.get("Representative_Name"));
				
				//Contact Number
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactNumber_Label']", "Contact Number", map_Inspection.get("Contact_Number"));
				
				//Contact Email
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Contact_Email_Id_Label']", "Contact Email", map_Inspection.get("Contact_Email"));
				
				//Save Button
				Result = Element.Click_Event(driver, test, reports, "//button[@title='Save']", "Create Inspection Requests");
				
				for (String Dec_No : numbers) {
					
					//Add Declaration
					Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declarations:New']", "Add Declaration");
					
					//Search
					Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Add Declaration:Search']", "Add Declaration Search");
					
					//Enter Dec No
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Declaration Number']", "Enter Dec No", Dec_No);
					
					//Go Declaration
					Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Add Declaration:Go']", "Add Declaration:Go");
					
					//Confirm Button
					Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Add Declaration:Confirm']", "Add Declaration:Confirm");
					
					Thread.sleep(5000);
					
				}
				
				//Generate Payment Details Button
				Result = Element.Click_Event(driver, test, reports, "//button[@title='Generate Payment Details']", "Generate Payment Details Button");
				
				Thread.sleep(3000);
				
				//Confirm Button
				Result = Element.Click_Event(driver, test, reports, "//button[@title='Confirm']", "Confirm Button");
				

				
				for (String Dec_No : numbers) {
					
				System.out.println("//td[contains(text(), '"+Dec_No+"')]");
				
				Result = Element.Click_Event(driver, test, reports, "//td[contains(text(), '"+Dec_No+"')]", "Confirm Button");
					
				Demand_NoticeNo1.add(Element.Get_Text(driver, test, reports, "//td[@id='1_s_3_l_DC_INS_Demamd_Notice_Number']", "Demand Notice No"));
				
				}
				
				for(String DD : Demand_NoticeNo1) {
					System.out.println(DD);
					test.log(Status.INFO, "Demand Notice No : " + DD);
				}
				Thread.sleep(5000);
				
				Result = "Pass";				
				reports.flush();
				driver.quit();
			}
			else {
			
		//Create Inspection Requests
		Result = Element.Click_Event(driver, test, reports, "//a[contains(text(), 'Create Inspection Requests')]", "Create Inspection Requests");
		
		//New Booking Request
		Result = Element.Click_Event(driver, test, reports, "//button[@title='New Booking Request']", "New Booking Request");
		
		//Representative Name
		Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSRepresentativeName_Label']", "Representative Name", map_Inspection.get("Representative_Name"));
		
		//Contact Number
		Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactNumber_Label']", "Contact Number", map_Inspection.get("Contact_Number"));
		
		//Contact Email
		Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Contact_Email_Id_Label']", "Contact Email", map_Inspection.get("Contact_Email"));
		
		//Add Declaration
		Result = Element.Click_Event(driver, test, reports, "//button[@title='Add Declaration']", "Add Declaration");
		
		//Search Button
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Add Declaration:Search']", "Search Button");
		
		//Declaration Number Text box
		Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Declarant_Reference_Number_Label']", "Declaration Number Text box", map_Inspection.get("Declaration_No"));
		
		//Add Declaration Go button
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Add Declaration:Go']", "Add Declaration Go button");
		
		//Confirm Button
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Add Declaration:Confirm']", "Confirm Button");
		
		//Generate Payment Details Button
		Result = Element.Click_Event(driver, test, reports, "//button[@title='Generate Payment Details']", "Generate Payment Details Button");
		
		//Confirm Button
		Result = Element.Click_Event(driver, test, reports, "//button[@title='Confirm']", "Confirm Button");
		
		Thread.sleep(5000);

		//Demand Notice No
		Demand_NoticeNo1.add(Element.Get_Text(driver, test, reports, "//td[@id='1_s_3_l_DC_INS_Demamd_Notice_Number']", "Demand Notice No"));
		
		System.out.println(Demand_NoticeNo);
		
		Thread.sleep(5000);		

		
		Result = "Pass";
		test.log(Status.INFO, "Demand Notice No : " + Demand_NoticeNo);
		reports.flush();
		driver.quit();
			}
		}
		catch(WebDriverException e) {
			
			Result = "Fail";
			test.log(Status.FAIL, "EService_Booking Request function Failed");			
			reports.flush();
			driver.quit();
			
		}
		
		return Demand_NoticeNo1;
		
	}

}
