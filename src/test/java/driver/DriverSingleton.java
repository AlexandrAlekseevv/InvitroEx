package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverSingleton  {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {

        if(null==driverThreadLocal.get()){
//            switch (System.getProperty("browser").toLowerCase()){
//                case "firefox":
//
//                    driverThreadLocal.set( new FirefoxDriver());
//                    break;
//                case "remote":
//                    WebDriverManager.chromedriver().setup();
//                    ChromeOptions chromeOptions = new ChromeOptions();
//                    try {
//                        driver = new RemoteWebDriver(new URL("http://192.168.1.183:4444/wd/hub"), chromeOptions);
//                    } catch (MalformedURLException e) {
//                        throw new RuntimeException(e);
//                    }
//                    break;
//                default:
//                    WebDriverManager.chromedriver().setup();
//                    driver = new ChromeDriver();
//                    break;
//            }
            driverThreadLocal.set( new FirefoxDriver());
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
