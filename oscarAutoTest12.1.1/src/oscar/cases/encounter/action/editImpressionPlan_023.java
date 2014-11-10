
package encounter.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class editImpressionPlan_023{

	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static String msg = "";
	private static String stylevalue="";
	private static  String homePageWin = "";
	private static  String patientSearchWin = "";
	private static  String encounterWin="";
	private static  String expectedLogContent = "";
	private static  String sendTickler = "";
	private static  String [] wins=new String[2];
	private static String editContents="";
	private static String Consultationmethod="";

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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy023");
		String testcaseName = p.getProperty("TestcaseName");
		editContents=p.getProperty("editContents");
		Consultationmethod=p.getProperty("Consultationmethod");
		sendTickler=p.getProperty("sendTickler");

		
		String result = "";
		String note = "";
		Date dt = new Date();
		try {
			
			LoginOscar.login(driver);
			wins=createAppointment.create(action);
			homePageWin=wins[0];
			msg=msg+wins[1];
			
			wins=createAppointment.openEncounter(action, demogName);
			encounterWin=wins[0];
			msg=msg+wins[1];
			Thread.sleep(6000);
			if(isTextareaExist("//textarea[@class='txtArea']")){
				BaseUtil.log("entet 1");
				
				addlong();
				BaseUtil.log("enter 2");
				
				if(addVerify())
				{
					BaseUtil.log("enter 3");
					
					if(addclickAndVerify())
					{
					BaseUtil.log("enter 4");
					if(sendTicklerAndVerify())
					{
						BaseUtil.log("pass");
						result = TestLinkAPIResults.TEST_PASSED;
					}else {
						result = TestLinkAPIResults.TEST_FAILED;
						note = getMsg();
					}
					}
					else {
						result = TestLinkAPIResults.TEST_FAILED;
						note = getMsg();
					}
				}
				else {
					result = TestLinkAPIResults.TEST_FAILED;
					note = getMsg();
				}
					
			 }else{
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
		    System.out.println("editImpressionPlan_023: Execution Terminated at " + dt);
		}
		
	driver.quit();
	}
	
	
	private static boolean isTextareaExist(String str) {
		if (action.isElementPresent(By.xpath(str))) {
			return true;
		} else {
			msg = " | This  connection does not exist";
			return false;
		}
	}	
	
	
	private static void addlong() {
	
		action.clear(By.xpath("//textarea[@class='txtArea']"));
		action.type(By.xpath("//textarea[@class='txtArea']"), editContents);
		action.click(By.xpath("//img[@id='observationDate_cal']"));
		List <WebElement> webList=new ArrayList<WebElement>();
		webList=action.getWebElements(By.xpath("//div[@class='calendar']"));
		WebElement clickElement=null;
		for(int i=0;i<webList.size();i++)
		{
			if(webList.get(i).getAttribute("class").trim().contains("display: block"));
			{
				clickElement=webList.get(i);
				break;
			}
			
		}
		
		action.click(clickElement, By.xpath("//table/thead/tr[2]/td[3]"));
		action.click(By.xpath("//input[@class='encTypeCombo']"));
	    action.click(By.xpath("//div[@class='autocomplete']/ul/li[contains(text(),'"+Consultationmethod+"')]"));
	    action.click(By.xpath("//input[@id='saveImg']"));
	    action.refreshCurrentWindow();
	    try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
	}
	
	
	private static boolean addVerify() {
		boolean flag=true;
		
	    String content=action.getValue(By.xpath("//textarea[@class='txtArea']"), "value");
	    String method=action.getValue(By.xpath("//input[@class='encTypeCombo']"), "value");
	    BaseUtil.log(content+"**"+method);
	    if(!(content.equals(editContents)&&method.equals(Consultationmethod)))
	    {
	    	msg=msg+"| add not success";
	    	flag=false;
	    	
	    }
		return flag;
	}
	
	private static boolean addclickAndVerify()
	{
		boolean flag=true;
		action.click(By.xpath("//input[@value='Arrange Plan']"));
		
		String planWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
		
		action.selectWindow(planWin);
		
		action.click(By.xpath("//a[1]"));
		action.type(By.xpath("//input[@id='followup_1.timespan']"), "15");
		
		action.type(By.xpath("//input[@id='followup_1.comment']"), "test Arrange Plan");
		
		action.click(By.xpath("//a[2]"));
		action.type(By.xpath("//input[@name='procedure_1.procedureName']"), "qi");
		action.type(By.xpath("//input[@name='procedure_1.location']"), "ming");
		action.type(By.xpath("//input[@name='procedure_1.comment']"), "test Arrange Plan of Procedure");
		
		action.click(By.xpath("//a[3]"));
		action.type(By.xpath("//input[@name='test_1.testname']"), "xiaozi");
		action.type(By.xpath("//input[@name='test_1.comment']"), "test Arrange Plan of Diagnostics");
		
		action.click(By.xpath("//input[@value='Submit']"));
		
		action.selectWindow(encounterWin);
		 action.refreshCurrentWindow();
		    try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
		
		action.click(By.xpath("//input[@value='Generate Note']"));
		action.click(By.xpath("//input[@id='saveImg']"));
	     action.refreshCurrentWindow();
		    try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
		
		  String content=action.getValue(By.xpath("//textarea[@class='txtArea']"), "value");
		  BaseUtil.log(content);
		  if(content.equals(editContents))
		    {
		    	msg=msg+"| Arrange Plan is error ";
		    	flag=false;
		    }
		  
		  action.click(By.xpath("//input[@id='ack1']"));
		  action.click(By.xpath("//input[@id='ack2']"));
		  action.click(By.xpath("//input[@id='ack3']"));
		  
		  action.click(By.xpath("//input[@value='Generate Note']"));
		  action.click(By.xpath("//input[@id='saveImg']"));
		    action.refreshCurrentWindow();
		    try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
		
		  String content2=action.getValue(By.xpath("//textarea[@class='txtArea']"), "value");
		  if(content.equals(content2))
		    {
		    	msg=msg+"| three checkbox is error ";
		    	flag=false;
		    }
		
		return flag;
		
	}
	
	
	private static boolean sendTicklerAndVerify()
	{
		boolean flag=true;
            action.click(By.xpath("//input[@value='Arrange Plan']"));
		
		String planWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
		
		action.selectWindow(planWin);
		
		action.click(By.xpath("//div[@id='followup_1']//a"));
		action.click(By.xpath("//div[@id='procedure_container']//a"));
		action.click(By.xpath("//div[@id='test_container']//a"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		action.click(By.xpath("//a[3]"));
		action.type(By.xpath("//input[@name='test_2.testname']"), "e");
		action.type(By.xpath("//input[@name='test_2.comment']"), "huan");
		
		action.click(By.xpath("//input[@value='Submit']"));
		
		action.selectWindow(encounterWin);
		
			action.selectByValue(By.xpath("//select[@id='ticklerRecip']"), sendTickler);
			action.click(By.xpath("//input[@id='stickler']"));
			action.accept();
			
			action.selectWindow(homePageWin);
			action.click(By.xpath("//a[@title='Tickler']"));
			String ticklerwin=action.getNewPopupWinHdl(homePageWin, encounterWin);
			action.selectWindow(ticklerwin);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			action.selectByValue(By.xpath("//select[@name='assignedTo']"), sendTickler);
			action.click(By.xpath("//input[@value='Create Report']"));
			
			if(!action.isElementPresent(By.xpath("//body//table//table//tr//td//span[contains(text(),'diag:e OU routine huan huan')]")))
			{
				flag=false;
				msg =msg+ " | error, the Recipient ticklerlist not find this tickler.";
			}
			
			action.click(By.xpath("//body//table//table//tr//td//span[contains(text(),'diag:e OU routine huan huan')]/../preceding-sibling::td/input"));
			action.click(By.xpath("//input[@value='Delete']"));
			
			
		
		return flag;
		
	}
	
	private static String getMsg() {
		return msg;
	}
}