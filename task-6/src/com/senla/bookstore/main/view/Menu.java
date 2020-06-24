package com.senla.bookstore.main.view;

import com.senla.bookstore.main.view.enumeration.MenuNames;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private MenuNames menuNames;
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public Menu(MenuNames menuNames) {
        this.menuNames = menuNames;
    }

    public MenuNames getMenuNames() {
        return menuNames;
    }

    public void setMenuNames(MenuNames menuNames) {
        this.menuNames = menuNames;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
