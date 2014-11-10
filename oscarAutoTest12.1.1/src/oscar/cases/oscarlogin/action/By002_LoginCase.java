package oscarlogin.action;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.oscarbase.*;

//User try to login oscar with wrong login information.
public class By002_LoginCase {
	private static void login(String username,String password,String pin,WebDriver driver){
	    String baseurl = CaseConf.getInstance().getBaseUrl();
	    
		driver.get(baseurl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("pin")).sendKeys(pin);
		
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	 boolean cases(String username,String password,String pin,WebDriver driver){
		    boolean bool;
			login(username,password,pin,driver);
			String currenturl = driver.getCurrentUrl();
			if (currenturl.contains("login=failed")) {				
				bool=true;
			} else {
				bool=false;
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return bool;
	}
	}

