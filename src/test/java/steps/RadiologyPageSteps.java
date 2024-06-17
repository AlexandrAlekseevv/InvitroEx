package steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import pages.invitro.RadiologyPage;

import static io.qameta.allure.Allure.label;
import static io.qameta.allure.Allure.step;

public class RadiologyPageSteps {
    private final ThreadLocal<RadiologyPage> radiologyPage = ThreadLocal.withInitial(RadiologyPage::new);
    @When("открыта страница Радиология")
    public void openTheRadiology() {
        label("owner", "A.Alekseev");
        radiologyPage.get().openPage();
    }

    @When("прокликиваем все пункты меню радиологических услуг")
    public void clickThroughAllTheItemsInTheMedicalServicesMenu() {
        label("owner", "A.Alekseev");
    }

    @Then("все пункты меню должны быть доступны")
    public void allMenuItemsShouldBeAccessible() {
        label("owner", "A.Alekseev");
        Assertions.assertTrue(clickAllMenuItemsSuccess());
    }
    @Step("Щелкните все пункты меню и убедитесь в успехе")
    public boolean clickAllMenuItemsSuccess() {
        label("owner", "A.Alekseev");
        ElementsCollection sideMenuItems = radiologyPage.get().getSideMenuItems();
        for (int i = 0; i < sideMenuItems.size(); i++) {
            String menuItemName = sideMenuItems.get(i).text();
            SelenideElement menuItem = sideMenuItems.get(i).as(menuItemName);
            step("Обрабатываем пункт меню: " + menuItem, () -> {
                radiologyPage.get().clickMenuItem(menuItem);
                ElementsCollection sideSubMenuItems = radiologyPage.get().getSideSubMenuItems()
                        .as("Список подменю в " + menuItem);
                if(!isPageTitleContains(menuItemName)) {
                    throw new AssertionError("Заголовок страницы "+radiologyPage.get().getPageTitleText()+" не содержит текст: " + menuItemName);
                }
                if (!sideSubMenuItems.isEmpty()) {
                    for (int j = 0; j < sideSubMenuItems.size(); j++) {
                        String subMenuItemName = sideSubMenuItems.get(j).text();
                        SelenideElement subMenuItem = sideSubMenuItems.get(j).as(subMenuItemName);
                        step("Обрабатываем подменю: " + subMenuItem, () -> {
                            radiologyPage.get().clickSubMenuItem(subMenuItem);
                            if (!isPageTitleContains(subMenuItemName)) {
                                throw new AssertionError("Заголовок страницы "+radiologyPage.get().getPageTitleText()+" не содержит текст: " + subMenuItemName);
                            }
                        });
                    }
                }
            });
        }
        return true;
    }
    @Step("Заголовок страницы содержит название пункта меню : {expectedString}")
    public boolean isPageTitleContains(String expectedString) {
        label("owner", "A.Alekseev");
        String pageTitleText = radiologyPage.get().getPageTitleText();
        return step("Заголовок страницы " + pageTitleText,() ->pageTitleText.contains(expectedString));
    }
}
