package pom_siebel_base;

import java.util.HashMap;

import utilities.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.Chrome_Browser;
import utilities.IE_Browser;
import utilities.ReadData_DubaiTrade;
import utilities.ReadData_Siebel;
import utilities.Reports;
import utilities.ScreenShot;

public class Siebel_TruckRelease_Request {
	
	
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
	
	public String TruckRelease_Request(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet , String Environment) throws Exception {
	
	this.driver = driver;
	
	this.test = test;
	
	this.reports = reports;
	
	this.driver = driver;
	
	this.test = test;
	
	this.reports = reports;
			
	Take_Screenshot = new ScreenShot();
	
	Element = new Elements();
	
	Read_Inspection_Data = new ReadData_DubaiTrade();
	
	map = Read_Inspection_Data.HashMap_Data_Inspection_DubaiTrade(j, Test_Data_Path , Sheet);
	
	Siebel_login = new Siebel_Login();
	
	try {
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='12_s_2_l_Display_Name']/a")).click();
		
		//Result = Element.Click_Event(driver, test, reports, "//*[@id='12_s_2_l_Display_Name']/a", "Truck Release Requests");
		
		Thread.sleep(3000);
		
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Initiate/Track Truck Release Requests:Query']", "Truck Release Requests:Query");
		
		Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Declaration_Number']", "Declaration Number select");
		
		Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='s_1_l_DC_INS_Declaration_Number ']" , "Declaration Number Text", map.get("Declaration_No"));
		
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Initiate/Track Truck Release Requests:Go']", "Truck Release Requests:Go");
		
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Initiate/Track Truck Release Requests:Self Assign']", "Truck Release Requests:Self Assign");
		
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Self Assign:Ok']", "Self Assign:Ok Button");
		
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Release Request Details:Truck Release']", "Truck Release Request Details:Truck Release");
		
		Thread.sleep(3000);
		
		Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Truck Release:Ok']", "Truck Release:Ok");
		

		Result = "Pass";
		
	}
	catch(WebDriverException e) {
		
		Result = "Fail";
		test.log(Status.FAIL, "Truck Release Failed");		
		reports.flush();
		
	}
	
	return Result;
	
	}

}
