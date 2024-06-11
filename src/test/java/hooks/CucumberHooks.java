package hooks;

import driver.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static driver.DriverSingleton.getDriver;

public class CucumberHooks {
    @Before("@ui")
    public void setUp() {
        setWebDriver(getDriver());
    }
    @After("@ui")
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
