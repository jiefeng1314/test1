package com.oscarbase;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;

public class GetAllWindows {
	public static String newPopupWindow(WebDriver driver,String currenthandle){
		String popupWindow = null;
		Set<String> handles = driver.getWindowHandles();
		handles.remove(currenthandle);
		Iterator<String> it = handles.iterator();
		if (it.hasNext()) {
			popupWindow = (String) it.next();
		}
		return popupWindow;
	}
	public static String newPopupWindow(WebDriver driver,String currenthandle1,String currenthandle2){
		String popupWindow = null;
		Set<String> handles = driver.getWindowHandles();
		handles.remove(currenthandle1);
		handles.remove(currenthandle2);
		Iterator<String> it = handles.iterator();
		if (it.hasNext()) {
			popupWindow = (String) it.next();
		}
		return popupWindow;
	}

}
