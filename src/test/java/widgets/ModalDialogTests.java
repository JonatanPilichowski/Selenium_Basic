package widgets;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.TestBase;

import java.time.Duration;
import java.util.List;

public class ModalDialogTests extends TestBase {
    @Test
    @DisplayName("Dialog users test")
    @Tag("dialog")
    public void addNewUserToDialog() {
        driver.get("http://51.75.61.161:9102/modal-dialog.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement createNewUsrBtn = driver.findElement(By.cssSelector("button[id='create-user']"));
        createNewUsrBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#name")));
        WebElement nameField = driver.findElement(By.cssSelector("input#name"));
        nameField.clear();
        String nameToFill = "Janusz Tracz";
        nameField.sendKeys(nameToFill);
        WebElement emailField = driver.findElement(By.cssSelector("input#email"));
        emailField.clear();
        String emailToFill = "jtracz@gmail.com";
        emailField.sendKeys(emailToFill);
        WebElement pswrdField = driver.findElement(By.cssSelector("input#password"));
        Actions action = new Actions(driver);
        action.scrollToElement(pswrdField).perform();
        pswrdField.clear();
        String passToFill = "password";
        pswrdField.sendKeys(passToFill);
        WebElement createAccntBtn = driver.findElement(By.xpath("//button[@class='ui-button ui-corner-all ui-widget'][.='Create an account']"));
        WebElement window = driver.findElement(By.cssSelector("div#dialog-form"));
        createAccntBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(window));
        List<WebElement> createdUserRow = driver.findElements(By.xpath("(//table//tbody/tr)[last()]/td"));
        WebElement createdUserName = createdUserRow.get(0);
        WebElement createdUserEmail = createdUserRow.get(1);
        WebElement createdUserPass = createdUserRow.get(2);
        String actualUserName = createdUserName.getText();
        String actualUserEmail = createdUserEmail.getText();
        String actualUserPass = createdUserPass.getText();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualUserName).isEqualTo(nameToFill);
        softly.assertThat(actualUserEmail).isEqualTo(emailToFill);
        softly.assertThat(actualUserPass).isEqualTo(passToFill);
        softly.assertAll();
    }

    @ParameterizedTest
    @DisplayName("Add user from data provider")
    @Tag("dialog")
    @MethodSource("setup.ModalDialogDataProvider#modalUsersData")
    public void addNewUserWithDataProvider(String nameOfUserProvided, String emailOfUserProvided, String passwordOfUserProvided){
        driver.get("http://51.75.61.161:9102/modal-dialog.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement createNewUsrBtn = driver.findElement(By.cssSelector("button[id='create-user']"));
        createNewUsrBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#name")));
        WebElement nameField = driver.findElement(By.cssSelector("input#name"));
        nameField.clear();
        nameField.sendKeys(nameOfUserProvided);
        WebElement emailField = driver.findElement(By.cssSelector("input#email"));
        emailField.clear();
        emailField.sendKeys(emailOfUserProvided);
        WebElement pswrdField = driver.findElement(By.cssSelector("input#password"));
        Actions action = new Actions(driver);
        action.scrollToElement(pswrdField).perform();
        pswrdField.clear();
        pswrdField.sendKeys(passwordOfUserProvided);
        WebElement createAccntBtn = driver.findElement(By.xpath("//button[@class='ui-button ui-corner-all ui-widget'][.='Create an account']"));
        WebElement window = driver.findElement(By.cssSelector("div#dialog-form"));
        createAccntBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(window));
        List<WebElement> createdUserRow = driver.findElements(By.xpath("(//table//tbody/tr)[last()]/td"));
        WebElement createdUserName = createdUserRow.get(0);
        WebElement createdUserEmail = createdUserRow.get(1);
        WebElement createdUserPass = createdUserRow.get(2);
        String actualUserName = createdUserName.getText();
        String actualUserEmail = createdUserEmail.getText();
        String actualUserPass = createdUserPass.getText();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualUserName).isEqualTo(nameOfUserProvided);
        softly.assertThat(actualUserEmail).isEqualTo(emailOfUserProvided);
        softly.assertThat(actualUserPass).isEqualTo(passwordOfUserProvided);
    }
}
