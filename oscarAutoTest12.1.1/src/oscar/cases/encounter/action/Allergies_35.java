package encounter.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import testlink.api.java.client.TestLinkAPIResults;
import com.oscarbase.*;

public class Allergies_35 {

	private static String homePageWin = "";
	private static String encounterWin="";
	private static 	String addAllergiesWin="";
	private static  String [] wins=new String[2];
	private static String msg = "";
	private static String expectedDate;
	private static String date;
	private static String searchName;
	private static String searchResult;
	private static String ageOfOnset;
	private static String lifeStage;
	private static String severityOfReaction;
	private static String content;
	private static String note;
	private static String onSetOfReaction;
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy035");
		String testcaseName = p.getProperty("TestcaseName").trim();
		 searchName = p.getProperty("searchName").trim();
		 searchResult = p.getProperty("searchResult").trim();
		 content = p.getProperty("content").trim();
		 ageOfOnset = p.getProperty("ageOfOnset").trim();
		 lifeStage = p.getProperty("lifeStage").trim();
		 severityOfReaction = p.getProperty("severityOfReaction").trim();
		 onSetOfReaction = p.getProperty("onSetOfReaction").trim();
		 note = p.getProperty("note").trim();

		String result = "";
		String note = "";
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");
		expectedDate = df.format(dt);
		date=df1.format(dt);
		LoginOscar.login(driver);
		wins=createAppointment.create(action);
		homePageWin=wins[0];
		msg=msg+wins[1];
		
		wins=createAppointment.openEncounter(action, demogName);
		encounterWin=wins[0];
		msg=msg+wins[1];
		 Thread.sleep(8000);
		try {
			Allergies_35 case089 = new Allergies_35();
			if(canOpenAddAllergiesWin()){
				 BaseUtil.log("enter 1");
				case089.addAllergies();
				 BaseUtil.log("enter 2");
					if (case089.verify()) {
						BaseUtil.log("enter 3");
						addAnnotation();
						BaseUtil.log("enter 4");
						if(verifyAnnotation())
						{
							BaseUtil.log("enter 5");
						if(deleteAndVerify())
						{
						result = TestLinkAPIResults.TEST_PASSED;
						BaseUtil.log("pass");
						}else {
							result = TestLinkAPIResults.TEST_FAILED;
							note = case089.getMsg();
						 }
						}
						else {
							result = TestLinkAPIResults.TEST_FAILED;
							note = case089.getMsg();
						}
					} else {
						result = TestLinkAPIResults.TEST_FAILED;
						note = case089.getMsg();
					}
			
			}else {
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
					.println("Allergies_035: Execution Terminated at "
							+ dt);
		}
		driver.quit();
	//	Thread.sleep(3000);
	}

	
	
	
	private static boolean isAllergiesPresent() {
		if (action.isElementPresent(By.id("allergies"))) {
			return true;
		} else {
			msg = msg+" | 'Allergies' doesn't exist from the right navigator.";
			return false;
		}
	}

	private static boolean canOpenAddAllergiesWin() {

		if (isAllergiesPresent()) {
			if (action.isElementPresent(
					action.getWebElement(By.id("menuTitleallergies")),
					By.linkText("+"))) {

				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
	
				}
				action.click(
						action.getWebElement(By.id("menuTitleallergies")),
						By.linkText("+"));
				
				addAllergiesWin=action.getNewPopupWinHdl(homePageWin,encounterWin);
				if (addAllergiesWin != null) {
					action.selectWindow(addAllergiesWin);
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

	
	private static void addAllergies()
	{
		action.click(By.xpath("//input[@value='Select All']"));
		action.clear(By.xpath("//input[@id='searchString']"));
		action.type(By.xpath("//input[@id='searchString']"), searchName);
		
		action.click(By.xpath("//input[@value='Search']"));
		
		action.click(By.xpath("//a[contains(text(),'"+searchResult+"')]"));
		
		action.type(By.xpath("//textarea"), content+date);
		
		action.type(By.xpath("//input[@name='startDate']"), expectedDate);
		
		action.type(By.xpath("//input[@name='ageOfOnset']"), ageOfOnset);
		
		action.selectByValue(By.xpath("//select[@name='lifeStage']"), lifeStage);
		
		action.selectByValue(By.xpath("//select[@name='severityOfReaction']"), severityOfReaction);
		
		action.selectByValue(By.xpath("//select[@name='onSetOfReaction']"), onSetOfReaction);
		
		action.click(By.xpath("//input[@value='Add Allergy']"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		action.closeCurrentWindow();
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
		}
		
		
		
	}

	private static boolean verify()
	{
		boolean  flag=true;
		if(!action.isElementPresent(By.xpath("//ul[@id='allergieslist']//a[contains(text(),'"+searchResult+"')]")))
		{
			flag=false;
			msg=msg+"| add not success ,the allergieslist not exist this allergie";
			
		}
		
		if(action.isElementPresent(By.xpath("//div[@id='allergies']//a[contains(text(),'Allergies')]")))
		{
			action.click(By.xpath("//div[@id='allergies']//a[contains(text(),'Allergies')]"));
			
			addAllergiesWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
			action.selectWindow(addAllergiesWin);
			
			if(!(action.isElementPresent(By.xpath("//table[@class='allergy_table']//tr//td[contains(text(),'"+searchResult+"')]"))&&action.isElementPresent(By.xpath("//table[@class='allergy_table']//tr//td[contains(text(),'"+content+date+"')]"))&&action.isElementPresent(By.xpath("//table[@class='allergy_table']//tr//td[contains(text(),'"+expectedDate+"')]"))))
			{
				msg=msg+"| the Allergies view not exist this Allergies ";
				flag=false;
			}
			
			
		}else {
			msg=msg+"| not exist Allergies  this link";
			flag=false;
		}
		
		
		return flag;
		
	}
	
	
	private static void addAnnotation(){
		

		action.click(By.xpath("//table[@class='allergy_table']//tr//td[contains(text(),'"+content+date+"')]/following-sibling::td//a//img"));
		
		String annotationWin=action.getNewPopupWinHdl(homePageWin, encounterWin,addAllergiesWin);
		
		action.selectWindow(annotationWin);
		
		action.clear(By.xpath("//textarea[@name='note']"));
		action.type(By.xpath("//textarea[@name='note']"), note);
		action.click(By.xpath("//input[@value='Save']"));
		
		action.selectWindow(addAllergiesWin);
		
	}
	
	private static boolean verifyAnnotation()
	{
		boolean flag=true;
		action.click(By.xpath("//table[@class='allergy_table']//tr//td[contains(text(),'"+content+date+"')]/following-sibling::td//a//img"));
String annotationWin=action.getNewPopupWinHdl(homePageWin, encounterWin,addAllergiesWin);
		
		action.selectWindow(annotationWin);
		
		String notevalue=action.getWebElement(By.xpath("//textarea[@name='note']")).getText().trim();
		BaseUtil.log(notevalue);
		
		action.click(By.xpath("//input[@value='Cancel']"));
		
		if(!notevalue.equals(note))
		{
			flag=false;
			msg=msg+"add annotation content is error";
		}
		
		action.selectWindow(addAllergiesWin);
		return flag;
	}
	private static boolean deleteAndVerify()
	{
		boolean flag=true;
		
		action.click(By.xpath("//table[@class='allergy_table']//tr//td[contains(text(),'"+content+date+"')]/following-sibling::td//a[contains(text(),'Inactivate')]"));
		
		action.accept();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
		}
		if((action.isElementPresent(By.xpath("//table[@class='allergy_table']//tr//td[contains(text(),'"+searchResult+"')]"))&&action.isElementPresent(By.xpath("//table[@class='allergy_table']//tr//td[contains(text(),'"+content+date+"')]"))&&action.isElementPresent(By.xpath("//table[@class='allergy_table']//tr//td[contains(text(),'"+expectedDate+"')]"))))
		{
			msg=msg+"| after delete the Allergies view still exist this Allergies ";
			flag=false;
		}
		
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		if(action.isElementPresent(By.xpath("//ul[@id='allergieslist']//a[contains(text(),'"+searchResult+"')]")))
		{
			flag=false;
			msg=msg+"| after delete  ,the allergieslist still exist this allergie";
			
		}
		
		
		return flag;
		
	}
	
	private String getMsg() {
		return msg;
	}
}
