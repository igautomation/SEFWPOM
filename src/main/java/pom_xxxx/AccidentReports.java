package pom_xxxx;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.codec.http.multipart.FileUpload;
import net.bytebuddy.asm.Advice;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class AccidentReports {

    public static void selectDate(WebElement calendar, String year, String monthName, String day, WebDriver driver) throws ParseException {
        //Clicking on calendar to open calendar widget
        calendar.click();

        // Retrieving current year value
        String currentYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();

        // Click on Next arrow till we get desired year
        if (!currentYear.equals(year)) {
            do {
                Select dropdown = new Select(driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")));
                dropdown.selectByVisibleText(year);
            } while (!driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText().equals(year));
        }

        // Select desired month after selecting desired year
        String currentMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
        if (!currentMonth.equalsIgnoreCase(monthName)) {
            do {
                driver.findElement(By.xpath("//a[@title='Prev']")).click();
            } while (!driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText().trim().equalsIgnoreCase(monthName));

        }
        //Select desired date after selecting desired month
        String currentDay = driver.findElement(By.xpath("//a[normalize-space()='" + day + "']")).getText();
        if (!currentDay.equalsIgnoreCase(day)) {
            do {
                driver.findElement(By.xpath("//a[normalize-space()='" + day + "']")).click();
            } while (!driver.findElement(By.xpath("//a[normalize-space()='\"+day+\"']")).getText().trim().equalsIgnoreCase(day));
        }

    }


    public static void main(String[] args) throws ParseException, InterruptedException {


        try {

            System.setProperty("webdriver.chrome.driver", "C:\\Automation\\DCSELENIUM\\Automation\\Projects\\Project_XXXX\\src\\main\\resources\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            //System.setProperty("webdriver.edge.driver", "C:\\Automation\\DCSELENIUM\\Automation\\Projects\\Project_XXXX\\src\\main\\resources\\msedgedriver.exe");
            //WebDriver driver = new EdgeDriver();
            //System.setProperty("webdriver.ie.driver", "C:\\Automation\\DCSELENIUM\\Automation\\Projects\\Project_XXXX\\src\\main\\resources\\IEDriverServer.exe");
            //WebDriver driver = new InternetExplorerDriver();
            //WebDriverManager.chromedriver().setup();
            //WebDriverManager.edgedriver().setup();
            //WebDriver driver = new EdgeDriver();
            //WebDriverManager.firefoxdriver().setup();
            //WebDriver driver = new FirefoxDriver();
            //WebDriverManager.chromedriver().setup();
            //WebDriver driver = new ChromeDriver();

            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(300));
            //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(-1));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            //Declare and initialise a fluent wait
            FluentWait wait = new FluentWait(driver);
            //Specify the timeout of the wait
            wait.withTimeout(Duration.ofSeconds(90));
            //Specify polling time
            wait.pollingEvery(Duration.ofSeconds(5));

            By userName = By.xpath("//input[@id='username']");
            By passWord = By.xpath("//input[@type='password']");
            By captcha = By.xpath("//input[@id='captchaVal']");
            By loginButton = By.xpath("//button[@class=\"primaryButton loginBtn\"]");
            By dashboardPage = By.xpath("//div[contains(text(),'التحليلات والمؤشرات')]");
            By seriousAccidentReport = By.xpath("//div[contains(text(),'الحوادث الجسيمة')]");
            By addAccidentReport = By.xpath("//button[@class='primaryButton addNewAccidentBtn']");
            By openMap = By.xpath("//*[contains(text(),' الانتقال الى الخرائط')]");
            By findAddress = By.xpath("//*[@id=\"viewDiv\"]/div[1]/div[3]/div[1]/div[2]/div/div/div[1]/form/input");
            //By findAddress = By.xpath("//div[@class='esri-search--show-suggestions esri-search__container']//input[@placeholder='Find address or place']");
            By searchButton = By.xpath("//div[@class='esri-search--show-suggestions esri-search__container']//button[@title='Search']");
            //By firstAddressListSelection = By.xpath("//li[@role='menuitem']//strong");
            By firstAddressListSelection = By.xpath("(//li[@role='menuitem'])[1]");
            By locationPoint = By.xpath("//div[@class='esri-popup__pointer' and @role='presentation']//div[@class=\"esri-popup__pointer-direction esri-popup--shadow\"]");
            By dialogOKButton = By.xpath("//button[@class='btn btn-outline-dark']");
            By loadSpinner = By.xpath("//img[@class='spinnerImg']");
            By uploadImage = By.xpath("//input[@name='filebrowser']");
            //By dateMonth = By.xpath("//*[@class=\"ui-datepicker-month ng-tns-c11-2 ng-star-inserted\"]");
            //By dateYear = By.xpath("//select[contains(@class,'year') and .//text()=\"2021\"]");


            for (int i = 0; i < 3; i++) {
                driver.get("http://192.168.100.16:8180/accident-reports/#/login");
                new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(loginButton));
                if (driver.findElements(By.xpath("//button[@class=\"primaryButton loginBtn\"]")).size() != 0) {
                    wait.until(ExpectedConditions.elementToBeClickable(loginButton));
                    break;
                }
            }

            //JavascriptExecutor js = (JavascriptExecutor) driver;

            //driver.findElement(By.xpath("//input[@id='datepicker']")).click();
            /*private By password = By.id("input-password");
            private By loginBtn = By.xpath("//input[@value='Login']");
            private By forgotPwdLink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
            private By header = By.cssSelector("div#logo h1 a");
            private By registerLink = By.linkText("Register");*/

            driver.findElement(userName).sendKeys("9999999999");
            driver.findElement(passWord).sendKeys("welcome2");
            driver.findElement(captcha).sendKeys("123456");
            driver.findElement(loginButton).click();
            Thread.sleep(3000);
            driver.findElement(seriousAccidentReport).click();

            if (driver.findElements(loadSpinner).size() == 0) {
                driver.findElement(addAccidentReport).click();
            } else {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(loadSpinner));
                driver.findElement(addAccidentReport).click();
            }

            //driver.findElement(By.xpath("//img[@src='assets/images/calendar.png']")).click();
            //String currentYear = driver.findElement(dateYear).getText();
            //System.out.println(currentYear);


            By dateYear = By.xpath("//select[contains(@class,'year') and .//text()=\"2021\"]");
            // Create object of the Select class
            Select se = new Select(driver.findElement(dateYear));
            // Select the option by value
            se.selectByValue("2020");



            if (driver.findElements(loadSpinner).size() == 0) {
                driver.findElement(openMap).click();
            } else {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(loadSpinner));
                driver.findElement(openMap).click();
            }


            wait.until(ExpectedConditions.elementToBeClickable(findAddress));
            driver.findElement(findAddress).sendKeys("Al Olaya, Riyadh 12213, Saudi Arabia");
            wait.until(ExpectedConditions.elementToBeClickable(firstAddressListSelection));
            driver.findElement(firstAddressListSelection).click();
            Thread.sleep(5000);

            //By canvas = By.xpath("//*[@id=\"viewDiv\"]/div[1]/div[1]/canvas");
            //new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(canvas));
            //WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(canvas));
            //Point location = element.getLocation();
            //int x = location.getX();
            //int y = location.getY();
            //System.out.println("Coordinates of an element is : " + x + " and " + y);

            WebElement element1 = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(locationPoint));
            Actions actions = new Actions(driver);
            new Actions(driver).moveToElement(element1, 0, 0).moveByOffset(100, 50).click().build().perform();

            Thread.sleep(2000);
            driver.findElement(dialogOKButton).click();


            WebElement addFile = driver.findElement(By.xpath("//input[@name='filebrowser']"));
            addFile.sendKeys("C:\\Automation\\2021-11-03_12-05-05.jpg");
            //driver.findElement(By.xpath(".//span[text()='Start upload']")).click();
            //uploadElement.sendKeys("File_path");

            Thread.sleep(2000);
            if (driver.findElement(By.xpath("//div[@class='uploadedFileDiv ng-star-inserted']")).isDisplayed()) {
                System.out.println("Image Uploaded");
            } else {
                System.out.println("Image NOT Uploaded");
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


}
