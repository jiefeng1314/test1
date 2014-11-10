package encounter.action;

import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import testlink.api.java.client.TestLinkAPIResults;
import com.oscarbase.*;

public class ExaminationHistory_032 {

	
	private static  String homePageWin = "";
	private static  String encounterWin="";
	private static  String examinationWin="";
	private static  String [] wins=new String[2];
	private static String expectedPageTitle1 = "";
	private static String expectedPageTitle2 = "";
	private static String docDesc = "";
	private static String linkURL = "";
	private static String msg = "";
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
		String demogName= CaseConf.getInstance().getCreateAppointmentDemogName();
		String build = CaseConf.getInstance().getBuild();
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		// @parameters for test data.
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy032");
		String testcaseName = p.getProperty("TestcaseName");
		expectedPageTitle1 = p.getProperty("ExpectedPageTitle1");
		expectedPageTitle2 = p.getProperty("ExpectedPageTitle2");
		docDesc = p.getProperty("DocDesc");
		linkURL = p.getProperty("LinkURL");

		String result = "";
		String note = "";
		Date dt = new Date();

		try {
			ExaminationHistory_032 case032 = new ExaminationHistory_032();
			LoginOscar.login(driver);
			wins=createAppointment.create(action);
			homePageWin=wins[0];
			msg=msg+wins[1];
			
			wins=createAppointment.openEncounter(action, demogName);
			encounterWin=wins[0];
			msg=msg+wins[1];
			 Thread.sleep(8000);
			
				if(case032.checklink())
				{
					BaseUtil.log("enter 1");
					
					case032.check();
					
				}else {
					result = TestLinkAPIResults.TEST_FAILED;
					note=case032.getMsg();
				}
				if (result.isEmpty()) {
					BaseUtil.log("pass");
					result = TestLinkAPIResults.TEST_PASSED;
				} else {
					note = case032.getMsg();
				}

			BaseUtil.log(note);
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();
		} finally {
			TestExecutionResult.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, note, result);
			System.out.println("ExaminationHistory_100: Execution Terminated at " + dt);
		}
		driver.quit();
		Thread.sleep(3000);
	}

	/*private void openEncounterWin() {
		homePageWin = action.getCurrentHandle();
		action.click(By.linkText("Search"));
		action.selectWindow(action.getNewPopupWinHdl(homePageWin));
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
		action.click(By.linkText("Demogp' No"));
		action.click(By.linkText("E"));
		action.driverWait(10);
		action.closeCurrentWindow();
		encounterWin = action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(encounterWin);
	}
*/


	

	private boolean checklink() {
	  boolean flag=false;
		if(action.isElementPresent(By.xpath("//a[contains(text(),'Examination History')]")))
		{
			flag=true;
			action.click(By.xpath("//a[contains(text(),'Examination History')]"));
			examinationWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
			action.selectWindow(examinationWin);
			
		}
		else {
			msg=msg+"| not exist 'Examination History' link";
		}
		
		return flag;
	}

	private void check()
	{
		action.click(By.xpath("//select[@name='fromlist1']/option[1]"));
		action.click(By.xpath("//input[@type='button']"));
		action.click(By.xpath("//input[@type='submit']"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		
		
	}
	

	
	private String getMsg() {
		return msg;
	}
}
