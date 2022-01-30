package pom_siebel_base;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.*;
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

import javax.sound.sampled.Line;

public class Siebel_Initiate_Inspection_Cleared {

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

    public String Fn_Siebel_Initiate_Inspection_Cleared(WebDriver driver, ExtentTest test, ExtentReports reports, int j, String Test_Data_Path, String Sheet, String Environment) throws Exception {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new ScreenShot();
        Element = new Elements();
        Read_Exceldata_Hashmap = new ReadData_Siebel();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        map_Inspection_Siebel = Read_Exceldata_Hashmap.HashMap_Data_Inspection_Siebel(j, Test_Data_Path, Sheet);
        List<String> numbers = Arrays.asList(map_Inspection_Siebel.get("Declaration_No").split(","));
        int k = numbers.size();
        System.out.println(k);
        for (String Dec_No : numbers) {
            test.log(Status.INFO, "Declaration No : " + Dec_No);
        }
        try {
            //Navigate to  Initiate Request Tab
            Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Initiate Request')]", "Initiate Request Tab");
            //Navigate to Cleared Declarations Pool
            Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Cleared Declaration')]", "Navigation to Cleared Declarations Pool");
            Thread.sleep(3000);
            for (String Dec_No : numbers) {
                //Declaration Add Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declaration View:New']", "Declaration Add Button");
                //Thread.Sleep(3000);
                //Enter Declaration
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby=' s_1_l_DC_INS_Declaration_Number ']", "Declaration Number", Dec_No);
                //Declarations View Menu Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declaration View:Menu']", "Declarations View Menu");
                //Save Option - Declarations View Menu
                Result = Element.Click_Event(driver, test, reports, "//ul[@id='s_at_m_1-menu']/li/a[contains(text(), 'Save Record                [Ctrl+S]')]", "Click Save Button - Payment List Menu");
                //Thread.sleep(3000);
                //Click on Validate Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declaration View:Validate']", "Click on Validate Button");
                Thread.sleep(5000);
                if (map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("ON")) {
                    //Click on Multiple Booking GridCell
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_Multiple_Declaration_Flag']", "Click on Multiple Booking Grid Cell");
                    //Click on Multiple Booking CheckBox
                    Result = Element.Check_Box(driver, test, reports, "//input[@id='1_DC_Multiple_Declaration_Flag']", "Click on Multiple Booking CheckBox", ON);
                    //Focus Out
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Failure_Reason']", "Focus Out Remarks Section");
                    //Multiple Booking Request Pop Up Dialog
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                    if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
                        driver.switchTo().alert().accept();
                    }
                    /*
                     * try { wait.until(ExpectedConditions.alertIsPresent()); Alert alert =
                     * driver.switchTo().alert(); alert.accept();
                     * System.out.println("alert was present and accepted"); } catch(Exception e) {
                     * System.out.println("alert was not present"); System.out.print(e); }
                     */
                }
                //Declarations View Menu Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declaration View:Menu']", "Declarations View Menu");
                //Save Option - Declarations View Menu
                Result = Element.Click_Event(driver, test, reports, "//ul[@id='s_at_m_1-menu']/li/a[contains(text(), 'Save Record                [Ctrl+S]')]", "Click Save Button - Payment List Menu");
            }
            //Click on Initiate Button
            Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declaration View:Initiate Inspection']", "Click on Initiate Button");
            //Inspection Centre
            Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSInspectionCentre_Label']", "Inspection Centre", map_Inspection_Siebel.get("Inspection_Centre"));
            //Inspection section
            Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSInspectionSection_Label']", "Inspection section", map_Inspection_Siebel.get("Inspection_Section"));
            if (map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("OFF")) {
                //Contact Email
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactEmailId_Label']", "Contact Email", map_Inspection_Siebel.get("Contact_Email"));
                //Representative Name
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSRepresentativeName_Label']", "Representative Name", map_Inspection_Siebel.get("Representative_Name"));
                //Contact Number
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactNumber_Label']", "Contact Number", map_Inspection_Siebel.get("Contact_Number"));
                //Mobile Number
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Mobile_Number_Label']", "Mobile Number", map_Inspection_Siebel.get("Mobile_Number"));

            } else if (map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("ON")) {
                //Contact Email
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Contact_Email_Id_Label']", "Contact Email", map_Inspection_Siebel.get("Contact_Email"));
                //Representative Name
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSRepresentativeName_Label']", "Representative Name", map_Inspection_Siebel.get("Representative_Name"));
                //Contact Number
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSContactNumber_Label']", "Contact Number", map_Inspection_Siebel.get("Contact_Number"));
                //Mobile Number
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Mobile_Number_Label']", "Mobile Number", map_Inspection_Siebel.get("Mobile_Number"));

            }
            String Preferred_Date = new SimpleDateFormat("MM/dd/Y").format(new Date());
            if (map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("OFF")) {
                //Select All  Goods Details
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Containers/Marks & Numbers:Select All']", "Select All  Goods Details");
                //Group Dates Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Containers/Marks & Numbers:Group Dates']", "Group Dates Button");
                //preferred date 1 text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Preferred Date 1']", "Preferred date 1 text box", Preferred_Date);
                //preferred Slot 1 text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Preferred Slot 1']", "Preferred Slot 1 text box", map_Inspection_Siebel.get("Preferred_Slot_1"));
                //Additional preferred Slot text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Additional Preferred Slot']", "Additional preferred Slot text box", map_Inspection_Siebel.get("Additional_Preferred_Slot"));
                //Group Dates OK Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Group Dates:OK']", "Group Dates OK Button");


            } else if (map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("ON")) {
                //preferred date 1 text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Preferred_Date_1_Label']", "Preferred date 1 text box", Preferred_Date);
                //preferred Slot 1 text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Preferred_Slot_1_Label']", "Preferred Slot 1 text box", map_Inspection_Siebel.get("Preferred_Slot_1"));
                //Additional preferred Slot text box
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Preferred_Additional_Slot_Label']", "Additional preferred Slot text box", map_Inspection_Siebel.get("Additional_Preferred_Slot"));

            }
            //Seal Details
            if (map_Inspection_Siebel.get("Seal_Flag").equals("ON")) {
                //Seal Check Box
                Result = Element.Check_Box(driver, test, reports, "//input[@aria-labelledby='DCINSSealRequiredFlag_Label']", "Seal Check Box", ON);
                //Seal Check Box
                Result = Element.Click_Event(driver, test, reports, "//*[@aria-labelledby='DCINSClientStatus_Label']", "Client Status");
                //Seal Quantity
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DCINSNoofSealsRequired_Label']", "Seal Quantity", map_Inspection_Siebel.get("Seal_Quantity"));

            }
            //Validate payment button
            Result = Element.Click_Event(driver, test, reports, "//button[@title='Validate Payment']", "Validate Payment Button");
            //Error Handler Inconsistent Dialog
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent());
                Alert simpleAlert = driver.switchTo().alert();
                simpleAlert.accept();
                test.log(Status.INFO, "Record already in use alert present & accepted");
                WebElement ele = driver.findElement(By.xpath("//button[@title='Validate Payment']"));
                js.executeScript("arguments[0].scrollIntoView(true);",
                        driver.findElement(By.xpath("//button[@title='Validate Payment']")));
                js.executeScript("arguments[0].click();", ele);
            } catch (Exception e) {
                test.log(Status.INFO, "No Record already in use alert is present");
            } finally {
                test.log(Status.INFO, "Error Handling Records already in use : Completed");
            }
            Thread.sleep(3000);
            if (map_Inspection_Siebel.get("Payment_Mode").equals("Cash")) {
            } else if (map_Inspection_Siebel.get("Payment_Mode").equals("Credit Account") && map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("OFF")) {
                //Activate Payment Mode element
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Payment_Mode']", "Activate Payment Mode element");
                //Pay mode Selection
                Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Payment_Mode']", "Pay mode Selection", "Credit Account");
                //Activate Business Code element
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Credit_Acc_Business_Code']", "Activate Business Code element");
                //Thread.Sleep(2000);
                //Business Code Selection
                Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Credit_Acc_Business_Code']", "Business Code Selection", map_Inspection_Siebel.get("Business_Code"));
                //Activate Credit Account element
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Transaction_Type']", "Activate Credit Account element");
                //Credit Account No Selection
                Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Transaction_Type']", "Credit Account No Selection", map_Inspection_Siebel.get("Credit_Account"));
                //Click Menu Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Payment List:Menu']", "Click Menu Button");
                //Click Save Button - Payment List Menu
                Result = Element.Click_Event(driver, test, reports, "//*[@id=\"s_at_m_1-menu\"]/li[6]", "Click Save Button - Payment List Menu");
                //Confirm Payment
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_5_0_Ctrl']", "Confirm Payment");
                //Thread.Sleep(5000);
                //Payment Success
                Result = Element.Text_Present(driver, test, reports, "//td[@title='Authorized']", "Payment Success");
                //Submit
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_43_0_Ctrl']", "Submit");
                //Thread.Sleep(5000);
            } else if (map_Inspection_Siebel.get("Payment_Mode").equals("Credit Account") && map_Inspection_Siebel.get("Multiple_Declaration_Flag").equals("ON")) {
                //Seal Payment
                //Activate Payment Mode element
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Payment_Mode']", "Activate Payment Mode element");
                //Pay mode Selection
                Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Payment_Mode']", "Pay mode Selection", "Credit Account");
                //Activate Business Code element
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Credit_Acc_Business_Code']", "Activate Business Code element");
                //Business Code Selection
                Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Credit_Acc_Business_Code']", "Business Code Selection", map_Inspection_Siebel.get("Business_Code"));
                //Activate Credit Account element
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Transaction_Type']", "Activate Credit Account element");
                //Credit Account No Selection
                Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Transaction_Type']", "Credit Account No Selection", map_Inspection_Siebel.get("Credit_Account"));
                //Click Menu Button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Payment List:Menu']", "Click Menu Button");
                //Click Save Button - Payment List Menu
                Result = Element.Click_Event(driver, test, reports, "//*[@id=\"s_at_m_1-menu\"]/li[6]", "Click Save Button - Payment List Menu");
                //Confirm Payment
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_5_0_Ctrl']", "Confirm Payment");
                //Payment Success
                Result = Element.Text_Present(driver, test, reports, "//td[@title='Authorized']", "Payment Success");
                //Navigate to  Declarations Tab
                Result = Element.Tabs_select(driver, test, reports, "//*[@id=\"s_vctrl_div\"]//a[contains(text(), 'Declarations')]", "Initiate Request Tab");
                for (int count = 1; count <= k; count++) {
                    //Thread.Sleep(5000);
                    //Declaration number table
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='" + count + "_s_2_l_DC_INS_Declaration_Id']/a", "Declaration Link");
                    //Activate Payment Mode element
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Payment_Mode']", "Activate Payment Mode element");
                    //Pay mode Selection
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Payment_Mode']", "Pay mode Selection", "Credit Account");
                    //Activate Business Code element
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Credit_Acc_Business_Code']", "Activate Business Code element");
                    //Business Code Selection
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Credit_Acc_Business_Code']", "Business Code Selection", map_Inspection_Siebel.get("Business_Code"));
                    //Activate Credit Account element
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_1_l_DC_INS_Transaction_Type']", "Activate Credit Account element");
                    //Credit Account No Selection
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Transaction_Type']", "Credit Account No Selection", map_Inspection_Siebel.get("Credit_Account"));
                    //Click Menu Button
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Payment List:Menu']", "Click Menu Button");
                    //Click Save Button - Payment List Menu
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_at_m_1-menu']/li[6]", "Click Save Button - Payment List Menu");
                    //Confirm Payment
                    Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_5_0_Ctrl']", "Confirm Payment");
                    //Payment Success
                    Result = Element.Text_Present(driver, test, reports, "//td[@title='Authorized']", "Payment Success	");
                    //Go To Booking Request
                    Result = Element.Click_Event(driver, test, reports, "//*[@aria-label='Declaration:Go To Booking Request']", "Go To Booking Request");

                }
                //Submit
                Result = Element.Click_Event(driver, test, reports, "//*[@aria-label='<b>Booking Request Details</b>:Submit']", "Submit");
                Thread.sleep(5000);
                Result = "Pass";
            } else if (map_Inspection_Siebel.get("Payment_Mode").equals("ePay")) {
            }

        } catch (WebDriverException e) {
            System.out.println(e);
            Result = "Fail";
        }
        return Result;


    }

}
