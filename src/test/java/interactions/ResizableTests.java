package interactions;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import setup.TestBase;

public class ResizableTests extends TestBase {
    @Test
    @DisplayName("Resize widow few times")
    @Tag("resize")
    public void resizeWindow() {
        driver.get("http://51.75.61.161:9102/resizable.php");
        WebElement resizeBtn = driver.findElement(By.cssSelector("div[class*='ui-icon-gripsmall-diagonal-se']"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(resizeBtn, 10, 0).perform();
        actions.dragAndDropBy(resizeBtn, 0, 10).perform();
        actions.dragAndDropBy(resizeBtn, 10, 10).perform();
    }
}
