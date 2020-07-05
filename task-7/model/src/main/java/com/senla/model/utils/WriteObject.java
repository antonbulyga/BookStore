package main.java.com.senla.model.utils;

import main.java.com.senla.model.entity.*;

import java.io.*;

public class WriteObject {
    public static void write(){
        try{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PropertyData.getProperty("bookStoreData")));
            oos.writeObject(Store.getInstance());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
