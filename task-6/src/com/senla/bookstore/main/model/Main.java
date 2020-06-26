package com.senla.bookstore.main.model;


import com.senla.bookstore.main.view.MenuController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MenuController menuController = new MenuController();
        menuController.run();
    }
}
