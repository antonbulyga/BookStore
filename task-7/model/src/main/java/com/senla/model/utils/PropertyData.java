package main.java.com.senla.model.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

public class PropertyData {
    private static FileInputStream fis;
    private static String path;

    public PropertyData(){
        init();
    }
    public static String getProperty(String key) {
        Properties property = new Properties();
        try{
            property.load(fis);
            path = property.getProperty(key);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return path;
    }
    public void init(){
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
