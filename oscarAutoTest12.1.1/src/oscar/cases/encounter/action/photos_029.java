package encounter.action;

import java.io.File;

import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import testlink.api.java.client.TestLinkAPIResults;
import com.oscarbase.*;

public class photos_029 {
	private static String fileName = "src/oscar/cases/encounter/data/huan029.jpg";
	private static String updatefileName = "src/oscar/cases/encounter/data/huan030.jpg";
	private String homePageWin = "";
	private String encounterWin = "";
	private String addPhotoWin = "";
	private String displayPhotoWin = "";
	private String photoName = "";
	private static String expectedPageTitle1 = "";
	private static String expectedPageTitle2 = "";
	private static String sourceFacility = "";
	private static String sourceAuthor = "";
	private static String phototitle="";
	private static String reportSubClass="";
	private static String note="";
	private static String editWin="";
	private String msg = "";
	public static WebDriver driver =null;
	public static WebDriverActions action = null;
	public static Date dt = new Date();
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
		Properties p = testData.getDataProp(testsuiteName, "UsedBy028-029");
		String testcaseName = p.getProperty("TestcaseName2");
		expectedPageTitle1 = p.getProperty("ExpectedPageTitle1");
		expectedPageTitle2 = p.getProperty("ExpectedPageTitle2");
		phototitle=p.getProperty("phototitle").trim();
		reportSubClass=p.getProperty("reportSubClass").trim();
		sourceFacility=p.getProperty("sourceFacility").trim();
		sourceAuthor=p.getProperty("sourceAuthor").trim();
		note=p.getProperty("note").trim();
		String result = "";
		String note = "";
		

		try {
			photos_029 case029 = new photos_029();
			LoginOscar.login(driver);
			case029.openEncounterWin();

			if (case029.canOpenAddDocWin()) {
                   BaseUtil.log("enter 1");
				case029.addPhoto();
				   BaseUtil.log("enter 2");
				if (case029.verifyCanOpenViewPhotoWin()) {
					BaseUtil.log("enter 3");
				if (case029.verifyAddAndUpdate()) {
					
					BaseUtil.log("enter 4");
					
					if(case029.verifyUpdate())
					{    BaseUtil.log("enter 5");
						if(case029.deleteAndVerify())
						{
					    	BaseUtil.log("pass");
						    result = TestLinkAPIResults.TEST_PASSED;
						}else {
							note = case029.getMsg();
							result = TestLinkAPIResults.TEST_FAILED;
						}
					}else {
						note = case029.getMsg();
						result = TestLinkAPIResults.TEST_FAILED;
					}
				}else {
					result = TestLinkAPIResults.TEST_FAILED;
					note = case029.getMsg();
				}
				}else {
					result = TestLinkAPIResults.TEST_FAILED;
					note = case029.getMsg();
				}
		
			} else {
				result = TestLinkAPIResults.TEST_FAILED;
				note = case029.getMsg();
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
			System.out.println("Photos_029: Execution Terminated at " + dt);
		}
		driver.quit();
		Thread.sleep(3000);
	}

	private void openEncounterWin() {
		homePageWin = action.getCurrentHandle();
		action.click(By.linkText("Search"));
		String seach=action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(seach);
		action.type(By.xpath("//input[@name='keyword']"), createAppointmentDemogName);
		action.click(By.xpath("//input[@type='SUBMIT'][@value='Search']"));
		action.click(By.linkText("Demogp' No"));
		action.click(By.linkText("E"));
		action.driverWait(10);
		action.closeCurrentWindow();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		encounterWin = action.getNewPopupWinHdl(homePageWin);
		action.selectWindow(encounterWin);
	}

	private boolean photosIsPresent() {
		if (action.isElementPresent(By.id("photos"))) {
			return true;
		} else {
			msg =  msg+" | 'Photos' doesn't exist from the left navigator.";
			return false;
		}
	}

	private boolean canOpenAddDocWin() {
        
		if (photosIsPresent()) {
			if (action.isElementPresent(
					action.getWebElement(By.id("menuTitlephotos")),
					By.linkText("+"))) {
			
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				action.click(By.xpath("//div[@id='menuTitlephotos']//a"));
				
				
				addPhotoWin = action.getNewPopupWinHdl(homePageWin,
						encounterWin);
				if (addPhotoWin != null) {
					action.selectWindow(addPhotoWin);
					String actualPageTitle = action.getPageTitle();
					if (actualPageTitle.contains(expectedPageTitle1)) {
						return true;
					} else {
						msg =  msg+" | Please make sure the open page is 'Photos' page.";
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

	private void addPhoto() {
		
		if (action.getValue(By.id("addDocDiv"), "style").contains(
				"display: none")) {
			action.click(By.partialLinkText("+Add Document"));
		}
		
		if (!action.isSelected(By
				.cssSelector("#addDocDiv option[value='photo']"))) {
			action.selectByValue(By.id("docType"), "photo");
		}
		
		action.clear(By.xpath("//input[@name='docDesc']"));
		action.type(By.xpath("//input[@name='docDesc']"),phototitle+dt);
		
		action.clear(By.xpath("//input[@id='docSubClass']"));
		action.type(By.xpath("//input[@id='docSubClass']"), reportSubClass);
		
		
		action.selectByValue(By.xpath("//select[@name='docClass']"), "Other Letter");
		selectFile(fileName);
		action.click(By.xpath("//input[@type='submit'][@value='Add']"));
		action.closeCurrentWindow();
		action.selectWindow(encounterWin);
		action.refreshCurrentWindow();
	}

	private void selectFile(String name) {

		File file = new File(name);

		String path = file.getAbsolutePath();
		BaseUtil.log(path);

		action.type(By.xpath("//input[@type='file'][@name='docFile']"), path);
	}

	private boolean verifyListAndLinksArePresent() {

		if (action.isElementPresent(By.id("photoslist"))) {
			WebElement list = action.getWebElement(By.id("photoslist"));
			if (action.isElementPresent(list, By.className("links"))) {
				return true;
			} else {
				msg = msg+ " | No photo link exists on Encounter page.";
				return false;
			}
		} else {
			msg =  msg+" | No photo list exists on Encounter page.";
			return false;
		}
	}

	private boolean verifyCanOpenViewPhotoWin() {

		boolean isOk = false;
		if (verifyListAndLinksArePresent()) {
			BaseUtil.log(phototitle+dt);
			if(action.isElementPresent(By.xpath("//ul[@id='photoslist']//span//a[contains(text(),'"+phototitle+dt+"')]")))
			{  
//				//ul[@id='photoslist']//span//a[contains(text(),'testhuanWed Nov 05 15:30:47 CST 2014')]
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//				}
//			action.click(action.getWebElement(By.id("photoslist")),By.className("links"));
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//			}
//			String viewPhotoWin = action.getNewPopupWinHdl(homePageWin,encounterWin);
//			if (viewPhotoWin != null) {
//				action.selectWindow(viewPhotoWin);
//				String actualPageTitle = action.getPageTitle();
//				
//                BaseUtil.log(actualPageTitle);
//                BaseUtil.log(expectedPageTitle2);
//                
//				if (actualPageTitle.contains(expectedPageTitle2)) {
//					isOk = true;
//				} else {
//					msg = msg+ " | Please make sure users can open view photo window by clicking doc link.";
//				}
//				action.closeCurrentWindow();
//				action.selectWindow(encounterWin);
//			} else {
//				msg = msg+ " | No window will pop up by clicking photo link.";
//			}
				isOk=true;
		}
		else {
		msg = msg+ " | photoslist not exist this photo";
		}
			
		}
		return isOk;
	}

	private boolean verifyAddAndUpdate() {
		boolean isOk = true;
		if (action.isElementPresent(By.linkText("Photos"))) {
			action.click(By.linkText("Photos"));
			displayPhotoWin = action.getNewPopupWinHdl(homePageWin,
					encounterWin);
			if (displayPhotoWin != null) {
				action.selectWindow(displayPhotoWin);
				String actualPageTitle = action.getPageTitle();
				if (actualPageTitle.contains(expectedPageTitle1)) {
					
					  action.selectByValue(By.xpath("//select[@id='viewstatus']"), "active");
			          action.selectByValue(By.xpath("//select[@id='viewdoctype']"), "photo");
			          if(!action.isElementPresent(By.xpath("//table[@id='privateDocs']//tr//td//a[contains(text(),'"+phototitle+dt+"')]")))
			          {
			        	  msg = msg+ " | the photo not exist this photo. ";
							isOk=false;
			          }
					
			          action.selectByValue(By.xpath("//select[@id='viewdoctype']"), "lab");
			          if(action.isElementPresent(By.xpath("//table[@id='privateDocs']//tr//td//a[contains(text(),'"+phototitle+dt+"')]")))
			          {
			        	  msg = msg+ " | the other type  exist this photo. ";
							isOk=false;
			          }
			          
			          action.selectByValue(By.xpath("//select[@id='viewdoctype']"), "photo");
			          action.selectByValue(By.xpath("//select[@id='viewstatus']"), "deleted");
			        
			          if(action.isElementPresent(By.xpath("//table[@id='privateDocs']//tr//td//a[contains(text(),'"+phototitle+dt+"')]")))
			          {
			        	  msg = msg+ " | the photo not delete ,but the delete view exist this photo. ";
							isOk=false;
			          }
			
			          action.selectByValue(By.xpath("//select[@id='viewstatus']"), "active");
			          action.click(By.xpath("//table[@id='privateDocs']//tr//td//a[contains(text(),'"+phototitle+dt+"')]/../following-sibling::td/a/img[@title='Edit']"));
			          editWin=action.getNewPopupWinHdl(homePageWin, encounterWin, displayPhotoWin);
			          if(editWin!=null)
			          {
			          action.selectWindow(editWin);
			          
			          String docTypeValue=action.getWebElement(By.xpath("//select[@id='docType']")).getAttribute("value");
			          String docClassValue=action.getWebElement(By.xpath("//select[@id='docClass']")).getAttribute("value");
			          String docSubClassValue=action.getWebElement(By.xpath("//input[@id='docSubClass']")).getAttribute("value");
			          String docDescValue=action.getWebElement(By.xpath("//input[@name='docDesc']")).getAttribute("value");
			          photoName =action.getWebElement(By.xpath("//td[contains(text(),'File Name:')]/following-sibling::td/div")).getText();
			          BaseUtil.log(docTypeValue+"*"+docClassValue+"*"+docSubClassValue+"*"+docDescValue+"*"+docTypeValue+"*"+photoName);
			          if(!(docTypeValue.trim().equals("photo")&&docClassValue.trim().equals("Other Letter"))&&docSubClassValue.trim().equals(reportSubClass)&&docDescValue.trim().equals(phototitle+dt))
			          {
			        	    msg = msg+ " | add photo OK . but the content is error";
							isOk=false;
			        	  
			          }
			          }else {
			        	  msg = msg+ " | not edit win";
							isOk=false;
					}
			          
			        //update
			          action.clear(By.xpath("//input[@name='source']"));
			          action.type(By.xpath("//input[@name='source']"), sourceAuthor);
			          action.clear(By.xpath("//input[@name='sourceFacility']"));
			          action.type(By.xpath("//input[@name='sourceFacility']"), sourceFacility);
			          selectFile(updatefileName);
			          
			          action.click(By.xpath("//input[@value='Annotation']"));
			          action.selectWindow( action.getNewPopupWinHdl(homePageWin, encounterWin, displayPhotoWin,editWin));
			          action.type(By.xpath("//textarea[@name='note']"), note);
			          action.click(By.xpath("//input[@value='Save']"));
			          
			          
			          action.selectWindow(editWin);
			          action.click(By.xpath("//input[@value='Update']"));
			          
			          
			          
					
				} else {
					msg = msg+ " | Please make sure the open page is edocs window. ";
					isOk=false;
				}

			} else {
				msg =  msg+" | Cannot open edocs window by clicking 'Photos' link.";
				isOk=false;
			}
		} else {
			msg =  msg+" | No 'Photos' link exists on encounter page.";
			isOk=false;
		}
		return isOk;
	}
	
	
	
	private boolean verifyUpdate()
	{
		boolean isOk=true;
		
        action.selectWindow(displayPhotoWin);
        action.click(By.xpath("//table[@id='privateDocs']//tr//td//a[contains(text(),'"+phototitle+dt+"')]/../following-sibling::td/a/img[@title='Edit']"));
        String editWinagain=action.getNewPopupWinHdl(homePageWin, encounterWin, displayPhotoWin);
        action.selectWindow(editWinagain);
        
        String updatesourceAuthor=action.getWebElement(By.xpath("//input[@name='source']")).getAttribute("value").trim();
        String updatesourceFacility=action.getWebElement(By.xpath("//input[@name='sourceFacility']")).getAttribute("value").trim();
        String updatephotoName=action.getWebElement(By.xpath("//td[contains(text(),'File Name:')]/following-sibling::td/div")).getText();
        
        if(!(updatesourceAuthor.equals(sourceAuthor)&&updatesourceFacility.equals(sourceFacility)))
        {
      	  msg = msg+ " | update after  the content is error";
				isOk=false;
        }
        
        if(photoName.equals(updatephotoName))
        {
      	  msg = msg+ " | update after  the photo not update";
				isOk=false;
        }
        
        action.click(By.xpath("//input[@value='Cancel']"));
        action.selectWindow(displayPhotoWin);
        
        action.click(By.xpath("//table[@id='privateDocs']//tr//td//a[contains(text(),'"+phototitle+dt+"')]/../following-sibling::td/a[3]"));
        
        String annotationWin=action.getNewPopupWinHdl(homePageWin, encounterWin, displayPhotoWin);
        action.selectWindow(annotationWin);
        
        String annotationvalue=action.getWebElement(By.xpath("//textarea[@name='note']")).getText();
        
        if(!annotationvalue.trim().equals(note))
        {
        	msg = msg+ " | update annotation after,  the annotation content is error";
			isOk=false;
        }
		
        action.click(By.xpath("//input[@value='Cancel']"));
        action.selectWindow(displayPhotoWin);
        
        action.click(By.xpath("//table[@id='privateDocs']//tr//td//a[contains(text(),'"+phototitle+dt+"')]/../following-sibling::td/a[4]"));
        
        String ticklerWin=action.getNewPopupWinHdl(homePageWin, encounterWin, displayPhotoWin);
        
        if(ticklerWin==null)
        {
        	msg = msg+ " | the tickler link is error";
			isOk=false;
        }
        
        action.selectWindow(displayPhotoWin);
        
		return isOk;
		
	}
	
	
	private boolean deleteAndVerify()
	{
		boolean isOk=true;
        action.click(By.xpath("//table[@id='privateDocs']//tr//td//a[contains(text(),'"+phototitle+dt+"')]/../following-sibling::td/a/img[@title='Delete']"));
        action.accept();
        
        action.selectByValue(By.xpath("//select[@id='viewstatus']"), "active");
        action.selectByValue(By.xpath("//select[@id='viewdoctype']"), "photo");
        if(action.isElementPresent(By.xpath("//table[@id='privateDocs']//tr//td//a[contains(text(),'"+phototitle+dt+"')]")))
        {
      	        msg = msg+ " | after delete the photo still exist this photo. ";
				isOk=false;
        }
        
        
        action.selectByValue(By.xpath("//select[@id='viewdoctype']"), "photo");
        action.selectByValue(By.xpath("//select[@id='viewstatus']"), "deleted");
        
        if(!action.isElementPresent(By.xpath("//table[@id='privateDocs']//tr//td//a[contains(text(),'"+phototitle+dt+"')]")))
        {
      	        msg = msg+ " | after delete the photo ,the delete view not exist this photo. ";
				isOk=false;
        }
        
        
        action.selectWindow(encounterWin);
        action.refreshCurrentWindow();
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
        
    	if(action.isElementPresent(By.xpath("//ul[@id='photoslist']//span//a[contains(text(),'"+phototitle+dt+"')]")))
		{
    		msg = msg+ " | after delete photoslist still exist this photo";
    		isOk=false;
		}
	
		return isOk;
		
	}

	private String getMsg() {
		return msg;
	}
}
