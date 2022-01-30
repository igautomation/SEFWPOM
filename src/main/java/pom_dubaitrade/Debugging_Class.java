package pom_dubaitrade;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class Debugging_Class {

    public static void main(String[] args) {

        // TODO Auto-generated method stub
        //WebDriverManager.chromedriver().setup();

        // set the driver path- You can also use WebDriverManager for drivers
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\DCSELENIUM\\Automation\\Projects\\Project_XXXX\\src\\main\\resources\\chromedriver.exe");

        // Create object of ChromeOptions Class
        ChromeOptions opt = new ChromeOptions();

        // pass the debuggerAddress and pass the port along with host. Since I am running test on local so using localhost
        opt.setExperimentalOption("debuggerAddress", "localhost:1559");

        // Chrome auto-open DevTools window
        opt.addArguments("--auto-open-devtools-for-tabs");

        // pass ChromeOptions object to ChromeDriver constructor
        WebDriver driver = new ChromeDriver(opt);

        // now you can use now existing Browser
        driver.get("http://192.168.100.16:8180/accident-reports/#/login");
        String varTitle = driver.getTitle();
        //System.out.println("Get Title : " + varTitle);

        if (varTitle == "Facebook – log in or sign up") {
            System.out.println("Page title is : " + varTitle);
        }



        /* public static void main (String[]args) {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.online-calculator.com/full-screen-calculator/");

            WebElement canvas = new WebElement() {
                @Override
                public void click() {

                }

                @Override
                public void submit() {

                }

                @Override
                public void sendKeys(CharSequence... keysToSend) {

                }

                @Override
                public void clear() {

                }

                @Override
                public String getTagName() {
                    return null;
                }

                @Override
                public String getAttribute(String name) {
                    return null;
                }

                @Override
                public boolean isSelected() {
                    return false;
                }

                @Override
                public boolean isEnabled() {
                    return false;
                }

                @Override
                public String getText() {
                    return null;
                }

                @Override
                public List<WebElement> findElements(By by) {
                    return null;
                }

                @Override
                public WebElement findElement(By by) {
                    return null;
                }

                @Override
                public boolean isDisplayed() {
                    return false;
                }

                @Override
                public Point getLocation() {
                    return null;
                }

                @Override
                public Dimension getSize() {
                    return null;
                }

                @Override
                public Rectangle getRect() {
                    return null;
                }

                @Override
                public String getCssValue(String propertyName) {
                    return null;
                }

                @Override
                public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
                    return null;
                }
            }By.xpath("//*[@id=\"viewDiv\"]/div[1]/div[1]/canvas");

            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(canvas));

            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(canvas));

            Point location = element.getLocation();
            int x = location.getX();
            int y = location.getY();
            System.out.println("Coordinates of an element is : " + x + " and " + y);

            //clicking on 3
            new Actions(driver).moveToElement(canvas, 0, 0).moveByOffset(0, (255 / 6) * 3).click().build().perform();
            //clicking on the substract sign (-)
            new Actions(driver).moveToElement(canvas, 0, 0).moveByOffset((174 / 5) * 2, (255 / 6) * 3).click().build().perform();
            //clicking on 1
            new Actions(driver).moveToElement(canvas, 0, 0).moveByOffset(-(174 / 5) * 4, (255 / 6) * 3).click().build().perform();
            //clicking on equals to sign (=)
            new Actions(driver).moveToElement(canvas, 0, 0).moveByOffset((174 / 5) * 4, (255 / 6) * 4).click().build().perform();

        } */

    }

}
