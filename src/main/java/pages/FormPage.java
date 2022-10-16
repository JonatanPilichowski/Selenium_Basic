package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class FormPage {

    @FindBy(id = "inputFirstName3")
    private WebElement firstNameInput;
    @FindBy(id = "inputLastName3")
    private WebElement lastNameInput;
    @FindBy(id = "inputEmail3")
    private WebElement emailInput;
    @FindBy(css = "input[name='gridRadiosSex']")
    private List<WebElement> genders;
    @FindBy(id = "inputAge3")
    private WebElement ageInput;
    @FindBy(css = "input[name='gridRadiosExperience']")
    private List<WebElement> experienceYears;
    @FindBy(xpath = "//input[@name='gridCheckboxProfession']")
    private List<WebElement> professions;
    @FindBy(id = "selectContinents")
    private WebElement selectContinents;
    @FindBy(xpath = "//select[@id='selectContinents']//option[position()>1]")
    private List<WebElement> continents;
    @FindBy(xpath = "//select[@id='selectSeleniumCommands']//option")
    private List<WebElement> seleniumCommands;
    @FindBy(id = "chooseFile")
    private WebElement uploadFileInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement sendButton;
    @FindBy(id = "validator-message")
    private WebElement validationMessage;
    @FindBy(css = "*[class*='btn-lg']")
    private WebElement downloadBtn;

    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillFirstName(String name) {
        firstNameInput.sendKeys(name);
    }

    public void fillLastName(String name) {
        lastNameInput.sendKeys(name);
    }

    public void fillEmail(String name) {
        emailInput.sendKeys(name);
    }

    public void selectRandomSex() {
        Random random = new Random();
        int randomSexIndex = random.nextInt(genders.size());
        genders.get(randomSexIndex).click();
    }

    public void fillAge(String age) {
        ageInput.sendKeys(age);
    }

    public void selectRandomExperience() {
        Random random = new Random();
        int randomYearIndex = random.nextInt(experienceYears.size());
        experienceYears.get(randomYearIndex).click();
    }

    public void selectAutomationTesterProfession() {
        professions.get(1).click();
    }

    public void selectRandomContinent() {
        Random random = new Random();
        int randomContinentIndex = random.nextInt(continents.size());
        continents.get(randomContinentIndex).click();
    }

    public void selectSwitchCommand() {
        seleniumCommands.get(2).click();
    }

    public void selectWaitCommand() {
        seleniumCommands.get(3).click();
    }

    public void uploadFile(String path) {
        File file = new File(path);
        uploadFileInput.sendKeys(file.getAbsolutePath());
    }

    public void sendFile() {
        sendButton.click();
    }

    public String getValidationMessage() {
        return validationMessage.getText();
    }

    public void clickDownloadFile() {
        downloadBtn.click();
    }

    public long getNumberOfFilesIn(String path) {
        try (Stream<Path> files = Files.list(Paths.get(path))) {
            return files.count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void downloadFileTo(WebDriver driver, String downloadDir) {
        String downloadFileName = getdownloadFileName();
        clickDownloadFile();
        Path downloadFilePath = Paths.get(downloadDir, downloadFileName);
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(d -> downloadFilePath.toFile().exists());
    }

    public boolean isNumbOfDownloadedIncreased(WebDriver driver, String downloadDir) {
        long originalNumberOfFiles = getNumberOfFilesIn(downloadDir);

        String downloadFileName = getdownloadFileName();
        clickDownloadFile();
        Path downloadFilePath = Paths.get(downloadDir, downloadFileName);
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(d -> downloadFilePath.toFile().exists());
        long currentNumberOfFiles = getNumberOfFilesIn(downloadDir);
        return (currentNumberOfFiles == (originalNumberOfFiles + 1L));
    }

    public String getdownloadFileName() {
        String downloadHref = downloadBtn.getAttribute("href").replace(":", "");
        return Paths.get(downloadHref).getFileName().toString();
    }
}
