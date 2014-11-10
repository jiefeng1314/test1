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

public class Consultations_095 {

	private String homePageWin = "";
	private String search="";
	private String encounterWin = "";
	private String msg = "";
	private static String expectedPageTitle1 = "";
	private static String expectedPageTitle2 = "";
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy095");
		String testcaseName = p.getProperty("TestcaseName");
		expectedPageTitle1 = p.getProperty("ExpectedPageTitle1");
		expectedPageTitle2 = p.getProperty("ExpectedPageTitle2");

		String result = "";
		String note = "";
		Date dt = new Date();

		try {
			Consultations_095 case095 = new Consultations_095();
			LoginOscar.login(driver);
			case095.openEncounterWin();

			if (case095.canOpenAddConWin()) {
                        BaseUtil.log("enter 1");
			     	case095.addCon();
                        BaseUtil.log("enter 2");
				if (!case095.verifyCanOpenViewConWin()) {
					 BaseUtil.log("enter error1");
					result = TestLinkAPIResults.TEST_FAILED;
				}

				if (!case095.verifyCanOpenConListWin()) {
					 BaseUtil.log("enter error2");
					result = TestLinkAPIResults.TEST_FAILED;
				}

				if (result.isEmpty()) {
					result = TestLinkAPIResults.TEST_PASSED;
					BaseUtil.log("pass");
				} else {
					note = case095.getMsg();
				}

			} else {
				result = TestLinkAPIResults.TEST_FAILED;
				note = case095.getMsg();
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
			System.out.println("Consultations_095: Execution Terminated at "
					+ dt);
		}
		driver.quit();
		Thread.sleep(3000);
	}

	private void openEncounterWin() {
		homePageWin = action.getCurrentHandle();
		action.click(By.linkText("Search"));
		search=action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(search);
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
		action.click(By.linkText("Demogp' No"));
		action.click(By.linkText("E"));
		action.driverWait(10);
		//action.closeCurrentWindow();
		encounterWin = action.getNewPopupWinHdl(homePageWin,search);
		action.selectWindow(encounterWin);
	}

	private boolean consultationIsPresent() {
		if (action.isElementPresent(By.id("consultation"))) {
			return true;
		} else {
			msg = " | 'consultations' doesn't exist from the right navigator.";
			return false;
		}
	}

	private boolean canOpenAddConWin() {

		if (consultationIsPresent()) {
			action.driverWait(20);
			if (action.isElementPresent(action.getWebElement(By.id("menuTitleconsultation")),By.linkText("+"))) {
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
				}
				
				action.click(
						action.getWebElement(By.id("menuTitleconsultation")),
						By.linkText("+"));
				String addConWin = action.getNewPopupWinHdl(homePageWin,search,
						encounterWin);
				if (addConWin != null) {
					action.selectWindow(addConWin);
					return true;

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

	private void addCon() {

		action.selectByVisibleText(By.id("service"), "Radiology");
		action.click(By.name("submitSaveOnly"));
		action.driverWait(3000);
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
	}

	private boolean verifyListAndLinksArePresent() {

		if (action.isElementPresent(By.id("consultationlist"))) {
			WebElement list = action.getWebElement(By.id("consultationlist"));
			if (action.isElementPresent(list, By.className("links"))) {
				return true;
			} else {
				msg = " | No consultation link exists on Encounter page.";
				return false;
			}
		} else {
			msg = " | No consultation list exists on Encounter page.";
			return false;
		}
	}

	private boolean verifyCanOpenViewConWin() {

		boolean isOk = false;
		if (verifyListAndLinksArePresent()) {
			

			   try {
					Thread.sleep(2000);
					} catch (InterruptedException e) {	
							}

			action.click(action.getWebElement(By.id("consultationlist")),
					By.className("links"));
			String conRequestWin = action.getNewPopupWinHdl(homePageWin,search,
					encounterWin);
			if (conRequestWin != null) {
				action.selectWindow(conRequestWin);
				action.driverWait(10);
				String actualPageTitle = action.getPageTitle();
                BaseUtil.log(actualPageTitle);
                BaseUtil.log(expectedPageTitle1);
				if (actualPageTitle.trim().compareTo(expectedPageTitle1)==0) {
					isOk = true;
				} else {
					msg = " | Please make sure users can view consultation by clicking consultation link.";
				}
				action.closeCurrentWindow();
				action.selectWindow(encounterWin);
			} else {
				msg = " | No window will pop up by clicking consultation link.";
			}
		}
		return isOk;
	}

	private boolean verifyCanOpenConListWin() {
		boolean isOk = false;
		if (action.isElementPresent(By.linkText("Consultations"))) {
			action.click(By.linkText("Consultations"));
			String conListWin = action.getNewPopupWinHdl(homePageWin,search,
					encounterWin);
			if (conListWin != null) {
				action.selectWindow(conListWin);
				String actualPageTitle = action.getPageTitle();
				if (actualPageTitle.contains(expectedPageTitle2)) {
					if (action.isElementPresent(By.linkText("Radiology"))) {
						isOk = true;
					} else {
						msg = msg
								+ " | No consultation link exists on consultation list window.";
					}
				} else {
					msg = msg
							+ " | Please make sure the open page is 'consultation List' window. ";
				}
			} else {
				msg = msg
						+ " | Cannot open consultation list window by clicking 'consultation' link.";
			}
		} else {
			msg = msg + " | No 'consultation' link exists on encounter page.";
		}
		return isOk;
	}

	private String getMsg() {
		return msg;
	}
}
