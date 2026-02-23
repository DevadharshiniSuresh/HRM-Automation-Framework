package stepdefinitions;

import org.testng.Assert;

import PageObject.LoginPage;
import io.cucumber.java.en.*;

public class Login {

	LoginPage loginpage;

	@Given("user launches browser")
	public void user_launches_browser() {
		loginpage = new LoginPage(Hooks.driver);
	}

	@When("user enters username {string}")
	public void user_enters_username(String string) throws InterruptedException {
		loginpage.enterUsername(string);
		Thread.sleep(2000);
	}

	@And("user enters password {string}")
	public void user_enters_password(String string) throws InterruptedException {
		loginpage.enterPassword(string);
		Thread.sleep(2000);
	}

	@And("user clicks login button")
	public void user_clicks_login_button() {
		loginpage.clickonSubmit();
	}

	@Then("user should see dashboard")
	public void user_should_see_dashboard() {
		Assert.assertTrue(loginpage.isDashboardDisplayed());
	    }

	@Then("user should see error message {string}")
	public void user_should_see_error_message(String expectedMessage) {

	    String actualMessage = loginpage.getErrorMessage();

	    Assert.assertEquals(actualMessage, expectedMessage);
	}


}
