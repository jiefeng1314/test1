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

public class Documents_028 {

	private String homePageWin = "";
	private String search="";
	private String encounterWin = "";
	private String addDocWin ="";
	private static String expectedPageTitle1 = "";
	private static String expectedPageTitle2 = "";
	private static String docDesc = "";
	private static String linkURL = "";
	private static String htmlvalue="";
	private static String observationDate="";
	private static String sourceFacility="";
	private static String sourceAuthor="";
	private static String provider="";
	private static String description="";
	private static String reportSubClass="";
	private static  String createAppointmentDemogName=CaseConf.getInstance().getCreateAppointmentDemogName();
	private String msg = "";
	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	private static Date dt = new Date();

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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy028-029");
		String testcaseName = p.getProperty("TestcaseName1");
		expectedPageTitle1 = p.getProperty("ExpectedPageTitle1");
		expectedPageTitle2 = p.getProperty("ExpectedPageTitle2");
		docDesc = p.getProperty("DocDesc");
		linkURL = p.getProperty("LinkURL");
		htmlvalue=p.getProperty("htmlvalue");
		observationDate=p.getProperty("observationDate");
		sourceFacility=p.getProperty("sourceFacility");
		sourceAuthor=p.getProperty("sourceAuthor");
		provider=p.getProperty("provider");
		description=p.getProperty("description");
		reportSubClass=p.getProperty("reportSubClass");
		
		String result = "";
		String note = "";
		

		try {
			Documents_028 case098 = new Documents_028();
			LoginOscar.login(driver);
			case098.openEncounterWin();

			if (case098.canOpenAddDocWin()) {
                BaseUtil.log("enter 1");
				case098.addLinkDoc();
				BaseUtil.log("enter 2");
				if (!case098.verifyCanOpenViewDocLinkWin()) {
					BaseUtil.log("enter error1");
					result = TestLinkAPIResults.TEST_FAILED;
				}

				if (!case098.verifyCanOpenDisplayDocLinkWin()) {
					BaseUtil.log("enter error2");
					result = TestLinkAPIResults.TEST_FAILED;
				}
				   BaseUtil.log("enter 3");
					case098.addHtmlDoc();
					BaseUtil.log("enter 4");
					
					if (!case098.verifyCanOpenViewDocHtmlWin()) {
						BaseUtil.log("enter error3");
						result = TestLinkAPIResults.TEST_FAILED;
					}

					if (!case098.verifyCanOpenDisplayDocHtmlWin()) {
						BaseUtil.log("enter error4");
						result = TestLinkAPIResults.TEST_FAILED;
					}
				if (result.isEmpty()) {
					BaseUtil.log("pass");
					result = TestLinkAPIResults.TEST_PASSED;
				} else {
					note = case098.getMsg();
				}

			} else {
				result = TestLinkAPIResults.TEST_FAILED;
				note = case098.getMsg();
			}

			BaseUtil.log(note);
			BaseUtil.log(result);
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();
		} finally {
			TestExecutionResult.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, note, result);
			System.out.println("Documents_098: Execution Terminated at " + dt);
		}
		driver.quit();
		Thread.sleep(3000);
	}

	private void openEncounterWin() {
		homePageWin = action.getCurrentHandle();
		action.click(By.linkText("Search"));
		search=action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(search);
		action.type(By.xpath("//input[@name='keyword']"), createAppointmentDemogName);
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
		action.click(By.linkText("Demogp' No"));
		action.click(By.linkText("E"));
		action.driverWait(10);
		//action.closeCurrentWindow();
		encounterWin = action.getNewPopupWinHdl(homePageWin,search);
		action.selectWindow(encounterWin);
	}

	private boolean docsIsPresent() {
		if (action.isElementPresent(By.id("docs"))) {
			return true;
		} else {
			msg = msg+" | 'Documents' doesn't exist from the left navigator.";
			return false;
		}
	}

	private boolean canOpenAddDocWin() {
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
		}
		if (docsIsPresent()) {
			if (action.isElementPresent(
					action.getWebElement(By.id("menuTitledocs")),
					By.linkText("+"))) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				action.click(action.getWebElement(By.id("menuTitledocs")),
						By.linkText("+"));
				 addDocWin = action.getNewPopupWinHdl(homePageWin,search,
						encounterWin);
				if (addDocWin != null) {
					action.selectWindow(addDocWin);
					String actualPageTitle = action.getPageTitle();
					if (actualPageTitle.contains(expectedPageTitle1)) {
						return true;
					} else {
						msg = msg+ " | Please make sure the open page is eDocs page.";
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

	private void addLinkDoc() {
		
		action.click(By.partialLinkText("+Add Link"));
		action.selectByValue(By.id("docType1"), "consult");
		action.clear(By.cssSelector("#addLinkDiv input[name='docDesc']"));
		action.type(By.cssSelector("#addLinkDiv input[name='docDesc']"),
				docDesc+dt);
		action.clear(By.cssSelector("#addLinkDiv input[name='html']"));
		action.type(By.cssSelector("#addLinkDiv input[name='html']"), linkURL);
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Add']"));
		action.selectWindow(encounterWin);
		
	}

	private boolean verifyListAndLinksArePresent() {

		if (action.isElementPresent(By.id("docslist"))) {
			WebElement list = action.getWebElement(By.id("docslist"));
			if (action.isElementPresent(list, By.className("links"))) {
				return true;
			} else {
				msg =  msg+" | No doc link exists on Encounter page.";
				return false;
			}
		} else {
			msg =  msg+" | No doc list exists on Encounter page.";
			return false;
		}
	}

	private boolean verifyCanOpenViewDocLinkWin() {
		action.refreshCurrentWindow();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		boolean isOk = false;
		if (verifyListAndLinksArePresent()) {
			
			
			List<WebElement> elements=action.getWebElements(action.getWebElement(By.id("docslist")),
					By.className("links"));
			
			for(WebElement element:elements)
			{
				//BaseUtil.log(element.getText());
				String compare=docDesc+dt+" (link)";
				//BaseUtil.log(compare);
				//BaseUtil.log(element.getText().trim().equals(compare)+"");
			if (compare.equals(element.getText().trim())) {
				isOk=true;
				break;
			   } 
			}
		}
		
		if(isOk==false)
		{
			msg =  msg+" | Please make sure users can open view doc window by clicking doc link";
		}
		return isOk;
	}

	private boolean verifyCanOpenDisplayDocLinkWin() {
		boolean isOk = false;
		if (action.isElementPresent(By.linkText("Documents"))) {
			action.click(By.linkText("Documents"));
			String displayDocWin = action.getNewPopupWinHdl(homePageWin,search,
					encounterWin);
			if (displayDocWin != null) {
				action.selectWindow(displayDocWin);
				String actualPageTitle = action.getPageTitle();
				if (actualPageTitle.contains(expectedPageTitle1)) {
					  
					action.selectByValue(By.xpath("//select[@id='viewdoctype']"), "consult");
					if(action.isElementPresent(By.xpath("//table[@id='privateDocs']//td//a[contains(text(),'"+docDesc+dt+"')]")))
					{
						isOk=true;
						action.closeCurrentWindow();
						action.selectWindow(encounterWin);
						
					}else {
						msg =  msg+" | the documentlist not include this link";
					}
				} else {
					msg =  msg+" | Please make sure the open page is edocs window. ";
				}

			} else {
				msg =  msg+" | Cannot open edocs window by clicking 'Documents' link.";
			}
		} else {
			msg = msg+ " | No 'Documents' link exists on encounter page.";
		}
		return isOk;
	}

	
private void addHtmlDoc() {
	
	   // action.click(By.linkText("Documents"));
	     addDocWin = action.getNewPopupWinHdl(homePageWin,search,encounterWin);
		if (addDocWin != null) {
			action.selectWindow(addDocWin);
		}
		action.click(By.partialLinkText("+Add HTML"));
		String htmlwin=action.getNewPopupWinHdl(homePageWin, encounterWin, addDocWin,search);
		action.selectWindow(htmlwin);
		
		action.selectByValue(By.id("docType"), "others");
		action.selectByValue(By.id("docClass"), "Other Letter");
		
		action.clear(By.xpath("//input[@id='docSubClass']"));
		action.type(By.xpath("//input[@id='docSubClass']"), reportSubClass);
		
		action.clear(By.xpath("//input[@name='docDesc']"));
		action.type(By.xpath("//input[@name='docDesc']"), description+dt);
		
		action.selectByValue(By.xpath("//select[@name='responsibleId']"), provider);
		
		action.clear(By.xpath("//input[@name='source']"));
		action.type(By.xpath("//input[@name='source']"), sourceAuthor);
		
		action.clear(By.xpath("//input[@name='sourceFacility']"));
		action.type(By.xpath("//input[@name='sourceFacility']"), sourceFacility);
		
		
		action.clear(By.xpath("//input[@name='observationDate']"));
		action.type(By.xpath("//input[@name='observationDate']"), observationDate);
		
		action.clear(By.xpath("//textarea[@name='html']"));
		action.type(By.xpath("//textarea[@name='html']"), htmlvalue);
		
		
		action.click(By.xpath("//input[@type='submit'][@value='Submit']"));
		action.selectWindow(encounterWin);
		
	}
	

private boolean verifyCanOpenViewDocHtmlWin() {
	action.refreshCurrentWindow();
	try {
		Thread.sleep(1500);
	} catch (InterruptedException e) {
	}
	boolean isOk = false;
	if (verifyListAndLinksArePresent()) {
		
		if(action.isElementPresent(By.xpath("//img[@id='imgdocs5']")))
				{
		action.click(By.xpath("//img[@id='imgdocs5']"));
				}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		
		List<WebElement> elements=action.getWebElements(action.getWebElement(By.id("docslist")),
				By.className("links"));
		
		for(WebElement element:elements)
		{
			//BaseUtil.log(element.getText());
			String compare=description+dt;
			//BaseUtil.log(compare);
			//BaseUtil.log(element.getText().trim().equals(compare)+"");
		if (compare.equals(element.getText().trim())) {
			isOk=true;
			break;
		   } 
		}
	}
	
	if(isOk==false)
	{
		msg =  msg+" | Please make sure users can open view doc window by clicking doc html";
	}
	return isOk;
}
	


private boolean verifyCanOpenDisplayDocHtmlWin() {
	boolean isOk = false;
	if (action.isElementPresent(By.linkText("Documents"))) {
		action.click(By.linkText("Documents"));
		String displayDocWin = action.getNewPopupWinHdl(homePageWin,search,
				encounterWin);
		if (displayDocWin != null) {
			action.selectWindow(displayDocWin);
			String actualPageTitle = action.getPageTitle();
			if (actualPageTitle.contains(expectedPageTitle1)) {
				  
				action.selectByValue(By.xpath("//select[@id='viewdoctype']"), "others");
				if(action.isElementPresent(By.xpath("//table[@id='privateDocs']//td//a[contains(text(),'"+description+dt+"')]")))
				{
					isOk=true;
					action.closeCurrentWindow();
					action.selectWindow(encounterWin);
					
				}else {
					msg =  msg+" | the documentlist not include this html";
				}
			} else {
				msg =  msg+" | Please make sure the open page is edocs window. ";
			}

		} else {
			msg =  msg+" | Cannot open edocs window by clicking 'Documents' html.";
		}
	} else {
		msg = msg+ " | No 'Documents' html exists on encounter page.";
	}
	return isOk;
}



	private String getMsg() {
		return msg;
	}
}
