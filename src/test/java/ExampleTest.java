import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static driver.DriverManager.getDriver;

public class ExampleTest {
    @BeforeEach
    public void setUp() {
        setWebDriver(getDriver());
    }
    @AfterEach
    public void tearDown() {
        DriverManager.closeDriver();
    }


    @Test
    void test1(){


        }
    }

