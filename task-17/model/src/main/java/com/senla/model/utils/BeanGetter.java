package com.senla.model.utils;

import com.senla.model.сontrollers.BookController;
import com.senla.model.сontrollers.CustomerController;
import com.senla.model.сontrollers.OrderController;
import com.senla.model.сontrollers.RequestForBookController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class BeanGetter {
    private static BeanGetter instance;
    private static final  ApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    private BeanGetter(){

    }

    public static BeanGetter getInstance() {
        if(instance == null) {
            instance = new BeanGetter();
        }
        return instance;
    }

   public BookController getBookControllerBean(){
       BookController bookController = context.getBean(BookController.class);
       return bookController;
   }

    public CustomerController getCustomerControllerBean(){
        CustomerController customerController = context.getBean(CustomerController.class);
        return customerController;
    }

    public OrderController getOrderControllerBean(){
        OrderController orderController = context.getBean(OrderController.class);
        return orderController;
    }

    public RequestForBookController getRequestForBookControllerBean(){
        RequestForBookController requestForBookController = context.getBean(RequestForBookController.class);
        return requestForBookController;
    }

}
