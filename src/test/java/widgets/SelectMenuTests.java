package widgets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import setup.TestBase;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.bouncycastle.util.Strings.toUpperCase;

public class SelectMenuTests extends TestBase {
    @Test
    @DisplayName("Selectable tests")
    @Tag("selectable")
    public void selectFromDropdown() {
        driver.get("http://51.75.61.161:9102/selectmenu.php");
        selectRandomSpeed();
        selectFileByText("ui.jQuery.js");
        selectNumberByIndex(12);
        selectRandomTitle();
    }

    private void selectRandomTitle() {
        WebElement titleDropdown = driver.findElement(By.id("salutation-button"));
        titleDropdown.click();
        List<WebElement> titleOptions = driver.findElements(By.cssSelector("#salutation-menu li[class='ui-menu-item']"));
        Random rand = new Random();
        int randTitleIndex = rand.nextInt(titleOptions.size());
        titleOptions.get(randTitleIndex).click();
    }

    private void selectRandomSpeed() {
        WebElement speedDropdown = driver.findElement(By.id("speed-button"));
        speedDropdown.click();
        List<WebElement> speedElements = driver.findElements(By.cssSelector("#speed-menu li"));
        Random rand = new Random();
        int randSpeedElIndex = rand.nextInt(speedElements.size());
        speedElements.get(randSpeedElIndex).click();
    }

    private void selectNumberByIndex(int index) {
        WebElement numberDropdown = driver.findElement(By.id("number-button"));
        numberDropdown.click();
        List<WebElement> numberOptions = driver.findElements(By.cssSelector("#number-menu  li"));
        numberOptions.get(index).click();
    }

    private void selectFileByText(String text) {
        WebElement selectFileDropdown = driver.findElement(By.id("files-button"));
        selectFileDropdown.click();
        List<WebElement> fileOptions = driver.findElements(By.cssSelector("#files-menu  li[class='ui-menu-item'"));
        for (WebElement fileOption : fileOptions) {
            if (Objects.equals(toUpperCase(fileOption.getText()), toUpperCase(text))) {
                fileOption.click();
            }
        }
    }
}
