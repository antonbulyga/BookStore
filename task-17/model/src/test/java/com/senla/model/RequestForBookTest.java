package com.senla.model;


import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.enumeration.RequestForBookStatus;
import com.senla.model.repository.api.RequestForBookRepository;
import com.senla.model.service.RequestForBookServiceImpl;
import com.senla.model.service.api.BookService;
import com.senla.model.service.api.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class RequestForBookTest {
    @InjectMocks
    private RequestForBookServiceImpl requestForBookService;
    @Mock
    private RequestForBookRepository requestForBookRepository;
    @Mock
    private BookService bookService;
    @Mock
    private OrderService orderService;
    private final int id = 1;
    private final Order order = new Order();
    private final RequestForBook requestForBook = new RequestForBook(1, "Война", "Петухов", RequestForBookStatus.ACTIVE, order);

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(requestForBookService, "path", "model/src/test/resources/files/Customer.csv");
    }

    @Test
    public void getRequestForBookById() {
        when(requestForBookRepository.read(id)).thenReturn(requestForBook);
        RequestForBook returnRequest = requestForBookService.getRequestForBookById(id);
        Assertions.assertEquals(requestForBook, returnRequest);
    }

    @Test
    public void shouldReturnListOfAllCustomers() {
        List<RequestForBook> requestForBookList = new ArrayList<>();
        requestForBookList.add(new RequestForBook(1, "Война", "Петухов", RequestForBookStatus.ACTIVE, order));
        requestForBookList.add(new RequestForBook(2, "Ночь", "Петров", RequestForBookStatus.ACTIVE, order));
        requestForBookList.add(new RequestForBook(3, "День света", "Конев", RequestForBookStatus.ACTIVE, order));
        when(requestForBookRepository.getAll()).thenReturn(requestForBookList);
        List<RequestForBook> expected = requestForBookService.getListOfRequestForBook();
        Assertions.assertEquals(requestForBookList, expected);
    }

    @Test
    public void createRequestForBookTest() throws SQLException {
        requestForBookService.addRequestForBookToList(requestForBook);
        verify(requestForBookRepository, times(1)).create(requestForBook);
    }

    @Test
    public void shouldBeDelete() {
        requestForBookService.deleteRequestForBook(requestForBook);
        verify(requestForBookRepository, times(1)).delete(requestForBook);
    }

    @Test
    public void deleteCustomerDoesNotThrow() {
        assertDoesNotThrow(() -> requestForBookService.deleteRequestForBook(any(RequestForBook.class)));
    }

    @Test
    public void exportCustomerDoesNotThrow() {
        assertDoesNotThrow(() -> requestForBookService.exportRequestForBook());
    }

    @Test
    public void updateCustomerDoesNotThrow() {
        assertDoesNotThrow(() -> requestForBookService.updateRequestForBook(any(RequestForBook.class)));
    }

    @Test
    public void shouldSortRequestByAlphabet(){
        List<RequestForBook> requestForBookList = new ArrayList<>();
        requestForBookList.add(new RequestForBook(1, "Война", "Петухов", RequestForBookStatus.ACTIVE, order));
        requestForBookList.add(new RequestForBook(2, "Ночь", "Петров", RequestForBookStatus.ACTIVE, order));
        requestForBookList.add(new RequestForBook(3, "День света", "Конев", RequestForBookStatus.ACTIVE, order));
        when(requestForBookRepository.getAll()).thenReturn(requestForBookList);
        List<RequestForBook> expected = requestForBookService.sortRequestByAlphabet();
        Assertions.assertEquals(requestForBookList, expected);
    }

}
