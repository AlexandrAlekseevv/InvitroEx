package pages.invitro;

import com.codeborne.selenide.SelenideElement;
import config.ConfigLoader;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TestResultsPage {
    private static final String PAGE_URL = ConfigLoader.getProperty("test.results.page.url");

    private final SelenideElement searchResultButton = $x("//button[text()='Search results']");
    private final SelenideElement pageTitle = $x("//h2");
    private final SelenideElement orderNumberInputField = $x("//input[@name='orderNumber']");
    private final SelenideElement birthDateInputField = $x("//input[@name='birthday']");
    private final SelenideElement lastNamInputField = $x("//input[@name='lastName']");
    private final SelenideElement warningMessage = $("div[class='UnauthResultsPage_error__m2C-2']");
    private final SelenideElement recaptchaAnchorLabel = $x("//input[@name='lastName']");




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
                .shouldHave(matchText("Fields: Order number.*Birth date.*Last name.*are required for filling"));
    }

}
