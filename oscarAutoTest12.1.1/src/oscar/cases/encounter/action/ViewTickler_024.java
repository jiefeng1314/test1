package encounter.action;

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

public class ViewTickler_024 {

	private String homePageWin = "";
	private String patientSearchWin="";
	private String encounterWin = "";
	private String addTicklerWin="";
	private static String serviceDate = "";
	private static String afterDate = "";
	private static String reminderMsg = "";
	private static String title = "";
	private String msg = "";
	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static String sendNumber="";
	private static String mrp="";
	private static String provider="";
    private static Date dt = new Date();
    private static  String createAppointmentDemogName=CaseConf.getInstance().getCreateAppointmentDemogName();

	public static void run() throws Exception {
		
		FirefoxProfile profile = new FirefoxProfile();  
		profile.setPreference("intl.accept_languages", "en-US");   
		driver = new FirefoxDriver(profile); 
		action=new WebDriverActions(driver);
		
		String projectName = CaseConf.getInstance().getProjectName();
		String testplanName = CaseConf.getInstance().getTestplanName();
		String build = CaseConf.getInstance().getBuild();
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy024");
		String testcaseName = p.getProperty("TestcaseName").trim();
		serviceDate = p.getProperty("ServiceDate").trim();
		afterDate = p.getProperty("afterDate").trim();
		reminderMsg = p.getProperty("ReminderMsg").trim();
		sendNumber=p.getProperty("sendNumber").trim();
		provider=p.getProperty("provider").trim();
		mrp=p.getProperty("mrp").trim();
		title=p.getProperty("title").trim();
		String result = "";
		String note = "";
		

		try {
			ViewTickler_024 case024 = new ViewTickler_024();
			By092_VerfiyAllEsArePresent onAddTicklerPage = new By092_VerfiyAllEsArePresent(driver);
			LoginOscar.login(driver);
			case024.openEncounterWin();
            Thread.sleep(7000);
			if (case024.canOpenAddTicklerWin()) {
				    BaseUtil.log("enter 1");
				if (onAddTicklerPage.verify()) {
					 BaseUtil.log("enter 2");
					case024.addTickler();
					  BaseUtil.log("enter 3");
					if (case024.verifyTTShowsRight()) {
						 BaseUtil.log("enter 4");
						 case024.completeTickler();
						 BaseUtil.log("enter 5");
						  if(case024.verifyTicklerIsComplete())
						  {   
							  BaseUtil.log("enter 6");
							   case024.deleteTickler();
							   BaseUtil.log("enter 7");
							if (case024.verifyTicklerIsDeleted()) {
								BaseUtil.log("pass");
								result = TestLinkAPIResults.TEST_PASSED;
							} else {
								result = TestLinkAPIResults.TEST_FAILED;
								note = case024.getMsg();
							}
						  }else {
							  result = TestLinkAPIResults.TEST_FAILED;
								note = case024.getMsg();
						}
						
					} else {
						result = TestLinkAPIResults.TEST_FAILED;
						note = case024.getMsg();
					}
				} else {
					result = TestLinkAPIResults.TEST_FAILED;
					note = onAddTicklerPage.getMsg();
				}
			} else {
				result = TestLinkAPIResults.TEST_FAILED;
				note = case024.getMsg();
			}

			BaseUtil.log(note);
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();
		} finally {
			TestExecutionResult.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, note, result);
			System.out
					.println("ViewTickler_024: Execution Terminated at " + dt);
		}
		driver.quit();
		Thread.sleep(3000);
	}

	private void openEncounterWin() {
		homePageWin = action.getCurrentHandle();
		action.click(By.linkText("Search"));
		patientSearchWin = action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(patientSearchWin );
		
		action.type((By.xpath("//input[@name='keyword'and @type='text']")),createAppointmentDemogName);
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
		action.click(By.linkText("Demogp' No"));
		action.click(By.linkText("E"));
		action.driverWait(10);
		encounterWin = action.getNewPopupWinHdl(homePageWin,patientSearchWin);
		action.selectWindow(encounterWin);
	}

	private boolean viewTicklerIsPresent() {
		if (action.isElementPresent(By.id("tickler"))) {
			return true;
		} else {
			msg = " | 'View Tickler' doesn't exist from the left navigator.";
			return false;
		}
	}

	private boolean canOpenAddTicklerWin() {
          
		if (viewTicklerIsPresent()) {
			if (action.isElementPresent(
					action.getWebElement(By.xpath("//div[@id='menuTitletickler']")),
					By.linkText("+"))) {
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				
				action.click(action.getWebElement(By.xpath("//div[@id='menuTitletickler']")),
						By.linkText("+"));
			  addTicklerWin = action.getNewPopupWinHdl(homePageWin,
						patientSearchWin,encounterWin);
				if (addTicklerWin != null) {
					action.selectWindow(addTicklerWin);
					
					return true;
				} else {
					msg = " | No window will pop up by clicking '+' link.";
					return false;
				}
			} else {
				msg = " | Link '+' is not present";
				return false;
			}
		} else {
			return false;
		}
	}

	private void addTickler() {
        action.clear(By.name("xml_appointment_date"));
		action.type(By.name("xml_appointment_date"), serviceDate);
		action.selectByValue(By.name("priority"), "Normal");
		action.selectByValue(By.name("task_assigned_to"), sendNumber.trim());
		action.type(By.name("textarea"), reminderMsg+dt);
		action.click(By
				.xpath("//input[@type='button'][@value='Submit and EXIT']"));
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			
		}
		
	}
	

	private boolean verifyListAndLinksArePresent() {

		if (action.isElementPresent(By.id("ticklerlist"))) {
			WebElement list = action.getWebElement(By.id("ticklerlist"));
			if (action.isElementPresent(list, By.className("links"))) {
				return true;
			} else {
				msg = " | No tickler link exists on Encounter page.";
				return false;
			}
		} else {
			msg = " | No tickler list exists on Encounter page.";
			return false;
		}
	}

	//check after add
	private boolean verifyTTShowsRight() {
		boolean isOk =true;
		if (verifyListAndLinksArePresent()) {
			action.click(action.getWebElement(By.id("ticklerlist")),By.className("links"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			String oscarTicklerWin = action.getNewPopupWinHdl(homePageWin,patientSearchWin,encounterWin);
			if (oscarTicklerWin != null) {
				action.selectWindow(oscarTicklerWin);
				if (driver.getTitle().trim().equals("oscarTickler")) {
					
					
					action.click(By.xpath("//input[@type='button'][@value='Add Tickler']"));
					String inboxAddWin=action.getNewPopupWinHdl(homePageWin, patientSearchWin, encounterWin, oscarTicklerWin);
					if(inboxAddWin!=null)
					{
					action.selectWindow(inboxAddWin);
					if(!driver.getTitle().trim().equals(title))
					{
						isOk=false;
						msg =msg+ " | error, the inbox add tickler  not OK";
					}
					action.closeCurrentWindow();
					action.selectWindow(oscarTicklerWin);
					}else {
						isOk=false;
						msg =msg+ " | error, the inbox add tickler link not OK";
					}
					
					
					action.selectByVisibleText(By.xpath("//select[@name='ticklerview']"), "Active");
					
					action.type(By.xpath("//input[@name='xml_vdate']"), serviceDate);
					
					action.click(By.xpath("//input[@type='submit']"));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					if(!action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
					{
						isOk=false;
						msg =msg+ " | error, the ticklerlist not find this tickler.";
					}
					
					//Check the time limit is valid
					action.clear(By.xpath("//input[@name='xml_vdate']"));
					action.type(By.xpath("//input[@name='xml_vdate']"), afterDate);
					action.click(By.xpath("//input[@type='submit']"));
					if(action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
					{
						isOk=false;
						msg =msg+ " |  time limit is error.";
					}
					
				}else {
					msg = msg+" |  the open window is error.";
				}
				
			} else {
				msg = msg+" |  There is not this window";
			}
		
		}
		else {
			msg =msg+ " | No window will pop up by clicking tickler link.";
		}
		
		action.closeCurrentWindow();
		//action.click(By.linkText("Close the Window"));
		
		action.selectWindow(homePageWin);
		action.selectByValue(By.xpath("//select[@id='mygroup_no']"), sendNumber);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		action.click(By.xpath("//a[@title='Tickler']"));
		String ticklerwin=action.getNewPopupWinHdl(homePageWin, encounterWin,patientSearchWin);
		action.selectWindow(ticklerwin);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		
		//验证接受方的盒子里的add tickler链接是否可以
		action.click(By.xpath("//input[@type='button'][@value='Add Tickler']"));
		String inboxAddWin=action.getNewPopupWinHdl(homePageWin, patientSearchWin, encounterWin, ticklerwin);
		if(inboxAddWin!=null)
		{
		action.selectWindow(inboxAddWin);
		if(!driver.getTitle().trim().equals(title))
		{
			isOk=false;
			msg =msg+ " | error, the inbox add tickler  not OK";
		}
		action.closeCurrentWindow();
		action.selectWindow(ticklerwin);
		}else {
			isOk=false;
			msg =msg+ " | error, the inbox add tickler link not OK";
		}
		
		
		
		action.type(By.xpath("//input[@name='xml_vdate']"), serviceDate);
		action.selectByVisibleText(By.xpath("//select[@name='ticklerview']"), "Active");
		BaseUtil.log(mrp);
		action.selectByValue(By.xpath("//select[@name='mrpview']"), mrp);
		action.selectByVisibleText(By.xpath("//select[@name='providerview']"),provider);
		action.selectByValue(By.xpath("//select[@name='assignedTo']"), sendNumber);
		action.click(By.xpath("//input[@value='Create Report']"));
		
		if(!action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
		{
			isOk=false;
			msg =msg+ " | error, the Recipient ticklerlist not find this tickler.";
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		//Check the time limit is valid
		action.clear(By.xpath("//input[@name='xml_vdate']"));
		action.type(By.xpath("//input[@name='xml_vdate']"), afterDate);
		action.click(By.xpath("//input[@value='Create Report']"));
		if(action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
		{
			isOk=false;
			msg =msg+ " | on the Recipient time limit is error.";
		}
	
		//Check the MRP limit is valid
		action.clear(By.xpath("//input[@name='xml_vdate']"));
		action.type(By.xpath("//input[@name='xml_vdate']"), serviceDate);
		action.selectByValue(By.xpath("//select[@name='mrpview']"), sendNumber);
		action.click(By.xpath("//input[@value='Create Report']"));
		if(action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
		{
			isOk=false;
			msg =msg+ " | on the Recipient MRP limit is error.";
		}
	
		//Check the provider limit is valid
		action.selectByValue(By.xpath("//select[@name='mrpview']"), mrp);
		action.selectByValue(By.xpath("//select[@name='providerview']"),mrp);
		action.click(By.xpath("//input[@value='Create Report']"));
		if(action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
		{
			isOk=false;
			msg =msg+ " | on the Recipient provider limit is error.";
		}
	
		//Check the assigned limit is valid
		action.selectByVisibleText(By.xpath("//select[@name='providerview']"),provider);
		action.selectByValue(By.xpath("//select[@name='assignedTo']"), "999998");
		action.click(By.xpath("//input[@value='Create Report']"));
		if(action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
		{
			isOk=false;
			msg =msg+ " | on the Recipient assigned limit is error.";
		}
		
		action.closeCurrentWindow();
		action.selectWindow(encounterWin);
		return isOk;
	}
	

	private void deleteTickler() {


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		action.click(action.getWebElement(By.id("ticklerlist")),
				By.className("links"));
		action.selectWindow(action.getNewPopupWinHdl(homePageWin,patientSearchWin, encounterWin));
		BaseUtil.log(reminderMsg+dt);
		action.selectByVisibleText(By.xpath("//select[@name='ticklerview']"), "Completed");
		action.click(By.xpath("//input[@type='submit']"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
		}
		action.click(By.xpath("//td[text()='"+reminderMsg+dt+"']/preceding-sibling::td/input[@type='checkbox']"));
		action.click(By.xpath("//input[@type='button'][@value='Delete']"));
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		}
		action.closeCurrentWindow();
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();

	}
	
	
	
	private void completeTickler() {


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		action.click(action.getWebElement(By.id("ticklerlist")),
				By.className("links"));
		action.selectWindow(action.getNewPopupWinHdl(homePageWin,patientSearchWin, encounterWin));
		BaseUtil.log(reminderMsg+dt);
		action.click(By.xpath("//td[text()='"+reminderMsg+dt+"']/preceding-sibling::td/input[@type='checkbox']"));
		action.click(By.xpath("//input[@type='button'][@value='Complete']"));
		action.closeCurrentWindow();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
		}

	}
	
	
	

	private boolean verifyTicklerIsDeleted() {
		boolean flag=true;
		List<WebElement> elements=action.getWebElements(action.getWebElement(By.id("ticklerlist")),
				By.className("links"));
		
		for(WebElement element:elements)
		{
		if (reminderMsg.contains(element.getText())) {
			
			msg = " | Tickler wasn't deleted from tickler list on Encounter page.";
			flag=false;
		} 
		}
		
		action.click(action.getWebElement(By.id("ticklerlist")),By.className("links"));
		String oscarTicklerWin = action.getNewPopupWinHdl(homePageWin,patientSearchWin,encounterWin);
		if (oscarTicklerWin != null) {
			action.selectWindow(oscarTicklerWin);
			action.selectByVisibleText(By.xpath("//select[@name='ticklerview']"), "Deleted");
			
			action.click(By.xpath("//input[@type='submit']"));
			if(!action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
			{
				flag=false;
				msg =msg+ " | error,after delete, the delete not find this tickler.";
			}
			
			
			
		}else {
			msg = msg+" |  There is not this window";
		}
		
		
		action.closeCurrentWindow();
		action.selectWindow(homePageWin);
		action.selectByValue(By.xpath("//select[@id='mygroup_no']"), sendNumber);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		action.click(By.xpath("//a[@title='Tickler']"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		String ticklerwin=action.getNewPopupWinHdl(homePageWin, encounterWin,patientSearchWin);
		action.selectWindow(ticklerwin);
		
		action.selectByVisibleText(By.xpath("//select[@name='ticklerview']"), "Deleted");
		action.selectByValue(By.xpath("//select[@name='assignedTo']"), sendNumber);
		action.click(By.xpath("//input[@value='Create Report']"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		if(!action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
		{
			flag=false;
			msg =msg+ " | error, after delete,the Recipient ticklerlist in the Deleted not find this tickler.";
		}
		
		
		return flag;
	}
	
	
	
	private boolean verifyTicklerIsComplete() {
		boolean flag=true;
		List<WebElement> elements=action.getWebElements(action.getWebElement(By.id("ticklerlist")),
				By.className("links"));
		
		for(WebElement element:elements)
		{
		if (reminderMsg.contains(element.getText())) {
			msg = " | after complete ,Tickler wasn't deleted from tickler list on Encounter page.";
			flag=false;
			break;
		} 
		}
		
		action.click(action.getWebElement(By.id("ticklerlist")),By.className("links"));
		String oscarTicklerWin = action.getNewPopupWinHdl(homePageWin,patientSearchWin,encounterWin);

		if (oscarTicklerWin != null) {
			action.selectWindow(oscarTicklerWin);
			if(action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
			{
				flag=false;
				msg =msg+ " |after complete,the tickler still  in the active.";
			}
			
			action.selectByVisibleText(By.xpath("//select[@name='ticklerview']"), "Completed");
			action.click(By.xpath("//input[@type='submit']"));
			
			if(!action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
			{
				flag=false;
				msg =msg+ " | error, after complete,the complete not find this tickler.";
			}
			
			
			
			action.closeCurrentWindow();
			action.selectWindow(homePageWin);
			action.selectByValue(By.xpath("//select[@id='mygroup_no']"), sendNumber);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			action.click(By.xpath("//a[@title='Tickler']"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			String ticklerwin=action.getNewPopupWinHdl(homePageWin, encounterWin,patientSearchWin);
			action.selectWindow(ticklerwin);
			
			//action.type(By.xpath("//input[@name='xml_vdate']"), serviceDate);
		//	action.selectByVisibleText(By.xpath("//select[@name='ticklerview']"), "Active");
			//action.selectByVisibleText(By.xpath("//select[@name='mrpview']"), mrp);
			//action.selectByVisibleText(By.xpath("//select[@name='providerview']"),provider);
			//action.selectByValue(By.xpath("//select[@name='assignedTo']"), sendNumber);
			//action.click(By.xpath("//input[@value='Create Report']"));
			
			if(action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
			{
				flag=false;
				msg =msg+ " | error, the Recipient ticklerlist after complete ,in the active can find this tickler.";
			}
			
		
			action.selectByVisibleText(By.xpath("//select[@name='ticklerview']"), "Completed");
			action.selectByValue(By.xpath("//select[@name='assignedTo']"), sendNumber);
			action.click(By.xpath("//input[@value='Create Report']"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if(!action.isElementPresent(By.xpath("//body//table//table//tr//td[contains(text(),'"+reminderMsg+dt+"')]")))
			{
				flag=false;
				msg =msg+ " | error, the Recipient ticklerlist in the complete not find this tickler.";
			}
			 
		}else {
			msg = msg+" |  There is not this window";
		}
		
		action.closeCurrentWindow();
		action.selectWindow(encounterWin);
		
		return flag;
	}

	private String getMsg() {
		return msg;
	}
}
