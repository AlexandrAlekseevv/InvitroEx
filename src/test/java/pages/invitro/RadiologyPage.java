package pages.invitro;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.ConfigLoader;
import lombok.Getter;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RadiologyPage {
    private static final String PAGE_URL = ConfigLoader.getPageURL("radiology.page.url");
    private final SelenideElement pageTitle = $(By.id("titlePage")).as("Заголовок страницы");
    @Getter private final ElementsCollection sideMenuItems = $$x("//li[@class='side-bar-second__items']").as("список страниц бокового меню");
    @Getter private final  ElementsCollection sideSubMenuItems = $$x("//li[@class='side-bar-second__items side-bar__items--active']/div/ul/li");

    public void openPage() {
        step("Открываем страницу радиологии", () -> open(PAGE_URL));
    }

    public String getPageTitleText(){
        return pageTitle.text();
    }

    public void clickMenuItem(SelenideElement menuItem) {
        step("Нажмите на пункт меню : " + menuItem.shouldBe(Condition.visible, Duration.ofSeconds(15)).text(),
                () -> menuItem.click());
    }

    public void clickSubMenuItem(SelenideElement subMenuItem) {
        step("Нажмите на пункт подменю : " + subMenuItem.shouldBe(Condition.visible, Duration.ofSeconds(15)).text(),
                () -> subMenuItem.click());
    }
}


