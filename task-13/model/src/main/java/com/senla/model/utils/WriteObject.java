package com.senla.model.utils;

import com.senla.config.annotations.Component;
import com.senla.config.annotations.MyInject;
import com.senla.model.entity.Store;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Component
public class WriteObject {
    @MyInject(key = "bookStoreData")
    private static String path;

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
