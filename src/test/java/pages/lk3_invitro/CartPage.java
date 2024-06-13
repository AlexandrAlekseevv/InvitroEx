package pages.lk3_invitro;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import config.ConfigLoader;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class CartPage {
    private static final String PAGE_URL = ConfigLoader.getPageURL("cart.page.url");
    private final SelenideElement products = $x("//div[@class='SummaryBlock_cartCostPosition__7GXLO']//div[@class='SummaryBlock_cartCostPositionCost__BYAgG']");
    private final SelenideElement selectLanguageButton = $x("//header[@class='Header_header__fI9zR Header_show__rulEm']//div[@class='LanguageSelector_selector__bN2Cx css-2b097c-container']");
    private final SelenideElement languageButtonRU = $x("//div[@id='react-select-3-option-1']");



    public void openPage() {
        step("Открываем корзину", () -> open(PAGE_URL));
    }
    @Step("Получаем стоимость выбраных анализов")
    public String getCartPrice() {
        return products.shouldBe(Condition.visible).text();
    }

}
