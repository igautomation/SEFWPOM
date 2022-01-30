package pom_siebel_base;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.Chrome_Browser;
import utilities.Elements;
import utilities.IE_Browser;
import utilities.ReadData_DubaiTrade;
import utilities.ReadData_Siebel;
import utilities.Reports;
import utilities.ScreenShot;

public class Siebel_Create_truck_Request {
	
	public WebDriver driver;
	public ExtentTest test;
	public ExtentReports reports;
	public Reports report_Generator;
	public ScreenShot Take_Screenshot;
	public String Result;
	public WebDriverWait wait;
	public Elements Element;
	public HashMap<String, String> map_Inspection;
	public ReadData_DubaiTrade Read_Inspection_Data;
	public ReadData_Siebel ReadData_Siebel_Hasmap;
	private IE_Browser getie;
	private Chrome_Browser getchrome;
	public HashMap<String, String> map_Login_Details;
	public  ReadData_Siebel Read_Exceldata_Hashmap;
	public static Siebel_Login Siebel_login;
	
	public String Create_truck_Request(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet , String Environment) throws Exception {
		
		this.driver = driver;
		
		this.test = test;
		
		this.reports = reports;
		
		this.driver = driver;
		
		this.test = test;
		
		this.reports = reports;
				
		Take_Screenshot = new ScreenShot();
		
		Element = new Elements();
		
		Read_Inspection_Data = new ReadData_DubaiTrade();
		
		ReadData_Siebel_Hasmap = new ReadData_Siebel();
		
		if (Sheet.equals("Inspection_DubaiTrade")) {
			// Create Local Hash Map for data sheet
			map_Inspection = Read_Inspection_Data.HashMap_Data_Inspection_DubaiTrade(j, Test_Data_Path,
					Sheet);
		}
		else if(Sheet.equals("Inspection_Siebel")) {
			// Create Local Hash Map for data sheet
			map_Inspection = ReadData_Siebel_Hasmap.HashMap_Data_Inspection_Siebel(j, Test_Data_Path, Sheet);
		}
		
		Siebel_login = new Siebel_Login();
		
		try {
			
			
			//Truck Request
			Result = Element.Click_Event(driver, test, reports, "//a[contains(text(), 'Truck Release Requests')]", "Truck Release Requests");
			
			//New Truck Request
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Initiate/Track Truck Release Requests:New']", "Truck Release Requests New button");
			
			//Declaration Number No
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Declaration Number']", "Declaration Number No textbox", map_Inspection.get("Declaration_No"));
			
			//Initiate Truck Release
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Initiate Truck Release']", "Initiate Truck Release Button");
			
			//Contact Email
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Contact Email']", "Contact Email text box", map_Inspection.get("Contact_Email"));
			
			//Representative name
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSRepresentativeName_Label']", "Representative name textbox", map_Inspection.get("Representative_Name"));
			
			//Contact Number TextBox
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactNumber_Label']", "Contact Number TextBox", map_Inspection.get("Contact_Number"));
			
			//Mobile number Textbox
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Mobile_Number_Label']", "Mobile number Textbox", map_Inspection.get("Mobile_Number"));
			
			//save Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Release Request Details:Save']", "save Button");
			
			//Truck number New Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Number:New']", "Truck number New Button");
			
			Thread.sleep(3000);
			
			//Truck Number Text box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Truck Number']", "Truck Number Text box", map_Inspection.get("Truck_Number"));
			
			Thread.sleep(3000);
			
			//save button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Number:Save']", "save button");
			
			//Generate Payment Details
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Release Request Details:Generate Payment Details']", "Generate Payment Details");
			
			
			//Self Assign Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Release Request Details:Self Assign']", "Self Assign Button");
			
			//Truck Release Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Release Request Details:Truck Release']", "Truck Release Button");
			
			Thread.sleep(3000);
			
			//Ok Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Release:Ok']", "Ok Button");
			
			//Back To Queue Button
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Release Request Details:Back To Queue']", "Back To Queue Button");
			
			Result ="Pass";
			
			
			
		}
		catch(WebDriverException e){
			
			Result = "Fail";
		}
		return Result;
		
	}

}
