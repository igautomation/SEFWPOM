package pom_dubaitrade;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utilities.Elements;
import utilities.ReadData_DubaiTrade;
import utilities.Reports;
import utilities.ScreenShot;



public class Track_Inspection {
	
	public WebDriver driver;
	public ExtentTest test;
	public ExtentReports reports;
	public Reports report_Generator;
	public ScreenShot Take_Screenshot;
	public String Result;
	public Elements Element;
	public HashMap<String, String> map_Inspection_DubaiTrade;
	public ReadData_DubaiTrade Read_Inspection_Data;
	
	
	public String Fn_Track_Inspection(WebDriver driver , ExtentTest test, ExtentReports reports , int j , String Test_Data_Path , String Sheet ) throws Exception {
		
		this.driver = driver;
		
		this.test = test;
		
		this.reports = reports;
				
		Take_Screenshot = new ScreenShot();
		
		Element = new Elements();
		
		Read_Inspection_Data = new ReadData_DubaiTrade();
		
		map_Inspection_DubaiTrade = Read_Inspection_Data.HashMap_Data_Inspection_DubaiTrade(j, Test_Data_Path , Sheet);
		
		try {
			
			 //NewDubaiTrade UI
				if (driver.findElements(By.xpath("//*[@id='step-0']/div[3]/button")).size()!=0) {
					
					Result = Element.Click_Event(driver, test, reports, "//*[@id='step-0']/div[3]/button" , "Click End Tour");
					
				}
			
		 //NewDubaiTrade UI
			Result = Element.Click_Event(driver, test, reports, "//*[@id='menu-button']" , "Click Menu Section");
			Thread.sleep(2000);
			Result = Element.Click_Event(driver, test, reports, "//body/div[2]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/ul[1]/li[13]/span[1]/span[2]" , "Click Inspection Section");
			Result = Element.Click_Event(driver, test, reports, "//span[@id='25788304']" , "Click Inspection-Cleared");
			Thread.sleep(3000);

	    //selectFrame
			Result = Element.Switch_Frame(driver, test, reports, "//iframe[@id='contentFrame']" );
				
		//Search Booking Button
			Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_13_0_Ctrl']" , "Search Booking Button");

		//Enter Booking Request Number
			Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_DC_INS_Booking_Reference_Number']" ,"Enter Booking Request Number", map_Inspection_DubaiTrade.get("Booking_Ref_No"));
			
		//Activate Declaration Search Field
			//Element.Click_Event(driver, "//td[@id='1_s_1_l_DC_INS_Declaration_Number']");			
			
		//Enter Booking Request Number
			//Element.TextBox_Value(driver, "//*[@id='1_DC_INS_Booking_Reference_Number']", declaration_No);			
			
		//Focus Out
		//	Element.Click_Event(driver, "//tb[@aria-labelledby='s_1_l_altCombo']");
			
		//Go button
			Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_11_0_Ctrl']" , "Go button");
			
			Thread.sleep(2000);

			
		//Search Results		    
	        //System.out.println("Booking Reference Number : " + Element.Get_Text(driver, "//td[@id='1_s_1_l_DC_INS_Booking_Reference_Number']"));
	        	
		String Ref_No = Element.Get_Text(driver, test, reports, "//*[@id=\"1_s_1_l_DC_INS_Booking_Reference_Number\"]" , "Reference no text");
		
		if (Ref_No.equals(map_Inspection_DubaiTrade.get("Booking_Ref_No"))) {
			
			System.out.println("Searh successful :" + Ref_No);
			Result = "Pass";			
			
		}
	        
		else {
			Result = "Fail";
			test.log(Status.FAIL, "Booking Reference Number Doesn't Exist");
			//test.log(Status.INFO, "Booking Reference Number Doesn't Exist : " + Test_Data_Login_Details(Environment, Test_Data_Path, "Dubai_Trade_UserID") );
			reports.flush();
			Result = "Fail";
		}
		
		}
		catch(WebDriverException e){
			test.log(Status.FAIL, "Track Inspection Functionality Failed");
			String Screen = Take_Screenshot.take_screenshot(driver);
			test.addScreenCaptureFromPath(Screen);			
			reports.flush();
		}
		
		return Result;
	}
	
}
