package pages.invitro;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import config.ConfigLoader;
import io.qameta.allure.Step;
import models.enums.Section;
import org.openqa.selenium.Keys;
import pages.lk3_invitro.TestResultsPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MainPage {
    private static final String PAGE_URL = ConfigLoader.getPageURL("main.page.url");
    private final SelenideElement cityButton = $x("//span[@class='city__name city__btn city__name--label']").as("Кнопка выбора города");
    private final SelenideElement citySearchInput = $("#select-basket-city-input").as("Поле ввода поиска города");
    private final SelenideElement headerButtonTestResult = $x("//a[@class='invitro_header-get_result' and @rel='index nofollow']").as("Кнопка результатов анализов");
    private final SelenideElement audienceButton = $x("//div[@id='buttonOpenPopupTargetSTATICSTRINGFORID']").as("кнопка выбора интересуемого раздела аудиторий");
    private final SelenideElement confirmChangeCityButton = $x("//span[contains(text(),'Выбрать')]").as("подтверждение смены города");
    private final String audienceSubmenuTemplate = "//div[@id='popupTargetSTATICSTRINGFORID']/a[@class='invitro_header-target_audience-item ']/span[text() = '%s']";

    public void openPage() {
        step("Открываем главную страницу ", () -> open(PAGE_URL));

    }

    @Step("Смена города на {city}")
    public void changeCity(String city) {
        cityButton.click();
        confirmChangeCityButton.click();
        citySearchInput.shouldBe(visible,Duration.ofSeconds(10)).sendKeys(city, Keys.ARROW_DOWN, Keys.ENTER);
    }

    @Step("Ожидаем изменения города на {city}")
    public String getNameOfNewCity(String city) {
        return cityButton.shouldHave(Condition.text(city)).text();
    }

    @Step("Открываем страницу с результатами анализов")
    public void openTestResultPage() {
        step("нажимаем кнопку \"Результаты анализов\"", () -> headerButtonTestResult.shouldBe(visible).click());
        new TestResultsPage();
    }


    @Step("Выбираю раздел {section.displayName}")
    public void selectSection(Section section) {
        if (!audienceButton.text().equals(section.getDisplayName())) {
            audienceButton.click();
            step("В аудитории выбираем подсекцию: " + section.getDisplayName(), () -> {
                SelenideElement audienceSubmenu = $x(String.format(audienceSubmenuTemplate, section.getDisplayName()))
                        .as(section.getDisplayName());
                audienceSubmenu.shouldBe(visible, Duration.ofSeconds(10)).click();
            });
        }
    }
}
