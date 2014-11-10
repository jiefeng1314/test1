package com.oscarbase;

import java.awt.Frame;
import java.awt.RenderingHints.Key;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverActions {

	public WebDriver driver;
	public WebDriverWait driverWait;

	public WebDriverActions(WebDriver driver) {
		this.driver = driver;
	}

	public void clickAndHold(By by)
	{
		Actions actions=new Actions(driver);
		actions.clickAndHold(driver.findElement(by));
		actions.release();
	}
	
	public void move(By by)
	{
		Actions actions=new Actions(driver);
		actions.moveToElement(driver.findElement(by)).build().perform();
	}
	public void move1(WebElement element)
	{
		Actions actions=new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
	public void backSpace(By by,int i)
	{
		Actions actions=new Actions(driver);
		for(int n=0;n<i;n++ )
		{
		actions.sendKeys(Keys.BACK_SPACE).build().perform();
		}
	}
	
	
	public void open(String url) {
		driver.get(url);
	}

	public void click(By by) {
		driver.findElement(by).click();
	}

	public void click(WebElement e, By by) {
		e.findElement(by).click();
	}

	public void click(By by, int index) {
		driver.findElements(by).get(index).click();
	}

	public void clickaa(By by){
		new Actions(driver).click(driver.findElement(by)).perform();
	}
	public void doubleClick(By by) {
		new Actions(driver).doubleClick(driver.findElement(by)).perform();
	}

	public void clickElementContainingText(By by, String text) {
		List<WebElement> elementList = driver.findElements(by);
		for (WebElement e : elementList) {
			if (e.getText().contains(text)) {
				e.click();
				break;
			}
		}
	}

	public void driverWait(int howManySeconds) {
		driver.manage().timeouts()
				.implicitlyWait(howManySeconds, TimeUnit.SECONDS);
	}
	
	public void refreshCurrentWindow(){
		driver.navigate().refresh();
	}

	public void submit(By by) {
		driver.findElement(by).submit();
	}

	public void clear(By by) {
		driver.findElement(by).clear();
	}

	public void type(By by, String value) {
		driver.findElement(by).sendKeys(value);
	}

	public void selectByValue(By by, String optionValue) {
		new Select(driver.findElement(by)).selectByValue(optionValue);
	}

	public void selectByVisibleText(By by, String visibleText) {
		new Select(driver.findElement(by)).selectByVisibleText(visibleText);
	}

	public void selectByIndex(By by, int index) {
		new Select(driver.findElement(by)).selectByIndex(index);
	}

	public boolean isSelected(By by) {
		boolean isSelected = false;
		if (driver.findElement(by).isSelected()) {
			isSelected = true;
		}
		return isSelected;
	}

	public String getCurrentHandle() {
		String currentHandle = driver.getWindowHandle();
		return currentHandle;
	}

	public String getNewPopupWinHdl(String currenthandle) {
		String newPopupWindowHandle = null;
		Set<String> handles = driver.getWindowHandles();
		handles.remove(currenthandle);
		Iterator<String> it = handles.iterator();
		if (it.hasNext()) {
			newPopupWindowHandle = (String) it.next();
		}
		return newPopupWindowHandle;
	}

	public String getNewPopupWinHdl(String currenthandle1, String currenthandle2) {
		String newPopupWindowHandle = null;
		Set<String> handles = driver.getWindowHandles();
		handles.remove(currenthandle1);
		handles.remove(currenthandle2);
		Iterator<String> it = handles.iterator();
		if (it.hasNext()) {
			newPopupWindowHandle = (String) it.next();
		}
		return newPopupWindowHandle;
	}
	
	public String getNewPopupWinHdl(String currenthandle1, String currenthandle2,String currenthandle3) {
		String newPopupWindowHandle = null;
		Set<String> handles = driver.getWindowHandles();
		handles.remove(currenthandle1);
		handles.remove(currenthandle2);
		handles.remove(currenthandle3);
		Iterator<String> it = handles.iterator();
		if (it.hasNext()) {
			newPopupWindowHandle = (String) it.next();
		}
		return newPopupWindowHandle;
	}

	public String getNewPopupWinHdl(String currenthandle1, String currenthandle2,String currenthandle3,String currenthandle4) {
		String newPopupWindowHandle = null;
		Set<String> handles = driver.getWindowHandles();
		handles.remove(currenthandle1);
		handles.remove(currenthandle2);
		handles.remove(currenthandle3);
		handles.remove(currenthandle4);
		Iterator<String> it = handles.iterator();
		if (it.hasNext()) {
			newPopupWindowHandle = (String) it.next();
		}
		return newPopupWindowHandle;
	}

	
	public void selectWindow(String handle) {
		driver.switchTo().window(handle);
	}

	public void maximizeWindow(){
		driver.manage().window().maximize();
	}
	public void closeCurrentWindow() {
		driver.close();
	}

	public void closeDriver() {
		driver.quit();
	}

	public Alert getAlert() {
		return driver.switchTo().alert();
	}

	public void accept() {
		driver.switchTo().alert().accept();
	}

	public void dismiss() {
		driver.switchTo().alert().dismiss();
	}

	public void chooseOk() {
		driver.switchTo().alert().accept();
	}

	public void chooseCancel() {
		driver.switchTo().alert().dismiss();
	}

	
	
    public void dealAlert(boolean option) {  
        try {  
            Alert alert = driver.switchTo().alert();  
            if (null==alert)  
            {
            	  System.out.println("nothing ." );
            }else {
         
                if (option) {  
                    alert.accept();  
                    System.out.println("Accept the alert: " + alert.getText());  
                } else {  
                    alert.dismiss();  
                    System.out.println("Dismiss the alert: " + alert.getText());  
                }  
           }
        } catch (Exception e) {  
            System.out.println("There is no alert appear!");  
        }  
      
    }  
	
	
	
	
	
	
	
	public boolean isChecked(By by) {
		if (driver.findElement(by).getAttribute("checked")!=null) {
			if(driver.findElement(by).getAttribute("checked").compareTo("true") == 0){
				return true;
			}else{
				return false;
			}		
		} else {
			return false;
		}
	}
	
	public WebElement getWebElement(By by){
		return driver.findElement(by);
	}
 
	public List<WebElement> getWebElements(By by){
		return driver.findElements(by);
	}
	
	public List<WebElement> getWebElements(WebElement e,By by){
		return e.findElements(by);
	}
	
	public String getLinkUrl(By by) {
		return driver.findElement(by).getAttribute("href");
	}

	public String getLinkUrlContainingText(By by, String text) {
		List<WebElement> subscribeButton = driver.findElements(by);
		String url = null;
		for (WebElement e : subscribeButton) {
			if (e.getText().contains(text)) {
				url = e.getAttribute("href");
				break;
			}
		}
		return url;
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	
	public boolean isElementPresent(WebElement e, By by) {
		try {
			e.findElement(by);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public String getText(By by) {
		try {
			return driver.findElement(by).getText().trim();
		} catch (NoSuchElementException e) {
			return "Text doesn't exist!";
		}
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getText(By by, int index) {
		try {
			return driver.findElements(by).get(index).getText();
		} catch (NoSuchElementException e) {
			return "Text doesn't exist!";
		}
	}

	public String getValue(By by, String attribute) {
		return driver.findElement(by).getAttribute(attribute).trim();
	}

	public void iframe(String idname)
	{
		driver.switchTo().frame(idname);
	}
	/*
	 * about table
	 */
	
	public List<WebElement> getAllTables(By by){
		return driver.findElements(by);
	}
	
	public WebElement getTable(By by) {
		return driver.findElement(by);
	}

	public WebElement getTable(By by, int index) {
		List<WebElement> tables = driver.findElements(by);
		return tables.get(index);
	}

	public int getSizeOfRows(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		return rows.size();
	}

	public List<WebElement> getAllRows(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		return rows;
	}

	public WebElement getRow(WebElement table, int index) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		return rows.get(index);
	}

	public WebElement getCell(WebElement row, int cell) {
		List<WebElement> cells;
		WebElement e = null;
		if (row.findElements(By.tagName("th")).size() > 0) {
			cells = row.findElements(By.tagName("th"));
			e = cells.get(cell);
		}
		if (row.findElements(By.tagName("td")).size() > 0) {
			cells = row.findElements(By.tagName("td"));
			e = cells.get(cell);
		}
		return e;
	}

	public String getCellText(WebElement row, int cell) {
		List<WebElement> cells;
		String text = null;
		if (row.findElements(By.tagName("th")).size() > 0) {
			cells = row.findElements(By.tagName("th"));
			// BaseUtil.log("how many ths: " + cells.size());
			if(cell<cells.size())
			{
			text = cells.get(cell).getText().trim();
			}
		}
		if (row.findElements(By.tagName("td")).size() > 0) {
			cells = row.findElements(By.tagName("td"));
			// BaseUtil.log("how many tds: " + cells.size());
			if(cell<cells.size())
			{
			text = cells.get(cell).getText().trim();
			}
		}
		return text;

	}
}
