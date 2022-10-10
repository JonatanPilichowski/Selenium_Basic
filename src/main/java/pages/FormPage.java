package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;
import java.util.Random;

public class FormPage {

    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="inputFirstName3")
    private WebElement firstNameInput;

    public void fillFirstName(String name){
        firstNameInput.sendKeys(name);
    }

    @FindBy(id="inputLastName3")
    private WebElement lastNameInput;

    public void fillLastName(String name){
        lastNameInput.sendKeys(name);
    }
    @FindBy(id="inputEmail3")
    private WebElement emailInput;

    public void fillEmail(String name){
        emailInput.sendKeys(name);
    }

    @FindBy(css = "input[name='gridRadiosSex']")
    private List<WebElement> genders;

    public void selectRandomSex(){
        Random random = new Random();
        int randomSexIndex = random.nextInt(genders.size());
        genders.get(randomSexIndex).click();
    }

    @FindBy(id="inputAge3")
    private WebElement ageInput;

    public void fillAge(String age){
        ageInput.sendKeys(age);
    }

    @FindBy(css="input[name='gridRadiosExperience']")
    private List<WebElement> experienceYears;

    public void selectRandomExperience(){
        Random random = new Random();
        int randomYearIndex = random.nextInt(experienceYears.size());
        experienceYears.get(randomYearIndex).click();
    }

    @FindBy(xpath = "//input[@name='gridCheckboxProfession']")
    private List<WebElement> professions;

    public void selectAutomationTesterProfession(){
        professions.get(1).click();
    }

    @FindBy(id="selectContinents")
    private WebElement selectContinents;

    @FindBy(xpath = "//select[@id='selectContinents']//option[position()>1]")
    private List<WebElement> continents;

    public void selectRandomContinent(){
        Random random = new Random();
        int randomContinentIndex = random.nextInt(continents.size());
        continents.get(randomContinentIndex).click();
    }

    @FindBy(xpath="//select[@id='selectSeleniumCommands']//option")
    private List<WebElement> seleniumCommands;

    public void selectSwitchCommand(){
        seleniumCommands.get(2).click();
    }
    public void selectWaitCommand(){
        seleniumCommands.get(3).click();
    }

    @FindBy(id="chooseFile")
    private WebElement uploadFileInput;

    public void uploadFile(String path){
        File file = new File(path);
        uploadFileInput.sendKeys(file.getAbsolutePath());
    }

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement sendButton;

    public void sendFile(){
        sendButton.click();
    }

    @FindBy(id="validator-message")
    private WebElement validationMessage;

    public String getValidationMessage(){
        return validationMessage.getText();
    }

}
