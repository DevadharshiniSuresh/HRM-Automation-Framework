package PageObject;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

//import io.cucumber.java.en.*;

public class PIMAddPage extends BasePage {
	WebDriver driver;
	WebDriverWait wait;

	public PIMAddPage(WebDriver driver) {
	    super(driver);
	}


	@FindBy(xpath="//a[contains(@href,'pim')]")
	public WebElement PIM;
	@FindBy(xpath="//a[contains(text(),'Add Employee')]")
	public WebElement addEmployee;
	@FindBy(name = "firstName")
	public WebElement save;
	@FindBy(xpath="//input[@name='lastName']")
	public WebElement lastName;
	@FindBy(xpath = "//h6[text()='Personal Details']")
	WebElement personalDetailsHeader;

	//search page
	@FindBy(xpath = "//a[normalize-space()='Employee List']")
	WebElement employeeListTab;
	@FindBy(xpath="//input[@placeholder='Type for hints...']")
	public WebElement EmpName;
	@FindBy(xpath="//button[normalize-space()='Search']")
	public WebElement searchButton;

	//delete page
	@FindBy(xpath = "//button[contains(@class,'oxd-button--label-danger')]")
	WebElement confirmDeleteButton;


	public void clickOnPIM() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By loader = By.cssSelector(".oxd-form-loader");
		By pimMenu = By.xpath("//span[text()='PIM']/ancestor::a");

		// Wait for dashboard loader to disappear
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

		// Wait for PIM menu to be present
		WebElement pim = wait.until(
				ExpectedConditions.visibilityOfElementLocated(pimMenu)
				);

		// Scroll slightly
		((JavascriptExecutor) driver)
		.executeScript("arguments[0].scrollIntoView({block:'center'});", pim);

		wait.until(ExpectedConditions.elementToBeClickable(pim));

		pim.click();
	}


	public void clickAddEmployee() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By loader = By.cssSelector(".oxd-form-loader");
		By addEmployeeBtn = By.xpath("//a[normalize-space()='Add Employee']");

		// Wait for PIM page fully loaded
		wait.until(ExpectedConditions.visibilityOf(PIM));

		// Wait for loader gone
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));


		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addEmployeeBtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

		btn.click();
	}



	public void enterFirstName(String fname) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		By loader = By.cssSelector(".oxd-form-loader");
		By firstNameField = By.name("firstName");

		// Wait for loader to disappear
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

		// Wait for field to appear fresh
		WebElement field = wait.until(
				ExpectedConditions.visibilityOfElementLocated(firstNameField)
				);

		field.clear();
		field.sendKeys(fname);
	}


	public void enterLastName(String name) {
		wait.until(ExpectedConditions.visibilityOf(lastName));
		lastName.sendKeys(name);
	}

	public void clickOnSave() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By loader = By.cssSelector(".oxd-form-loader");
		By saveBtn = By.xpath("//button[@type='submit']");

		// Wait until loader disappears safely
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

		WebElement button = wait.until(
				ExpectedConditions.elementToBeClickable(saveBtn)
				);

		((JavascriptExecutor) driver)
		.executeScript("arguments[0].scrollIntoView(true);", button);

		try {
			button.click();
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver)
			.executeScript("arguments[0].click();", button);
		}

		// Wait again after clicking Save
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
	}

	public boolean isPersonalDetailsDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By loader = By.cssSelector(".oxd-form-loader");
		By header = By.xpath("//h6[normalize-space()='Personal Details']");

		// Wait for loader after Save
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

		// Wait for header to appear freshly (NOT proxy)
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(header)
				);

		return element.isDisplayed();
	}


	//search page
	public void SearchEmpname(String name) {

		By loader = By.cssSelector(".oxd-form-loader");

		wait.until(ExpectedConditions.elementToBeClickable(employeeListTab));
		employeeListTab.click();

		wait.until(ExpectedConditions.visibilityOf(EmpName));
		EmpName.clear();
		EmpName.sendKeys(name);
		searchButton.click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
	}


	public boolean isEmployeeDisplayed(String empName) {
		String firstName = empName.split(" ")[0];
		By employeeName = By.xpath(
				"//div[@class='oxd-table-body']//*[normalize-space()='" + firstName + "']"
				);
		wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName));
		return driver.findElement(employeeName).isDisplayed();	
	}

	//update employee

	public void openEmployeeFromResult(String fullName) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By loader = By.cssSelector(".oxd-form-loader");

		String firstName = fullName.split(" ")[0];

		By employeeRow = By.xpath(
				"//div[@class='oxd-table-body']//div[@role='row' and .//div[normalize-space()='" 
						+ firstName + "']]"
				);


		// Wait for loader gone
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

		// Wait for row visible
		WebElement row = wait.until(
				ExpectedConditions.visibilityOfElementLocated(employeeRow)
				);

		row.click();

		// Wait for personal page
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
	}

	public void updateFirstName(String newName) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		By loader = By.cssSelector(".oxd-form-loader");
		By firstNameField = By.name("firstName");

		// Ensure loader fully gone
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

		WebElement field = wait.until(
				ExpectedConditions.elementToBeClickable(firstNameField)
				);

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});", field
				);

		// Small stabilization pause (OrangeHRM needs it)
		try { Thread.sleep(500); } catch (InterruptedException e) {}

		field.sendKeys(Keys.CONTROL + "a");
		field.sendKeys(Keys.DELETE);
		field.sendKeys(newName);
	}



	public String getFirstName() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		By loader = By.cssSelector(".oxd-form-loader");

		// Wait until loader disappears after Save
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

		// Wait until first name field is visible again
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));

		return driver.findElement(By.name("firstName"))
				.getAttribute("value");
	}


	public boolean isFirstNameUpdated(String expectedName) {
		WebElement firstNameField = driver.findElement(By.name("firstName"));
		String actualName = firstNameField.getAttribute("value");
		return actualName.equals(expectedName);
	}



	//delete

	public void deleteEmployee(String empName) {

		String firstName = empName.split(" ")[0];

		By deleteIcon = By.xpath(
				"//div[@role='row' and .//div[normalize-space()='" 
						+ firstName + "']]//i[contains(@class,'bi-trash')]"
				);


		wait.until(ExpectedConditions.elementToBeClickable(deleteIcon));
		driver.findElement(deleteIcon).click();

		wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
		confirmDeleteButton.click();

		// Wait for success toast
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class,'oxd-toast')]")
				));
	}

	public boolean isEmployeeDeleted(String empName) throws TimeoutException {

		String firstName = empName.split(" ")[0];

		By employeeName = By.xpath(
				"//div[@class='oxd-table-body']//div[normalize-space()='" 
						+ firstName + "']"
				);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(employeeName));
		return true;
	}	
}
