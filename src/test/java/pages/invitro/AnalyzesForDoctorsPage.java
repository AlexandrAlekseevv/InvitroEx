package pages.invitro;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import config.ConfigLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.ElementClickInterceptedException;
import pages.lk3_invitro.CartPage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AnalyzesForDoctorsPage {
    private static final String PAGE_URL = ConfigLoader.getPageURL("analyzes.for.doctors.page.url");

    private final SelenideElement searchField = $x("//input[@name='q']");
    private final SelenideElement inCartButton = $x("//div[@class='invitro_header-menu']//a[@href='https://lk3.invitro.ru/cart']");

    public void openPage() {
        step("Открываем страницу Анализы", () -> open(PAGE_URL));

    }

    public SelenideElement getAnalysisByNumber(String numberOfAnalyze) {
        return $x("//div[@class='analyzes-item__head--number']/span[text()= '№ " + numberOfAnalyze + "']");
    }

    public String getAnalysisTitle(SelenideElement analysisElement) {
        return analysisElement.$x(".//ancestor::div[contains(@class, 'analyzes-list')]//div[@class='analyzes-item__title']").getText();
    }

    @Step("Получаем стоимость выбраного анализа")
    public String getAnalysisPrice(SelenideElement analysisElement) {
        return analysisElement.$x(".//ancestor::div[contains(@class, 'analyzes-list')]//div[@class='analyzes-item__total--sum']").getText();
    }

    @Step("Добавляем выбранный анализ в корзину")
    public void addAnalysisToCart(SelenideElement analysisElement) {
        if($("#ednamb-button").isDisplayed()){
        executeJavaScript(
                "$('#ednamb-button').hide();"
        );}
        analysisElement.$x("..//ancestor::div[contains(@class, 'analyzes-list')]//a[@href and @title='В корзину']").shouldBe(Condition.clickable).click();
    }

    @Step("Нажимаем иконку\"В корзине\"")
    public void openCartPage() {
        inCartButton.shouldBe(Condition.visible).click();
        new CartPage();
    }

    @Step("Производим поиск по сайту по тексту {text}")
    public void searchInSearchField(String text){
        searchField.setValue(text).pressEnter();
    }

}


