package widgets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.TestBase;

import javax.naming.Name;
import java.time.Duration;

public class ModelDialogTests extends TestBase {
    @Test
    @DisplayName("Dialog users test")
    @Tag("dialog")
    public void usersDialog(){
        driver.get("http://51.75.61.161:9102/modal-dialog.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement createNewUsrBtn = driver.findElement(By.cssSelector("button[id='create-user']"));
        createNewUsrBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#name")));
        WebElement NameField = driver.findElement(By.cssSelector("input#name"));
        NameField.clear();
        NameField.sendKeys("Janusz Tracz");
        WebElement emailField = driver.findElement(By.cssSelector("input#email"));
        emailField.clear();
        emailField.sendKeys("jtracz@gmail.com");
        WebElement pswrdField = driver.findElement(By.cssSelector("input#password"));
        Actions action = new Actions(driver);
        action.scrollToElement(pswrdField).perform();
        pswrdField.clear();
        pswrdField.sendKeys("password");
        WebElement createAccntBtn = driver.findElement(By.xpath("//button[@class='ui-button ui-corner-all ui-widget'][.='Create an account']"));
        WebElement window = driver.findElement(By.cssSelector("div#dialog-form"));
        createAccntBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(window));
        //take last tr from table users
        //take its values and compare with values inserted

        //repeat for input values from DataSource

    }
}
