package com.senla.bookstore.controller;


import com.senla.bookstore.model.*;
import com.senla.bookstore.service.Store;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Test {
    private Order order1;
    private Order order2;
    private Book[] arrayOfBookForFirstOrder;
    private Book[] arrayOfBookForSecondOrder;
    private OrderService orderService = new OrderService();
    private StockLevel[] arrayOfStockLevels;


    public static void main(String[] args) {
       Store store = new Store();
       Test test = new Test();
       Stock stock = new Stock();
       store.initializeArraysInStore();
       Book[] books = store.getArrayOfBooksInStorehouse();
       test.initTest(books);
       Book[] arrayOfBookForFirstOrder = test.getArrayOfBookForFirstOrder();
       Book[] arrayOfBookForSecondOrder = test.getArrayOfBookForSecondOrder();
       Customer[] arrayOfCustomers = store.getArrayOfCustomers();
       test.createOrder(arrayOfBookForFirstOrder, arrayOfBookForSecondOrder, arrayOfCustomers);
       Order order1 = test.getOrder1();
       Order order2 = test.getOrder2();
       StockLevel[] stockLevels = stock.getArrayOfStockLevels();
       store.orderProcessing(order1,order2, stockLevels);
       store.bookSort();
       store.orderSort();
       store.requestSort();
       store.showInfoForUser(order1,order2);
    }

    public void initTest(Book[] books) {
        arrayOfBookForFirstOrder = addBookForFirstOrder(books);
        arrayOfBookForSecondOrder = addBookForSecondOrder(books);
    }

    public void createOrder(Book[] arrayOfBookForFirstOrder, Book[] arrayOfBookForSecondOrder, Customer[] arrayOfCustomers) {
        Store store = new Store();
        Order[] orders = new Order[0];

        order1 = orderService.createOrder(arrayOfBookForFirstOrder, arrayOfCustomers[1]);
        orders = orderService.addOrder(orders, order1);
        order2 = orderService.createOrder(arrayOfBookForSecondOrder, arrayOfCustomers[2]);
        orders = orderService.addOrder(orders, order2);
        store.setArrayOfOrders(orders);
    }

    public StockLevel[] initStockLevel(Book[] book) {
        StockLevelService stockLevelService = new StockLevelService();
        Stock stock = new Stock();
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
        stock.setArrayOfStockLevels(arrayOfStockLevels);
        return arrayOfStockLevels;
    }

    public Book[] initBook() {
        BookService bookService = new BookService();
        Book[] arrayOfBookInStorehouse = new Book[0];
        Book book1 = new Book( 1,"Война и мир", "Лев Толстой", 4, BookStatus.IN_STOCK, null, LocalDate.of(2013,04,06), LocalDate.of(2012,05,20));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book1);
        Book book2 = new Book(2,"Преступление и наказание", "Федор Достоевский", 3.5, BookStatus.IN_STOCK, null, LocalDate.of(2020,06,5), LocalDate.of(1996,05,25));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book2);
        Book book3 = new Book(3,"Мертвые души", "Гоголь Николай", 4, BookStatus.OUT_OF_STOCK,  null, LocalDate.of(2015,05,13), LocalDate.of(1998,01,05));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book3);
        Book book4 = new Book(4,"Война и мир", "Лев Толстой", 4, BookStatus.IN_STOCK,null, LocalDate.of(2019,03,04), LocalDate.of(2006,02,24));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book4);
        Book book5 = new Book(5,"Руслан и Людмила", "Александр Пушкин", 5, BookStatus.IN_STOCK, null, LocalDate.of(2018,06,14), LocalDate.of(2011,06,01));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book5);
        Book book6 = new Book(6,"Введение в психоанализ", "Зигмунд Фрейд", 7, BookStatus.OUT_OF_STOCK,null, LocalDate.of(2018,03,13), LocalDate.of(2020,01,4));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book6);
        Book book7 = new Book(7,"Психология влияния", "Роберт Чалдини", 6, BookStatus.IN_STOCK,  null,LocalDate.of (2017,04,06), LocalDate.of(2001,02,18));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book7);
        Book book8 = new Book(8,"Как перестать беспокоитьс и начать жить", "Дейл Карнеги", 7, BookStatus.IN_STOCK, null, LocalDate.of(2017,02,27), LocalDate.of(2000,02,19));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book8);
        return arrayOfBookInStorehouse;
    }



    public  Customer[] initCustomer(){
        CustomerService customerService = new CustomerService();
        Customer[] arrayOfCustomer = new Customer[0];
        Customer customer1 = new Customer(1, 32, "Иван Петров");
        arrayOfCustomer = customerService.addCustomer(arrayOfCustomer, customer1);
        Customer customer2 = new Customer(2, 23, "Мария Чернобровина");
        arrayOfCustomer = customerService.addCustomer(arrayOfCustomer, customer2);
        Customer customer3 = new Customer(3, 33, "Антон Чабанов");
        arrayOfCustomer = customerService.addCustomer(arrayOfCustomer, customer3);
        return arrayOfCustomer;
    }

    public Book[] addBookForFirstOrder(Book[] arrayOfBookInStorehouse) {
        Book[] arrayOfBookForFirstOrder = new Book[4];
        arrayOfBookForFirstOrder[0] = arrayOfBookInStorehouse[0];
        arrayOfBookForFirstOrder[1] = arrayOfBookInStorehouse[4];
        arrayOfBookForFirstOrder[2] = arrayOfBookInStorehouse[2];
        arrayOfBookForFirstOrder[3] = arrayOfBookInStorehouse[3];

        return arrayOfBookForFirstOrder;
    }

    public Book[]  addBookForSecondOrder(Book[] arrayOfBookInStorehouse){
        Book[] arrayOfBookForSecondOrder = new Book[2];
        arrayOfBookForSecondOrder[0] = arrayOfBookInStorehouse[0];
        arrayOfBookForSecondOrder[1] = arrayOfBookInStorehouse[2];
        return arrayOfBookForSecondOrder;
    }

    public Order getOrder1() {
        return order1;
    }

    public void setOrder1(Order order1) {
        this.order1 = order1;
    }

    public Order getOrder2() {
        return order2;
    }

    public void setOrder2(Order order2) {
        this.order2 = order2;
    }

    public Book[] getArrayOfBookForFirstOrder() {
        return arrayOfBookForFirstOrder;
    }

    public void setArrayOfBookForFirstOrder(Book[] arrayOfBookForFirstOrder) {
        this.arrayOfBookForFirstOrder = arrayOfBookForFirstOrder;
    }

    public Book[] getArrayOfBookForSecondOrder() {
        return arrayOfBookForSecondOrder;
    }

    public void setArrayOfBookForSecondOrder(Book[] arrayOfBookForSecondOrder) {
        this.arrayOfBookForSecondOrder = arrayOfBookForSecondOrder;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public StockLevel[] getArrayOfStockLevels() {
        return arrayOfStockLevels;
    }

    public void setArrayOfStockLevels(StockLevel[] arrayOfStockLevels) {
        this.arrayOfStockLevels = arrayOfStockLevels;
    }
}
