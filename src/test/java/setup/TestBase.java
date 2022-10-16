package setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.HashMap;

public class TestBase {
    private static final Logger logger= LoggerFactory.getLogger(TestBase.class);
    public WebDriver driver;
    private static final String downloadDir = Paths.get("src/main/downloads").toAbsolutePath().toString();

    public static String getDownloadDir(){
        return downloadDir;
    }
    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        logger.info("Webdriver started successfully");

    }

    @BeforeEach
    void setupStart() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadDir);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("start-maximized");
        logger.info("Window maximized");
        driver = new ChromeDriver(options);
        logger.info("Open browser");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        logger.info("Webdriver closed");
    }

}
