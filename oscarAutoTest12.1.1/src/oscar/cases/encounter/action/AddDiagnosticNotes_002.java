package encounter.action;

import java.util.Properties;
import com.oscarbase.CaseConf;
import com.oscarbase.GetTestData;

public class AddDiagnosticNotes_002 {

	public static void run() throws Exception {
		
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy001_008");
		String testcaseName = p.getProperty("TestcaseName2");
		String navOrCppId=p.getProperty("NavOrCppId_002");
		String navOrCppLinkText=p.getProperty("NavOrCppLinkText_002");
		
		NavAndCPPBox1 case002=new NavAndCPPBox1();
		case002.start(testcaseName, navOrCppId,navOrCppLinkText);
	}
}
