package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.BeforeTest;

public class Reports {

		public static ExtentSparkReporter htmlReporter;
		public static ExtentReports reports;
		public ExtentTest test;
		public TakesScreenshot Take_Screenshot;
		public static String Run_ID;

		@BeforeTest
		public static void Test_Reports(){

		String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

		Run_ID = RunId();

		htmlReporter = new ExtentSparkReporter("Z:\\INS_AUTOMATION\\INS_Reports_"+timeStamp+"\\Initiate_Inspection"+Run_ID+".html");

		htmlReporter.config().setDocumentTitle("Inspection Automation Test Report");

		htmlReporter.config().setReportName("Initiate Inspection");

		htmlReporter.config().setTheme(Theme.DARK);

		reports = new ExtentReports();

		reports.attachReporter(htmlReporter);

		reports.setSystemInfo("Host Name", "LocalHost");
		reports.setSystemInfo("Run ID", RunId());
		reports.setSystemInfo("OS", "Windows 10");
		reports.setSystemInfo("Tester Name", "Automation");

		BasicConfigurator.configure();

	}

		public static String RunId(){


			int randomPIN = (int)(Math.random()*9000)+1000;
			String val = ""+randomPIN;
			return val;

		}

}
