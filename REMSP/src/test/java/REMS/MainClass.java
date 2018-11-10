package REMS;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MainClass 
{
  public static WebDriver driver;
  
  public static ExtentHtmlReporter htmlReporter;
  public static ExtentReports extent;
  public static ExtentTest log;
  
  
  @BeforeSuite
  public void startReport()
  {
	  System.out.println("======before suite");
  	  htmlReporter = new ExtentHtmlReporter("c:\\selenium\\REMS_Report.html");
	  extent = new ExtentReports ();
	  extent.attachReporter(htmlReporter);
	  extent.setSystemInfo("Host Name", "SystemName");
	  extent.setSystemInfo("Environment", "SAT Evn");
	  extent.setSystemInfo("User Name", "Anna Durai");
	  
	  htmlReporter.config().setDocumentTitle("REMS Portal");
	  htmlReporter.config().setReportName("REMS Portal Functional Testing");
	  htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	  htmlReporter.config().setTheme(Theme.STANDARD);
  }
  public void takescreenshot(String filename) throws Exception
  {
	    File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f,new File("c:\\selenium\\REMSScreenshots\\"+filename));		
		log.addScreenCaptureFromPath("c:\\selenium\\REMSScreenshots\\"+filename);
		System.out.println("======taken scheenshot");
  }
  @AfterSuite
  public void endReport()
  {
	  System.out.println("=====after suite");
	  extent.flush();
  }
}
