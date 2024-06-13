package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.invitro.MainPage;
import pages.invitro.RadiologyPage;
import pages.invitro.TestResultsPage;

public class MyStepdefs {
    private final ThreadLocal<RadiologyPage> radiologyPage = ThreadLocal.withInitial(RadiologyPage::new);
    private final ThreadLocal<MainPage> mainPage = ThreadLocal.withInitial(MainPage::new);
    private final ThreadLocal<TestResultsPage> testResultPage = ThreadLocal.withInitial(TestResultsPage::new);

    @When("открыта страница Радиология")
    public void openTheRadiology() {
        radiologyPage.get().openPage();
    }

    @When("прокликиваем все пункты меню радиологических услуг")
    public void clickThroughAllTheItemsInTheMedicalServicesMenu() {
    }

    @Then("все пункты меню должны быть доступны")
    public void allMenuItemsShouldBeAccessible() {
        Assertions.assertTrue(radiologyPage.get().clickAllMenuItemsSuccess());
    }

    @When("открываем главную страницу Invitro")
    public void openTheInvitroWebsite() {
        mainPage.get().openPage();
    }

    @When("изменяем город на {string}")
    public void changeTheCityTo(String city) {
        mainPage.get().changeCity(city);
    }

    @Then("город должен быть изменен на {string}")
    public void theCityShouldBeChangedTo(String city) {
        String currentSelectedCityName = mainPage.get().getNameOfNewCity(city);
        Assertions.assertEquals(currentSelectedCityName, city);
    }

    @When("нажимаем кнопку Результаты анализов")
    public void clickTestResultButton() {
        mainPage.get().openTestResultPage();
    }

    @Then("название открывшейся страницы {string}")
    public void theTitleOfThePageThatOpensIs(String titleOfPage) {
        Assertions.assertEquals(titleOfPage, testResultPage.get().getTextOfPageTirle());
    }

    @When("нажимаем на кнопку \"Найти результаты\", не заполняя поля")
    public void clickOnWithoutFillingTheFields() {
        testResultPage.get().clickOnSearchResultButton();
    }

    @Then("поля должны быть выделены красным цветом")
    public void theFieldsShouldBeHighlightedInRed() {
        testResultPage.get().fieldsShouldBeHighlightedInRed();
    }

    @Then("должно появиться предупреждающее сообщение")
    public void theWarningMessageShouldBeDisplayed() {
        testResultPage.get().theWarningMessageShouldBeDisplayed();
    }

    @When("вводим данные индивидуального заказа для {string}")
    public void enterIndividualOrderDataFor(String userID) {
        testResultPage.get().iEnterIndividualOrderDetailsFor(userID);
    }



    @Then("поля должны содержать введённые значения {string}")
    public void fieldsMustContainEnteredValues(String userId) {
        testResultPage.get().theFieldsShouldContainCorrectValuesFor(userId);
    }
}
