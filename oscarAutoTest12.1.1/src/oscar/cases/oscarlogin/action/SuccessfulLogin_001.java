package oscarlogin.action;

import java.util.Date;
import java.util.Properties;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIResults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.oscarbase.*;

public class SuccessfulLogin_001 {

	public static void run() throws Exception {
		// @parameters for testlink and some case info.
		String DEVKEY = CaseConf.getInstance().getDevkey();
		String URL = CaseConf.getInstance().getTestlinkUrl();
		String projectName = CaseConf.getInstance().getProjectName();
		String testplanName = CaseConf.getInstance().getTestplanName();
		String build = CaseConf.getInstance().getBuild();
		String testsuiteName = CaseConf.getInstance().getLoginOscarSuiteName();
		// @parameters for test data.
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName,
				"SuccessfulLogin_001");
		String testcaseName = p.getProperty("TestcaseName");
		String tabletitle = p.getProperty("TableTitle");

		String result = null;
		String note = null;
		Date dt;
		dt = new Date();
		FirefoxProfile profile = new FirefoxProfile();  
		profile.setPreference("intl.accept_languages", "en-US");   
		WebDriver driver = new FirefoxDriver(profile);
		try {

			LoginOscar.login(driver);
			String gettitle = driver.findElement(
					By.xpath("//tr[@id='ivoryBar']/td[@class='title']"))
					.getText();
			Thread.sleep(3000);
			if (gettitle.compareTo(tabletitle) == 0) {
				BaseUtil.log("Successfully log in");
				result = TestLinkAPIResults.TEST_PASSED;

			} else {
				BaseUtil.log("Error on page");
				result = TestLinkAPIResults.TEST_FAILED;
			}
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			note = e.getMessage();
			e.printStackTrace();
		} finally {
			TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEVKEY, URL);
			testlinkAPIClient.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, note, result);
			System.out.println("SuccessfulLogin_001: Execution Terminated at "
					+ dt);
		}
	 driver.close();
	}
}