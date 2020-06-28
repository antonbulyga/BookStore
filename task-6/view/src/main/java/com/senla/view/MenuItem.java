package main.java.com.senla.view;

import main.java.com.senla.view.api.IAction;
import main.java.com.senla.view.enumeration.TitleOfMenuItem;

import java.io.IOException;

public class MenuItem  {
    private TitleOfMenuItem titleOfMenuItem;
    private IAction action;
    private Menu nextMenu;

    public void doAction() throws IOException {
        action.execute();
    }

    public MenuItem(TitleOfMenuItem titleOfMenuItems, IAction action, Menu nextMenu){
        this.titleOfMenuItem = titleOfMenuItems;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public TitleOfMenuItem getTitleOfMenuItems() {
        return titleOfMenuItem;
    }

    public void setTitleOfMenuItems(TitleOfMenuItem titleOfMenuItems) {
        this.titleOfMenuItem = titleOfMenuItems;
    }

    public IAction getAction() {
        return action;
    }

    public void setAction(IAction action) {
        this.action = action;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public void setNextMenu(Menu nextMenu) {
        this.nextMenu = nextMenu;
    }
}
