package basicwebelements;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.TestBase;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class AlertsTests extends TestBase {
//    @FindBy(css = "#simple-alert") private WebElement simpleAlertButton;

    @Test
    @DisplayName("Simple Alert Pop Up")
    @Tag("alert")
    public void simpleAlertPopUp(){
        driver.get("http://51.75.61.161:9102/alerts.php");
        WebElement buttonSimpleAlert = driver.findElement(By.cssSelector("button#simple-alert"));
        buttonSimpleAlert.click();
        driver.switchTo().alert().accept();
        WebElement textField = driver.findElement(By.cssSelector("#simple-alert-label"));
        String actualText = textField.getText();
        String expectedText = "OK button pressed";
        assertThat("Simple Alert Pop Up", actualText, equalTo(expectedText));
    }


    @Test
    @DisplayName("Prompt Alert box")
    @Tag("alert")
    public void promptAlertBox(){
        driver.get("http://51.75.61.161:9102/alerts.php");
        WebElement buttonPromptPopUp = driver.findElement(By.cssSelector("button#prompt-alert"));
        buttonPromptPopUp.click();
        driver.switchTo().alert().sendKeys("Lord Vader");
        driver.switchTo().alert().accept();
        WebElement textField = driver.findElement(By.cssSelector("#prompt-label"));
        String actualText = textField.getText();
        String expectedText = "Hello Lord Vader! How are you today?";
        assertThat("Prompt Alert box", actualText, equalTo(expectedText));
    }

    @Test
    @DisplayName("Confirm Alert box")
    @Tag("alert")
    public void confirmAlertBox(){
        driver.get("http://51.75.61.161:9102/alerts.php");
        WebElement buttonConfirm = driver.findElement(By.cssSelector("button#confirm-alert"));
        buttonConfirm.click();
        driver.switchTo().alert().accept();
        WebElement confirmTextField = driver.findElement(By.cssSelector("#confirm-label"));
        String actualConfirmText = confirmTextField.getText();
        String expectedConfirmText = "You pressed OK!";

        assertThat("Confirm Alert box",actualConfirmText, equalTo(expectedConfirmText));
    }

    @Test
    @DisplayName("Cancel Alert box")
    @Tag("alert")
    public void cancelAlertBox(){
        driver.get("http://51.75.61.161:9102/alerts.php");
        WebElement buttonConfirm = driver.findElement(By.cssSelector("button#confirm-alert"));
        buttonConfirm.click();
        driver.switchTo().alert().dismiss();
        WebElement cancelTextField = driver.findElement(By.cssSelector("#confirm-label"));
        String actualCancelText = cancelTextField.getText();
        String expectedCancelText = "You pressed Cancel!";

        assertThat("Cancel Alert box", actualCancelText, equalTo(expectedCancelText));
    }

    @Test
    @DisplayName("Delayed Alert")
    @Tag("alert")
    public void delayedAlert(){
        driver.get("http://51.75.61.161:9102/alerts.php");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement buttonDelayed = driver.findElement(By.cssSelector("#delayed-alert"));
        buttonDelayed.click();
        wait.until(alertIsPresent());
        driver.switchTo().alert().accept();
        WebElement confirmTextField = driver.findElement(By.cssSelector("#delayed-alert-label"));
        String actualConfirmText = confirmTextField.getText();
        assertThat("Delayed Alert", actualConfirmText, equalTo("OK button pressed"));
    }

}
