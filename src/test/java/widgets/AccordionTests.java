package widgets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.TestBase;

import java.time.Duration;

public class AccordionTests extends TestBase {
    @Test
    @DisplayName("Accordion test")
    @Tag("accordion")
    public void accordionTextDisplay(){
        driver.get("http://51.75.61.161:9102/accordion.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-id-2")));
        WebElement accordion1Content = driver.findElement(By.cssSelector("#ui-id-2"));
        String section1Text = accordion1Content.getText();
        System.out.println(section1Text);
        displayContentOfAccordion("#ui-id-3", wait, "#ui-id-4");
        displayContentOfAccordion("#ui-id-5", wait, "#ui-id-6");
        displayContentOfAccordion("#ui-id-7", wait, "#ui-id-8");
    }

    private void displayContentOfAccordion(String cssSelectorForExpandBtn, WebDriverWait wait, String cssSelectorForAccordionContent) {
        WebElement accordionSection = driver.findElement(By.cssSelector(cssSelectorForExpandBtn));
        accordionSection.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelectorForAccordionContent)));
        WebElement accordionContent = driver.findElement(By.cssSelector(cssSelectorForAccordionContent));
        String accordionContentText = accordionContent.getText();
        System.out.println(accordionContentText);
    }
}
