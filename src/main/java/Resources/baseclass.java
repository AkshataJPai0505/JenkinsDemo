package Resources;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sun.tools.sjavac.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseclass {
	public WebDriver driver;
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest test;
	public static Logger log=LogManager.getLogger("baseclass");
	
	@BeforeMethod
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		
		driver=new ChromeDriver();
		log.info("Browser has been launched");
		driver.get("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
		log.info("URL has been entered");
		driver.manage().window().maximize();
		log.info("Browser window has been maximized");
	}

	
	@AfterMethod
	public void teardown(ITestResult result) throws IOException{
		if(result.getStatus()== result.FAILURE)
		{
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() +"-Test cases failed", ExtentColor.RED));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() +"-Test cases failed", ExtentColor.RED));
			String file=baseclass.screenshot(driver, result.getName());
			test.addScreenCaptureFromPath(file);
			
		}
		
		else if(result.getStatus()== result.SKIP)
		{
			test.log(Status.SKIP, "Skipped test case"+result.getName());
		}
		else if(result.getStatus()== result.SUCCESS)
		{
			test.log(Status.PASS, "Passed test case"+result.getName());
		}
		
		driver.close();
		log.info("Browser window has been closed");
	}
	@BeforeSuite
	public void startreport()
	{
		htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/Myreports/Test report.html");
		 
		htmlreporter.config().setDocumentTitle("Automation Report");
		htmlreporter.config().setReportName("Basic test cases");
		htmlreporter.config().setTheme(Theme.STANDARD);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("ProjectName", "Automationproject");
		extent.setSystemInfo("Tester", "Akshata J Pai");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Browser", "Chrome");
	}
	@AfterSuite
	public void  endreport()
	{
		extent.flush();
	}
	
  public static String screenshot(WebDriver driver,String filename) throws IOException
  {
	String date=new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date()); 
	TakesScreenshot takescreenshot=(TakesScreenshot) driver;
	File source=takescreenshot.getScreenshotAs(OutputType.FILE);
	String dest=System.getProperty("user.dir")+"\\Screenshot\\"+filename+"_"+date+".png";
	File destination =new File(dest);
	FileUtils.copyFile(source,destination);
	return dest;

  }
}





















