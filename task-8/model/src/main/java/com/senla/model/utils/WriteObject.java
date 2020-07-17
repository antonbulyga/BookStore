package main.java.com.senla.model.utils;

import annotation.Config;
import main.java.com.senla.model.entity.*;

import java.io.*;

public class WriteObject {
    @Config(key = "bookStoreData")
    private static String path = null;

    public static void write(){
        try{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(Store.getInstance());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
