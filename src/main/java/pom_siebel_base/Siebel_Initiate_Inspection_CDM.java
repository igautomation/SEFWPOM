package pom_siebel_base;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utilities.DB_Connections;
import utilities.Elements;
import utilities.ReadData_Siebel;
import utilities.Reports;
import utilities.ScreenShot;

public class Siebel_Initiate_Inspection_CDM {

	private static final String ON = null;
	public WebDriver driver;
	public ExtentTest test;
	public ExtentReports reports;
	public Reports report_Generator;
	public ScreenShot Take_Screenshot;
	public String Result;
	public WebDriverWait wait;
	public Elements Element;
	public ReadData_Siebel Read_Exceldata_Hashmap;
	public HashMap<String, String> map_Inspection_Siebel;
	public String recordAvailable;

	public String Fn_Siebel_Initiate_Inspection_CDM(WebDriver driver , ExtentTest test, ExtentReports reports ,  int j , String Test_Data_Path, String Sheet, String Environment) throws Exception {

		this.driver = driver;

		this.test = test;

		this.reports = reports;

		Take_Screenshot = new ScreenShot();

		Element = new Elements();

		Read_Exceldata_Hashmap = new ReadData_Siebel();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		map_Inspection_Siebel = Read_Exceldata_Hashmap.HashMap_Data_Inspection_Siebel(j, Test_Data_Path , Sheet);

		List<String> numbers = Arrays.asList(map_Inspection_Siebel.get("Declaration_No").split(","));

		WebDriverWait wait=new WebDriverWait(driver, 20);

		int k = numbers.size();

		System.out.println(k);

		for (String Dec_No : numbers) {
			test.log(Status.INFO, "Declaration No : " + Dec_No);
		}		

		try {

			//Navigate to  Initiate Request Tab
			Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Initiate Request')]", "Initiate Request Tab");

			//Navigate to Cleared Declarations Pool
			Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'CDM Identified')]", "Navigation to Cleared Declarations Pool");		

			for (String Dec_No : numbers) {

				//Declaration Add Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declaration View:New']" , "Declaration Add Button" );				

				//Enter Declaration 
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby=' s_1_l_DC_INS_Declaration_Number ']" , "Declaration Number" , Dec_No);

				//Focus Out
				//Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_1_l_DC_INS_DEC_AEO_FLAG']" , "Focus Out");							

				//Declarations View Menu Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declaration View:Menu']" , "Declarations View Menu" );

				//Save Option - Declarations View Menu
				Result = Element.Click_Event(driver, test, reports, "//ul[@id='s_at_m_1-menu']/li/a[contains(text(), 'Save Record                [Ctrl+S]')]", "Click Save Button - Declaration List Menu");

				try {
					recordAvailable=null;

					wait.until(ExpectedConditions.alertIsPresent());
					Alert alert = driver.switchTo().alert();
					recordAvailable = alert.getText();
					alert.accept();
					test.log(Status.INFO, "record already in queue alert was present and accepted : " + recordAvailable);
					System.out.println("alert was present and accepted");
					
					//Handling Validate Button
					WebElement validateButton= driver.findElement(By.xpath("//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']"));
					boolean eleEnabled= driver.findElement(By.xpath("//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']")).isEnabled();

					// retrieving html attribute value using getAttribute() method
					String typeValue = validateButton.getAttribute("disabled");
					System.out.println("Value of type attribute: "+typeValue);			
					
					if (typeValue!="true" && eleEnabled==true){
						//Click on Validate Button
						Result = Element.Click_Event(driver, test, reports, "//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']" , "Click on Validate Button" );					
					}						
					
				}
				catch(Exception e) {
					System.out.println("alert was not present");
					
					test.log(Status.INFO, "record already in queue alert not present");
					
					//Handling Validate Button
					WebElement validateButton= driver.findElement(By.xpath("//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']"));
					boolean eleEnabled= driver.findElement(By.xpath("//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']")).isEnabled();

					// retrieving html attribute value using getAttribute() method
					String typeValue = validateButton.getAttribute("disabled");
					System.out.println("Value of type attribute: "+typeValue);			
					
					if (typeValue!="true" && eleEnabled==true){
						//Click on Validate Button
						Result = Element.Click_Event(driver, test, reports, "//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']" , "Click on Validate Button" );					
					}				

				}

                
				Thread.sleep(3000);
				//Click Search Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declaration View:Query']" , "Click on Search Button" );

				//Enter Declaration 
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='s_1_l_DC_INS_Declaration_Number ']" , "Declaration Number" , Dec_No);

				//Click Go Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declaration View:Go']" , "Click on Go Button" );	
				
				
				try {
					recordAvailable=null;

					wait.until(ExpectedConditions.alertIsPresent());
					Alert alert = driver.switchTo().alert();
					recordAvailable = alert.getText();
					alert.accept();
					test.log(Status.INFO, "record already in queue alert was present and accepted : " + recordAvailable);
					System.out.println("alert was present and accepted");
					
					//Handling Validate Button
					WebElement validateButton= driver.findElement(By.xpath("//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']"));
					boolean eleEnabled= driver.findElement(By.xpath("//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']")).isEnabled();

					// retrieving html attribute value using getAttribute() method
					String typeValue = validateButton.getAttribute("disabled");
					System.out.println("Value of type attribute: "+typeValue);			
					
					if (typeValue!="true" && eleEnabled==true){
						//Click on Validate Button
						Result = Element.Click_Event(driver, test, reports, "//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']" , "Click on Validate Button" );					
					}						
					
				}
				catch(Exception e) {
					System.out.println("alert was not present");
					
					test.log(Status.INFO, "record already in queue alert not present");
					
					//Handling Validate Button
					WebElement validateButton= driver.findElement(By.xpath("//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']"));
					boolean eleEnabled= driver.findElement(By.xpath("//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']")).isEnabled();

					// retrieving html attribute value using getAttribute() method
					String typeValue = validateButton.getAttribute("disabled");
					System.out.println("Value of type attribute: "+typeValue);			
					
					if (typeValue!="true" && eleEnabled==true){
						//Click on Validate Button
						Result = Element.Click_Event(driver, test, reports, "//*[@class='siebui-btn-grp-search']/button[@aria-label='Declaration View:Validate']" , "Click on Validate Button" );					
					}
					
				}
				
				/*
				 * else { if
				 * (driver.findElements(By.xpath("//*[contains(text(),'Validate')]")).size()!=0)
				 * { //Click on Validate Button Result = Element.Click_Event(driver, test,
				 * reports, "//*[contains(text(),'Validate')]" , "Click on Validate Button" ); }
				 * }
				 */
				
				if (map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("ON")) {

					//Thread.sleep(2000);

					//Click on Multiple Booking GridCell
					Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_1_l_DC_Multiple_Declaration_Flag']" , "Click on Multiple Booking Grid Cell" );																																		

					
					//Handling Validate Button
					WebElement multiSelectButton= driver.findElement(By.xpath("//input[@aria-labelledby='s_1_l_DC_Multiple_Declaration_Flag s_1_l_altCheckBox']"));
					boolean eleChecked= multiSelectButton.isSelected();		
					
					if (eleChecked!=true){						
						//Click on Multiple Booking CheckBox
						Result = Element.Check_Box(driver, test, reports, "//input[@aria-labelledby='s_1_l_DC_Multiple_Declaration_Flag s_1_l_altCheckBox']" , "Click on Multiple Booking CheckBox", ON );			
					}
					
					//Focus Out
					Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_1_l_DC_INS_Exception_Status']" , "Focus Out Remarks Section");

					//Multiple Booking Request Pop Up Dialog
					/*
					 * if (ExpectedConditions.alertIsPresent()!=null) {
					 * driver.switchTo().alert().accept(); }
					 */

					try {
						recordAvailable=null;
						wait.until(ExpectedConditions.alertIsPresent());
						Alert alert = driver.switchTo().alert();
						recordAvailable = alert.getText();
						alert.accept();
						System.out.println("multiple records selection alert was present and accepted");
						test.log(Status.INFO, "multiple records selection alert was present and accepted : " + recordAvailable);
					}
					catch(Exception e) {
						System.out.println("multiple records selection alert was not present");
						System.out.print(e);
						test.log(Status.INFO, "multiple records selection alert not present");
					}

					//Declarations View Menu Button
					Result = Element.Click_Event(driver, test, reports, "//*[@aria-label='Declaration View:Menu']" , "Declarations View Menu" );

					//Save Option - Declarations View Menu
					Result = Element.Click_Event(driver, test, reports, "//ul[@id='s_at_m_1-menu']/li/a[contains(text(), 'Save Record                [Ctrl+S]')]", "Click Save Button - Payment List Menu");

					//Navigate to  Initiate Request Tab
					Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Initiate Request')]", "Initiate Request Tab");

					//Navigate to Cleared Declarations Pool
					Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'CDM Identified')]", "Navigation to Cleared Declarations Pool");


				}

			}


			//Click on Initiate Button
			Result = Element.Click_Event(driver, test, reports, "//*[@aria-label='Declaration View:Initiate Inspection']" , "Click on Initiate Button");

			//Inspection Centre
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSInspectionCentre_Label']" , "Inspection Centre" , map_Inspection_Siebel.get("Inspection_Centre"));			

			//Inspection section
			Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSInspectionSection_Label']" , "Inspection section" , map_Inspection_Siebel.get("Inspection_Section"));			


			if (map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("OFF")) {

				//Contact Email
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactEmailId_Label']" , "Contact Email" , map_Inspection_Siebel.get("Contact_Email"));

				//Representative Name
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSRepresentativeName_Label']" , "Representative Name" , map_Inspection_Siebel.get("Representative_Name"));

				//Contact Number
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactNumber_Label']" , "Contact Number", map_Inspection_Siebel.get("Contact_Number"));

				//Mobile Number
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Mobile_Number_Label']" , "Mobile Number" , map_Inspection_Siebel.get("Mobile_Number"));

			}
			else if (map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("ON")) {

				//Contact Email
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Contact_Email_Id_Label']" , "Contact Email" , map_Inspection_Siebel.get("Contact_Email"));

				//Representative Name
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSRepresentativeName_Label']" , "Representative Name" , map_Inspection_Siebel.get("Representative_Name"));

				//Contact Number
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactNumber_Label']" , "Contact Number", map_Inspection_Siebel.get("Contact_Number"));

				//Mobile Number
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Mobile_Number_Label']" , "Mobile Number" , map_Inspection_Siebel.get("Mobile_Number"));

			}

			String Preferred_Date = new SimpleDateFormat("MM/dd/Y").format(new Date());

			if (map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("OFF")) {				

				//Select All  Goods Details
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Containers/Marks & Numbers:Select All']" , "Select All  Goods Details" );		

				//Group Dates Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Containers/Marks & Numbers:Group Dates']" , "Group Dates Button");				

				//preferred date 1 text box
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Preferred Date 1']" , "preferred date 1 text box" , Preferred_Date);

				//preferred Slot 1 text box
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Preferred Slot 1']" , "preferred Slot 1 text box" , map_Inspection_Siebel.get("Preferred_Slot_1"));

				//Additional preferred Slot text box
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Additional Preferred Slot']" , "Additional preferred Slot text box" , map_Inspection_Siebel.get("Additional_Preferred_Slot"));

				//Group Dates OK Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Group Dates:OK']" , "Group Dates OK Button");


			}
			else if (map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("ON")) {

				//preferred date 1 text box
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Preferred_Date_1_Label']" , "preferred date 1 text box" , Preferred_Date);

				//preferred Slot 1 text box
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Preferred_Slot_1_Label']" , "preferred Slot 1 text box" , map_Inspection_Siebel.get("Preferred_Slot_1"));

				//Additional preferred Slot text box
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Preferred_Additional_Slot_Label']" , "Additional preferred Slot text box" , map_Inspection_Siebel.get("Additional_Preferred_Slot"));	

			}


			//Seal Details
			if (map_Inspection_Siebel.get("Seal_Flag").equals("ON")) {

				//Seal Check Box
				Result = Element.Check_Box(driver, test, reports, "//*[@aria-labelledby='DCINSSealRequiredFlag_Label']" , "Seal Check Box", ON );

				//Seal Check Box
				Result = Element.Click_Event(driver, test, reports, "//*[@aria-labelledby='DCINSClientStatus_Label']" , "Client Status");					

				//Seal Quantity
				Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSNoofSealsRequired_Label']" , "Seal Quantity" , map_Inspection_Siebel.get("Seal_Quantity"));			

			}			


			//Validate payment button
			Result = Element.Click_Event(driver, test, reports, "//span[contains(text(),'Validate Payment')]" , "Validate Payment Button");

			if (map_Inspection_Siebel.get("Payment_Mode").equals("Cash")) {



			}
			else if (map_Inspection_Siebel.get("Payment_Mode").equals("Credit Account") && map_Inspection_Siebel.get("Seal_Flag").equals("ON")) {

				//Activate Payment Mode element
				Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Payment_Mode']" , "Activate Payment Mode element" );

				//Pay mode Selection
				Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Payment_Mode']", "Pay mode Selection" , "Credit Account");

				//Activate Business Code element
				Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Credit_Acc_Business_Code']", "Activate Business Code element");

				//Thread.Sleep(2000);

				//Business Code Selection
				Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Credit_Acc_Business_Code']", "Business Code Selection", map_Inspection_Siebel.get("Business_Code"));					

				//Activate Credit Account element
				Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Transaction_Type']", "Activate Credit Account element");				

				//Credit Account No Selection
				Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Transaction_Type']", "Credit Account No Selection" , map_Inspection_Siebel.get("Credit_Account"));

				//Click Menu Button
				Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Payment List:Menu']", "Click Menu Button");				

				//Click Save Button - Payment List Menu
				Result = Element.Click_Event(driver, test, reports, "//*[@id='s_at_m_1-menu']/li[6]", "Click Save Button - Payment List Menu");									

				//Confirm Payment
				Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_5_0_Ctrl']" , "Confirm Payment");	

				//Payment Success	
				Result = Element.Text_Present(driver, test, reports, "//td[@title='Authorized']" , "Payment Success	" );		
		

			}
			else if (map_Inspection_Siebel.get("Payment_Mode").equals("ePay")) {	

			}

			//Submit
			Result = Element.Click_Event(driver, test, reports, "//button[@title='Submit']" , "Submit");	
			
			Thread.sleep(5000);
			
			Result = "Pass";

			/*
			 * for (String Dec_No : numbers) {
			 * 
			 * String BookingRefNo = DB_Connections.BooKingRequest_Declaration_No(Dec_No,
			 * Environment); String Booking_Request_Status =
			 * DB_Connections.Booking_Request_Status(BookingRefNo, Environment); String
			 * Booking_Request_Sub_Status =
			 * DB_Connections.Booking_Request_Sub_Status(BookingRefNo, Environment);
			 * 
			 * if (Booking_Request_Status.equals("02") &&
			 * Booking_Request_Sub_Status.equals("01")) { test.log(Status.PASS,
			 * "Request Declaration Info : " + Dec_No); test.log(Status.PASS,
			 * "Request Status : " + Booking_Request_Status); test.log(Status.PASS,
			 * "Request Sub Status : " + Booking_Request_Sub_Status); Result = "Pass"; }
			 * else { test.log(Status.FAIL, "Initiate Inspection CDM Failed");
			 * reports.flush(); Result = "Fail"; driver.quit(); }
			 * 
			 * }
			 */	

		}catch(

	WebDriverException e)
	{
			Result = "Fail";
		}

	return Result;

}

}
