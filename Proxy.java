package apiPractice;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Proxy {
	
    Proxy p=new Proxy();
	p.setHttpProxy("localhost:8888");
	DesiredCapabilities cap=new DesiredCapabilities();
    cap.setCapability(CapabilityType.PROXY,p);
    
    WebDriver driver=new ChromeDriver(cap);
 driver.get("https://www.flipkart.com");
}
