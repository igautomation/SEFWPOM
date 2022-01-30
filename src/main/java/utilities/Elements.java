package utilities;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Elements {

    WebDriver driver;
    public WebDriverWait wait;
    public WebElement Element;
    public ExtentTest test;
    public ExtentReports reports;
    public String Result;
    public String Get_Value;
    public TakeScreenShot Take_Screenshot;
    public Page_Load_utilitie PageLoad_Wait;


    public WebDriverWait waitMethod(WebDriver driver) {
        this.driver = driver;
        PageLoad_Wait = new Page_Load_utilitie();
        PageLoad_Wait.waitForPageLoadComplete(driver, 30);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait;
    }
    //*************Text_Present reusable component*****************\\

    public String Text_Present(WebDriver driver, ExtentTest test, ExtentReports reports, String Locator, String refrence) throws IOException {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new TakeScreenShot();
        if (driver.findElements(By.xpath(Locator)).size() != 0) {
            try {
                //waitMethod(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Locator)));
                Get_Value = driver.findElement(By.xpath(Locator)).getText();
                Result = "Pass";

            } catch (WebDriverException e) {
                Result = "Fail";
                test.log(Status.FAIL, refrence + " Text not present in the Application");
                String Screen = Take_Screenshot.take_screenshot(driver);
                test.addScreenCaptureFromPath(Screen);
                reports.flush();
                driver.quit();

            }

        } else {
            Result = "Fail";
            test.log(Status.FAIL, refrence + " not present in DOM");
            String Screen = Take_Screenshot.take_screenshot(driver);
            test.addScreenCaptureFromPath(Screen);
            reports.flush();
            driver.quit();

        }
        return Get_Value;

    }

    //*******************Click reusable component*******************\\
    public String Click_Event(WebDriver driver, ExtentTest test, ExtentReports reports, String Locator, String refrence) throws InterruptedException, IOException {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new TakeScreenShot();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        PageLoad_Wait = new Page_Load_utilitie();
        //PageLoad_Wait.waitForPageLoadComplete(driver, 60);
        if (driver.findElements(By.xpath(Locator)).size() > 0) {
            try {
                waitMethod(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Locator)));
                waitMethod(driver).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(Locator))));
                js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(Locator)));
                driver.findElement(By.xpath(Locator)).click();
                Result = "Pass";
            } catch (WebDriverException e) {
                System.out.println(e);
                test.log(Status.FAIL, refrence + " is not Clickable");
                String Screen = Take_Screenshot.take_screenshot(driver);
                test.addScreenCaptureFromPath(Screen);
                reports.flush();
                driver.quit();
            }


        } else {
            Result = "Fail";
            System.out.println("" + refrence);
            test.log(Status.FAIL, refrence + " not present in DOM");
            String Screen = Take_Screenshot.take_screenshot(driver);
            test.addScreenCaptureFromPath(Screen);
            reports.flush();
            driver.quit();
        }
        return Result;

    }

    //*************Enter Value reusable component*****************\\
    public String TextBox_Value(WebDriver driver, ExtentTest test, ExtentReports reports, String Locator, String refrence, String Value) throws IOException {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new TakeScreenShot();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        PageLoad_Wait = new Page_Load_utilitie();
        if (driver.findElements(By.xpath(Locator)).size() != 0) {
            try {
                waitMethod(driver).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator)));
                waitMethod(driver).until(ExpectedConditions.elementToBeClickable(By.xpath(Locator)));
                js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(Locator)));
                driver.findElement(By.xpath(Locator)).clear();
                driver.findElement(By.xpath(Locator)).sendKeys(Value);
                Result = "Pass";
            } catch (WebDriverException e) {
                Result = "Fail";
                test.log(Status.FAIL, refrence + " is not visible in the application");
                String Screen = Take_Screenshot.take_screenshot(driver);
                test.addScreenCaptureFromPath(Screen);
                reports.flush();
                driver.quit();

            }


        } else {
            Result = "Fail";
            test.log(Status.FAIL, refrence + " not present in DOM");
            String Screen = Take_Screenshot.take_screenshot(driver);
            test.addScreenCaptureFromPath(Screen);
            reports.flush();
            driver.quit();
        }
        return Result;

    }

    //*************Drop Down Value reusable component*****************\\
    public String Dropdown_Value(WebDriver driver, ExtentTest test, ExtentReports reports, String Locator, String refrence, String Value) throws IOException {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new TakeScreenShot();
        PageLoad_Wait = new Page_Load_utilitie();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //PageLoad_Wait.waitForPageLoadComplete(driver, 60);
        if (driver.findElements(By.xpath(Locator)).size() != 0) {
            try {
                //PageLoad_Wait.waitForPageLoadComplete(driver, 60);
                waitMethod(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Locator)));
                js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(Locator)));
                Select dropdown = new Select(driver.findElement(By.xpath(Locator)));
                dropdown.selectByVisibleText(Value);
                Result = "Pass";

            } catch (WebDriverException e) {
                Result = "Fail";
                test.log(Status.FAIL, "Unable to select" + refrence + " dropdown value in the application");
                String Screen = Take_Screenshot.take_screenshot(driver);
                test.addScreenCaptureFromPath(Screen);
                reports.flush();
                driver.quit();

            }

        } else {
            Result = "Fail";
            test.log(Status.FAIL, refrence + " not present in DOM");
            String Screen = Take_Screenshot.take_screenshot(driver);
            test.addScreenCaptureFromPath(Screen);
            reports.flush();
            driver.quit();
        }
        return Result;

    }

    //*************Radio Button reusable component*****************\\
    public String Radio_Button(WebDriver driver, ExtentTest test, ExtentReports reports, String Locator, String refrence, String Value) throws IOException {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new TakeScreenShot();
        PageLoad_Wait = new Page_Load_utilitie();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //PageLoad_Wait.waitForPageLoadComplete(driver, 60);
        if (driver.findElements(By.xpath(Locator)).size() != 0) {
            try {
                waitMethod(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Locator)));
                js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(Locator)));
                driver.findElement(By.xpath(Locator)).click();
                Result = "Pass";
            } catch (WebDriverException e) {
                Result = "Fail";
                test.log(Status.FAIL, "Unable to Click " + refrence + " radio button in the application");
                String Screen = Take_Screenshot.take_screenshot(driver);
                test.addScreenCaptureFromPath(Screen);
                reports.flush();
                driver.quit();

            }

        } else {
            Result = "Fail";
            test.log(Status.FAIL, refrence + " not present in DOM");
            String Screen = Take_Screenshot.take_screenshot(driver);
            test.addScreenCaptureFromPath(Screen);
            reports.flush();
            driver.quit();
        }
        return Result;
    }

    //*************Check Box reusable component*****************\\
    public String Check_Box(WebDriver driver, ExtentTest test, ExtentReports reports, String Locator, String refrence, String Value) throws IOException {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new TakeScreenShot();
        PageLoad_Wait = new Page_Load_utilitie();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //PageLoad_Wait.waitForPageLoadComplete(driver, 60);
        if (driver.findElements(By.xpath(Locator)).size() != 0) {
            try {
                waitMethod(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Locator)));
                js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(Locator)));
                driver.findElement(By.xpath(Locator)).click();
                Result = "Pass";

            } catch (WebDriverException e) {
                Result = "Fail";
                test.log(Status.FAIL, "Unable to Click " + refrence + " Checkbox in the application");
                String Screen = Take_Screenshot.take_screenshot(driver);
                test.addScreenCaptureFromPath(Screen);
                reports.flush();
                driver.quit();

            }

        } else {
            Result = "Fail";
            test.log(Status.FAIL, refrence + " not present in DOM");
            String Screen = Take_Screenshot.take_screenshot(driver);
            test.addScreenCaptureFromPath(Screen);
            reports.flush();
            driver.quit();
        }
        return Result;

    }

    //*************Select Text Box before entering values reusable component***********public void TextBox_select(WebDriver driver,ExtentTest test , ExtentReports reports, String Locator , String Value ) throws IOException {
    public String TextBox_select(WebDriver driver, ExtentTest test, ExtentReports reports, String Locator, String refrence, String Value) throws IOException {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new TakeScreenShot();
        PageLoad_Wait = new Page_Load_utilitie();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //PageLoad_Wait.waitForPageLoadComplete(driver, 60);
        if (driver.findElements(By.xpath(Locator)).size() != 0) {
            try {
                waitMethod(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Locator)));
                js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(Locator)));
                driver.findElement(By.xpath(Locator)).click();
                Result = "Pass";

            } catch (WebDriverException e) {
                Result = "Fail";
                test.log(Status.FAIL, "Unable to select " + refrence + " textbox in the application");
                String Screen = Take_Screenshot.take_screenshot(driver);
                test.addScreenCaptureFromPath(Screen);
                reports.flush();
                driver.quit();

            }

        } else {
            Result = "Fail";
            test.log(Status.FAIL, refrence + " not present in DOM");
            String Screen = Take_Screenshot.take_screenshot(driver);
            test.addScreenCaptureFromPath(Screen);
            reports.flush();
            driver.quit();
        }
        return Result;

    }
    //*************Select Tabs reusable component*****************\\

    public String Tabs_select(WebDriver driver, ExtentTest test, ExtentReports reports, String Locator, String refrence) throws IOException, InterruptedException {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new TakeScreenShot();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //PageLoad_Wait = new Page_Load_utilitie();
        //PageLoad_Wait.waitForPageLoadComplete(driver, 60);
        if (driver.findElements(By.xpath(Locator)).size() != 0) {
            Thread.sleep(6000);
            try {
                waitMethod(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Locator)));
                waitMethod(driver).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(Locator))));
                js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(Locator)));
                driver.findElement(By.xpath(Locator)).click();
                Result = "Pass";

            } catch (WebDriverException e) {
                Result = "Fail";
                test.log(Status.FAIL, "Unable to select " + refrence + " tabs in the application");
                String Screen = Take_Screenshot.take_screenshot(driver);
                test.addScreenCaptureFromPath(Screen);
                reports.flush();
                driver.quit();

            }

        } else {
            Result = "Fail";
            test.log(Status.FAIL, refrence + " not present in DOM");
            String Screen = Take_Screenshot.take_screenshot(driver);
            test.addScreenCaptureFromPath(Screen);
            reports.flush();
            driver.quit();
        }
        return Result;

    }
    //*************Get_Text reusable component*****************\\

    public String Get_Text(WebDriver driver, ExtentTest test, ExtentReports reports, String Locator, String refrence) throws IOException {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new TakeScreenShot();
        //PageLoad_Wait = new Page_Load_utilitie();
        //PageLoad_Wait.waitForPageLoadComplete(driver, 60);
        if (driver.findElements(By.xpath(Locator)).size() != 0) {
            try {
                //waitMethod(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Locator)));
                Get_Value = driver.findElement(By.xpath(Locator)).getText();
                System.out.println(Get_Value);
            } catch (WebDriverException e) {
                Result = "Fail";
                System.out.println(e);
                test.log(Status.FAIL, refrence + " text is not present in the application");
                String Screen = Take_Screenshot.take_screenshot(driver);
                test.addScreenCaptureFromPath(Screen);
                reports.flush();
                driver.quit();

            }


        } else {
            Result = "Fail";
            test.log(Status.FAIL, refrence + " not present in DOM");
            String Screen = Take_Screenshot.take_screenshot(driver);
            test.addScreenCaptureFromPath(Screen);
            reports.flush();
            driver.quit();
        }
        return Get_Value;

    }
    //*************Switch_Frame reusable component*****************\\

    public String Switch_Frame(WebDriver driver, ExtentTest test, ExtentReports reports, String Locator) throws IOException {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        Take_Screenshot = new TakeScreenShot();
        PageLoad_Wait = new Page_Load_utilitie();
        if (driver.findElements(By.xpath(Locator)).size() != 0) {
            try {
                waitMethod(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Locator)));
                driver.switchTo().frame(driver.findElement(By.xpath(Locator)));
                Result = "Pass";
            } catch (WebDriverException e) {
                Result = "Fail";
                test.log(Status.FAIL, "Frame is not present in the application");
                String Screen = Take_Screenshot.take_screenshot(driver);
                test.addScreenCaptureFromPath(Screen);
                reports.flush();
                driver.quit();

            }


        } else {
            Result = "Fail";
            test.log(Status.FAIL, "Frame Locator is not present in DOM");
            String Screen = Take_Screenshot.take_screenshot(driver);
            test.addScreenCaptureFromPath(Screen);
            reports.flush();
            driver.quit();
        }
        return Result;

    }

    //*************Find Element reusable component*****************\\
    public WebElement retrieveElement(WebDriver driver, ExtentTest test, ExtentReports reports, String Locator) {
        this.driver = driver;
        this.test = test;
        this.reports = reports;
        waitMethod(driver).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Locator)));
        return driver.findElement(By.xpath(Locator));
    }

}
