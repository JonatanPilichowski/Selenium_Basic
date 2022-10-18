package other;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.TestBase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class ScrollTests extends TestBase {
    private static final Logger log= LoggerFactory.getLogger(TestBase.class);
    @Test
    public void scrollUntil(){
        driver.get("http://51.75.61.161:9102/high-site.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        while(driver.findElements(By.cssSelector("#scroll-button")).size() == 0){
            Actions action = new Actions(driver);
            action.scrollByAmount(0,driver.manage().window().getSize().getHeight()/2).perform();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("scroll-button")));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            String fileName = new Date().getTime() + "_screenshot.png";
            FileUtils.copyFile(scrFile, new File("src/main/screens/" + fileName));
            log.info("Screenshot saved");
        } catch (IOException e) {
            log.error("Can't save the screenshot");
            throw new RuntimeException(e);
        }
    }
}
