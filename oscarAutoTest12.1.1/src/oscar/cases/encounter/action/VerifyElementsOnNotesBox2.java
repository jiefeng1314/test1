package encounter.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.oscarbase.BaseUtil;
import com.oscarbase.WebDriverActions;

public class VerifyElementsOnNotesBox2 {

	private String msg = "";
	public WebDriver driver;

	public VerifyElementsOnNotesBox2(WebDriver driver) {
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

	

		if (!action.isElementPresent(By.id("position"))) {
			msg = msg + " | 'Insert Position' button doesn't exist.";
			areTheyPresent = false;
		}

	
		
		if (action.isElementPresent(By.id("Itemproblemdescription"))) {
			if (!action.getValue(By.id("Itemproblemdescription"), "style").contains("display: none")
					|| !action.isElementPresent(By.id("problemdescription"))) {
				msg = msg
						+ " | Item 'problemdescription' doesn't dislay or input element 'problemdescription' doesn't exist.";
				areTheyPresent = false;
			}
		} else {
			msg = msg + " | Item 'Treatment' doesn't exist.";
			areTheyPresent = false;
		}
	
		if (action.isElementPresent(By.id("Itemproblemstatus"))) {
			if (!action.getValue(By.id("Itemproblemstatus"), "style").contains("display: none")
					|| !action.isElementPresent(By.id("problemstatus"))) {
				msg = msg
						+ " | Item 'problemstatus' doesn't dislay or input element 'problemstatus' doesn't exist.";
				areTheyPresent = false;
			}
		} else {
			msg = msg + " | Item 'Treatment' doesn't exist.";
			areTheyPresent = false;
		}

		if (action.isElementPresent(By.id("Itemlifestage"))) {
			if (!action.getValue(By.id("Itemlifestage"), "style").isEmpty()
					|| !action.isElementPresent(By.id("lifestage"))) {
				msg = msg
						+ " | Item 'Life Stage' doesn't dislay or input element 'Life Stage' doesn't exist.";
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
						+ " | Item 'Hide CPP' doesn't dislay or input element 'Hide CPP' doesn't exist.";
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
