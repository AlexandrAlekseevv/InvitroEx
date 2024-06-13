package pages.invitro;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import config.ConfigLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MainPage {
    private static final String PAGE_URL = ConfigLoader.getPageURL("main.page.url");
    private final SelenideElement cityButton = $x("//span[@class='city__name city__btn city__name--label']");
    private final SelenideElement citySearchInput = $("#select-basket-city-input");
    private final SelenideElement headerButtonTestResult = $x("//a[@class='invitro_header-get_result' and @rel='index nofollow']");

    public void openPage() {
        step("Открываем главную страницу ", () -> open(PAGE_URL));

    }

    @Step("Смена города на {city}")
    public void changeCity(String city) {
        String currentCity = getSelectedCityName();
        step("Нажимаем кнопку выбора города (текущий город: " + currentCity + ")", () -> cityButton.click());
        step("Подтверждаем смену города", () -> $x("//span[contains(text(),'Выбрать')]").shouldBe(Condition.visible).click());
        step("В поле ввода вводим город: " + city, () -> citySearchInput.shouldBe(Condition.visible).sendKeys(city, Keys.ARROW_DOWN, Keys.ENTER));


    }

    @Step("Ожидаем изменения города на {city}")
    public String getNameOfNewCity(String city) {
        return cityButton.shouldHave(Condition.text(city)).text();
    }

    @Step("Открываем страницу с результатами анализов")
    public void openTestResultPage(){
        step("нажимаем кнопку \"Результаты анализов\"",() -> headerButtonTestResult.shouldBe(Condition.visible).click());
        new TestResultsPage();
    }

    private String getSelectedCityName() {
        return cityButton.text();
    }
}
