package utilities;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;


public class IE_Browser {

    public WebDriver driver;

    String Result;

    public WebDriver IEBrowser(WebDriver driver) throws IOException, AWTException {
        //Close all running IEDriverServer.exe
        try {
            Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe*32");
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.driver = driver;
        /*
         * DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
         * capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
         * capabilities.setCapability(InternetExplorerDriver.
         * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
         * capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
         * capabilities.setCapability("requireWindowFocus", true);
         * capabilities.setCapability("allow-blocked-content", true);
         * capabilities.setCapability("allowBlockedContent", true);
         * capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
         *
         * true);
         */
        InternetExplorerOptions options = new InternetExplorerOptions();
        // Help with slow typing
        //options.ignoreZoomSettings();
        options.introduceFlakinessByIgnoringSecurityDomains();
        options.destructivelyEnsureCleanSession();
        // options.requireWindowFocus();
        System.setProperty("webdriver.ie.driver", "D:\\Users\\muhammadghouse.imran\\Automation\\Projects\\Project_Inspection\\src\\main\\resources\\IEDriverServer.exe");
        driver = new InternetExplorerDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }

}
