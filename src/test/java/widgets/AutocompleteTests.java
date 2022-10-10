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
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class AutocompleteTests extends TestBase {
    @Test
    @DisplayName("Autocomplete dropdown")
    @Tag("autocomplete")
    public void autocomplete(){
        driver.get("http://51.75.61.161:9102/autocomplete.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement searchField = driver.findElement(By.cssSelector("#search"));
        searchField.sendKeys("a");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='ui-id-1']")));
        List<WebElement> dropdownResults = driver.findElements(By.cssSelector("li[class='ui-menu-item']"));
        for(WebElement row : dropdownResults){
            System.out.println(row.getText());
        }
        Random random = new Random();
        int randomResultIndex = random.nextInt(dropdownResults.size());
        WebElement randomResult =  dropdownResults.get(randomResultIndex);
        String randomResultText = randomResult.getText();
        randomResult.click();
        String searchFieldText = searchField.getText();

        assertThat("Displayed value equals chosen value", randomResultText, equalTo(searchField.getAttribute("value")));
    }

}
