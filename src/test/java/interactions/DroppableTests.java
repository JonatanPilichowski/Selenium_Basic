package interactions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.TestBase;
import widgets.DatepickerTests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DroppableTests extends TestBase {
    @Test
    @DisplayName("Drag block and drop it in correct place")
    @Tag("drag")
    @Tag("drop")
    public void dragAndDrop(){
        driver.get("http://51.75.61.161:9102/droppable.php");
        WebElement draggableBlock = driver.findElement(By.id("draggable"));
        WebElement droppableBlock = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggableBlock,droppableBlock).perform();
        assertThat("Correct text displayed after action", droppableBlock.getText(), equalTo("Dropped!"));
    }
}
