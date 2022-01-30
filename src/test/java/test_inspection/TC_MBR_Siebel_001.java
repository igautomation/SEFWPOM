package test_inspection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pom_dubaitrade.DubaiTrade_Login;
import pom_dubaitrade.Initiate_Inspection_Cleared;
import pom_siebel_base.*;
import utilities.Chrome_Browser;
import utilities.DB_Connections;
import utilities.ExcelApiTest;
import utilities.IE_Browser;
import utilities.ReadData_DubaiTrade;
import utilities.ReadData_Environment;
import utilities.ReadData_Siebel;
import utilities.Reports;
import utilities.TakeScreenShot;

public class TC_MBR_Siebel_001 {

    WebDriver driver;
    WebElement element;
    public String Result;
    public ExtentSparkReporter htmlReporter;
    public ExtentReports reports;
    public ExtentTest test;
    public ReadData_Siebel Read_Exceldata_Hashmap;
    public ReadData_Environment ReadData_Environment_Details;
    public ReadData_Siebel ReadData_Siebel_Hasmap;
    private IE_Browser getie;
    public Reports report_Generator;
    public TakeScreenShot Take_Screenshot;
    public String BookingRefNo;
    public String Planner_User;
    public String Booking_Request_Status;
    public String Booking_Request_Sub_Status;
    public String Dec_StatusNo;
    public String ReadStore_DecNo;

    private DubaiTrade_Login var_dtlogin;
    private Siebel_Initiate_Inspection_Cleared var_initiate_inspection_cleared;
    public HashMap<String, String> map_Environment_Details;
    public HashMap<String, String> map_Inspection_Siebel;
    public static DB_Connections Connect_DB;
    public static Siebel_Login Siebel_login;
	public static EService_Login EService_Login;
    private static Inspection_Planning INS_Planning;
    private static Release_Inspection INS_Release;
    public static Seize_Inspection INS_Seize;

    @Parameters({"Test_Data_Path", "Environment"})
    @Test
    public void TC_MBR_Siebel_01(String Test_Data_Path, String Environment) throws Exception {
        //------------------------Initialise Browser---------------------------------------------------------------------------------------------
        getie = new IE_Browser();
        new Chrome_Browser();
        Take_Screenshot = new TakeScreenShot();
        Connect_DB = new DB_Connections();
        Siebel_login = new Siebel_Login();
		EService_Login = new EService_Login();
        INS_Planning = new Inspection_Planning();
        INS_Release = new Release_Inspection();
        INS_Seize = new Seize_Inspection();
        //------------------------Initialise Excel data read through HashMap----------------------------------------------------------------------
        ExcelApiTest Read_Data = new ExcelApiTest(Test_Data_Path);
        int NoOf_Itration = Read_Data.ExcelIteration_Count(Test_Data_Path, "Inspection_Siebel");
        Read_Exceldata_Hashmap = new ReadData_Siebel();
        var_initiate_inspection_cleared = new Siebel_Initiate_Inspection_Cleared();
        ReadData_Environment_Details = new utilities.ReadData_Environment();
        // Create Local Hash Map for Environment sheet
        map_Environment_Details = ReadData_Environment_Details.HashMap_Data_Environment_Details(Environment, Test_Data_Path);
        NoOf_Itration = NoOf_Itration - 1;
        //------------------------Start of Test Iteration-----------------------------------------------------------------------------------------
        for (int i = 1; i <= NoOf_Itration; i++) {
            // Create Local Hash Map for Inspection_Siebel sheet
            map_Inspection_Siebel = Read_Exceldata_Hashmap.HashMap_Data_Inspection_Siebel(i, Test_Data_Path,
                    "Inspection_Siebel");
            if (map_Inspection_Siebel.get("Execute").equals("NO")) {
                continue;
            }
            if (map_Inspection_Siebel.get("TestCaseNo").equals("TC_MBR_Siebel_001")) {
                reports = Reports.reports;
                reports.setSystemInfo("Host Name", "LocalHost");
                reports.setSystemInfo("Run ID", Reports.Run_ID);
                reports.setSystemInfo("OS", "Windows 10");
                reports.setSystemInfo("Tester Name", "Automation");
                reports.setSystemInfo("Browser", map_Environment_Details.get("Browser"));
                test = reports.createTest(map_Inspection_Siebel.get("TestCaseDescription"));
                List<String> numbers = Arrays.asList(map_Inspection_Siebel.get("Declaration_No").split(","));
                for (String Dec_No : numbers) {
                    Dec_StatusNo = DB_Connections.Declaration_Clear_Status(Dec_No, Environment);
                    ReadStore_DecNo = DB_Connections.Read_Store_Status(Dec_No, Environment);
                    if ((Dec_StatusNo.equals("6") && ReadStore_DecNo.equals(Dec_No)) || (Dec_StatusNo.equals("7") && ReadStore_DecNo.equals(Dec_No))) {
                        System.out.println(Dec_No);
                    } else {
                        reports.flush();
                        driver.quit();
                        continue;
                    }
                }
                //------------------------Initialise Browser---------------------------------------------------------------------------------
                if (map_Environment_Details.get("Browser").equals("IE")) {
                    driver = getie.IEBrowser(driver);

                } else if (map_Environment_Details.get("Browser").equals("CHROME")) {
                }
                //-----------------------------------Enter URL-------------------------------------------------------------------------------
                driver.get(map_Environment_Details.get("SiebelBase_URL"));
                Result = Siebel_login.Siebel_Login_Function(driver, test, reports, Environment, Test_Data_Path,
                        "Booking");
                if (Result.equals("Pass")) {
                    test.log(Status.PASS, "Siebel logged in successfully with Planner User : "
                            + map_Environment_Details.get("Planner_UserID"));
                    reports.flush();
                } else {
                    test.log(Status.FAIL, "Siebel logged in Failed");
                    reports.flush();
                    driver.quit();
                    continue;
                }
                //----------------------------------------------------Initiate Inspection Cleared----------------------------------------------
                Result = var_initiate_inspection_cleared.Fn_Siebel_Initiate_Inspection_Cleared(driver, test, reports, i,
                        Test_Data_Path, "Inspection_Siebel", Environment);
                // Retrieve Booking Ref NO From DB
                if (Result.equals("Pass")) {
                    for (String Dec_No : numbers) {
                        BookingRefNo = DB_Connections.BooKingRequest_Declaration_No(Dec_No, Environment);
                        if (BookingRefNo.isEmpty() == false) {
                        } else {
                            continue;

                        }

                    }
                    test.log(Status.PASS, "Initiate Inspection Cleared Submitted Successfully");
                    test.log(Status.PASS, "Booking Reference Number : " + BookingRefNo);
                    reports.flush();
                    driver.quit();
                    Thread.sleep(3000);

                } else if (Result.equals("Fail")) {
                    test.log(Status.FAIL, "Initiate Inspection Cleared Failed");
                    reports.flush();
                    driver.quit();
                    continue;
                }
                //-------------------------------------------------Booking Planning----------------------------------------------------------
                if (map_Inspection_Siebel.get("Inspection_Centre").equals("Hatta") != true) {
                    for (int k = 0; k <= 20; k++) {
                        Planner_User = DB_Connections.Siebel_Planning_Queue_Status(BookingRefNo, Environment);
                        System.out.println(Planner_User);
                        if (Planner_User == null) {
                            Thread.sleep(10000);
                        } else {
                            break;
                        }

                    }
                    if (Planner_User.equals(map_Environment_Details.get("Planner_UserID"))) {
                        if (map_Environment_Details.get("Browser").equals("IE")) {
                            driver = getie.IEBrowser(driver);

                        } else if (map_Environment_Details.get("Browser").equals("CHROME")) {
                        }
                        // -----------------------------Enter URL------------------------------------------------------------------------------------
                        driver.get(map_Environment_Details.get("SiebelBase_URL"));
                        //---------------------------Login to Application-----------------------------------------------------------------------------
                        Result = Siebel_login.Siebel_Login_Function(driver, test, reports, Environment, Test_Data_Path,
                                "Planning");
                        if (Result.equals("Pass")) {
                            test.log(Status.PASS, "Siebel logged in successfully with Planner User : "
                                    + map_Environment_Details.get("Planner_UserID"));
                            reports.flush();
                        } else {
                            test.log(Status.FAIL, "Siebel logged in Failed");
                            reports.flush();
                            driver.quit();
                            continue;
                        }
                        if (Planner_User.equals(map_Environment_Details.get("Planner_UserID"))) {
                            Result = INS_Planning.Planner(driver, test, reports, i, Test_Data_Path, "Inspection_Siebel",
                                    Environment, BookingRefNo);
                        } else {
                            test.log(Status.FAIL, "Request is not yet assigned to : " + map_Environment_Details.get("Planner_UserID"));
                            reports.flush();
                            driver.quit();
                            continue;
                        }
                        if (Result.equals("Pass")) {
                            test.log(Status.PASS, "Inspection Planning is Submitted Successfully");
                            reports.flush();
                            driver.quit();

                        } else {
                            test.log(Status.FAIL, "Inspection Planning is not submitted Successfully");
                            reports.flush();
                            driver.quit();
                            continue;
                        }
                    } else {
                        test.log(Status.FAIL, "Inspection Planning is not Assigned to " + map_Environment_Details.get("Planner_UserID"));
                        reports.flush();
                        driver.quit();
                        continue;
                    }

                }
                Thread.sleep(5000);
                //-----------------------------Initiate Browser---------------------------------------------------------------------------------
                if (map_Environment_Details.get("Browser").equals("IE")) {
                    driver = getie.IEBrowser(driver);

                } else if (map_Environment_Details.get("Browser").equals("CHROME")) {
                }
                //-----------------------------Enter URL-----------------------------------------------------------------------------------------
                driver.get(map_Environment_Details.get("SiebeleService_URL"));
                //---------------------------Login to Application--------------------------------------------------------------------------------
                if (map_Inspection_Siebel.get("Inspection_Centre").equals("Hatta")) {
                    Result = EService_Login.EService_Login_Function(driver, test, reports, Environment, Test_Data_Path,
                            "Hatta");

                } else {
                    Result = EService_Login.EService_Login_Function(driver, test, reports, Environment, Test_Data_Path,
                            "Release");
                }
                if (Result.equals("Pass")) {
                    test.log(Status.PASS, "Siebel logged in successfully with Inspector User : "
                            + map_Environment_Details.get("Inspector_UserID"));
                    reports.flush();
                } else {
                    test.log(Status.FAIL, "Siebel logged in Failed");
                    reports.flush();
                    driver.quit();
                    continue;

                }
                if (map_Inspection_Siebel.get("Sieze_Flag").equals("OFF")) {
                    Result = INS_Release.Release(driver, test,
                            reports, i, Test_Data_Path, "Inspection_Siebel", Environment, BookingRefNo);
                    if (Result.equals("Pass")) {
                        test.log(Status.PASS, "Release Inspection is Submitted Successfully");
                        reports.flush();
                    } else {
                        test.log(Status.FAIL, "Release Inspection is not submitted Successfully");
                        reports.flush();
                        driver.quit();
                        continue;
                    }


                } else if (map_Inspection_Siebel.get("Sieze_Flag").equals("ON")) {
                    Result = INS_Seize.Seizure(driver, test, reports, i,
                            Test_Data_Path, "Inspection_Siebel", Environment, BookingRefNo);
                    if (Result.equals("Pass")) {
                        test.log(Status.PASS, "Sieze Inspection is Submitted and Approved Successfully");
                        reports.flush();
                        driver.quit();

                    } else {
                        test.log(Status.FAIL, "Sieze Inspection is not submitted Successfully");
                        reports.flush();
                        driver.quit();
                        continue;
                    }

                } else {
                    test.log(Status.FAIL, "Declaration not reached Read store");
                    reports.flush();
                    continue;

                }
                for (int l = 1; l <= 10; l++) {
                    Booking_Request_Status = DB_Connections.Booking_Request_Status(BookingRefNo, Environment);
                    Booking_Request_Sub_Status = DB_Connections.Booking_Request_Sub_Status(BookingRefNo, Environment);
                    System.out.println("Booking_Request_Status" + Booking_Request_Status);
                    System.out.println("Booking_Request_Status" + Booking_Request_Sub_Status);
                    if (Booking_Request_Status.equals("11") && Booking_Request_Sub_Status.equals("12")) {
                        test.log(Status.PASS, "Booking_Request_Status: " + Booking_Request_Status);
                        test.log(Status.PASS, "Booking_Request_Sub_Status: " + Booking_Request_Sub_Status);
                        reports.flush();
                        driver.quit();
                        break;
                    } else if (Booking_Request_Status.equals("11") && Booking_Request_Sub_Status.equals("15")) {
                        test.log(Status.PASS, "Booking_Request_Status: " + Booking_Request_Status);
                        test.log(Status.PASS, "Booking_Request_Sub_Status: " + Booking_Request_Sub_Status);
                        reports.flush();
                        driver.quit();
                        break;
                    } else {
                        Thread.sleep(10000);
                    }
                }


            } else {
                continue;
            }





            /*
             * test.log(Status.PASS, "Booking Request Status is : " +
             * Booking_Request_Status); test.log(Status.PASS, "Test Pass"); reports.flush();
             * driver.quit();
             */
        }

    }

}
