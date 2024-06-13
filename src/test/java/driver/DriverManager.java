package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {

        if (null == driverThreadLocal.get()) {
            switch (System.getProperty("browser", "chrome").toLowerCase()) {
                case "firefox":

                    driverThreadLocal.set(new FirefoxDriver());
                    break;
                case "chrome":
                    driverThreadLocal.set(new ChromeDriver());
                    break;

                default:
                    driverThreadLocal.set(new ChromeDriver());
                    break;
            }
            driverThreadLocal.get().manage().window().maximize();
        }

        return driverThreadLocal.get();
}

    public static void closeDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }

}
