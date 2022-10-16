package interactions;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.TestBase;
import widgets.DatepickerTests;

public class DraggableTests extends TestBase {
    private static final Logger log = LoggerFactory.getLogger(DatepickerTests.class);

    @Test
    @DisplayName("Draq square to different positions")
    @Tag("drag")
    public void draggable() {
        driver.get("http://51.75.61.161:9102/draggable.php");
        SoftAssertions softly = new SoftAssertions();
        Actions action = new Actions(driver);
        WebElement draggableBlock = driver.findElement(By.id("draggable"));
        Dimension screenSize = getScreenSize();

        Point rightTopCornerPosition = new Point((screenSize.getWidth() - getBlockSize().getWidth()), (0));
        action.dragAndDropBy(draggableBlock, (getScreenSize().getWidth() - getBlockLocation().getX() - getBlockSize().getWidth()),
                (-getBlockLocation().getY())).perform();
        softly.assertThat(getBlockLocation()).isEqualTo(rightTopCornerPosition);
        log.info("Moved draggable block to right top corner");

        Point rightBottomCornerPosition = new Point(screenSize.getWidth() - getBlockSize().getWidth(),
                screenSize.getHeight() - getBlockSize().getHeight());
        action.dragAndDropBy(draggableBlock, (getScreenSize().getWidth() - getBlockLocation().getX() - getBlockSize().getWidth()),
                (getScreenSize().getHeight() - getBlockLocation().getY() - getBlockSize().getWidth())).perform();
        softly.assertThat(getBlockLocation()).isEqualTo(rightBottomCornerPosition);
        log.info("Moved draggable block to right bottom corner");

        Point centeredPosition = new Point(screenSize.getWidth() / 2, screenSize.getHeight() / 2);
        action.dragAndDropBy(draggableBlock, (getScreenSize().getWidth() / 2 - getBlockLocation().getX()),
                (getScreenSize().getHeight() / 2 - getBlockLocation().getY())).perform();
        softly.assertThat(getBlockLocation()).isEqualTo(centeredPosition);
        log.info("Moved draggable block to center");

        Point leftBottomCornerPosition = new Point(0, screenSize.getHeight() - getBlockSize().getHeight());
        action.dragAndDropBy(draggableBlock, (-getBlockLocation().getX()),
                (getScreenSize().getHeight() - getBlockLocation().getY() - getBlockSize().getWidth())).perform();
        softly.assertThat(getBlockLocation()).isEqualTo(leftBottomCornerPosition);
        log.info("Moved draggable block to left bottom corner");
        softly.assertAll();
    }

    private Dimension getScreenSize() {
        WebElement screen = driver.findElement(By.cssSelector("html"));
        return screen.getSize();
    }

    private Dimension getBlockSize() {
        WebElement draggableBlock = driver.findElement(By.id("draggable"));
        return draggableBlock.getSize();
    }

    public Point getBlockLocation() {
        WebElement draggableBlock = driver.findElement(By.id("draggable"));
        return draggableBlock.getLocation();
    }
}
