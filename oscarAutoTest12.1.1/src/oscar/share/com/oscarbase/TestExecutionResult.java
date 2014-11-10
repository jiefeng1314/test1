package com.oscarbase;
import java.text.SimpleDateFormat;
import java.util.Date;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;

public class TestExecutionResult{
	
	public static void reportTestCaseResult(String projectName,
			String testplanName, String testcaseName, String build, String note,
			String result) throws TestLinkAPIException {
		
		String DEVKEY=CaseConf.getInstance().getDevkey();
		String URL=CaseConf.getInstance().getTestlinkUrl();
		/*BaseUtil.log(DEVKEY);
		BaseUtil.log(URL);
		BaseUtil.log(projectName);
		BaseUtil.log(testplanName);
		BaseUtil.log(testcaseName);
		BaseUtil.log(note);
		BaseUtil.log(result);*/
		TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEVKEY, URL);
		testlinkAPIClient.reportTestCaseResult(projectName, testplanName,testcaseName, build.trim(), note,result);

	
	}
}