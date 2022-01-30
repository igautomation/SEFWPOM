package pom_siebel_base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.*;

import java.io.IOException;
import java.util.HashMap;

public class EService_Login {

    public WebDriver driver;
    public ExtentTest test;
    public ExtentReports reports;
    public Reports report_Generator;
    public ScreenShot Take_Screenshot;
    public String Result;
    public WebDriverWait wait;
    public Elements Element;
    public HashMap<String, String> map;
    public ReadData_Environment ReadData_Environment_Details;
    public ReadData_DubaiTrade Read_Inspection_Data;

    public String EService_Login_Function(WebDriver driver, ExtentTest test, ExtentReports reports, String Environment, String Test_Data_Path, String Scenario) throws InterruptedException, IOException {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new ScreenShot();
        Element = new Elements();
        Read_Inspection_Data = new ReadData_DubaiTrade();
        ReadData_Environment_Details = new ReadData_Environment();
        map = ReadData_Environment_Details.HashMap_Data_Environment_Details(Environment, Test_Data_Path);
        //Login User ID
        try {
            if (Environment.equals("SIT2") || Environment.equals("UAT2")) {
                if (map.get("Browser").equals("IE")) {
                    if (driver.findElements(By.id("overridelink")).size() != 0) {
                        driver.findElement(By.id("overridelink")).click();
                    }
                } else if (map.get("Browser").equals("Chrome")) {
                    if (driver.findElements(By.xpath("//*[@id='details-button']")).size() != 0) {
                        driver.findElement(By.xpath("//*[@id='details-button']")).click();
                        driver.findElement(By.xpath("//*[@id='proceed-link']")).click();
                    }
                }
            }

            //switch case - user types
            switch(Scenario) {
                case "Bureau":
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_1']", "Login ID", map.get("Bureau_UserID"));
                    // Password Filed
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_2']", "Password", map.get("Bureau_Password"));
                    //Login Button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_swepi_22']", "Login Button");
                    break;
                case "Planner":
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_1']", "Login ID", map.get("Planner_UserID"));
                    // Password Filed
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_2']", "Password", map.get("Planner_Password"));
                    //Login Button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_swepi_22']", "Login Button");
                    break;
                case "Inspector":
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_1']", "Login ID", map.get("Inspector_UserID"));
                    // Password Filed
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_2']", "Password", map.get("Inspector_Password"));
                    //Login Button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_swepi_22']", "Login Button");
                    break;
                case "Hatta":
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_1']", "Login ID", map.get("Inspector_UserID_Hatta"));
                    // Password Filed
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_2']", "Password", map.get("Inspector_Password_Hatta"));
                    //Login Button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_swepi_22']", "Login Button");
                    break;
                case "ED":
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_1']", "Login ID", map.get("ED_UserID"));
                    // Password Filed
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_2']", "Password", map.get("ED_Password"));
                    //Login Button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_swepi_22']", "Login Button");
                    break;
                case "VC_Entry":
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_1']", "Login ID", map.get("VC_Entry_Inspector_UserID"));
                    // Password Filed
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_2']", "Password", map.get("VC_Entry_Inspector_Password"));
                    //Login Button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_swepi_22']", "Login Button");
                    break;
                case "VC_Exit":
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_1']", "Login ID", map.get("VC_Exit_Inspector_UserID"));
                    // Password Filed
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='s_swepi_2']", "Password", map.get("VC_Exit_Inspector_Password"));
                    //Login Button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_swepi_22']", "Login Button");
                    break;
                default:
                    // code block
            }

            //Checking if Home Label is available after logging in---------------------------------------------------//
            if (driver.findElements(By.xpath("//a[normalize-space()='Assigned Requests']")).size() != 0) {
                Result = "Pass";
            } else {
                Result = "Fail";
            }


        } catch (WebDriverException e) {
            Result = "Fail";

            /*
             * test.log(Status.FAIL, "Siebel Login Failed"); String Screen =
             * Take_Screenshot.take_screenshot(driver);
             * test.addScreenCaptureFromPath(Screen); reports.flush();
             */
        }
        return Result;

    }

}




