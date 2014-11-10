package encounter.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.oscarbase.WebDriverActions;

public class By096_VerifyAllEsArePresent {
	private String msg = "";
	public WebDriver driver;

	public By096_VerifyAllEsArePresent(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verify() {

		boolean areTheyPresent = true;
		WebDriverActions action = new WebDriverActions(driver);

		if (!action.isElementPresent(By.name("macro.label"))) {
			msg = " | 'Label' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("macro.displayOrder"))) {
			msg = msg + " | 'Display Order' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("macro.impression"))) {
			msg = msg + " | 'Impression Text' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("macro.followupNo"))) {
			msg = msg + " | 'Followup in' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("macro.followupUnit"))) {
			msg = msg + " | 'Followup Unit' select option list doesn't exist.";
			areTheyPresent = false;
		}
		
		if (!action.isElementPresent(By
				.name("macro.followupDoctorId"))) {
			msg = msg + " | 'with doctor' select option list doesn't exist.";
			areTheyPresent = false;
		}
		
		if (!action.isElementPresent(By
				.name("macro.testRecords"))) {
			msg = msg + " | 'Book Tests' input field doesn't exist.";
			areTheyPresent = false;
		}
		
		if (!action.isElementPresent(By
				.name("macro.ticklerRecipient"))) {
			msg = msg + " | 'Send tickler to' select option list doesn't exist.";
			areTheyPresent = false;
		}
		
		if (!action.isElementPresent(By
				.xpath("//input[@type='submit'][@value='Save']"))) {
			msg = msg + " | 'Save' button doesn't exist.";
			areTheyPresent = false;
		}	
		
		if (!action.isElementPresent(By
				.xpath("//input[@type='submit'][@value='Cancel']"))) {
			msg = msg + " | 'Cancel' button doesn't exist.";
			areTheyPresent = false;
		}	
		
		if (!action.isElementPresent(By
				.xpath("//input[@type='button'][@value='Close']"))) {
			msg = msg + " | 'Close' button doesn't exist.";
			areTheyPresent = false;
		}	

		return areTheyPresent;
	}

	public String getMsg() {
		return msg;
	}
}
