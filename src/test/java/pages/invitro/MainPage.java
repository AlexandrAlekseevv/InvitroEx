package pages.invitro;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import config.ConfigLoader;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private static final String PAGE_URL = ConfigLoader.getProperty("main.page.url");
    private final SelenideElement cityButton = $x("//span[@class='city__name city__btn city__name--label']");
    private final SelenideElement citySearchInput = $("#select-basket-city-input");

    public MainPage openPage() {
        open(PAGE_URL);
        return this;
    }

    public void changeCity(String city) {
        cityButton.click();
        $x("//span[contains(text(),'Выбрать')]").shouldBe(Condition.visible).click();
        citySearchInput.shouldBe(Condition.visible).sendKeys(city , Keys.ARROW_DOWN,Keys.ENTER);
        sleep(3000);
    }

    public String getSelectedCityName(){
        return cityButton.text();
    }
}
