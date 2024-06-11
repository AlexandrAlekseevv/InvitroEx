import com.codeborne.selenide.SelenideDriver;
import driver.DriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.invitro.MainPage;
import pages.invitro.RadiologyPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static driver.DriverSingleton.getDriver;

public class ExampleTest {
    @BeforeEach
    public void setUp() {
        setWebDriver(getDriver());
    }
    @AfterEach
    public void tearDown() {
        DriverSingleton.closeDriver();
    }


    @Test
    void test1(){


        }
    }

