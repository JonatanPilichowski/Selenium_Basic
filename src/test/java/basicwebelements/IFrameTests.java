package basicwebelements;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import setup.TestBase;

import java.util.List;
import java.util.Random;

public class IFrameTests extends TestBase {
    @Test
    @DisplayName("iFrames test")
    @Tag("iFrame")
    public void iFrameTest(){
        driver.get("http://automation-practice.emilos.pl/iframes.php");
        driver.switchTo().frame("iframe1");
        WebElement firstNameInput = driver.findElement(By.cssSelector("#inputFirstName3"));
        firstNameInput.sendKeys("Janusz");
        WebElement lastNameInput = driver.findElement(By.cssSelector("#inputSurname3"));
        lastNameInput.sendKeys("Tracz");
        WebElement signInBtn = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        driver.switchTo().defaultContent();
        driver.switchTo().frame("iframe2");

        WebElement loginInput = driver.findElement(By.cssSelector("#inputLogin"));
        loginInput.sendKeys("jtracz999");
        WebElement passwordInput = driver.findElement(By.cssSelector("#inputPassword"));
        passwordInput.sendKeys("jestemNajlepszy!23");
        WebElement continentsDropDown = driver.findElement(By.cssSelector("#inlineFormCustomSelectPref"));
        continentsDropDown.click();
        List<WebElement> continents = driver.findElements(By.xpath("//select[@id='inlineFormCustomSelectPref']//option[position()>1]"));
        clickOnRandomElementFromList(continents);
        List<WebElement> yearsOfExperience = driver.findElements(By.cssSelector("input[name='gridRadios']"));
        clickOnRandomElementFromList(yearsOfExperience);
        driver.switchTo().defaultContent();
        WebElement basicDropdownMenu = driver.findElement(By.xpath("//li[@class='nav-ite dropdown']"));
        Actions action = new Actions(driver);
        action.moveToElement(basicDropdownMenu).perform();
        basicDropdownMenu.click();
    }

    public void clickOnRandomElementFromList(List<WebElement> ListOfElements){
        Random random = new Random();
        int randomContinentIndex = random.nextInt(ListOfElements.size());
        ListOfElements.get(randomContinentIndex).click();
    }

}
