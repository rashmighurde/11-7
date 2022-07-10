package apiPractice;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentReports;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.JsonPath;


public class ThirdProject {
	public ExtentReports extent;
	public ExtentTest extentTest;
	
 /* @BeforeTest
  public void beforeTest()
  {
	extent=new ExtentReports(System.getProperty("user.dir")+"/test-output/NewExtentReport.html",true);
  }*/
 public static void main(String[]args)
 {
	// extentTest=extent.startTest("ComplexJsonParse");
	JsonPath js=new JsonPath(Reusable_Json_Body.ThirdProject());
	
	//Q.Print No of courses returned by API
	 int count=js.getInt("courses.Size()");
	 System.out.println(count);
	 
	//Q.Print Purchase Amount
	 int amount=js.getInt("dashboard.purchaseAmount");
	 System.out.println(amount);

	 //Q.Print Title of the first course
	 System.out.println(js.get("courses[0].title").toString());
    
	 //Q.Print All course titles and their respective Prices
	 for(int i=0;i<count;i++)
	 {
		 System.out.println(js.get("courses["+i+"].title").toString());
		 int price=js.get("courses["+i+"].price");
		 System.out.println(price);
	 }
	
	 //Q.Print no of copies sold by RPA Course
	 for(int i=0;i<count;i++)
	 {   String courseTitles=js.get("courses["+i+"].title");
		 if(courseTitles.equalsIgnoreCase("RPA")) {
			 int copies=js.getInt("courses["+i+"].copies");
			 System.out.println(copies);
		 }
	 }
      int copies=js.get("courses[2].copies");   
      System.out.println(copies);
  
 }
/*@AfterTest
public void AfterTest()
{
	extent.flush();
	extent.close();
}*/
}





















