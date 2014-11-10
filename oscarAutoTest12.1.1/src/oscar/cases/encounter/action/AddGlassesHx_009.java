package encounter.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.xalan.templates.ElemLiteralResult.LiteralElementAttributes;
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

public class AddGlassesHx_009{

	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static List<WebElement> list=new ArrayList<WebElement>();
	private static String msg = "";
	private static   String stylevalue="";
	private static String divid="//div[@id='s_20']";
	private static String aid="//a[@id='a_20']";
	private static List<String> dataList=new ArrayList<String>() ;
	private static List<String> webList=new ArrayList<String>() ;
	private static String upDate2="";
	private static String upDate1="";
	private static String tr1td1num=null;
	private static String tr1td2num=null;
	private static String tr1td3num=null;
	private static String tr1td4num=null;
	private static String tr1td5num=null;
	private static String tr1td6num=null;
	private static String tr1td7num=null;
	private static String tr1td8num=null;
	private static String tr1td9num=null;
	private static String tr1td10num=null;
	
	
   	private static String tr2td1num=null;
	private static String tr2td2num=null;
	private static String tr2td3num=null;
	private static String tr2td4num=null;
	private static String tr2td5num=null;
	private static String tr2td6num=null;
	private static String tr2td7num=null;
	private static String tr2td8num=null;
	private static String tr2td9num=null;
	private static String tr2td10num=null;
	
	private static String tr3td1num=null;
	private static String tr3td2num=null;
	private static String tr3td3num=null;
	private static String tr3td4num=null;
	private static String tr3td5num=null;
	private static String tr3td6num=null;
	private static String tr3td7num=null;
	private static String tr3td8num=null;
	private static String tr3td9num=null;
	private static String tr3td10num=null;
	
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
	
	private static String typeSelectValue1=null;
	private static String typeSelectValue2=null;
	private static String typeSelectValue3=null;
	private static String typeSelectValue4=null;
	
	private static String dateSelectValue1=null;
	private static String dateSelectValue2=null;
	private static String dateSelectValue3=null;
	private static String dateSelectValue4=null;
	
	private static String noteValue1=null;
	private static String noteValue2=null;
	private static String noteValue3=null;
	private static String noteValue4=null;
	
	
	private static  String homePageWin= "";
	private static  String patientSearch = "";
	private static  String encounterWin="";
	private static  String expectedLogContent = "";
	private static  String noteWin="";
	private static  String [] wins=new String[2];
	

	public static void run() throws Exception {
 

		FirefoxProfile profile = new FirefoxProfile();  
		profile.setPreference("intl.accept_languages", "en-US");   
		driver = new FirefoxDriver(profile); 
		action=new WebDriverActions(driver);
		action.driverWait(10);
		String projectName = CaseConf.getInstance().getProjectName();
		String testplanName = CaseConf.getInstance().getTestplanName();
		String build = CaseConf.getInstance().getBuild();
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		String demogName= CaseConf.getInstance().getCreateAppointmentDemogName();
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy009");
		String testcaseName = p.getProperty("TestcaseName");
		
		
		upDate1=p.getProperty("upDate1");
		upDate2=p.getProperty("upDate2");
		tr1td1num=p.getProperty("tr1td1num");
		tr1td2num=p.getProperty("tr1td2num");
		tr1td3num=p.getProperty("tr1td3num");
		tr1td4num=p.getProperty("tr1td4num");
		tr1td5num=p.getProperty("tr1td5num");
		tr1td6num=p.getProperty("tr1td6num");
	    tr1td7num=p.getProperty("tr1td7num");
	    tr1td8num=p.getProperty("tr1td8num");
		tr1td9num=p.getProperty("tr1td9num");
	    tr1td10num=p.getProperty("tr1td10num");
	    
	    tr2td1num=p.getProperty("tr2td1num");
	    tr2td2num=p.getProperty("tr2td2num");
	    tr2td3num=p.getProperty("tr2td3num");
	    tr2td4num=p.getProperty("tr2td4num");
	    tr2td5num=p.getProperty("tr2td5num");
	    tr2td6num=p.getProperty("tr2td6num");
	    tr2td7num=p.getProperty("tr2td7num");
	    tr2td8num=p.getProperty("tr2td8num");
	    tr2td9num=p.getProperty("tr2td9num");
	    tr2td10num=p.getProperty("tr2td10num");
		 
		 tr3td1num=p.getProperty("tr3td1num");
		 tr3td2num=p.getProperty("tr3td2num");
		 tr3td3num=p.getProperty("tr3td3num");
		 tr3td4num=p.getProperty("tr3td4num");
		 tr3td5num=p.getProperty("tr3td5num");
		 tr3td6num=p.getProperty("tr3td6num");
		 tr3td7num=p.getProperty("tr3td7num");
		 tr3td8num=p.getProperty("tr3td8num");
		 tr3td9num=p.getProperty("tr3td9num");
		 tr3td10num=p.getProperty("tr3td10num");
		 
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
		
		typeSelectValue1=p.getProperty("typeSelectValue1");
		typeSelectValue2=p.getProperty("typeSelectValue2");
		typeSelectValue3=p.getProperty("typeSelectValue3");
		typeSelectValue4=p.getProperty("typeSelectValue4");
		
		dateSelectValue1=p.getProperty("dateSelectValue1");
		dateSelectValue2=p.getProperty("dateSelectValue2");
		dateSelectValue3=p.getProperty("dateSelectValue3");
		dateSelectValue4=p.getProperty("dateSelectValue4");
		
	
		
		dataList.add(typeSelectValue1);
		dataList.add(tr1td1num);
		dataList.add(tr1td2num);
		dataList.add(tr1td3num);
		dataList.add(tr1td4num);
		dataList.add(tr1td5num);
		dataList.add(tr1td6num);
		dataList.add(tr1td7num);
		dataList.add(tr1td8num);
		dataList.add(tr1td9num);
		dataList.add(tr1td10num);
		dataList.add(dateSelectValue1);
		
		dataList.add(typeSelectValue2);
		dataList.add(tr2td1num);
		dataList.add(tr2td2num);
		dataList.add(tr2td3num);
		dataList.add(tr2td4num);
		dataList.add(tr2td5num);
		dataList.add(tr2td6num);
		dataList.add(tr2td7num);
		dataList.add(tr2td8num);
		dataList.add(tr2td9num);
		dataList.add(tr2td10num);
		dataList.add(dateSelectValue2);
		
		dataList.add(typeSelectValue3);
		dataList.add(tr3td1num);
		dataList.add(tr3td2num);
		dataList.add(tr3td3num);
		dataList.add(tr3td4num);
		dataList.add(tr3td5num);
		dataList.add(tr3td6num);
		dataList.add(tr3td7num);
		dataList.add(tr3td8num);
		dataList.add(tr3td9num);
		dataList.add(tr3td10num);
		dataList.add(dateSelectValue3);
		
		dataList.add(typeSelectValue4);
		dataList.add(tr4td1num);
		dataList.add(tr4td2num);
		dataList.add(tr4td3num);
		dataList.add(tr4td4num);
		dataList.add(tr4td5num);
		dataList.add(tr4td6num);
		dataList.add(tr4td7num);
		dataList.add(tr4td8num);
		dataList.add(tr4td9num);
		dataList.add(tr4td10num);
		dataList.add(dateSelectValue4);
		
		noteValue1=p.getProperty("noteValue1");
		noteValue2=p.getProperty("noteValue2");
		noteValue3=p.getProperty("noteValue3");
		noteValue4=p.getProperty("noteValue4");
		
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
				 action.click(By.linkText("Glasses Hx:"));
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
			BaseUtil.log(getMsg());
		} 
		catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();

	  }
		
		finally{
		    TestExecutionResult.reportTestCaseResult(projectName, testplanName,
			testcaseName, build, note, result);
		    System.out.println("AddGlassesHx_009: Execution Terminated at " + dt);
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

	
	private static void addNewLog() throws InterruptedException {
		
		action.click(By.xpath("//a[contains(text(),'[add]')]"));
		action.click(By.xpath("//a[contains(text(),'[add]')]"));
		action.click(By.xpath("//a[contains(text(),'[add]')]"));
		
		list=action.getWebElements(By.xpath("//div[@id='s_20']//table//tr[3]//input"));
		action.selectByValue(By.xpath("//select[@id='type001']"), typeSelectValue1);
		for(int i=0;i<11;i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(dataList.get(i+1));
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
		Thread.sleep(100);
		list.get(8).clear();
		list.get(8).sendKeys(tr1td9num);
		Thread.sleep(100);
		list.get(9).clear();
		list.get(9).sendKeys(tr1td10num);
		Thread.sleep(100);
		
		//SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		//Date date=new Date(dateSelectValue1.split("-")[0]+"/"+dateSelectValue1.split("-")[1]+"/"+dateSelectValue1.split("-")[2]);
		//BaseUtil.log(date+"");
		list.get(10).clear();
	    list.get(10).sendKeys(dateSelectValue1);
		Thread.sleep(100);
		*/
		list.clear();
		
		action.click(By.xpath("//div[@id='s_20']//table//tr[3]//a"));
		Thread.sleep(1000);
		noteWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
		action.selectWindow(noteWin);
		action.type(By.xpath("//textarea"), noteValue1);
		action.click(By.xpath("//input[@value='Save']"));
		Thread.sleep(2000);
		action.selectWindow(encounterWin);
		
		
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_20']//table//tr[4]//input"));
		action.selectByValue(By.xpath("//select[@id='type002']"), typeSelectValue2);
		
		for(int i=0;i<11;i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(dataList.get(i+13));
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
		Thread.sleep(100);
		list.get(6).clear();
		list.get(6).sendKeys(tr2td7num);
		Thread.sleep(100);
		list.get(7).clear();
		list.get(7).sendKeys(tr2td8num);
		Thread.sleep(100);
		list.get(8).clear();
		list.get(8).sendKeys(tr2td9num);
		Thread.sleep(100);
		list.get(9).clear();
		list.get(9).sendKeys(tr2td10num);
		Thread.sleep(100);
		list.get(10).clear();
		list.get(10).sendKeys(dateSelectValue2);
		Thread.sleep(100);*/
		list.clear();
		
		action.click(By.xpath("//div[@id='s_20']//table//tr[4]//a"));
		noteWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
		action.selectWindow(noteWin);
		action.type(By.xpath("//textarea"), noteValue2);
		action.click(By.xpath("//input[@value='Save']"));
		Thread.sleep(2000);
		action.selectWindow(encounterWin);
		
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_20']//table//tr[5]//input"));
		action.selectByValue(By.xpath("//select[@id='type003']"), typeSelectValue3);
		
		for(int i=0;i<11;i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(dataList.get(i+25));
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
		Thread.sleep(100);
		list.get(8).clear();
		list.get(8).sendKeys(tr3td9num);
		Thread.sleep(100);
		list.get(9).clear();
		list.get(9).sendKeys(tr3td10num);
		Thread.sleep(100);
		list.get(10).clear();
		list.get(10).sendKeys(dateSelectValue3);
		Thread.sleep(100);*/
		list.clear();
		
		action.click(By.xpath("//div[@id='s_20']//table//tr[5]//a"));
		noteWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
		action.selectWindow(noteWin);
		action.type(By.xpath("//textarea"), noteValue3);
		action.click(By.xpath("//input[@value='Save']"));
		Thread.sleep(2000);
		action.selectWindow(encounterWin);
		
		
		
		
		list=action.getWebElements(By.xpath("//div[@id='s_20']//table//tr[6]//input"));
		action.selectByValue(By.xpath("//select[@id='type004']"), typeSelectValue4);
		
		for(int i=0;i<11;i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(dataList.get(i+37));
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
		Thread.sleep(100);
		list.get(10).clear();
		list.get(10).sendKeys(dateSelectValue4);
		Thread.sleep(100);*/
		list.clear();
		
		action.click(By.xpath("//div[@id='s_20']//table//tr[6]//a"));
		noteWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
		action.selectWindow(noteWin);
		action.type(By.xpath("//textarea"), noteValue4);
		action.click(By.xpath("//input[@value='Save']"));
		Thread.sleep(2000);
		action.selectWindow(encounterWin);
		
	 
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
		
		 //String str =(String) ((JavascriptExecutor)driver).executeScript(" function chacktest(){ var n=0;var l=document.getElementById('s_20'); var ll=l.getElementsByTagName('input');  for(var i=0;i<ll.length;i++){ if( ll[i].value=="+tr1td1num+"){  n=n+1;}   if( ll[i].value=="+tr1td2num+"){  n=n+1;}   if( ll[i].value=="+tr1td3num+"){  n=n+1;}    if( ll[i].value=="+tr1td4num+"){  n=n+1;}    if( ll[i].value=="+tr1td5num+"){  n=n+1;}    if( ll[i].value=="+tr1td6num+"){  n=n+1;}    if( ll[i].value=="+tr1td7num+"){  n=n+1;}     if( ll[i].value=="+tr1td8num+"){  n=n+1;}  if( ll[i].value=="+tr1td9num+"){  n=n+1;}  if( ll[i].value=="+tr1td10num+"){  n=n+1;}           if( ll[i].value=="+tr2td1num+"){  n=n+1;}   if( ll[i].value=="+tr2td2num+"){  n=n+1;}  if( ll[i].value=="+tr2td3num+"){  n=n+1;}   if( ll[i].value=="+tr2td4num+"){  n=n+1;}  if( ll[i].value=="+tr2td5num+"){  n=n+1;}   if( ll[i].value=="+tr2td6num+"){  n=n+1;}   if( ll[i].value=="+tr2td7num+"){  n=n+1;}     if( ll[i].value=="+tr2td8num+"){  n=n+1;}    if( ll[i].value=="+tr2td9num+"){  n=n+1;}  if( ll[i].value=="+tr2td10num+"){  n=n+1;}   if( ll[i].value=="+tr4td1num+"){  n=n+1;}    if( ll[i].value=="+tr4td3num+"){  n=n+1;}   if( ll[i].value=="+tr4td2num+"){  n=n+1;}  if( ll[i].value=="+tr4td4num+"){  n=n+1;}    if( ll[i].value=="+tr4td5num+"){  n=n+1;}    if( ll[i].value=="+tr4td6num+"){  n=n+1;}      if( ll[i].value=="+tr4td7num+"){  n=n+1;}   if( ll[i].value=="+tr4td8num+"){  n=n+1;}   if( ll[i].value=="+tr4td9num+"){  n=n+1;}   if( ll[i].value=="+tr4td10num+"){  n=n+1;}    if( ll[i].value=="+tr3td1num+"){  n=n+1;}    if( ll[i].value=="+tr3td2num+"){  n=n+1;}    if( ll[i].value=="+tr3td3num+"){  n=n+1;}    if( ll[i].value=="+tr3td4num+"){  n=n+1;}    if( ll[i].value=="+tr3td5num+"){  n=n+1;}    if( ll[i].value=="+tr3td6num+"){  n=n+1;}   if( ll[i].value=="+tr3td7num+"){  n=n+1;}   if( ll[i].value=="+tr3td8num+"){  n=n+1;}   if( ll[i].value=="+tr3td9num+"){  n=n+1;} if( ll[i].value=="+tr3td10num+"){  n=n+1;}                }   if(n==40){ return 'true' ;} else{return 'false';} }  return chacktest()");
		   boolean flag=true;
		   try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
		    action.click(By.xpath("//div[@id='s_20']//table//tr[6]//a"));
			noteWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
			action.selectWindow(noteWin);
			String checkNote4=action.getText(By.xpath("//textarea"));
			action.click(By.xpath("//input[@value='Cancel']"));
			action.selectWindow(encounterWin);

				    action.click(By.xpath("//div[@id='s_20']//table//tr[5]//a"));
				    try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}
					noteWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
					action.selectWindow(noteWin);
					String checkNote3=action.getText(By.xpath("//textarea"));
					action.click(By.xpath("//input[@value='Cancel']"));
					action.selectWindow(encounterWin);
					
					
				 action.click(By.xpath("//div[@id='s_20']//table//tr[4]//a"));
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}
					noteWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
					action.selectWindow(noteWin);
					String checkNote2=action.getText(By.xpath("//textarea"));
					action.click(By.xpath("//input[@value='Cancel']"));
					action.selectWindow(encounterWin);
				
					  action.click(By.xpath("//div[@id='s_20']//table//tr[3]//a"));
						noteWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
						}
						action.selectWindow(noteWin);
						String checkNote1=action.getText(By.xpath("//textarea"));
						action.click(By.xpath("//input[@value='Cancel']"));
						action.selectWindow(encounterWin);			
					
	     getAllValue();
	     for(int i=0;i<webList.size();i++)
	     {
	    	 if(!webList.get(i).trim().equals((dataList.get(i).trim())))
	    	 {
	    		 BaseUtil.log(webList.get(i).trim()+"******"+dataList.get(i).trim());
	    		 flag=false;
	    	 }
	    	
	     }
	     BaseUtil.log(flag+"");
	     
	     
	     
		if(flag&&noteValue4.equals(checkNote4)&&noteValue3.equals(checkNote3)&&noteValue2.equals(checkNote2)&&noteValue1.equals(checkNote1))
		{
			return true;
		}else {
			msg=msg+"| add not success";
			return false;
		}
	}
	
		
	private static String getMsg() {
		return msg;
	}
	
	private static boolean verifyUpdateIsOk()
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

		list=action.getWebElements(By.xpath("//div[@id='s_20']//table//tr//input[@type='text']"));
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log("begincolor:"+list.get(i).getAttribute("class").trim());
			boolean check=list.get(i).getAttribute("class").trim().equals("examfieldgrey");
			if(!check)
			{
				flag=false;
			
				msg=msg+"| color is error, not all examfieldgrey";
			}
			
			Thread.sleep(100);
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_20']//table//tr[3]//input[@type='text']"));
		for(int i=0;i<list.size()-1;i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(dataList.get(i+1).trim()+"1");
			Thread.sleep(100);
		}
		list.get(list.size()-1).clear();
		list.get(list.size()-1).sendKeys(upDate1);
		
		list=action.getWebElements(By.xpath("//div[@id='s_20']//table//tr[5]//input[@type='text']"));
		for(int i=0;i<list.size()-1;i++)
		{
			list.get(i).clear();
			list.get(i).sendKeys(dataList.get(i+25).trim()+"1");
			Thread.sleep(100);
		}
		list.get(list.size()-1).clear();
		list.get(list.size()-1).sendKeys(upDate2);
		
		action.click(By.xpath("//a[@id='save_measurements']"));
	    Thread.sleep(1000);
		action.refreshCurrentWindow();
		Thread.sleep(6000);
		
		list=action.getWebElements(By.xpath("//div[@id='s_20']//table//tr[3]//input[@type='text']"));
		for(int i=0;i<list.size();i++)
		{   
			BaseUtil.log("1color:"+list.get(i).getAttribute("class"));
			if(!list.get(i).getAttribute("class").equals("examfieldwhite"))
			{
				flag=false;
				msg=msg+"| update after, the one color not examfieldwhite";
			}
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_20']//table//tr[5]//input[@type='text']"));
		for(int i=0;i<list.size();i++)
		{  
			BaseUtil.log("3color:"+list.get(i).getAttribute("class"));
			if(!list.get(i).getAttribute("class").equals("examfieldwhite"))
			{
				flag=false;
				msg=msg+"| update after, the color not examfieldwhite";
			}
			
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_20']//table//tr[4]//input[@type='text']"));
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log("2color:"+list.get(i).getAttribute("class"));
			if(!list.get(i).getAttribute("class").equals("examfieldgrey"))
			{
				flag=false;
				msg=msg+"|not update, the color is error";
			}
		}
		
		list=action.getWebElements(By.xpath("//div[@id='s_20']//table//tr[6]//input[@type='text']"));
		for(int i=0;i<list.size();i++)
		{
			BaseUtil.log("4color:"+list.get(i).getAttribute("class"));
			if(!list.get(i).getAttribute("class").equals("examfieldgrey"))
			{
				flag=false;
				msg=msg+"|not update, the color is error";
			}
			
		}
		
		 webList.clear();
		 getAllValue();
		  for(int i=1;i<11;i++)
		     {
			     BaseUtil.log(webList.get(i).trim()+"******"+dataList.get(i).trim()+"1");
		    	 if(!(dataList.get(i).trim()+"1").equals((webList.get(i).trim())))
		    	 {
		    		 
		    		 flag=false;
		    		 msg=msg+"|after update, the value is error";
		    	 }
		    	
		     }
		  
		  BaseUtil.log(webList.get(11).trim()+"******"+upDate1);
		   if(!(webList.get(11).trim().equals(upDate1)))
		   {
			   
			     BaseUtil.log(webList.get(11).trim()+"******"+upDate1);
	    		 flag=false;
	    		 msg=msg+"|after update, the datevalue1 is error";
		   }
		  
		  for(int i=12;i<24;i++)
		     {
			     BaseUtil.log(webList.get(i).trim()+"******"+dataList.get(i).trim());
		    	 if(!(webList.get(i).trim()).equals((dataList.get(i).trim())))
		    	 {
		    		 
		    		 flag=false;
		    		 msg=msg+"|not update, the value is error";
		    	 }
		    	
		     }
		  
		  for(int i=25;i<35;i++)
		     {
			  BaseUtil.log(webList.get(i).trim()+"******"+dataList.get(i).trim()+"1");
		    	 if(!(dataList.get(i).trim()+"1").equals((webList.get(i).trim())))
		    	 {
		    		 
		    		 flag=false;
		    		 msg=msg+"|after update, the value is error";
		    	 }
		    	
		     }
		  
		  BaseUtil.log(webList.get(11).trim()+"******"+upDate2);
		  if(!(webList.get(35).trim().equals(upDate2)))
		   {
			   
			    
	    		 flag=false;
	    		 msg=msg+"|after update, the datevalue2 is error";
		   }
		  for(int i=36;i<48;i++)
		     {
			  BaseUtil.log(webList.get(i).trim()+"******"+dataList.get(i).trim());
		    	 if(!(webList.get(i).trim()).equals((dataList.get(i).trim())))
		    	 {
		    		
		    		 flag=false;
		    		 msg=msg+"|not update, the value is error";
		    	 }
		    	
		     }
		  
		  BaseUtil.log(flag+"again");
		
		} catch (Exception e) {
			flag=false;
			msg=msg+e.getMessage();
			e.printStackTrace();
			
		}
		
		
		  return flag;
	}
	
	public static void getAllValue()
	{
		
		String select1=action.getText(By.xpath("//select[@id='type001']/option[1]"));
		String select2=action.getText(By.xpath("//select[@id='type002']/option[2]"));
		String select3=action.getText(By.xpath("//select[@id='type003']/option[3]"));
		String select4=action.getText(By.xpath("//select[@id='type004']/option[4]"));
		
	
		String odSphValue1=action.getValue(By.xpath("//input[@id='odSph1']"), "value");
		String odSphValue2=action.getValue(By.xpath("//input[@id='odSph2']"), "value");
		String odSphValue3=action.getValue(By.xpath("//input[@id='odSph3']"), "value");
		String odSphValue4=action.getValue(By.xpath("//input[@id='odSph4']"), "value");
		
		String odCylValue1=action.getValue(By.xpath("//input[@id='odCyl1']"), "value");
		String odCylValue2=action.getValue(By.xpath("//input[@id='odCyl2']"), "value");
		String odCylValue3=action.getValue(By.xpath("//input[@id='odCyl3']"), "value");
		String odCylValue4=action.getValue(By.xpath("//input[@id='odCyl4']"), "value");
			
		
		String odAxisValue1=action.getValue(By.xpath("//input[@id='odAxis1']"), "value");
		String odAxisValue2=action.getValue(By.xpath("//input[@id='odAxis2']"), "value");
		String odAxisValue3=action.getValue(By.xpath("//input[@id='odAxis3']"), "value");
		String odAxisValue4=action.getValue(By.xpath("//input[@id='odAxis4']"), "value");
		
		String odAddValue1=action.getValue(By.xpath("//input[@id='odAdd1']"), "value");
		String odAddValue2=action.getValue(By.xpath("//input[@id='odAdd2']"), "value");
		String odAddValue3=action.getValue(By.xpath("//input[@id='odAdd3']"), "value");
		String odAddValue4=action.getValue(By.xpath("//input[@id='odAdd4']"), "value");
		
		
		String odPrismValue1=action.getValue(By.xpath("//input[@id='odPrism1']"), "value");
		String odPrismValue2=action.getValue(By.xpath("//input[@id='odPrism2']"), "value");
		String odPrismValue3=action.getValue(By.xpath("//input[@id='odPrism3']"), "value");
		String odPrismValue4=action.getValue(By.xpath("//input[@id='odPrism4']"), "value");
		
		String osSphValue1=action.getValue(By.xpath("//input[@id='osSph1']"), "value");
		String osSphValue2=action.getValue(By.xpath("//input[@id='osSph2']"), "value");
		String osSphValue3=action.getValue(By.xpath("//input[@id='osSph3']"), "value");
		String osSphValue4=action.getValue(By.xpath("//input[@id='osSph4']"), "value");
				
		
		String osCylValue1=action.getValue(By.xpath("//input[@id='osCyl1']"), "value");
		String osCylValue2=action.getValue(By.xpath("//input[@id='osCyl2']"), "value");
		String osCylValue3=action.getValue(By.xpath("//input[@id='osCyl3']"), "value");
		String osCylValue4=action.getValue(By.xpath("//input[@id='osCyl4']"), "value");
		
		String osAxisValue1=action.getValue(By.xpath("//input[@id='osAxis1']"), "value");
		String osAxisValue2=action.getValue(By.xpath("//input[@id='osAxis2']"), "value");
		String osAxisValue3=action.getValue(By.xpath("//input[@id='osAxis3']"), "value");
		String osAxisValue4=action.getValue(By.xpath("//input[@id='osAxis4']"), "value");
		
		String osAddValue1=action.getValue(By.xpath("//input[@id='osAdd1']"), "value");
		String osAddValue2=action.getValue(By.xpath("//input[@id='osAdd2']"), "value");
		String osAddValue3=action.getValue(By.xpath("//input[@id='osAdd3']"), "value");
		String osAddValue4=action.getValue(By.xpath("//input[@id='osAdd4']"), "value");
		
		
		String osPrismValue1=action.getValue(By.xpath("//input[@id='osPrism1']"), "value");
		String osPrismValue2=action.getValue(By.xpath("//input[@id='osPrism2']"), "value");
		String osPrismValue3=action.getValue(By.xpath("//input[@id='osPrism3']"), "value");
		String osPrismValue4=action.getValue(By.xpath("//input[@id='osPrism4']"), "value");
		
		String gl_dateValue1=action.getValue(By.xpath("//input[@id='gl_date1']"), "value");
		String gl_dateValue2=action.getValue(By.xpath("//input[@id='gl_date2']"), "value");
		String gl_dateValue3=action.getValue(By.xpath("//input[@id='gl_date3']"), "value");
		String gl_dateValue4=action.getValue(By.xpath("//input[@id='gl_date4']"), "value");
		
		
     webList.add(select1); 
     webList.add(odSphValue1);
     webList.add(odCylValue1); 
     webList.add(odAxisValue1);	
     webList.add(odAddValue1); 
     webList.add(odPrismValue1);
     webList.add(osSphValue1); 
     webList.add(osCylValue1);	
     webList.add(osAxisValue1); 
     webList.add(osAddValue1);
     webList.add(osPrismValue1); 
     webList.add(gl_dateValue1);
		
     webList.add(select2); 
     webList.add(odSphValue2);
     webList.add(odCylValue2); 
     webList.add(odAxisValue2);	
     webList.add(odAddValue2); 
     webList.add(odPrismValue2);
     webList.add(osSphValue2); 
     webList.add(osCylValue2);	
     webList.add(osAxisValue2); 
     webList.add(osAddValue2);
     webList.add(osPrismValue2); 
     webList.add(gl_dateValue2);
     
     webList.add(select3); 
     webList.add(odSphValue3);
     webList.add(odCylValue3); 
     webList.add(odAxisValue3);	
     webList.add(odAddValue3); 
     webList.add(odPrismValue3);
     webList.add(osSphValue3); 
     webList.add(osCylValue3);	
     webList.add(osAxisValue3); 
     webList.add(osAddValue3);
     webList.add(osPrismValue3); 
     webList.add(gl_dateValue3);
     
     webList.add(select4); 
     webList.add(odSphValue4);
     webList.add(odCylValue4); 
     webList.add(odAxisValue4);	
     webList.add(odAddValue4); 
     webList.add(odPrismValue4);
     webList.add(osSphValue4); 
     webList.add(osCylValue4);	
     webList.add(osAxisValue4); 
     webList.add(osAddValue4);
     webList.add(osPrismValue4); 
     webList.add(gl_dateValue4);
		
	}
}
