package encounter.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.spi.Resolver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.oscarbase.BaseUtil;
import com.oscarbase.CaseConf;
import com.oscarbase.GetTestData;
import com.oscarbase.LoginOscar;
import com.oscarbase.TestExecutionResult;
import com.oscarbase.WebDriverActions;
import com.oscarbase.createPatientSearch;

import testlink.api.java.client.TestLinkAPIResults;

public class DiseaseRegistry_030 {

	private static String homePageWin = "";
	private static String patientSearchWin="";
	private static String encounterWin = "";
	private  String addDocWin ="";
	private static String [] wins=new String[2];
	private static String msg = "";
	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static String expectedPageTitle1="";
	private static String addWin="" ;
	private static int addbefore=0;
	private static int addafter=0;
	private static String search1="";
	private static String Diagnosis="";
	private static String CodingSystem="";
	private static String newquick="";
	private static String addcode="";
	private static String codename="";
	private static int linksAddBefore=0;
	private static int linksAddafter=0;
	private static List <WebElement> list=new ArrayList<WebElement>();
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy030");
		String testcaseName = p.getProperty("TestcaseName").trim();
		expectedPageTitle1=p.getProperty("expectedPageTitle1").trim();
		CodingSystem=p.getProperty("CodingSystem").trim();
		search1=p.getProperty("search1").trim();
		Diagnosis=p.getProperty("Diagnosis").trim();
		newquick=p.getProperty("newquick").trim();
		addcode=p.getProperty("addcode").trim();
		codename=p.getProperty("codename").trim();
		String result = "";
		String note = "";
		Date dt = new Date();
		
		try {
			DiseaseRegistry_030 case030=new DiseaseRegistry_030();
			LoginOscar.login(driver);
			wins=createPatientSearch.openPatientSearchWin(action);
			patientSearchWin=wins[1];
			homePageWin=wins[0];
			encounterWin=createPatientSearch.openEncounterWin(action);
			
			list=action.getWebElements(By.xpath("//ul[@id='Dxlist']//li"));
			 linksAddBefore=list.size();
			if (case030.canOpenAddDiseaseRegistryWin()) {
                BaseUtil.log("enter 1");
                case030.add();
                BaseUtil.log("enter 2");
                if(addVerifySuccess())
                {
                	BaseUtil.log("enter 3");
                	if(ResolverAndVerify()){
                	  BaseUtil.log("enter 4");
                	   if(deleteAndVerify()){
                		   BaseUtil.log("enter 5");
                		   if( addQuickList())
                		   {
                		   BaseUtil.log("pass");
                         	result=TestLinkAPIResults.TEST_PASSED;
                		   }
                		   else {
                			   result = TestLinkAPIResults.TEST_FAILED;
                  				note=case030.getMsg();
						}
                	   }else {
                		   result = TestLinkAPIResults.TEST_FAILED;
           				note=case030.getMsg();
					}
                
                	}else {
                		result = TestLinkAPIResults.TEST_FAILED;
        				note=case030.getMsg();
					}
                	
                	
                }else {
                	result = TestLinkAPIResults.TEST_FAILED;
    				note=case030.getMsg();
				}
                
			}else {
				result = TestLinkAPIResults.TEST_FAILED;
				note=case030.getMsg();
			}
			BaseUtil.log(note);
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			TestExecutionResult.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, note, result);
			System.out.println("diseaseRegistry_30: Execution Terminated at " + dt);
			
		}
	}
	
	private boolean diseaseRegistryIsPresent() {
		if (action.isElementPresent(By.xpath("//div[@id='Dx']"))) {
			return true;
		} else {
			msg =  msg+" | 'Disease Registry' doesn't exist from the left navigator.";
			return false;
		}
	}
	
private boolean canOpenAddDiseaseRegistryWin() {
        
		if (diseaseRegistryIsPresent()) {
			if (action.isElementPresent(
					action.getWebElement(By.id("menuTitleDx")),
					By.linkText("+"))) {
			
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				action.click(By.xpath("//div[@id='menuTitleDx']//a"));
				
				
				addWin= action.getNewPopupWinHdl(homePageWin,patientSearchWin,
						encounterWin);
				if (addWin != null) {
					action.selectWindow(addWin);
					String actualPageTitle = action.getPageTitle();
					BaseUtil.log(actualPageTitle);
					if (actualPageTitle.contains(expectedPageTitle1.trim())) {
						return true;
					} else {
						msg =  msg+" | Please make sure the open page is 'DiseaseRegistry' page.";
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
private void add()
{
	
	list=action.getWebElements(By.xpath("//td//table[@bgcolor='#FFFFFF'and @cellpadding='0']//tr"));
	addbefore=list.size();
	
	action.selectByValue(By.xpath("//select[@name='selectedCodingSystem']"), CodingSystem);
	
	action.clear(By.xpath("//input[@name='xml_research1']"));
	action.type(By.xpath("//input[@name='xml_research1']"), search1);
	
	
	action.click(By.xpath("//input[@name='codeSearch']"));
	try {
		Thread.sleep(4000);
	} catch (InterruptedException e) {
	}
	String add=action.getNewPopupWinHdl(addWin, homePageWin, encounterWin,patientSearchWin);
	action.selectWindow(add);
	
	//action.click(By.xpath("//table//tr[3]//input"));

	action.click(By.xpath("//input[@name='confirm']"));
	
	action.selectWindow(addWin);
	
	action.click(By.xpath("//input[@name='codeAdd']"));
	
	list=action.getWebElements(By.xpath("//td//table[@bgcolor='#FFFFFF'and @cellpadding='0']//tr"));
	addafter=list.size();
	
	
 }

private static  boolean addVerifySuccess()
{
  boolean flag=true;
  
  BaseUtil.log(addbefore+"*"+addafter);
    //if(addafter==addbefore+1)
   // {
    	
    	if(!action.isElementPresent(By.xpath("//td//table[@bgcolor='#FFFFFF'and @cellpadding='0']//tr//td[contains(text(),'"+Diagnosis+"')]")))
    	{
    		flag=false;
    		msg=msg+"| it's not the "+search1+",but another";
    	}
    	
    	action.selectWindow(encounterWin);
    	action.refreshCurrentWindow();
    	try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
		}
		if(action.isElementPresent(By.xpath("//img[@id='imgDx5']")))
		{
          action.click(By.xpath("//img[@id='imgDx5']"));
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		list=action.getWebElements(By.xpath("//ul[@id='Dxlist']//li"));
		linksAddafter=list.size();
		BaseUtil.log(linksAddBefore+"*"+linksAddafter);
	//	if(linksAddafter<=linksAddBefore+1)
	//	{
			
			if(!action.isElementPresent(By.xpath("//ul[@id='Dxlist']//a[contains(text(),'"+Diagnosis+"')]")))
			{
				msg=msg+"| the encounter  Win add it's not the "+search1+",but another";
				flag=false;
				
			}
			
			
	//	}else {
		//	msg=msg+"| the encounter  Win add not success";
	//		flag=false;
	//	}
		
    	
   // }else {
		//msg=msg+"| the DiseaseRegistry win add not success";
	//	flag=false;
	//}
  
  return flag;

}


   public static boolean ResolverAndVerify()
   {
	   boolean flag=true;
	 action.selectWindow(addWin);
	 action.click(By.xpath("//td//table[@bgcolor='#FFFFFF'and @cellpadding='0']//tr//td[contains(text(),'"+Diagnosis+"')]/following-sibling::td/a[contains(text(),'Resolve')]"));
	 action.selectWindow(encounterWin);
	 action.refreshCurrentWindow();
	 try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
	}
	
	if(action.isElementPresent(By.xpath("//ul[@id='Dxlist']//a[contains(text(),'"+Diagnosis+"')]")))
	{
		msg=msg+"| the encounter  Win add it's not the "+search1+",but another";
		flag=false;
		
	}
	
	 return flag;
   }
	
   
   public static boolean deleteAndVerify()
   {
	   boolean flag=true;
	 action.selectWindow(addWin);
	 action.click(By.xpath("//td//table[@bgcolor='#FFFFFF'and @cellpadding='0']//tr//td[contains(text(),'"+Diagnosis+"')]/following-sibling::td/a[contains(text(),'Delete')]"));
	 //action.click(By.linkText("Delete"));
	 action.accept();
	 try {
		Thread.sleep(1000);
	 } catch (InterruptedException e) {
	}
	 if(action.isElementPresent(By.xpath("//td//table[@bgcolor='#FFFFFF'and @cellpadding='0']//tr//td[contains(text(),'"+Diagnosis+"')]")))
 	{
 		flag=false;
 		msg=msg+"| it's not the "+search1+",but another";
 	}
	 
	 action.closeCurrentWindow();
	 action.selectWindow(patientSearchWin);
	 action.closeCurrentWindow();
	
	 return flag;
   }
   
   public static boolean  addQuickList()
   {
	   boolean flag=true;
	   action.selectWindow(homePageWin);
	   action.click(By.linkText("Admin"));
	   String adminWin=action.getNewPopupWinHdl(homePageWin, encounterWin);
	   if(adminWin!=null)
	   {
	    action.selectWindow(adminWin);
	    action.click(By.linkText("Customize Disease Registry Quick List"));
	    
	    String quicklistWin=action.getNewPopupWinHdl(homePageWin, encounterWin,adminWin);
	    if(quicklistWin!=null)
	    {
	      action.selectWindow(quicklistWin);
	      action.click(By.xpath("//input[@value='Add New Quick List']"));
	      
	      String addNewQuick=action.getNewPopupWinHdl(homePageWin, encounterWin, adminWin, quicklistWin);
	      action.selectWindow(addNewQuick);
	      action.type(By.xpath("//input[@type='text']"), newquick);
	      action.click(By.xpath("//input[@type='submit']"));
	      
	      action.clear(By.xpath("//input[@name='xml_research1']"));
	  	action.type(By.xpath("//input[@name='xml_research1']"), addcode);
	  	
	  	action.click(By.xpath("//input[@value='Add >>']"));
	  	action.click(By.xpath("//input[@value='Close']"));
	  	
	  	 action.selectWindow(encounterWin);
	  	 action.click(By.linkText("Disease Registry"));
	  	 
	  	 String DiseaseRegistryWin=action.getNewPopupWinHdl(homePageWin, encounterWin, quicklistWin, adminWin);
	  	 
	  	 action.selectWindow(DiseaseRegistryWin);
	  	 
	  	 action.selectByValue(By.xpath("//select[@name='quickList']"), newquick);
	  	 
	  	 action.click(By.xpath("//a[contains(text(),'"+codename+"')]"));
	  	 
	  	 
	  	if(!action.isElementPresent(By.xpath("//td//table[@bgcolor='#FFFFFF'and @cellpadding='0']//tr//td[contains(text(),'"+codename+"')]")))
    	{
    		flag=false;
    		msg=msg+"| it's not the "+codename+"";
    	}
	  	
	  	action.selectWindow(encounterWin);
	  	action.refreshCurrentWindow();
	  	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
	  	if(!action.isElementPresent(By.xpath("//ul[@id='Dxlist']//a[contains(text(),'"+codename+"')]")))
		{
			msg=msg+"| the encounter  Win add it's not the "+codename+"";
			flag=false;
			
		}
	  	
	  	action.selectWindow(DiseaseRegistryWin);
	  	 action.click(By.xpath("//td//table[@bgcolor='#FFFFFF'and @cellpadding='0']//tr//td[contains(text(),'"+codename+"')]/following-sibling::td/a[contains(text(),'Delete')]"));
		 action.accept();
	  	
	      
	      
	    }else {
	    	msg=msg+"| not exist quicklistWin";
			flag=false;
		}
	    
	   }else {
		msg=msg+"| not exist adminWin";
		flag=false;
	}
	   return flag;
	   
   }
   
	private String getMsg() {
		return msg;
	}
}
