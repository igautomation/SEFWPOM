package utilities;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page_Load_utilitie {
	
	public WebDriver driver;
	
	public void waitForPageLoadComplete(WebDriver driver, int specifiedTimeout) {
	    Wait<WebDriver> wait = new WebDriverWait(driver, specifiedTimeout);
	    wait.until(driver1 -> String
	            .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
	            .equals("complete"));
	}

}
