package com.senla.bookstore.view;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private Enum name;
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public Menu(Enum name) {
        this.name = name;
    }

    public Enum getName() {
        return name;
    }

    public void setName(Enum name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void add(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

}
