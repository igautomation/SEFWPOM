package pom_dubaitrade;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pom_siebel_base.Siebel_Login;
import utilities.Chrome_Browser;
import utilities.Elements;
import utilities.IE_Browser;
import utilities.ReadData_DubaiTrade;
import utilities.ReadData_Siebel;
import utilities.Reports;
import utilities.ScreenShot;

public class DubaiTrade_TruckRequest {

	
	public WebDriver driver;
	public ExtentTest test;
	public ExtentReports reports;
	public Reports report_Generator;
	public ScreenShot Take_Screenshot;
	public String Result;
	public WebDriverWait wait;
	public Elements Element;
	public HashMap<String, String> map;
	public ReadData_DubaiTrade Read_Inspection_Data;
	private IE_Browser getie;
	private Chrome_Browser getchrome;
	public HashMap<String, String> map_Login_Details;
	public  ReadData_Siebel Read_Exceldata_Hashmap;
	public static Siebel_Login Siebel_login;
	
	
	public String Fn_Dubai_Trade_TruckRelease(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet ) throws Exception  {
		
		this.driver = driver;
		
		this.test = test;
		
		this.reports = reports;
				
		Take_Screenshot = new ScreenShot();
		
		Element = new Elements();
		
		Read_Inspection_Data = new ReadData_DubaiTrade();
		
		map = Read_Inspection_Data.HashMap_Data_Inspection_DubaiTrade(j, Test_Data_Path , Sheet);
		
		Siebel_login = new Siebel_Login();
		
		try {

	 //NewDubaiTrade UI
		if (driver.findElements(By.xpath("//*[@id='step-0']/div[3]/button")).size()!=0) {
			
			Result = Element.Click_Event(driver, test, reports, "//*[@id='step-0']/div[3]/button" , "Click End Tour");
			
		}
		
		Result = Element.Click_Event(driver, test, reports, "//*[@id='menu-button']" , "Click Menu Section");
		
		Thread.sleep(2000);
		
		Result = Element.Click_Event(driver, test, reports, "//body/div[2]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/ul[1]/li[13]/span[1]/span[2]" , "Click Inspection Section");
		
		Result = Element.Click_Event(driver, test, reports, "//span[@id='25790589']" , "Truck Release Requests");
		
		Thread.sleep(3000); 
		
		//selectFrame
			Result = Element.Switch_Frame(driver, test, reports, "//iframe[@id='contentFrame']");
			
			Thread.sleep(2000);	
			
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Initiate/Track Truck Release Requests:New']" , "Truck Release Requests New Button");	
								
			//Enter Declaration
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Declaration Number']" , "Enter Declaration" , map.get("Declaration_No"));
			
			test.log(Status.INFO, "Declaration No : " + map.get("Declaration_No") );
			
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Initiate Truck Release:Initiate Truck Release']" , "Truck Release Requests Initiate");
			
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSRepresentativeName_Label']" , "Representative Name" , map.get("Representative_Name1"));
			
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Mobile_Number_Label']" , "Representative Mobile Number" , map.get("Representative_Mobile_Number"));
			
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Release Request Details:Save']" , "Save Button");
			
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Number:New']" , "New Button");
			
			Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Action_taken']" , "Truck Number TExt Box" , map.get("Truck_Number"));
			
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Number:Save']" , "Save Button");
			
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Release Request Details:Next']" , "Next Button");
			
			Thread.sleep(2000);
			
			Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Payment_Mode']" , "Payment Mode Text Select");
			
			Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Payment_Mode']" , "Payment Mode Text box" , map.get("Payment_Mode"));
			
			Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Transaction_Type']" , "Transaction type Text Select");
			
			Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Transaction_Type']" , "Transaction type Text box" , map.get("Credit_Account"));
			
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Payments:Save']" , "Payment Save Button");
			
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Payments:Make Payment']" , "Make Payment Button");
			
			Thread.sleep(10000);
			
			Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Release Request Details:Submit']" , "Submit Button");
			
			Thread.sleep(3000);
				
			Result = "Pass";
				
			reports.flush();
				
			driver.quit();
				
		}
		catch(WebDriverException e) {
			
			test.log(Status.FAIL, "Dubai Trade Truck Release Request Failed");
			//test.log(Status.INFO, "Booking Reference Number Generated : " + Test_Data_Login_Details(Environment, Test_Data_Path, "Dubai_Trade_UserID") );
			reports.flush();
			driver.quit();
			Result = "Fail";
			
		}
		
		return Result;
		
	}
	
}
