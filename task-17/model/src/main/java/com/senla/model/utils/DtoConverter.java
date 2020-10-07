package com.senla.model.utils;

import com.senla.model.dto.BookDto;
import com.senla.model.dto.CustomerDto;
import com.senla.model.dto.OrderDto;
import com.senla.model.dto.RequestDto;
import com.senla.model.entity.Book;
import com.senla.model.entity.Customer;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.service.api.CustomerService;
import com.senla.model.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoConverter {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;

    public BookDto bookEntityToDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPrice(book.getPrice());
        bookDto.setArriveDate(book.getArriveDate());
        bookDto.setPublicationDate(book.getPublicationDate());
        if(book.getOrder() != null) {
            bookDto.setOrderId(book.getOrder().getId());
        }
        else {
            bookDto.setOrderId(0);
        }
        return bookDto;
    }

    public Book bookDtoToEntity(BookDto bookDto){
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        book.setArriveDate(bookDto.getArriveDate());
        book.setPublicationDate(bookDto.getPublicationDate());
        if(bookDto.getOrderId() != 0) {
            book.setOrder(orderService.getOrderById(bookDto.getOrderId()));
        }
        else {
            book.setOrder(null);
        }
        return book;
    }

    public RequestDto requestEntityToDto(RequestForBook requestForBook){
        RequestDto requestDto = new RequestDto();
        requestDto.setId(requestForBook.getId());
        requestDto.setTitleOfBook(requestForBook.getTitleOfBook());
        requestDto.setAuthorOfBook(requestForBook.getAuthorOfBook());
        requestDto.setRequestStatus(requestForBook.getRequestStatus());
        if (requestForBook.getOrder() != null) {
            requestDto.setOrderId(requestForBook.getOrder().getId());
        } else {
            requestDto.setOrderId(0);
        }
        return requestDto;
    }

    public RequestForBook requestDtoToEntity(RequestDto requestDto) {
        RequestForBook requestForBook = new RequestForBook();
        requestForBook.setId(requestDto.getId());
        requestForBook.setTitleOfBook(requestDto.getTitleOfBook());
        requestForBook.setAuthorOfBook(requestDto.getAuthorOfBook());
        requestForBook.setRequestStatus(requestDto.getRequestStatus());
        if (requestDto.getOrderId() != 0) {
            requestForBook.setOrder(orderService.getOrderById(requestDto.getOrderId()));
        } else {
            requestForBook.setOrder(null);
        }
        return requestForBook;
    }

    public Customer customerDtoToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setAge(customerDto.getAge());
        return customer;
    }

    public CustomerDto customerEntityToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setAge(customer.getAge());
        return customerDto;
    }

    public OrderDto orderEntityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDateOfOrder(order.getDateOfOrder());
        orderDto.setDateOfDoneOrder(order.getDateOfDoneOrder());
        orderDto.setPriceOfOrder(order.getPriceOfOrder());
        orderDto.setOrderStatus(order.getOrderStatus());
        if (order.getCustomer() != null) {
            orderDto.setCustomerId(order.getCustomer().getId());
        } else {
            orderDto.setCustomerId(0);
        }
        List<Book> books = order.getBooks();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (int j = 0; j < books.size(); j++) {
            BookDto bookDto = new BookDto();
            bookDto.setId(books.get(j).getId());
            bookDto.setTitle(books.get(j).getTitle());
            bookDto.setAuthor(books.get(j).getAuthor());
            bookDto.setPrice(books.get(j).getPrice());
            bookDto.setArriveDate(books.get(j).getArriveDate());
            bookDto.setPublicationDate(books.get(j).getPublicationDate());
            if (books.get(j).getOrder() != null) {
                bookDto.setOrderId(books.get(j).getOrder().getId());
            } else {
                bookDto.setOrderId(0);
            }
            bookDtoList.add(bookDto);
        }
        orderDto.setBooksDto(bookDtoList);

        List<RequestForBook> requestForBookList = order.getListOfRequestForBooks();
        List<RequestDto> dtoRequests = new ArrayList<>();
        for (int k = 0; k < requestForBookList.size(); k++) {
            RequestDto requestDto = new RequestDto();
            requestDto.setId(requestForBookList.get(k).getId());
            requestDto.setTitleOfBook(requestForBookList.get(k).getTitleOfBook());
            requestDto.setAuthorOfBook(requestForBookList.get(k).getAuthorOfBook());
            requestDto.setRequestStatus(requestForBookList.get(k).getRequestStatus());
            if (requestForBookList.get(k).getOrder() != null) {
                requestDto.setOrderId(requestForBookList.get(k).getOrder().getId());
            } else {
                requestDto.setOrderId(0);
            }
            dtoRequests.add(requestDto);
        }
        orderDto.setListOfRequestDto(dtoRequests);
        return orderDto;
    }

    public Order orderDtoToEntity(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setDateOfOrder(orderDto.getDateOfOrder());
        order.setDateOfDoneOrder(orderDto.getDateOfDoneOrder());
        order.setPriceOfOrder(orderDto.getPriceOfOrder());
        order.setOrderStatus(orderDto.getOrderStatus());
        if (orderDto.getCustomerId() != 0) {
            order.setCustomer(customerService.getCustomerById(orderDto.getCustomerId()));
        } else {
            order.setCustomer(null);
        }
        List<BookDto> booksDto = orderDto.getBooksDto();
        List<Book> books = new ArrayList<>();
        for (int j = 0; j < booksDto.size(); j++) {
            Book book = new Book();
            book.setId(booksDto.get(j).getId());
            book.setTitle(booksDto.get(j).getTitle());
            book.setAuthor(booksDto.get(j).getAuthor());
            book.setPrice(booksDto.get(j).getPrice());
            book.setArriveDate(booksDto.get(j).getArriveDate());
            book.setPublicationDate(booksDto.get(j).getPublicationDate());
            Order newOrder = new Order();
            newOrder.setId(booksDto.get(j).getOrderId());
            book.setOrder(newOrder);
            books.add(book);
        }
        order.setBooks(books);

        List<RequestDto> requestsDto = orderDto.getListOfRequestDto();
        List<RequestForBook> requestForBookList = new ArrayList<>();
        for (int k = 0; k < requestsDto.size(); k++) {
            RequestForBook requestForBook = new RequestForBook();
            requestForBook.setId(requestsDto.get(k).getId());
            requestForBook.setTitleOfBook(requestsDto.get(k).getTitleOfBook());
            requestForBook.setAuthorOfBook(requestsDto.get(k).getAuthorOfBook());
            requestForBook.setRequestStatus(requestsDto.get(k).getRequestStatus());
            Order newOrder = new Order();
            newOrder.setId(booksDto.get(k).getOrderId());
            requestForBook.setOrder(newOrder);
            requestForBookList.add(requestForBook);
        }
        order.setListOfRequestForBooks(requestForBookList);
        return order;
    }
}
