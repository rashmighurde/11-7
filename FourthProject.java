package apiPractice;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FourthProject {
/*@DataProvider(name="PassData")
public Object[][] addData()
{
	return new Object[][] {{"abcd","123"},{"efg","456"},{"hij","789"}};
	
}*/

@Test
public void addBook()
{
	RestAssured.baseURI="http://216.10.245.166";
	/*String responce=given().log().all()
	.header("Content-Type","application/json")
	.body(Reusable_Json_Body.FourthProject("abcd","123"))
	//.body(GenerateStringFromResource(""))
	.when()
	.put("/Library/Addbook.php")
	.then().assertThat().log().all().statusCode(200)
	.extract().response().asString();
	
	JsonPath js=new JsonPath(responce);
	String id=js.get("ID");
	System.out.println(id);*/
	
//delete api
		given().log().all()
		.body(Reusable_Json_Body.FourthProject("bcze","2271"))
		.when()
		.delete("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(400);
	
	
	
	
	
	
	
	
	
	
	
}
}
