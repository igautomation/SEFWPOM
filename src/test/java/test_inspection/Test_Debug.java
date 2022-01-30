package test_inspection;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pom_dubaitrade.DubaiTrade_Login;
import pom_dubaitrade.Initiate_Inspection_Cleared;
import pom_siebel_base.Inspection_Planning;
import pom_siebel_base.Release_Inspection;
import pom_siebel_base.Seize_Inspection;
import pom_siebel_base.Siebel_Login;
import utilities.Chrome_Browser;
import utilities.DB_Connections;
import utilities.ExcelApiTest;
import utilities.IE_Browser;
import utilities.ReadData_DubaiTrade;
import utilities.Reports;
import utilities.TakeScreenShot;

public class Test_Debug {

	static WebDriver driver;
	WebElement element;
	public String Result;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports reports;
	public static ExtentTest test;
	public static ReadData_DubaiTrade Read_Exceldata_Hashmap;
	public static  utilities.ReadData_Environment ReadData_Environment;
	private static IE_Browser getie;
	private static Chrome_Browser getchrome;
	public static Reports report_Generator;
	public static TakeScreenShot Take_Screenshot;
	public static Siebel_Login Siebel_login;
	
	private DubaiTrade_Login var_dtlogin;
	private static Inspection_Planning INS_Planning;
	private static Release_Inspection INS_Release;
	private static Seize_Inspection INS_Seize; 
	private Initiate_Inspection_Cleared var_initiate_inspection_cleared;
	public static HashMap<String, String> map_Login_Details;
	public static HashMap<String, String> map_Inspection_DubaiTrade;
	public static DB_Connections Connect_DB;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String DecNo1 = "";
		String DecNo2 = "";
		
JavascriptExecutor js = (JavascriptExecutor)driver;
		
		  /*DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();		            
		  capabilities.setCapability("ignoreProtectedModeSettings", true);
		  capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		  capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);	  
		  capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		  capabilities.setCapability("requireWindowFocus", true);
		  capabilities.setCapability("allow-blocked-content", true);
		  capabilities.setCapability("allowBlockedContent", true);
		  capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);*/
			 
		System.setProperty("webdriver.ie.driver","D:\\Users\\muhammadghouse.imran\\Automation\\Projects\\Project_Inspection\\src\\main\\resources\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://eservicesuat.dubaitrade.ae/");
		
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("UFO");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("login345");
		driver.findElement(By.xpath("//*[@id='loginData']/div/div[1]/div[5]/div/button")).click();
		
		
		Thread.sleep(5000);
		
		//NewDubaiTrade UI
		driver.findElement(By.xpath("//*[@id='menu-button']")).click();
		
		driver.findElement(By.xpath("//ul[@class='top-level']/li[@class='top-head head closed']/span[@class='title']/span[contains(text(), 'Inspection Request')]")).click();
		
		driver.findElement(By.xpath("//span[contains(text(), 'Initiate Inspection – Cleared Declaration')]")).click();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='contentFrame']")));
		
		driver.findElement(By.xpath("//button[@aria-label='Cleared Declaration Details:New']")).click();
		
		driver.findElement(By.xpath("//input[@id='1_DC_INS_Declaration_Number']")).sendKeys(DecNo1);
		
		
		  
		
	}

}
