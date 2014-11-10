package com.oscarbase;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GetTestData {
	public Properties getDataProp(String testsuiteName, String dataFileName) {

		String allTestSuitesPath = CaseConf.getInstance().ALL_TEST_SUITES_PATH;
		String datafolderName = CaseConf.getInstance().DATA_FOLDER_NAME;
		String datafilePath = (new File("")).getAbsolutePath()
				+ allTestSuitesPath + testsuiteName + datafolderName;
		File configfile = new File(datafilePath + dataFileName + ".properties");
		BaseUtil.log("configfile is " + configfile);
		Properties prop = new Properties();

		try {
			FileInputStream is = new FileInputStream(configfile);
			prop.load(is);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}