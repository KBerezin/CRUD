package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {
    public static Properties getProperty(String name) {
        Properties properties = new Properties();

        ClassLoader classLoader = PropertyReader.class.getClassLoader();
        File configFile = new File(Objects.requireNonNull(classLoader.getResource(name)).getFile());

        try (InputStream fis = new FileInputStream(configFile)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error to access to file ".concat(name));
        }
        return properties;
    }
}
