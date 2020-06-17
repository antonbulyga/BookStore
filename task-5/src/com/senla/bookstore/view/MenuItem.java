package com.senla.bookstore.view;

import java.io.IOException;

public class MenuItem  {
    private TitleOfMenuItems titleOfMenuItems;
    private IAction action;
    private Menu nextMenu;

    public void doAction() throws IOException {
        action.execute();
    }

    public MenuItem(TitleOfMenuItems titleOfMenuItems, IAction action, Menu nextMenu){
        this.titleOfMenuItems = titleOfMenuItems;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public TitleOfMenuItems getTitleOfMenuItems() {
        return titleOfMenuItems;
    }

    public void setTitleOfMenuItems(TitleOfMenuItems titleOfMenuItems) {
        this.titleOfMenuItems = titleOfMenuItems;
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
