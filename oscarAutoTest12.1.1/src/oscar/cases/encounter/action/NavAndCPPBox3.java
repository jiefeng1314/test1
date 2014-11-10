package encounter.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import testlink.api.java.client.TestLinkAPIResults;
import com.oscarbase.*;

public class NavAndCPPBox3 {
  
	private String homePageWin = "";
	private String patientSearchWin = "";
	private String encounterWin= "";
	private String expectedLogContent= "";
	private String startDate = "";
	private String resolutionDate = "";
	private String ageAtOnset = "";
	private String relationship = "";
	private String treatment = "";
	private String lifeStage="";
	
	private String updateexpectedLogContent= "";
	private String updateStartDate = "";
	private String updateResolutionDate = "";
	private String updateRelationship = "";
	private String updateTreatment="";
	private String updateLifeStage="";
	private String updateAgeAtOnset="";
	
	private String msg = "";
	private String pdate = "";
	private WebDriver driver =null;
	private WebDriverActions action = null;
	private static  String createAppointmentDemogName=CaseConf.getInstance().getCreateAppointmentDemogName();

	private int number=0;
	public void start(String testcaseName, String navOrCppId,
			String navOrCppLinkText) throws Exception {
		
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy001_008");
		
		expectedLogContent= p.getProperty("ExpectedLogContent");
		startDate = p.getProperty("StartDate");
		resolutionDate = p.getProperty("ResolutionDate");
		ageAtOnset = p.getProperty("AgeAtOnset");
		relationship=p.getProperty("Relationship");
		treatment = p.getProperty("Treatment");
		lifeStage=p.getProperty("lifeStage");
		
		updateexpectedLogContent=p.getProperty("updateExpectedLogContent");
		updateStartDate = p.getProperty("updateStartDate");
		updateResolutionDate = p.getProperty("updateResolutionDate");
		updateRelationship = p.getProperty("updateRelationship");
		updateTreatment = p.getProperty("updateTreatment");
		updateLifeStage = p.getProperty("updateLifeStage");
		updateAgeAtOnset=p.getProperty("updateAgeAtOnset");
		
		

		String result = "";
		String note = "";
		Date dt = new Date();
		
		SimpleDateFormat forma=new SimpleDateFormat("yyyyMMddhhmmss");
		pdate=forma.format(dt);

		try {

			VerifyElementsOnNotesBox3 navOrCppNotesBox = new VerifyElementsOnNotesBox3(
					driver);

			LoginOscar.login(driver);
			openPatientSearchWin();
			Thread.sleep(2000);
			openEncounterWin();
			Thread.sleep(7000);	
			number=countbefore(navOrCppLinkText.trim());
			
			if (isNavOrCppPresent(navOrCppId.trim()) && canOpenNavOrCppBox(navOrCppId.trim())) {
				     BaseUtil.log("enter 1");
				if (navOrCppNotesBox.verify()) {
					BaseUtil.log("enter 2");
					addNewLog();
					BaseUtil.log("enter 3");
					if (verifyAddSuccessfully(navOrCppLinkText.trim(),navOrCppId.trim())) {
						
						 BaseUtil.log("enter 4");
						   if(update(navOrCppId.trim()))
						   {
							   BaseUtil.log("enter 5");
							   if(updateSuccess(navOrCppLinkText.trim(),navOrCppId.trim()))
							   {   
								    BaseUtil.log("pass");
								    result = TestLinkAPIResults.TEST_PASSED;
								    
							   }else {
								   result = TestLinkAPIResults.TEST_FAILED;
									note = getMsg();
							}
						   }else {
							   result = TestLinkAPIResults.TEST_FAILED;
								note = getMsg();
						}
						
						   
					} else {
						result = TestLinkAPIResults.TEST_FAILED;
						note = getMsg();
					}
				} else {
					note = navOrCppNotesBox.getMsg();
					result = TestLinkAPIResults.TEST_FAILED;
				}
			} else {
				result = TestLinkAPIResults.TEST_FAILED;
				note = getMsg();

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
					.println(testcaseName + ": Execution Terminated at " + dt);
		}
		driver.quit();
		Thread.sleep(3000);
	}

	private void openPatientSearchWin() {
		homePageWin = action.getCurrentHandle();
		action.click(By.linkText("Search"));
		patientSearchWin = action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(patientSearchWin);
		action.type((By.xpath("//input[@name='keyword'and @type='text']")),createAppointmentDemogName);
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
		action.click(By.linkText("Demogp' No"));
	}

	private void openEncounterWin() {
		action.click(By.linkText("E"));
		action.driverWait(30);
		encounterWin = action.getNewPopupWinHdl(homePageWin,
				patientSearchWin);
		action.selectWindow(encounterWin);
	}

	private boolean isNavOrCppPresent(String navOrCppId) {
		if (action.isElementPresent(By.id(navOrCppId))) {
			return true;
		} else {
			msg = " | This navigator link or cpp link is not present.";
			return false;
		}
	}

	private boolean canOpenNavOrCppBox(String navOrCppId) {
		if (action.isElementPresent(action.getWebElement(By.id(navOrCppId)),
				By.linkText("+"))) {
		action.click(action.getWebElement(By.id(navOrCppId)), By.linkText("+"));
		String isBoxVisible = action.getValue(By.id("showEditNote"), "style");
		if (isBoxVisible.contains("display: table")) {
			action.driverWait(30);
			return true;
		} else {
			msg = msg + " | Cannot open this navigator or CPP note box.";
			return false;
		}
		}else{
			msg = msg + " | Link '+' is not present";
			return false;
		}
	}

	private void addNewLog() {
		action.type(By.id("noteEditTxt"), expectedLogContent+pdate);
		action.type(By.id("startdate"), startDate);
		action.type(By.id("resolutiondate"), resolutionDate);
		action.type(By.id("ageatonset"), ageAtOnset);
		action.type(By.id("relationship"), relationship);
		action.type(By.id("treatment"), treatment);
		action.selectByVisibleText(By.xpath("//select[@id='lifestage']"), lifeStage);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		}
		action.click(By
				.xpath("//input[@type='image'][@title='Sign, Save and Exit'][ @style='padding-right:10px;']"));
	}

	private boolean verifyAddSuccessfully(String navOrCppLinkText,String navOrCppId) {
		boolean canAdd = false;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		List<WebElement> allcontents = action.getWebElements(By.xpath("//div[@id='"+navOrCppId+"']//ul/li/span/a"));
		BaseUtil.log(allcontents.size()+"*"+allcontents.get(0).getText().trim());
		if(allcontents.size()==(number+1)&&allcontents.get(0).getText().trim().equals(expectedLogContent+pdate))
		{
		
		action.click(By.linkText(navOrCppLinkText));
		String fimalyWin = action.getNewPopupWinHdl(homePageWin,
				patientSearchWin,encounterWin);
		action.selectWindow(fimalyWin);
		List<WebElement> allLogs = driver.findElements(By.xpath("//div/pre"));
		

		String title=action.getText(By.xpath("//h3"));

				BaseUtil.log(allLogs.size()+"*"+title);
				BaseUtil.log(""+number);
		for (WebElement log : allLogs) {
			String actualLogContent = log.getText();
			if ((actualLogContent.trim()).compareTo(expectedLogContent+pdate) == 0&&title.contains(navOrCppLinkText)&&allLogs.size()==(number+1)) {
				canAdd = true;
				break;
			}
		}
		}else {
			msg=msg+"|add after,the count not right";
		}
			if (!canAdd) {
				msg = msg+" | Cannot add new log successfully.";
			}
			action.closeCurrentWindow();
			return canAdd;
	}
	
	private int  countbefore(String navOrCppLinkText) {
		   int number=0;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			action.click(By.linkText(navOrCppLinkText));
			String fimalyWin = action.getNewPopupWinHdl(homePageWin,
					patientSearchWin,encounterWin);
			action.selectWindow(fimalyWin);

			List<WebElement> allLogs = action.getWebElements(By.tagName("pre"));
		    number=allLogs.size();
		    try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			action.closeCurrentWindow();
			action.selectWindow(encounterWin);
			return number;
		}
	
	public  boolean update(String navOrCppId)
	{
		boolean flag=false;
		
		action.selectWindow(encounterWin);
		action.click(By.xpath("//div[@id='"+navOrCppId+"']//ul/li/span/a"));
		
		String content=action.getValue(By.xpath("//textarea[@id='noteEditTxt']"), "value");
		String sDate=action.getValue(By.xpath("//input[@id='startdate']"), "value");
		String endDate=	action.getValue(By.xpath("//input[@id='resolutiondate']"), "value");
		String Treatment=action.getValue(By.xpath("//input[@id='treatment']"), "value");
		String rship=action.getValue(By.xpath("//input[@id='relationship']"), "value");
		String age=action.getValue(By.xpath("//input[@id='ageatonset']"), "value");
		
		
		//String lStage=action.getText(By.xpath("//select[@id='lifestage']"));
		Select select = new Select(driver.findElement(By.xpath("//select[@id='lifestage']")));
		WebElement element=select.getFirstSelectedOption();
		String lStage=element.getText();
	
		BaseUtil.log(content+"*"+sDate+"*"+endDate+"*"+rship+"*"+Treatment+"*"+lStage+"*"+age);
		BaseUtil.log(""+content.trim().equals(expectedLogContent+pdate));
		BaseUtil.log(""+sDate.trim().equals(startDate.trim()));
		BaseUtil.log(""+endDate.trim().equals(resolutionDate.trim()));
		BaseUtil.log(""+age.trim().equals(ageAtOnset.trim()));
		BaseUtil.log(""+Treatment.trim().equals(treatment));
		BaseUtil.log(""+lStage.trim().equals(lifeStage.trim()));
		BaseUtil.log(""+rship.trim().equals(relationship.trim()));
	
		if(content.trim().equals(expectedLogContent+pdate.trim())&&sDate.trim().equals(startDate.trim())&&endDate.trim().equals(resolutionDate.trim())&&age.trim().equals(ageAtOnset.trim())&&Treatment.trim().equals(treatment.trim())&&lStage.trim().equals(lifeStage.trim())&&rship.trim().equals(relationship.trim()))
		{
			flag=true;
		}else {
			msg=msg+"The last time the contents is error";
		}
		action.clear(By.xpath("//textarea[@id='noteEditTxt']"));
		action.type(By.xpath("//textarea[@id='noteEditTxt']"), updateexpectedLogContent+pdate);
		
		action.clear(By.xpath("//input[@id='startdate']"));
		action.type(By.xpath("//input[@id='startdate']"), updateStartDate);
		
		action.clear(By.xpath("//input[@id='resolutiondate']"));
		action.type(By.xpath("//input[@id='resolutiondate']"), updateResolutionDate);
		
		action.clear(By.xpath("//input[@id='ageatonset']"));
		action.type(By.xpath("//input[@id='ageatonset']"), updateAgeAtOnset);
		
		action.clear(By.xpath("//input[@id='treatment']"));
		action.type(By.xpath("//input[@id='treatment']"), updateTreatment);
		
		
		action.clear(By.xpath("//input[@id='relationship']"));
		action.type(By.xpath("//input[@id='relationship']"), updateRelationship);
		
		action.selectByVisibleText(By.xpath("//select[@id='lifestage']"), updateLifeStage);
		
		action.click(By
				.xpath("//input[@type='image'][@title='Sign, Save and Exit'][ @style='padding-right:10px;']"));
		
		action.refreshCurrentWindow();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
		}
		return flag;
	}
	
	public  boolean updateSuccess(String navOrCppLinkText,String navOrCppId)
	{
		boolean flag=true;
		boolean canAdd=false;
        action.click(By.xpath("//div[@id='"+navOrCppId+"']//ul/li/span/a"));
		
		String content=action.getValue(By.xpath("//textarea[@id='noteEditTxt']"), "value");
		String sDate=action.getValue(By.xpath("//input[@id='startdate']"), "value");
		String endDate=	action.getValue(By.xpath("//input[@id='resolutiondate']"), "value");
		String Treatment=action.getValue(By.xpath("//input[@id='treatment']"), "value");
		String rship=action.getValue(By.xpath("//input[@id='relationship']"), "value");
		String age=action.getValue(By.xpath("//input[@id='ageatonset']"), "value");
		Select select = new Select(driver.findElement(By.xpath("//select[@id='lifestage']")));
		//String lSatge=action.getText(By.xpath("//select[@id='lifestage']")); 
		WebElement element=select.getFirstSelectedOption();
		String lSatge=element.getText();
		
		BaseUtil.log(content+"*"+sDate+"*"+endDate+"*"+rship+"*"+Treatment+"*"+lSatge+"*"+age);
		if(!(content.trim().equals(updateexpectedLogContent+pdate.trim())&&sDate.trim().equals(updateStartDate.trim())&&endDate.trim().equals(updateResolutionDate.trim())&&age.trim().equals(updateAgeAtOnset.trim())&&Treatment.trim().equals(updateTreatment.trim())&&lSatge.trim().equals(updateLifeStage.trim())&&rship.trim().equals(updateRelationship.trim())))
		{
			flag=false;
			msg=msg+"The update the contents is error";
		}
		action.click(By
				.xpath("//input[@type='image'][@title='Sign, Save and Exit'][ @style='padding-right:10px;']"));
		
	
		List<WebElement> allcontents = action.getWebElements(By.xpath("//div[@id='"+navOrCppId+"']//ul/li/span/a"));
		BaseUtil.log(allcontents.size()+"*"+allcontents.get(0).getText().trim());
		if(allcontents.size()==(number+1)&&allcontents.get(0).getText().trim().equals(updateexpectedLogContent+pdate))
		{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		action.click(By.linkText(navOrCppLinkText));
		String fimalyWin = action.getNewPopupWinHdl(homePageWin,
				patientSearchWin,encounterWin);
		action.selectWindow(fimalyWin);
		String title=action.getText(By.xpath("//h3"));
		List<WebElement> allLogs = action.getWebElements(By.tagName("pre"));
				
		BaseUtil.log(allLogs.size()+"*"+title);
		BaseUtil.log(""+number);
		for (WebElement log : allLogs) {
			String actualLogContent = log.getText();
			if ((actualLogContent.compareTo(updateexpectedLogContent+pdate) == 0&&title.contains(navOrCppLinkText.split(" ")[0])&&allLogs.size()==(number+1))) {
				canAdd = true;
				break;
			}
		}
	}else {
		msg=msg+"update after,count not rigth";
	}
		
		if(!canAdd)
		{
			flag=false;
			msg=msg+"| update after ,but the list not update";
		}
		
		return flag;
	}
	private String getMsg() {
		return msg;
	}
}
