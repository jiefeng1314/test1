package com.oscarbase;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CaseConf {

	public final String GLOBAL_PROP_FILE = "/src/oscar/start/caseconfig/GlobalData.properties";
	public final String ALL_TEST_SUITES_PATH = "/src/oscar/cases/";
	public final String DATA_FOLDER_NAME = "/data/";

	static CaseConf conf = null;
	// login information
	String baseUrl = null;
	String username = null;
	String password = null;
	String pin = null;
	// testlink parameters
	String testlinkUrl = null;
	String devkey = null;
	String projectName = null;
	String testplanName = null;
	String build = null;
	String adminUrl = null;
	String oscarLoginUrl = null;
	String logfilePath = null;
	// testsuite Name parameters
	String loginOscarSuiteName = null;
	String adminPageSuiteName = null;
	String billingSuiteName=null;
	String encounterSuiteName=null;
	String createAppointmentTime=null;
	String createAppointmentDemogName=null;
	String sendNumber=null;

	public static CaseConf getInstance() {
		if (conf == null) {
			conf = new CaseConf();
			conf.init();
		}
		return conf;
	}

	public void init() {
		String filepath = (new File("")).getAbsolutePath() + GLOBAL_PROP_FILE;
		File configfile = new File(filepath);
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

		baseUrl = prop.getProperty("BaseUrl");
		username = prop.getProperty("Username");
		password = prop.getProperty("Password");
		pin = prop.getProperty("Pin");

		testlinkUrl = prop.getProperty("TestlinkUrl");
		devkey = prop.getProperty("DEVKEY");
		projectName = prop.getProperty("ProjectName");
		testplanName = prop.getProperty("TestplanName");
		build = prop.getProperty("Build");
		adminUrl = prop.getProperty("AdminUrl");
		oscarLoginUrl = prop.getProperty("OscarLoginUrl");
		logfilePath = prop.getProperty("LoginfilePath");
		loginOscarSuiteName = prop.getProperty("LoginOscarSuiteName");
		adminPageSuiteName = prop.getProperty("AdminPageSuiteName");
		billingSuiteName=prop.getProperty("BillingSuiteName");
		encounterSuiteName=prop.getProperty("EncounterSuiteName");
		createAppointmentTime=prop.getProperty("createAppointmentTime");
		createAppointmentDemogName=prop.getProperty("createAppointmentDemogName");
		sendNumber=prop.getProperty("sendNumber");
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPin() {
		return pin;
	}

	public String getTestlinkUrl() {
		return testlinkUrl;
	}

	public String getDevkey() {
		return devkey;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getTestplanName() {
		return testplanName;
	}

	public String getBuild() {
		return build;
	}

	public String getAdminUrl() {
		return adminUrl;
	}

	public String getOscarLoginUrl() {
		return oscarLoginUrl;
	}

	public String getLogfilePath() {
		return logfilePath;
	}

	public String getLoginOscarSuiteName() {
		return loginOscarSuiteName;
	}

	public String getAdminPageSuiteName() {
		return adminPageSuiteName;
	}
	public String getBillingSuiteName() {
		return billingSuiteName;
	}
	public String getEncounterSuiteName() {
		return encounterSuiteName;
	}

	public String getCreateAppointmentTime() {
		return createAppointmentTime;
	}
	
	public String getCreateAppointmentDemogName() {
		return createAppointmentDemogName;
	}
	
	public String getSendNumber() {
		return sendNumber;
	}
	

}