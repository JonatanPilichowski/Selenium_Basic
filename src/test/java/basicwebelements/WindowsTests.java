package basicwebelements;

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

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class WindowsTests extends TestBase {

    @Test
    @DisplayName("Windows/Tabs test")
    @Tag("window")
    @Tag("tab")
    public void windowsTest() {
        driver.get("http://51.75.61.161:9102/windows-tabs.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String parentWindow = driver.getWindowHandle();
        WebElement newBrowserWinBtn = driver.findElement(By.xpath("//button[@id='newBrowserWindow']"));
        newBrowserWinBtn.click();
        moveToNewWindow(wait, parentWindow);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody tr")));
        displayRowsFor("Switzerland", 4000);

        moveBackTo(parentWindow);
        WebElement newMessageWinBtn = driver.findElement(By.cssSelector("#newMessageWindow"));
        newMessageWinBtn.click();
        moveToNewWindow(wait, parentWindow);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
        String windowText = driver.findElement(By.cssSelector("body")).getText();
        System.out.println(windowText);

        moveBackTo(parentWindow);
        WebElement newBrowserTab = driver.findElement(By.xpath("//button[@id='newBrowserTab']"));
        newBrowserTab.click();
        moveToNewWindow(wait, parentWindow);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody tr")));
        displayRowsFor("Switzerland", 4000);
        moveBackTo(parentWindow);
    }

    private void moveToNewWindow(WebDriverWait waitValue, String parentWindow) {
        waitValue.until(numberOfWindowsToBe(2));
        for (String winHandle : driver.getWindowHandles()) {
            if (!parentWindow.contentEquals(winHandle)) {
                driver.switchTo().window(winHandle);
                break;
            }
        }
    }

    private void moveBackTo(String parentWindowValue) {
        driver.close();
        driver.switchTo().window(parentWindowValue);
        assert driver.getWindowHandles().size() == 1;
    }

    private void displayRowsFor(String stateCondition, int minHeight) {
        List<WebElement> allRows = driver.findElements(By.cssSelector("tbody tr"));

        for (WebElement row : allRows) {
            String rank = row.findElement(By.xpath("./th")).getText();
            String peak = row.findElement(By.xpath("./td[1]")).getText();
            String mountainRange = row.findElement(By.xpath("./td[2]")).getText();
            String state = row.findElement(By.xpath("./td[3]")).getText();
            String height = row.findElement(By.xpath("./td[4]")).getText();
            if (state.contains(stateCondition) && Integer.parseInt(height) > minHeight) {
                System.out.println("For rank: " + rank + " of peak: " + peak + " in Mountain range: " + mountainRange);
            }
        }
    }

}
