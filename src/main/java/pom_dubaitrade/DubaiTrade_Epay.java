package pom_dubaitrade;

import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.Elements;
import utilities.ReadData_DubaiTrade;
import utilities.Reports;
import utilities.ScreenShot;

public class DubaiTrade_Epay {

	
	public WebDriver driver;
	public ExtentTest test;
	public ExtentReports reports;
	public Reports report_Generator;
	public ScreenShot Take_Screenshot;
	public String Result;
	public Elements Element;
	public HashMap<String, String> map;
	public ReadData_DubaiTrade Read_Inspection_Data;
	
	public String Epay_DT(WebDriver driver , ExtentTest test, ExtentReports reports , int j , String Test_Data_Path, String Sheet) throws Exception {
		
		this.driver = driver;
		
		this.test = test;
		
		this.reports = reports;
				
		Take_Screenshot = new ScreenShot();
		
		Element = new Elements();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		Read_Inspection_Data = new ReadData_DubaiTrade();
		
		map = Read_Inspection_Data.HashMap_Data_Inspection_DubaiTrade(j, Test_Data_Path , Sheet);
		
		test.log(Status.INFO, "Declaration No : " + map.get("Declaration_No"));
		
		
		//Activate Payment Mode element
		Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_3_l_DC_INS_Payment_Mode']" , "Activate Payment Mode element" );
				
	//Pay mode Selection
		Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Payment_Mode']", "Pay mode Selection" , map.get("Payment_Mode"));
		
		//Record Save Button
		Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_19_0_Ctrl']" , "Record Save Button");		
		
	//Make Payment
		Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_12_0_Ctrl']" , "Make Payment");	
		
		Thread.sleep(5000);
		
	//Make Payment	
		Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_2_0_Ctrl']" , "Make Payment");
		
		Thread.sleep(2000);
		
		String MainWindow = driver.getWindowHandle();

	//Make Payment OK Button
		Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_2_0_Ctrl']" , "Make Payment ok Button");
		
		Thread.sleep(10000);
		
		
		  Set<String> allWindowHandles = driver.getWindowHandles();
		  
		  for (String handle : allWindowHandles) {
		  
		  driver.switchTo().window(handle);
		  
		  
		  Thread.sleep(5000);
		  
		  }
		 				

//Pay Button
	Result = Element.Click_Event(driver, test, reports, "//*[@id='mainTable']/div[4]/div[3]/div[1]/input" , "Pay Button");

//Credit Card Number
	Result = Element.TextBox_Value(driver, test, reports, "//*[@id='txtCardNo']", "Card number" , map.get("CreditCard_No"));

//Expiry Month	
	Result = Element.TextBox_Value(driver, test, reports, "//*[@id='ddlMonth']", "Expiry Month" , map.get("Expiry_Month"));

//Expiry Year
	Result = Element.TextBox_Value(driver, test, reports, "//*[@id='ddlYear']", "Expiry Year" , map.get("Expiry_Year"));
	
//CVV
	Result = Element.TextBox_Value(driver, test, reports, "//*[@id='txtCvvNo']", "CVV" , map.get("CVV"));
	
//Pay Button
	Result = Element.Click_Event(driver, test, reports, "//*[@id='payButton']" , "Pay Button");
	
		
		if (driver.findElements(By.xpath("/html/body/pre[2]")).size()!=0) {
			
			driver.close();

		}
		

		driver.switchTo().window(MainWindow);
		
//Back Buttons
	Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_1_0_Ctrl']" , "Back Button");	
	
//Record Save Button
	Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_19_0_Ctrl']" , "Record Save Button");		

//Submit
	Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_34_0_Ctrl']" , "Submit");	
	
	
	return Result;
	
	}
	
}
