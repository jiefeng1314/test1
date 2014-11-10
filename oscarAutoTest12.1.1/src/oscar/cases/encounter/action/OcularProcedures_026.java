package encounter.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import testlink.api.java.client.TestLinkAPIResults;
import com.oscarbase.*;

public class OcularProcedures_026 {

	private String homePageWin = "";
	private String patientSearchWin="";
	private String encounterWin = "";
	private String addOcularProdWin ="";
	private String msg = "";
	private static String pDate;
	private static String eyeType;
	private static String procedureName;
	private static String doctorName;
	private static String location;
	private static String createDate;
	private static String procedureNotes;
	public static WebDriver driver =null;
	public static WebDriverActions action = null;
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy026");
		String testcaseName = p.getProperty("TestcaseName");
		eyeType = p.getProperty("EyeType").trim();
		procedureName = p.getProperty("ProcedureName").trim();
		doctorName = p.getProperty("DoctorName").trim();
		location = p.getProperty("Location").trim();
		procedureNotes = p.getProperty("ProcedureNotes").trim();
        createDate=p.getProperty("Date").trim();
		
		String result = "";
		String note = "";
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		pDate = df.format(dt);

		try {
			OcularProcedures_026 case091 = new OcularProcedures_026();
			By091_VerifyAllEsArePresent onAddOcularProdWin = new By091_VerifyAllEsArePresent(
					driver);
			LoginOscar.login(driver);
			case091.openEncounterWin();
			if (case091.canOpenAddOcularProdWin()) {
				  BaseUtil.log("enter 1");
				if (onAddOcularProdWin.verify()) {
					  BaseUtil.log("enter 2");
					case091.addOcularProd();
					 BaseUtil.log("enter 3");
					if (case091.verifyAddContent() && case091.verifyAddIsOk()) {
						BaseUtil.log("pass");
						result = TestLinkAPIResults.TEST_PASSED;
					} else {
						result = TestLinkAPIResults.TEST_FAILED;
						note = case091.getMsg();
					}
				} else {
					result = TestLinkAPIResults.TEST_FAILED;
					note = onAddOcularProdWin.getMsg();
				}
			} else {
				result = TestLinkAPIResults.TEST_FAILED;
				note = case091.getMsg();
			}

			BaseUtil.log(note);
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();
		} finally {
			TestExecutionResult.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, note, result);
			System.out.println("OcularProcedures_026: Execution Terminated at "
					+ dt);
		}
		driver.quit();
		Thread.sleep(3000);
	}

	private void openEncounterWin() {
		homePageWin = action.getCurrentHandle();
		action.click(By.linkText("Search"));
		patientSearchWin = action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(patientSearchWin );
		action.type(By.xpath("//input[@name='keyword']"), demogName);
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
		action.click(By.linkText("Demogp' No"));
		action.click(By.linkText("E"));
		action.driverWait(10);
		encounterWin = action.getNewPopupWinHdl(homePageWin,patientSearchWin);
		action.selectWindow(encounterWin);
	}

	private boolean isOcularProdPresent() {
		if (action.isElementPresent(By.id("ocularprocedure"))) {
			return true;
		} else {
			msg = msg+" | 'Ocular Procedure' doesn't exist from the right navigator.";
			return false;
		}
	}

	private boolean canOpenAddOcularProdWin() {

		if (isOcularProdPresent()) {
			if (action.isElementPresent(
					action.getWebElement(By.id("menuTitleocularprocedure")),
					By.linkText("+"))) {

				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
	
				}
				action.click(
						action.getWebElement(By.id("menuTitleocularprocedure")),
						By.linkText("+"));
				
				addOcularProdWin=action.getNewPopupWinHdl(homePageWin,patientSearchWin,encounterWin);
				if (addOcularProdWin != null) {
					action.selectWindow(addOcularProdWin);
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

	private void addOcularProd() {

		action.type(By.id("pdate"), createDate);
		action.selectByVisibleText(By.name("proc.eye"), eyeType);
		action.type(By.name("proc.procedureName"), procedureName+pDate);
		action.selectByValue(By.name("proc.doctor"), doctorName);
		action.type(By.name("proc.location"), location);
		action.type(By.name("proc.procedureNote"), procedureNotes);
		action.click(By.xpath("//input[@type='submit'][@value='Submit']"));
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
	}

	private boolean verifyAddContent() {
		boolean canEdit = false;
		if (action.isElementPresent(By.id("ocularprocedurelist"))) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			
			if(action.isElementPresent(By.xpath("//img[@id='imgocularprocedure5']")))
			{
				action.click(By.xpath("//img[@id='imgocularprocedure5']"));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}
			//action.move(By.xpath("//ul[@id='ocularprocedurelist']//a[contains(text(),'"+eyeType+" "+procedureName+pDate+"')]"));
			action.click(action.getWebElement(By.id("ocularprocedurelist")),By.xpath("//a[contains(text(),'"+eyeType+" "+procedureName+pDate+"')]"));
			//action.click(By.xpath("//ul[@id='ocularprocedurelist']//a[contains(text(),'"+eyeType+" "+procedureName+pDate+"')]"));
			String editWin = action.getNewPopupWinHdl(homePageWin, encounterWin,patientSearchWin);
			if (editWin != null) {
				
				action.selectWindow(editWin);
				//this is Exception Handling
				if(!action.isElementPresent(By.xpath("//input[@id='pdate']")))
				{
				  String check=action.getNewPopupWinHdl(homePageWin, encounterWin,patientSearchWin,editWin);
				  BaseUtil.log("come into check win .");
				  action.closeCurrentWindow();
				  action.selectWindow(check);
				}
				
				String date=action.getWebElement(By.xpath("//input[@id='pdate']")).getAttribute("value").trim();
				String type=action.getWebElement(By.xpath("//select[@name='proc.eye']")).getAttribute("value").trim();
				String procedure=action.getWebElement(By.xpath("//input[@name='proc.procedureName']")).getAttribute("value").trim();
				String doctorname=action.getWebElement(By.xpath("//select[@name='proc.doctor']")).getAttribute("value").trim();
				String Location=action.getWebElement(By.xpath("//input[@name='proc.location']")).getAttribute("value").trim();
				String note=action.getWebElement(By.xpath("//textarea")).getAttribute("value").trim();
			
				BaseUtil.log(date+"*"+type+"*"+procedure+"*"+doctorname+"*"+Location+"*"+note+"*");
				
				if (date.equals(createDate)&&type.equals(eyeType)&&procedure.equals(procedureName+pDate)&&doctorname.equals(doctorName)&&Location.equals(location)&&note.equals(procedureNotes)) {
					canEdit = true;
				} else {
					msg = msg+ " | the content is error.";
				}
				action.closeCurrentWindow();
				action.selectWindow(encounterWin);
			} else {
				msg =  msg+" | No edit window will pop up by clicking ocualr procedure list.";
			}

		} else {
			msg =  msg+" | No ocular procedure list exists on Encounter page.";
		}
		return canEdit;
	}

	private boolean verifyAddIsOk() {
		boolean isAddOk = true;
		if (action.isElementPresent(By.linkText("Ocular Procedures"))) {
			 
			try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
	
				}
			
			action.click(By.linkText("Ocular Procedures"));
			String ocularProdHistoryWin = action.getNewPopupWinHdl(homePageWin,patientSearchWin,
					encounterWin);
			if (ocularProdHistoryWin != null) {

				action.selectWindow(ocularProdHistoryWin);

				if(!action.isElementPresent(By.xpath("//table//td[@class='MainTableRightColumn']//table//tr//td[contains(text(),'"+procedureName+pDate+"')]")))
				{
					
					isAddOk = false;
					msg =  msg+" | error,this OcularProcedures not exist";
				}
				
				

			} else {
				isAddOk = false;
				msg =  msg+" | No window will pop up by clicking 'Ocular Procedure' link.";
			}
		} else {
			isAddOk = false;
			msg =  msg+" | 'Ocular Procedures' link doesn't exist.";
		}
		return isAddOk;
	}

	private String getMsg() {
		return msg;
	}
}
