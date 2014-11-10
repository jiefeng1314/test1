package encounter.action;

import java.util.Properties;
import com.oscarbase.CaseConf;
import com.oscarbase.GetTestData;

public class AddPastOcularHistory_003 {

	public static void run() throws Exception {
		
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy001_008");
		String testcaseName = p.getProperty("TestcaseName3");
		String navOrCppId=p.getProperty("NavOrCppId_003");
		String navOrCppLinkText=p.getProperty("NavOrCppLinkText_003");
		
		NavAndCPPBox1 case003=new NavAndCPPBox1();
		case003.start(testcaseName, navOrCppId,navOrCppLinkText);
	}
}
