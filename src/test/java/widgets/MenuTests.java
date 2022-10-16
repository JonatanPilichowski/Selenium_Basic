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

import java.time.Duration;

public class MenuTests extends TestBase {
    @Test
    @DisplayName("Menu hover click")
    @Tag("menu")
    public void menuModernJazz() {
        driver.get("http://51.75.61.161:9102/menu-item.php#");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement musicMenuItem = driver.findElement(By.id("ui-id-9"));
        Actions action = new Actions(driver);
        action.moveToElement(musicMenuItem).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-13")));
        WebElement jazzMenuItemChildOfMusic = driver.findElement(By.id("ui-id-13"));
        action.moveToElement(jazzMenuItemChildOfMusic).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-16")));
        WebElement modernMenuItemChildOfJazz = driver.findElement(By.id("ui-id-16"));
        action.moveToElement(modernMenuItemChildOfJazz).perform();
        modernMenuItemChildOfJazz.click();
    }
}
