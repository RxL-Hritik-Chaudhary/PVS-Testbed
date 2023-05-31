package com.rxlogix.testEngine;

import java.io.FileReader;
import java.util.Properties;

import org.apache.commons.exec.util.StringUtils;

public class PropertiesWrapper {
	
	private static final String TEST_CLASSES_PROPERTIES_FILE_NAME = "testclasses.properties";
    private static final String UI_CONFIG_PROPERTIES_FILE_NAME = "uiconfiguration.properties";
    private static final String CONF_PATH = "conf/";

    public static Properties getPropertiesForTestClasses(String fileName) throws Exception {
            if(fileName == "") {
                fileName = TEST_CLASSES_PROPERTIES_FILE_NAME;
            }
            FileReader reader = new FileReader(CONF_PATH+fileName);
            Properties properties = new Properties();
            properties.load(reader);
            return properties;
    }

    public static Properties getPropertiesForUIConfig(String fileName) throws Exception {
            if(fileName == "") {
                fileName = UI_CONFIG_PROPERTIES_FILE_NAME;
            }
            FileReader reader = new FileReader(CONF_PATH+fileName);
            Properties properties = new Properties();
            properties.load(reader);
            return properties;
    }

}
