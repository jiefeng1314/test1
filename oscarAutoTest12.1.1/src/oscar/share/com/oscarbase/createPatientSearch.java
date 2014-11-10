package com.oscarbase;
import org.openqa.selenium.By;

import com.sun.jna.platform.win32.Winspool;

public class createPatientSearch {
	private static  String createAppointmentDemogName=CaseConf.getInstance().getCreateAppointmentDemogName();
	private static String [] wins=new String[2];
	public createPatientSearch(){}
	
	  public static String[] openPatientSearchWin(WebDriverActions action) {
		String homePageWin = action.getCurrentHandle();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		action.click(By.linkText("Search"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		String patientSearchWin = action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(patientSearchWin);
		action.type((By.xpath("//input[@name='keyword'and @type='text']")),createAppointmentDemogName);
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
		action.click(By.linkText("Demogp' No"));
		
		wins[0]=homePageWin;
		wins[1]=patientSearchWin;
		return wins;
	}
		
		public  static String openEncounterWin(WebDriverActions action) {
			action.click(By.linkText("E"));
			action.driverWait(30);
		   String encounterWin = action.getNewPopupWinHdl(wins[0],
					wins[1]);
			action.selectWindow(encounterWin);
			return encounterWin;
		}
}
