package com.oscarbase;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssignRoleToProviderPage {
	public static AssignRoleToProviderPage providerRolePage=null;
	@FindAll(value = { @FindBy(css = "body>table>tbody>tr") })
	public List<WebElement> allProviders;
	@FindAll(value = { @FindBy(name = "roleNew") })
	public List<WebElement> allSelectRoles;
	@FindAll(value = { @FindBy(xpath = "//input[@type='submit'][@value='Add']") })
	public List<WebElement> allAddButtons;
	@FindAll(value = { @FindBy(xpath = "//input[@type='submit'][@value='Update']") })
	public List<WebElement> allUpdateButtons;
	@FindAll(value = { @FindBy(xpath = "//input[@type='submit'][@value='Delete']") })
	public List<WebElement> allDeleteButtons;

	@FindBy(name = "roleNew")
	public WebElement firstSelectRole;
	@FindBy(xpath = "//input[@type='submit'][@value='Add']")
	public WebElement firstAddButton;
	@FindBy(xpath = "//input[@type='submit'][@value='Update']")
	public WebElement firstUpdateButton;
	@FindBy(xpath = "//input[@type='submit'][@value='Delete']")
	public WebElement firstDeleteButton;

	public static AssignRoleToProviderPage getInstance(WebDriver driver){
		if (providerRolePage == null) {
			providerRolePage = new AssignRoleToProviderPage();
			providerRolePage.initPage(driver);
		}
		return providerRolePage;
	}
	public void initPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getAllProviders(){
		return allProviders;
	}
	public List<WebElement> getAllSelectRoles(){
		return allSelectRoles;
	}
	public List<WebElement> getAllAddButtons(){
		return allAddButtons;
	}
	public List<WebElement> getAllUpdateButtons(){
		return allUpdateButtons;
	}
	public List<WebElement> getAllDeleteButtons(){
		return allDeleteButtons;
	}
	public WebElement getSelectRole(){
		return firstSelectRole;
	}
	public WebElement getAddButton(){
		return firstAddButton;
	}
	public WebElement getUpdateButton(){
		return firstUpdateButton;
	}
	public WebElement getDeleteButton(){
		return firstDeleteButton;
	}
}
