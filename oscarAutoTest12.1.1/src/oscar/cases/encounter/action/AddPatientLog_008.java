package encounter.action;

import java.util.Properties;
import com.oscarbase.CaseConf;
import com.oscarbase.GetTestData;

public class AddPatientLog_008 {

	public static void run() throws Exception {
		
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy001_008");
		String testcaseName = p.getProperty("TestcaseName8");
		String navOrCppId=p.getProperty("NavOrCppId_008");
		String navOrCppLinkText=p.getProperty("NavOrCppLinkText_008");
		
		NavAndCPPBox1 case008=new NavAndCPPBox1();
		case008.start(testcaseName, navOrCppId,navOrCppLinkText);
	}
}
