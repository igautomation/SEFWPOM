package pom_dubaitrade;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utilities.Elements;
import utilities.Reports;
import utilities.ScreenShot;

public class DubaiTrade_Login {
    public WebDriver driver;
    public ExtentTest test;
    public ExtentReports reports;
    public Reports report_Generator;
    public ScreenShot Take_Screenshot;
    public String Result;
    public Elements Element;
    public utilities.ReadData_Environment ReadData_Environment;
    public HashMap<String, String> map;

    public String DT_login(WebDriver driver, ExtentTest test, ExtentReports reports, String Environment, String Test_Data_Path) throws IOException, InterruptedException {

        this.driver = driver;

        this.test = test;

        this.reports = reports;

        Take_Screenshot = new ScreenShot();

        Element = new Elements();

        ReadData_Environment = new utilities.ReadData_Environment();

        map = ReadData_Environment.HashMap_Data_Environment_Details(Environment, Test_Data_Path);

        try {
            Result = Element.TextBox_Value(driver, test, reports, "//*[@id='username']", "Login ID", map.get("DubaiTrade_UserID"));
            // Password Filed
            Result = Element.TextBox_Value(driver, test, reports, "//*[@id='password']", "Password", map.get("DubaiTrade_Password"));
            //Login Button
            Result = Element.Click_Event(driver, test, reports, "//button[normalize-space()='Sign In']", "Login Button");


            if (driver.findElements(By.xpath("//button[contains(text(), 'End tour')]")).size() != 0) {

                driver.findElement(By.xpath("//button[contains(text(), 'End tour')]")).click();
            }

            if (driver.findElements(By.xpath("//*[@id='home-top-icon']")).size() != 0) {
                test.log(Status.PASS, "Dubai Trade Logged in successfully");
                reports.flush();
                Result = "Pass";

            } else {
                Result = "Fail";
            }


        } catch (WebDriverException e) {
            test.log(Status.FAIL, "Dubai Trade Login Failed");
            String Screen = Take_Screenshot.take_screenshot(driver);
            test.addScreenCaptureFromPath(Screen);
            reports.flush();
        }

        return Result;
    }

}
	
