package com.senla.bookstore.main.model.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyPath {
    public static String choose(String key) {
        FileInputStream fis;
        String path = null;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            path = property.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
    
}
