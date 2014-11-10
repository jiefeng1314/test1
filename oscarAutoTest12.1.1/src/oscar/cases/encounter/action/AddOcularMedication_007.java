package encounter.action;

import java.util.Properties;
import com.oscarbase.CaseConf;
import com.oscarbase.GetTestData;

public class AddOcularMedication_007 {

	public static void run() throws Exception {
		
		String testsuiteName = CaseConf.getInstance().getEncounterSuiteName();
		GetTestData testData = new GetTestData();
		Properties p = testData.getDataProp(testsuiteName, "UsedBy001_008");
		String testcaseName = p.getProperty("TestcaseName7");
		String navOrCppId=p.getProperty("NavOrCppId_007");
		String navOrCppLinkText=p.getProperty("NavOrCppLinkText_007");
		
		NavAndCPPBox1 case007=new NavAndCPPBox1();
		case007.start(testcaseName, navOrCppId,navOrCppLinkText);
	}
}
