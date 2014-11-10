package encounter.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.oscarbase.WebDriverActions;

public class By094_VerifyAllEsArePresent {
	private String msg = "";
	public WebDriver driver;

	public By094_VerifyAllEsArePresent(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verify() {

		boolean areTheyPresent = true;
		WebDriverActions action = new WebDriverActions(driver);

		if (!action.isElementPresent(By.id("search_coding_system"))) {
			msg = " | 'Codeing system' select option list doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.id("description"))) {
			msg = msg + " | 'Description' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.id("startDate"))) {
			msg = msg + " | 'Start Date' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.id("endDate"))) {
			msg = msg + " | 'End Date' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.id("code"))) {
			msg = msg + " | 'Code' input field doesn't exist.";
			areTheyPresent = false;
		}
		
		if (!action.isElementPresent(By
				.id("episode.status"))) {
			msg = msg + " | 'Status' select list doesn't exist.";
			areTheyPresent = false;
		}
		
		if (!action.isElementPresent(By
				.xpath("//input[@type='submit'][@value='Submit']"))) {
			msg = msg + " | 'Submit' button doesn't exist.";
			areTheyPresent = false;
		}	

		return areTheyPresent;
	}

	public String getMsg() {
		return msg;
	}
}
