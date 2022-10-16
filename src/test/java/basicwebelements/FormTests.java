package basicwebelements;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.FormPage;
import setup.TestBase;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class FormTests extends TestBase {

    @Test
    @DisplayName("Form test")
    @Tag("form")
    public void fillFormAndSubmit() {
        String downloadDir = getDownloadDir();
        driver.get("http://51.75.61.161:9102/form.php");
        FormPage formPage = new FormPage(driver);
        formPage.fillFirstName("Jan");
        formPage.fillLastName("Tracz");
        formPage.fillEmail("jtracz@gmail.com");
        formPage.selectRandomSex();
        formPage.fillAge("18");
        formPage.selectRandomExperience();
        formPage.selectAutomationTesterProfession();
        formPage.selectRandomContinent();
        formPage.selectSwitchCommand();
        formPage.selectWaitCommand();
        formPage.uploadFile("src/main/resources/test.txt");
        formPage.sendFile();
        assertThat("File submitted success", formPage.getValidationMessage(), equalTo("Form send with success"));
        String downloadFileName = formPage.getdownloadFileName();
        formPage.downloadFileTo(driver, downloadDir);
        assertThat("Check that file exists", new File(downloadDir, downloadFileName).exists(), equalTo(true));
    }
}
