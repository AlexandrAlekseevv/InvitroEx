package hooks;

import com.codeborne.selenide.logevents.SelenideLogger;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static driver.DriverManager.getDriver;

public class CucumberHooks {
    @Before("@ui")
    public void setUp() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        setWebDriver(getDriver());
    }

    @After("@ui")
    public void tearDown() {
        DriverManager.closeDriver();
    }
}
