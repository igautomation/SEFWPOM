package pom_dubaitrade;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HandlingMMTCalendar {

    public static void selectDate(WebElement calendar, String year, String monthName, String day, WebDriver driver) throws ParseException
    {
        //Clicking on calendar to open calendar widget
        calendar.click();

        // Retrieving current year value
        String currentYear= driver.findElement(By.xpath("//select[@class='ui-datepicker-year ng-tns-c11-0 ng-star-inserted']")).getText();

        // Click on Next arrow till we get desired year
        if(!currentYear.equals(year))
        {
            do{
                driver.findElement(By.xpath("(//span[text()='Next'])[1]")).click();
            }while(!driver.findElement(By.xpath("//div[@class='ui-datepicker-title']/span[@class='ui-datepicker-year']")).getText().equals(year));

        }

        // Select desired month after selecting desired year

        String currentMonth= driver.findElement(By.xpath("(//div[@class='ui-datepicker-title']/span[@class='ui-datepicker-month'])[1]")).getText();
        if(!currentMonth.equalsIgnoreCase(monthName))
        {
            do{
                driver.findElement(By.xpath("(//span[text()='Next'])[1]")).click();
            }while(!driver.findElement(By.xpath("(//div[@class='ui-datepicker-title']/span[@class='ui-datepicker-month'])[1]")).getText().trim().equalsIgnoreCase(monthName));

        }
        //get java month number for desired month

        int javaMonthInt= HandlingMMTCalendar.getMonthJavaInt(monthName);

        // Find dates of desired month only

        List<WebElement> allDateOfDesiredMonth= driver.findElements(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//table/tbody[1]//td[(@class=' ' or @class=' ui-datepicker-week-end ' ) and @data-month='"+javaMonthInt+"']"));
        for(WebElement d:allDateOfDesiredMonth )
        {
            if(d.getText().trim().equals(day))
            {
                d.click();
                break;
            }
        }

    }

    // Code to get java month number
    public static int getMonthJavaInt(String monthName) throws ParseException
    {

        Date date = new SimpleDateFormat("MMMM").parse(monthName);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static void main(String[] args) throws ParseException {

        //System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/");

        // Locating departure date calendar
        driver.findElement(By.xpath("//li[@class='makeFlex hrtlCenter font10 makeRelative lhUser userLoggedOut']")).click();
        WebElement departCal= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div[1]/div[3]"));
        HandlingMMTCalendar.selectDate(departCal, "2017", "September", "22", driver);

    }
}
