package test_inspection;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

public class BrowserDebugger {

	public static void main(String[] args) throws InterruptedException {

		
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "D:\\Users\\MuhammadGhouse.Imran\\Desktop\\Selenium\\Chrome\\chromedriver.exe"
		 * ); ChromeOptions options = new ChromeOptions();
		 * 
		 * // pass the debuggerAddress and pass the port along with host. Since I am
		 * //running test on local so using localhost
		 * options.setExperimentalOption("debuggerAddress","localhost:9988");
		 * 
		 * // pass ChromeOptions object to ChromeDriver constructor WebDriver driver=new
		 * ChromeDriver(options);
		 * 
		 * // now you can use now existing Browser
		 * //driver.get("http://eservicesuat.dubaitrade.ae");
		 * 
		 * InternetExplorerOptions options = new InternetExplorerOptions(); // Help with
		 * slow typing
		 */
		
		
		
		
/*		// options.ignoreZoomSettings();
		options.introduceFlakinessByIgnoringSecurityDomains();
		options.destructivelyEnsureCleanSession();

		System.setProperty("webdriver.ie.driver",
				"D:\\Users\\muhammadghouse.imran\\Automation\\Projects\\Project_Inspection\\src\\main\\resources\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		Thread.sleep(10000);
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String IP = driver.getCurrentUrl();
		String LocalHost = IP.substring(17, 22);
		System.out.println(LocalHost);*/

		
	}

	/*
	 * // set the driver path- You can also use WebDriverManager for drivers
	 * System.setProperty("webdriver.chrome.driver",
	 * "E:\\MukeshData\\chromedriver.exe");
	 * 
	 * // Create object of ChromeDriver class ChromeDriver driver=new
	 * ChromeDriver();
	 * 
	 * // getCapabilities will return all browser capabilities Capabilities
	 * cap=driver.getCapabilities();
	 * 
	 * // asMap method will return all capability in MAP Map<String, Object>
	 * myCap=cap.asMap();
	 * 
	 * // print the map data- System.out.println(myCap);
	 */

}
