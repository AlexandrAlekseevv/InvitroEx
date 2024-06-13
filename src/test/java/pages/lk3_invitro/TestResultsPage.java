package pages.lk3_invitro;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import config.ConfigLoader;
import io.qameta.allure.Step;
import models.IndividualOrderNumberForm;
import service.IndividualOrderNumberFormCreator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TestResultsPage {
    private static final String PAGE_URL = ConfigLoader.getPageURL("test.results.page.url");

    private final SelenideElement selectLanguageButton = $x("//header[@class='Header_header__fI9zR Header_show__rulEm']//div[@class='LanguageSelector_selector__bN2Cx css-2b097c-container']");
    private final SelenideElement languageButtonRU = $x("//div[@id='react-select-3-option-1']");
    private final SelenideElement searchResultButton = $x("//div[@class='']/button[@class= 'Button_button__Zk130 UnauthResultsPage_buttonWithIcon__xwbHQ']");
    private final SelenideElement pageTitle = $x("//h2");
    private final SelenideElement orderNumberInputField = $x("//input[@name='orderNumber']");
    private final SelenideElement birthDateInputField = $x("//input[@name='birthday']");
    private final SelenideElement lastNamInputField = $x("//input[@name='lastName']");
    private final SelenideElement warningMessage = $("div[class='UnauthResultsPage_error__m2C-2']");


    {
        selectLanguageButton.shouldBe(visible).click();
        languageButtonRU.shouldBe(visible).click();
        selectLanguageButton.shouldBe(text("ru"));
    }


    public void openPage() {

        step("Открываем страницу с результатами анализов", () -> open(PAGE_URL));
    }

    @Step("Нажимаем кнопку выполнить поиск")
    public void clickOnSearchResultButton(){
        searchResultButton.click();
    }

    @Step("проверяем текст заголовка страницы")
    public String getTextOfPageTirle(){
        return pageTitle.text();
    }

    @Step("обязательные поля выделены красным ")
    public void fieldsShouldBeHighlightedInRed() {
        orderNumberInputField.shouldHave(attributeMatching("class","Input_input__C7FoV Input_error__TVQ0l"));
        birthDateInputField.shouldHave(attributeMatching("class","Input_input__C7FoV Input_error__TVQ0l"));
        lastNamInputField.shouldHave(attributeMatching("class","Input_input__C7FoV Input_error__TVQ0l"));
    }

    @Step("появилось предупреждение \"Поля Код ИНЗ Дата рождения Фамилия обязательны для заполнения\"")
    public void theWarningMessageShouldBeDisplayed() {
        warningMessage.shouldBe(visible)
                .shouldHave(matchText("Поля Код ИНЗ.*Дата рождения.*Фамилия.*обязательны для заполнения"));
    }

    @Step("заполняем поля : код ИНЗ, Дата рождения,Фамилия")
    public void iEnterIndividualOrderDetailsFor(String userId) {
        IndividualOrderNumberForm formData = IndividualOrderNumberFormCreator.withCredentialFromProperty(userId);
        orderNumberInputField.setValue(formData.getOrderNumber());
        birthDateInputField.click();
        birthDateInputField.append(formData.getBirthday());
        executeJavaScript(
                "$('.react-datepicker-popper').hide();"
        );
        lastNamInputField.setValue(formData.getLastName());
        step("введённые данные : " + formData);
        sleep(3000);
    }
    @Step("проверяем, что в полях код ИНЗ, Дата рождения,Фамилия есть введёные значения")
    public void theFieldsShouldContainCorrectValuesFor(String userId) {
        IndividualOrderNumberForm formData = IndividualOrderNumberFormCreator.withCredentialFromProperty(userId);
        verifyFieldValue("код ИНЗ", orderNumberInputField, formData.getOrderNumber());
        verifyFieldValue("Дата рождения", birthDateInputField, formData.getBirthday());
        verifyFieldValue("Фамилия", lastNamInputField, formData.getLastName());
    }
    @Step("Поле {fieldName} содержит значение {expectedValue}")
    private void verifyFieldValue(String fieldName, SelenideElement field, String expectedValue) {
        field.shouldHave(Condition.value(expectedValue));
    }

}
