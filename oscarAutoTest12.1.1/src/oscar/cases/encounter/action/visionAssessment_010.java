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

public class visionAssessment_010 {

	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static List<WebElement> list=new ArrayList<WebElement>();
	
	private static List<String> datelist=new ArrayList<String>();
	private static String msg = "";
	private static  String stylevalue="";
	private static String divid="//div[@id='s_22']";
	private static String aid="//a[@id='a_22']";
	private static String tr1td1num=null;
	private static String tr1td2num=null;
	private static String tr1td3num=null;
	private static String tr1td4num=null;
	private static String tr1td5num=null;
	private static String tr1td6num=null;
	private static String tr1td7num=null;
	private static String tr1td8num=null;
	
   	private static String tr2td1num=null;
	private static String tr2td2num=null;
	private static String tr2td3num=null;
	private static String tr2td4num=null;
	private static String tr2td5num=null;
	private static String tr2td6num=null;
	
	private static String tr3td1num=null;
	private static String tr3td2num=null;
	private static String tr3td3num=null;
	private static String tr3td4num=null;
	private static String tr3td5num=null;
	private static String tr3td6num=null;
	
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy010");
		String testcaseName = p.getProperty("TestcaseName");
		
		tr1td1num=p.getProperty("tr1td1num");
		tr1td2num=p.getProperty("tr1td2num");
		tr1td3num=p.getProperty("tr1td3num");
		tr1td4num=p.getProperty("tr1td4num");
		tr1td5num=p.getProperty("tr1td5num");
		tr1td6num=p.getProperty("tr1td6num");
	    tr1td7num=p.getProperty("tr1td7num");
	    tr1td8num=p.getProperty("tr1td8num");
		
		 tr2td1num=p.getProperty("tr2td1num");
		 tr2td2num=p.getProperty("tr2td2num");
		 tr2td3num=p.getProperty("tr2td3num");
		 tr2td4num=p.getProperty("tr2td4num");
		 tr2td5num=p.getProperty("tr2td5num");
		 tr2td6num=p.getProperty("tr2td6num");
		
		tr3td1num=p.getProperty("tr3td1num");
		tr3td2num=p.getProperty("tr3td2num");
	    tr3td3num=p.getProperty("tr3td3num");
		tr3td4num=p.getProperty("tr3td4num");
		tr3td5num=p.getProperty("tr3td5num");
		tr3td6num=p.getProperty("tr3td6num");
		datelist.add(tr1td1num);
		datelist.add(tr1td2num);
		datelist.add(tr1td3num);
		datelist.add(tr1td4num);
		datelist.add(tr1td5num);
		datelist.add(tr1td6num);
		datelist.add(tr1td7num);
		datelist.add(tr1td8num);
		
		datelist.add(tr2td1num);
		datelist.add(tr2td2num);
		datelist.add(tr2td3num);
		datelist.add(tr2td4num);
		datelist.add(tr2td5num);
		datelist.add(tr2td6num);
		
		datelist.add(tr3td1num);
		datelist.add(tr3td2num);
		datelist.add(tr3td3num);
		datelist.add(tr3td4num);
		datelist.add(tr3td5num);
		datelist.add(tr3td6num);
		
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
				 action.click(By.linkText("Vision Assessment:"));
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
		    System.out.println("visionAssessment_010: Execution Terminated at " + dt);
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
		
		list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr[3]//input"));
		
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datelist.get(i));
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
		Thread.sleep(100);
		list.get(7).clear();
		list.get(7).sendKeys(tr1td8num);
		Thread.sleep(100);*/
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr[4]//input"));
		
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datelist.get(i+8));
			Thread.sleep(100);
		}
		/*list.get(0).clear();
		list.get(0).sendKeys(tr2td1num);
		Thread.sleep(100);
		list.get(1).clear();
		list.get(1).sendKeys(tr2td2num);
		Thread.sleep(100);
		list.get(2).clear();
		list.get(2).sendKeys(tr2td3num);
		Thread.sleep(100);
		list.get(3).clear();
		list.get(3).sendKeys(tr2td4num);
		Thread.sleep(100);
		list.get(4).clear();
		list.get(4).sendKeys(tr2td5num);
		Thread.sleep(100);
		list.get(5).clear();
		list.get(5).sendKeys(tr2td6num);
		Thread.sleep(100);*/
		
		list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr[5]//input"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datelist.get(i+14));
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
		Thread.sleep(100);*/
	
	
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
	  
		boolean flag=true;
		// String str =(String) ((JavascriptExecutor)driver).executeScript(" function chacktest(){ var n=0;var l=document.getElementById('s_22'); var ll=l.getElementsByTagName('input');  for(var i=0;i<ll.length;i++){ if( ll[i].value=="+tr1td1num+"){  n=n+1;}   if( ll[i].value=="+tr1td2num+"){  n=n+1;}   if( ll[i].value=="+tr1td3num+"){  n=n+1;}    if( ll[i].value=="+tr1td4num+"){  n=n+1;}    if( ll[i].value=="+tr1td5num+"){  n=n+1;}    if( ll[i].value=="+tr1td6num+"){  n=n+1;}    if( ll[i].value=="+tr1td7num+"){  n=n+1;}    if( ll[i].value=="+tr1td8num+"){  n=n+1;}    if( ll[i].value=="+tr2td1num+"){  n=n+1;}    if( ll[i].value=="+tr2td2num+"){  n=n+1;}    if( ll[i].value=="+tr2td3num+"){  n=n+1;}    if( ll[i].value=="+tr2td4num+"){  n=n+1;}    if( ll[i].value=="+tr2td5num+"){  n=n+1;}    if( ll[i].value=="+tr2td6num+"){  n=n+1;}    if( ll[i].value=="+tr3td1num+"){  n=n+1;}    if( ll[i].value=="+tr3td2num+"){  n=n+1;}    if( ll[i].value=="+tr3td3num+"){  n=n+1;}    if( ll[i].value=="+tr3td4num+"){  n=n+1;}    if( ll[i].value=="+tr3td5num+"){  n=n+1;}    if( ll[i].value=="+tr3td6num+"){  n=n+1;}        }   if(n==20){ return 'true' ;} else{return 'false';} }  return chacktest()");
		list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr[3]//input"));
		for(int i=0;i<list.size();i++)
		{
			if(!list.get(i).getAttribute("value").equals(datelist.get(i)))
			{
				msg=msg+"|one row add not success";
				flag=false;
			}
		}
		list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr[4]//input"));
		
		for(int i=0;i<list.size();i++)
		{
			if(!list.get(i).getAttribute("value").equals(datelist.get(i+8)))
			{
				msg=msg+"| two row add not success";
				flag=false;
			}
		}
	list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr[5]//input"));
		
		for(int i=0;i<list.size();i++)
		{
			if(!list.get(i).getAttribute("value").equals(datelist.get(i+14)))
			{
				msg=msg+"| three row add not success";
				flag=false;
			}
		}
		
			return flag;
		
	}
	
	
	public static boolean  verifyUpdateIsOk()
	{
		boolean flag=true;
		try {
			Thread.sleep(8000);
			  stylevalue =action.getValue(By.xpath(divid), "style");
			  BaseUtil.log("link status:"+stylevalue);
			if(stylevalue.equals("display: none;"))
			   {
				 BaseUtil.log("click");
				 Thread.sleep(1500);
				 action.click(By.linkText("Vision Assessment:"));
		     }  				

		list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr//input[@type='text']"));
		for(int i=0;i<list.size();i++)
		{
			//BaseUtil.log(list.get(i).getAttribute("class").trim().equals("examfieldgrey")+"");
			 BaseUtil.log("begincolor:"+list.get(i).getAttribute("class"));
			boolean check=list.get(i).getAttribute("class").trim().equals("examfieldgrey");
			if(!check)
			{
				flag=false;
				msg=msg+"| color is error, not all examfieldgrey";
			}
			
			Thread.sleep(100);
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr[3]//input[@type='text']"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datelist.get(i).trim()+"1");
			Thread.sleep(100);
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr[5]//input[@type='text']"));
		for(int i=0;i<list.size();i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(datelist.get(i+14).trim()+"1");
			Thread.sleep(100);
		}
		
		
		action.click(By.xpath("//a[@id='save_measurements']"));
	    Thread.sleep(1000);
		action.refreshCurrentWindow();
		Thread.sleep(6000);
		
		list.clear();
		list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr[3]//input[@type='text']"));
		
		for(int i=0;i<list.size();i++)
		{
			 BaseUtil.log("3color:"+list.get(i).getAttribute("class")+"kkkkk");
			 BaseUtil.log("3value:"+list.get(i).getAttribute("value")+"datevalue:"+datelist.get(i).trim()+"1");
			if(!list.get(i).getAttribute("class").equals("examfieldgrey examfieldwhite"))
			{
				flag=false;
				msg=msg+"| update after, the one row color not examfieldwhite";
			}
			
			if(!list.get(i).getAttribute("value").equals(datelist.get(i).trim()+"1"))
			{
				flag=false;
				msg=msg+"| update after, the one row value  not right";
				
			}
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr[5]//input[@type='text']"));
		for(int i=0;i<list.size();i++)
		{
			 BaseUtil.log("5color:"+list.get(i).getAttribute("class"));
			 BaseUtil.log("5value:"+list.get(i).getAttribute("value")+"datevalue:"+datelist.get(i+14).trim()+"1");
			if(!list.get(i).getAttribute("class").equals("examfieldgrey examfieldwhite"))
			{
				flag=false;
				msg=msg+"| update after, the  three row color not examfieldwhite";
			}
			if(!list.get(i).getAttribute("value").equals(datelist.get(i+14).trim()+"1"))
			{
				flag=false;
				msg=msg+"| update after, the three row value  not right";
				
			}
			
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_22']//table//tr[4]//input[@type='text']"));
		for(int i=0;i<list.size();i++)
		{
			 BaseUtil.log("4color:"+list.get(i).getAttribute("class"));
			 BaseUtil.log("4value:"+list.get(i).getAttribute("value")+"datevalue:"+datelist.get(i+8).trim());
			if(!list.get(i).getAttribute("class").equals("examfieldgrey"))
			{
				flag=false;
				msg=msg+"|not update, the two row color is error";
			}
			if(!list.get(i).getAttribute("value").equals(datelist.get(i+8).trim()))
			{
				flag=false;
				msg=msg+"| not update , the two row value  not right";
				
			}
		}
		
		}catch (Exception e) {
			flag=false;
			msg=msg+e.getMessage();
			e.printStackTrace();
		}
		
		
		return flag;
	}
}
