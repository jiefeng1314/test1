package encounter.action;

import java.util.Properties;
import com.oscarbase.CaseConf;
import com.oscarbase.GetTestData;



public class AddCurrentHistory_001 {

	public static void run() throws Exception {
		
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy001_008");
		String testcaseName = p.getProperty("TestcaseName1");
		String navOrCppId=p.getProperty("NavOrCppId_001");
		String navOrCppLinkText=p.getProperty("NavOrCppLinkText_001");
		
		NavAndCPPBox1 case001=new NavAndCPPBox1();
		case001.start(testcaseName, navOrCppId,navOrCppLinkText);
	}
}
