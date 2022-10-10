package widgets;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.TestBase;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class DatepickerTests extends TestBase {
    private static Logger logger = LoggerFactory.getLogger(DatepickerTests.class);

    @Test
    @DisplayName("Date picker test")
    @Tag("date-picker")
    public void selectDate() {
        driver.get("http://51.75.61.161:9102/datepicker.php");
        SoftAssertions softly = new SoftAssertions();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement datepicker = driver.findElement(By.id("datepicker"));
        clickOnBtnWithWait(wait, datepicker);
        WebElement currentDayParent = driver.findElement(By.cssSelector("tr td[class*='ui-datepicker-today']"));
        WebElement currentDay = currentDayParent.findElement(By.cssSelector("a[class*='highlight']"));
        String expectedDate = getDateFrom(currentDayParent, currentDay);
        currentDayParent.click();
        String actualDate = datepicker.getAttribute("value");
        softly.assertThat(actualDate).isEqualTo(expectedDate);
        logger.info("Selected today's date.");

        clickOnBtnWithWait(wait, datepicker);
        WebElement nextMonth = driver.findElement(By.xpath("//*[contains(@class, 'ui-datepicker-next ui-corner-all')]//span"));
        nextMonth.click();
        WebElement firstDayOfMonth = driver.findElement(By.xpath("//*[@data-handler='selectDay' and not(@class=' ui-datepicker-other-month ')]/a[.='1']"));
        WebElement firstDayOfMonthParent = driver.findElement(By.xpath("//*[@data-handler='selectDay' and not(@class=' ui-datepicker-other-month ')]/a[.='1']/parent::td"));
        expectedDate = getDateFrom(firstDayOfMonthParent, firstDayOfMonth);
        firstDayOfMonth.click();
        actualDate = datepicker.getAttribute("value");
        softly.assertThat(actualDate).isEqualTo(expectedDate);
        logger.info("Moved to next month and clicked first day of it.");

        clickOnBtnWithWait(wait, datepicker);
        changeMonthForwardUntilReach("January");
        WebElement lastDayOfJanuary = driver.findElement(By.xpath("//*[@data-handler='selectDay'][@data-month='0']/a[.='31']"));
        WebElement lastDayOfJanuaryParent = driver.findElement(By.xpath("//*[@data-handler='selectDay'][@data-month='0']/a[.='31']/parent::td"));
        expectedDate = getDateFrom(lastDayOfJanuaryParent, lastDayOfJanuary);
        lastDayOfJanuaryParent.click();
        actualDate = datepicker.getAttribute("value");
        softly.assertThat(actualDate).isEqualTo(expectedDate);
        logger.info("Moved to 31 of January next year and clicked it.");

        clickOnBtnWithWait(wait, datepicker);
        WebElement selectedDayParent = driver.findElement(By.cssSelector("tr td[class*='ui-datepicker-current-day']"));
        WebElement selectedDay = selectedDayParent.findElement(By.cssSelector("*[class*='ui-state-default ui-state-active']"));
        expectedDate = getDateFrom(selectedDayParent, selectedDay);
        selectedDayParent.click();
        actualDate = datepicker.getAttribute("value");
        softly.assertThat(actualDate).isEqualTo(expectedDate);
        logger.info("Selected again the same day - 31.01 next year.");

        clickOnBtnWithWait(wait, datepicker);
        WebElement prevMonth = driver.findElement(By.xpath("//*[contains(@class, 'ui-datepicker-prev ui-corner-all')]//span"));
        prevMonth.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
        clickAndCheckRandomDay(softly);
        logger.info("Selected random day from previous month.");

        clickOnBtnWithWait(wait, datepicker);
        moveBackNumberOfYears(1);
        moveBackwardToRandomMonth();
        clickAndCheckRandomDay(softly);
        logger.info("Selected random day from previous year.");

        softly.assertAll();
    }

    private void clickOnBtnWithWait(WebDriverWait wait, WebElement datepicker) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ui-datepicker-div")));
        datepicker.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
    }

    private void clickAndCheckRandomDay(SoftAssertions softAssert) {
        String actualDate;
        String expectedDate;

        WebElement datepicker = driver.findElement(By.id("datepicker"));
        List<WebElement> daysInTheMonth = driver.findElements(By.xpath("//*[@data-handler='selectDay' and not(contains(@class,'ui-datepicker-other-month'))]"));
        WebElement randomDayParent = getRandomDay(daysInTheMonth);
        WebElement randomDay = randomDayParent.findElement(By.xpath("./child::a"));
        expectedDate = getDateFrom(randomDayParent, randomDay);
        randomDayParent.click();
        actualDate = datepicker.getAttribute("value");
        softAssert.assertThat(actualDate).isEqualTo(expectedDate);
    }

    private void moveBackNumberOfYears(int years) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement currentYearField = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']//span[@class='ui-datepicker-year']"));
        int currentYear = Integer.parseInt(currentYearField.getText());
        int yearToMove = currentYear - years;

        while (currentYear != yearToMove) {
            WebElement prevMonthBtn = driver.findElement(By.xpath("//*[contains(@class, 'ui-datepicker-prev ui-corner-all')]//span"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
            prevMonthBtn.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
            currentYearField = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']//span[@class='ui-datepicker-year']"));
            currentYear = Integer.parseInt(currentYearField.getText());
        }
    }

    private void moveBackwardToRandomMonth() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Random rand = new Random();
        int randomMonth = rand.nextInt(11);
        for (int i = 0; i < randomMonth; i++) {
            WebElement prevMonthBtn = driver.findElement(By.xpath("//*[contains(@class, 'ui-datepicker-prev ui-corner-all')]//span"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
            prevMonthBtn.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
        }
    }

    private WebElement getRandomDay(List<WebElement> daysInTheMonth) {
        Random rand = new Random();
        int index = rand.nextInt(daysInTheMonth.size());
        return daysInTheMonth.get(index);
    }

    private void changeMonthForwardUntilReach(String month) {
        String currMonthText;
        do {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'ui-datepicker-next ui-corner-all')]//span")));
            WebElement nextMonth = driver.findElement(By.xpath("//*[contains(@class, 'ui-datepicker-next ui-corner-all')]//span"));
            nextMonth.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ui-datepicker-title']//span[@class='ui-datepicker-month']")));
            WebElement currMonth = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']//span[@class='ui-datepicker-month']"));
            currMonthText = currMonth.getText();
        }
        while (!Objects.equals(currMonthText, month));
    }

    private void changeMonthBackwardUntilReach(String month) {
        String currMonthText;
        do {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'ui-datepicker-prev ui-corner-all')]//span")));
            WebElement prevMonthBtn = driver.findElement(By.xpath("//*[contains(@class, 'ui-datepicker-prev ui-corner-all')]//span"));
            prevMonthBtn.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ui-datepicker-title']//span[@class='ui-datepicker-month']")));
            WebElement currMonth = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']//span[@class='ui-datepicker-month']"));
            currMonthText = currMonth.getText();
        }
        while (!Objects.equals(currMonthText, month));
    }

    private String getDateFrom(WebElement currDayParent, WebElement currDay) {

        String monthIndex = currDayParent.getAttribute("data-month");
        String monthValue = String.valueOf(Integer.parseInt(monthIndex) + 1);
        String month = (Integer.parseInt(monthValue) > 9 ? monthValue : "0" + monthValue);
        String dayValue = currDay.getText();
        String day = (Integer.parseInt(dayValue) > 9 ? dayValue : "0" + dayValue);
        String year = currDayParent.getAttribute("data-year");

        return month + "/" + day + "/" + year;
    }
}
