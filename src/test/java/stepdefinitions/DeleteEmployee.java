package stepdefinitions;

import java.util.concurrent.TimeoutException;

import org.testng.Assert;

import PageObject.PIMAddPage;
import io.cucumber.java.en.*;

public class DeleteEmployee {
	PIMAddPage pimPage;
	@When("user deletes employee {string}")
	public void user_deletes_employee(String name) {
		pimPage=new PIMAddPage(Hooks.driver);
	    pimPage.deleteEmployee(name);
	}

	@Then("employee {string} should be deleted")
	public void employee_should_be_deleted(String name) throws TimeoutException {
	    Assert.assertTrue(pimPage.isEmployeeDeleted(name));
	}

}
