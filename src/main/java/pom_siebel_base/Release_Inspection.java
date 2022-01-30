package pom_siebel_base;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
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

public class Release_Inspection {

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
    public HashMap<String, String> map_Inspection_Siebel;
    public HashMap<String, String> map_Inspection;

    public WebDriverWait waitMethod(WebDriver driver) {
        this.driver = driver;
        return wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    }

    public String Release(WebDriver driver, ExtentTest test, ExtentReports reports, int j, String Test_Data_Path, String Sheet, String Environment, String Booking_Req_No) throws Exception {
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
        } else if (Sheet.equals("Inspection_Siebel")) {
            // Create Local Hash Map for data sheet
            map_Inspection = ReadData_Siebel_Hasmap.HashMap_Data_Inspection_Siebel(j, Test_Data_Path, Sheet);
        }
        List<String> numbers = Arrays.asList(map_Inspection.get("Declaration_No").split(","));
        int Dec_Count = numbers.size();
        try {
            if (map_Inspection.get("Inspection_Section").equals("Hatta")) {
                //Unassigned Request
                Result = Element.Click_Event(driver, test, reports, "//a[contains(text(), 'Unassigned Requests')]", "Unassigned Requests");
                //Search Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Task Queue:Query']", "Task Queue:Query");
                //Booking request no Text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Booking_Reference_Number']", "Booking request no Text box", Booking_Req_No);
                //Go Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Task Queue:Go']", "Go Button");
                //Status text box
                Result = Element.Click_Event(driver, test, reports, "//td[@title='Submitted']", "Status text box");
                //Self Assign Button
                Result = Element.Click_Event(driver, test, reports, "//button[@title='Self Assign']", "Self Assign Button");
                //DrillDown Booking Request No
                //Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS Booking Reference Number']", "DrillDown Booking Request No");
            } else {
                //Assigned Requests
                Result = Element.Click_Event(driver, test, reports, "//a[normalize-space()='Assigned Requests']", "Assigned Requests");
                //Work Queue:Query
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Work Queue:Query']", "Work Queue:Query");
                //Booking request no Text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='DC_INS_Booking_Request_Number']", "Booking request no Text box", Booking_Req_No);
                //Work Queue:Query -> Go
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Work Queue:Go']", "Work Queue:Query -> Go");
                //Contact Number text box
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Contact_Number']", "Contact Number text box");
                //DrillDown Booking Request No
                //DC INS Booking Request Number
                Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS Booking Request Number']", "DrillDown Booking Request No");

            }
            //Thread.Sleep(5000);
            //Multiple Declaration Flow
            if (map_Inspection.get("Multiple_Declaration_Flag").equalsIgnoreCase("ON")) {
                //Start Inspection
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declarations:Start Inspection']", "Start Inspection Button");

            } else {
                //Start Inspection
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Track Request:Start Inspection']", "Start Inspection Button");

            }
            Thread.sleep(5000);
            //AOR Message
            if (driver.findElements(By.xpath("//button[@aria-label='Message:OK']")).size() != 0) {
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Message:OK']", "AOR Message");

            }
            //Inspection Finding Tab
            Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Inspection Findings')]", "Inspection Finding Tab");
            //Multiple Declaration Flow
            if (map_Inspection.get("Multiple_Declaration_Flag").equalsIgnoreCase("ON")) {
                for (String Dec_No : numbers) {
                    //Declaration Row No
                    Result = Element.Click_Event(driver, test, reports, "//td[contains(text()," + Dec_No + ")]", "Action Outcome report New");
                    //Action Outcome report New
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Action Outcome Report:New']", "Action Outcome report New");
                    //AOR Results
                    //input[@aria-label='Results']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Results']", "AOR Results", map_Inspection.get("AOR_Results"));
                    //AOR Notes
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Notes']", "AOR Notes", map_Inspection.get("AOR_Notes"));
                    //Action Outcome Report:Save
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Action Outcome Report:Save']", "Action Outcome Report:Save");
                    //Action Taken
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Action Taken:New']", "Action Taken");
                    //Action taken value
                    Result = Element.Click_Event(driver, test, reports, "//td[@title='Approval from CID']", "Action taken value");
                    //Action Taken Add Button
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Action Taken:Add']", "Action Taken Add Button");
                    //Action Outcome
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Outcome:New']", "Action Outcome");
                    //Action Outcome value
                    Result = Element.Click_Event(driver, test, reports, "//td[@title='Drugs detected']", "Action Outcome value");
                    //Action Outcome Add Button
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Outcome:Add']", "Action Outcome Add Button");
                    //Validate Button
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Action Outcome Report:Validate']", "Validate Button");

                }
                //Inspection Activities Tab
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Inspection Activities')]", "Inspection Activities Tab");
                for (int count = 1; count <= Dec_Count; count++) {
                    //Select Declaration
                    Result = Element.Click_Event(driver, test, reports, "//tr[@id=" + count + "]/td[@aria-labelledby='s_1_l_altCheckBox']", "Select Declaration");
                    //select Check Box
                    Result = Element.Click_Event(driver, test, reports, "//*[@name='DC_INS_Select_Flag']", "select Check Box");
                    //Release Button
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declarations:Release']", "Release Button");
                    Thread.sleep(5000);
                }

            } else {
                //Action Outcome report New
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Action Outcome Report:New']", "Action Outcome report New");
                //AOR Results
                //input[@aria-label='Results']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Results']", "AOR Results", map_Inspection.get("AOR_Results"));
                //AOR Notes
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Notes']", "AOR Notes", map_Inspection.get("AOR_Notes"));
                //Action Outcome Report:Save
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Action Outcome Report:Save']", "Action Outcome Report:Save");
                //Action Taken
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Action Taken:New']", "Action Taken");
                //Action taken value
                Result = Element.Click_Event(driver, test, reports, "//td[@title='Approval from CID']", "Action taken value");
                //Action Taken Add Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Action Taken:Add']", "Action Taken Add Button");
                //Action Outcome
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Outcome:New']", "Action Outcome");
                //Action Outcome value
                Result = Element.Click_Event(driver, test, reports, "//td[@title='Drugs detected']", "Action Outcome value");
                //Action Outcome Add Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Outcome:Add']", "Action Outcome Add Button");
                //Validate Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Action Outcome Report:Validate']", "Validate Button");
                //Thread.Sleep(5000);
                //Inspection Activities Tab
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Inspection Activities')]", "Inspection Activities Tab");
                //Release Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Track Request:Release']", "Release Button");

            }
            //Thread.Sleep(10000);
            if (driver.findElements(By.xpath("//button[@aria-label='Message:OK']")).size() != 0) {
                //AOR Message
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Message:OK']", "AOR Message");
            }
            Result = "Pass";
            test.log(Status.PASS, "Release Inspection Completed Successfully");
            reports.flush();
            driver.quit();

        } catch (WebDriverException e) {
            Result = "Fail";
            test.log(Status.FAIL, "Release Inspection Failed");
            reports.flush();

        }
        return Result;

    }

}
