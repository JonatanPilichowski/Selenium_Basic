package widgets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import setup.TestBase;

import java.util.List;

public class AccordionTests extends TestBase {
    @Test
    @DisplayName("Accordion test")
    @Tag("accordion")
    public void accordionTextDisplay() {
        driver.get("http://51.75.61.161:9102/accordion.php");
        List<WebElement> accordions = driver.findElements(By.className("ui-accordion-header"));
        List<WebElement> accContents = driver.findElements(By.className("ui-accordion-content"));
        for (int i = 0; i < accordions.size(); i++) {
            if (!accContents.get(i).isDisplayed()) {
                accordions.get(i).click();
            }
            String accordionContentText = accContents.get(i).getText();
            System.out.println(accordionContentText);
        }
    }
}
