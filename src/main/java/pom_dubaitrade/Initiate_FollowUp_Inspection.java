package pom_dubaitrade;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utilities.Elements;
import utilities.ReadData_DubaiTrade;
import utilities.Reports;
import utilities.ScreenShot;


public class Initiate_FollowUp_Inspection {
	
	public WebDriver driver;
	public ExtentTest test;
	public ExtentReports reports;
	public Reports report_Generator;
	public ScreenShot Take_Screenshot;
	public String Result;
	public Elements Element;	
	public HashMap<String, String> map;
	public ReadData_DubaiTrade Read_Inspection_Data;
	
	
	public String Fn_Initiate_FollowUp_Inspection(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet ) throws Exception  {
		
		this.driver = driver;
		
		this.test = test;
		
		this.reports = reports;
				
		Take_Screenshot = new ScreenShot();
		
		Element = new Elements();
		
		Read_Inspection_Data = new ReadData_DubaiTrade();
		
		map = Read_Inspection_Data.HashMap_Data_Inspection_DubaiTrade(j, Test_Data_Path , Sheet);
		
		try {
			
		 //NewDubaiTrade UI
			Result = Element.Click_Event(driver, test, reports, "//*[@id=\"menu-button\"]" , "Click Menu Section");
			Thread.sleep(2000);
			Result = Element.Click_Event(driver, test, reports, "//body/div[2]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/ul[1]/li[13]/span[1]/span[2]" , "Click Inspection Section");
			Result = Element.Click_Event(driver, test, reports, "//span[@id='25789807']" , "Click Initiate FollowUp Inspection");
			Thread.sleep(3000);
			
		//selectFrame
			Result = Element.Switch_Frame(driver, test, reports, "//iframe[@id='contentFrame']");
					
		//Add declaration Button
			//Element.Click_Event(driver, "//*[@id='s_1_1_28_0_Ctrl']");
			
		//Search declaration Button
			Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_15_0_Ctrl']", "Search declaration Button");							

		//Enter Declaration
			Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_DC_INS_Declaration_Number']" , "Enter Declaration", map.get("Declaration_No"));
			
		//Focus Out
		//	Element.Click_Event(driver, "//tb[@aria-labelledby='s_1_l_altCombo']");
			
		//Save Record button
			//Element.Click_Event(driver, "//*[@id='s_1_1_27_0_Ctrl']");
			
		//Go button
			Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_13_0_Ctrl']", "Go button");			
			
		//Record Save confirmation message
			//System.out.println("Saved Successful" + Element.Get_Text(driver, "//*[@id='CommitNotifyContainer']"));			
			
		//Validate Inspection
			Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_25_0_Ctrl']", "Validate Inspection");
		
			Thread.sleep(5000);
			
		//Initiate Inspection button	
			Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_22_0_Ctrl']", "Initiate Inspection button");
			
		//Inspection centre
		//	Element.TextBox_Value(driver, "", Inspection_center);
			
		//Inspection section
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_2_1_20_0']","Inspection section", map.get("Inspection_Section"));
			
		//Contact Email
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_2_1_14_0']","Contact Email", map.get("Contact_Email"));
			
		//Representative Name
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_2_1_27_0']","Representative Name", map.get("Representative_Name"));
			
		//Contact Number
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_2_1_16_0']","Contact Number", map.get("Contact_Number"));
			
		//Mobile Number
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_2_1_15_0']","Mobile Number", map.get("Mobile_Number"));
			
		//Seal Flag
			Result = Element.Check_Box(driver, test, reports, "//input[@name='s_2_1_12_0']", "ON", "Seal Flag");
			
		//Seal Quantity
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_2_1_24_0']","Seal Quantity", "10");		
				
			Thread.sleep(2000);
			
		//Save Record button
			Result = Element.Click_Event(driver, test, reports, "//button[@id='s_2_1_47_0_Ctrl']", "Save Record button");
						
		//Record Save confirmation message			
			//System.out.println("Saved Successful" + Element.Get_Text(driver, "//*[@id='CommitNotifyContainer']"));
		
		//Select All  Goods Details
			Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_3_0_Ctrl']", "Select All  Goods Details");		
			
		//Group Dates Button
			Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_2_0_Ctrl']", "Group Dates Button");
			
			String Preferred_Date = new SimpleDateFormat("MM/dd/Y").format(new Date());
			
		//preferred date 1 text box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_22_0']", "preferred date 1 text box", Preferred_Date);
			
		//preferred Slot 1 text box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_23_0']","preferred Slot 1 text box", map.get("Preferred_Slot_1"));
		
		//Additional preferred Slot text box
			Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_29_0']","Additional preferred Slot text box", map.get("Additional_Preferred_Slot"));
			
			 Thread.sleep(2000);
		
		//Record Save Button
			 Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_32_0_Ctrl']", "Record Save Button");
			
			Thread.sleep(2000);
			
		//Record Save confirmation message
			//System.out.println("Saved Successful" + Element.Get_Text(driver, "//*[@id='CommitNotifyContainer']"));
			
			
		//Group Dates OK Button
			Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_33_0_Ctrl']", "Group Dates OK Button");	
			
		//Record Save Button
			Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_16_0_Ctrl']", "Record Save Button");			
						
		//Next Button
			Result = Element.Click_Event(driver, test, reports, "//*[@id='s_2_1_31_0_Ctrl']", "Next Button");
		
		//Activate Payment Mode element
			Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_3_l_DC_INS_Payment_Mode']", "Activate Payment Mode element");
					
		//Pay mode Selection
			Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Payment_Mode']","Pay mode Selection", "Credit Account");

		//Activate Credit Account element
			Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_3_l_DC_INS_Transaction_Type']", "Activate Credit Account element");				
			
		//Credit Account No Selection
			Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Transaction_Type']","Credit Account No Selection", "1222532");

		//Record Save Button
			Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_19_0_Ctrl']", "Record Save Button");		
			
		//Make Payment
			Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_12_0_Ctrl']", "Make Payment");		
			
		//Payment Success	
			Result = Element.Text_Present(driver, test, reports, "//td[@title='Authorized']","Payment Success");		

		//Record Save Button
			Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_19_0_Ctrl']", "Record Save Button");		
		
		//Submit
			Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_34_0_Ctrl']", "Submit");		
			
		    Thread.sleep(5000);
		    
		 //Submit
		    String bookingRefNo = Element.Get_Text(driver, test, reports, "//input[@aria-label='Booking Reference Number']" , "Submit Bookingref text");
		    
		    
			Result = Element.Get_Text(driver, test, reports, "//input[@aria-label='Booking Reference Number']" , "Submit Bookingref text");
			
	        if (bookingRefNo.isEmpty()){			
				test.log(Status.FAIL, "Initiate Inspection Cleared Failed");
				//test.log(Status.INFO, "Booking Reference Number Generated : " + Test_Data_Login_Details(Environment, Test_Data_Path, "Dubai_Trade_UserID") );
				reports.flush();
				Result = "Fail";
			}	        		       
	        
			else {
				
				Result = "Pass";
			}
					
		}catch(WebDriverException e){
			test.log(Status.FAIL, "Initiate Inspection CDM Functionality Failed");
			String Screen = Take_Screenshot.take_screenshot(driver);
			test.addScreenCaptureFromPath(Screen);			
			reports.flush();
		}
		
		return Result;
	}
	
}
