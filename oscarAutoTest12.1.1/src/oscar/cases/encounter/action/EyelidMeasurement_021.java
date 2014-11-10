
package encounter.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class EyelidMeasurement_021{

	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static List<WebElement> list=new ArrayList<WebElement>();
	private static List<String> datalist=new ArrayList<String>();
	private static String msg = "";
	private static String stylevalue="";
	private static 	String divid="//div[@id='s_31']";
	private static  String aid="//a[@id='a_31']";
	private static String tr1td1num=null;
	private static String tr1td2num=null;
	private static String tr1td3num=null;
	private static String tr1td4num=null;	
	
   	private static String tr2td1num=null;
 	private static String tr2td2num=null;
	
	private static String tr3td1num=null;
	private static String tr3td2num=null;

	private static String tr4td1num=null;
	private static String tr4td2num=null;
	
	private static String tr5td1num=null;
	private static String tr5td2num=null;
	
	private static String tr6td1num=null;
	private static String tr6td2num=null;
	
	private static String tr7td1num=null;
	private static String tr7td2num=null;
	

	
	
	private static  String homePageWin = "";
	private static  String patientSearchWin = "";
	private static  String encounterWin="";
	private static  String expectedLogContent = "";
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy021");
		String testcaseName = p.getProperty("TestcaseName");
		
		tr1td1num=p.getProperty("tr1td1num");
		tr1td2num=p.getProperty("tr1td2num");
		tr1td3num=p.getProperty("tr1td3num");
		tr1td4num=p.getProperty("tr1td4num");


		 tr2td1num=p.getProperty("tr2td1num");
		 tr2td2num=p.getProperty("tr2td2num");
		 
		 tr3td1num=p.getProperty("tr3td1num");
		 tr3td2num=p.getProperty("tr3td2num");


	     tr4td1num=p.getProperty("tr4td1num");
		 tr4td2num=p.getProperty("tr4td2num");

		 tr5td1num=p.getProperty("tr5td1num");
		 tr5td2num=p.getProperty("tr5td2num");
			 
		 tr6td1num=p.getProperty("tr6td1num");
		 tr6td2num=p.getProperty("tr6td2num");

		 tr7td1num=p.getProperty("tr7td1num");
		 tr7td2num=p.getProperty("tr7td2num");

		 datalist.add(tr1td1num);
		 datalist.add(tr1td2num);
		 datalist.add(tr1td3num);
		 datalist.add(tr1td4num);
		 datalist.add(tr2td1num);
		 datalist.add(tr2td2num);
		 datalist.add(tr3td1num);
		 datalist.add(tr3td2num);
		 datalist.add(tr4td1num);
		 datalist.add(tr4td2num);
		 datalist.add(tr5td1num);
		 datalist.add(tr5td2num);
		 datalist.add(tr6td1num);
		 datalist.add(tr6td2num);
		 datalist.add(tr7td1num);
		 datalist.add(tr7td2num);
		 
		
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
			if(isLinExist(aid))
			{
			  BaseUtil.log("enter 1");
			  stylevalue=action.getValue(By.xpath(divid), "style");
			  BaseUtil.log("link status:"+stylevalue);
			 if(stylevalue.equals("display: none;"))
			   {
				 BaseUtil.log("click");
				 Thread.sleep(1500);
				 action.click(By.linkText("Eyelid Measurement:"));
		     }
			   Thread.sleep(1000);
			   if(OpenLinkVerify(divid))
			   {
				    BaseUtil.log("enter 2");
				    addNewLog();
				    BaseUtil.log("enter 3");
				    if (verifyAddSuccessfully()) {
				    	BaseUtil.log("enter 4");
					    wins=createAppointment.deleteAppointmentAndcreateAgain(action,demogName);
						homePageWin=wins[0];
						msg=msg+wins[1];
						wins=createAppointment.openEncounter(action, demogName);
						encounterWin=wins[0];
						msg=msg+wins[1];
					    BaseUtil.log("enter 5");
					    if(verifyUpdateIsOk())
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
			   
			}else {
				result = TestLinkAPIResults.TEST_FAILED;
				note = getMsg();
			}
			BaseUtil.log(msg);
		} 
		catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();

	  }
		
		finally{
		    TestExecutionResult.reportTestCaseResult(projectName, testplanName,
			testcaseName, build, note, result);
		    System.out.println("EyelidMeasurement_021: Execution Terminated at " + dt);
		}
		
	driver.quit();
	}
	
	private static boolean isLinExist(String str) {
		if (action.isElementPresent(By.xpath(str))) {
			return true;
		} else {
			msg = " | This  connection does not exist";
			return false;
		}
	}
	
	private static boolean OpenLinkVerify(String str) {
		
		String stylevalue=action.getValue(By.xpath(str), "style");
		BaseUtil.log("link status:"+stylevalue);
		if (stylevalue.equals("display: block;")||stylevalue.length()==0) {
			
			if (action.getSizeOfRows(driver.findElement(By.xpath(str+"//table")))>0) {
				return true;
			} else {
				msg = msg + " | Link's content error";
				return false;
			}
		} else {
			msg = msg + " | not open this Link.";
			return false;
		}
	}
	
	private static String getMsg() {
		return msg;
	}
	
	private static void addNewLog() throws InterruptedException {
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[3]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr1td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr1td2num);
		Thread.sleep(100);
		list.get(2).clear();
		list.get(2).sendKeys(tr1td3num);
		Thread.sleep(100);
		list.get(3).clear();
		list.get(3).sendKeys(tr1td4num);
		Thread.sleep(100);
		list.clear();
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[4]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr2td1num);
		list.get(1).clear();
		list.get(1).sendKeys(tr2td2num);
		list.clear();
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[5]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr3td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr3td2num);
		Thread.sleep(100);
		list.clear();
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[6]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr4td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr4td2num);
		Thread.sleep(100);
		list.clear();
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[7]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr5td1num);
		list.get(1).clear();
		list.get(1).sendKeys(tr5td2num);
		list.clear();
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[8]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr6td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr6td2num);
		Thread.sleep(100);
		list.clear();
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[9]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr7td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr7td2num);
		Thread.sleep(100);
		list.clear();
		
		
	
		
		action.click(By.xpath("//a[@id='save_measurements']"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		action.refreshCurrentWindow();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
	}
	}
	
	private static boolean verifyAddSuccessfully(){
		
		// String str =(String) ((JavascriptExecutor)driver).executeScript(" function chacktest(){ var n=0;var l=document.getElementById('s_31'); var ll=l.getElementsByTagName('input');  for(var i=0;i<ll.length;i++){ if( ll[i].value=="+tr1td1num+"){  n=n+1;}   if( ll[i].value=="+tr1td2num+"){  n=n+1;}          if( ll[i].value=="+tr1td3num+"){  n=n+1;}    if( ll[i].value=="+tr1td4num+"){  n=n+1;} if( ll[i].value=="+tr2td1num+"){  n=n+1;}  if( ll[i].value=="+tr2td2num+"){  n=n+1;}            if( ll[i].value=="+tr3td1num+"){  n=n+1;}    if( ll[i].value=="+tr3td2num+"){  n=n+1;}    if( ll[i].value=="+tr4td1num+"){  n=n+1;}    if( ll[i].value=="+tr4td2num+"){  n=n+1;}   if( ll[i].value=="+tr5td1num+"){  n=n+1;}    if( ll[i].value=="+tr5td2num+"){  n=n+1;}      if( ll[i].value=="+tr6td1num+"){  n=n+1;}    if( ll[i].value=="+tr6td2num+"){  n=n+1;}      if( ll[i].value=="+tr7td1num+"){  n=n+1;}    if( ll[i].value=="+tr7td2num+"){  n=n+1;}     }if(n==16){ return 'true' ;} else{return 'false';} }  return chacktest()");

         boolean flag=true;
         list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr//input"));
         for(int i=0;i<list.size();i++)
     	{
     		if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i).trim()))
     		{
     			msg=msg+"| add not success";
     			flag=false;
     		}
     	}
		
		return flag;

	}
	
	public static boolean verifyUpdateIsOk()
	{
		boolean flag=true;
		try {
			Thread.sleep(8000);
			 stylevalue=action.getValue(By.xpath(divid), "style");
			 BaseUtil.log("link status:"+stylevalue);
		if(stylevalue.equals("display: none;"))
		   {
			    BaseUtil.log("click");
			    action.click(By.linkText("Eyelid Measurement:"));
	      }  
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr//input"));
		for(int i=0;i<list.size();i++)
		{
	    	if(!list.get(i).getAttribute("class").equals("examfieldgrey"))
		  {
			msg=msg+"| the color is error ; begin the color not all examfieldgrey";
			flag=false;
			break;
		  }
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[3]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i).trim()+"1");
			Thread.sleep(100);
		}
	
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[5]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i+6).trim()+"1");
			Thread.sleep(100);
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[7]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i+10).trim()+"1");
			Thread.sleep(100);
		}
		
		action.click(By.xpath("//a[@id='save_measurements']"));
	    Thread.sleep(1000);
		action.refreshCurrentWindow();
		Thread.sleep(6000);
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[3]//input"));
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log(list.get(i).getAttribute("value").trim()+"**"+datalist.get(i).trim()+"1");
			if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i).trim()+"1"))
			{
				msg=msg+"| update after ;  the one row value not right";
				flag=false;
				
			}
			
			BaseUtil.log(list.get(i).getAttribute("class").trim()+"**");
			if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey examfieldwhite"))
			{
				msg=msg+"| update after ;  the one row color not right";
				flag=false;
				
			}
			
		}
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[5]//input"));
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log(list.get(i).getAttribute("value").trim()+"**"+datalist.get(i+6).trim()+"1");
			if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+6).trim()+"1"))
			{
				msg=msg+"| update after ;  the three row value not right";
				flag=false;
				
			}
			
			BaseUtil.log(list.get(i).getAttribute("class").trim()+"**");
			if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey examfieldwhite"))
			{
				msg=msg+"| update after ;  the three row color not right";
				flag=false;
				
			}
			
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[7]//input"));
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log(list.get(i).getAttribute("value").trim()+"**"+datalist.get(i+10).trim()+"1");
			if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+10).trim()+"1"))
			{
				msg=msg+"| update after ;  the five row value not right";
				flag=false;
				
			}
			
			BaseUtil.log(list.get(i).getAttribute("class").trim()+"**");
			if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey examfieldwhite"))
			{
				msg=msg+"| update after ;  the five row color not right";
				flag=false;
				
			}
			
		}
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[4]//input"));
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log(list.get(i).getAttribute("value").trim()+"**"+datalist.get(i+4).trim());
			if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+4).trim()))
			{
				msg=msg+"| not update;  the two row value not right";
				flag=false;
				
			}
			
			BaseUtil.log(list.get(i).getAttribute("class").trim()+"**");
			if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey"))
			{
				msg=msg+"| not update  ;  the two row color not right";
				flag=false;
				
			}
		}
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[6]//input"));
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log(list.get(i).getAttribute("value").trim()+"**"+datalist.get(i+8).trim());
			if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+8).trim()))
			{
				msg=msg+"| not update;  the four row value not right";
				flag=false;
				
			}
			
			BaseUtil.log(list.get(i).getAttribute("class").trim()+"**");
			if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey"))
			{
				msg=msg+"| not update  ;  the four row color not right";
				flag=false;
				
			}
		}
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[8]//input"));
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log(list.get(i).getAttribute("value").trim()+"**"+datalist.get(i+12).trim());
			if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+12).trim()))
			{
				msg=msg+"| not update;  the six row value not right";
				flag=false;
				
			}
			
			BaseUtil.log(list.get(i).getAttribute("class").trim()+"**");
			if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey"))
			{
				msg=msg+"| not update  ;  the six row color not right";
				flag=false;
				
			}
		}
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_31']//table//tr[9]//input"));
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log(list.get(i).getAttribute("value").trim()+"**"+datalist.get(i+14).trim());
			if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+14).trim()))
			{
				msg=msg+"| not update;  the seven row value not right";
				flag=false;
				
			}
			
			BaseUtil.log(list.get(i).getAttribute("class").trim()+"**");
			if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey"))
			{
				msg=msg+"| not update  ;  the seven row color not right";
				flag=false;
				
			}
		}
		
		}catch (Exception e) {
	    	    msg=msg+e.getMessage();
				flag=false;
				e.printStackTrace();
		}	
		
		return flag;
		
	}
}

