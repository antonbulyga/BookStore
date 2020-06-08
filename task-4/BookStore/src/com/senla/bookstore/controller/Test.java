package com.senla.bookstore.controller;


import com.senla.bookstore.model.*;
import com.senla.bookstore.service.Store;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
       Store store = new Store();
       Stock stock = new Stock();
       OrderService orderService = new OrderService();
       RequestForBookService requestForBookService = new RequestForBookService();
       StoreService storeService = new StoreService();
       BookService bookService = new BookService();
       StockLevelService stockLevelService = new StockLevelService();
       CustomerService customerService = new CustomerService();
       Book[] arrayOfBookInStore = initBook(bookService,store);
       store.setArrayOfBooksInStorehouse(arrayOfBookInStore);
       StockLevel[] stockLevels = initStockLevel(arrayOfBookInStore, stockLevelService);
       stock.setArrayOfStockLevels(stockLevels);
       Customer[] arrayOfCustomers = initCustomer(customerService, store);
       Book[] arrayOfBooksForFirstOrder = addBookForFirstOrder(arrayOfBookInStore);
       Book[] arrayOfBooksForSecondOrder = addBookForSecondOrder(arrayOfBookInStore);
       Order order1 = createFirstOrder(arrayOfBooksForFirstOrder, arrayOfCustomers[1], orderService);

       Order[] arrayOfOrders;
       arrayOfOrders = orderService.addOrder(store, order1);
       Order order2 = createSecondOrder(arrayOfBooksForSecondOrder, arrayOfCustomers[1],orderService);
        arrayOfOrders = orderService.addOrder(store, order2);
        LocalDate date1 = LocalDate.of(2020, 06, 01);
        LocalDate date2 = LocalDate.of(2020, 06, 07);

       // storeService.deleteOrder(order1);

       store.setArrayOfOrders(arrayOfOrders);
       storeService.checkingInStockStatus(order1, store);
       storeService.orderExecution(order1, stockLevels);
       storeService.arriveBookToStock(arrayOfBookInStore[3], stockLevels);
       storeService.completingRequestAfterArrivingNewBook(arrayOfBookInStore[2], stockLevels);

        requestForBookService.createRequestForBook(arrayOfBookInStore[2],order1);
        storeService.checkingInStockStatus(order2, store);
        storeService.orderExecution(order2, stockLevels);
        bookService.bookSort();
        orderService.orderSort();
        storeService.requestSort();
        storeService.sumOfMoneyPerPeriodOfTime(store.getArrayOfOrders(), date1, date2);
        orderService.showDetailsOfOrder(order1);
        orderService.showDetailsOfOrder(order2);

        storeService.countOfDoneOrdersByPeriodOfTime(store.getArrayOfOrders(), date1, date2);
        storeService.showUnsoldBooksMoreThanSixMonth(store.getArrayOfBooksInStorehouse());
    }
    public static Book[] initBook(BookService bookService, Store store) {
        Book[] arrayOfBookInStorehouse = new Book[0];
        Book book1 = new Book( 1,"Война и мир", "Лев Толстой", 4, BookStatus.IN_STOCK, null, LocalDate.of(2013,04,06), LocalDate.of(2012,05,20));
        arrayOfBookInStorehouse = bookService.addBook(store, book1);
        Book book2 = new Book(2,"Преступление и наказание", "Федор Достоевский", 3.5, BookStatus.IN_STOCK, null, LocalDate.of(2020,06,5), LocalDate.of(1996,05,25));
        arrayOfBookInStorehouse = bookService.addBook(store, book2);
        Book book3 = new Book(3,"Мертвые души", "Гоголь Николай", 4, BookStatus.OUT_OF_STOCK,  null, LocalDate.of(2015,05,13), LocalDate.of(1998,01,05));
        arrayOfBookInStorehouse = bookService.addBook(store, book3);
        Book book4 = new Book(4,"Война и мир", "Лев Толстой", 4, BookStatus.IN_STOCK,null, LocalDate.of(2019,03,04), LocalDate.of(2006,02,24));
        arrayOfBookInStorehouse = bookService.addBook(store, book4);
        Book book5 = new Book(5,"Руслан и Людмила", "Александр Пушкин", 5, BookStatus.IN_STOCK, null, LocalDate.of(2018,06,14), LocalDate.of(2011,06,01));
        arrayOfBookInStorehouse = bookService.addBook(store, book5);
        Book book6 = new Book(6,"Введение в психоанализ", "Зигмунд Фрейд", 7, BookStatus.OUT_OF_STOCK,null, LocalDate.of(2018,03,13), LocalDate.of(2020,01,4));
        arrayOfBookInStorehouse = bookService.addBook(store, book6);
        Book book7 = new Book(7,"Психология влияния", "Роберт Чалдини", 6, BookStatus.IN_STOCK,  null,LocalDate.of (2017,04,06), LocalDate.of(2001,02,18));
        arrayOfBookInStorehouse = bookService.addBook(store, book7);
        Book book8 = new Book(8,"Как перестать беспокоитьс и начать жить", "Дейл Карнеги", 7, BookStatus.IN_STOCK, null, LocalDate.of(2017,02,27), LocalDate.of(2000,02,19));
        arrayOfBookInStorehouse = bookService.addBook(store, book8);
        return  arrayOfBookInStorehouse;

    }

    public static Order createFirstOrder(Book[] arrayOfBooksForFirstOrder, Customer customer, OrderService orderService) {
        Order order1 = orderService.createOrder(arrayOfBooksForFirstOrder, customer);
        return order1;
    }

    public static Order createSecondOrder(Book[] arrayOfBooksForSecondOrder, Customer customer, OrderService orderService){
        Order order2 = orderService.createOrder(arrayOfBooksForSecondOrder, customer);
        return order2;
    }

    public static StockLevel[] initStockLevel(Book[] book, StockLevelService stockLevelService) {
        StockLevel[] arrayOfStockLevels = new StockLevel[0];
        StockLevel stockLevel1 = new StockLevel(book[0], 10);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel1);
        StockLevel stockLevel2 = new StockLevel(book[1], 11);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel2);
        StockLevel stockLevel3 = new StockLevel(book[2], 0);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel3);
        StockLevel stockLevel4 = new StockLevel(book[3], 2);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel4);
        StockLevel stockLevel5 = new StockLevel(book[4], 3);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel5);
        StockLevel stockLevel6 = new StockLevel(book[5], 1);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel6);
        StockLevel stockLevel7 = new StockLevel(book[6], 15);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel7);
        StockLevel stockLevel8 = new StockLevel(book[7], 25);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel8);
        return arrayOfStockLevels;

    }

    public static Customer[] initCustomer(CustomerService customerService, Store store){
        Customer[] arrayOfCustomer;
        Customer customer1 = new Customer(1, 32, "Иван Петров");
        arrayOfCustomer = customerService.addCustomer(store, customer1);
        Customer customer2 = new Customer(2, 23, "Мария Чернобровина");
        arrayOfCustomer = customerService.addCustomer(store, customer2);
        Customer customer3 = new Customer(3, 33, "Антон Чабанов");
        arrayOfCustomer = customerService.addCustomer(store, customer3);
        return arrayOfCustomer;
    }

    public static Book[] addBookForFirstOrder(Book[] arrayOfBookInStorehouse) {
        Book[] arrayOfBookForFirstOrder = new Book[4];
        arrayOfBookForFirstOrder[0] = arrayOfBookInStorehouse[0];
        arrayOfBookForFirstOrder[1] = arrayOfBookInStorehouse[4];
        arrayOfBookForFirstOrder[2] = arrayOfBookInStorehouse[2];
        arrayOfBookForFirstOrder[3] = arrayOfBookInStorehouse[3];
        return arrayOfBookForFirstOrder;
    }

    public static Book[]  addBookForSecondOrder(Book[] arrayOfBookInStorehouse){
        Book[] arrayOfBookForSecondOrder = new Book[2];
        arrayOfBookForSecondOrder[0] = arrayOfBookInStorehouse[0];
        arrayOfBookForSecondOrder[1] = arrayOfBookInStorehouse[2];
        return arrayOfBookForSecondOrder;
    }

}
