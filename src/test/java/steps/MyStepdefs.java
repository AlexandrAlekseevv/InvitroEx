package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import pages.invitro.AnalyzesForDoctorsPage;
import pages.invitro.MainPage;
import pages.invitro.RadiologyPage;
import pages.lk3_invitro.CartPage;
import pages.lk3_invitro.TestResultsPage;

import static io.qameta.allure.Allure.step;

public class MyStepdefs {
    private final ThreadLocal<RadiologyPage> radiologyPage = ThreadLocal.withInitial(RadiologyPage::new);
    private final ThreadLocal<MainPage> mainPage = ThreadLocal.withInitial(MainPage::new);
    private final ThreadLocal<TestResultsPage> testResultPage = ThreadLocal.withInitial(TestResultsPage::new);
    private final ThreadLocal<AnalyzesForDoctorsPage>  analyzesPage = ThreadLocal.withInitial(AnalyzesForDoctorsPage::new);
    private final ThreadLocal<CartPage>  cartPage = ThreadLocal.withInitial(CartPage::new);
    private String savedPrice;

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

    @Given("пользователь находится на странице Анализы")
    public void userIsOnTheAnalyzesPage() {
        analyzesPage.get().openPage();
    }

    @When("пользователь сохраняет цену выбраного анализа {string}")
    public void userSavesThePriceOfAnalysisNumber(String number) {
        SelenideElement analysisElement = analyzesPage.get().getAnalysisByNumber(number);
        step("стоимость анализа : " + savedPrice,() ->{

        savedPrice = analyzesPage.get().getAnalysisPrice(analysisElement);
        });
    }

    @And("пользователь добавляет выбранный анализ {string} в корзину")
    public void userAddsAnalysisNumberToTheCart(String number) {
        SelenideElement analysisElement = analyzesPage.get().getAnalysisByNumber(number);
        analyzesPage.get().addAnalysisToCart(analysisElement);
    }

    @When("кликаем по иконке корзины")
    public void clickOnCartIcon(){
        analyzesPage.get().openCartPage();
    }

    @Then("цена в корзине должна совпадать с сохраненной ценой")
    public void theCartPriceShouldBeComparedWithTheSavedPrice() {

        String cartPrice = cartPage.get().getCartPrice();
        int cartPriceValue = Integer.parseInt(cartPrice.replaceAll("[^0-9]", ""));
        int savedPriceValue = Integer.parseInt(savedPrice.replaceAll("[^0-9]", ""));

        if (savedPriceValue == 10000) {
            throw new AssertionError("Price is exactly 10000");
        } else if (savedPriceValue > 10000) {
            System.out.println("Saved price is greater than 10000");
        } else {
            System.out.println("Saved price is less than 10000");
        }

        Assertions.assertEquals(savedPriceValue,cartPriceValue);
    }

    @When("ищем анализ по коду в поисковой строке {string}")
    public void searchAnalysisByAnalyzeCodeInSearchField(String number) {
        analyzesPage.get().searchInSearchField(number);
    }
}
