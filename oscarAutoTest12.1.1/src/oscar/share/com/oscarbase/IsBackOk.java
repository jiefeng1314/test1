package com.oscarbase;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IsBackOk {

public static boolean verifyBackLink(WebDriver driver){
	boolean isBackOk;
	
	String adminUrl=CaseConf.getInstance().getAdminUrl();
	BaseUtil.log("adminurl is "+adminUrl);
	driver.switchTo().defaultContent();
	driver.findElement(By.partialLinkText("Administration Panel")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	if (driver.getCurrentUrl().compareTo(adminUrl)==0){
		isBackOk=true;
	}else{
		isBackOk=false;
	}
	return isBackOk;
}
}