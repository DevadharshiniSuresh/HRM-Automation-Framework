package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForLoaderToDisappear() {
        By loader = By.cssSelector(".oxd-form-loader");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public void click(By locator) {
        waitForLoaderToDisappear();
        waitForVisibility(locator).click();
    }

    public void type(By locator, String text) {
        waitForLoaderToDisappear();
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }
}

