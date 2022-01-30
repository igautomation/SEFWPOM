package utilities;

import java.io.IOException;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome_Browser {

	WebDriver driver;

	public WebDriver ChromeBrowser(){
		
	 //Close all running IEDriverServer.exe
	   try {
		  Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		  } catch (IOException e) {
		  e.printStackTrace();
		  }

		System.setProperty("webdriver.chrome.driver","D:\\Users\\muhammadghouse.imran\\Automation\\Projects\\Project_Inspection\\src\\main\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		//options.setExperimentalOption("useAutomationExtension", false);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		return driver;

	}
	
}
 