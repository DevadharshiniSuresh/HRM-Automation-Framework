package stepdefinitions;

import org.testng.Assert;

import PageObject.PIMAddPage;
import io.cucumber.java.en.*;

public class UpdateEmployee {
	PIMAddPage page;
	@When("user opens employee details for {string}")
	public void user_opens_employee_details_for(String string) {
	 page = new PIMAddPage(Hooks.driver);
		page.openEmployeeFromResult(string);
	}

	@And("user updates first name to {string}")
	public void user_updates_first_name_to(String string) {
		page.updateFirstName(string);	
		page.clickOnSave();
	}

	@Then("employee first name should be updated to {string}")
	public void employee_first_name_should_be_updated_to(String string) {
		String actualName = page.getFirstName();
		Assert.assertEquals(actualName, string);
	}

}
