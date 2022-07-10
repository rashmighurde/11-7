package apiPractice;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class PojoSeventhProject {
    @Test
	public void oauthPractice() throws InterruptedException
	{  /*url=https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss
	&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M
	&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#*/
    	
    //google sign in page ko automate nahi kiya ja sakta, isliye wo part hame manually karna padega
    	
    	String url="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println(code);
		
String response=given().urlEncodingEnabled(false)
		.queryParam("code",code)
		.queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("grant-type","authorization_code")
		.queryParam("state","verifyfjdss")
		.queryParam("session_state","ff4a89d1f7011eb34eef8cf02ce4353316d9744b")
		.queryParam("scope","email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
		.queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js=new JsonPath(response);
		String accessToken=js.getString("access_token");
		System.out.println(accessToken);
		
String response2=given()
        .contentType("application/json")
        .queryParams("access_token",accessToken).expect().defaultParser(Parser.JSON)
        
        .when()
        .get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println(response2);
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
