package com.senla.model.utils;

import com.senla.model.entity.Store;
import com.senla.model.сontrollers.BookController;
import com.senla.model.сontrollers.CustomerController;
import com.senla.model.сontrollers.OrderController;
import com.senla.model.сontrollers.RequestForBookController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanGetter {
    private static BeanGetter instance;

    private BeanGetter(){

    }

    public static BeanGetter getInstance() {
        if(instance == null) {
            instance = new BeanGetter();
        }
        return instance;
    }

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public  BookController getBookControllerBean(){
        BookController bookController = context.getBean(BookController.class);
        return bookController;
    }

    public CustomerController getCustomerControllerBean(){
        CustomerController customerController = context.getBean(CustomerController.class);
        return customerController;
    }

    public OrderController getOrderControllerBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderController orderController = context.getBean(OrderController.class);
        return orderController;
    }
    public RequestForBookController getRequestForBookControllerBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RequestForBookController requestForBookController = context.getBean(RequestForBookController.class);
        return requestForBookController;
    }

    public WriteObject getWriteObjectBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        WriteObject writeObject = context.getBean(WriteObject.class);
        return writeObject;
    }
    public ReadObject getReadObjectBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ReadObject readObject = context.getBean(ReadObject.class);
        return readObject;
    }

    public Store getStoreObjectBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Store store = context.getBean(Store.class);
        return store;
    }


}
