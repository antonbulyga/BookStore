package com.senla.model.utils.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DoubleInput {
    private static double doubleInput;

    public static double getDoubleInput(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            doubleInput = Double.parseDouble(reader.readLine());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch(NumberFormatException e){
            System.out.println("Please fill in correct data (double)");
        }
        return doubleInput;
    }
}
