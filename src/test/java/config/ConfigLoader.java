package config;


import java.util.ResourceBundle;

public class ConfigLoader {
    private static final ResourceBundle resourceURLBundle = ResourceBundle.getBundle("page_url_config");
    private static final ResourceBundle resourceTestDataBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getPageURL(String key) {
        return resourceURLBundle.getString(key);
    }
    public static String getTestData(String key){
        return resourceTestDataBundle.getString(key);
    }
}