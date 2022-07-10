package apiPractice;
import com.relevantcodes.extentreports.ExtentTest;
import apiPractice.JSONPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.LogStatus;

import baseclasses.Base_Class;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utilityclasses.Util_Class;
import apiPractice.ExtentReport;

public class FirstProject extends Util_Class {
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	@BeforeTest
	public void setExtent(){
extent=new ExtentReports(System.getProperty("user.dir")+"/test-output/NewExtentReport.html",true);
	}
	
	
	@Test
	public void Update_Address_Api()
	{
		extentTest = extent.startTest("Update_Address_Api");
 
    RestAssured.baseURI= "https://rahulshettyacademy.com";
  String response=given()
        .log().all().queryParam("key","qaclick123")
        .header("Content-Type","application/json")
        .body(Reusable_Json_Body.FirstProject())
        .when()
        .post("maps/api/place/add/json")
        .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
        .header("Server",equalTo("Apache/2.4.41 (Ubuntu)")).extract().response().asString();
        //System.out.println(response);
        JsonPath js=new JsonPath(response);
        String placeID=js.getString("place_id");
        System.out.println(placeID);
	
        
        
        
        //Put Method
        String newExpectedAddress="Sadguru niwas,Saroj colony,Amravati";
        	given()
            .log().all().queryParam("key","qaclick123")
            .header("content-type","application/json")
            .body("{\r\n" + 
				"\"place_id\":\""+placeID+"\",\r\n" + 
				"\"address\":\""+newExpectedAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
            .when()
            .put("maps/api/place/update/json")
            .then().log().all().assertThat().statusCode(200)
            .body("msg",equalTo("Address successfully updated"))
            ;
    	
        
        	//Get Method
        	
   String response2=given()
        	.header("accept","application/json")
        	.log().all().queryParam("place_id",placeID)
        	.queryParam("key", "qaclick123")
        	.when()
        	.get("maps/api/place/get/json")
        	.then()
        	.log().all().assertThat().statusCode(200).extract().response().asString();
         JsonPath js1=new JsonPath(response2);
        String actualAddress =js1.getString("address");
        System.out.println(actualAddress);
       Assert.assertEquals(actualAddress,"Sadguru niwas,Saroj colony,Amravati");
	
        	
    //delete method
	String res=given()
	.log().all().queryParam("key","qaclick123")
	.when()
	.delete("maps/api/place/delete/json")
	.then()
	.log().all().assertThat().statusCode(200).extract().response().asString();
    JsonPath js2=new JsonPath(response2);
      System.out.println(js2.getString(res).toString());
	}    	
        	//@AfterMethod
    		public void tearDown(ITestResult result) throws IOException{

    			if(result.getStatus()==ITestResult.FAILURE){
    				extentTest.log(LogStatus.FAIL, "Test Case Failed Is "+result.getName()); //to add name in extent report
    				extentTest.log(LogStatus.FAIL, "Test Case Failed Is "+result.getThrowable()); //to add error/exception in extent report

    				String screenshotPath = Util_Class.getScreenshot(driver, result.getName());
    				extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
    				//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
    			}
    			else if(result.getStatus()==ITestResult.SKIP){
    				extentTest.log(LogStatus.SKIP, "Test Case Skipped IS " + result.getName());
    			}
    			else if(result.getStatus()==ITestResult.SUCCESS){
    				extentTest.log(LogStatus.PASS, "Test Case Passed IS " + result.getName());

    			}


    			extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
    			//driver.quit();
    		}
            //@AfterTest
        	public void endReport()
        	{
        		extent.flush();
        		extent.close();
        	}

        
    	/*@AfterClass
    	public void afterClass()
    	{
    		report.flush();
    	}
    	*/
}	
    	
 
