package com.onseo.ht.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.onseo.ht.util.Const.PROPERTY_FILE_NAME;
import static com.sun.org.apache.xml.internal.utils.LocaleUtility.EMPTY_STRING;

class PropertiesProvider {

    static synchronized String getProperty(String name) {
        Properties prop = new Properties();
        try(InputStream input = PropertiesProvider.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {

            prop.load(input);
           return prop.getProperty(name);

        } catch (IOException ex) {
            ex.printStackTrace();
            return EMPTY_STRING;
        }
    }
}
