package widgets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.TestBase;

import java.time.Duration;

public class ProgressbarTests extends TestBase {
    @Test
    @DisplayName("progressbar loading test")
    @Tag("progressbar")
    public void progressbarloading(){
        driver.get("http://51.75.61.161:9102/progressbar.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[.='Complete!']")));
    }

}
