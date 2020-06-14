package com.senla.bookstore.view;

import com.senla.bookstore.view.Actions.ActionShowBooksInStock;
import com.senla.bookstore.view.Actions.ActionSortBookByAuthor;
import com.senla.bookstore.view.Actions.ActionSortBookByDateArrive;
import com.senla.bookstore.view.Actions.ActionSortBookByPrice;

import java.util.ArrayList;
import java.util.List;

    public class Builder {
        private static Menu rootMenu;

        private Builder() {

        }
        public void buildMenu(){
            List<MenuItem> arrayOfMenuItems = new ArrayList<>();

            MenuItem menuItem1 = new MenuItem(TitleOfMenuItems.SHOW_BOOK_SORTED_BY_AUTHOR, new ActionSortBookByPrice(), null);
            MenuItem menuItem2 = new MenuItem(TitleOfMenuItems.SORT_BOOKS_BY_AVAILABILITY_IN_STOCK, new ActionShowBooksInStock(), null);
            MenuItem menuItem3 = new MenuItem(TitleOfMenuItems.SORT_BOOKS_BY_DATE_OF_ARRIVE, new ActionSortBookByDateArrive(), null);
            MenuItem menuItem4 = new MenuItem(TitleOfMenuItems.SHOW_BOOK_SORTED_BY_AUTHOR, new ActionSortBookByAuthor(), null);


            Menu level1 = new Menu("Books");
            arrayOfMenuItems.add(menuItem1);
            arrayOfMenuItems.add(menuItem2);
            arrayOfMenuItems.add(menuItem3);
            arrayOfMenuItems.add(menuItem4);

            level1.setMenuItems(arrayOfMenuItems);
            setRootMenu(level1);

            Menu level2 = new Menu("  ");
            menuItem1.setNextMenu(level2);
        }

        public static Menu getRootMenu() {
            if(rootMenu == null ){
                rootMenu = new Menu("Menu");
            }
            return rootMenu;
        }

        public static void setRootMenu(Menu rootMenu) {
            Builder.rootMenu = rootMenu;
        }


}
