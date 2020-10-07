package main.java.com.senla.view;

import main.java.com.senla.model.utils.ReadObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuController {
    private Builder builder = Builder.getInstance();
    private Navigator navigator = Navigator.getInstance();

    public void run() throws IOException {
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getRootMenu());
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                navigator.printMenu();
                System.out.println("Fill in index");
                int index = Integer.parseInt(reader.readLine());
                navigator.navigate(index);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Incorrect index, please try again");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
