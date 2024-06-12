package pages.invitro;

import com.codeborne.selenide.*;
import config.ConfigLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RadiologyPage {
    private static final String PAGE_URL = ConfigLoader.getProperty("radiology.page.url");
    private final SelenideElement pageTitle = $(By.id("titlePage"));
    private final ElementsCollection sideMenuItems = $$x("//li[@class='side-bar-second__items']");

    public void openPage() {

        step("Открываем страницу радиологии", () -> open(PAGE_URL));
    }


    @Step("Щелкните все пункты меню и убедитесь в успехе")
    public boolean clickAllMenuItemsSuccess() {
        for (int i = 0; i < sideMenuItems.size(); i++) {
            clickMenuItem(sideMenuItems.get(i));

            ElementsCollection sideSubMenuItems = $$x("//li[@class='side-bar-second__items side-bar__items--active']/div/ul/li");
            if (!sideSubMenuItems.isEmpty()) {
                for (int j = 0; j < sideSubMenuItems.size(); j++) {
                    String subMenuItemText = sideSubMenuItems.get(j).text();
                    clickSubMenuItem(sideSubMenuItems.get(j));
                    if (!pageTitle.text().contains(subMenuItemText)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Step("Нажмите на пункт меню {index}: {text}")
    private void clickMenuItem(SelenideElement menuItem) {
        menuItem.shouldBe(Condition.visible, Duration.ofSeconds(7)).click();
    }

    @Step("Нажмите на пункт подменю {index}: {text}")
    private void clickSubMenuItem(SelenideElement subMenuItem) {
        subMenuItem.shouldBe(Condition.visible, Duration.ofSeconds(7)).click();
        System.out.println(subMenuItem.text() + " : " + pageTitle.text());
    }
}


