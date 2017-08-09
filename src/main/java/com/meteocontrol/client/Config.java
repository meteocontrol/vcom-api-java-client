package com.meteocontrol.client;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class Config {
    private Properties config;
    private String[] expectedKeys = {
        "API_URL",
        "API_KEY",
        "API_USERNAME",
        "API_PASSWORD",
        "API_AUTH_MODE"
    };

    public Config() {
        this("config.default");
        this.validate();
    }

    public Config(Properties config) {
        this.config = config;
        this.validate();
    }

    public Config(String configFilePath) {
        this.readConfigurationFile(configFilePath);
        this.validate();
    }

    public String getApiUrl() {
        return this.config.getProperty("API_URL");
    }

    public void setApiUrl(String url) {
        this.config.setProperty("API_URL", url);
    }

    public String getApiKey() {
        return this.config.getProperty("API_KEY");
    }

    public void setApiKey(String key) {
        this.config.setProperty("API_KEY", key);
    }

    public String getApiUsername() {
        return this.config.getProperty("API_USERNAME");
    }

    public void setApiUsername(String username) {
        this.config.setProperty("API_USERNAME", username);
    }

    public String getApiPassword() {
        return this.config.getProperty("API_PASSWORD");
    }

    public void setApiPassword(String password) {
        this.config.setProperty("API_PASSWORD", password);
    }

    public String getApiAuthorizationMode() {
        return this.config.getProperty("API_AUTH_MODE");
    }

    public void setApiAuthorizationMode(String authorizationMode) {
        this.config.setProperty("API_AUTH_MODE", authorizationMode);
    }

    public void validate() {
        this.checkForUnexpectedKeys();
        this.checkForMissingKeys();
    }

    private void readConfigurationFile(String path) {
        this.config = new Properties();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            this.config.load(classLoader.getResourceAsStream(path));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("config file '" + path + "' is not found");
        } catch (IOException e) {
            throw new IllegalArgumentException("config file '" + path + "' cannot be read");
        }
    }

    private void checkForUnexpectedKeys() {
        for (Map.Entry<Object, Object> element: this.config.entrySet()) {
            if (!ArrayUtils.contains(this.expectedKeys, element.getKey())) {
                throw new IllegalArgumentException("wrong config file provided - unexpected key '" + element.getKey() + "' found");
            }
        }
    }

    private void checkForMissingKeys() {
        for (String element: this.expectedKeys) {
            if (!this.config.containsKey(element)) {
                throw new IllegalArgumentException("wrong config file provided - expected key '" + element + "' not found");
            }
        }
    }
}
