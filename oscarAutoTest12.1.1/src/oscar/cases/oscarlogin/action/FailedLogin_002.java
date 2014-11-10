package oscarlogin.action;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import testlink.api.java.client.TestLinkAPIResults;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.oscarbase.*;

public class FailedLogin_002 {

	public static void run() throws Exception {
		// @parameters for testlink and some basic info about case.
		String username = CaseConf.getInstance().getUsername();
		String password = CaseConf.getInstance().getPassword();
		String pin = CaseConf.getInstance().getPin();
		String projectName = CaseConf.getInstance().getProjectName();
		String testplanName = CaseConf.getInstance().getTestplanName();
		String build = CaseConf.getInstance().getBuild();
		String testsuiteName = CaseConf.getInstance().getLoginOscarSuiteName();
		// @parameters for test data.
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "FailedLogin_002");
		String testcaseName = p.getProperty("TestcaseName");
		String wrongUsername = p.getProperty("WrongUsername");
		String wrongPassword = p.getProperty("WrongPassword");
		String wrongPin = p.getProperty("WrongPin");
		String result = null;
		String nota = null;
		Date dt = new Date();

		try {
			WebDriver driver = new FirefoxDriver();
			By002_LoginCase tryLogin = new By002_LoginCase();
			// Case on testlink is FailLogin001.
			boolean wrongCase01 = tryLogin.cases(wrongUsername, password, pin,
					driver);
			// Case on testlink is FailLogin002.
			boolean wrongCase02 = tryLogin.cases(username, wrongPassword, pin,
					driver);
			// This correct login is to avoid locking account because of three
			// continuous failed login.
			tryLogin.cases(username, password, pin, driver);
			driver.findElement(By.id("firstTable"))
					.findElement(By.partialLinkText("Log Out ")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Case on testlink is FailLogin003.
			boolean wrongCase03 = tryLogin.cases(username, password, wrongPin,
					driver);
			driver.close();

			if (wrongCase01 && wrongCase02 && wrongCase03) {
				result = TestLinkAPIResults.TEST_PASSED;
				BaseUtil.log(result);
			} else {
				BaseUtil.log("Users can login oscar with incorrect username or password/pin.");
				result = TestLinkAPIResults.TEST_FAILED;
			}

		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			nota = e.getMessage();
			e.printStackTrace();
		} finally {
			TestExecutionResult.reportTestCaseResult(projectName, testplanName,
					testcaseName, build, nota, result);
			BaseUtil.log("FailedLogin_002: Execution Terminated at " + dt);
		}
	}
}
