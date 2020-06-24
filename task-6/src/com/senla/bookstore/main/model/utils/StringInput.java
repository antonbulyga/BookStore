package com.senla.bookstore.main.model.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringInput {
    private static String stringInput = null;

    public static String getStringInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            stringInput = reader.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return stringInput;
    }

}
