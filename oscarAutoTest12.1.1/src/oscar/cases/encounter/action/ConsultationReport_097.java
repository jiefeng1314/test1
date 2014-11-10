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

public class ConsultationReport_097 {

	private static String toName = "";
	private static String expectedPageTitle="";
	private static String addConReportWin="";
	private static String demogName="";
	private static  String [] wins=new String[2];
	private static String homePageWin = "";
	private static String encounterWin = "";
	private static String msg = "";
	private static String updateExpectedLogContent = "";
	private static String ProcedureNotes = "";
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
		// @parameters for test data.
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy097");
		String testcaseName = p.getProperty("TestcaseName");
		demogName=p.getProperty("demogName").trim();
		expectedPageTitle=p.getProperty("ExpectedPageTitle").trim();
        toName=p.getProperty("toName").trim();
        updateExpectedLogContent=p.getProperty("updateExpectedLogContent").trim();
        ProcedureNotes=p.getProperty("ProcedureNotes").trim();
		String result = "";
		String note = "";
		Date dt = new Date();

		try {
			ConsultationReport_097 case097 = new ConsultationReport_097();
			LoginOscar.login(driver);
			
			wins=createAppointment.create(action);
			homePageWin=wins[0];
			msg=msg+wins[1];
			
			wins=createAppointment.openEncounter(action, demogName);
			encounterWin=wins[0];
			msg=msg+wins[1];
			//case097.openEncounterWin();

			if (case097.canOpenAddConReportWin()) {
                    BaseUtil.log("enter 1");
				case097.addConReport();
                   BaseUtil.log("enter 2");
				if (!case097.verifyCanOpenViewConReportWin()) {
					 BaseUtil.log("enter error1");
					result = TestLinkAPIResults.TEST_FAILED;
				}

				if (!case097.verifyCanOpenConReportListWin()) {
					 BaseUtil.log("enter error2");
					result = TestLinkAPIResults.TEST_FAILED;
				}

				if (result.isEmpty()) {
					BaseUtil.log("pass");
					result = TestLinkAPIResults.TEST_PASSED;
				} else {
					note = case097.getMsg();
				}

			} else {
				result = TestLinkAPIResults.TEST_FAILED;
				note = case097.getMsg();
			}

			BaseUtil.log(result);
			BaseUtil.log(note);
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();
		} finally {
			TestExecutionResult.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, note, result);
			System.out
					.println("ConsultationReport_097: Execution Terminated at "
							+ dt);
		}
		driver.quit();
		Thread.sleep(3000);
	}

	private void openEncounterWin() {
		homePageWin = action.getCurrentHandle();
		action.click(By.linkText("Search"));
		action.selectWindow(action.getNewPopupWinHdl(homePageWin));
		action.type(By.xpath("//input[@name='keyword']"), demogName);
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
		action.click(By.linkText("Demogp' No"));
		action.click(By.linkText("E"));
		action.driverWait(10);
		action.closeCurrentWindow();
		encounterWin = action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(encounterWin);
	}

	private boolean conReportIsPresent() {
		if (action.isElementPresent(By.id("conReport"))) {
			return true;
		} else {
			msg = " | 'Consultation report' doesn't exist from the right navigator.";
			return false;
		}
	}

	private boolean canOpenAddConReportWin() {
        try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		if (conReportIsPresent()) {
			if (action.isElementPresent(
					action.getWebElement(By.id("menuTitleconReport")),
					By.linkText("+"))) {
				action.click(action.getWebElement(By.id("menuTitleconReport")),
						By.linkText("+"));
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
				}
				 addConReportWin = action.getNewPopupWinHdl(homePageWin,
						encounterWin);
				if (addConReportWin != null) {
					action.selectWindow(addConReportWin);
					String actualPageTitle = action.getPageTitle();
					if (actualPageTitle
							.contains(expectedPageTitle)) {
						return true;
					} else {
						msg = " | Please make sure the open page is 'generate consultation report' page.";
						return false;
					}
				} else {
					msg = " | No window will pop up by clicking '+' link.";
					return false;
				}
			} else {
				msg = " | Link '+' is not present";
				return false;
			}
		} else {
			return false;
		}
	}

	private static boolean addConReport() {
		boolean flag=true;
		action.click(By.linkText("Search #"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		String searchName = action.getNewPopupWinHdl(homePageWin,addConReportWin,
				encounterWin);
		action.selectWindow(searchName);
		action.type(By.xpath("//input[@name='keyword']"), toName);
		action.click(By.xpath("//input[@type='submit']"));
		action.click(By.xpath("//center//tr[2]"));
		action.selectWindow(addConReportWin);
	     
		
		action.clear(By.xpath("//textarea[@name='cp.clinicalInfo']"));
		action.click(By.xpath("//input[@value='current hx']"));
		String currentValue=action.getWebElement(By.xpath("//textarea[@name='cp.clinicalInfo']")).getText().trim();
		if(!currentValue.contains(updateExpectedLogContent))
		{
			flag=false;
			msg=msg+"current content is error";
		}
		
		action.clear(By.xpath("//textarea[@name='cp.clinicalInfo']"));
		action.click(By.xpath("//input[@value='past ocular hx']"));
		String pastOcularValue=action.getWebElement(By.xpath("//textarea[@name='cp.clinicalInfo']")).getText().trim();
		if(!currentValue.contains(updateExpectedLogContent))
		{
			flag=false;
			msg=msg+"past ocular hx content is error";
		}
		
		action.clear(By.xpath("//textarea[@name='cp.clinicalInfo']"));
		action.click(By.xpath("//input[@value='medical hx']"));
		String medicalValue=action.getWebElement(By.xpath("//textarea[@name='cp.clinicalInfo']")).getText().trim();
		if(!medicalValue.contains(updateExpectedLogContent))
		{
			flag=false;
			msg=msg+"medical  content is error";
		}
		
		action.clear(By.xpath("//textarea[@name='cp.clinicalInfo']"));
		action.click(By.xpath("//input[@value='family hx']"));
		String familyValue=action.getWebElement(By.xpath("//textarea[@name='cp.clinicalInfo']")).getText().trim();
		if(!familyValue.contains(updateExpectedLogContent))
		{
			flag=false;
			msg=msg+"family content is error";
		}
		
		
		action.clear(By.xpath("//textarea[@name='cp.clinicalInfo']"));
		action.click(By.xpath("//input[@value='ocular meds']"));
		String ocularMedsValue=action.getWebElement(By.xpath("//textarea[@name='cp.clinicalInfo']")).getText().trim();
		if(!ocularMedsValue.contains(updateExpectedLogContent))
		{
			flag=false;
			msg=msg+"ocularMeds  content is error";
		}
		
		action.clear(By.xpath("//textarea[@name='cp.clinicalInfo']"));
		action.click(By.xpath("//input[@value='other meds']"));
		String otherMedsValue=action.getWebElement(By.xpath("//textarea[@name='cp.clinicalInfo']")).getText().trim();
		if(!otherMedsValue.contains(updateExpectedLogContent))
		{
			flag=false;
			msg=msg+"otherMeds  content is error";
		}
		
		
		action.clear(By.xpath("//textarea[@name='cp.clinicalInfo']"));
		action.click(By.xpath("//input[@value='diag notes']"));
		String diagNotesValue=action.getWebElement(By.xpath("//textarea[@name='cp.clinicalInfo']")).getText().trim();
		if(!diagNotesValue.contains(updateExpectedLogContent))
		{
			flag=false;
			msg=msg+"diagNotes  content is error";
		}
		
		
		action.clear(By.xpath("//textarea[@name='cp.clinicalInfo']"));
		action.click(By.xpath("//input[@value='ocular proc']"));
		String ocularProcValue=action.getWebElement(By.xpath("//textarea[@name='cp.clinicalInfo']")).getText().trim();
		if(!ocularProcValue.contains(ProcedureNotes))
		{
			flag=false;
			msg=msg+"diagNotes  content is error";
		}
		
		
		
		
		
		
		
		action.click(By.xpath("//input[@type='button'][@value='save and close']"));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		action.selectWindow(encounterWin);
		
		action.refreshCurrentWindow();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		
		
		return flag;
	}

	private boolean verifyListAndLinksArePresent() {

		if (action.isElementPresent(By.id("conReportlist"))) {
			WebElement list = action.getWebElement(By.id("conReportlist"));
			if (action.isElementPresent(list, By.className("links"))) {
				return true;
			} else {
				msg = " | No conReport link exists on Encounter page.";
				return false;
			}
		} else {
			msg = " | No conReport list exists on Encounter page.";
			return false;
		}
	}

	private boolean verifyCanOpenViewConReportWin() {

		boolean isOk = false;
		
		if (verifyListAndLinksArePresent()) {
			action.click(action.getWebElement(By.id("conReportlist")),
					By.className("links"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			String viewConReportWin = action.getNewPopupWinHdl(homePageWin,
					encounterWin);
			if (viewConReportWin != null) {
				action.selectWindow(viewConReportWin);
				String actualPageTitle = action.getPageTitle();

				if (actualPageTitle.contains(expectedPageTitle)) {
					isOk = true;
				} else {
					msg = " | Please make sure users can view conReport by clicking conReport link.";
				}
				action.closeCurrentWindow();
				action.selectWindow(encounterWin);
			} else {
				msg = " | No window will pop up by clicking conReport link.";
			}
		}
		return isOk;
	}

	private boolean verifyCanOpenConReportListWin() {
		boolean isOk = false;
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		if (action.isElementPresent(By.linkText("Consulation Report"))) {
			action.click(By.linkText("Consulation Report"));
			String conReportListWin = action.getNewPopupWinHdl(homePageWin,
					encounterWin);
			if (conReportListWin != null) {
				action.selectWindow(conReportListWin);
				if (action.isElementPresent(By.id("conreport"))) {
					if (action
							.getSizeOfRows(action.getTable(By.id("conreport"))) >= 2) {
						isOk = true;
					} else {
						msg = msg
								+ " | It seems no conreport was listed on conReport list window.";
					}
				} else {
					msg = msg
							+ " | Please make sure the open window is conReport list window.";
				}

			} else {
				msg = msg + " | No window pops up by clicking conReport link.";
			}
		} else {
			msg = msg + " | No 'Con Report' link exists on encounter page.";
		}
		return isOk;
	}

	private String getMsg() {
		return msg;
	}
}
