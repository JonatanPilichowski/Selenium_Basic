package setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    private static Logger logger= LoggerFactory.getLogger(TestBase.class);
    public WebDriver driver;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        logger.info("Webdriver started successfully");
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeEach
    void setupStart() {
        driver = new ChromeDriver();
        logger.info("Open browser");
        driver.manage().window().maximize();
        logger.info("Window maximized");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        logger.info("Webdriver closed");
    }
}
