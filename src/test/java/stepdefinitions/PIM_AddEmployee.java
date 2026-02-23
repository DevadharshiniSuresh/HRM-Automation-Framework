package stepdefinitions;

import org.testng.Assert;

import PageObject.LoginPage;
import PageObject.PIMAddPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PIM_AddEmployee {
	PIMAddPage page;

	@When("user navigates to PIM module")
	public void user_navigates_to_pim_module() throws InterruptedException {
		page = new PIMAddPage(Hooks.driver);
		page.clickOnPIM();
		Thread.sleep(2000);
	}

	@And("user clicks on Add Employee")
	public void user_clicks_on_add_employee() throws InterruptedException {
		page.clickAddEmployee();
		Thread.sleep(2000);
	}

	@And("user enters first name {string}")
	public void user_enters_first_name(String string) {
		page.enterFirstName(string);	}

	@And("user enters last name {string}")
	public void user_enters_last_name(String string) {
		page.enterLastName(string);	
	}

	@And("user clicks Save button")
	public void user_clicks_save_button() {
		page.clickOnSave();
	}

	@Then("employee should be added successfully")
	public void employee_should_be_added_successfully() {
		 Assert.assertTrue(page.isPersonalDetailsDisplayed());
	}
}
