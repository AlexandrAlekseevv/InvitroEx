package pages.invitro;

import com.codeborne.selenide.*;
import config.ConfigLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RadiologyPage {
    private static final String PAGE_URL = ConfigLoader.getPageURL("radiology.page.url");
    private final SelenideElement pageTitle = $(By.id("titlePage")).as("Заголовок страницы");
    private final ElementsCollection sideMenuItems = $$x("//li[@class='side-bar-second__items']").as("список страниц бокового меню");

    public void openPage() {

        step("Открываем страницу радиологии", () -> open(PAGE_URL));
    }


    @Step("Щелкните все пункты меню и убедитесь в успехе")
    public boolean clickAllMenuItemsSuccess() {
        for (int i = 0; i < sideMenuItems.size(); i++) {
            String menuItemName = sideMenuItems.get(i).text();
            SelenideElement menuItem = sideMenuItems.get(i).as(menuItemName);
            step("Обрабатываем пункт меню: " + menuItem, () -> {
                clickMenuItem(menuItem);
                ElementsCollection sideSubMenuItems = $$x("//li[@class='side-bar-second__items side-bar__items--active']/div/ul/li")
                        .as("Список подменю в " + menuItem);
                if(!isPageTitleContains(menuItemName)) {
                    throw new AssertionError("Заголовок страницы "+pageTitle.text()+" не содержит текст: " + menuItemName);
                }
                if (!sideSubMenuItems.isEmpty()) {
                    for (int j = 0; j < sideSubMenuItems.size(); j++) {
                        String subMenuItemName = sideSubMenuItems.get(j).text();
                        SelenideElement subMenuItem = sideSubMenuItems.get(j).as(subMenuItemName);
                        step("Обрабатываем подменю: " + subMenuItem, () -> {
                            clickSubMenuItem(subMenuItem);
                            if (!isPageTitleContains(subMenuItemName)) {
                                throw new AssertionError("Заголовок страницы "+pageTitle.text()+" не содержит текст: " + subMenuItemName);
                            }
                        });
                    }
                }
            });
        }
        return true;
    }

    @Step("Заголовок страницы содержит название пункта меню : {expectedString}")
    private boolean isPageTitleContains(String expectedString) {
        return step("Заголовок страницы " + pageTitle.text(),() ->pageTitle.text().contains(expectedString));
    }


    private void clickMenuItem(SelenideElement menuItem) {
        step("Нажмите на пункт меню : " + menuItem.shouldBe(Condition.visible, Duration.ofSeconds(15)).text(),
                () -> menuItem.click());
    }

    private void clickSubMenuItem(SelenideElement subMenuItem) {
        step("Нажмите на пункт подменю : " + subMenuItem.shouldBe(Condition.visible, Duration.ofSeconds(15)).text(),
                () -> subMenuItem.click());
    }
}


