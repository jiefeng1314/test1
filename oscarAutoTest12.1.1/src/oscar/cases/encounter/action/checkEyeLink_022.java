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

public class checkEyeLink_022{

	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static List<String> linklist=new ArrayList<String>();
	private static String msg = "";
	private static String stylevalue="";
	private static  String homePageWin = "";
	private static  String encounterWin="";
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy022");
		String testcaseName = p.getProperty("TestcaseName");
		
		linklist.add("//div[@id='s_20']");
		linklist.add("//div[@id='s_22']");
		linklist.add("//div[@id='s_23']");
		linklist.add("//div[@id='s_24']");
		linklist.add("//div[@id='s_25']");
		linklist.add("//div[@id='s_26']");
		linklist.add("//div[@id='s_32']");
		linklist.add("//div[@id='s_33']");
		linklist.add("//div[@id='s_27']");
		linklist.add("//div[@id='s_28']");
		linklist.add("//div[@id='s_29']");
		linklist.add("//div[@id='s_30']");
		linklist.add("//div[@id='s_31']");
		
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
			 if(isLinExist("[expand all sections]"))
			 {
				 BaseUtil.log("enter 1");
				 action.click(By.linkText("[expand all sections]"));
				 if(openSectionsLinkVerify())
				 {
					 BaseUtil.log("enter 2");
					 
					 action.click(By.linkText("[collapse all sections]"));
					 if(closeSectionsLinkVerify())
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
		    System.out.println("checkEyeLink_022: Execution Terminated at " + dt);
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
	
	
	private static boolean openSectionsLinkVerify() {
		boolean flag=true;
		for(int i=0;i<linklist.size();i++)
		{
			String stylevalue=action.getValue(By.xpath(linklist.get(i)), "style");
			if(stylevalue.equals("display: none;"))
			{
				msg = msg + " | expand all sections link not Ok";
				flag=false;
			}
			
		}
		return flag;
	}
	
	private static boolean closeSectionsLinkVerify() {
		boolean flag=true;
		for(int i=0;i<linklist.size();i++)
		{
			String stylevalue=action.getValue(By.xpath(linklist.get(i)), "style");
			if(!stylevalue.equals("display: none;"))
			{
				msg = msg + " |collapse all sections link not Ok";
				flag=false;
			}
			
		}
		return flag;
	}
	
	/*private static boolean checkSectionslinkVerify() {
		boolean flag=true;
		for(int i=0;i<linklist.size();i++)
		{
			String stylevalue=action.getValue(By.xpath(linklist.get(i)), "style");
			if(!stylevalue.equals("display: none;"))
			{
				msg = msg + " |collapse all sections link not Ok";
				flag=false;
			}
			
		}
		return flag;
	}*/
	
	
	
	private static String getMsg() {
		return msg;
	}
}