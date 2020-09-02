package main.java.com.senla.model.DAO;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.repository.BookRepositoryImpl;
import main.java.com.senla.model.repository.CustomerRepositoryImpl;
import main.java.com.senla.model.repository.OrderRepositoryImpl;
import main.java.com.senla.model.repository.RequestForBookRepositoryImpl;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class CallConnector {
    public static void main(String[] args) {
     Connection connection = MysqlConnect.getInstance().conn;
     if(connection != null){
         System.out.println("Ура");
     }


        /*Book book = BookRepositoryImpl.getInstance().read(5);
        book.setAuthor("FFFFFFFFFFFFFFFFFFFFFFFFF");
        book.setPublicationDate(LocalDate.parse("1900-01-01"));
        BookRepositoryImpl.getInstance().update(book);*/


        /*CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Customer customer = customerRepository.read(2);
        customer.setName("Владик Легенький");
        customerRepository.update(customer);
        customerRepository.create(customer);
        customerRepository.delete(customer);
*/
        Order order = OrderRepositoryImpl.getInstance().read(1);




        /*Customer customer = CustomerRepositoryImpl.getInstance().read(2);
        customer.setAge(150);
        CustomerRepositoryImpl.getInstance().update(customer);
        CustomerRepositoryImpl.getInstance().create(customer);
        CustomerRepositoryImpl.getInstance().delete(customer);*/


        //RequestForBook requestForBook = RequestForBookRepositoryImpl.getInstance().read(1);
        //requestForBook.setBook(book);
        //requestForBookRepository.update(requestForBook);
        //List<RequestForBook> requestForBooks = requestForBookRepository.getAll();


    }
}
