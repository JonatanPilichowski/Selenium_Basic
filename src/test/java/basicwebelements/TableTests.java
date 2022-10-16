package basicwebelements;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import setup.TestBase;

import java.util.List;

public class TableTests extends TestBase {


    @Test
    @DisplayName("Table test")
    @Tag("table")
    public void tableRows(){
        driver.get("http://51.75.61.161:9102/table.php");

        List<WebElement> allRows = driver.findElements(By.cssSelector("tbody tr"));

        for (WebElement row : allRows) {
            String rank = row.findElement(By.xpath("./th")).getText();
            String peak = row.findElement(By.xpath("./td[1]")).getText();
            String mountainRange = row.findElement(By.xpath("./td[2]")).getText();
            String state = row.findElement(By.xpath("./td[3]")).getText();
            String height = row.findElement(By.xpath("./td[4]")).getText();
            if (state.contains("Switzerland") && Integer.parseInt(height) > 4000) {
                System.out.println("For rank: " + rank + " of peak: " + peak + " in Mountain range: " + mountainRange);
            }
        }
    }
}
