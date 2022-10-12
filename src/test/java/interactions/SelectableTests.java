package interactions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import setup.TestBase;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SelectableTests extends TestBase {
    @Test
    @DisplayName("Select elements")
    public void selectElements(){
        driver.get("http://51.75.61.161:9102/selectable.php");
        List<WebElement> elements = driver.findElements(By.cssSelector("#selectable li"));
        Actions elementsActions = new Actions(driver);
        elementsActions.keyDown(Keys.CONTROL);
        elementsActions.moveToElement(elements.get(0)).click();
        elementsActions.moveToElement(elements.get(2)).click();
        elementsActions.moveToElement(elements.get(3)).click().keyUp(Keys.CONTROL).build().perform();
        WebElement feedback = driver.findElement(By.id("feedback"));
        assertThat("The text is displayed properly", feedback.getText(), equalTo("You've selected: #1 #3 #4."));
    }
}
