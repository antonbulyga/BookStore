package com.senla.bookstore.main.model;


import com.senla.bookstore.main.view.MenuController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MenuController menuController = new MenuController();
        menuController.run();
    }
      /* List<Book> arrayOfBookInStore = initBook();
       List<StockLevel> stockLevels = initStockLevel(arrayOfBookInStore);
       Stock stock = new Stock(stockLevels);
       List<Customer> arrayOfCustomers = initCustomer();
      Store store = new Store(stock, arrayOfBookInStore,arrayOfCustomers);
     OrderService orderService = orderService.getInstance();
       orderService.setStore(store);
       RequestForBookService requestForBookService = new RequestForBookService();
       StoreService storeService = new StoreService();
       BookService bookService = new BookService();
       MenuController menuController = new MenuController();

       store.setListOfBooksInStorehouse(arrayOfBookInStore);

       stock.setArrayOfStockLevels(stockLevels);
       setBookStatus(stock);

       List<Book> arrayOfBooksForFirstOrder = addBookForFirstOrder(arrayOfBookInStore);
       List<Book> arrayOfBooksForSecondOrder = addBookForSecondOrder(arrayOfBookInStore);
       List<Book> arrayOfBooksForThirdOrder = addBookForThirdOrder(arrayOfBookInStore);
       Order order1 = createFirstOrder(arrayOfBooksForFirstOrder, arrayOfCustomers.get(1), orderService);
        List<Order> arrayOfOrders = new ArrayList<>();
        arrayOfOrders.add(order1);
        store.setListOfOrders(arrayOfOrders);
        Order order2 = createSecondOrder(arrayOfBooksForSecondOrder, arrayOfCustomers.get(0),orderService);
        arrayOfOrders.add(order2);
        store.setListOfOrders(arrayOfOrders);
        Order order3 = createThirdOrder(arrayOfBooksForThirdOrder, arrayOfCustomers.get(2), orderService);
        arrayOfOrders.add(order3);
        store.setListOfOrders(arrayOfOrders);

        LocalDate date1 = LocalDate.of(2020, 06, 01);
        LocalDate date2 = LocalDate.of(2020, 06, 07);

       // storeService.deleteOrder(order1);

       storeService.addOrderToStore(order1, store);
       storeService.executeOrder(order1, stock);
       storeService.arriveBookToStock(arrayOfBookInStore.get(3), stock);
       storeService.completingRequestAfterArrivingNewBook(arrayOfBookInStore.get(2), stock);

        requestForBookService.createRequestForBook(arrayOfBookInStore.get(2),order1);
        requestForBookService.showListOfRequestsForBooks();
        storeService.addOrderToStore(order2, store);
        storeService.executeOrder(order2, stock);
        bookService.showBooksInStock();
        bookService.bookSort();
        orderService.showListOfOrders();
        orderService.orderSort();
        requestForBookService.requestSort();
        storeService.sumOfMoneyPerPeriodOfTime(store.getListOfOrders(), date1, date2);
        orderService.showDetailsOfOrder(order1);
        orderService.showDetailsOfOrder(order2);

        orderService.countOfDoneOrdersByPeriodOfTime(store.getListOfOrders(), date1, date2);
        storeService.showUnsoldBooksMoreThanSixMonth(store);
        menuController.run();

    }
    public static List<Book> initBook() {
        List<Book> arrayOfBookInStorehouse = new ArrayList<Book>();
        arrayOfBookInStorehouse.add(new Book( 1,"Война и мир", "Лев Толстой", 4, null, null, LocalDate.of(2020,04,06), LocalDate.of(2012,05,20)));
        arrayOfBookInStorehouse.add(new Book(2,"Преступление и наказание", "Федор Достоевский", 3.5, null, null, LocalDate.of(2020,06,5), LocalDate.of(1996,05,25)));
        arrayOfBookInStorehouse.add(new Book(3,"Мертвые души", "Гоголь Николай", 4, null,  null, LocalDate.of(2015,05,13), LocalDate.of(1998,01,05)));
        arrayOfBookInStorehouse.add(new Book(4,"Война и мир", "Лев Толстой", 4, null,null, LocalDate.of(2020,03,04), LocalDate.of(2006,02,24)));
        arrayOfBookInStorehouse.add(new Book(5,"Руслан и Людмила", "Александр Пушкин", 5, null, null, LocalDate.of(2018,06,14), LocalDate.of(2011,06,01)));
        arrayOfBookInStorehouse.add(new Book(6,"Введение в психоанализ", "Зигмунд Фрейд", 7, null,null, LocalDate.of(2018,03,13), LocalDate.of(2020,01,4)));
        arrayOfBookInStorehouse.add(new Book(7,"Психология влияния", "Роберт Чалдини", 6, null,  null,LocalDate.of (2017,04,06), LocalDate.of(2001,02,18)));
        arrayOfBookInStorehouse.add(new Book(8,"Как перестать беспокоитьс и начать жить", "Дейл Карнеги", 7, null, null, LocalDate.of(2020,02,27), LocalDate.of(2000,02,19)));
        return  arrayOfBookInStorehouse;

    }

    public static List<StockLevel> initStockLevel(List<Book> books) {
        List<StockLevel> arrayOfStockLevels = new ArrayList<StockLevel>();
        arrayOfStockLevels.add(new StockLevel(books.get(0), 10));
        arrayOfStockLevels.add(new StockLevel(books.get(1), 11));
        arrayOfStockLevels.add(new StockLevel(books.get(2), 10));
        arrayOfStockLevels.add(new StockLevel(books.get(3), 5));
        arrayOfStockLevels.add(new StockLevel(books.get(4), 0));
        arrayOfStockLevels.add(new StockLevel(books.get(5), 3));
        arrayOfStockLevels.add(new StockLevel(books.get(6), 15));
        arrayOfStockLevels.add(new StockLevel(books.get(7), 25));
        return arrayOfStockLevels;

    }

    public static void setBookStatus(Stock stock){
        List<StockLevel> stockLevels = stock.getArrayOfStockLevels();

        for (int i = 0; i < stockLevels.size(); i++) {
            if(stockLevels.get(i).getCount()>0){
                stockLevels.get(i).getBook().setBookStatus(BookStatus.IN_STOCK);

            }
            else {
                stockLevels.get(i).getBook().setBookStatus(BookStatus.OUT_OF_STOCK);
            }
        }

    }


    public static List<Customer> initCustomer(){
        List<Customer> arrayOfCustomer = new ArrayList<>();
        arrayOfCustomer.add(new Customer(1, 32, "Иван Петров"));
        arrayOfCustomer.add(new Customer(2, 23, "Мария Чернобровина"));
        arrayOfCustomer.add(new Customer(3, 33, "Антон Чабанов"));
        return arrayOfCustomer;
    }

    public static List<Book> addBookForFirstOrder(List<Book> arrayOfBookInStorehouse) {
        List<Book> arrayOfBookForFirstOrder = new ArrayList<>();
        arrayOfBookForFirstOrder.add(arrayOfBookInStorehouse.get(0));
        arrayOfBookForFirstOrder.add(arrayOfBookInStorehouse.get(4));
        arrayOfBookForFirstOrder.add(arrayOfBookInStorehouse.get(2));
        arrayOfBookForFirstOrder.add(arrayOfBookInStorehouse.get(3));
        return arrayOfBookForFirstOrder;
    }

    public static List<Book> addBookForSecondOrder(List<Book> arrayOfBookInStorehouse){
        List<Book> arrayOfBookForSecondOrder = new ArrayList<>();
        arrayOfBookForSecondOrder.add(arrayOfBookInStorehouse.get(0));
        arrayOfBookForSecondOrder.add(arrayOfBookInStorehouse.get(2));
        return arrayOfBookForSecondOrder;
    }

    public static List<Book> addBookForThirdOrder(List<Book> arrayOfBookInStorehouse){
        List<Book> arrayOfBookForThirdOrder = new ArrayList<>();
        arrayOfBookForThirdOrder.add(arrayOfBookInStorehouse.get(0));
        arrayOfBookForThirdOrder.add(arrayOfBookInStorehouse.get(2));
        arrayOfBookForThirdOrder.add(arrayOfBookInStorehouse.get(3));
        arrayOfBookForThirdOrder.add(arrayOfBookInStorehouse.get(4));
        arrayOfBookForThirdOrder.add(arrayOfBookInStorehouse.get(5));
        return arrayOfBookForThirdOrder;
    }

    public static Order createFirstOrder(List<Book> arrayOfBooksForFirstOrder, Customer customer, OrderService orderService) {
        Order order1 = orderService.createOrder(arrayOfBooksForFirstOrder, customer);
        return order1;
    }

    public static Order createSecondOrder(List<Book> arrayOfBooksForSecondOrder, Customer customer, OrderService orderService){
        Order order2 = orderService.createOrder(arrayOfBooksForSecondOrder, customer);
        return order2;
    }

    public static Order createThirdOrder(List<Book> arrayOfBooksForThirdOrder, Customer customer, OrderService orderService){
        Order order3 = orderService.createOrder(arrayOfBooksForThirdOrder, customer);
        return order3;
    }*/

}
