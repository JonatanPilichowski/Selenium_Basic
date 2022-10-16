package interactions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import setup.TestBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SortableTests extends TestBase {
    @Test
    public void sortElements() {
        driver.get("http://51.75.61.161:9102/sortable.php");
        List<WebElement> elementsShuffled = driver.findElements(By.cssSelector("#sortable li"));

        Collections.shuffle(elementsShuffled, new Random());
        List<String> elementsExpectedTexts = new ArrayList<>();
        for (WebElement element : elementsShuffled) {
            elementsExpectedTexts.add(element.getText());
        }

        Actions actions = new Actions(driver);
        for (int i = 0; i < elementsShuffled.size(); i++) {
            WebElement elementByOrder = driver.findElement(By.xpath("//*[@id='sortable']//li[" + (i + 1) + "]"));
            actions.dragAndDrop(elementsShuffled.get(i), elementByOrder).perform();
        }

        List<WebElement> elementsAfterSwap = driver.findElements(By.cssSelector("#sortable li"));
        List<String> elementsActualTexts = new ArrayList<>();
        for (WebElement element : elementsAfterSwap) {
            elementsActualTexts.add(element.getText());
        }

        assertThat("Check that order of elements is correct", elementsExpectedTexts, equalTo(elementsActualTexts));
    }
}
