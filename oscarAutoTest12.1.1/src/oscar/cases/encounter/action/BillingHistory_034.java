package encounter.action;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import testlink.api.java.client.TestLinkAPIResults;
import com.oscarbase.*;

public class BillingHistory_034 {

	private static String homePageWin = "";
	private static String encounterWin = "";
	private static String lookWin = "";
	private static String addWin = "";
	private static  String billingWin = "";
	private static String msg = "";
	private static  String [] wins=new String[2];
	private static String expectedPageTitle="";
	private static String code="";
	private static String provider="";
	private static String dxCode="";
	private static String time="";
	private static String note="";
	private static String pDate;
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
		// @parameters for test data.
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy034");
		String testcaseName = p.getProperty("TestcaseName");
		expectedPageTitle=p.getProperty("ExpectedPageTitle");
		time=p.getProperty("time");
		code=p.getProperty("code");
		note=p.getProperty("note");
		provider=p.getProperty("provider");
		dxCode=p.getProperty("dxCode");
		String demogName= CaseConf.getInstance().getCreateAppointmentDemogName();
		String result = "";
		String note = "";
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		pDate = df.format(dt);
		try {
			BillingHistory_034 case090 = new BillingHistory_034();
			LoginOscar.login(driver);
			
			wins=createAppointment.create(action);
			homePageWin=wins[0];
			msg=msg+wins[1];
			
			wins=createAppointment.openEncounter(action, demogName);
			encounterWin=wins[0];
			msg=msg+wins[1];
			 Thread.sleep(8000);
			//case090.openEncounterWin();
			//div[@id='menuTitleBilling']//a
			
			if (case090.canOpenAddBilingHistoryWin()) {
				 BaseUtil.log("enter 1");
				 addBillingHistory();
				 BaseUtil.log("enter 2");
				 
				 if(case090.addVerify())
				 {   BaseUtil.log("enter 3");
					 updateBillingHistory();
					 BaseUtil.log("enter 4");
					 if(case090.updateVerify())
					 {
						 BaseUtil.log("pass");
					    result = TestLinkAPIResults.TEST_PASSED;
					
					 }else {
						   result = TestLinkAPIResults.TEST_FAILED;
							note = case090.getMsg();	
					}
				 }else {
					 result = TestLinkAPIResults.TEST_FAILED;
						note = case090.getMsg();	
				}
				
			}else{
				result = TestLinkAPIResults.TEST_FAILED;
				note = case090.getMsg();	
			}
			BaseUtil.log(note);
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();
		} finally {
			TestExecutionResult.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, note, result);
			System.out.println("BillingHistory_034: Execution Terminated at "
					+ pDate);
		}
		driver.quit();
		Thread.sleep(3000);
	}


	
   private boolean isBillinglistPresent1() {
	     action.selectWindow(encounterWin);
		if (action.isElementPresent(By.id("Billinglist"))) {
			int howManyBillings = action.getWebElements(
					action.getWebElement(By.id("Billinglist")),
					By.tagName("li")).size();
			if (howManyBillings > 0) {

				return true;
			} else {
				msg = " | No billing was listed on encounter page.";
				return false;
			}
		} else {
			msg = " | No billing list exists from the left navigator on encounter page.";
			return false;
		}
		
		
		
	}

   private boolean diseaseRegistryIsPresent() {
		if (action.isElementPresent(By.xpath("//div[@id='menuTitleBilling']"))) {
			return true;
		} else {
			msg =  msg+" | 'billing history' doesn't exist from the left navigator.";
			return false;
		}
	}

	
   private boolean canOpenAddBilingHistoryWin() {
       
		if (diseaseRegistryIsPresent()) {
			if (action.isElementPresent(
					action.getWebElement(By.id("menuTitleBilling")),
					By.linkText("+"))) {
			
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				action.click(By.xpath("//div[@id='menuTitleBilling']//a"));
				addWin= action.getNewPopupWinHdl(homePageWin,encounterWin);
				if (addWin != null) {
					action.selectWindow(addWin);
					String actualPageTitle = action.getPageTitle();
					BaseUtil.log(actualPageTitle);
					if (actualPageTitle.contains(expectedPageTitle.trim())) {
				       return true;
					} else {
						msg =  msg+" | Please make sure the open page is 'billing history' page.";
						return false;
					}

				} else {
					msg = msg+ " | No window will pop up by clicking '+' link.";
					return false;
				}
			} else {
				msg = msg+ " | Link '+' is not present";
				return false;
			}
		} else {
			return false;
		}
	} 
   
   public static void addBillingHistory()
   {
	   
	   action.selectByValue(By.xpath("//select[@name='xml_provider']"), provider);
	   action.clear(By.xpath("//input[@name='serviceCode0']"));
	   action.type(By.xpath("//input[@name='serviceCode0']"), code);
	   action.clear(By.xpath("//input[@name='serviceUnit0']"));
	   action.type(By.xpath("//input[@name='serviceUnit0']"), time);
	  // action.click(By.xpath("//div[@id='group1_MFP']//input[@id='xml_"+code+"']"));
	   action.clear(By.xpath("//input[@name='dxCode']"));
	   action.type(By.xpath("//input[@name='dxCode']"), dxCode);
	   action.click(By.xpath("//input[@name='submit']"));
	   
	   action.type(By.xpath("//textarea"), note);
	   
	   action.click(By.xpath("//input[@name='submit'][@value='Save']"));
	   
	   action.selectWindow(encounterWin);
	   action.refreshCurrentWindow();
	   try {
		Thread.sleep(6000);
	} catch (InterruptedException e) {
	}
	   
	 //ul[@id='Billinglist']//a[@title='A003A x 1 (346) - 2014-11-05']
	   
   }
   
   public static void updateBillingHistory()
   {
	   
	   action.clear(By.xpath("//textarea[@name='comment']"));
	   action.type(By.xpath("//textarea[@name='comment']"), note+"update");
	   
	   action.clear(By.xpath("//input[@name='billingunit0']"));
	   action.type(By.xpath("//input[@name='billingunit0']"), "1");
	
	   action.clear(By.xpath("//input[@id='billingamount0']"));
	   //deal excetion
	   action.click(By.xpath("//input[@id='billingamount0']"));
	   action.backSpace(By.xpath("//input[@id='billingamount0']"), 4);
	   action.type(By.xpath("//input[@id='billingamount0']"),"21.70");
	   action.click(By.xpath("//input[@value='Submit']"));
	   //action.dealAlert(true);
	   //action.selectWindow(encounterWin);
	   //action.click(By.xpath("//div[@id='Billing']//a[contains(text(),'Billing History')]"));
	  // billingWin=action.getNewPopupWinHdl(homePageWin,encounterWin);
	   action.selectWindow(billingWin);
	   action.refreshCurrentWindow();
	   BaseUtil.log("refush ok");
	   
   }
   
   
    public static boolean addVerify()
   {
	   boolean flag=true;
	   if(action.isElementPresent(By.xpath("//img[@id='imgBilling5']")))
	   {
		   action.click(By.xpath("//img[@id='imgBilling5']"));
	   }
	   
	   BaseUtil.log(code+" x "+time+" ("+dxCode+") - "+pDate);
	   if(!action.isElementPresent(By.xpath("//ul[@id='Billinglist']//a[@title='"+code+" x "+time+" ("+dxCode+") - "+pDate+"']")))
	   {
		   msg=msg+"| add billing history not success";
		   flag=false;
	   }
    	
	   
	  // action.click(By.linkText("Billing History"));
	   action.click(By.xpath("//div[@id='Billing']//a[contains(text(),'Billing History')]"));
	   billingWin=action.getNewPopupWinHdl(homePageWin,encounterWin);
	   
	   if(billingWin!=null)
	   {
	   action.selectWindow(billingWin);
	   
	   if(!(action.isElementPresent(By.xpath("//table//tr//td[contains(text(),'"+pDate+"')]"))&& action.isElementPresent(By.xpath("//table//tr//td[contains(text(),'"+dxCode+"')]"))&&action.isElementPresent(By.xpath("//table//tr//td[contains(text(),'"+code+" x "+time+"')]"))))
	      {
		   
		   msg=msg+"| billing history list not exist this billing history";
		   flag=false;
		   
			}
	     action.click(By.xpath("//table//tr//td[contains(text(),'"+code+" x "+time+"')]/preceding-sibling::td//a[1]"));
	    
	    
	     
	    lookWin=action.getNewPopupWinHdl(homePageWin, encounterWin, billingWin);
	    action.selectWindow(lookWin);
	   // action.dealAlert(true);
	    
	    String dxcodevalue=action.getWebElement(By.xpath("//input[@name='xml_diagnostic_detail']")).getAttribute("value").trim();
	    String datevalue=action.getWebElement(By.xpath("//input[@name='xml_appointment_date']")).getAttribute("value").trim();
	    String codevalue=action.getWebElement(By.xpath("//input[@name='servicecode0']")).getAttribute("value").trim();
	    String timevalue=action.getWebElement(By.xpath("//input[@name='billingunit0']")).getAttribute("value").trim();
	    String notevalue=action.getWebElement(By.xpath("//textarea[@name='comment']")).getAttribute("value").trim();
	    
	    if(!(dxcodevalue.equals(dxCode)&&datevalue.equals(pDate)&&codevalue.equals(code)&&timevalue.equals(time)&&notevalue.equals(note)))
	    {
	    	   msg=msg+"| the billing content is error in the look view.";
			   flag=false;
	    	
	    }
	    action.closeCurrentWindow();
	    action.selectWindow(billingWin);
	    
	    action.click(By.xpath("//table//tr//td[contains(text(),'"+code+" x "+time+"')]/preceding-sibling::td//a[2]"));
	   
	    lookWin=action.getNewPopupWinHdl(homePageWin, encounterWin, billingWin);
	    action.selectWindow(lookWin);
	    //action.dealAlert(true);
	    
	   dxcodevalue=action.getWebElement(By.xpath("//input[@name='xml_diagnostic_detail']")).getAttribute("value").trim();
	   datevalue=action.getWebElement(By.xpath("//input[@name='xml_appointment_date']")).getAttribute("value").trim();
	   codevalue=action.getWebElement(By.xpath("//input[@name='servicecode0']")).getAttribute("value").trim();
	   timevalue=action.getWebElement(By.xpath("//input[@name='billingunit0']")).getAttribute("value").trim();
	   notevalue=action.getWebElement(By.xpath("//textarea[@name='comment']")).getAttribute("value").trim();
	    
	    if(!(dxcodevalue.equals(dxCode)&&datevalue.equals(pDate)&&codevalue.equals(code)&&timevalue.equals(time)&&notevalue.equals(note)))
	    {
	    	   msg=msg+"| the billing content is error in the edit view .";
			   flag=false;
	    	
	    }
	    
	   }else {
		msg=msg+"| this link can't open";
	}
	   
	   return flag;
   }
   
   
    private boolean updateVerify()
    {
    	boolean flag=true;
    	if(action.isElementPresent(By.xpath("//table//tr//td[contains(text(),'"+code+" x "+time+"')]")))
    	{
    		msg=msg+"| after update time,the time not change";
    		flag=false;
    	}
    	
    	action.click(By.xpath("//a[1]"));
    	
    	lookWin=action.getNewPopupWinHdl(homePageWin, encounterWin, billingWin);
 	    action.selectWindow(lookWin);
 	   // action.dealAlert(true);
 	    String timevalue=action.getWebElement(By.xpath("//input[@name='billingunit0']")).getAttribute("value").trim();
 	    String notevalue=action.getWebElement(By.xpath("//textarea[@name='comment']")).getAttribute("value").trim();
 	    
 	    if(!(timevalue.equals("1")&&notevalue.equals(note+"update")))
 	    	
 	    {
 	    	   msg=msg+"| update after .the content not update int the look view";
 			   flag=false;
 	    	
 	    }
    	
 	   action.closeCurrentWindow();
	   action.selectWindow(billingWin);
    	
	   action.click(By.xpath("//a[2]"));
	   
	    lookWin=action.getNewPopupWinHdl(homePageWin, encounterWin, billingWin);
	    action.selectWindow(lookWin);
	   // action.dealAlert(true);
	    timevalue=action.getWebElement(By.xpath("//input[@name='billingunit0']")).getAttribute("value").trim();
 	    notevalue=action.getWebElement(By.xpath("//textarea[@name='comment']")).getAttribute("value").trim();
 	    
       if(!(timevalue.equals("1")&&notevalue.equals(note+"update")))
 	    	
 	    {
 	    	   msg=msg+"| update after .the content not update int the edit view";
 			   flag=false;
 	    	
 	    }
       action.closeCurrentWindow();
       action.selectWindow(billingWin);
       action.click(By.linkText("Unbill"));
	    
    	return flag;
    	
    }
    
    
	private String getMsg() {
		return msg;
	}
}
