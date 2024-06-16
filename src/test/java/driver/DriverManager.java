package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {

        if (null == driverThreadLocal.get()) {
            switch (System.getProperty("browser", "chrome").toLowerCase()) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--start-maximized");
                    try {
                        driverThreadLocal.set(new RemoteWebDriver(new URL("http://192.168.1.183:4444/wd/hub"), firefoxOptions));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    try {
                        driverThreadLocal.set(new RemoteWebDriver(new URL("http://192.168.1.183:4444/wd/hub"), chromeOptions));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                default:
                    driverThreadLocal.set(new ChromeDriver());
                    break;
            }
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
