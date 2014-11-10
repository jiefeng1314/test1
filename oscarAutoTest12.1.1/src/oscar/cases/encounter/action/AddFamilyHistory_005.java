package encounter.action;

import java.util.Properties;
import com.oscarbase.CaseConf;
import com.oscarbase.GetTestData;

public class AddFamilyHistory_005 {

	public static void run() throws Exception {
		
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy001_008");
		String testcaseName = p.getProperty("TestcaseName5");
		String navOrCppId=p.getProperty("NavOrCppId_005");
		String navOrCppLinkText=p.getProperty("NavOrCppLinkText_005");
		
		NavAndCPPBox3 case005=new NavAndCPPBox3();
		case005.start(testcaseName, navOrCppId,navOrCppLinkText);
	}
}
