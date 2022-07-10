package apiPractice;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class FifithProject {
	@Test
	public void PassJSONFile() throws IOException
	{
	RestAssured.baseURI="http://216.10.245.166";
	given().log().all()
	.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Rashmi\\eclipseNew\\Groww\\Fifth.json"))))
	.when()
	.post("/Library/Addbook.php")
	.then().log().all().assertThat().statusCode(200);
	
	}	
}

















