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
import com.oscarbase.*;

public class OscarMsg_025 {

	private String homePageWin = "";
	private String search="";
	private String encounterWin = "";
	private String createMsgWin="";
	private static String subject = "";
	private static String context = "";
	private static String expectedPageTitle1 = "";
	private static String expectedPageTitle2 = "";
	private static String sendNumber="";
	private String msg = "";
	public static WebDriver driver =null;
	public static WebDriverActions action = null;
    private List<WebElement> list=new ArrayList<WebElement>();
    private List<WebElement> l=new ArrayList<WebElement>();
    private static Date dt = new Date();
    private static  String createAppointmentDemogName=CaseConf.getInstance().getCreateAppointmentDemogName();
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy025");
		String testcaseName = p.getProperty("TestcaseName");
		subject = p.getProperty("Subject");
		context = p.getProperty("Context");
		expectedPageTitle1 = p.getProperty("ExpectedPageTitle1");
		sendNumber = p.getProperty("sendNumber");
		String result = "";
		String note = "";
		

		try {
			OscarMsg_025 case093 = new OscarMsg_025();
			LoginOscar.login(driver);
			case093.openEncounterWin();

			if (case093.canOpenCreateMsgWin()) {
                     BaseUtil.log("enter 1");
				    if( case093.createMsg())
				      {
                      BaseUtil.log("enter 2");
				if (!case093.verifyCanOpenViewMsgWin()) {
					 result = TestLinkAPIResults.TEST_FAILED;
					 BaseUtil.log("enter error1");
				}

				else{
					  BaseUtil.log("enter 3");
					  case093.unlinkMsg();
					  BaseUtil.log("enter 4");
				}
					if (!case093.verifyMsgIsDeleted()) {
						result = TestLinkAPIResults.TEST_FAILED;
						BaseUtil.log("enter error2");
					}
				 
				if (result.isEmpty()) {
					result = TestLinkAPIResults.TEST_PASSED;
					BaseUtil.log("pass");
				} else {
					note = case093.getMsg();
				}
				    }else {
				    	result = TestLinkAPIResults.TEST_FAILED;
						note = case093.getMsg();
					}
			} else {
				result = TestLinkAPIResults.TEST_FAILED;
				note = case093.getMsg();
			}

		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();
		} finally {
			TestExecutionResult.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, note, result);
			System.out.println("OscarMsg_025: Execution Terminated at " + dt);
		}
		driver.quit();
		Thread.sleep(3000);
	}

	private void openEncounterWin() {
		homePageWin = action.getCurrentHandle();
		action.click(By.linkText("Search"));
		search=action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(search);
		action.type((By.xpath("//input[@name='keyword'and @type='text']")),createAppointmentDemogName);
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
		action.click(By.linkText("Demogp' No"));
		action.click(By.linkText("E"));
		action.driverWait(10);
		encounterWin = action.getNewPopupWinHdl(homePageWin,search);
		action.selectWindow(encounterWin);
	}

	private boolean oscarMsgIsPresent() {
		if (action.isElementPresent(By.id("msgs"))) {
			return true;
		} else {
			msg =  msg+" | 'Oscar Msg' doesn't exist from the left navigator.";
			return false;
		}
	}

	private boolean canOpenCreateMsgWin() {

		if (oscarMsgIsPresent()) {
			if (action.isElementPresent(
					action.getWebElement(By.id("menuTitlemsgs")),
					By.linkText("+"))) {
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
				}
				action.click(action.getWebElement(By.id("menuTitlemsgs")),
						By.linkText("+"));
		       createMsgWin = action.getNewPopupWinHdl(homePageWin,search,
						encounterWin);
				if (createMsgWin != null) {
					action.selectWindow(createMsgWin);
					if (action
							.isElementPresent(By
									.xpath("//input[@class='ControlPushButton'][@value='Send Message']"))) {
						return true;
					} else {
						msg = msg+ " | Please make sure the open page is 'createMessage' page.";
						return false;
					}

				} else {
					msg =  msg+" | No window will pop up by clicking '+' link.";
					return false;
				}
			} else {
				msg =  msg+" | Link '+' is not present";
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean  createMsg() {
        boolean flag=true;
		action.type(By.name("subject"), subject+dt);
		action.type(By.name("message"), context);
		WebElement selectProvider = action.getWebElement(By.xpath("//table[@id='tblDFR2']//input[@name='provider'][@value='"+sendNumber+"']"));
		action.click(By.xpath("//input[@type='checkbox'][@name='tblDFR2']/preceding-sibling::span"));
	    selectProvider.click();
    
	     action.click(By.xpath("//input[@name='searchDemo']"));
	     String searchagain=action.getNewPopupWinHdl(homePageWin, encounterWin, createMsgWin,search);
	     if(searchagain!=null)
	     {
	        action.selectWindow(searchagain);
	        action.type((By.xpath("//input[@name='keyword'and @type='text']")),createAppointmentDemogName);
			action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
			action.click(By.linkText("Demogp' No"));
			action.click(By.linkText("1"));
	     }else {
	    	 flag=false;
	    	 msg = msg+ " | No search win.";
		}
	     action.selectWindow(createMsgWin);
			String demogvalue=action.getWebElement(By.xpath("//input[@name='selectedDemo']")).getAttribute("value").trim();
	        BaseUtil.log(demogvalue);
			if(!demogvalue.equals(createAppointmentDemogName))
			{
				flag=false;
		    	 msg = msg+ " | after search .but the demographic not come into Msg";
			}
	    
		action.click(By.xpath("//input[@class='ControlPushButton'][@value='Send Message']"));
		action.closeCurrentWindow();
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
		
		return flag;
	}

	private boolean verifyListAndLinksArePresent() {
        boolean flag=false;
        
		if (action.isElementPresent(By.id("msgslist"))) {
			WebElement list = action.getWebElement(By.id("msgslist"));
			if (action.isElementPresent(list, By.className("links"))) {
			    flag=true;
				
			} else {
				msg = msg+ " | No oscar msg link exists on Encounter page.";
				
			}
		} else {
			msg = msg+ " | No msg list exists on Encounter page.";
			
		}
		
		return flag;
	}

	private boolean verifyCanOpenViewMsgWin() {

		boolean flag = false;
		if (verifyListAndLinksArePresent()) {
			
			 try {
				Thread.sleep(2000);
				} catch (InterruptedException e) {	}
				
			action.click(action.getWebElement(By.id("msgslist")),
					By.className("links"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e2) {
			}
			String viewMsgWin = action.getNewPopupWinHdl(homePageWin,search,
					encounterWin);
			if (viewMsgWin != null) {
				action.selectWindow(viewMsgWin);
				String actualPageTitle = action.getPageTitle();
                BaseUtil.log(actualPageTitle);
                BaseUtil.log(expectedPageTitle1);
				if (actualPageTitle.trim().compareTo(expectedPageTitle1)==0) {
					action.closeCurrentWindow();
					action.selectWindow(encounterWin);
					
					action.click(By.linkText("Oscar Msg"));
					action.selectWindow(action.getNewPopupWinHdl(homePageWin, encounterWin, search));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				    if(action.isElementPresent(By.xpath("//form/table[1]//td//a[contains(text(),'"+subject+dt+"')]")))
				    {
				    	action.closeCurrentWindow();
				    	try {
							Thread.sleep(1500);
						} catch (InterruptedException e1) {
						}
						action.selectWindow(homePageWin);
				    	action.selectByValue(By.xpath("//select[@id='mygroup_no']"), sendNumber);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
						}
						action.click(By.xpath("//a[@title='Messenger']"));
						String ticklerwin=action.getNewPopupWinHdl(homePageWin, encounterWin ,search);
						action.selectWindow(ticklerwin);
						action.click(By.xpath("//a[contains(text(),'All Messages')]"));
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
						}
						
						action.type(By.xpath("//input[@name='searchString']"), subject+dt);
						action.click(By.xpath("//input[@name='btnSearch']"));
						
						if(action.isElementPresent(By.xpath("//a[contains(text(),'"+subject+dt+"')]")))
						{
							flag=true;
							action.closeCurrentWindow();
						}else {
							msg = msg+" | the msglist not exists this.";
						}
				    	  
				    }
					
					

				} else {
					msg =  msg+" | Please be sure users can open view message window by clicking msg link.";
					BaseUtil.log(msg);
				}

			} else {
				msg =  msg+" | No window will pop up by clicking msg link.";
				BaseUtil.log(msg);
			}
		}
		return flag;
	}

	private void unlinkMsg() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
		}
		action.selectWindow(encounterWin);
        action.click(By.linkText("Oscar Msg"));
		String viewMsgWin = action.getNewPopupWinHdl(homePageWin,search,
				encounterWin);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
		}
		action.selectWindow(viewMsgWin);
		driver.findElement(By.xpath("//table//table//table//tr/td/a[contains(text(),'"+subject+dt+"')]/../preceding-sibling::td/input[@type='checkbox']")).click();
		action.click(By
				.xpath("//input[@type='button'][@value='Unlink Messages']"));
		action.closeCurrentWindow();
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();

	}

	private boolean verifyMsgIsDeleted() {
		
		List<WebElement> elements=action.getWebElements(action.getWebElement(By.id("msgslist")),By.className("links"));
		
		for(WebElement element:elements)
		{
		if ((subject+dt).equals(element.getText())) {
			
			msg =  msg+" | Msg wasn't deleted from tickler list on Encounter page.";
			return false;
		} 
		}
		
		return true;
		
	}

	private String getMsg() {
		return msg;
	}
}
