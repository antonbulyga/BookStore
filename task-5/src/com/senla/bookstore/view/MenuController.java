package com.senla.bookstore.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuController {
    private Builder builder = Builder.getInstance();
    private Navigator navigator = Navigator.getInstance();

    public void run() throws IOException {
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        System.out.println("Fill in index");
        int index1 = printIndex();
        navigator.navigate(index1);
        int index2 = printIndex();
        navigator.navigate(index2);
    }

    public int printIndex() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int index = Integer.parseInt(reader.readLine());
        return index;
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
