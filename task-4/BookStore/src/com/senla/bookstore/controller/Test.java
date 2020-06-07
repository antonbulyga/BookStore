package com.senla.bookstore.controller;


import com.senla.bookstore.model.*;
import com.senla.bookstore.service.Store;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) throws ParseException {
       Store store = new Store();
       store.initializeArraysInStore();
       store.orderProcessing();
       store.bookSort();
       store.orderSort();
       store.requestSort();
       store.showInfoForUser();

    }

    public StockLevel[] initStockLevel() {
        StockLevelService stockLevelService = new StockLevelService();
        StockLevel[] arrayOfStockLevels = new StockLevel[0];
        StockLevel stockLevel1 = new StockLevel(1, 10);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel1);
        StockLevel stockLevel2 = new StockLevel(2, 11);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel2);
        StockLevel stockLevel3 = new StockLevel(3, 0);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel3);
        StockLevel stockLevel4 = new StockLevel(4, 2);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel4);
        StockLevel stockLevel5 = new StockLevel(5, 3);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel5);
        StockLevel stockLevel6 = new StockLevel(6, 1);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel6);
        StockLevel stockLevel7 = new StockLevel(7, 15);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel7);
        StockLevel stockLevel8 = new StockLevel(8, 25);
        arrayOfStockLevels = stockLevelService.addStocklevel(arrayOfStockLevels, stockLevel8);

        return arrayOfStockLevels;
    }

    public Book[] initBook(StockLevel[] stockLevels) {
        BookService bookService = new BookService();
        Book[] arrayOfBookInStorehouse = new Book[0];
        Book book1 = new Book( 1,"Война и мир", "Лев Толстой", 4, BookStatus.IN_STOCK, stockLevels[0], null, LocalDate.of(2020,04,06), LocalDate.of(2012,05,20));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book1);
        Book book2 = new Book(2,"Преступление и наказание", "Федор Достоевский", 3.5, BookStatus.IN_STOCK, stockLevels[1], null, LocalDate.of(2020,06,5), LocalDate.of(1996,05,25));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book2);
        Book book3 = new Book(3,"Мертвые души", "Гоголь Николай", 4, BookStatus.OUT_OF_STOCK, stockLevels[2], null, LocalDate.of(2020,05,13), LocalDate.of(1998,01,05));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book3);
        Book book4 = new Book(4,"Война и мир", "Лев Толстой", 4, BookStatus.IN_STOCK,stockLevels[3], null, LocalDate.of(2019,03,04), LocalDate.of(2006,02,24));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book4);
        Book book5 = new Book(5,"Руслан и Людмила", "Александр Пушкин", 5, BookStatus.IN_STOCK, stockLevels[4], null, LocalDate.of(2020,06,14), LocalDate.of(2011,06,01));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book5);
        Book book6 = new Book(6,"Введение в психоанализ", "Зигмунд Фрейд", 7, BookStatus.OUT_OF_STOCK, stockLevels[5], null, LocalDate.of(2018,03,13), LocalDate.of(2020,01,4));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book6);
        Book book7 = new Book(7,"Психология влияния", "Роберт Чалдини", 6, BookStatus.IN_STOCK, stockLevels[6], null,LocalDate.of (2020,04,06), LocalDate.of(2001,02,18));
        arrayOfBookInStorehouse = bookService.addBook(arrayOfBookInStorehouse, book7);
        Book book8 = new Book(8,"Как перестать беспокоитьс и начать жить", "Дейл Карнеги", 7, BookStatus.IN_STOCK, stockLevels[7], null, LocalDate.of(2017,02,27), LocalDate.of(2000,02,19));
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

}
