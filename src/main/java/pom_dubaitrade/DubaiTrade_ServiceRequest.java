package pom_dubaitrade;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.Elements;
import utilities.ReadData_DubaiTrade;
import utilities.Reports;
import utilities.ScreenShot;

public class DubaiTrade_ServiceRequest {

	
	public WebDriver driver;
	public ExtentTest test;
	public ExtentReports reports;
	public Reports report_Generator;
	public ScreenShot Take_Screenshot;
	public String Result;
	public Elements Element;
	public WebDriverWait wait;
	public HashMap<String, String> map_Inspection_DubaiTrade;
	public HashMap<String, String> map_Login_Details;
	public ReadData_DubaiTrade Read_Inspection_Data;
	public utilities.ReadData_Environment ReadData_Environment;
	
	public String Service_Request(WebDriver driver , ExtentTest test, String Environment, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet ) throws Exception  {

		
		this.driver = driver;

		this.test = test;

		this.reports = reports;

		Take_Screenshot = new ScreenShot();

		Element = new Elements();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		Read_Inspection_Data = new ReadData_DubaiTrade();

		map_Inspection_DubaiTrade = Read_Inspection_Data.HashMap_Data_Inspection_DubaiTrade(j, Test_Data_Path, Sheet);

		ReadData_Environment = new utilities.ReadData_Environment();

		// Create Local Hash Map for DubaiTrade_Login sheet
		map_Login_Details = ReadData_Environment.HashMap_Data_Environment_Details(Environment, Test_Data_Path);
		
		/*
		 * List<String> numbers =
		 * Arrays.asList(map_Inspection_DubaiTrade.get("Declaration_No").split(","));
		 * 
		 * int k = numbers.size();
		 * 
		 * for (String Dec_No : numbers) { test.log(Status.INFO, "Declaration No : " +
		 * Dec_No); }
		 */
		try {
					
					//NewDubaiTrade UI
					Result = Element.Click_Event(driver, test, reports, "//*[@id=\"menu-button\"]" , "Click Menu Section");
					//Thread.Sleep(1000);
					Result = Element.Click_Event(driver, test, reports, "//ul[@class='top-level']/li[@class='top-head head closed']/span[@class='title']/span[contains(text(), 'Inspection Request')]" , "Click Inspection Section");
					//Thread.Sleep(3000);
					
					if (map_Login_Details.get("Environment").equals("SIT2")) {
						
						Result = Element.Click_Event(driver, test, reports, "//span[contains(text(), 'Service Request S2')]" , "Click Track-Inspection");
						
					}else {
					
					Result = Element.Click_Event(driver, test, reports, "//span[contains(text(), 'Service Request')]" , "Click Track-Inspection");
					
					//Thread.Sleep(3000);
					}
					
					if (map_Login_Details.get("Environment").equals("SIT2")) {
							
						if (driver.findElements(By.xpath("//*[@id='remember-me']")).size()!=0) {
							//remember me check box
							Result = Element.Click_Event(driver, test, reports, "//*[@id='remember-me']" , "remember me check box");
							
							//Ok Button
							Result = Element.Click_Event(driver, test, reports, "//*[@id='modifyid']" , "Ok Button");				
						}
					}
					
				//selectFrame
					Result = Element.Switch_Frame(driver, test, reports, "//iframe[@id='contentFrame']");
					
				//create service Request
					Result = Element.Click_Event(driver, test, reports, "//button[@title='Create Service Request']" , "create service Request");
					
				//DC_INS_Document_Reference_Label
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Document_Reference_Label']" , "Document_Reference_Label", map_Inspection_DubaiTrade.get("Document_Reference"));
					
				//DC_INS_Document_Reference_Number_Label
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Document_Reference_Number_Label']" , "Document_Reference_Number", map_Inspection_DubaiTrade.get("Document_Reference_Number"));
					
				//Document Center	
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSInspectionCentre_Label']" , "Document Center", map_Inspection_DubaiTrade.get("Inspection_Centre"));
				
				//Document_Section
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSInspectionSection_Label']" , "DCINSInspectionSection_Label", map_Inspection_DubaiTrade.get("Inspection_Section"));	
					
				//Location	
					Result = Element.TextBox_Value(driver, test, reports, "//textarea[@aria-labelledby='DC_INS_Location_Label']" , "Location", map_Inspection_DubaiTrade.get("Location"));
					
					
					String Preferred_Date = new SimpleDateFormat("MM/dd/Y").format(new Date());
					
				//DC_INS_Preferred_Date_1_Label
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Preferred_Date_1_Label']" , "DC_INS_Preferred_Date_1_Label", Preferred_Date);
					
				//DC_INS_Preferred_Slot_1_Label
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Preferred_Slot_1_Label']" , "DC_INS_Preferred_Slot_1_Label", map_Inspection_DubaiTrade.get("Preferred_Slot_1"));
					
				//Additional_Preferred_Slot_Label
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='Additional_Preferred_Slot_Label']" , "Additional_Preferred_Slot_Label", map_Inspection_DubaiTrade.get("Additional_Preferred_Slot"));
					
				//DCINSContactName_Label
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactName_Label']" , "DCINSContactName_Label", map_Inspection_DubaiTrade.get("Contact_Name"));
					
				//DCINSRepresentativeName_Label
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSRepresentativeName_Label']" , "DCINSRepresentativeName_Label", map_Inspection_DubaiTrade.get("Representative_Name"));
					
				//DCINSContactNumber_Label
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactNumber_Label']" , "DCINSContactNumber_Label", map_Inspection_DubaiTrade.get("Contact_Number"));
					
				//DCINSContactEmailId_Label
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactEmailId_Label']" , "DCINSContactEmailId_Label", map_Inspection_DubaiTrade.get("Contact_Email"));
					
				//DC_INS_Mobile_Number_Label
					Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Mobile_Number_Label']" , "DC_INS_Mobile_Number_Label", map_Inspection_DubaiTrade.get("Mobile_Number"));
					
				//Save Button
					Result = Element.Click_Event(driver, test, reports, "//button[@title='Save']" , "Save button"); 	
					
				//Next Button
					Result = Element.Click_Event(driver, test, reports, "//button[@title='Next']" , "Next button"); 
					
				//Payment Mode select
					Result = Element.Click_Event(driver, test, reports, "//td[@aria-labelledby='s_3_l_altCombo']" , "Payment Mode select");
					
				//DC_INS_Payment_Mode
					Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Payment_Mode']" , "Input Payment Mode", map_Inspection_DubaiTrade.get("Payment_Mode"));
					
				//Transaction type select	
					Result = Element.Click_Event(driver, test, reports, "//td[@aria-labelledby='s_3_l_altpick']" , "Transaction type select");
					
				//Transaction type
					Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Transaction_Type']" , "Transaction type input", map_Inspection_DubaiTrade.get("Credit_Account"));
					
				//Save Payment Button	
					Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Payments:Save']" , "Save Payment Button");
				
				//Make payment Button
					Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Payments:Make Payment']" , "make Payment Button");
					
				//Submit Button
					Result = Element.Click_Event(driver, test, reports, "//button[@title='Submit']" , "Submit Button");
					
					Thread.sleep(5000);
					
					String Confirmation_Text = Element.Get_Text(driver, test, reports, "//*[@id='HTML_FormSection_Label']", "Confirmation text");
					
					if (Confirmation_Text.equals("Thank you for submitting your Inspection Request for")) {
						
						driver.quit();	
						Result = "Pass";
						
					}else {
						Result = "Fail";
					}
		}
	catch(WebDriverException e){
		test.log(Status.FAIL, "Initiate Service Request Functionality Failed");
		String Screen = Take_Screenshot.take_screenshot(driver);
		test.addScreenCaptureFromPath(Screen);			
		reports.flush();
		Result = "Fail";
	}
		
		return Result;
			
		}
}
