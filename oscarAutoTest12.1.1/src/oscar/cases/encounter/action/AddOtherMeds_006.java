package encounter.action;

import java.util.Properties;
import com.oscarbase.CaseConf;
import com.oscarbase.GetTestData;

public class AddOtherMeds_006 {

	public static void run() throws Exception {
		
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy001_008");
		String testcaseName = p.getProperty("TestcaseName6");
		String navOrCppId=p.getProperty("NavOrCppId_006");
		String navOrCppLinkText=p.getProperty("NavOrCppLinkText_006");
		
		NavAndCPPBox1 case006=new NavAndCPPBox1();
		case006.start(testcaseName, navOrCppId,navOrCppLinkText);
	}
}
