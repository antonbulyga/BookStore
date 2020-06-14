package com.senla.bookstore.view;

public class MenuController {
    private Builder builder;
    private Navigator navigator;

    public void run(){
         builder.buildMenu();
         navigator.printMenu();
         navigator.navigate(2);
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
