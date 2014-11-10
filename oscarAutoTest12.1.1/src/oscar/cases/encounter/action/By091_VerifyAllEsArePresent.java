package encounter.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.oscarbase.WebDriverActions;

public class By091_VerifyAllEsArePresent {
	private String msg = "";
	public WebDriver driver;

	public By091_VerifyAllEsArePresent(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verify() {

		boolean areTheyPresent = true;
		WebDriverActions action = new WebDriverActions(driver);

		if (!action.isElementPresent(By.id("pdate"))) {
			msg = " | 'Date' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.id("pdate_cal"))) {
			msg = msg + " | Calendar image doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("proc.eye"))) {
			msg = msg + " | 'Eye' select option list doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("proc.procedureName"))) {
			msg = msg + " | 'Procedure' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("proc.doctor"))) {
			msg = msg + " | 'Docotor Name' select option list doesn't exist.";
			areTheyPresent = false;
		}
		if (!action.isElementPresent(By.name("proc.location"))) {
			msg = msg + " | 'Location' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("proc.procedureNote"))) {
			msg = msg + " | 'Procedure Notes' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By
				.xpath("//input[@type='submit'][@value='Submit']"))) {
			msg = msg + " | 'Submit' button doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("cancel"))) {
			msg = msg + " | 'Cancel' button doesn't exist.";
			areTheyPresent = false;
		}

		return areTheyPresent;
	}

	public String getMsg() {
		return msg;
	}
}
