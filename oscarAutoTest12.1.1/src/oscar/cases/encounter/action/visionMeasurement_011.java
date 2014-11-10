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

public class visionMeasurement_011 {

	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static List<WebElement> list=new ArrayList<WebElement>();
	private static List<String> datalist=new ArrayList<String>();
	private static String msg = "";
	private static String stylevalue="";
	private static String divid="//div[@id='s_23']";
	private static String aid="//a[@id='a_23']";
	private static String tr1td1num=null;
	private static String tr1td2num=null;
	private static String tr1td3num=null;
	private static String tr1td4num=null;
	private static String tr1td5num=null;
	private static String tr1td6num=null;
	private static String tr1td7num=null;
	
   	private static String tr2td1num=null;
	
	
	private static String tr3td1num=null;
	private static String tr3td2num=null;
	private static String tr3td3num=null;
	private static String tr3td4num=null;
	private static String tr3td5num=null;
	private static String tr3td6num=null;
	private static String tr3td7num=null;
	private static String tr3td8num=null;
	
	private static String tr4td1num=null;
	private static String tr4td2num=null;
	private static String tr4td3num=null;
	private static String tr4td4num=null;
	private static String tr4td5num=null;
	private static String tr4td6num=null;
	private static String tr4td7num=null;
	private static String tr4td8num=null;
	private static String tr4td9num=null;
	
	
	private static String tr5td1num=null;
	private static String tr5td2num=null;
	private static String tr5td3num=null;
	private static String tr5td4num=null;
	private static String tr5td5num=null;
	private static String tr5td6num=null;
	private static String tr5td7num=null;
	private static String tr5td8num=null;

	
	private static String tr6td1num=null;
	private static String tr6td2num=null;
	private static String tr6td3num=null;
	private static String tr6td4num=null;
	private static String tr6td5num=null;
	private static String tr6td6num=null;
	private static String tr6td7num=null;
	private static String tr6td8num=null;
	private static String tr6td9num=null;
	
	
	
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy011");
		String testcaseName = p.getProperty("TestcaseName");
		
		tr1td1num=p.getProperty("tr1td1num");
		tr1td2num=p.getProperty("tr1td2num");
		tr1td3num=p.getProperty("tr1td3num");
		tr1td4num=p.getProperty("tr1td4num");
		tr1td5num=p.getProperty("tr1td5num");
		tr1td6num=p.getProperty("tr1td6num");
	    tr1td7num=p.getProperty("tr1td7num");
	    
		 tr2td1num=p.getProperty("tr2td1num");
		 
		 tr3td1num=p.getProperty("tr3td1num");
		 tr3td2num=p.getProperty("tr3td2num");
		 tr3td3num=p.getProperty("tr3td3num");
		 tr3td4num=p.getProperty("tr3td4num");
		 tr3td5num=p.getProperty("tr3td5num");
		 tr3td6num=p.getProperty("tr3td6num");
		 tr3td7num=p.getProperty("tr3td7num");
		 tr3td8num=p.getProperty("tr3td8num");
		
		tr4td1num=p.getProperty("tr4td1num");
		tr4td2num=p.getProperty("tr4td2num");
	    tr4td3num=p.getProperty("tr4td3num");
		tr4td4num=p.getProperty("tr4td4num");
		tr4td5num=p.getProperty("tr4td5num");
		tr4td6num=p.getProperty("tr4td6num");
		tr4td7num=p.getProperty("tr4td7num");
		tr4td8num=p.getProperty("tr4td8num");
		tr4td9num=p.getProperty("tr4td9num");
		
		tr5td1num=p.getProperty("tr5td1num");
		tr5td2num=p.getProperty("tr5td2num");
	    tr5td3num=p.getProperty("tr5td3num");
		tr5td4num=p.getProperty("tr5td4num");
		tr5td5num=p.getProperty("tr5td5num");
		tr5td6num=p.getProperty("tr5td6num");
		tr5td7num=p.getProperty("tr5td7num");
		tr5td8num=p.getProperty("tr5td8num");

		tr6td1num=p.getProperty("tr6td1num");
		tr6td2num=p.getProperty("tr6td2num");
	    tr6td3num=p.getProperty("tr6td3num");
		tr6td4num=p.getProperty("tr6td4num");
		tr6td5num=p.getProperty("tr6td5num");
		tr6td6num=p.getProperty("tr6td6num");
		tr6td7num=p.getProperty("tr6td7num");
		tr6td8num=p.getProperty("tr6td8num");
		tr6td9num=p.getProperty("tr6td9num");
		
		datalist.add(tr1td1num);
		datalist.add(tr1td2num);
		datalist.add(tr1td3num);
		datalist.add(tr1td4num);
		datalist.add(tr1td5num);
		datalist.add(tr1td6num);	
		datalist.add(tr1td7num);
		
		datalist.add(tr2td1num);
		
		datalist.add(tr3td1num);
		datalist.add(tr3td2num);
		datalist.add(tr3td3num);
		datalist.add(tr3td4num);
		datalist.add(tr3td5num);
		datalist.add(tr3td6num);	
		datalist.add(tr3td7num);
		datalist.add(tr3td8num);
		
		datalist.add(tr4td1num);
		datalist.add(tr4td2num);
		datalist.add(tr4td3num);
		datalist.add(tr4td4num);
		datalist.add(tr4td5num);
		datalist.add(tr4td6num);	
		datalist.add(tr4td7num);
		datalist.add(tr4td8num);
		datalist.add(tr4td9num);
		
		datalist.add(tr5td1num);
		datalist.add(tr5td2num);
		datalist.add(tr5td3num);
		datalist.add(tr5td4num);
		datalist.add(tr5td5num);
		datalist.add(tr5td6num);	
		datalist.add(tr5td7num);
		datalist.add(tr5td8num);
		
		datalist.add(tr6td1num);
		datalist.add(tr6td2num);
		datalist.add(tr6td3num);
		datalist.add(tr6td4num);
		datalist.add(tr6td5num);
		datalist.add(tr6td6num);	
		datalist.add(tr6td7num);
		datalist.add(tr6td8num);
		datalist.add(tr6td9num);
		
		
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
			 Thread.sleep(8000);
			if(isLinExist(aid))
			{
			  BaseUtil.log("enter 1");
			  stylevalue =action.getValue(By.xpath(divid), "style");
			  BaseUtil.log("link status:"+stylevalue);
			 if(stylevalue.equals("display: none;"))
			   {
				 BaseUtil.log("click");
				 Thread.sleep(1500);
				 action.click(By.linkText("Vision Measurement:"));
		     }
		
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
					    }
					    else {
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
		    System.out.println("visionMeasurement_011: Execution Terminated at " + dt);
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
		BaseUtil.log(stylevalue+stylevalue.length());
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
		
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[3]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i));
			Thread.sleep(100);
			
		}
		/*list.get(0).clear();
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
		list.get(4).clear();
		list.get(4).sendKeys(tr1td5num);
		Thread.sleep(100);
		list.get(5).clear();
		list.get(5).sendKeys(tr1td6num);
		Thread.sleep(100);
		list.get(6).clear();
		list.get(6).sendKeys(tr1td7num);
		Thread.sleep(100);*/
		list.clear();
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[4]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr2td1num);
		list.clear();
		
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[5]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i+8));
			Thread.sleep(100);
			
		}
		/*list.get(0).clear();
		list.get(0).sendKeys(tr3td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr3td2num);
		Thread.sleep(100);
		list.get(2).clear();
		list.get(2).sendKeys(tr3td3num);
		Thread.sleep(100);
		list.get(3).clear();
		list.get(3).sendKeys(tr3td4num);
		Thread.sleep(100);
		list.get(4).clear();
		list.get(4).sendKeys(tr3td5num);
		Thread.sleep(100);
		list.get(5).clear();
		list.get(5).sendKeys(tr3td6num);
		Thread.sleep(100);
		list.get(6).clear();
		list.get(6).sendKeys(tr3td7num);
		Thread.sleep(100);
		list.get(7).clear();
		list.get(7).sendKeys(tr3td8num);
		Thread.sleep(100);*/
		list.clear();
		
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[6]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i+16));
			Thread.sleep(100);
			
		}
		/*list.get(0).clear();
		list.get(0).sendKeys(tr4td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr4td2num);
		Thread.sleep(100);
		list.get(2).clear();
		list.get(2).sendKeys(tr4td3num);
		Thread.sleep(100);
		list.get(3).clear();
		list.get(3).sendKeys(tr4td4num);
		Thread.sleep(100);
		list.get(4).clear();
		list.get(4).sendKeys(tr4td5num);
		Thread.sleep(100);
		list.get(5).clear();
		list.get(5).sendKeys(tr4td6num);
		Thread.sleep(100);
		list.get(6).clear();
		list.get(6).sendKeys(tr4td7num);
		Thread.sleep(100);
		list.get(7).clear();
		list.get(7).sendKeys(tr4td8num);
		Thread.sleep(100);
		list.get(8).clear();
		list.get(8).sendKeys(tr4td9num);
		Thread.sleep(100);*/
		list.clear();
		
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[7]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i+25));
			Thread.sleep(100);
			
		}
		/*list.get(0).clear();
		list.get(0).sendKeys(tr5td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr5td2num);
		Thread.sleep(100);
		list.get(2).clear();
		list.get(2).sendKeys(tr5td3num);
		Thread.sleep(100);
		list.get(3).clear();
		list.get(3).sendKeys(tr5td4num);
		Thread.sleep(100);
		list.get(4).clear();
		list.get(4).sendKeys(tr5td5num);
		Thread.sleep(100);
		list.get(5).clear();
		list.get(5).sendKeys(tr5td6num);
		Thread.sleep(100);
		list.get(6).clear();
		list.get(6).sendKeys(tr5td7num);
		Thread.sleep(100);
		list.get(7).clear();
		list.get(7).sendKeys(tr5td8num);
		Thread.sleep(100);*/
		list.clear();
		
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[8]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i+33));
			Thread.sleep(100);
			
		}
		/*list.get(0).clear();
		list.get(0).sendKeys(tr6td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr6td2num);
		Thread.sleep(100);
		list.get(2).clear();
		list.get(2).sendKeys(tr6td3num);
		Thread.sleep(100);
		list.get(3).clear();
		list.get(3).sendKeys(tr6td4num);
		Thread.sleep(100);
		list.get(4).clear();
		list.get(4).sendKeys(tr6td5num);
		Thread.sleep(100);
		list.get(5).clear();
		list.get(5).sendKeys(tr6td6num);
		Thread.sleep(100);
		list.get(6).clear();
		list.get(6).sendKeys(tr6td7num);
		Thread.sleep(100);
		list.get(7).clear();
		list.get(7).sendKeys(tr6td8num);
		Thread.sleep(100);
		list.get(8).clear();
		list.get(8).sendKeys(tr6td9num);
		Thread.sleep(100);*/
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
		
		 //String str =(String) ((JavascriptExecutor)driver).executeScript(" function chacktest(){ var n=0;var l=document.getElementById('s_23'); var ll=l.getElementsByTagName('input');  for(var i=0;i<ll.length;i++){ if( ll[i].value=="+tr1td1num+"){  n=n+1;}   if( ll[i].value=="+tr1td2num+"){  n=n+1;}   if( ll[i].value=="+tr1td3num+"){  n=n+1;}    if( ll[i].value=="+tr1td4num+"){  n=n+1;}    if( ll[i].value=="+tr1td5num+"){  n=n+1;}    if( ll[i].value=="+tr1td6num+"){  n=n+1;}    if( ll[i].value=="+tr1td7num+"){  n=n+1;}      if( ll[i].value=="+tr2td1num+"){  n=n+1;}    if( ll[i].value=="+tr4td1num+"){  n=n+1;}    if( ll[i].value=="+tr4td3num+"){  n=n+1;}   if( ll[i].value=="+tr4td2num+"){  n=n+1;}  if( ll[i].value=="+tr4td4num+"){  n=n+1;}    if( ll[i].value=="+tr4td5num+"){  n=n+1;}    if( ll[i].value=="+tr4td6num+"){  n=n+1;}      if( ll[i].value=="+tr4td7num+"){  n=n+1;}   if( ll[i].value=="+tr4td8num+"){  n=n+1;}   if( ll[i].value=="+tr4td9num+"){  n=n+1;}   if( ll[i].value=="+tr3td1num+"){  n=n+1;}    if( ll[i].value=="+tr3td2num+"){  n=n+1;}    if( ll[i].value=="+tr3td3num+"){  n=n+1;}    if( ll[i].value=="+tr3td4num+"){  n=n+1;}    if( ll[i].value=="+tr3td5num+"){  n=n+1;}    if( ll[i].value=="+tr3td6num+"){  n=n+1;}   if( ll[i].value=="+tr3td7num+"){  n=n+1;}   if( ll[i].value=="+tr3td8num+"){  n=n+1;}          if( ll[i].value=="+tr5td1num+"){  n=n+1;}    if( ll[i].value=="+tr5td3num+"){  n=n+1;}   if( ll[i].value=="+tr5td2num+"){  n=n+1;}  if( ll[i].value=="+tr5td4num+"){  n=n+1;}    if( ll[i].value=="+tr5td5num+"){  n=n+1;}    if( ll[i].value=="+tr5td6num+"){  n=n+1;}      if( ll[i].value=="+tr5td7num+"){  n=n+1;}   if( ll[i].value=="+tr5td8num+"){  n=n+1;}            if( ll[i].value=="+tr6td1num+"){  n=n+1;}    if( ll[i].value=="+tr6td3num+"){  n=n+1;}   if( ll[i].value=="+tr6td2num+"){  n=n+1;}  if( ll[i].value=="+tr6td4num+"){  n=n+1;}    if( ll[i].value=="+tr6td5num+"){  n=n+1;}    if( ll[i].value=="+tr6td6num+"){  n=n+1;}      if( ll[i].value=="+tr6td7num+"){  n=n+1;}   if( ll[i].value=="+tr6td8num+"){  n=n+1;}   if( ll[i].value=="+tr6td9num+"){  n=n+1;}    }   if(n==42){ return 'true' ;} else{return 'false';} }  return chacktest()");
		boolean flag=true;
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr//input"));
		BaseUtil.log(list.size()+"**"+datalist.size());
		for(int i=0;i<list.size();i++)
		{
			//BaseUtil.log(list.get(i).getAttribute("value").trim()+"***"+datalist.get(i)+"**"+list.get(i).getAttribute("value").trim().equals(datalist.get(i)));
			if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i).trim()))
			{
				flag=false;
				msg=msg+"|error  add not success";	
			}
		}
		return flag;
	}
	
	public static boolean verifyUpdateIsOk()
	{
		boolean flag=true;
		try {
			Thread.sleep(6000);
			  stylevalue =action.getValue(By.xpath(divid), "style");
			  BaseUtil.log("link status:"+stylevalue);
		if(stylevalue.equals("display: none;"))
		   {
			    BaseUtil.log("click");
				Thread.sleep(1500);
			    action.click(By.linkText("Vision Measurement:"));
	      }  				

		
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr//input"));
		for(int i=0;i<list.size();i++)
		{
	    	if(!list.get(i).getAttribute("class").equals("examfieldgrey"))
		  {
			msg=msg+"| the color is error ; begin the color not all examfieldgrey";
			flag=false;
			break;
		  }
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[3]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i)+"1");
				Thread.sleep(100);
			
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[5]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i+8)+"1");
				Thread.sleep(100);
			
		}
		
		action.click(By.xpath("//a[@id='save_measurements']"));
	    Thread.sleep(1000);
		action.refreshCurrentWindow();
		Thread.sleep(6000);
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[3]//input"));
		for(int i=0;i<list.size();i++)
		{
		
			if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i).trim()+"1"))
			{
				msg=msg+"| update after ;  the one row value not right";
				flag=false;
			}
			BaseUtil.log(list.get(i).getAttribute("class").trim()+"*111*"+list.get(i).getAttribute("itemtime").trim());
			if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey examfieldwhite"))
			{
				msg=msg+"| update after ;  the one row color not right";
				flag=false;
			}
			
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[5]//input"));
		
			for(int i=0;i<list.size();i++)
			{
				if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+8).trim()+"1"))
				{
					msg=msg+"| update after ;  the three row value not right";
					flag=false;
				}
				BaseUtil.log(list.get(i).getAttribute("class").trim()+"*333*");
				if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey examfieldwhite"))
				{
					msg=msg+"| update after ;  the three row color not right";
					flag=false;
				}
		}
			
			
			
			list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[4]//input"));
			
			for(int i=0;i<list.size();i++)
			{
				if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+7).trim()))
				{
					msg=msg+"| not update ;  the two row value not right";
					flag=false;
				}
				if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey"))
				{
					msg=msg+"| not update  ;  the two row color not right";
					flag=false;
				}
		}
			
	   list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[6]//input"));
			
			for(int i=0;i<list.size();i++)
			{
				if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+16).trim()))
				{
					msg=msg+"| not update ;  the four row value not right";
					flag=false;
				}
				if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey"))
				{
					msg=msg+"| not update  ;  the four row color not right";
					flag=false;
				}
		}
			
			
	list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[7]//input"));
			
			for(int i=0;i<list.size();i++)
			{
				if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+25).trim()))
				{
					msg=msg+"| not update ;  the five row value not right";
					flag=false;
				}
				if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey"))
				{
					msg=msg+"| not update  ;  the five row color not right";
					flag=false;
				}
		}
			
	list=action.getWebElements(By.xpath("//div[@id='s_23']//table//tr[8]//input"));
			
			for(int i=0;i<list.size();i++)
			{
				if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+33).trim()))
				{
					msg=msg+"| not update ;  the six row value not right";
					flag=false;
				}
				if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey"))
				{
					msg=msg+"| not update  ;  the six row color not right";
					flag=false;
				}
		}
			
			
			
		} catch (Exception e) {
			msg=msg+e.getMessage();
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}
}
