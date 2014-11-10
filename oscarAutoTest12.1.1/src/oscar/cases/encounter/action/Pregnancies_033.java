
package encounter.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import testlink.api.java.client.TestLinkAPIResults;

import com.oscarbase.BaseUtil;
import com.oscarbase.CaseConf;
import com.oscarbase.GetTestData;
import com.oscarbase.LoginOscar;
import com.oscarbase.TestExecutionResult;
import com.oscarbase.WebDriverActions;
import com.oscarbase.createAppointment;

public class Pregnancies_033{

	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static String msg = "";
	private static  String homePageWin = "";
	private static  String encounterWin="";
	private static  String type="";
	private static  String notes="";
	private static String pdate="";
	private static String date="";
	private static String status="";
	private static  String [] wins=new String[2];
	

	public static void run() throws Exception {
 

		FirefoxProfile profile = new FirefoxProfile();  
		profile.setPreference("intl.accept_languages", "en-US");   
		driver = new FirefoxDriver(profile); 
		action=new WebDriverActions(driver);
		
		String projectName = CaseConf.getInstance().getProjectName();
		String testplanName = CaseConf.getInstance().getTestplanName();
		String build = CaseConf.getInstance().getBuild();
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		String demogName= CaseConf.getInstance().getCreateAppointmentDemogName();
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy033");
		String testcaseName = p.getProperty("TestcaseName");
		type = p.getProperty("type");
	    notes = p.getProperty("notes");
		status = p.getProperty("status");
		String result = "";
		String note = "";
		Date dt = new Date();
		SimpleDateFormat forma=new SimpleDateFormat("yyyyMMddhhmmss");
		SimpleDateFormat forma2=new SimpleDateFormat("yyyy-MM-dd");
		pdate=forma.format(dt);
		date=forma2.format(dt);
		try {
			
			LoginOscar.login(driver);
			wins=createAppointment.create(action);
			homePageWin=wins[0];
			msg=msg+wins[1];
			
			wins=createAppointment.openEncounter(action, demogName);
			encounterWin=wins[0];
			msg=msg+wins[1];
			 if(isLinExist("Pregnancies"))
			 {
				 BaseUtil.log("enter 1");
				 addPregnancies();
				 
				 if(addVerify())
				 {
					 BaseUtil.log("enter 2");
					 delatePregnancies();
					BaseUtil.log("enter 3");
					 if(deleteVerify())
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
				 
			 }else {
				    result = TestLinkAPIResults.TEST_FAILED;
					note = getMsg();
			}
			
			 BaseUtil.log(note);
		}catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();

	  }
		
		finally{
		    TestExecutionResult.reportTestCaseResult(projectName, testplanName,
			testcaseName, build, note, result);
		    System.out.println("Pregnancies_033: Execution Terminated at " + dt);
		}
		
	driver.quit();
	}
	
	
	private static boolean isLinExist(String str) {
		if (action.isElementPresent(By.linkText(str))) {
			return true;
		} else {
			msg = " | This  connection does not exist";
			return false;
		}
	}	
	
	
	

	public static void addPregnancies()
	{
		  for(int i=1;i<8;i++)
		  {
		  action.move(By.xpath("//div[@id='menuTitle5']//a"));
		  }
		  
		  action.click(By.xpath("//div[@id='menu5']//a[contains(text(),'"+type+"')]"));
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		 action.click(By.xpath("//ul[@id='pregnancylist']//a[@class='links']"));
		 String editWin=action.getNewPopupWinHdl(homePageWin,encounterWin);
		 action.selectWindow(editWin);
		
		 action.type(By.xpath("//textarea[@id='notes']"), notes+pdate);
		 
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		 action.type(By.xpath("//input[@id='endDate']"), date);
		 action.click(By.xpath("//input[@value='Submit']"));
		 
	}
	
	public static void delatePregnancies()
	{
		
		 
		action.click(By.xpath("//table[@id='ocanTable']//tr//td[contains(text(),'"+notes+pdate+"')]/preceding-sibling::td//a[contains(text(),'"+type+" pregnancy')]"));
		
		action.selectByValue(By.xpath("//select[@id='episode.status']"), status);
		
		action.click(By.xpath("//input[@value='Submit']"));
		
	}
	
	public static boolean deleteVerify()
	{
		boolean flag=true;
		
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
		
		if(action.isElementPresent(By.xpath("//span[@title='"+notes+pdate+"']")))
		{
			  msg=msg+"| after delete ,the Pregnancies list  still exist this .";
			  flag=false;
		}
		action.click(By.xpath("//div[@id='pregnancy']//a[contains(text(),'Pregnancies')]"));
		String checkWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
		action.selectWindow(checkWin);
	   if(action.isElementPresent(By.xpath("//table[@id='ocanTable']//tr//td[contains(text(),'"+notes+pdate+"')]")))
	   {
		   msg=msg+"| after delete ,the Pregnancies view still exist this .";
		  flag=false;
		   
	   }
		return flag;
		
	}
	
	public static boolean addVerify()
	{
		boolean flag=true;
		action.selectWindow(encounterWin);
		
		if(!action.isElementPresent(By.xpath("//span[@title='"+notes+pdate+"']")))
		{
			  msg=msg+"| the Pregnancies list  not exist this .";
			  flag=false;
			
		}
		action.click(By.xpath("//div[@id='pregnancy']//a[contains(text(),'Pregnancies')]"));
		String checkWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
		action.selectWindow(checkWin);
	   if(!action.isElementPresent(By.xpath("//table[@id='ocanTable']//tr//td[contains(text(),'"+notes+pdate+"')]")))
		
	   {
		  msg=msg+"| the content is error .";
		  flag=false;
		   
	   }
		return flag;
		
	}
	
	private static String getMsg() {
		return msg;
	}
}
