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
    private static FileInputStream fis;
    @Config()
    private String pathOfPropertyFile;
    private static Properties property;

    public PropertyData(){
        init();
    }
    public static String getProperty(String key) {
        String pathByKey = property.getProperty(key);
        return pathByKey;
    }
    public void init(){
        try {
            PropertyData propertyData = new PropertyData();
            AnnotationAnalyzer.setPathFromAnnotation(propertyData);
            fis = new FileInputStream(pathOfPropertyFile);
            property = new Properties();
            property.load(fis);
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
