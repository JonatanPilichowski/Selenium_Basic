package widgets;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import setup.TestBase;

public class SliderTests extends TestBase {
    @Test
    @DisplayName("Slider moving tests")
    @Tag("slider")
    public void sliderMoving() {
        driver.get("http://51.75.61.161:9102/slider.php");
        SoftAssertions softly = new SoftAssertions();
        moveSliderToPosition(50);
        int sliderValue = getSliderValue();
        softly.assertThat(sliderValue).isEqualTo(50);

        moveSliderToPosition(80);
        sliderValue = getSliderValue();
        softly.assertThat(sliderValue).isEqualTo(80);

        moveSliderToPosition(80);
        sliderValue = getSliderValue();
        softly.assertThat(sliderValue).isEqualTo(80);

        moveSliderToPosition(20);
        sliderValue = getSliderValue();
        softly.assertThat(sliderValue).isEqualTo(20);

        moveSliderToPosition(0);
        sliderValue = getSliderValue();
        softly.assertThat(sliderValue).isEqualTo(0);
        softly.assertAll();
    }

    private void moveSliderToPosition(int positionToMove) {
        WebElement slider = getSlider();
        int sliderValue = getSliderValue();
        if ((positionToMove - sliderValue) >= 0) {
            for (int i = 0; i < (positionToMove - sliderValue); i++) slider.sendKeys(Keys.ARROW_RIGHT);
        } else {
            for (int i = 0; i < (sliderValue - positionToMove); i++) slider.sendKeys(Keys.ARROW_LEFT);
        }
    }

    private WebElement getSlider() {
        return driver.findElement(By.id("custom-handle"));
    }

    private int getSliderValue() {
        return Integer.parseInt(getSlider().getText());
    }
}
