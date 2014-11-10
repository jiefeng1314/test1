package encounter.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.oscarbase.WebDriverActions;

public class VerifyElementsOnNotesBox3 {

	private String msg = "";
	public WebDriver driver;

	public VerifyElementsOnNotesBox3(WebDriver driver) {
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

		if (action.isElementPresent(By.id("Itemageatonset"))) {
			if (!action.getValue(By.id("Itemageatonset"), "style").isEmpty()
					|| !action.isElementPresent(By.id("ageatonset"))) {

				msg = msg
						+ " | Item 'Age at Onset' doesn't display or input element 'Age at Onset' doesn't exist.";
				areTheyPresent = false;
			}
		} else {
			msg = msg + " | Item 'Age at Onset' doesn't exist.";
			areTheyPresent = false;
		}

		if (action.isElementPresent(By.id("Itemtreatment"))) {
			if (!action.getValue(By.id("Itemtreatment"), "style").isEmpty()
					|| !action.isElementPresent(By.id("treatment"))) {
				msg = msg
						+ " | Item 'Treatment' doesn't display or input element 'Treatment' doesn't exist.";
				areTheyPresent = false;
			}
		} else {
			msg = msg + " | Item 'Treatment' doesn't exist.";
			areTheyPresent = false;
		}

		if (action.isElementPresent(By.id("Itemrelationship"))) {
			if (!action.getValue(By.id("Itemrelationship"), "style").isEmpty()
					|| !action.isElementPresent(By.id("relationship"))) {
				msg = msg
						+ " | Item 'Relationship' doesn't display or input element 'Relationship' doesn't exist.";
				areTheyPresent = false;
			}
		} else {
			msg = msg + " | Item 'Relationship' doesn't exist.";
			areTheyPresent = false;
		}
		
		if (action.isElementPresent(By.id("Itemlifestage"))) {
			if (!action.getValue(By.id("Itemlifestage"), "style").isEmpty()
					|| !action.isElementPresent(By.id("lifestage"))) {
				msg = msg
						+ " | Item 'Life Stage' doesn't display or input element 'Life Stage' doesn't exist.";
				areTheyPresent = false;
			}
		} else {
			msg = msg + " | Item 'Life Stage' doesn't exist.";
			areTheyPresent = false;
		}

		if (action.isElementPresent(By.id("Itemhidecpp"))) {
			if (!action.getValue(By.id("Itemhidecpp"), "style").isEmpty()
					|| !action.isElementPresent(By.id("hidecpp"))) {
				msg = msg
						+ " | Item 'Hide CPP' doesn't display or input element 'Hide CPP' doesn't exist.";
				areTheyPresent = false;
			}
		} else {
			msg = msg + " | Item 'Hide CPP' doesn't exist.";
			areTheyPresent = false;
		}

		return areTheyPresent;
	}

	public String getMsg() {
		return msg;
	}
}
