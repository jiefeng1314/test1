package oscarlogin.action;

import java.util.Date;
import java.util.Properties;
import testlink.api.java.client.TestLinkAPIResults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.oscarbase.*;

public class CorrectLogout_005 {

	public static void run() throws Exception {
		// @parameters for testlink and some case info.
		String loginurl = CaseConf.getInstance().getOscarLoginUrl();
		String projectName = CaseConf.getInstance().getProjectName();
		String testplanName = CaseConf.getInstance().getTestplanName();
		String build = CaseConf.getInstance().getBuild();
		String testsuiteName = CaseConf.getInstance().getLoginOscarSuiteName();
		// @parameters for test data.
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "CorrectLogout_005");
		String testcaseName = p.getProperty("TestcaseName");
		String tabletitle = p.getProperty("TableTitle");

		String result = null;
		String nota = null;
		Date dt = new Date();
		WebDriver driver = new FirefoxDriver();
		try {
			LoginOscar.login(driver);
			String gettitle = driver.findElement(
					By.xpath("//tr[@id='ivoryBar']/td[@class='title']"))
					.getText();
			Thread.sleep(3000);
			BaseUtil.log("Login step 1");
			BaseUtil.log(gettitle); 
			BaseUtil.log(tabletitle); 
			BaseUtil.log(gettitle.trim().compareTo(tabletitle.trim())+""); 
			if (gettitle.trim().compareTo(tabletitle.trim()) == 0) {
				driver.findElement(By.id("firstTable"))
						.findElement(By.partialLinkText("Log Out ")).click();
				BaseUtil.log("step2: find out logout and click it! ");
				Thread.sleep(300);
				if (driver.getCurrentUrl().compareTo(loginurl) == 0) {
					result = TestLinkAPIResults.TEST_PASSED;
					BaseUtil.log("result is " + result);
				} else {
					result = TestLinkAPIResults.TEST_FAILED;
				}

			} else {
				result = TestLinkAPIResults.TEST_FAILED;
			}

		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			nota = e.getMessage();
			e.printStackTrace();
		} finally {
			TestExecutionResult.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, nota, result);
			System.out
					.println("CorrectLogout_005: Execution Terminated at " + dt);
		}
		driver.close();
	}
}
