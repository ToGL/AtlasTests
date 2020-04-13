package atlastests;

import atlastests.components.FilterControl;
import atlastests.components.FormControl;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CharacterizationStepDefs implements FormControl, FilterControl {

    private String characterizationName;
    private String featureName;
    private SelenideElement pageHeader = $(".heading-title span");
    private SelenideElement cohortTableName = $("tbody .characterizations-list__tbl-col--name a");
    private SelenideElement featureAnalysisTableName = $("tbody .feature-analyses-list__tbl-col--name");

    @Then("^can see Characterization page$")
    public void canSeeCharaterizationPage() {
        pageHeader.shouldHave(text("Cohort Characterizations"));
    }

    @When("^click to New characterization button$")
    public void clickToNewCharacterizationButton() {
        $(byText("New Characterization")).click();
        pageHeader.shouldHave(text("New Characterization"));
    }

    @When("^enter Characterization name and save it$")
    public void enterCharacterizationNameAndSaveIt() {
        characterizationName = "Test_" + RandomStringUtils.randomAlphanumeric(10);
        setTitle(characterizationName);
        saveAction();
    }

    @When("^return to Characterization table$")
    public void returnToCharacterizationTable() {
        closeAction();
    }

    @When("^enter created characterization name in filter$")
    public void enterCreatedCharacterizationNameInFilter() {
        search(characterizationName);
    }

    @Then("^can see new characterization in table$")
    public void canSeeNewCharacterizationInTable() {
        cohortTableName.shouldHave(text(characterizationName));
    }

    @When("^click to our characterization$")
    public void clickToOurCharacterization() {
        cohortTableName.click();
    }

    @When("^click to delete characterization button$")
    public void clickToDeleteCharacterizationButton() {
        deleteAction();
    }

    @When("^accept delete characterization$")
    public void acceptDeleteCharacterization() {
        Selenide.confirm();
    }

    @Then("^cant find characterization in the table$")
    public void cantFindCharacterizationInTheTable() {
        search(characterizationName);
        cohortTableName.shouldNotHave(text(characterizationName));
    }

    @When("^click to Import Cohort Definition$")
    public void clickToImportCohortDefinition() {
        $$(byText("Import")).get(0).waitUntil(visible, 5000).click();
    }

    @When("^click to Feature Analyses tab$")
    public void clickToFeatureAnalysesTab() {
        $$("[role='presentation']").find(Condition.matchesText("Feature analyses")).click();
        $(".btn-primary").waitUntil(Condition.matchesText("New Feature analysis"), 5000);
    }

    @Then("^can see Feature Analyses table$")
    public void canSeeFeatureAnalysesTable() {
        $(".facetedDataTable tbody").waitUntil(visible, 10000);
    }

    @When("^click to New Feature analyses$")
    public void clickToNewFeatureAnalyses() {
        $(byText("New Feature analysis")).click();
    }

    @Then("^can see page of creation New Feature Analyse$")
    public void canSeePageOfCreationNewFeatureAnalyse() {
        pageHeader.shouldHave(text("New Feature Analysis"));
    }

    @When("^enter description$")
    public void enterDescription() {
        $("textarea.feature-analysis-view-edit__descr").setValue("TEST DESCRIPTION");
    }

    @When("^choose Criteria design$")
    public void chooseCriteriaDesign() {
        $$(".feature-analysis-view-edit__nav a").find(text("Criteria")).click();
        $("input[placeholder='Criteria name']").waitUntil(visible, 5000);
    }

    @When("^enter name of New Feature Analyse$")
    public void enterNameOfNewFeatureAnalyse() {
        featureName = "Test_" + RandomStringUtils.randomAlphanumeric(10);
        setTitle(featureName);
    }

    @When("^click to save feature analyse button$")
    public void clickToSaveFeatureAnalyseButton() {
        saveAction();
    }

    @Then("^go to feature analyses table by pressing close button$")
    public void goToFeatureAnalysesTableByPressingCloseButton() {
        closeAction();
    }

    @When("^enter name of our feature to filter$")
    public void enterNameOfOurFeatureToFilter() {
        search(featureName);
    }

    @Then("^can see our feature in table of feature analyses$")
    public void canSeeOurFeatureInTableOfFeatureAnalyses() {
        featureAnalysisTableName.shouldHave(text(featureName));
    }

    @When("^click to our feature analyse$")
    public void clickToOurFeatureAnalyse() {
        featureAnalysisTableName.waitUntil(visible, 5000).click();
    }

    @Then("^can see page of our Feature Analyse$")
    public void canSeePageOfOurFeatureAnalyse() {
        pageHeader.shouldHave(text("Feature Analysis #"));
    }


    @When("^click to delete feature analyse$")
    public void clickToDeleteFeatureAnalyse() {
        deleteAction();
    }

    @When("^accept delete feature analyse$")
    public void acceptDeleteFeatureAnalyse() {
        switchTo().alert().accept();
    }

    @Then("^cant find feature analyse in the table$")
    public void cantFindFeatureAnalyseInTheTable() {
        search(featureName);
        featureAnalysisTableName.shouldNotHave(text(featureName));
    }

    @When("^click to Import Feature analyses$")
    public void clickToImportFeatureAnalyses() {
        $$(byText("Import")).get(1).click();
    }

    @Then("^can see Feature analyses window$")
    public void canSeeFeatureAnalysesWindow() {
        $(".characterization-design__feature-analyses-modal .modal-header .modal-title").
                waitUntil(visible, 4000).shouldHave(text("Choose a Feature analyses"));
    }

    @Then("^Feature analyse table is visible$")
    public void featureAnalyseTableIsVisible() {
        $(By.xpath("//*[@class='characterization-design__col-feature-id sorting_asc']")).waitUntil(visible, 3000);


    }


    @When("^click to Import Feature analyse$")
    public void clickToImportFeatureAnalyse() {
        $(By.xpath("//*[@class='characterization-design__button-panel']/button[1]")).waitUntil(visible, 3000).click();

    }

    @When("^enter the same Characterization name and save it$")
    public void enterTheSameCharacterizationNameAndSaveIt() {
        setTitle(characterizationName);
        saveAction();
    }

    @Then("^can see alert message about uniqueness$")
    public void canSeeAlertMessageAboutUniqueness() {
        switchTo().alert().accept();
    }

    @When("^choose cohort definition \"([^\"]*)\" from the table in characterization$")
    public void chooseCohortDefinitionFromTheTableInCharacterization(String arg0) {
        $(By.xpath("//*[@class='col-xs-6 search']/div/label/input")).setValue(arg0);
        $(By.xpath("//table/tbody/tr/td[2]/span")).shouldHave(text(arg0)).click();
    }

    @Then("^can see cohort definition in characterization list with text \"([^\"]*)\"$")
    public void canSeeCohortDefinitionInCharacterizationListWithText(String arg0) {
        $(By.xpath("//table/tbody/tr/td[2]")).waitUntil(visible, 2000).shouldHave(text(arg0));
    }

    @When("^click to feature checkbox with text \"([^\"]*)\" from Feature analyses$")
    public void clickToFeatureCheckboxWithTextFromFeatureAnalyses(String arg0) {
        $(By.xpath("//*[@class='facetedDataTable']/div/div[2]/label/input")).setValue(arg0);
        $(By.xpath("//*[@class='fa fa-check'][1]")).click();
    }

    @Then("^can see result of our search \"([^\"]*)\" and \"([^\"]*)\"$")
    public void canSeeResultOfOurSearchAnd(String arg0, String arg1) {
        $$(By.xpath("//*[@class=' characterization-design__col-feature-name ']")).get(0).shouldHave(text(arg0));
        $$(By.xpath("//*[@class=' characterization-design__col-feature-name ']")).get(1).shouldHave(text(arg1));
    }

    @When("^click to save Chacterization$")
    public void clickToSaveChacterization() {
        $(By.xpath("//*[@class='fa fa-save']")).click();
    }

    @When("^click Remove first Feature Analyse from the table$")
    public void clickRemoveFirstFeatureAnalyseFromTheTable() {
        $(By.xpath("//*[@class=' characterization-design__col-feature-remove ']/a")).click();
    }

    @When("^click to Executions tab in Characterizations$")
    public void clickToExecutionsTabInCharacterizations() {
        $(By.xpath("//*[@class='tabs__header']/span[2]")).click();
    }

    @When("^click Generate report button on first data source$")
    public void clickToGenerateReportButtonOnFirstDataSource() {
        $$(By.xpath("//*[@class='characterization-view-edit-executions__action-text']")).get(0).click();
    }

    @Then("^first data source generate button has to be with Cancel text$")
    public void firstGenerateButtonHasToBeWithCancelText() {
        $$(By.xpath("//*[@class='btn btn-sm btn-danger']")).get(0).waitUntil(text("Cancel"), 4000);
    }

    @When("^click to Netezza Generate report button$")
    public void clickToNetezzaGenerateReportButton() {
        $$(By.xpath("//*[@class='characterization-view-edit-executions__action-text']")).get(1).click();
    }

    @Then("^Netezza generate button has to be with Cancel text$")
    public void netezzaGenerateButtonHasToBeWithCancelText() {
        $$(By.xpath("//*[@class='btn btn-sm btn-danger']")).get(0).waitUntil(text("Cancel"), 4000);
    }

    @When("^click to SynPUF(\\d+)k Generate report button$")
    public void clickToSynPUFKGenerateReportButton(int arg0) {
        $$(By.xpath("//*[@class='characterization-view-edit-executions__action-text']")).get(5).click();
    }

    @Then("^SynPUF(\\d+)k generate button has to be with Cancel text$")
    public void synpufKGenerateButtonHasToBeWithCancelText(int arg0) {
        $$(By.xpath("//*[@class='btn btn-sm btn-danger']")).get(0).waitUntil(text("Cancel"), 15000);
    }

    @When("^click to oracle Generate report button$")
    public void clickToOracleGenerateReportButton() {
        $$(By.xpath("//*[@class='characterization-view-edit-executions__action-text']")).get(9).click();
    }

    @Then("^oracle generate button has to be with Cancel text$")
    public void oracleGenerateButtonHasToBeWithCancelText() {
        $$(By.xpath("//*[@class='btn btn-sm btn-danger']")).get(0).waitUntil(text("Cancel"), 15000);
    }

    @When("^click to copy characterization$")
    public void clickToCopyCharacterization() {
        copyAction();
    }

    @When("^enter \"([^\"]*)\" and name of our characterization$")
    public void enterAndNameOfOurCharacterization(String arg0) {
        search(arg0 + characterizationName);
    }

    @Then("^can see copy of our characterization$")
    public void canSeeCopyOfOurCharacterization() {
        cohortTableName.shouldHave(text("COPY OF: " + characterizationName));

    }

    @When("^click to Add Subgroup analyses$")
    public void clickToAddSubgroupAnalyses() {
        $(By.xpath("//*[@class='characterization-design btn btn-sm btn-primary']")).click();
    }

    @Then("^can see Stratified input and subgroup table$")
    public void canSeeStratifiedInputAndSubgroupTable() {
        $(By.xpath("//*[@class='characterization-design__stratified-by form-control']")).shouldBe(visible);
    }

    @When("^enter Stratified by text \"([^\"]*)\"$")
    public void enterStratifiedByText(String arg0) {
        $(By.xpath("//*[@class='characterization-design__stratified-by form-control']")).setValue(arg0);
    }

    @When("^click to add criteria to group button$")
    public void clickToAddCriteriaToGroupButton() {
        $(By.xpath("//*[@class='drop-down-menu btn btn-primary btn-sm dropdown-toggle']/span[2]")).click();

    }

    @When("^click to Add Demographic point$")
    public void clickToAddDemographicPoint() {
        $(By.xpath("//*[@class='drop-down-menu dropdown-menu']/li/a/div[1]")).click();
    }

    @Then("^can see Add attribute button$")
    public void canSeeAddAttributeButton() {
        $(By.xpath("//*[@class='btn btn-primary btn-sm dropdown-toggle']")).shouldBe(visible);
    }

    @When("^click to New parameter button$")
    public void clickToNewParameterButton() {
        $$(By.xpath("//*[@class='linked-entity-list__btn btn btn-primary btn-sm']")).get(2).click();
    }

    @When("^enter name of parameter \"([^\"]*)\" and value \"([^\"]*)\" and click Submit button$")
    public void enterNameOfParameterAndValueAndClickSubmitButton(String arg0, String arg1) {
        $$(By.xpath("//*[@class='form-control']")).get(1).setValue(arg0);
        $$(By.xpath("//*[@class='form-control']")).get(2).setValue(arg1);
        $(By.xpath("//*[@class='btn btn-default']")).click();
    }

    @Then("^can see our parameter \"([^\"]*)\" and value \"([^\"]*)\" in the table$")
    public void canSeeOurParameterAndValueInTheTable(String arg0, String arg1) {
        $(By.xpath("//*[@class='characterization-design__col-param-name sorting_1']")).shouldHave(text(arg0));
        $(By.xpath("//*[@class=' characterization-design__col-param-value ']")).shouldHave(text(arg1));

    }
}
