package encounter.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.oscarbase.WebDriverActions;

public class By092_VerfiyAllEsArePresent {
	private String msg = "";
	public WebDriver driver;

	public By092_VerfiyAllEsArePresent(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verify() {

		boolean areTheyPresent = true;
		WebDriverActions action = new WebDriverActions(driver);

		if (!action.isElementPresent(By.name("keyword"))) {
			msg = " | 'Demographic Name' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("xml_appointment_date"))) {
			msg = msg + " | 'Service Date' input field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("priority"))) {
			msg = msg + " | 'Priority' select option list doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("task_assigned_to"))) {
			msg = msg + " | 'Task Assigned to' select option list doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.name("textarea"))) {
			msg = msg + " | 'Remind Message' input field doesn't exist.";
			areTheyPresent = false;
		}
		
		if (!action.isElementPresent(By
				.xpath("//input[@type='button'][@value='Cancel and EXIT']"))) {
			msg = msg + " | 'Cancel and Exit' button doesn't exist.";
			areTheyPresent = false;
		}
		
		if (!action.isElementPresent(By
				.xpath("//input[@type='button'][@value='Submit and EXIT']"))) {
			msg = msg + " | 'Submit and Exit' button doesn't exist.";
			areTheyPresent = false;
		}	

		return areTheyPresent;
	}

	public String getMsg() {
		return msg;
	}
}
