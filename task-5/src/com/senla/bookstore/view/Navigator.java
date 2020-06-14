package com.senla.bookstore.view;

import java.util.ArrayList;
import java.util.List;

public class Navigator {
    private Menu currentMenu;

    public void printMenu(){
        System.out.println(currentMenu.getName());
    }

    public void navigate(int index){
        List<MenuItem> listOfMenuItems = currentMenu.getMenuItems();
        listOfMenuItems.get(index).doAction();
        if (listOfMenuItems.get(index).getNextMenu() != null) {
            currentMenu = listOfMenuItems.get(index).getNextMenu();
        }
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
