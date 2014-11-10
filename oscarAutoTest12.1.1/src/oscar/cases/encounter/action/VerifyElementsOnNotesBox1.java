package encounter.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.oscarbase.WebDriverActions;

public class VerifyElementsOnNotesBox1 {

	private String msg = "";
	public WebDriver driver;

	public VerifyElementsOnNotesBox1(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verify() {

		boolean areTheyPresent = true;
		WebDriverActions action = new WebDriverActions(driver);

		if (!action.isElementPresent(By.id("noteEditTxt"))) {
			msg = " | Text area doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.id("startdate"))) {
			msg = msg + " | 'Start Date' field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.id("resolutiondate"))) {
			msg = msg + " | 'Resolution Date' field doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.id("anno"))) {
			msg = msg + " | 'Annotation' button doesn't exist.";
			areTheyPresent = false;
		}

		if (!action
				.isElementPresent(By
						.xpath("//input[@type='image'][@title='Copy to Current Note']"))) {
			msg = msg + " | 'Copy to Current Note' button doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By
				.xpath("//input[@type='image'][@title='Archive']"))) {
			msg = msg + " | 'Archive' button doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By
				.xpath("//input[@type='image'][@title='Sign, Save and Exit']"))) {
			msg = msg + " | 'Sign, Save and Exit' button doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By
				.xpath("//input[@type='image'][@title='Exit']"))) {
			msg = msg + " | 'Exit' button doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By
				.xpath("//input[@type='image'][@title='Exit']"))) {
			msg = msg + " | 'Exit' button doesn't exist.";
			areTheyPresent = false;
		}

		if (!action.isElementPresent(By.id("position"))) {
			msg = msg + " | 'Insert Position' button doesn't exist.";
			areTheyPresent = false;
		}
		return areTheyPresent;
	}

	public String getMsg() {
		return msg;
	}
}
