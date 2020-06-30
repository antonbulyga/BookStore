package main.java.com.senla.view;

import java.io.IOException;
import java.util.List;

public class Navigator {
    private static Navigator instance;
    private Menu currentMenu;

    private Navigator() {

    }

    public static Navigator getInstance() {
        if(instance == null){
            instance = new Navigator();
        }
        return instance;
    }

    public void printMenu(){
        System.out.println(currentMenu.getMenuNames());
        List<MenuItem> menuItems = currentMenu.getMenuItems();
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(i + " " + currentMenu.getMenuItems().get(i).getTitleOfMenuItems());
        }
    }
    public void navigate(int index) throws IOException {
        List<MenuItem> menuItems = currentMenu.getMenuItems();
        if ((index < menuItems.size()) && (index >= 0)) {
            if (menuItems.get(index).getAction() == null) {
                currentMenu = menuItems.get(index).getNextMenu();
            } else {
                menuItems.get(index).doAction();
            }
        }
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
