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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TooltipTests extends TestBase {
    @Test
    @DisplayName("Tooltip text display check")
    @Tag("tooltip")
    public void tooltipText(){
        driver.get("http://51.75.61.161:9102/tooltip.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement ageField = driver.findElement(By.id("age"));
        ageField.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class=ui-tooltip-content]")));
        WebElement ageTooltip = driver.findElement(By.cssSelector("div[class=ui-tooltip-content]"));
        String ageTooltipText = ageTooltip.getText();
        System.out.println(ageTooltipText);
        assertThat("Age tooltip text comparison", ageTooltipText, equalTo("We ask for your age only for statistical purposes."));
    }
}
