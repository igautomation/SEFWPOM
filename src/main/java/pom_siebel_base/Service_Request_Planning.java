package pom_siebel_base;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
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

public class Service_Request_Planning {

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
	
	public HashMap<String, String> map_Environment_Details;
	public HashMap<String, String> map_Inspection;

	public WebDriverWait waitMethod(WebDriver driver )  {

		this.driver = driver;

		//return wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		return wait=new WebDriverWait(driver, 20);
		

	}

	public void waitForPageLoadComplete(WebDriver driver, int specifiedTimeout) {
		Wait<WebDriver> wait = new WebDriverWait(driver, specifiedTimeout);
		wait.until(driver1 -> String
				.valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
				.equals("complete"));
	}
	
	public String Service_request(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet  , String Environment, String Booking_Ref_No) throws Exception {
		
		
		this.driver = driver;

		this.test = test;

		this.reports = reports;

		Take_Screenshot = new ScreenShot();

		Element = new Elements();
		
		ReadData_Environment_Details = new ReadData_Environment();

		Read_Exceldata_Hashmap = new ReadData_DubaiTrade();
		
		ReadData_Siebel_Hasmap = new ReadData_Siebel();
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;


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

		JavascriptExecutor js = (JavascriptExecutor)driver;

		// get date format MM/DD/YYYY HH:MM A 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY hh:mm a");
		LocalDateTime now = LocalDateTime.now();
		//Start time
		String Start_Time = dtf.format(now.plusHours(2));
		//End Time
		String End_Time = dtf.format(now.plusHours(2).plusMinutes(2));

		System.out.println(Start_Time +"  -  "+ End_Time);
		try {

			//Booking Request - Planner Tab
			////*[@id='ui-id-103']		
			Result = Element.Click_Event(driver, test, reports, "/html/body/div[1]/div/div[4]/div/div/div[1]/div[1]/ul/li[4]/a", "Booking Request - Planner Tab");

			//Booking Request Search Button
			///html/body/div[1]/div/div[6]/div/div[7]/div/div[1]/div/div[1]/div/form/span/div/div[1]/div[2]/button[1]
			//*[@id='s_1_1_14_0_Ctrl']/span
			Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_14_0_Ctrl']", "Booking Request Search Button");

			if (driver.findElements(By.xpath("//*[@id=\"1_s_1_l_Serial_Number\"]")).size()!=0) {
				driver.findElement(By.xpath("//*[@id=\"1_s_1_l_Serial_Number\"]")).click();
			}

			//Booking ref number text box
			Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_Serial_Number']" , "Enter Booking request No",Booking_Ref_No);

			//Go Button
			Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_11_0_Ctrl']", "Go Button");

			for(int k = 0 ; k<=5 ; k++) {
				if (driver.findElements(By.xpath("//*[@id='1_s_1_l_Serial_Number']")).size()==0) {

					//Booking Request Search Button
					Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_14_0_Ctrl']", "Booking Request Search Button");

					if (driver.findElements(By.xpath("//*[@id=\"1_s_1_l_Serial_Number\"]")).size()!=0) {
						driver.findElement(By.xpath("//*[@id=\"1_s_1_l_Serial_Number\"]")).click();
					}

					//Booking ref number text box
					Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_Serial_Number']" , "Enter Booking request No",Booking_Ref_No);

					//Go Button
					Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_11_0_Ctrl']", "Go Button");

					continue;
				}
				Thread.sleep(1000);
			}

			//Accept button
			Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_7_0_Ctrl']", "Accept button");

			Thread.sleep(5000);
			if (Environment.equals("UAT1")) {	
				//wait.until(ExpectedConditions.alertIsPresent());
				if (ExpectedConditions.alertIsPresent()!=null) {
					driver.switchTo().alert().accept();
				}
			}

			Thread.sleep(5000);
			//Drill DOwn Booking reference Number
			Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_1_l_Serial_Number']", "Booking reference Number Link");
			
			Thread.sleep(5000);
			
			//Drill DOwn Serial Number
			Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS Serial Number']", "Drill DOwn Serial Number");
			
			Thread.sleep(5000);
			
			//shift text box select

			if (driver.findElements(By.xpath("//*[@id='1_s_2_l_DC_INS_Shift']")).size()!= 0){
				
				WebElement ele = driver.findElement(By.xpath("//*[@id='1_s_2_l_DC_INS_Shift']"));

				executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id='1_s_2_l_DC_INS_Shift']")));
									
				executor.executeScript("arguments[0].click();", ele);
			}
			
			//Shift text box
			Element.TextBox_Value(driver, test, reports, "//*[@id='1_DC_INS_Shift']", "Shift text box" , "Shift 1");
			
			Element.Click_Event(driver, test, reports, "//*[@id='1_s_2_l_DC_INS_Group_Name']", "shift text box select");
			
			Thread.sleep(5000);

			//Start time //*[@id="a_4"]/div/table/tbody/tr[3]/td[3]/div/input
			Element.TextBox_Value(driver, test, reports, "//*[@id='a_4']/div/table/tbody/tr[3]/td[3]/div/input" , "Calander details - Start Time", Start_Time);
			
			Thread.sleep(2000);
			
			//Start time 
			Element.TextBox_Value(driver, test, reports, "//*[@id='a_4']/div/table/tbody/tr[3]/td[3]/div/input" , "Calander details - Start Time", Start_Time);

			//End Time  //*[@id="a_4"]/div/table/tbody/tr[3]/td[5]/div/input
			Element.TextBox_Value(driver, test, reports, "//*[@id='a_4']/div/table/tbody/tr[3]/td[5]/div/input" , "Calander details - End Time", End_Time);
			
			Thread.sleep(2000);

			//Get Inspector button 
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Get Inspectors']", "Get Inspectors button");
			
			Thread.sleep(15000);

			//Validate button 
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Validate']", "Validate button");

			Thread.sleep(3000);
			
			//confirm Button
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Confirm']", "Confirm button");
			
			if(driver.findElements(By.xpath("//button[@title='Accept']")).size()!=0) {
				Result = "Pass";			
				reports.flush();
				driver.quit();
			}
		}
		catch(WebDriverException e) {
			Result = "Fail";
			test.log(Status.FAIL, "Service Request planning Failed");			
			reports.flush();
			driver.quit();
		}
		return Result;
		
	}
	
}
