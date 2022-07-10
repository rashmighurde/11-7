package apiPractice;
import io.restassured.path.json.JsonPath;

public class JSONPath {
public static JsonPath reusableJSON(String response)
{
    JsonPath js=new JsonPath(response);
    return js;
}
}
