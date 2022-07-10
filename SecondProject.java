package apiPractice;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import baseclasses.Base_Class;
import utilityclasses.Util_Class;


public class SecondProject {


	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;



	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/NewExtentReport.html", true);
		extent.addSystemInfo("Environment", "QA");
	}

	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}

     @Parameters({"Browser"})
	@BeforeMethod
	public void setup(String k){
    driver=Base_Class.getDriver(k);
        driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));
	}



	@Test
	public void WikiPedia_Title_Test(){
		extentTest = extent.startTest("WikiPedia_Title_Test");
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title,"#1 Free CRM for Any Business: Online Customer Relationship Software123");
	}

	@Test
	public void Search_Button_Check(){
		extentTest = extent.startTest("Search_Button_Check");
		boolean b = driver.findElement(By.xpath("//i[@class=\"sprite svg-search-icon\"]")).isDisplayed();
		Assert.assertTrue(b);
	}

	@AfterMethod
	public void Print_Results_In_Report(ITestResult result) throws IOException{

		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report

			String screenshotPath = Util_Class.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}


		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}


}
