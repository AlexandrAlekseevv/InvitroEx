package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.enums.Section;
import org.junit.jupiter.api.Assertions;
import pages.invitro.MainPage;

import static io.qameta.allure.Allure.label;

public class MainPageSteps {
    private final ThreadLocal<MainPage> mainPage = ThreadLocal.withInitial(MainPage::new);

    @When("открываем главную страницу Invitro")
    public void openTheInvitroWebsite() {
        label("owner", "A.Alekseev");
        mainPage.get().openPage();
    }

    @When("изменяем город на {string}")
    public void changeTheCityTo(String city) {
        label("owner", "A.Alekseev");
        mainPage.get().changeCity(city);
    }

    @Then("город должен быть изменен на {string}")
    public void theCityShouldBeChangedTo(String city) {
        label("owner", "A.Alekseev");
        String currentSelectedCityName = mainPage.get().getNameOfNewCity(city);
        Assertions.assertEquals(currentSelectedCityName, city);
    }

    @When("нажимаем кнопку Результаты анализов")
    public void clickTestResultButton() {
        label("owner", "A.Alekseev");
        mainPage.get().openTestResultPage();
    }

    @When("выбираю раздел {section}")
    public void selectSection(Section section) {
        label("owner", "A.Alekseev");
        mainPage.get().selectSection(section);
    }
}

