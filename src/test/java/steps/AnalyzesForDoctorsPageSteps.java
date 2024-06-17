package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.When;
import pages.invitro.AnalyzesForDoctorsPage;

import static io.qameta.allure.Allure.label;
import static io.qameta.allure.Allure.step;

public class AnalyzesForDoctorsPageSteps {

    private final ThreadLocal<AnalyzesForDoctorsPage> analyzesPage = ThreadLocal.withInitial(AnalyzesForDoctorsPage::new);


    @When("пользователь находится на странице Анализы")
    public void userIsOnTheAnalyzesPage() {
        label("owner", "A.Alekseev");
        analyzesPage.get().openPage();
    }


    @When("пользователь сохраняет цену выбранного анализа {string}")
    public void userSavesThePriceOfAnalysisNumber(String number) {
        label("owner", "A.Alekseev");
        SelenideElement analysisElement = analyzesPage.get().getAnalysisByNumber(number);
        String savedPrice = analyzesPage.get().getAnalysisPrice(analysisElement);
        step("стоимость анализа : " + savedPrice);

    }


    @When("пользователь добавляет выбранный анализ {string} в корзину")
    public void userAddsAnalysisNumberToTheCart(String number) {
        label("owner", "A.Alekseev");
        SelenideElement analysisElement = analyzesPage.get().getAnalysisByNumber(number);
        analyzesPage.get().addAnalysisToCart(analysisElement);
    }


    @When("кликаем по иконке корзины")
    public void clickOnCartIcon() {
        label("owner", "A.Alekseev");
        analyzesPage.get().openCartPage();
    }


    @When("ищем анализ по коду в поисковой строке {string}")
    public void searchAnalysisByAnalyzeCodeInSearchField(String number) {
        label("owner", "a.alekseev");
        analyzesPage.get().searchInSearchField(number);
    }
}
