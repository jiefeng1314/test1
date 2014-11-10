package com.oscarbase;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginOscar {
	public static void login(WebDriver driver) throws IOException{
	    String baseurl = CaseConf.getInstance().getBaseUrl();
	    String username=CaseConf.getInstance().getUsername();
	    String password=CaseConf.getInstance().getPassword();
	    String pin= CaseConf.getInstance().getPin();
		driver.get(baseurl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("pin")).sendKeys(pin);
		
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}