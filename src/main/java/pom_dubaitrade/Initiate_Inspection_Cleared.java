package pom_dubaitrade;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
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

import utilities.Elements;
import utilities.ReadData_DubaiTrade;
import utilities.ReadData_Environment;
import utilities.Reports;
import utilities.ScreenShot;

public class Initiate_Inspection_Cleared {

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

    /*
     * public void Fn_Initiate_Inspection_Cleared_Map(HashMap<String, String> map) {
     *
     * String representative_Name =
     * map_Inspection_DubaiTrade.get("Representative_Name");
     *
     * }
     */

    public String Fn_Initiate_Inspection_Cleared(WebDriver driver, ExtentTest test, String Environment,
                                                 ExtentReports reports, int j, String Test_Data_Path, String Sheet) throws Exception {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new ScreenShot();
        Element = new Elements();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60) );
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Read_Inspection_Data = new ReadData_DubaiTrade();
        map_Inspection_DubaiTrade = Read_Inspection_Data.HashMap_Data_Inspection_DubaiTrade(j, Test_Data_Path, Sheet);
        ReadData_Environment = new utilities.ReadData_Environment();
        // Create Local Hash Map for DubaiTrade_Login sheet
        map_Login_Details = ReadData_Environment.HashMap_Data_Environment_Details(Environment, Test_Data_Path);
        List<String> numbers = Arrays.asList(map_Inspection_DubaiTrade.get("Declaration_No").split(","));
        int k = numbers.size();
        System.out.println(k);
        for (String Dec_No : numbers) {
            test.log(Status.INFO, "Declaration No : " + Dec_No);
        }
        try {
            // NewDubaiTrade UI
            Result = Element.Click_Event(driver, test, reports, "//*[@id='menu-button']", "Click Menu Section");
            //Thread.Sleep(1000);
            Result = Element.Click_Event(driver, test, reports,
                    "//ul[@class='top-level']/li[@class='top-head head closed']/span[@class='title']/span[contains(text(), 'Inspection Request')]",
                    "Click Inspection Section");
            //Thread.Sleep(3000);
            if (map_Login_Details.get("Environment").equals("SIT2")) {
                Result = Element.Click_Event(driver, test, reports,
                        "//span[contains(text(), 'Initiate Inspection - Cleared Declaration S2')]",
                        "Click Track-Inspection");

            } else {
                Result = Element.Click_Event(driver, test, reports,
                        "//span[contains(text(), 'Initiate Inspection – Cleared Declaration')]",
                        "Click Track-Inspection");
                //Thread.Sleep(3000);
            }
            if (map_Login_Details.get("Environment").equals("SIT2")) {
                if (driver.findElements(By.xpath("//*[@id='remember-me']")).size() != 0) {
                    // remember me check box
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='remember-me']",
                            "remember me check box");
                    // Ok Button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='modifyid']", "Ok Button");
                }
            }
            // selectFrame
            Result = Element.Switch_Frame(driver, test, reports, "//iframe[@id='contentFrame']");
            for (String Dec_No : numbers) {
                Thread.sleep(2000);
                // Add declaration Button
                Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_28_0_Ctrl']",
                        "Add declaration Button");
                // Enter Declaration
                Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_DC_INS_Declaration_Number']",
                        "Enter Declaration", Dec_No);
                // Focus Out
                Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_1_l_DC_INS_Zone']",
                        "Focus Out Cargo Channel Section");
                // Save Record button
                Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_26_0_Ctrl']", "Save Record button");
                // Record Save confirmation message
                Result = Element.Get_Text(driver, test, reports, "//*[@id='CommitNotifyContainer']",
                        "Record Save confirmation message");
                // Validate Inspection
                Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_24_0_Ctrl']",
                        "Validate Inspection");
                Thread.sleep(10000);

                //Search button
                Result = Element.Click_Event(driver, test, reports, "//*[@aria-label='Cleared Declaration Details:Query']",
                        "Cleared Declaration Details:Query");
                // Enter Declaration
                Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_DC_INS_Declaration_Number']",
                        "Enter Declaration", Dec_No);
                // Go button
                Result = Element.Click_Event(driver, test, reports, "//*[@aria-label='Cleared Declaration Details:Go']", "Go button");
                // Record Save confirmation message
                Result = Element.Get_Text(driver, test, reports, "//*[@id='CommitNotifyContainer']",
                        "Record Save confirmation message");
                Thread.sleep(5000);

                if (map_Inspection_DubaiTrade.get("Multiple_Declaration_Flag").equalsIgnoreCase("ON")) {
                    for (int v = 1; v <= 10; v++) {
                        // Multiple Declaration Flag
                        Result = Element.Click_Event(driver, test, reports,
                                "//td[@id='1_s_1_l_DC_Multiple_Declaration_Flag']", "Multiple Declaration Flag");
                        if (driver.findElements(By.xpath("//input[@id='1_DC_Multiple_Declaration_Flag']")).size() != 0) {
                            // Multiple Declaration Flag CheckBox
                            Result = Element.Click_Event(driver, test, reports, "//input[@id='1_DC_Multiple_Declaration_Flag']",
                                    "Multiple Declaration Flag CheckBox");
                            break;
                        }
                        Thread.sleep(3000);
                    }
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_1_l_DC_INS_Regime_Type']", "Multiple Declaration Flag CheckBox");
                    try {
                        wait.until(ExpectedConditions.alertIsPresent());
                        Alert alert = driver.switchTo().alert();
                        alert.accept();
                        System.out.println("alert was present and accepted");
                    } catch (Exception e) {
                        System.out.println("alert was not present");
                        System.out.print(e);
                    }
                    // Save Record button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_26_0_Ctrl']", "Save Record button");
                    test.log(Status.PASS, "Decleration Added : " + Dec_No);

                }

            }
            // Initiate Inspection button
            Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_22_0_Ctrl']",
                    "Initiate Inspection button");
            if (map_Inspection_DubaiTrade.get("Multiple_Declaration_Flag").equalsIgnoreCase("ON")) {
                // Contact Email
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_15_0']", "Contact Email",
                        map_Inspection_DubaiTrade.get("Contact_Email"));
                // Representative Name
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_27_0']", "Representative Name",
                        map_Inspection_DubaiTrade.get("Representative_Name"));
                // Contact Number
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_17_0']", "Contact Number",
                        map_Inspection_DubaiTrade.get("Contact_Number"));
                // Mobile Number
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_16_0']", "Mobile Number",
                        map_Inspection_DubaiTrade.get("Mobile_Number"));
                // Inspection centre
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSInspectionCentre_Label']", "Inspection centre",
                        map_Inspection_DubaiTrade.get("Inspection_Centre"));
                // Inspection section
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_21_0']", "Inspection section",
                        map_Inspection_DubaiTrade.get("Inspection_Section"));
                // Follow Up
                if (map_Inspection_DubaiTrade.get("Auto_FollowUp").equals("ON")) {
                    // Follow Up Flag
                    Result = Element.Check_Box(driver, test, reports, "//input[@aria-label='Follow Up Required']", "Auto Follow Up Flag", "ON");
                    // Request Status
                    Result = Element.Click_Event(driver, test, reports, "//*[@aria-labelledby='DCINSStatus_Label']", "Request Status");
                    // Inspection centre
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Followup_Centre_Label']", "Inspection centre",
                            map_Inspection_DubaiTrade.get("Inspection_Centre"));
                    // Inspection section
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Followup_Section_Label']", "Inspection section",
                            map_Inspection_DubaiTrade.get("Inspection_Section"));

                }
                // Seal Flag
                if (map_Inspection_DubaiTrade.get("Seal_Flag").equals("ON")) {
                    // Seal Flag
                    Result = Element.Check_Box(driver, test, reports, "//input[@aria-label='Seal Required']", "Seal Flag", "ON");
                    // Request Status
                    Result = Element.Click_Event(driver, test, reports, "//*[@aria-labelledby='DCINSStatus_Label']", "Request Status");
                    // Seal Quantity
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSNoofSealsRequired_Label']", "Seal Quantity",
                            map_Inspection_DubaiTrade.get("Seal_Quantity"));
                }
                Thread.sleep(2000);

                String Preferred_Date = new SimpleDateFormat("MM/dd/Y").format(new Date());
                // preferred date 1 text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_3_0']",
                        "preferred date 1 text box", Preferred_Date);
                // preferred Slot 1 text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_6_0']",
                        "preferred Slot 1 text box", map_Inspection_DubaiTrade.get("Preferred_Slot_1"));
                // Additional preferred Slot text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_0_0']",
                        "Additional preferred Slot text box", map_Inspection_DubaiTrade.get("Additional_Preferred_Slot"));
                Thread.sleep(2000);
                // Record Save Button
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_49_0_Ctrl']",
                        "Record Save Button");
                // Next Button
                Result = Element.Click_Event(driver, test, reports, "//*[@aria-label='<b>Booking Request Details</b>:Next']", "Next Button");
                for (int count = 1; count <= k; count++) {
                    Thread.sleep(5000);
                    //Declaration number table
                    //tr[@id="+count+"]/td[@aria-labelledby='s_1_l_altCheckBox']
                    Result = Element.Click_Event(driver, test, reports, "//table[@summary='Declarations']//tr[@id=" + count + "]",
                            "Dec_No table");
                    Thread.sleep(5000);
                    //Payment mode textbox
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_4_l_DC_INS_Payment_Mode']", "Payment mode textbox");
                    //Payment mode textbox
                    WebElement ele = driver.findElement(By.xpath("//td[@id='1_s_4_l_DC_INS_Payment_Mode']"));
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", ele);
                    //payment mode text mode
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Payment_Mode']",
                            "credit Account textbox", map_Inspection_DubaiTrade.get("Payment_Mode"));
                    // credit Account
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_4_l_DC_INS_Transaction_Type']",
                            "credit Account");
                    //Credit Account textbox
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_DC_INS_Transaction_Type']",
                            "credit Account textbox", map_Inspection_DubaiTrade.get("Credit_Account"));
                    //payment Save
                    Result = Element.Click_Event(driver, test, reports, "//button[@id='s_4_1_19_0_Ctrl']",
                            "payment Save");
                    Thread.sleep(2000);
                    // Make Payment
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_4_1_12_0_Ctrl']",
                            "make Payment Button");
                    Thread.sleep(10000);

                }
                // Seal Flag
                if (map_Inspection_DubaiTrade.get("Seal_Flag").equals("ON")) {
                    // Next button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_2_1_30_0_Ctrl']",
                            "Next Button");
                    //Payment mode textbox
                    WebElement ele = driver.findElement(By.xpath("//td[@id='1_s_3_l_DC_INS_Payment_Mode']"));
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", ele);
                    //payment mode text mode
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Payment_Mode']",
                            "credit Account textbox", map_Inspection_DubaiTrade.get("Payment_Mode"));
                    // credit Account
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_3_l_DC_INS_Transaction_Type']",
                            "credit Account");
                    //Credit Account textbox
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_DC_INS_Transaction_Type']",
                            "credit Account textbox", map_Inspection_DubaiTrade.get("Credit_Account"));
                    //payment Save
                    Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_19_0_Ctrl']",
                            "payment Save");
                    Thread.sleep(2000);
                    // Make Payment
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_3_1_12_0_Ctrl']",
                            "make Payment Button");
                    Thread.sleep(2000);
                    // Submit button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_31_0_Ctrl']",
                            "Submit Button");

                } else {
                    test.log(Status.PASS, "Paymanet submitted successfully");
                    // Submit button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_2_1_31_0_Ctrl']",
                            "Submit Button");
                }

            } else {
                // Contact Email
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_2_1_14_0']", "Contact Email",
                        map_Inspection_DubaiTrade.get("Contact_Email"));
                // Representative Name
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_2_1_27_0']", "Representative Name",
                        map_Inspection_DubaiTrade.get("Representative_Name"));
                // Contact Number
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_2_1_16_0']", "Contact Number",
                        map_Inspection_DubaiTrade.get("Contact_Number"));
                // Mobile Number
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_2_1_15_0']", "Mobile Number",
                        map_Inspection_DubaiTrade.get("Mobile_Number"));
                // Inspection centre
                Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSInspectionCentre_Label']", "Inspection centre",
                        map_Inspection_DubaiTrade.get("Inspection_Centre"));
                // Inspection section
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_2_1_20_0']", "Inspection section",
                        map_Inspection_DubaiTrade.get("Inspection_Section"));
                // Follow Up
                if (map_Inspection_DubaiTrade.get("Auto_FollowUp").equals("ON")) {
                    // Follow Up Flag
                    Result = Element.Check_Box(driver, test, reports, "//input[@aria-label='Follow Up Required']", "Auto Follow Up Flag", "ON");
                    // Request Status
                    Result = Element.Click_Event(driver, test, reports, "//*[@aria-labelledby='DCINSStatus_Label']", "Request Status");
                    // Inspection centre
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Followup_Centre_Label']", "Inspection centre",
                            map_Inspection_DubaiTrade.get("Inspection_Centre"));
                    // Inspection section
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Followup_Section_Label']", "Inspection section",
                            map_Inspection_DubaiTrade.get("Inspection_Section"));

                }
                // Seal Flag
                if (map_Inspection_DubaiTrade.get("Seal_Flag").equals("ON")) {
                    // Seal Flag
                    Result = Element.Check_Box(driver, test, reports, "//input[@aria-label='Seal Required']", "Seal Flag", "ON");
                    // Seal Quantity
                    Result = Element.Click_Event(driver, test, reports, "//*[@aria-labelledby='DCINSStatus_Label']", "Seal Status");
                    // Seal Quantity
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSNoofSealsRequired_Label']", "Seal Quantity",
                            map_Inspection_DubaiTrade.get("Seal_Quantity"));

                }
                //Thread.Sleep(2000);
                // Save Record button
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_2_1_47_0_Ctrl']",
                        "Save Record button");
                // Select All Goods Details
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_3_0_Ctrl']",
                        "Select All  Goods Details");
                // Group Dates Button
                Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_2_0_Ctrl']", "Group Dates Button");
                String Preferred_Date = new SimpleDateFormat("MM/dd/Y").format(new Date());
                // preferred date 1 text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_22_0']",
                        "preferred date 1 text box", Preferred_Date);
                // preferred Slot 1 text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_23_0']",
                        "preferred Slot 1 text box", map_Inspection_DubaiTrade.get("Preferred_Slot_1"));
                // Additional preferred Slot text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@name='s_3_1_29_0']",
                        "Additional preferred Slot text box", map_Inspection_DubaiTrade.get("Additional_Preferred_Slot"));
                //Thread.Sleep(2000);
                // Record Save Button
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_32_0_Ctrl']",
                        "Record Save Button");
                //Thread.Sleep(2000);
                // Group Dates OK Button
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_33_0_Ctrl']",
                        "Group Dates OK Button");
                // Next Button
                Result = Element.Click_Event(driver, test, reports, "//*[@id='s_2_1_31_0_Ctrl']", "Next Button");
                if (driver.findElements(By.xpath("//*[@id='LabelStatement_Label']")).size() != 0) {
                    // OK Button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_4_0_Ctrl']", "FollowUp OK Button");
                    //Thread.Sleep(1000);
                }
                if (map_Inspection_DubaiTrade.get("Payment_Mode").equals("Credit Account")) {
                    // Activate Payment Mode element
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_3_l_DC_INS_Payment_Mode']",
                            "Activate Payment Mode element");
                    // Pay mode Selection
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Payment_Mode']",
                            "Pay mode Selection", "Credit Account");
                    // Activate Credit Account element
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_3_l_DC_INS_Transaction_Type']",
                            "Activate Credit Account element");
                    // Credit Account No Selection
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Transaction_Type']",
                            "Credit Account No Selection", map_Inspection_DubaiTrade.get("Credit_Account"));
                    // Record Save Button
                    Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_19_0_Ctrl']",
                            "Record Save Button");
                    //Thread.Sleep(5000);
                    // Make Payment
                    Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_12_0_Ctrl']", "Make Payment");
                    Thread.sleep(10000);

                } else if (map_Inspection_DubaiTrade.get("Payment_Mode").equals("ePay")) {
                }
                // Payment Success
                Result = Element.Text_Present(driver, test, reports, "//td[@title='Authorized']", "Payment Success	");
                // Record Save Button
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_19_0_Ctrl']",
                        "Record Save Button");
                // Submit
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_34_0_Ctrl']", "Submit");
                //Thread.Sleep(5000);
            }
            String SubmissionMessage = Element.Get_Text(driver, test, reports,
                    "//span[contains(text(), 'Thank you for submitting your Inspection Request for')]", "Submit text");
            if (SubmissionMessage.equals("Thank you for submitting your Inspection Request for")) {
                test.log(Status.PASS, "Multiple Booking Request submitted Successfully");
                Result = "Pass";
            } else {
                test.log(Status.FAIL, "Initiate Inspection Cleared Failed");
                // test.log(Status.INFO, "Booking Reference Number Generated : " +
                // Test_Data_Login_Details(Environment, Test_Data_Path, "Dubai_Trade_UserID") );
                reports.flush();
                Result = "Fail";
            }
            // Submit
            /*
             * String bookingRefNo = Element.Get_Text(driver, test, reports,
             * "//input[@aria-label='Booking Reference Number']" , "Submit text"); Result =
             * Element.Get_Text(driver, test, reports,
             * "//input[@aria-label='Booking Reference Number']" , "Submit text");
             */
        } catch (WebDriverException e) {
            System.out.println(e);
            driver.quit();
            Result = "Fail";

        }
        return Result;
    }

}
