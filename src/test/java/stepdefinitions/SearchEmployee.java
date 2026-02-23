package stepdefinitions;

import org.testng.Assert;

import PageObject.PIMAddPage;

import io.cucumber.java.en.*;

public class SearchEmployee {
	
	PIMAddPage page;
	
	@When("user searches employee by name {string}")
	public void user_searches_employee_by_name(String string) {
	 page=new PIMAddPage(Hooks.driver);
	 page.SearchEmpname(string);
	}

	@Then("employee {string} should be displayed in results")
	public void employee_should_be_displayed_in_results(String empName) {
		  Assert.assertTrue(page.isEmployeeDisplayed(empName));
	}

}
