package encounter.action;

import java.util.Properties;
import com.oscarbase.CaseConf;
import com.oscarbase.GetTestData;

public class AddMedicalHistory_004 {

	public static void run() throws Exception {
		
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy001_008");
		String testcaseName = p.getProperty("TestcaseName4");
		String navOrCppId=p.getProperty("NavOrCppId_004");
		String navOrCppLinkText=p.getProperty("NavOrCppLinkText_004");
		
		NavAndCPPBox2 case004=new NavAndCPPBox2();
		case004.start(testcaseName, navOrCppId,navOrCppLinkText);
	}
}
