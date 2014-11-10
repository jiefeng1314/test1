package com.oscarbase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class IsElementPresent{
	
public static boolean isElementPresent(WebDriver driver, By by) {
	boolean itIsPresent=false;
		try {
			driver.findElement(by);
			itIsPresent=true;
		} catch (NoSuchElementException e) {
			//e.printStackTrace();
			BaseUtil.log("not find this element");
		}
		return itIsPresent;
	}
public static boolean isElementPresent(WebElement element, By by) {
	boolean itIsPresent=false;
	try {
		element.findElement(by);
		itIsPresent=true;
	} catch (NoSuchElementException e) {
		e.printStackTrace();
	}
	return itIsPresent;
}
}