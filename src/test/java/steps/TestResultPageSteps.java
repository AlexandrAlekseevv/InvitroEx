package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import models.IndividualOrderNumberForm;
import org.junit.jupiter.api.Assertions;
import pages.lk3_invitro.TestResultsPage;
import service.IndividualOrderNumberFormCreator;

import static io.qameta.allure.Allure.label;

public class TestResultPageSteps {
    private final ThreadLocal<TestResultsPage> testResultPage = ThreadLocal.withInitial(TestResultsPage::new);

    @Then("название открывшейся страницы {string}")
    public void theTitleOfThePageThatOpensIs(String titleOfPage) {
        label("owner", "A.Alekseev");

        Assertions.assertEquals(titleOfPage, testResultPage.get().getTextOfPageTitle());
    }

    @When("нажимаем на кнопку \"Найти результаты\", не заполняя поля")
    public void clickOnWithoutFillingTheFields() {
        label("owner", "A.Alekseev");
        testResultPage.get().clickOnSearchResultButton();
    }

    @Then("поля должны быть выделены красным цветом")
    public void theFieldsShouldBeHighlightedInRed() {
        label("owner", "A.Alekseev");
        testResultPage.get().fieldsShouldBeHighlightedInRed();
    }

    @Then("должно появиться предупреждающее сообщение")
    public void theWarningMessageShouldBeDisplayed() {
        label("owner", "A.Alekseev");
        testResultPage.get().theWarningMessageShouldBeDisplayed();
    }

    @When("вводим данные индивидуального заказа для {string}")
    public void enterIndividualOrderDataFor(String userID) {
        testResultPage.get().iEnterIndividualOrderDetailsFor(userID);
    }

    @Then("поля должны содержать введённые значения {string}")
    public void fieldsMustContainEnteredValues(String userId) {
        theFieldsShouldContainCorrectValuesFor(userId);
    }
    @Step("проверяем, что в полях код ИНЗ, Дата рождения,Фамилия есть введёные значения")
    private void theFieldsShouldContainCorrectValuesFor(String userId) {
        IndividualOrderNumberForm formData = IndividualOrderNumberFormCreator.withCredentialFromProperty(userId);
        verifyFieldValue("код ИНЗ", testResultPage.get().getOrderNumberInputField(), formData.getOrderNumber());
        verifyFieldValue("Дата рождения", testResultPage.get().getBirthDateInputField(), formData.getBirthday());
        verifyFieldValue("Фамилия", testResultPage.get().getLastNamInputField(), formData.getLastName());
    }
    @Step("Поле {fieldName} содержит значение {expectedValue}")
    private void verifyFieldValue(String fieldName, SelenideElement field, String expectedValue) {
        field.shouldHave(Condition.value(expectedValue));
    }
}
