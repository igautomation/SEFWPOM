package pom_siebel_base;

import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
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

public class Inspection_Planning {

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

    public WebDriverWait waitMethod(WebDriver driver) {
        this.driver = driver;
        // return wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    public void waitForPageLoadComplete(WebDriver driver, int specifiedTimeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(driver1 -> String.valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }

    public String Planner(WebDriver driver, ExtentTest test, ExtentReports reports, int j, String Test_Data_Path,
                          String Sheet, String Environment, String Booking_Ref_No) throws Exception {
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
        map_Environment_Details = ReadData_Environment_Details.HashMap_Data_Environment_Details(Environment,
                Test_Data_Path);
        if (Sheet.equals("Inspection_DubaiTrade")) {
            // Create Local Hash Map for data sheet
            map_Inspection = Read_Exceldata_Hashmap.HashMap_Data_Inspection_DubaiTrade(j, Test_Data_Path, Sheet);
        } else if (Sheet.equals("Inspection_Siebel")) {
            // Create Local Hash Map for data sheet
            map_Inspection = ReadData_Siebel_Hasmap.HashMap_Data_Inspection_Siebel(j, Test_Data_Path, Sheet);
        }
        List<String> numbers = Arrays.asList(map_Inspection.get("Declaration_No").split(","));
        int Dec_Count = numbers.size();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // get date format MM/DD/YYYY HH:MM A
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY hh:mm a");
        LocalDateTime now = LocalDateTime.now();
        // Start time
        String Start_Time = dtf.format(now.plusHours(2));
        // End Time
        String End_Time = dtf.format(now.plusHours(2).plusMinutes(2));
        System.out.println(Start_Time + "  -  " + End_Time);
        try {
            // Booking Request - Planner Tab
            //// *[@id='ui-id-103']
            Result = Element.Click_Event(driver, test, reports,
                    "/html/body/div[1]/div/div[4]/div/div/div[1]/div[1]/ul/li[4]/a", "Booking Request - Planner Tab");
            // Booking Request Search Button
            /// html/body/div[1]/div/div[6]/div/div[7]/div/div[1]/div/div[1]/div/form/span/div/div[1]/div[2]/button[1]
            // *[@id='s_1_1_14_0_Ctrl']/span
            Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_14_0_Ctrl']",
                    "Booking Request Search Button");
            if (driver.findElements(By.xpath("//*[@id=\"1_s_1_l_Serial_Number\"]")).size() != 0) {
                driver.findElement(By.xpath("//*[@id=\"1_s_1_l_Serial_Number\"]")).click();
            }
            // Booking ref number text box
            Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_Serial_Number']",
                    "Enter Booking request No", Booking_Ref_No);
            // Go Button
            Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_11_0_Ctrl']", "Go Button");
            for (int k = 0; k <= 5; k++) {
                if (driver.findElements(By.xpath("//*[@id='1_s_1_l_Serial_Number']")).size() == 0) {
                    // Booking Request Search Button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_14_0_Ctrl']",
                            "Booking Request Search Button");
                    if (driver.findElements(By.xpath("//*[@id=\"1_s_1_l_Serial_Number\"]")).size() != 0) {
                        driver.findElement(By.xpath("//*[@id=\"1_s_1_l_Serial_Number\"]")).click();
                    }
                    // Booking ref number text box
                    Result = Element.TextBox_Value(driver, test, reports, "//*[@id='1_Serial_Number']",
                            "Enter Booking request No", Booking_Ref_No);
                    // Go Button
                    Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_11_0_Ctrl']", "Go Button");
                    continue;
                }
                // Thread.sleep(1000);
            }
            // Accept button
            Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_7_0_Ctrl']", "Accept button");
            // Thread.sleep(10000);
            if (Environment.equals("UAT1")) {
                // wait.until(ExpectedConditions.alertIsPresent());
                if (ExpectedConditions.alertIsPresent() != null) {
                    driver.switchTo().alert().accept();
                }
            }
            // Thread.sleep(10000);
            // Drill DOwn Booking reference Number
            Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_1_l_Serial_Number']",
                    "Booking reference Number Link");
            // Thread.sleep(5000);
            // Multiple Declaration Flow
            if (map_Inspection.get("Multiple_Declaration_Flag").equalsIgnoreCase("ON")) {
                // Groups Tab
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Groups')]", "Groups Tab");
                // Thread.sleep(2000);
                // DC INS Serial Number
                // *[@id="1_s_2_l_DC_INS_Serial_Number"]/a
                Result = Element.Click_Event(driver, test, reports,
                        "/html/body/div[1]/div/div[6]/div/div[7]/div/div[1]/div/div[3]/div[2]/div/form/span/div/div[3]/div/div/div[3]/div[3]/div/table/tbody/tr[2]/td[2]/a",
                        "Serial Number DrillDown");
                Thread.sleep(5000);
                // shift text box select
                if (driver.findElements(By.xpath("//*[@id='1_s_2_l_DC_INS_Shift']")).size() != 0) {
                    WebElement ele = driver.findElement(By.xpath("//*[@id='1_s_2_l_DC_INS_Shift']"));
                    executor.executeScript("arguments[0].scrollIntoView(true);",
                            driver.findElement(By.xpath("//*[@id='1_s_2_l_DC_INS_Shift']")));
                    executor.executeScript("arguments[0].click();", ele);
                }
                // Result = Element.Click_Event(driver, test, reports,
                // "//*[@id='1_s_2_l_DC_INS_Shift']", "shift text box select");
                // Shift text box
                Element.TextBox_Value(driver, test, reports, "//*[@id='1_DC_INS_Shift']", "Inspection Shift Selection", "Shift 1");
                Element.Click_Event(driver, test, reports, "//*[@id='1_s_2_l_DC_INS_Group_Name']",
                        "Select Group");
                Thread.sleep(5000);
                // Start time
                Element.TextBox_Value(driver, test, reports, "//*[@id='a_4']/div/table/tbody/tr[3]/td[3]/div/input",
                        "Calander details - Start Time", Start_Time);
                // Start time
                Element.TextBox_Value(driver, test, reports, "//*[@id='a_4']/div/table/tbody/tr[3]/td[3]/div/input",
                        "Calander details - Start Time", Start_Time);
                // End Time
                Element.TextBox_Value(driver, test, reports, "//*[@id='a_4']/div/table/tbody/tr[3]/td[5]/div/input",
                        "Calander details - End Time", End_Time);
                // Get Inspector button
                Result = Element.Click_Event(driver, test, reports,
                        "//button[@title='Get Inspectors']",
                        "Get Inspectors button");
                Thread.sleep(15000);
                // Validate button
                Result = Element.Click_Event(driver, test, reports,
                        "//button[@title='Validate']",
                        "Validate button");
                // Declarations Tab
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Declarations')]",
                        "Declarations Tab");
                // Thread.sleep(3000);
                for (int count = 1; count <= Dec_Count; count++) {
                    // Dec ID link
                    Result = Element.Click_Event(driver, test, reports,
                            "//*[@id=" + count + "]/td/a[@name='DC INS Declaration Id']", "Dec ID link");
                    // Document Tab
                    Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Documents')]",
                            "Document Tab");
                    // Add Document Button
                    Result = Element.Tabs_select(driver, test, reports,
                            "//button[@aria-label='Documents:Add Documents']", "Add Document Button");
                    // Document type
                    Result = Element.Tabs_select(driver, test, reports,
                            "//td[contains(text(), 'Org Structure/Profile Copy')]", "Document type");
                    // ok Button
                    Result = Element.Click_Event(driver, test, reports, "//button[@aria-label='Document Type:Ok']",
                            "Ok button");
                    // Go To Booking Request
                    Result = Element.Tabs_select(driver, test, reports,
                            "//button[@aria-label='Declaration:Go To Booking Request']", "Go To Booking Request");
                }
                // Confirm Button
                Result = Element.Click_Event(driver, test, reports,
                        "//button[@aria-label='<b>Booking Request Details</b>:Confirm']", "Confirm button");
                Thread.sleep(3000);
                driver.close();

            } else {
                /**
                 * Validate isEnabled and click
                 */
                // Containers/Marks & Numbers
                Result = Element.Tabs_select(driver, test, reports,
                        "//a[contains(text(), 'Containers/Marks & Numbers')]", "Containers/Marks & Numbers Tab");

                /*Result = Element.Click_Event(driver, test, reports, "//td[@id='1_s_3_l_DC_INS_Select_Flag']",
                        "Group line item select");*/

                //Instantiating Actions class
                Actions act = new Actions(driver);
                //span[@class='siebui-icon-white_gif']
                WebElement element = driver.findElement(By.xpath("//td[@id='1_s_3_l_DC_INS_Select_Flag']//img"));
                //Double Click the button
                act.doubleClick(element).perform();

                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(20))
                        .pollingEvery(Duration.ofSeconds(5))
                        .ignoring(NoSuchElementException.class);

                Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver arg0) {
                        WebElement element = arg0.findElement(By.xpath("//input[@id='1_DC_INS_Select_Flag']"));
                        String eletype = element.getAttribute("type");
                        System.out.println("Group Checkbox isChecked" + eletype);
                        if (eletype.equals("checkbox")) {
                            element.click();
                            return true;
                        }
                        return false;
                    }
                };
                wait.until(function);


  /*          WebElement checkBoxEnabled = driver.findElement(By.xpath("//input[@id='1_DC_INS_Select_Flag']"));
            boolean isEnabled = checkBoxEnabled.isDisplayed();
            // performing click operation if element is enabled
            if (isEnabled == true) {
                checkBoxEnabled.click();
            }*/
                // groups line item select
                Element.Click_Event(driver, test, reports, "//td[@id='1_s_3_l_DC_INS_Grouped']",
                        "Shift focus Group line item");
                // Group Button
                Result = Element.Click_Event(driver, test, reports, "//span[normalize-space()='Group']", "Group Button");
                // Thread.sleep(5000);
                // Groups Tab
                // *[@id="ui-id-162"]s
                // a[contains(text(), 'Groups')]
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Groups')]", "Groups Tab");
                Thread.sleep(5000);
                // Drill DOwn Booking reference Number under groups tab
                Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_2_l_DC_INS_Serial_Number']/a",
                        "Groups tab - Booking reference Number Link");
                Thread.sleep(5000);
                js.executeScript("arguments[0].scrollIntoView();",
                        driver.findElement(By.xpath("//*[@id='1_s_2_l_DC_INS_Shift']")));
                // Thread.sleep(2000);
                // shift text box select
                if (driver.findElements(By.xpath("//*[@id='1_s_2_l_DC_INS_Shift']")).size() != 0) {
                    WebElement ele = driver.findElement(By.xpath("//*[@id='1_s_2_l_DC_INS_Shift']"));
                    executor.executeScript("arguments[0].click();", ele);
                }
                // Result = Element.Click_Event(driver, test, reports,
                // "//*[@id='1_s_2_l_DC_INS_Shift']", "shift text box select");
                // Shift text box
                Element.TextBox_Value(driver, test, reports, "//*[@id='1_DC_INS_Shift']", "Shift text box", "Shift 1");
                // Thread.sleep(5000);
                // Start time
                Element.TextBox_Value(driver, test, reports, "//*[@id='a_4']/div/table/tbody/tr[3]/td[3]/div/input",
                        "Calander details - Start Time", Start_Time);
                // Start time
                Element.TextBox_Value(driver, test, reports, "//*[@id='a_4']/div/table/tbody/tr[3]/td[3]/div/input",
                        "Calander details - Start Time", Start_Time);
                // End Time
                Element.TextBox_Value(driver, test, reports, "//*[@id='a_4']/div/table/tbody/tr[3]/td[5]/div/input",
                        "Calander details - End Time", End_Time);
                // Get Inspector button
                /*Result = Element.Click_Event(driver, test, reports,
                        "/html/body/div[1]/div/div[6]/div/div[7]/div/div[1]/div/div[3]/div[2]/div/form/div/span/div[2]/button",
                        "Get Inspectors button");*/
                Result = Element.Click_Event(driver, test, reports,
                        "//button[@title='Get Inspectors']",
                        "Get Inspectors button");
                Thread.sleep(15000);
                // Validate button
                /*Result = Element.Click_Event(driver, test, reports,
                        "/html/body/div[1]/div/div[6]/div/div[7]/div/div[1]/div/div[3]/div[2]/div/form/div/span/div[2]/div[1]/button",
                        "Validate button");*/
/*                Result = Element.Click_Event(driver, test, reports,
                        "//button[@id='s_4_1_24_0_Ctrl']",
                        "Validate button");*/
                Result = Element.Click_Event(driver, test, reports,
                        "//button[@title='Validate']",
                        "Validate button");
                // Documents Tab
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Documents')]",
                        "Documents Tab");
                // Thread.sleep(2000);
                // Add Document Button
                Result = Element.Click_Event(driver, test, reports, "//span[contains(text(), 'Add Documents')]",
                        "Add Document Button");
                // OK Button
                Result = Element.Click_Event(driver, test, reports, "//span[contains(text(), 'Ok')]", "OK Button");
                test.log(Status.PASS, "Document Added successfully");
                reports.flush();
                // Groups Tab
                Result = Element.Tabs_select(driver, test, reports, "//a[contains(text(), 'Groups')]", "Groups Tab");
                Thread.sleep(5000);
                // Containers/Marks & Numbers - Assigned to text box select
                Result = Element.Click_Event(driver, test, reports, "//*[@id='1_s_3_l_DC_INS_Assigned_To']",
                        "Assigned to text box select");
                // Assigned select ICON
                Result = Element.Click_Event(driver, test, reports, "//*[@id='s_3_2_15_0_icon']",
                        "Assigned select ICON");
                // Thread.sleep(5000);
                // select Inspector
                Result = Element.Click_Event(driver, test, reports,
                        "//table[@summary='Add Employees']//td[contains(text(), '"
                                + map_Environment_Details.get("Inspector_UserID") + "')]",
                        "Select Inspector");
                // Employee Add Button
               /* Result = Element.Click_Event(driver, test, reports, "//*[@id='s_5_1_28_0_Ctrl']",
                        "Employee Add Button");*/
                Result = Element.Click_Event(driver, test, reports, "//span[@class='siebui-popup-button']//button[@id='s_5_1_28_0_Ctrl']",
                        "Employee Add Button");
                // OK Button
                Result = Element.Click_Event(driver, test, reports, "//span[@class='siebui-popup-button']//button[@id='s_5_1_31_0_Ctrl']", "OK Button");
                test.log(Status.PASS,
                        "Request assigned to Inspector : " + map_Environment_Details.get("Inspector_UserID"));
                reports.flush();
                // Confirm Button
                Result = Element.Click_Event(driver, test, reports, "//*[@id='s_1_1_53_0_Ctrl']", "Confirm Button");
                // Thread.sleep(3000);
                driver.close();
            }
        } catch (WebDriverException e) {
            Result = "Fail";
            test.log(Status.FAIL, "Inspection Planning Process : Failed");
            reports.flush();
        }
        return Result;
    }

}
