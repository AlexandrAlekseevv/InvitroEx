package config;


import java.util.ResourceBundle;

public class ConfigLoader {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("page_url_config");

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }


}