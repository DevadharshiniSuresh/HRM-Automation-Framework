package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver) {

        this.driver = driver;

        // VERY IMPORTANT LINE
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

	@FindBy(xpath="//input[@name='username']")
	public WebElement username;
	@FindBy(xpath="//input[@name='password']")
	public WebElement password;
	@FindBy(xpath="//button[@type='submit']")
	public WebElement submit;
	@FindBy(xpath = "//h6[normalize-space()='Dashboard']")
	public WebElement dashboard;
	@FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
    public WebElement errorMessage;
	
	public void enterUsername(String name) {
		wait.until(ExpectedConditions.visibilityOf(username));
		username.sendKeys(name);
	}

	public void enterPassword(String pwd) {
		wait.until(ExpectedConditions.visibilityOf(password));
		password.sendKeys(pwd);
	}
	
	public void clickonSubmit() {
		submit.click();
	}

	public boolean isDashboardDisplayed() {

        wait.until(ExpectedConditions.visibilityOf(dashboard));
        return dashboard.isDisplayed();
    }
	
	public String getErrorMessage() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    By errorMsg = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");

	    WebElement message = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(errorMsg)
	    );

	    return message.getText();
	}

}

