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
    private final int decreasedValueOfClick = 18;

    @Test
    @DisplayName("Resize widow few times")
    @Tag("resize")
    public void resizeWindow() {

        final int resizeByPixels = 10;
        driver.get("http://51.75.61.161:9102/resizable.php");
        WebElement resizableField = driver.findElement(By.id("resizable"));
        Dimension resFieldSizeOriginal = resizableField.getSize();
        WebElement resizeBtn = driver.findElement(By.cssSelector("div[class*='ui-icon-gripsmall-diagonal-se']"));
        SoftAssertions softly = new SoftAssertions();
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(resizeBtn, resizeByPixels+decreasedValueOfClick, 0).perform();
        softly.assertThat(getResFieldSize().getWidth()).isEqualTo(getExpectedWidth(resizeByPixels, resFieldSizeOriginal));
        actions.dragAndDropBy(resizeBtn, 0, resizeByPixels+decreasedValueOfClick).perform();
        softly.assertThat(getResFieldSize().getHeight()).isEqualTo(getExpectedHeight(resizeByPixels, resFieldSizeOriginal));
        Dimension  resFieldSizeCurr = resizableField.getSize();
        actions.dragAndDropBy(resizeBtn, resizeByPixels+decreasedValueOfClick, resizeByPixels+decreasedValueOfClick).perform();
        softly.assertThat(getResFieldSize()).isEqualTo(new Dimension(getExpectedWidth(resizeByPixels, resFieldSizeCurr), getExpectedHeight(resizeByPixels, resFieldSizeCurr)));
        softly.assertAll();
    }

    private int getExpectedHeight(int resizeByPixels, Dimension resFieldSizeOriginal) {
        return resFieldSizeOriginal.getHeight() + resizeByPixels;
    }

    private int getExpectedWidth(int resizeByPixels, Dimension resFieldSizeOriginal) {
        return resFieldSizeOriginal.getWidth() + resizeByPixels;
    }

    private Dimension getResFieldSize() {
        WebElement resizableField = driver.findElement(By.id("resizable"));
        return resizableField.getSize();
    }
}
