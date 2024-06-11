package pages.invitro;

import com.codeborne.selenide.*;
import config.ConfigLoader;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class RadiologyPage {
    private static final String PAGE_URL = ConfigLoader.getProperty("radiology.page.url");
    private SelenideElement pageTitle = $(By.id("titlePage"));
    private final ElementsCollection sideMenuItems = $$x("//li[@class='side-bar-second__items']");

    public void openPage() {
        open(PAGE_URL);
    }


    public boolean clickAllMenuItemsSuccess() {
        for (int i = 0; i < sideMenuItems.size(); i++) {
            sideMenuItems.get(i).shouldBe(Condition.visible, Duration.ofSeconds(7)).click();
            if (!sideMenuItems.get(i).text().contains(sideMenuItems.get(i).text())) {
                return false;
            }
            ElementsCollection sideSubMenuItems = $$x("//li[@class='side-bar-second__items side-bar__items--active']/div/ul/li");
            if (!sideSubMenuItems.isEmpty()) {
                for (int j = 0; j < sideSubMenuItems.size(); j++) {
                    sideSubMenuItems.get(j).shouldBe(Condition.visible, Duration.ofSeconds(7)).click();
                    System.out.println(sideSubMenuItems.get(j).text() + " : " + pageTitle.text());
                    if (!pageTitle.text().contains(sideSubMenuItems.get(j).text())) {
                        return false;
                    }
                }
            }
        }
                return true;
    }
}


