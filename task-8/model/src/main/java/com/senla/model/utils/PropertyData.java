package main.java.com.senla.model.utils;

import annotation.AnnotationAnalyzer;
import annotation.Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

public class PropertyData {
    private static Properties property;

    public static String getProperty(String key, String pathOfPropertyFile){
        try {
            FileInputStream fis = new FileInputStream(pathOfPropertyFile);
            property = new Properties();
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
       return property.getProperty(key);
    }

}
