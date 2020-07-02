package main.java.com.senla.model.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

public class PropertyData {
    private static FileInputStream fis;
    private static String path;
    private static Properties property;

    public PropertyData(){
        init();
    }
    public static String getProperty(String key) {
        path = property.getProperty(key);
        return path;
    }
    public void init(){
        try {
            fis = new FileInputStream("C:\\Users\\Anton\\Documents\\bulyha_anton\\task-7\\model\\src\\main\\resources\\config.properties");
            property = new Properties();
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
