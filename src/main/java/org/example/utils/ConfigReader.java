package org.example.utils;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop =
            new Properties();

    static {

        try {

            InputStream input =
                    ConfigReader.class
                            .getClassLoader()
                            .getResourceAsStream(
                                    "config.properties");

            prop.load(input);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to load config.properties");
        }
    }

    public static String getProperty(
            String key) {

        return prop.getProperty(key);
    }
}
