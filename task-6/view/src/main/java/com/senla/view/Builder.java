package main.java.com.senla.view;

import main.java.com.senla.view.actions.*;
import main.java.com.senla.view.enumeration.MenuNames;
import main.java.com.senla.view.enumeration.TitleOfMenuItem;

import java.util.ArrayList;
import java.util.List;

    public final class Builder {
        private static Builder instance;
        private Menu rootMenu;

        private Builder() {

        }
        public void buildMenu(){
         Menu bookMenu = buildBookMenu();
         Menu orderMenu = buildOrderMenu();
         Menu customerMenu = buildCustomerMenu();
         Menu requestMenu = buildRequestMenu();
         rootMenu = buildRootMenu(bookMenu,orderMenu,customerMenu,requestMenu);
        }

        public static Builder getInstance() {
            if(instance == null ){
                instance = new Builder();
            }
            return instance;
        }

        public Menu buildBookMenu(){
            Menu booksMenu = new Menu(MenuNames.BOOK_MENU);
            List<MenuItem> listOfMenuItemsOfBook = new ArrayList<>();
            listOfMenuItemsOfBook.add(new MenuItem(TitleOfMenuItem.CREATE_BOOK, new ActionCreateBook(), booksMenu));
            listOfMenuItemsOfBook.add(new MenuItem(TitleOfMenuItem.SHOW_BOOK_SORTED_BY_AUTHOR, new ActionSortBookByAuthor(), booksMenu));
            listOfMenuItemsOfBook.add(new MenuItem(TitleOfMenuItem.SORT_BOOKS_BY_AVAILABILITY_IN_STOCK, new ActionShowBooksInStock(), booksMenu));
            listOfMenuItemsOfBook.add(new MenuItem(TitleOfMenuItem.SHOW_BOOKS_IN_STOCK, new ActionShowBooksInStock(), booksMenu));
            listOfMenuItemsOfBook.add(new MenuItem(TitleOfMenuItem.SORT_BOOKS_BY_DATE_OF_ARRIVE, new ActionSortBookByDateArrive(), booksMenu));
            listOfMenuItemsOfBook.add(new MenuItem(TitleOfMenuItem.SORT_BOOK_BY_PUBLICATION_DATE, new ActionSortBookByPublicationDate(), booksMenu));
            listOfMenuItemsOfBook.add(new MenuItem(TitleOfMenuItem.SORT_BOOK_BY_PRICE, new ActionSortBookByPrice(), booksMenu));
            listOfMenuItemsOfBook.add(new MenuItem(TitleOfMenuItem.SHOW_UNSOLD_BOOKS_MORE_THAN_SIX_MONTH, new ActionShowUnsoldBooksMoreThanSixMonth(), booksMenu));
            listOfMenuItemsOfBook.add(new MenuItem(TitleOfMenuItem.IMPORT_BOOK, new ActionImportBook(), booksMenu));
            listOfMenuItemsOfBook.add(new MenuItem(TitleOfMenuItem.EXPORT_BOOK, new ActionExportBook(), booksMenu));
            listOfMenuItemsOfBook.add(new MenuItem(TitleOfMenuItem.BACK_TO_THE_MAIN_MENU, null, rootMenu));
            booksMenu.setMenuItems(listOfMenuItemsOfBook);
            return booksMenu;
        }

        public Menu buildOrderMenu(){
            Menu orderMenu = new Menu(MenuNames.ORDER_MENU);
            List<MenuItem> listOfOrdersItemsOfOrder = new ArrayList<>();
            listOfOrdersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.CREATE_ORDER,new ActionCreateOrder(),orderMenu));
            listOfOrdersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.COUNT_OF_DONE_ORDERS_BY_PERIOD_OF_TIME,new ActionCountOfDoneOrdersByPeriodOfTime(),orderMenu));
            listOfOrdersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.SHOW_DETAILS_OF_ORDER, new ActionShowDetailsOfOrder(),orderMenu));
            listOfOrdersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.SHOW_LIST_OF_ORDERS,new ActionShowListOfOrders(),orderMenu));
            listOfOrdersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.SORT_ORDERS_BY_DATE_OF_DONE,new ActionSortOrdersByDateOfDone(),orderMenu));
            listOfOrdersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.SORT_ORDERS_BY_PRICE,new ActionSortOrdersByPrice(),orderMenu));
            listOfOrdersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.SORT_ORDERS_BY_STATUS,new ActionSortOrdersByStatus(),orderMenu));
            listOfOrdersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.SHOW_SUM_OF_MONEY_PER_PERIOD_OF_TIME,new ActionSumOfMoneyPerPeriodOfTime(),orderMenu));
            listOfOrdersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.IMPORT_ORDER,new ActionImportOrder(),orderMenu));
            listOfOrdersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.BACK_TO_THE_MAIN_MENU, null, rootMenu));
            orderMenu.setMenuItems(listOfOrdersItemsOfOrder);
            return orderMenu;
        }

        public Menu buildCustomerMenu(){
            Menu customerMenu = new Menu(MenuNames.CUSTOMER_MENU);
            List<MenuItem> listOfCustomersItemsOfOrder = new ArrayList<>();
            listOfCustomersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.CREATE_CUSTOMER,new ActionCreateCustomer(),customerMenu));
            listOfCustomersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.EXPORT_CUSTOMER,new ActionExportCustomer(),customerMenu));
            listOfCustomersItemsOfOrder.add(new MenuItem(TitleOfMenuItem.BACK_TO_THE_MAIN_MENU, null, rootMenu));
            customerMenu.setMenuItems(listOfCustomersItemsOfOrder);
            return customerMenu;
        }

        public Menu buildRequestMenu(){
            Menu requestMenu = new Menu(MenuNames.REQUEST_FOR_BOOK_MENU);
            List<MenuItem> listOfRequestsForBookItemsOfOrder = new ArrayList<>();
            listOfRequestsForBookItemsOfOrder.add(new MenuItem(TitleOfMenuItem.CREATE_REQUEST_FOR_BOOK,new ActionCreateRequestForBook(),requestMenu));
            listOfRequestsForBookItemsOfOrder.add(new MenuItem(TitleOfMenuItem.SHOW_LIST_OF_REQUEST_FOR_BOOKS,new ActionShowListOfRequestsForBooks(),requestMenu));
            listOfRequestsForBookItemsOfOrder.add(new MenuItem(TitleOfMenuItem.SORT_REQUEST_FOR_BOOKS_BY_ALPHABET,new ActionSortRequestByAlphabet(),requestMenu));
            listOfRequestsForBookItemsOfOrder.add(new MenuItem(TitleOfMenuItem.SORT_REQUEST_FOR_BOOKS_BY_COUNT,new ActionSortRequestByCount(),requestMenu));
            listOfRequestsForBookItemsOfOrder.add(new MenuItem(TitleOfMenuItem.IMPORT_REQUEST_FOR_BOOK,new ActionImportRequestForBook(),requestMenu));
            listOfRequestsForBookItemsOfOrder.add(new MenuItem(TitleOfMenuItem.EXPORT_REQUEST_FOR_BOOK,new ActionExportRequestForBook(),requestMenu));
            listOfRequestsForBookItemsOfOrder.add(new MenuItem(TitleOfMenuItem.BACK_TO_THE_MAIN_MENU, null, rootMenu));
            requestMenu.setMenuItems(listOfRequestsForBookItemsOfOrder);
            return requestMenu;
        }

        public Menu buildRootMenu(Menu booksMenu, Menu orderMenu, Menu customerMenu, Menu requestMenu){
            Menu rootMenu = new Menu(MenuNames.MAIN_MENU);
            List<MenuItem> rootMenuItems = new ArrayList<>();
            rootMenuItems.add(new MenuItem(TitleOfMenuItem.BOOK_MENU ,null,booksMenu));
            rootMenuItems.add(new MenuItem(TitleOfMenuItem.ORDER_MENU, null, orderMenu));
            rootMenuItems.add(new MenuItem(TitleOfMenuItem.CUSTOMER_MENU, null, customerMenu));
            rootMenuItems.add(new MenuItem(TitleOfMenuItem.REQUEST_FOR_BOOK_MENU, null, requestMenu));
            rootMenu.setMenuItems(rootMenuItems);
            return rootMenu;
        }

        public Menu getRootMenu() {
            return rootMenu;
        }

        public void setRootMenu(Menu rootMenu) {
            this.rootMenu = rootMenu;
        }
    }
