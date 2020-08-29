package main.java.com.senla.model.DAO;

import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.repository.RequestForBookRepositoryImpl;

import java.sql.Connection;
import java.util.List;

public class CallConnector {
    public static void main(String[] args) {
     Connection connection = MysqlConnect.getInstance().conn;
     if(connection != null){
         System.out.println("Ура");
     }

        /*BookRepositoryImpl bookRepository = new BookRepositoryImpl();
        Book book = bookRepository.read(5);
        book.setAuthor("FFFFFFFFFFFFFFFFFFFFFFFFF");
        book.setPublicationDate(LocalDate.parse("1900-01-01"));
        bookRepository.update(book);*/


      /*  CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Customer customer = customerRepository.read(2);
        customer.setName("Владик Легенький");
        customerRepository.update(customer);
        customerRepository.create(customer);
        customerRepository.delete(customer);*/

       /* OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();
        Order order = orderRepository.read(1);
        List<Order> orderList = orderRepository.getAll();*/




        /*Customer customer = CustomerRepositoryImpl.getInstance().read(2);
        customer.setAge(150);
        CustomerRepositoryImpl.getInstance().update(customer);
        CustomerRepositoryImpl.getInstance().create(customer);
        CustomerRepositoryImpl.getInstance().delete(customer);*/


        RequestForBookRepositoryImpl requestForBookRepository = new RequestForBookRepositoryImpl();
        RequestForBook requestForBook = requestForBookRepository.read(1);
        requestForBookRepository.create(requestForBook);
        requestForBookRepository.update(requestForBook);
        List<RequestForBook> requestForBooks = requestForBookRepository.getAll();



    }
}
