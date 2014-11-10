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

public class anteriorSegment_015 {

	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static List<WebElement> list=new ArrayList<WebElement>();
	private static List<String> datalist=new ArrayList<String>();
	private static String msg = "";
	private static String stylevalue="";
	private static String divid="//div[@id='s_32']";
	private static String aid="//a[@id='a_32']";
	private static String tr1td1num=null;
	private static String tr1td2num=null;

	
   	private static String tr2td1num=null;
 	private static String tr2td2num=null;

 	
	private static String tr3td1num=null;
	private static String tr3td2num=null;


	
	private static String tr4td1num=null;
	private static String tr4td2num=null;
	private static String tr4td3num=null;
	private static String tr4td4num=null;
	private static String tr4td5num=null;
	private static String tr4td6num=null;
	private static String tr4td7num=null;
	private static String tr4td8num=null;
	private static String tr4td9num=null;
	private static String tr4td10num=null;


	private static String tr5td1num=null;
	private static String tr5td2num=null;
	
	private static String tr6td1num=null;
	private static String tr6td2num=null;

	
	
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy015");
		String testcaseName = p.getProperty("TestcaseName");
		
		tr1td1num=p.getProperty("tr1td1num");
		tr1td2num=p.getProperty("tr1td2num");
	

		 tr2td1num=p.getProperty("tr2td1num");
		 tr2td2num=p.getProperty("tr2td2num");
	
		 
		 tr3td1num=p.getProperty("tr3td1num");
		 tr3td2num=p.getProperty("tr3td2num");
	

		tr4td1num=p.getProperty("tr4td1num");
		tr4td2num=p.getProperty("tr4td2num");
		tr4td3num=p.getProperty("tr4td3num");
		tr4td4num=p.getProperty("tr4td4num");
		tr4td5num=p.getProperty("tr4td5num");
		tr4td6num=p.getProperty("tr4td6num");
		tr4td7num=p.getProperty("tr4td7num");
		tr4td8num=p.getProperty("tr4td8num");
		tr4td9num=p.getProperty("tr4td9num");
		tr4td10num=p.getProperty("tr4td10num");

		
		tr5td1num=p.getProperty("tr5td1num");
		tr5td2num=p.getProperty("tr5td2num");

		tr6td1num=p.getProperty("tr6td1num");
		tr6td2num=p.getProperty("tr6td2num");
		
		datalist.add(tr1td1num);
		datalist.add(tr1td2num);
		
		datalist.add(tr2td1num);
		datalist.add(tr2td2num);
		
		datalist.add(tr3td1num);
		datalist.add(tr3td2num);
		
		datalist.add(tr4td1num);
		datalist.add(tr4td2num);
		datalist.add(tr4td3num);
		datalist.add(tr4td4num);
		datalist.add(tr4td5num);
		datalist.add(tr4td6num);
		datalist.add(tr4td7num);
		datalist.add(tr4td8num);
		datalist.add(tr4td9num);
		datalist.add(tr4td10num);
		
		datalist.add(tr5td1num);
		datalist.add(tr5td2num);
		
		datalist.add(tr6td1num);
		datalist.add(tr6td2num);
		
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
			 stylevalue  =action.getValue(By.xpath(divid), "style");
			  BaseUtil.log("link status:"+stylevalue);
			 if(stylevalue.equals("display: none;"))
			   {
				 BaseUtil.log("click");
				 Thread.sleep(1500);
				 action.click(By.linkText("Anterior Segment:"));
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
		    System.out.println("anteriorSegment_015: Execution Terminated at " + dt);
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
		
		list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[2]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr1td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr1td2num);
		Thread.sleep(100);
		list.clear();
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[3]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr2td1num);
		list.get(1).clear();
		list.get(1).sendKeys(tr2td2num);
		list.clear();
		
		list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[4]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr3td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr3td2num);
		Thread.sleep(100);
		list.clear();
		
		list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[5]//input"));
		
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i+6));
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
		Thread.sleep(100);
		list.get(9).clear();
		list.get(9).sendKeys(tr4td10num);
		Thread.sleep(100);*/
		list.clear();
		
		list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[6]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr5td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr5td2num);
		Thread.sleep(100);
		list.clear();
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[7]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr6td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr6td2num);
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
		
		// String str =(String) ((JavascriptExecutor)driver).executeScript(" function chacktest(){ var n=0;var l=document.getElementById('s_32'); var ll=l.getElementsByTagName('input');  for(var i=0;i<ll.length;i++){ if( ll[i].value=="+tr1td1num+"){  n=n+1;}   if( ll[i].value=="+tr1td2num+"){  n=n+1;}        if( ll[i].value=="+tr2td1num+"){  n=n+1;}    if( ll[i].value=="+tr2td2num+"){  n=n+1;}    if( ll[i].value=="+tr4td1num+"){  n=n+1;}       if( ll[i].value=="+tr4td2num+"){  n=n+1;}     if( ll[i].value=="+tr4td3num+"){  n=n+1;}  if( ll[i].value=="+tr4td4num+"){  n=n+1;} if( ll[i].value=="+tr4td5num+"){  n=n+1;}  if( ll[i].value=="+tr4td6num+"){  n=n+1;}      if( ll[i].value=="+tr4td7num+"){  n=n+1;}     if( ll[i].value=="+tr4td8num+"){  n=n+1;}    if( ll[i].value=="+tr4td9num+"){  n=n+1;}  if( ll[i].value=="+tr4td10num+"){  n=n+1;}       if( ll[i].value=="+tr3td1num+"){  n=n+1;}    if( ll[i].value=="+tr3td2num+"){  n=n+1;}    if( ll[i].value=="+tr5td1num+"){  n=n+1;} if( ll[i].value=="+tr5td2num+"){  n=n+1;}     if( ll[i].value=="+tr6td1num+"){  n=n+1;} if( ll[i].value=="+tr6td2num+"){  n=n+1;}   }if(n==20){ return 'true' ;} else{return 'false';} }  return chacktest()");
		boolean flag=true;
        list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr//input"));
		
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log(""+list.get(i).getAttribute("value"));
			if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i)))
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
			    action.click(By.linkText("Anterior Segment:"));
	      }  
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_24']//table//tr//input"));
		for(int i=0;i<list.size();i++)
		{
	    	if(!list.get(i).getAttribute("class").equals("examfieldgrey"))
		  {
			msg=msg+"| the color is error ; begin the color not all examfieldgrey";
			flag=false;
			break;
		  }
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[2]//input"));
		list.get(0).clear();
		list.get(0).sendKeys(tr1td1num+"1");
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr1td2num+"1");
		Thread.sleep(100);
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[5]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datalist.get(i+6)+"1");
			Thread.sleep(100);	
		}
		
		action.click(By.xpath("//a[@id='save_measurements']"));
	    Thread.sleep(1000);
		action.refreshCurrentWindow();
		Thread.sleep(6000);
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[2]//input"));
		BaseUtil.log(list.get(0).getAttribute("value").trim()+"**"+list.get(1).getAttribute("value").trim());
			if(!(list.get(0).getAttribute("value").trim().equals(tr1td1num+"1")&&list.get(1).getAttribute("value").trim().equals(tr1td2num+"1")))
			{
				msg=msg+"| update after ;  the one row value not right";
				flag=false;
			}
			BaseUtil.log(list.get(0).getAttribute("class").trim()+"*111*"+list.get(1).getAttribute("class").trim()+"*111*");
			if(!(list.get(0).getAttribute("class").trim().equals("examfieldgrey examfieldwhite")&&list.get(1).getAttribute("class").trim().equals("examfieldgrey examfieldwhite")))
			{
				msg=msg+"| update after ;  the one row color not right";
				flag=false;
			}
	
		
		list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[5]//input"));
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log(list.get(i).getAttribute("value").trim()+"**"+datalist.get(i+6)+"1");
			if(!list.get(i).getAttribute("value").trim().equals(datalist.get(i+6)+"1"))
			{
				msg=msg+"| update after ;  the four row value not right";
				flag=false;
			}
			
			BaseUtil.log(list.get(i).getAttribute("class").trim()+"**");
			if(!list.get(i).getAttribute("class").trim().equals("examfieldgrey examfieldwhite"))
			{
				msg=msg+"| update after ;  the four row value not right";
				flag=false;
			}
			
		}
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[3]//input"));
		BaseUtil.log(list.get(0).getAttribute("value").trim()+"**"+list.get(1).getAttribute("value").trim());
			if(!(list.get(0).getAttribute("value").trim().equals(tr2td1num)&&list.get(1).getAttribute("value").trim().equals(tr2td2num)))
			{
				msg=msg+"| not update  ;  the two row value not right";
				flag=false;
			}
			BaseUtil.log(list.get(0).getAttribute("class").trim()+"*111*"+list.get(1).getAttribute("class").trim()+"*111*");
			if(!(list.get(0).getAttribute("class").trim().equals("examfieldgrey")&&list.get(1).getAttribute("class").trim().equals("examfieldgrey")))
			{
				msg=msg+"| not update  ;  the two row color not right";
				flag=false;
			}
			
			
			list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[4]//input"));
			BaseUtil.log(list.get(0).getAttribute("value").trim()+"**"+list.get(1).getAttribute("value").trim());
				if(!(list.get(0).getAttribute("value").trim().equals(tr3td1num)&&list.get(1).getAttribute("value").trim().equals(tr3td2num)))
				{
					msg=msg+"| not update  ;  the three row value not right";
					flag=false;
				}
				BaseUtil.log(list.get(0).getAttribute("class").trim()+"*111*"+list.get(1).getAttribute("class").trim()+"*111*");
				if(!(list.get(0).getAttribute("class").trim().equals("examfieldgrey")&&list.get(1).getAttribute("class").trim().equals("examfieldgrey")))
				{
					msg=msg+"| not update  ;  the three row color not right";
					flag=false;
				}
		
				
				list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[6]//input"));
				BaseUtil.log(list.get(0).getAttribute("value").trim()+"**"+list.get(1).getAttribute("value").trim());
					if(!(list.get(0).getAttribute("value").trim().equals(tr5td1num)&&list.get(1).getAttribute("value").trim().equals(tr5td2num)))
					{
						msg=msg+"| not update  ;  the five row value not right";
						flag=false;
					}
					BaseUtil.log(list.get(0).getAttribute("class").trim()+"*111*"+list.get(1).getAttribute("class").trim()+"*111*");
					if(!(list.get(0).getAttribute("class").trim().equals("examfieldgrey")&&list.get(1).getAttribute("class").trim().equals("examfieldgrey")))
					{
						msg=msg+"| not update  ;  the five row color not right";
						flag=false;
					}
		
					
					list=action.getWebElements(By.xpath("//div[@id='s_32']/table/tbody/tr[7]//input"));
					BaseUtil.log(list.get(0).getAttribute("value").trim()+"**"+list.get(1).getAttribute("value").trim());
						if(!(list.get(0).getAttribute("value").trim().equals(tr6td1num)&&list.get(1).getAttribute("value").trim().equals(tr6td2num)))
						{
							msg=msg+"| not update  ;  the six row value not right";
							flag=false;
						}
						BaseUtil.log(list.get(0).getAttribute("class").trim()+"*111*"+list.get(1).getAttribute("class").trim()+"*111*");
						if(!(list.get(0).getAttribute("class").trim().equals("examfieldgrey")&&list.get(1).getAttribute("class").trim().equals("examfieldgrey")))
						{
							msg=msg+"| not update  ;  the six row color not right";
							flag=false;
						}
		
		}catch (Exception e) {
	    	    msg=msg+e.getMessage();
				flag=false;
				e.printStackTrace();
		}				
		
		return flag;
		
	}
}
