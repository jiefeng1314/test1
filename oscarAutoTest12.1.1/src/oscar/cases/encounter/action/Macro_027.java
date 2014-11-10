package encounter.action;

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

public class Macro_027 {

	private String homePageWin = "";
	private String searchwin = "";
	private String encounterWin = "";
	private String msg = "";
	private static String label = "";
	private static String displayOrder = "";
	private static String impressionText = "";
	private static String followup = "";
	private static String bookTest = "";
	private static String sendNumber="";
	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static Date dt = new Date();
	private static String demogName=CaseConf.getInstance().getCreateAppointmentDemogName();

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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy027");
		String testcaseName = p.getProperty("TestcaseName");
		label = p.getProperty("Label");
		displayOrder = p.getProperty("DisplayOrder");
		impressionText = p.getProperty("ImpressionText");
		followup = p.getProperty("Followup");
		bookTest = p.getProperty("BookTest");
		sendNumber=p.getProperty("sendNumber");
		String result = "";
		String note = "";
		

		try {
			Macro_027 case096 = new Macro_027();
			By096_VerifyAllEsArePresent onAddMacroPage = new By096_VerifyAllEsArePresent(
					driver);
			LoginOscar.login(driver);
			case096.openEncounterWin();

			if (case096.canOpenAddMacroWin()) {
				BaseUtil.log("enter 1");
				if (onAddMacroPage.verify()) {
					BaseUtil.log("enter 2");
					case096.addMacro();
					BaseUtil.log("enter 3");

					if (!case096.verifyListAndLinksArePresent()) {
						BaseUtil.log("enter error1");
						result = TestLinkAPIResults.TEST_FAILED;
					}

					if (case096.verifyCanOpenMacroListWin()) {
						BaseUtil.log("enter 4");
						if (case096.deleteMacro()) {
							BaseUtil.log("enter 5");
							if (!case096.verfiyMacroIsDeleted()) {
								BaseUtil.log("enter error2");
								result = TestLinkAPIResults.TEST_FAILED;
							}
						} else {
							result = TestLinkAPIResults.TEST_FAILED;
						}
					} else {
						result = TestLinkAPIResults.TEST_FAILED;
					}

					if (result.isEmpty()) {
						BaseUtil.log("pass");
						result = TestLinkAPIResults.TEST_PASSED;
					} else {
						note = case096.getMsg();
					}

				} else {
					result = TestLinkAPIResults.TEST_FAILED;
					note = case096.getMsg();
				}
			} else {
				result = TestLinkAPIResults.TEST_FAILED;
				note = onAddMacroPage.getMsg();
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
			System.out.println("Macro_027: Execution Terminated at " + dt);
		}
		driver.quit();
		Thread.sleep(3000);
	}

	private void openEncounterWin() {
		homePageWin = action.getCurrentHandle();
		action.click(By.linkText("Search"));
		searchwin=action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(searchwin);
		action.type(By.xpath("//input[@name='keyword']"), demogName);
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
		action.click(By.linkText("Demogp' No"));
		action.click(By.linkText("E"));
		action.driverWait(10);
		encounterWin = action.getNewPopupWinHdl(homePageWin,searchwin);
		action.selectWindow(encounterWin);
	}

	private boolean macroIsPresent() {
		if (action.isElementPresent(By.id("macro"))) {
			return true;
		} else {
			msg = msg+" | 'Macro' doesn't exist from the right navigator.";
			return false;
		}
	}

	private boolean canOpenAddMacroWin() {

		if (macroIsPresent()) {
			try {
				Thread.sleep(1000);
							} catch (InterruptedException e) {}
							
			if (action.isElementPresent(
					action.getWebElement(By.id("menuTitlemacro")),
					By.linkText("+"))) {

				try {
					Thread.sleep(1500);
						} catch (InterruptedException e) {	}
						
				action.click(action.getWebElement(By.id("menuTitlemacro")),
						By.linkText("+"));
				String addMacroWin = action.getNewPopupWinHdl(homePageWin,searchwin,
						encounterWin);
				if (addMacroWin != null) {
					action.selectWindow(addMacroWin);
					return true;

				} else {
					msg =  msg+" | No window will pop up by clicking '+' link.";
					return false;
				}
			} else {
				msg =  msg+" | Link '+' is not present";
				return false;
			}
		} else {
			return false;
		}
	}

	private void addMacro() {

		action.type(By.name("macro.label"), label+dt);
		action.type(By.name("macro.displayOrder"), displayOrder);
		action.type(By.name("macro.impression"), impressionText);
		action.type(By.name("macro.followupNo"), followup);
		action.type(By.name("macro.testRecords"), bookTest);
		action.selectByValue(By.name("macro.ticklerRecipient"), sendNumber);
		action.click(By.xpath("//input[@type='submit'][@value='Save']"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		
		}
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
	}

	private boolean verifyListAndLinksArePresent() {

		if (action.isElementPresent(By.id("macrolist"))) {
			WebElement list = action.getWebElement(By.id("macrolist"));
			if(action.isElementPresent(By.xpath("//ul[@id='macrolist']//span//a[contains(text(),'"+label+dt+"')]")))
			{  
				return true;
			
			} else {
				msg =  msg+" | No macro link exists on Encounter page.";
				return false;
			}
		} else {
			msg = msg+ " | No macro list exists on Encounter page.";
			return false;
		}
	}

	

	private boolean verifyCanOpenMacroListWin() {
		boolean isOk = false;
		
		if (action.isElementPresent(By.linkText("Macro"))) {
			try {
				Thread.sleep(2000);
							} catch (InterruptedException e) {}
			action.click(By.linkText("Macro"));
			String macroListWin = action.getNewPopupWinHdl(homePageWin,searchwin,
					encounterWin);
			if (macroListWin != null) {
				action.selectWindow(macroListWin);
				
				if (action.isElementPresent(By.id("macro"))) {
					
					
					if (action.isElementPresent(By.xpath("//table[@id='macro']//tr//td//a[contains(text(),'"+label+dt+"')]"))) {
						isOk = true;
					} else {
						msg = msg
								+ " | It seems no macro was listed on macro list window.";
					}
				} else {
					msg = msg
							+ " | Please make sure the open window is marco list window.";
				}

			} else {
				msg = msg + " | No window pops up by clicking macro link.";
			}
		} else {
			msg = msg + " | No 'Macro' link exists on encounter page.";
		}
		return isOk;
	}

	private boolean deleteMacro() {
		if (action.isElementPresent(By.xpath("//input[@type='button'][@value='Delete']"))) {

			try {
		            Thread.sleep(1000);
							} catch (InterruptedException e) {	}
			action.click(By.xpath("//table[@id='macro']//td/a[contains(text(),'"+label+dt+"')]/../preceding-sibling::td/input[@type='checkbox']"));
			action.click(By.xpath("//input[@type='button'][@value='Delete']"));
			action.accept();
			
			
			return true;
		} else {
			msg = msg + " | No 'Delete' button exists.";
			return false;
		}
	}

	private boolean verfiyMacroIsDeleted() {
		
		boolean isOk=true;
		if (action.isElementPresent(By.xpath("//table[@id='macro']//tr//td//a[contains(text(),'"+label+dt+"')]"))) {
			isOk = false;
			msg = msg
			+ " | after delete .still exist";
		  }
		
		
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
		try {
			Thread.sleep(2000);
			} catch (InterruptedException e) {}
			
			List<WebElement> elements=action.getWebElements(action.getWebElement(By.id("macrolist")),
					By.className("links"));
			
			for(WebElement element:elements)
			{
			if ((label+dt).equals(element.getText())) {
				
				msg = msg + " | Macro wasn't deleted from left navigator.";
				isOk=false;
			} 
			}
			
			return isOk;
			
		
	}

	private String getMsg() {
		return msg;
	}
}
