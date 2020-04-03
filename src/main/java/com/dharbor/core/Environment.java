package com.dharbor.core;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains the environment's settings configuration.
 */
public class Environment {
    private Properties properties;
    private Logger log = Logger.getLogger(getClass());
    private static Environment instance;

    public Environment() {
        initialize();
    }

    /**
     * Gets an instance of Environment.
     *
     * @return Environment
     */
    public static Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    /**
     * Initializes the Environment settings.
     */
    private void initialize() {
        log.info("Initialize Environment manager");
        properties = new Properties();
        try {
            File file = new File("gradle.properties");
            FileReader fileReader = new FileReader(file);
            properties.load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            log.error("Unable to read gradle.properties file");
        }
    }

    /**
     * Gets the given setting from the properties file.
     *
     * @param setting - Setting from properties file.
     * @return String - The configuration value for the setting.
     */
    private String getEnvironmentSetting(String setting) {
        return (String) getInstance().properties.get(setting);
    }

    /**
     * Gets the browser setting from the properties file.
     *
     * @return String - The browser setting.
     */
    public String getBrowser() {
        return getEnvironmentSetting("browser");
    }

    /**
     * Gets the application's URL from the properties file.
     *
     * @return String - The application URL.
     */
    public String getBaseURL() {
        return getEnvironmentSetting("baseURL");
    }

    /**
     * Gets the application's home page URL.
     *
     * @return String - The application's home page URL.
     */
    public String getHomeURL() {
        return getBaseURL().concat("/index.php");
    }
}
