package com.senla.bookstore.main.model.utils.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntegerInput {
    private static int inputInteger = 0;

    public static int getInputInteger() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            inputInteger = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Please fill in correct data (integer)");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return inputInteger;
    }
}
