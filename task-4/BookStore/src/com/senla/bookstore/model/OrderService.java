package com.senla.bookstore.model;

import com.senla.bookstore.service.Store;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class OrderService {
    public Order[] addOrder(Store store, Order order){
        Order[] arrayOfOrder = store.getArrayOfOrders();
        Order[] copyOfArray = Arrays.copyOf(arrayOfOrder, arrayOfOrder.length + 1);
        copyOfArray[copyOfArray.length - 1] = order;
        store.setArrayOfOrders(arrayOfOrder);
        return copyOfArray;
    }

   public Order createOrder(Book[] books, Customer customer){
       LocalDate date = LocalDate.now();
        int priceOfOrder = 0;
        Order order = new Order(1, date , null, books, OrderStatus.NEW, customer, 0);
        for (int i = 0; i < books.length; i++) {
           priceOfOrder += books[i].getPrice();
        }
        order.setPriceOfOrder(priceOfOrder);
        return order;
    }


    public void sortOrdersByDateOfDone(Order[] orders) {
        OrderDataOfDoneComparator orderDataOfDoneComparator = new OrderDataOfDoneComparator();
        Arrays.sort(orders, orderDataOfDoneComparator);
        System.out.println("Array of orders sorted by date of done: ");
        for (int i = 0; i < orders.length; i++) {
            System.out.println(orders[i].getId() + " - " + orders[i].getDateOfDoneOrder());
        }
    }

    public void sortOrdersByPrice(Order[] orders) {
        OrderPriceComparator orderPriceComparator = new OrderPriceComparator();
        Arrays.sort(orders, orderPriceComparator);
        System.out.println("Array of orders sorted by price: ");
        for (int i = 0; i < orders.length; i++) {
            System.out.println(orders[i].getId() + " - " +orders[i].getPriceOfOrder());
        }
    }

    public void sortOrdersByStatus(Order[] orders) {
        OrderStatusComparator orderStatusComparator = new OrderStatusComparator();
        Arrays.sort(orders, orderStatusComparator);
        System.out.println("Array of orders sorted by status: ");
        for (int i = 0; i < orders.length; i++) {
            System.out.println(orders[i].getId() + " - " +orders[i].getOrderStatus());
        }
    }

    public void showDetailsOfOrder(Order order){
        System.out.println("Details of the customer: " + "name: " + order.getCustomer().getName() + " age " + order.getCustomer().getAge());
        System.out.println("Details of the order : " + "price of order is " +  order.getPriceOfOrder() + ", date of done order is: " +order.getDateOfDoneOrder());
    }

    public void orderSort(Store store) {
        sortOrdersByDateOfDone(store.getArrayOfOrders());
        sortOrdersByPrice(store.getArrayOfOrders());
        sortOrdersByStatus(store.getArrayOfOrders());
    }

}