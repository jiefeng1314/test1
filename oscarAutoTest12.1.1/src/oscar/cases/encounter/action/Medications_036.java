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

public class Medications_036 {

	private static String homePageWin = "";
	private static String encounterWin="";
	private static 	String addMedicationsWin="";
	private static  String [] wins=new String[2];
	private static String msg = "";
	private static String expectedDate;
	private static String date;
	private static String searchName;
	private static String searchResult;
	private static String time;
	private static String quantity;
	private static String repeats;
	private static String content;
	private static String note;
	private static String comment;
	private static String type1;
	private static String type2;
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy036");
		String testcaseName = p.getProperty("TestcaseName").trim();
		 searchName = p.getProperty("searchName").trim();
		 searchResult = p.getProperty("searchResult").trim();
		 content = p.getProperty("content").trim();
		 quantity = p.getProperty("quantity").trim();
		 repeats = p.getProperty("repeats").trim();
		 note = p.getProperty("note").trim();
		 comment = p.getProperty("comment").trim();
		 type1 = p.getProperty("type1").trim();
		 type2 = p.getProperty("type2").trim();

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
			Medications_036 case089 = new Medications_036();
			if(canOpenMedicationsWin()){
				 BaseUtil.log("enter 1");
				case089.addMedications();
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
					.println("Medications_036: Execution Terminated at "
							+ dt);
		}
		driver.quit();
	//	Thread.sleep(3000);
	}

	
	
	
	private static boolean isMedicationsPresent() {
		if (action.isElementPresent(By.id("Rx"))) {
			return true;
		} else {
			msg = msg+" | 'Medications' doesn't exist from the right navigator.";
			return false;
		}
	}

	private static boolean canOpenMedicationsWin() {

		if (isMedicationsPresent()) {
			if (action.isElementPresent(
					action.getWebElement(By.id("menuTitleRx")),
					By.linkText("+"))) {

				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
	
				}
				action.click(
						action.getWebElement(By.id("menuTitleRx")),
						By.linkText("+"));
				
				addMedicationsWin=action.getNewPopupWinHdl(homePageWin,encounterWin);
				if (addMedicationsWin != null) {
					action.selectWindow(addMedicationsWin);
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

	
	private static void addMedications()
	{
		action.click(By.xpath("//input[@name='search']"));
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e1) {
		}
		String searchWin=action.getNewPopupWinHdl(homePageWin, encounterWin, addMedicationsWin);
		action.selectWindow(searchWin);
		
		action.clear(By.xpath("//input[@id='searchString']"));
		action.type(By.xpath("//input[@id='searchString']"), searchName);
		
		action.click(By.xpath("//input[@value='Search']"));
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e1) {
		}
		action.click(By.xpath("//div[@class='ChooseDrugBox']//td//a[contains(text(),'"+searchResult+"')]"));
		action.dealAlert(true);
		
		action.selectWindow(addMedicationsWin);
		action.dealAlert(true);
		action.clear(By.xpath("//input[@onchange='parseIntr(this);']"));
		action.type(By.xpath("//input[@onchange='parseIntr(this);']"), content);
		
		action.clear(By.xpath("//input[@onblur='updateQty(this);']"));
		action.type(By.xpath("//input[@onblur='updateQty(this);']"), quantity);
		
		
		action.clear(By.xpath("//input[@onblur='updateQty(this);']/following-sibling::input[@type='text'][1]"));
		action.type(By.xpath("//input[@onblur='updateQty(this);']/following-sibling::input[@type='text'][1]"), repeats);
		
		action.clear(By.xpath("//input[@onblur='updateQty(this);']/following-sibling::input[@type='text'][2]"));
		action.type(By.xpath("//input[@onblur='updateQty(this);']/following-sibling::input[@type='text'][2]"), expectedDate);
		
		action.clear(By.xpath("//input[@onblur='updateQty(this);']/following-sibling::input[@type='text'][3]"));
		action.type(By.xpath("//input[@onblur='updateQty(this);']/following-sibling::input[@type='text'][3]"), time);
		action.clear(By.xpath("//input[@onblur='updateQty(this);']/following-sibling::input[@type='text'][4]"));
		action.type(By.xpath("//input[@onblur='updateQty(this);']/following-sibling::input[@type='text'][4]"), comment);
		
		action.selectByValue(By.xpath("//select[option[@value='ACU']]"), type1);
		action.selectByValue(By.xpath("//select[option[@value='Active']]"), type2);
		
		action.click(By.xpath("//input[@id='saveOnlyButton']"));
		
		
	}

	private static boolean verify()
	{
		boolean  flag=true;
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
		}
		
		if(!action.isElementPresent(By.xpath("//ul[@id='Rxlist']//li//span//a//span[contains(text(),'"+searchResult+"')]")))
		
		{
			flag=false;
			msg=msg+"| add not success ,Medicationslist  not exist this allergie";
		}
		
		if(action.isElementPresent(By.xpath("//div[@id='Rx']//a[contains(text(),'Medications')]")))
		{
			action.click(By.xpath("//div[@id='Rx']//a[contains(text(),'Medications')]"));
			
			addMedicationsWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
			action.selectWindow(addMedicationsWin);
			
			action.click(By.xpath("//a[@id='selected_default']"));
			if(!action.isElementPresent(By.xpath("//div[@class='drugProfileText']//tr//td//a[contains(text(),'"+searchResult+"  "+content+"  Qty:"+quantity+" Repeats:"+repeats+"')]")))
			{
				flag=false;
				msg=msg+"| add not success ,Medications view not exist this allergie";
				
			}
			
		}else {
			msg=msg+"| not exist Medications  this link";
			flag=false;
		}
		
		
		return flag;
		
	}
	
	
	private static void addAnnotation(){
		
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		action.click(By.xpath("//div[@class='drugProfileText']//tr//td//a[contains(text(),'"+searchResult+"  "+content+"  Qty:"+quantity+" Repeats:"+repeats+"')]/../following-sibling::td//a//img"));
		
		String annotationWin=action.getNewPopupWinHdl(homePageWin, encounterWin,addMedicationsWin);
		
		action.selectWindow(annotationWin);
		
		action.clear(By.xpath("//textarea[@name='note']"));
		action.type(By.xpath("//textarea[@name='note']"), note);
		action.click(By.xpath("//input[@value='Save']"));
		
		action.selectWindow(addMedicationsWin);
		
	}
	
	private static boolean verifyAnnotation()
	{
		boolean flag=true;
		action.click(By.xpath("//div[@class='drugProfileText']//tr//td//a[contains(text(),'"+searchResult+"  "+content+"  Qty:"+quantity+" Repeats:"+repeats+"')]/../following-sibling::td//a//img"));
        String annotationWin=action.getNewPopupWinHdl(homePageWin, encounterWin,addMedicationsWin);
		
		action.selectWindow(annotationWin);
		
		String notevalue=action.getWebElement(By.xpath("//textarea[@name='note']")).getText().trim();
		BaseUtil.log(notevalue);
		
		action.click(By.xpath("//input[@value='Cancel']"));
		
		if(!notevalue.equals(note))
		{
			flag=false;
			msg=msg+"add annotation content is error";
		}
		
		action.selectWindow(addMedicationsWin);
		return flag;
	}
	private static boolean deleteAndVerify()
	{
		boolean flag=true;
		action.click(By.xpath("//div[@class='drugProfileText']//tr//td//a[contains(text(),'"+searchResult+"  "+content+"  Qty:"+quantity+" Repeats:"+repeats+"')]/../following-sibling::td//a[contains(text(),'Del')]"));
		action.accept();
		action.refreshCurrentWindow();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
		}
		if(action.isElementPresent(By.xpath("//div[@class='drugProfileText']//tr//td//a[contains(text(),'"+searchResult+"  "+content+"  Qty:"+quantity+" Repeats:"+repeats+"')]")))
		{
			flag=false;
			msg=msg+"| after delete ,Medications view still exist this allergie";
			
		}
		
		
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		BaseUtil.log(searchResult+"  "+content+"  Qty:"+quantity+" Repeats:"+repeats+" "+date+" - "+date);
		if(action.isElementPresent(By.xpath("//ul[@id='Rxlist']//li//span//a[@title='"+searchResult+"  "+content+"  Qty:"+quantity+" Repeats:"+repeats+" "+expectedDate+" - "+expectedDate+"']")))
			
		{
			flag=false;
			msg=msg+"| after delete ,Medicationslist  still exist this allergie";
		}
		
		return flag;
		
	}
	
	private String getMsg() {
		return msg;
	}
}
