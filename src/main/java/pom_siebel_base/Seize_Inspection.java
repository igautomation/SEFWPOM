package pom_siebel_base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
import com.aventstack.extentreports.reporter.*;

import pom_dubaitrade.DubaiTrade_Login;
import pom_dubaitrade.Initiate_Inspection_Cleared;
import utilities.Chrome_Browser;
import utilities.DB_Connections;
import utilities.Elements;
import utilities.IE_Browser;
import utilities.Page_Load_utilitie;
import utilities.ReadData_DubaiTrade;
import utilities.ReadData_Environment;
import utilities.ReadData_Siebel;
import utilities.Reports;
import utilities.ScreenShot;
import utilities.TakeScreenShot;

public class Seize_Inspection {


    WebDriver driver;
    WebElement element;
    public String Result;
    public ExtentSparkReporter htmlReporter;
    public ExtentReports reports;
    public ExtentTest test;
    public ReadData_DubaiTrade Read_Exceldata_Hashmap;
    public ReadData_Environment ReadData_Environment_Details;
    public ReadData_Siebel ReadData_Siebel_Hasmap;
    private IE_Browser getie;
    public Reports report_Generator;
    public String BookingRefNo;
    public String Planner_User;
    public String Booking_Request_Status;
    public String Booking_Request_Sub_Status;
    public String Dec_StatusNo;
    public String ReadStore_DecNo;
    public Elements Element;
    public ScreenShot Take_Screenshot;
    public Page_Load_utilitie PageLoad_Wait;

    private DubaiTrade_Login var_dtlogin;
    private Initiate_Inspection_Cleared var_initiate_inspection_cleared;
    public HashMap<String, String> map_Environment_Details;
    public HashMap<String, String> map_Inspection;
    public static DB_Connections Connect_DB;
    public static EService_Login EService_Login;
    public static Siebel_Login Siebel_login;
    private static Inspection_Planning INS_Planning;
    private static Release_Inspection INS_Release;
    public static Seize_Inspection INS_Seize;


    public String Seizure(WebDriver driver, ExtentTest test, ExtentReports reports, int j, String Test_Data_Path, String Sheet, String Environment, String Booking_Req_No) throws Exception {
        getie = new IE_Browser();
        Siebel_login = new Siebel_Login();
        EService_Login = new EService_Login();
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new ScreenShot();
        PageLoad_Wait = new Page_Load_utilitie();
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
        } else if (Sheet.equals("Inspection_Siebel")) {
            // Create Local Hash Map for data sheet
            map_Inspection = ReadData_Siebel_Hasmap.HashMap_Data_Inspection_Siebel(j, Test_Data_Path, Sheet);
        }
        List<String> numbers = Arrays.asList(map_Inspection.get("Declaration_No").split(","));
        int Dec_Count = numbers.size();
        try {
            if (map_Inspection.get("Inspection_Section").equals("Hatta")) {
                //Unassigned Request
                Result = Element.Click_Event(driver, test, reports, "//a[normalize-space()='Unassigned Requests']", "Unassigned Requests");
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
                Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS Booking Request Number']", "DrillDown Booking Request No");

            }
            Thread.sleep(5000);
            if (Result.equals("Pass") && map_Inspection.get("Multiple_Declaration_Flag").equalsIgnoreCase("OFF")) {
                //Start Inspection
                Result = Element.Click_Event(driver, test, reports, "//*[@class='siebui-btn-grp-search']/button[@data-display='Start Inspection']", "Start Inspection");
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Message:OK']", "AOR Message");

            }
            //Multiple Declaration Flow
            if (map_Inspection.get("Multiple_Declaration_Flag").equalsIgnoreCase("ON")) {
                //Start Inspection
                //button[@aria-label='Track Request:Start Inspection']
                Result = Element.Click_Event(driver, test, reports, "//*[@class='siebui-btn-grp-search']/button[@data-display='Start Inspection']", "Start Inspection");
                //AOR Message
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Message:OK']", "AOR Message");
                Thread.sleep(5000);
                for (int count = 1; count <= Dec_Count; count++) {
                    Thread.sleep(3000);
                    Result = Element.Click_Event(driver, test, reports, "//tr[@id=" + count + "]/td[@aria-labelledby='s_1_l_altCheckBox']", "Decleration Row select");
                    Result = Element.Click_Event(driver, test, reports, "//input[@name='DC_INS_Select_Flag']", "Decleration Row select CheckBox");
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declarations:Seizure Findings']", "Seizure Findings");
                    //Drilldown Seizure Report Number
                    Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS Booking Request Number']", "Drilldown Seizure Report Number");
                    Thread.sleep(5000);
                    //Means of Transport:
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Means of Transport']", "Means of Transport", map_Inspection.get("Means_of_Transport"));
                    // Number (Transport):
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Number (Transport)']", "Number (Transport)", map_Inspection.get("Number_Transport"));
                    //Flight #:
                    //input[@aria-label='Flight #']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Flight #']", "Flight #", map_Inspection.get("Flight_#"));
                    //Nature Of Transport:
                    //input[@aria-label='Nature Of Transport']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Nature Of Transport']", "Nature Of Transport", map_Inspection.get("Nature_Of_Transport"));
                    //Nature of Customs Center:
                    //input[@aria-label='Nature of Customs Center<img src="images/icon_req.gif" border="0">']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Nature_of_Customs_Centre_Label']", "Nature of Customs Center:", map_Inspection.get("Nature_of_Customs_Center"));
                    //Shift
                    //input[@aria-label='Shift<img src='images/icon_req.gif' border="0" height="7" width="8">']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Shift_Label']", "Shift", map_Inspection.get("Shift"));
                    //Detained Receipt Number:
                    //input[@aria-label='Detained Receipt Number']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Detained Receipt Number']", "Detained Receipt Number:", map_Inspection.get("Detained_Receipt_Number"));
                    //Section/Centre
                    //input[@aria-label='Section/Centre<img src="images/icon_req.gif" border="0" height="7" width="8">']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Section/Centre_Label']", "Section/Centre", map_Inspection.get("Section_Centre"));
                    //Location/Group
                    //span[@id='s_1_1_6_0_icon']
                    Result = Element.Click_Event(driver, test, reports, "//span[@id='s_1_1_6_0_icon']", "Location/Group");
                    //Location/Group
                    //td[contains(text(),'Emirates Inspection Group')]
                    Result = Element.Click_Event(driver, test, reports, "//td[contains(text(),'Emirates Inspection Group')]", "Location/Group");
                    //Location/Group - Pick Button
                    //button[@aria-label='Location:Pick']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Location:Pick']", "Location/Group - Pick Button");
                    //Section/Centre
                    //input[@aria-label='Section/Centre<img src="images/icon_req.gif" border="0" height="7" width="8">']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Section/Centre_Label']", "Section/Centre", map_Inspection.get("Section_Centre"));
                    //Seizure Save Record Button
                    //button[@id='s_1_1_31_0_Ctrl']
                    Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_31_0_Ctrl']", "Seizure Save Record Button");
                    //-----------------------------Fraud And Detection Details Tab--------------------------------------------------------//
                    //Fraud And Detection Details Tab
                    //a[contains(text(), 'Fraud And Detection Details')]
                    Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Fraud And Detection Details')]", "Fraud And Detection Details Tab");
                    //Pattern of Defraud
                    //span[@id='s_2_1_10_0_icon']
                    Result = Element.Click_Event(driver, test, reports, "//span[@id='s_2_1_10_0_icon']", "Pattern of Defraud");
                    //button[@aria-label='Pattern of Defraud:New']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Pattern of Defraud:New']", "Pattern of Defraud");
                    //input[@id='1_DC_INS_Pattern_of_Defraud']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Pattern_of_Defraud']", "Pattern of Defraud", map_Inspection.get("Pattern_of_Defraud"));
                    //button[@id='s_3_1_27_0_Ctrl']
                    Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_27_0_Ctrl']", "Pattern of Defraud");
                    //button[@aria-label='Pattern of Defraud:OK']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Pattern of Defraud:OK']", "Pattern of Defraud");
                    //Method Of Shipment
                    //input[@aria-label='Method Of Shipment']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Method Of Shipment']", "Method Of Shipment", map_Inspection.get("Method_Of_Shipment"));
                    //Method Of Concealing
                    //input[@aria-label='Method Of Concealing<img src="images/icon_req.gif" border="0">']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Method_of_Concealing_Label']", "Method Of Concealing", map_Inspection.get("Method_Of_Concealing"));
                    //Detection Aid
                    //input[@aria-label='Detection Aid']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Detection Aid']", "//Detection Aid", map_Inspection.get("Detection_Aid"));
                    //Method Of Detection
                    //input[@aria-label='Method Of Detection<img src="images/icon_req.gif" border="0">']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Method_of_Detection_Label']", "Method Of Detection", map_Inspection.get("Method_Of_Detection"));
                    //Inspection Source
                    //input[@aria-label='Inspection Source']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Inspection Source']", "Inspection Source", map_Inspection.get("Inspection_Source"));
                    //Seizure Information Source
                    //input[@aria-label='Seizure Information Source']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Seizure Information Source']", "Seizure Information Source", map_Inspection.get("Seizure_Information_Source"));
                    //Fraud And Detection Details:Save
                    //button[@aria-label='Fraud And Detection Details:Save']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Fraud And Detection Details:Save']", "Fraud And Detection Details:Save");
                    //-----------------------------------Attachments Tab---------------------------------------------------------------------//
                    //attachement Tab
                    //a[contains(text() , 'Attachments')]
                    Result = Element.Tabs_select(driver, test, reports, "//a[contains(text() , 'Attachments')]", "attachement Tab");
                    //Attachments:New tab
                    //button[@aria-label='Attachments:New']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Attachments:New']", "Attachments:New tab");
                    //Name icon
                    //span[@id='s_2_2_33_0_icon']
                    Result = Element.Click_Event(driver, test, reports, "//span[@id='s_2_2_33_0_icon']", "Name icon");
                    //input[@id='s_SweFileName_0']
                    //Result = Element.Click_Event(driver, test, reports, "//input[@id='s_SweFileName_0']", "Upload File");
                    driver.findElement(By.xpath("//input[@id='s_SweFileName_0']")).sendKeys(map_Inspection.get("Upload_File"));
                    //driver.findElement(By.id("inputFile")).sendKeys(map.get("Upload_File"));
                    Thread.sleep(2000);
                    //Search Keyword text box activate
                    //td[@id='1_s_2_l_DC_INS_Document_title']
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Comments']", "Search Keyword text box activate");
                    //Search Keyword text box
                    //input[@id='1_DC_INS_Comments']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Comments']", "Search Keyword text box", map_Inspection.get("Search_Keyword"));
                    //document type text box activate
                    //td[@id='1_s_2_l_DC_INS_Document_title']
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Document_title']", "document type text box activate");
                    //document type text box
                    //input[@id='1_DC_INS_Document_title']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Document_title']", "document type text box", map_Inspection.get("Document_Type"));
                    //Attachments:Save
                    //button[@aria-label='Attachments:Save']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Attachments:Save']", "Attachments:Save");
                    //-----------------------------------Violator Details Tab---------------------------------------------------------------------//
                    //Violator Details Tab
                    //a[contains(text() , 'Violator Details')]
                    Result = Element.Tabs_select(driver, test, reports, "//a[contains(text() , 'Violator Details')]", "Violator Details Tab");
                    //Seizure:New button
                    //button[@aria-label='Seizure:New']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seizure:New']", "Seizure:New button");
                    //Involvement Type
                    //input[@aria-label='Involvement Type']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Involvement Type']", "Involvement Type", map_Inspection.get("Involvement_Type"));
                    //Type of Visa
                    //input[@aria-label='Type Of Visa']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Type Of Visa']", "Type of Visa", map_Inspection.get("Type_Of_Visa"));
                    //Full name button Icon
                    //span[@id='s_3_1_4_0_icon']
                    Result = Element.Click_Event(driver, test, reports, "//span[@id='s_3_1_4_0_icon']", "Full name button Icon");
                    List<String> Full_Name = Arrays.asList(map_Inspection.get("Full_Name").split(","));
                    if (count == 1) {
                        //Select Name
                        Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_4_l_M_M']", "Select Name");

                    } else if (count == 2) {
                        Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_4_l_M_M']", "Select Name");
                    }
                    //ok button
                    //button[@aria-label='Pick Contact:OK']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Pick Contact:OK']", "ok button");
                    Thread.sleep(3000);
                    //no of previous offence
                    //input[@aria-label='No Of Previous Offences']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='No Of Previous Offences']", "no of previous offence", map_Inspection.get("No_Of_Previous_Offences"));
                    //Statements
                    //input[@aria-label='Statements']
                    //textarea[@name='s_3_1_9_0']
                    Result = Element.TextBox_Value(driver, test, reports, "//textarea[@name='s_3_1_9_0']", "Statements", map_Inspection.get("Statements"));
                    //Seizure:Save
                    //button[@aria-label='Seizure:Save']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seizure:Save']", "Seizure:Save");
                    //-----------------------------------Report Initiators Tab---------------------------------------------------------------------//
                    //Report Initiators Tab
                    //a[contains(text() , 'Report Initiators')]
                    Result = Element.Tabs_select(driver, test, reports, "//a[contains(text() , 'Report Initiators')]", "Report Initiators Tab");
                    //Report Initiators:New
                    //button[@aria-label='Report Initiators:New']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Report Initiators:New']", "Report Initiators:New");
                    //Report Initiators Name icon
                    //span[@id='s_2_2_28_0_icon']
                    Result = Element.Click_Event(driver, test, reports, "//span[@id='s_2_2_28_0_icon']", "Report Initiators Name icon");
                    //Name
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_3_1_17_0_Ctrl']", "Query");
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_3_l_Login_Name']", "USER ID Text Box Activate");
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_Login_Name']", "USER ID Text Box", map_Inspection.get("Report_Initiator_Name"));
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_3_1_14_0_Ctrl']", "Query Go");
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_3_1_36_0_Ctrl']", "Query OK");
                    //Employee Number
                    //td[@id='1_s_2_l_DC_INS_Employee_Number']
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Employee_Number']", "Employee Number");
                    //Employee Number Text Box
                    //input[@id='1_DC_INS_Employee_Number']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Employee_Number']", "Employee Number Text Box", map_Inspection.get("Employee_Number"));
                    //Role in seizure Report
                    //td[@id='1_s_2_l_DC_INS_Role_In_Seizure_Report']
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Role_In_Seizure_Report']", "Role in seizure Report");
                    //Role in seizure Report text Box
                    //input[@id='1_DC_INS_Role_In_Seizure_Report']
                    Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Role_In_Seizure_Report']", "Role in seizure Report text Box", map_Inspection.get("Role_In_Seizure_Report"));
                    //Initiator_Responsible_For_Seizure
                    //td[@id='1_s_2_l_DC_INS_Is_Initiator_Responsible_For_Seizure']
                    Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Is_Initiator_Responsible_For_Seizure']", "Initiator_Responsible_For_Seizure");
                    //Initiator_Responsible_For_Seizure Check Box
                    //input[@id='1_DC_INS_Is_Initiator_Responsible_For_Seizure']
                    Result = Element.Check_Box(driver, test, reports, "//input[@id='1_DC_INS_Is_Initiator_Responsible_For_Seizure']", "Initiator_Responsible_For_Seizure Check Box", "");
                    //Report Initiators:Save
                    //button[@aria-label='Report Initiators:Save']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Report Initiators:Save']", "Report Initiators:Save");
                    //-----------------------------------Witness Details Tab---------------------------------------------------------------------//
                    //Witness Details Tab
                    //a[contains(text() , 'Witness Details')]
                    Result = Element.Tabs_select(driver, test, reports, "//a[contains(text() , 'Witness Details')]", "Witness Details Tab");
                    //Witness Details:New
                    //button[@aria-label='Witness Details:New']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Witness Details:New']", "Witness Details:New");
                    //name Icon
                    //span[@id='s_1_2_27_0_icon']
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_2_27_0_icon']", "Name Icon");
                    Thread.sleep(3000);
                    //select Name
                    //td[contains(text(), 'ALTHAFF AHMED')]
                    //Result = Element.Click_Event(driver, test, reports, "//td[contains(text(), " + map_Inspection.get("Full_Name") + ")]", "select Name");
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_3_l_Full_Name']", "Select Witness Name");
                    //Pick Witness:OK
                    //button[@aria-label='Pick Witness:OK']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Pick Witness:OK']", "Pick Witness:OK");
                    //Statement
                    //input[@id='Statements']
                    Result = Element.TextBox_Value(driver, test, reports, "//textarea[@aria-label='Statements']", "Statement", map_Inspection.get("Witness_Statement"));
                    //Witness Details:Save
                    //button[@aria-label='Witness Details:Save']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Witness Details:Save']", "Witness Details:Save");
                    //-----------------------------------Additional Information Tab---------------------------------------------------------------------//
                    //Additional Information Tab
                    //a[contains(text() , 'Additional Information')]
                    Result = Element.Tabs_select(driver, test, reports, "//a[contains(text() , 'Additional Information')]", "Additional Information Tab");
                    //Fact Details
                    //input[@aria-label='Fact Details<img src="images/icon_req.gif" border="0">']
                    Result = Element.TextBox_Value(driver, test, reports, "//textarea[@aria-labelledby='DC_INS_Fact_Details_Label']", "Fact Details", map_Inspection.get("Fact_Details"));
                    //Additional Information:Save
                    //button[@aria-label='Additional Information:Save']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Additional Information:Save']", "Additional Information:Save");
                    //-----------------------------------validate Seizure---------------------------------------------------------------------//
                    //Validate Seizure
                    //button[@id="s_1_1_16_0_Ctrl"]
                    Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_16_0_Ctrl']", "Validate Seizure");
                    //Back Button
                    //button[@aria-label='Seizure:Back']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seizure:Back']", "Back Button");
                    Thread.sleep(3000);
                    Result = Element.Click_Event(driver, test, reports, "//tr[@id=" + count + "]/td[@id='" + count + "_s_1_l_DC_INS_Declaration_Number']", "Decleration Row select");
                    //Release for Sieze
                    //button[@aria-label='Track Request:Request For Seize']
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declarations:Request For Seize']", "Request for Sieze");
                    Thread.sleep(3000);

                }
                test.log(Status.PASS, "Sieze finding activity is submitted successfully");
                reports.flush();
                Thread.sleep(5000);
                driver.quit();

            } else {
                //Seizure Findings
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Track Request:Seizure Findings']", "Seizure Findings");
                //Drilldown Seizure Report Number
                Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS Booking Request Number']", "Drilldown Seizure Report Number");
                Thread.sleep(5000);
                //Means of Transport:
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Means of Transport']", "Means of Transport", map_Inspection.get("Means_of_Transport"));
                // Number (Transport):
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Number (Transport)']", "Number (Transport)", map_Inspection.get("Number_Transport"));
                //Flight #:
                //input[@aria-label='Flight #']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Flight #']", "Flight #", map_Inspection.get("Flight_#"));
                //Nature Of Transport:
                //input[@aria-label='Nature Of Transport']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Nature Of Transport']", "Nature Of Transport", map_Inspection.get("Nature_Of_Transport"));
                //Nature of Customs Center:
                //input[@aria-label='Nature of Customs Center<img src="images/icon_req.gif" border="0">']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Nature_of_Customs_Centre_Label']", "Nature of Customs Center:", map_Inspection.get("Nature_of_Customs_Center"));
                //Shift
                //input[@aria-label='Shift<img src='images/icon_req.gif' border="0" height="7" width="8">']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Shift_Label']", "Shift", map_Inspection.get("Shift"));
                //Section/Centre
                //input[@aria-label='Section/Centre<img src="images/icon_req.gif" border="0" height="7" width="8">']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Section/Centre_Label']", "Section/Centre", map_Inspection.get("Section_Centre"));
                //Detained Receipt Number:
                //input[@aria-label='Detained Receipt Number']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Detained Receipt Number']", "Detained Receipt Number:", map_Inspection.get("Detained_Receipt_Number"));
                //Location/Group
                //span[@id='s_1_1_6_0_icon']
                Result = Element.Click_Event(driver, test, reports, "//span[@id='s_1_1_6_0_icon']", "Location/Group");
                //Location/Group
                //td[contains(text(),'Emirates Inspection Group')]
                Result = Element.Click_Event(driver, test, reports, "//td[contains(text(),'Emirates Inspection Group')]", "Location/Group");
                //Location/Group - Pick Button
                //button[@aria-label='Location:Pick']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Location:Pick']", "Location/Group - Pick Button");
                //Seizure Save Record Button
                //button[@id='s_1_1_31_0_Ctrl']
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_31_0_Ctrl']", "Seizure Save Record Button");
                //-----------------------------Fraud And Detection Details Tab--------------------------------------------------------//
                //Fraud And Detection Details Tab
                //a[contains(text(), 'Fraud And Detection Details')]
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Fraud And Detection Details')]", "Fraud And Detection Details Tab");
                //Pattern of Defraud
                //span[@id='s_2_1_10_0_icon']
                Result = Element.Click_Event(driver, test, reports, "//span[@id='s_2_1_10_0_icon']", "Pattern of Defraud");
                //button[@aria-label='Pattern of Defraud:New']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Pattern of Defraud:New']", "Pattern of Defraud");
                //input[@id='1_DC_INS_Pattern_of_Defraud']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Pattern_of_Defraud']", "Pattern of Defraud", map_Inspection.get("Pattern_of_Defraud"));
                //button[@id='s_3_1_27_0_Ctrl']
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_3_1_27_0_Ctrl']", "Pattern of Defraud");
                //button[@aria-label='Pattern of Defraud:OK']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Pattern of Defraud:OK']", "Pattern of Defraud");
                //Method Of Shipment
                //input[@aria-label='Method Of Shipment']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Method Of Shipment']", "Method Of Shipment", map_Inspection.get("Method_Of_Shipment"));
                //Method Of Concealing
                //input[@aria-label='Method Of Concealing<img src="images/icon_req.gif" border="0">']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Method_of_Concealing_Label']", "Method Of Concealing", map_Inspection.get("Method_Of_Concealing"));
                //Detection Aid
                //input[@aria-label='Detection Aid']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Detection Aid']", "//Detection Aid", map_Inspection.get("Detection_Aid"));
                //Method Of Detection
                //input[@aria-label='Method Of Detection<img src="images/icon_req.gif" border="0">']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-labelledby='DC_INS_Method_of_Detection_Label']", "Method Of Detection", map_Inspection.get("Method_Of_Detection"));
                //Inspection Source
                //input[@aria-label='Inspection Source']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Inspection Source']", "Inspection Source", map_Inspection.get("Inspection_Source"));
                //Seizure Information Source
                //input[@aria-label='Seizure Information Source']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Seizure Information Source']", "Seizure Information Source", map_Inspection.get("Seizure_Information_Source"));
                //Fraud And Detection Details:Save
                //button[@aria-label='Fraud And Detection Details:Save']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Fraud And Detection Details:Save']", "Fraud And Detection Details:Save");
                //-----------------------------------Attachments Tab---------------------------------------------------------------------//
                //attachement Tab
                //a[contains(text() , 'Attachments')]
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text() , 'Attachments')]", "attachement Tab");
                //Attachments:New tab
                //button[@aria-label='Attachments:New']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Attachments:New']", "Attachments:New tab");
                //Name icon
                //span[@id='s_2_2_33_0_icon']
                Result = Element.Click_Event(driver, test, reports, "//span[@id='s_2_2_33_0_icon']", "Name icon");
                //input[@id='s_SweFileName_0']
                //Result = Element.Click_Event(driver, test, reports, "//input[@id='s_SweFileName_0']", "Upload File");
                driver.findElement(By.xpath("//input[@id='s_SweFileName_0']")).sendKeys(map_Inspection.get("Upload_File"));
                //driver.findElement(By.id("inputFile")).sendKeys(map.get("Upload_File"));
                Thread.sleep(2000);
                //Search Keyword text box activate
                //td[@id='1_s_2_l_DC_INS_Document_title']
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Comments']", "Search Keyword text box activate");
                //Search Keyword text box
                //input[@id='1_DC_INS_Comments']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Comments']", "Search Keyword text box", map_Inspection.get("Search_Keyword"));
                //document type text box activate
                //td[@id='1_s_2_l_DC_INS_Document_title']
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Document_title']", "document type text box activate");
                //document type text box
                //input[@id='1_DC_INS_Document_title']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Document_title']", "document type text box", map_Inspection.get("Document_Type"));
                //Attachments:Save
                //button[@aria-label='Attachments:Save']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Attachments:Save']", "Attachments:Save");
                //-----------------------------------Violator Details Tab---------------------------------------------------------------------//
                //Violator Details Tab
                //a[contains(text() , 'Violator Details')]
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text() , 'Violator Details')]", "Violator Details Tab");
                //Seizure:New button
                //button[@aria-label='Seizure:New']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seizure:New']", "Seizure:New button");
                //Involvement Type
                //input[@aria-label='Involvement Type']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Involvement Type']", "Involvement Type", map_Inspection.get("Involvement_Type"));
                //Type of Visa
                //input[@aria-label='Type Of Visa']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='Type Of Visa']", "Type of Visa", map_Inspection.get("Type_Of_Visa"));
                //Full name button Icon
                //span[@id='s_3_1_4_0_icon']
                Result = Element.Click_Event(driver, test, reports, "//span[@id='s_3_1_4_0_icon']", "Full name button Icon");
                //Select Name
                //td[contains(text(),'ALTHAFF AHMED')]
                //Result = Element.Click_Event(driver, test, reports, "//td[contains(text()," + map_Inspection.get("Full_Name") + ")]", "Select Name");
                //Result = Element.Click_Event(driver, test, reports, "//td[contains(text()," + map_Inspection.get("Full_Name") + ")]", "Select Name");
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_4_l_M_M']", "Select Name");
                //ok button
                //button[@aria-label='Pick Contact:OK']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Pick Contact:OK']", "ok button");
                //no of previous offence
                //input[@aria-label='No Of Previous Offences']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@aria-label='No Of Previous Offences']", "no of previous offence", map_Inspection.get("No_Of_Previous_Offences"));
                //Statements
                //input[@aria-label='Statements']
                //textarea[@name='s_3_1_9_0']
                Result = Element.TextBox_Value(driver, test, reports, "//textarea[@name='s_3_1_9_0']", "Statements", map_Inspection.get("Statements"));
                //Seizure:Save
                //button[@aria-label='Seizure:Save']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seizure:Save']", "Seizure:Save");
                //-----------------------------------Report Initiators Tab---------------------------------------------------------------------//
                //Report Initiators Tab
                //a[contains(text() , 'Report Initiators')]
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text() , 'Report Initiators')]", "Report Initiators Tab");
                //Report Initiators:New
                //button[@aria-label='Report Initiators:New']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Report Initiators:New']", "Report Initiators:New");
                //Report Initiators Name icon
                //span[@id='s_2_2_28_0_icon']
                Result = Element.Click_Event(driver, test, reports, "//span[@id='s_2_2_28_0_icon']", "Report Initiators Name icon");
                //Name
                Result = Element.Click_Event(driver, test, reports, "//*[@id='s_3_1_17_0_Ctrl']", "Query");
                Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_3_l_Login_Name']", "USER ID Text Box Activate");
                Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_Login_Name']", "USER ID Text Box", map_Inspection.get("Report_Initiator_Name"));
                Result = Element.Click_Event(driver, test, reports, "//*[@id='s_3_1_14_0_Ctrl']", "Query Go");
                Result = Element.Click_Event(driver, test, reports, "//*[@id='s_3_1_36_0_Ctrl']", "Query OK");
                //Employee Number
                //td[@id='1_s_2_l_DC_INS_Employee_Number']
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Employee_Number']", "Employee Number");
                //Employee Number Text Box
                //input[@id='1_DC_INS_Employee_Number']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Employee_Number']", "Employee Number Text Box", map_Inspection.get("Employee_Number"));
                //Role in seizure Report
                //td[@id='1_s_2_l_DC_INS_Role_In_Seizure_Report']
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Role_In_Seizure_Report']", "Role in seizure Report");
                //Role in seizure Report text Box
                //input[@id='1_DC_INS_Role_In_Seizure_Report']
                Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Role_In_Seizure_Report']", "Role in seizure Report text Box", map_Inspection.get("Role_In_Seizure_Report"));
                //Initiator_Responsible_For_Seizure
                //td[@id='1_s_2_l_DC_INS_Is_Initiator_Responsible_For_Seizure']
                Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_2_l_DC_INS_Is_Initiator_Responsible_For_Seizure']", "Initiator_Responsible_For_Seizure");
                //Initiator_Responsible_For_Seizure Check Box
                //input[@id='1_DC_INS_Is_Initiator_Responsible_For_Seizure']
                Result = Element.Check_Box(driver, test, reports, "//input[@id='1_DC_INS_Is_Initiator_Responsible_For_Seizure']", "Initiator_Responsible_For_Seizure Check Box", "");
                //Report Initiators:Save
                //button[@aria-label='Report Initiators:Save']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Report Initiators:Save']", "Report Initiators:Save");
                //-----------------------------------Witness Details Tab---------------------------------------------------------------------//
                //Witness Details Tab
                //a[contains(text() , 'Witness Details')]
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text() , 'Witness Details')]", "Witness Details Tab");
                //Witness Details:New
                //button[@aria-label='Witness Details:New']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Witness Details:New']", "Witness Details:New");
                //name Icon
                //span[@id='s_1_2_27_0_icon']
                Result = Element.Click_Event(driver, test, reports, "//span[@id='s_1_2_27_0_icon']", "Name Icon");
                //select Witness Name
                //td[contains(text(), 'ALTHAFF AHMED')]
                //Result = Element.Click_Event(driver, test, reports, "1_s_3_l_Full_Name", "Select Witness Name");
                Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_3_l_Full_Name']", "Select Witness Name");
                //Pick Witness:OK
                //button[@aria-label='Pick Witness:OK']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Pick Witness:OK']", "Pick Witness:OK");
                //Statement
                //input[@id='Statements']
                Result = Element.TextBox_Value(driver, test, reports, "//textarea[@aria-label='Statements']", "Statement", map_Inspection.get("Witness_Statement"));
                //Witness Details:Save
                //button[@aria-label='Witness Details:Save']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Witness Details:Save']", "Witness Details:Save");
                //-----------------------------------Additional Information Tab---------------------------------------------------------------------//
                //Additional Information Tab
                //a[contains(text() , 'Additional Information')]
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text() , 'Additional Information')]", "Additional Information Tab");
                //Fact Details
                //input[@aria-label='Fact Details<img src="images/icon_req.gif" border="0">']
                Result = Element.TextBox_Value(driver, test, reports, "//textarea[@aria-labelledby='DC_INS_Fact_Details_Label']", "Fact Details", map_Inspection.get("Fact_Details"));
                //Additional Information:Save
                //button[@aria-label='Additional Information:Save']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Additional Information:Save']", "Additional Information:Save");
                //-----------------------------------validate Seizure---------------------------------------------------------------------//
                //Validate Seizure
                //button[@id="s_1_1_16_0_Ctrl"]
                Result = Element.Click_Event(driver, test, reports, "//button[@id='s_1_1_16_0_Ctrl']", "Validate Seizure");
                //Back Button
                //button[@aria-label='Seizure:Back']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Seizure:Back']", "Back Button");
                //Release for Sieze
                //button[@aria-label='Track Request:Request For Seize']
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Track Request:Request For Seize']", "Release for Sieze");
                test.log(Status.PASS, "Sieze finding activity is submitted successfully");
                reports.flush();
                Thread.sleep(5000);
                driver.close();

            }
            //--------------------------------ED-------------------------------------------------------------------------------//
            //-------------------------------Initiate Browser------------------------------------------------------------------//
            if (map_Environment_Details.get("Browser").equals("IE")) {
                driver = getie.IEBrowser(driver);

            } else if (map_Environment_Details.get("Browser").equals("CHROME")) {
            }
            //-----------------------------------Enter URL-----------------------------------------------------------------------//
            driver.get(map_Environment_Details.get("SiebelBase_URL"));
            //---------------------------Login to Application--------------------------------------------------------------------//
            for (int i = 1; i <= 5; i++) {
                Result = Siebel_login.Siebel_Login_Function(driver, test, reports, Environment, Test_Data_Path, "ED");
                if (driver.findElements(By.xpath("//a[contains(text() , 'Monitor Inspection')]")).size() != 0) {
                    break;
                } else {
                    driver.quit();
                    driver = getie.IEBrowser(driver);
                    driver.get(map_Environment_Details.get("SiebeleService_URL"));
                }
            }
            if (Result.equals("Pass")) {
                test.log(Status.PASS, "Siebel logged in successfully with Planner User : " + map_Environment_Details.get("ED_UserID"));
                reports.flush();
            } else {
                test.log(Status.FAIL, "Siebel logged in Failed");
                reports.flush();
            }
            //---------------------------------------------------------------------------------------------------------------------//
            //Monitor Inspection Tab
            Result = Element.Tabs_select(driver, test, reports, "//a[normalize-space()='Monitor Inspection']", "Monitor Inspection Tab");
            //Monitor Inspection sub Section
            //Result = Element.Click_Event(driver, test, reports, "/html/body/div[1]/div/div[4]/div/div/div[1]/div[2]/ul/li[2]/a", "Monitor Inspection sub Section");
            Result = Element.Click_Event(driver, test, reports, "//li[@class='ui-tabs-tab ui-corner-top ui-state-default ui-tab']//a[@role='presentation'][normalize-space()='Monitor Inspection']", "Monitor Inspection sub Section");
            //Search Icon
            Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Query']", "Search Icon");
            //Request No TextBox
            Result = Element.TextBox_Value(driver, test, reports, "//input[@id='1_DC_INS_Booking_Reference_Number']", "Request No TextBox", Booking_Req_No);
            //Activity:Go
            Result = Element.Click_Event(driver, test, reports, "//button[@name='s_1_1_7_0']", "Activity:Go");
            //Drilldown Request No
            Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS Booking Reference Number']", "Drilldown Request No");
            //Multiple Declaration Flow
            if (map_Inspection.get("Multiple_Declaration_Flag").equalsIgnoreCase("ON")) {
                for (String Dec_No : numbers) {
                    Thread.sleep(5000);
                    Result = Element.Click_Event(driver, test, reports, "//td[@title='" + Dec_No + "']", "select declaration row");
                    //Approve
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declarations:Approve']", "Approve");
                    Thread.sleep(5000);
                    if (ExpectedConditions.alertIsPresent() != null) {
                        driver.switchTo().alert().accept();
                    }

                }
            } else {
                //Approve
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Containers/Marks & Numbers:Approve']", "Approve");
                if (ExpectedConditions.alertIsPresent() != null) {
                    driver.switchTo().alert().accept();
                }
            }
            test.log(Status.PASS, "Containers/Marks & Number is approved successfully by ED User");
            reports.flush();
            driver.close();
            Thread.sleep(5000);
            //------------------------Initialise Browser---------------------------------------------------------------------------------
            if (map_Environment_Details.get("Browser").equals("IE")) {
                driver = getie.IEBrowser(driver);

            } else if (map_Environment_Details.get("Browser").equals("CHROME")) {
            }
            //-----------------------------------Enter URL-------------------------------------------------------------------------------
            driver.get(map_Environment_Details.get("SiebeleService_URL"));
            //---------------------------Login to Application--------------------------------------------------------------------------------
            if (map_Inspection.get("Inspection_Centre").equals("Hatta")) {
                Result = EService_Login.EService_Login_Function(driver, test, reports, Environment, Test_Data_Path,
                        "Hatta");

            } else {
                Result = EService_Login.EService_Login_Function(driver, test, reports, Environment, Test_Data_Path,
                        "Inspector");
            }
            if (Result.equals("Pass")) {
                test.log(Status.PASS, "Siebel logged in successfully with Inspector User : "
                        + map_Environment_Details.get("Inspector_UserID"));
                reports.flush();
            } else {
                test.log(Status.FAIL, "Siebel logged in Failed");
                reports.flush();
                driver.quit();
            }
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
            Result = Element.Click_Event(driver, test, reports, "//a[@name='DC INS Booking Request Number']", "DrillDown Booking Request No");
            //Multiple Declaration Flow
            if (map_Inspection.get("Multiple_Declaration_Flag").equalsIgnoreCase("ON")) {
                //Inspection Finding Tab
                //Result = Element.Tabs_select(driver, test, reports, "/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div/div/div[1]/div/div[2]/div[2]/ul/li[3]/a", "Inspection Finding Tab");
                Result = Element.Click_Event(driver, test, reports, "//a[normalize-space()='Inspection Findings']", "Click on Inspection Findings Tab");
                for (String Dec_No : numbers) {
                    Thread.sleep(5000);
                    Result = Element.Click_Event(driver, test, reports, "//td[@title='" + Dec_No + "']", "Declaration row select");
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
                test.log(Status.PASS, "Risk Mittigated Successfully");
                reports.flush();
                //Inspection Activities Tab
                //Result = Element.Tabs_select(driver, test, reports, "/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div/div/div[1]/div/div[2]/div[2]/ul/li[2]/a", "Inspection Activities Tab");
                Result = Element.Click_Event(driver, test, reports, "//a[normalize-space()='Inspection Activities']", "Click on Inspection Activities Tab");
                //Seize button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Declarations:Seize']", "Seize button");

            } else {
                //Inspection Finding Tab
                //Result = Element.Tabs_select(driver, test, reports, "/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div/div/div[1]/div/div[2]/div[2]/ul/li[4]/a", "Inspection Finding Tab");
                Result = Element.Click_Event(driver, test, reports, "//a[normalize-space()='Inspection Findings']", "Click on Inspection Findings Tab");
                //Thread.sleep(3000);
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
                test.log(Status.PASS, "Risk Mittigated Successfully");
                reports.flush();
                //Inspection Activities Tab
                //Result = Element.Tabs_select(driver, test, reports, "/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div/div/div[1]/div/div[2]/div[2]/ul/li[3]/a", "Inspection Activities Tab");
                Result = Element.Click_Event(driver, test, reports, "//a[normalize-space()='Inspection Activities']", "Click on Inspection Activities Tab");
                Thread.sleep(5000);
                //Seize button
                Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Track Request:Seize']", "Seize button");
                Result = "Pass";
                driver.quit();
            }

        } catch (WebDriverException e) {
            System.out.println(e);
            Result = "Fail";
            test.log(Status.FAIL, "Seize Inspection Failed");
            driver.quit();
            reports.flush();
        }
        return Result;
    }
}
