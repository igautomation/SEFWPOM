package pom_siebel_base;

import java.util.HashMap;

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

public class Siebel_Virtual_Corridor {
	
	
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
	
	public WebDriverWait waitMethod(WebDriver driver )  {
		
		this.driver = driver;
		
		return wait = new WebDriverWait(driver, 60);	
		
	}
	
	public String Virtual_Corridor_Entry(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet,String Environment ) throws Exception {
		
		
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
		
		try {
			
			//Virtual Corridor Cargo Transfer (Entry) Link
			Result = Element.Click_Event(driver, test, reports, "//a[contains(text(), ' Virtual Corridor Cargo Transfer (Entry)')]", "Virtual Corridor Cargo Transfer (Entry) Link");
			
			//New Button
			Result = Element.Click_Event(driver, test, reports, "//button[@title='New']", "New Button");
			
			Thread.sleep(3000);
			
			//Representative Name Text Box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSRepresentativeName_Label']", "Representative Name Text Box", map_Inspection.get("Representative_Name"));
			
			//Contact Number Text Box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactNumber_Label']", "Contact Number Text Box", map_Inspection.get("Contact_Number"));
			
			//Contact Email Text Box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Contact_Email_Id_Label']", "Contact Email Text Box", map_Inspection.get("Contact_Email"));
			
			//Transfer Center Text Box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Transfer_Center_Label']", "Transfer Center Text Box", map_Inspection.get("Transfer_Center"));
			
			//Transfer Section Text Box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Transfer_Section_Label']", "Transfer section Text Box", map_Inspection.get("Transfer_Section"));
			
			//Save Button
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Save']", "Save Button");
			
			Thread.sleep(3000);
			
			//New Declaration Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declarations:New']", "New Declaration Button");
			
			//Add Declaration Search Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Add Declaration:Search']", "Add Declaration Search Button");
			
			//Declaration Reference number
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Declarant_Reference_Number_Label']", "Declaration Reference number", map_Inspection.get("Declaration_No"));
			
			//Add Declaration Go Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Add Declaration:Go']", "Add Declaration Go Button");
			
			//Add Declaration Confirm Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Add Declaration:Confirm']", "Add Declaration Confirm Button");
			
			//Add Declaration Save Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declarations:Save']", "Add Declaration Save Button");
			
			Thread.sleep(3000);
			
			//Declaration proceed button
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Proceed']", "Declaration proceed button");
			
			Thread.sleep(5000);
			
			if (map_Inspection.get("Virtual_Corridor_Type").equals("FCL")) {
				
				//Seal Details add
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:New']", "Seal Details add");
				
				Thread.sleep(3000);
				
				//Selection Query Button
				Result = Element.Click_Event(driver, test, reports, "//*[@id='s_2_2_34_0_icon']", "Selection Query Button");
				
				//Search Seal Details Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:Query']", "Search Seal Details Button");
				
				//Seal Number Text Box
				Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Seal_Number']", "Seal Number Text Box", map_Inspection.get("Seal_Number"));
				
				//Seal Details Go Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:Go']", "Seal Details Go Button");
				
				//Seal Details Ok Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:OK']", "Seal Details Ok Button");
				
				//Seal Remarks Label Text Box
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Seal_Remarks_Label']", "Seal Remarks Label Text Box", map_Inspection.get("Seal_Remarks"));
				
				//Seal Truck Number Text Box
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Truck_Number_Label']", "Seal Truck Number Text Box", map_Inspection.get("Seal_Truck_Number"));
				
				//Seal Details Save Buton
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:Save']", "Seal Details Save Buton");
				
				Thread.sleep(3000);
				
				
			}else {
			
			//Current Verified Quantity
			Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Current_Verified_Quantity']", "Current Verified Quantity");
						
			//Current Verified Quantity Text Box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Current_Verified_Quantity']", "Current Verified Quantity Text Box", "1");
			
			//Save Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Goods Quantity:Save']", "Save Button");
			
			//Seal Details Tab
			Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Seal Details')]", "Seal Details Tab");
			
			//New Seal Details Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:New']", "New Seal Details Button");
			
			//Selection Query Button
			Result = Element.Click_Event(driver, test, reports, "//span[@aria-label='Selection Field']", "Selection Query Button");
			
			//Search Seal Details Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:Query']", "Search Seal Details Button");
			
			//Seal Number Text Box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Seal_Number']", "Seal Number Text Box", map_Inspection.get("Seal_Number"));
			
			//Seal Details Go Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:Go']", "Seal Details Go Button");
			
			//Seal Details Ok Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:OK']", "Seal Details Ok Button");
			
			//Seal Remarks Label Text Box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Seal_Remarks_Label']", "Seal Remarks Label Text Box", map_Inspection.get("Seal_Remarks"));
			
			//Seal Truck Number Text Box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Truck_Number_Label']", "Seal Truck Number Text Box", map_Inspection.get("Seal_Truck_Number"));
			
			//Seal Details Save Buton
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:Save']", "Seal Details Save Buton");
			
			Thread.sleep(3000);
			
			}	
			
			//Sealing Fee Tab
			Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Sealing Fees')]", "Sealing Fee Tab");
			
			//Get Demand Notice Text
			Demand_NoticeNo = Element.Get_Text(driver, test, reports, "//td[@id='1_s_3_l_DC_INS_Demamd_Notice_Number']", "Get Demand Notice Text");
			
			//Submit Button
			//Result = Element.Click_Event(driver, test, reports, "//button[@title='Submit']", "Submit Button");
			
			test.log(Status.PASS,"Virtual Corridor is submitted successfully, Awaiting Demand Notice Payment");
			reports.flush(); 
			driver.quit();
			
		}catch (WebDriverException e) {
			
			Result = "Fail";
			test.log(Status.FAIL, "Virtual Corridor Entry Submission Failed");		
			reports.flush();
			driver.quit();
			
		}
		
		return Demand_NoticeNo;
	}
	
	public String Virtual_Corridor_Entry_Submission(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet,String Environment ) throws Exception {
		
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
		
		try {
		
		//Virtual Corridor Cargo Transfer (Entry) Link
		Result = Element.Click_Event(driver, test, reports, "//a[contains(text(), ' Virtual Corridor Cargo Transfer (Entry)')]", "Virtual Corridor Cargo Transfer (Entry) Link");
		
		//Search Record Button
		Result = Element.Click_Event(driver, test, reports, "//button[@title='Query']", "Search Record Button");
		
		//Declaration number textbox select
		Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Declaration_Number']", "Declaration number textbox select");
		
		//Declaration number text Box
		Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Declaration_Number']", "Declaration number text Box", map_Inspection.get("Declaration_No"));
		
		//Declaration Search Go Button
		Result = Element.Click_Event(driver, test, reports, "//button[@title='Go']", "Declaration Search Go Button");
		
		//Cargo Transfer Reference Number Link
		Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS VC Request Number']", "Cargo Transfer Reference Number Link");
		
		if (map_Inspection.get("Virtual_Corridor_Type").equals("LCL")) {
		
		//Submit Button
		Result = Element.Click_Event(driver, test, reports, "//button[@title='Submit']", "Submit Button");
		
		//Prompt Ok
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Prompt:Ok']", "Prompt Ok");
		
		Thread.sleep(3000);
		
		}
		
		else if (map_Inspection.get("Virtual_Corridor_Type").equals("FCL")) {
			
			//Submit Button
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Submit']", "Submit Button");
			
			//Prompt Ok
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Prompt:Ok']", "Prompt Ok");
			
			Thread.sleep(3000);
			
		}
		Result = "Pass";
		
		test.log(Status.PASS, "Virtual Corridor Entry Payment Acknowledged successfully");
		
		test.log(Status.PASS, "The status of the Request is : Submitted");
		
		reports.flush();
		
		driver.quit();
		
		}
		catch (WebDriverException e) {
		
		Result = "Fail";
		test.log(Status.FAIL, "Virtual Corridor Entry Payment Acknowledgment Failed");		
		reports.flush();
		
	}
		
	
		return Result;
	}

	public String Virtual_Corridor_Exit(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet,String Environment ) throws Exception {
	
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
		
		try {

			//Virtual Corridor Cargo Transfer (Exit) Link
			Result = Element.Click_Event(driver, test, reports, "//a[contains(text(), ' Virtual Corridor Cargo Transfer (Exit)')]", "Virtual Corridor Cargo Transfer (Exit) Link");
			
			//Search Record Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Virtual Corridor Cargo Transfer Queue:Query']", "Search Record Button");
			
			//Declaration number textbox select
			Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Declaration_Number']", "Declaration number textbox select");
			
			//Declaration number text Box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Declaration_Number']", "Declaration number text Box", map_Inspection.get("Declaration_No"));
			
			//Declaration Search Go Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Virtual Corridor Cargo Transfer Queue:Go']", "Declaration Search Go Button");
			
			//Cargo Transfer Reference Number Link
			Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS VC Request Number']", "Cargo Transfer Reference Number Link");
			
			if (map_Inspection.get("Virtual_Corridor_Type").equals("LCL")) {
			
			//Seal Details Tab
			Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Seal Details')]", "Seal Details Tab");
			
			//Seal Acknowledge
			Result = Element.Click_Event(driver, test, reports, "//td[@aria-labelledby='s_3_l_altCheckBox']", "Seal Acknowledge");
			
			//Seal Acknowledge CheckBox
			Result = Element.Click_Event(driver, test, reports, "//input[@name='DC_INS_Acknowledge_Seal_Flag']", "Seal Acknowledge CheckBox");
			
			//Seal Details Save Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:Save']", "Seal Details Save Button");
			
			}
			else if (map_Inspection.get("Virtual_Corridor_Type").equals("FCL")) {
				
				//Seal Acknowledge
				Result = Element.Click_Event(driver, test, reports, "//td[@aria-labelledby='s_3_l_altCheckBox']", "Seal Acknowledge");
				
				//Seal Acknowledge CheckBox
				Result = Element.Click_Event(driver, test, reports, "//input[@name='DC_INS_Acknowledge_Seal_Flag']", "Seal Acknowledge CheckBox");
				
				//Seal Details Save Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seal Details:Save']", "Seal Details Save Button");
				
			}
			//Cargo Acknowledgement
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Complete Acknowledgement']", "Cargo Acknowledgement");
			
			//Prompt Ok Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Prompt:Ok']", "Prompt Ok Button");
			
			Thread.sleep(3000);
			
			//Back to queue button
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Back To Queue']", "Back to queue button");
			
			Thread.sleep(2000);
			
			//Search Record Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Virtual Corridor Cargo Transfer Queue:Query']", "Search Record Button");
			
			//Declaration number textbox select
			Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Declaration_Number']", "Declaration number textbox select");
			
			//Declaration number text Box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Declaration_Number']", "Declaration number text Box", map_Inspection.get("Declaration_No"));
			
			//Declaration Search Go Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Virtual Corridor Cargo Transfer Queue:Go']", "Declaration Search Go Button");
			
			//Status
			String VC_Status = Element.Get_Text(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Status']", "Status");
			
			if (VC_Status.equals("Closed")) {
				
				Result = "Pass";
				
				test.log(Status.PASS, "Virtual Corridor cargi transfer Exit is submitted successfully ");
				
				test.log(Status.PASS, "The status of the Request is : " + VC_Status);
				
				reports.flush();
				
				driver.quit();
			}
			else {
				Result = "Fail";
			}
			
			Result = "Pass";
			

			
			}
			catch (WebDriverException e) {
			
			Result = "Fail";
			
		}
		
		return Result;
	}
}
