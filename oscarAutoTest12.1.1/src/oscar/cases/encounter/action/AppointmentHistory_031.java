package encounter.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import testlink.api.java.client.TestLinkAPIResults;
import com.oscarbase.*;

public class AppointmentHistory_031 {

	private static String homePageWin = "";
	private static String encounterWin="";
	private static String appointment="";
	private static 	String Searchpointment="";
	private static  String [] wins=new String[2];
	private static String addApptWin="";
	private static String msg = "";
	private static String time = "";
	private static String expectedDate;
	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	
	public static void run() throws Exception {
 
		FirefoxProfile profile = new FirefoxProfile();  
		profile.setPreference("intl.accept_languages", "en-US");   
		driver = new FirefoxDriver(profile); 
		action=new WebDriverActions(driver);
		
		// @parameters for testlink and some basic info about case.
		String projectName = CaseConf.getInstance().getProjectName();
		String testplanName = CaseConf.getInstance().getTestplanName();
		String build = CaseConf.getInstance().getBuild();
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		String demogName= CaseConf.getInstance().getCreateAppointmentDemogName();
		// @parameters for test data.
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy031");
		String testcaseName = p.getProperty("TestcaseName");
	   time = p.getProperty("time");

		String result = "";
		String note = "";
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		expectedDate = df.format(dt);
		LoginOscar.login(driver);
		wins=createAppointment.create(action);
		homePageWin=wins[0];
		msg=msg+wins[1];
		
		wins=createAppointment.openEncounter(action, demogName);
		encounterWin=wins[0];
		msg=msg+wins[1];
		
		
		 Thread.sleep(8000);
		
		try {
			AppointmentHistory_031 case089 = new AppointmentHistory_031();
				if (case089.addAppointment()) {
					   BaseUtil.log("enter 1");
					if (case089.verify()) {
						result = TestLinkAPIResults.TEST_PASSED;
						BaseUtil.log("pass");
					} else {
						result = TestLinkAPIResults.TEST_FAILED;
						note = case089.getMsg();
					}
				} else {
					result = TestLinkAPIResults.TEST_FAILED;
					note = case089.getMsg();
				}
			
		
		 BaseUtil.log(note);
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();
		} finally {
			TestExecutionResult.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, note, result);
			System.out
					.println("AppointmentHistory_031: Execution Terminated at "
							+ dt);
		}
		driver.quit();
	//	Thread.sleep(3000);
	}

	
	
	

	private boolean addAppointment() throws InterruptedException {
		
		if (action.isElementPresent(By.xpath("//div[@id='appointmentHistory']//a[contains(text(),'Appointment History')]"))) 
		{
			action.click(By.xpath("//div[@id='appointmentHistory']//a[contains(text(),'Appointment History')]"));
			action.driverWait(10);
			appointment = action.getNewPopupWinHdl(homePageWin ,encounterWin);
			if (appointment != null) {
				action.selectWindow(appointment);
				action.click(By.xpath("//td[@class='MainTableLeftColumn']//a[contains(text(),'Add Appointment')]"));
				
				Searchpointment=action.getNewPopupWinHdl(homePageWin, encounterWin, appointment);
				action.selectWindow(Searchpointment);
				if (openAddAppointmentWin()) {
					action.click(By.id("addButton"));
					action.selectWindow(appointment);
				
					return true;
				} else {
					msg =msg+ " | Cannot open 'Add Appointment' window by clicking time flip link.";
					return false;
				}

			} else {
				msg =msg+ " | Cannot open  page by clicking 'Appointment History' link.";
				return false;
			}
		} else {
			msg = msg+" | 'Appointment History' link text doesn't exist.";
			return false;
		}
	}

	private boolean openAddAppointmentWin() {
         BaseUtil.log(time);
		action.click(By.xpath("//td[@title='"+time+"']//a"));//16:00
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		addApptWin =action.getNewPopupWinHdl(homePageWin, Searchpointment,encounterWin,appointment);
		if (addApptWin != null) {
			action.selectWindow(addApptWin);
			return true;
		} else {
			return false;
		}
	}

	private boolean verify() {
		
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		if (verifyApptHistoryList()&& verifyApptHistoryTbl()&&verifyApptOnTheHomeApponiment() ) {
			
			return true;
		} else {
			return false;
		}
	}

	private boolean verifyApptHistoryList() {
		boolean listedCorrectly = false;

		if (action.isElementPresent(By.id("appointmentHistorylist"))) {
			List<WebElement> apptHistoryList = action.getWebElements(
					action.getWebElement(By.id("appointmentHistorylist")),
					By.className("links"));
			int howMany = apptHistoryList.size();
			BaseUtil.log("howMany is: " + howMany);
			if (howMany > 0) {
				for (WebElement e : apptHistoryList) {
					String linkText = e.getText();
					if (linkText.contains(expectedDate)) {
						listedCorrectly = true;
				
						break;
					}
				}
				if (!listedCorrectly) {
					msg =msg+ "| New appointment wasn't listed.";
				}
			} else {
				msg = msg+ " | no appointment was listed. ";
			}

		} else {
			msg =msg+  " | Webelment 'appointment history list' doesn't exist on encounter page.";
		}

		return listedCorrectly;
	}

	private boolean verifyApptHistoryTbl() {

		boolean listedByTbl = false;

		action.selectWindow(appointment);
		action.refreshCurrentWindow();
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
		}
		if (action.isElementPresent(By.id("apptHistoryTbl"))) {
			List<WebElement> allLinks = action.getWebElements(
					action.getWebElement(By.id("apptHistoryTbl")),
					By.tagName("a"));
			for (WebElement e : allLinks) {
				String linkText = e.getText();
				if (linkText.contains(expectedDate)) {
					listedByTbl = true;
					break;
				}
			}
			if (listedByTbl) {
			 
			}else {
				msg = msg+ " | New appointment doesn't listed from appointment history table. ";
			}
		} else {
			msg = msg
					+ " | Webelement 'appointment history table' doesn't exist.";
		}
		return listedByTbl;
	}
	private boolean verifyApptOnTheHomeApponiment()
	{
		action.selectWindow(homePageWin);
		action.refreshCurrentWindow();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		
		if(action.isElementPresent(By.xpath("//a[contains(text(),'Wimmer,Jord')]/../preceding-sibling::td//a[contains(text(),'"+time+"')]")))
		{
			return true;
		}else {
			msg=msg+"| add not success";
			return false;
		}
	}

	private String getMsg() {
		return msg;
	}
}
