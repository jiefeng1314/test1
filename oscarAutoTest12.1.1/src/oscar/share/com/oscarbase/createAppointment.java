package com.oscarbase;

import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.RETURN;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class createAppointment {
    
	private static String createAppointmentDemogName = CaseConf.getInstance().getCreateAppointmentDemogName();
	private static String createAppointmentTime = CaseConf.getInstance().getCreateAppointmentTime();
	private static String sendNumber = CaseConf.getInstance().getSendNumber();
	private static  String  note="";
	private static String [] createAppoinmentResult=new String[2];
	private static String [] createEncounterResult=new String[2];
	private static String encounterwin="";
	private static String homePageWin="";
	
	public createAppointment() {
		
	}
	
	public static String[] create(WebDriverActions action)
	{  
		homePageWin = action.getCurrentHandle();
		
		try {
		action.selectByValue(By.xpath("//select[@id='mygroup_no']"), sendNumber);
		Thread.sleep(1500);
		action.click(By.xpath("//a[contains(text(),'"+ createAppointmentTime+"')]"));
		Thread.sleep(1500);
		String searchAppointment = action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(searchAppointment);
		action.click(By.xpath("//input[@type='submit'][@value='Search']"));

		Thread.sleep(1500);
		action.type((By.xpath("//input[@name='keyword'and @type='text']")),createAppointmentDemogName);
		action.click(By.xpath("//input[@type='SUBMIT']"));
		Thread.sleep(1500);
		action.click(By.xpath("//input[@id='addButton']"));
		Thread.sleep(1000);
		}catch (Exception e) {
			note=e.getMessage();
		    e.printStackTrace();
		}
		action.selectWindow(homePageWin);
		createAppoinmentResult[0]=homePageWin;
		createAppoinmentResult[1]=note;
		return createAppoinmentResult;
		
	
	}
	
	
	public static String [] openEncounter(WebDriverActions action,String demogName){
		
		try {
		BaseUtil.log("enter openEncounter");
		action.click(By.xpath("//a[contains(text(),'"+demogName.split(" ")[0]+"')]/following-sibling::a[@class='encounterBtn']"));
		String homePageWin = action.getCurrentHandle();
	    encounterwin=action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(encounterwin);
		} catch (Exception e) {
			note=e.getMessage();
			e.printStackTrace();
		}
		
		 createEncounterResult[0]=encounterwin;
	     createEncounterResult[1]=note;
	    return createEncounterResult;
	}
	
	
	public  static String [] deleteAppointmentAndcreateAgain(WebDriverActions action,String demogName)
	{
		try {	
	   action.selectWindow(encounterwin);
	   action.closeCurrentWindow();
	   Thread.sleep(2000);
	   action.selectWindow(homePageWin);
	   action.click(By.xpath("//a[contains(text(),'"+demogName.split(" ")[0]+"')]"));
	   String searchAppointment = action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(searchAppointment);
		action.click(By.xpath("//input[@id='deleteButton']"));
		action.accept();
		Thread.sleep(1000);
		action.selectWindow(homePageWin);
		} catch (Exception e) {
		}
	   
		create(action);
		
	   return createAppoinmentResult;
		
	}
	
	  
	
}
