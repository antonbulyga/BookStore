package com.senla.model;

import com.senla.model.entity.Book;
import com.senla.model.repository.api.BookRepository;
import com.senla.model.repository.api.RequestForBookRepository;
import com.senla.model.service.BookServiceImpl;
import com.senla.model.service.api.OrderService;
import com.senla.model.service.api.RequestForBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private RequestForBookService requestForBookService;
    @Mock
    private OrderService orderService;
    @Mock
    private RequestForBookRepository requestForBookRepository;
    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(bookService, "path", "model/src/test/resources/files/Books.csv");
    }
    private final Book book = new Book(1, "Гоголь Николай", "Мертвые души",4, LocalDate.of(2012,05,13), LocalDate.of(1998,01,05));


    @Test
    public void shouldSortBookByAuthor(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Гоголь Николай", "Мертвые души",4, LocalDate.of(2012,05,13), LocalDate.of(1998,01,05)));
        bookList.add(new Book( 2,"Лев Толстой", "Война и мир", 4, LocalDate.of(2016,04,06), LocalDate.of(2012,05,20)));
        bookList.add(new Book(3, "Федор Достоевский", "Преступление и наказание", 3.5, LocalDate.of(2014,06,5), LocalDate.of(1996,05,25)));
        when(bookRepository.getAll()).thenReturn(bookList);
        List<Book> result = bookService.sortBookByAuthor();
        Assertions.assertEquals(bookList,result);
    }


    @Test
    public void shouldSortBookByDateArrive(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Гоголь Николай", "Мертвые души",4, LocalDate.of(2012,05,13), LocalDate.of(1998,01,05)));
        bookList.add(new Book( 2,"Лев Толстой", "Война и мир", 4, LocalDate.of(2016,04,06), LocalDate.of(2012,05,20)));
        bookList.add(new Book(3, "Федор Достоевский", "Преступление и наказание", 3.5, LocalDate.of(2014,06,5), LocalDate.of(1996,05,25)));
        when(bookRepository.getAll()).thenReturn(bookList);
        List<Book> result = bookService.sortBookByDateArrive();
        Assertions.assertEquals(bookList,result);
    }

    @Test
    public void shouldSortBookByPublicationDate(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Гоголь Николай", "Мертвые души",4, LocalDate.of(2012,05,13), LocalDate.of(1998,01,05)));
        bookList.add(new Book( 2,"Лев Толстой", "Война и мир", 4, LocalDate.of(2016,04,06), LocalDate.of(2012,05,20)));
        bookList.add(new Book(3, "Федор Достоевский", "Преступление и наказание", 3.5, LocalDate.of(2014,06,5), LocalDate.of(1996,05,25)));
        when(bookRepository.getAll()).thenReturn(bookList);
        List<Book> result = bookService.sortBookByPublicationDate();
        Assertions.assertEquals(bookList,result);
    }

    @Test
    public void shouldSortBookByPrice() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Гоголь Николай", "Мертвые души",4, LocalDate.of(2012,05,13), LocalDate.of(1998,01,05)));
        bookList.add(new Book( 2,"Лев Толстой", "Война и мир", 4, LocalDate.of(2016,04,06), LocalDate.of(2012,05,20)));
        bookList.add(new Book(3, "Федор Достоевский", "Преступление и наказание", 3.5, LocalDate.of(2014,06,5), LocalDate.of(1996,05,25)));
        when(bookRepository.getAll()).thenReturn(bookList);
        List<Book> result = bookService.sortBookByPrice();
        Assertions.assertEquals(bookList,result);
    }

    @Test
    public void shouldCheckBookInStore(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Гоголь Николай", "Мертвые души",4, LocalDate.of(2012,05,13), LocalDate.of(1998,01,05)));
        bookList.add(new Book( 2,"Лев Толстой", "Война и мир", 4, LocalDate.of(2016,04,06), LocalDate.of(2012,05,20)));
        bookList.add(new Book(3, "Федор Достоевский", "Преступление и наказание", 3.5, LocalDate.of(2014,06,5), LocalDate.of(1996,05,25)));
        when(bookRepository.getAll()).thenReturn(bookList);
        Assertions.assertTrue(bookService.bookInStockChecker("Гоголь Николай", "Мертвые души"));
    }

    @Test
    public void shouldGetAll(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Гоголь Николай", "Мертвые души",4, LocalDate.of(2012,05,13), LocalDate.of(1998,01,05)));
        bookList.add(new Book( 2,"Лев Толстой", "Война и мир", 4, LocalDate.of(2016,04,06), LocalDate.of(2012,05,20)));
        bookList.add(new Book(3, "Федор Достоевский", "Преступление и наказание", 3.5, LocalDate.of(2014,06,5), LocalDate.of(1996,05,25)));
        when(bookRepository.getAll()).thenReturn(bookList);
        List<Book> result = bookService.getListOfBooksInStoreHouse();
        Assertions.assertEquals(bookList,result);
    }

    @Test
    public void shouldGetBookByAuthorAndTitle(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Гоголь Николай", "Мертвые души",4, LocalDate.of(2012,05,13), LocalDate.of(1998,01,05)));
        bookList.add(new Book( 2,"Лев Толстой", "Война и мир", 4, LocalDate.of(2016,04,06), LocalDate.of(2012,05,20)));
        bookList.add(new Book(3, "Федор Достоевский", "Преступление и наказание", 3.5, LocalDate.of(2014,06,5), LocalDate.of(1996,05,25)));
        when(bookRepository.getAll()).thenReturn(bookList);
        Book book = bookService.getBookByAuthorAndTitle("Гоголь Николай", "Мертвые души");
        Assertions.assertNotNull(book);
    }
    @Test
    public void getCustomerById(){
        when(bookRepository.read(book.getId())).thenReturn(book);
        Book returnBook = bookService.getBookById(book.getId());
        Assertions.assertEquals(book, returnBook);
    }
    @Test
    public void createCustomerTest() throws SQLException {
        bookService.addBookToListOfBookInTheStorehouse(book);
        verify(bookRepository, times(1)).create(book);
    }

    @Test
    public void shouldBeDelete(){
        Book book = new Book(1, "Гоголь Николай", "Мертвые души",4, LocalDate.of(2012,05,13), LocalDate.of(1998,01,05));
        bookService.deleteBook(book);
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    public void completingRequestAfterArrivingNewBookDoesNotThrow() {
        assertDoesNotThrow(() -> bookService.completingRequestAfterArrivingNewBook(book));
    }

    @Test
    public void showUnsoldBooksMoreThanSixMonthDoesNotThrow() {
        assertDoesNotThrow(() -> bookService.showUnsoldBooksMoreThanSixMonth());
    }

    @Test
    public void exportBookDoesNotThrow(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Гоголь Николай", "Мертвые души",4, LocalDate.of(2012,05,13), LocalDate.of(1998,01,05)));
        bookList.add(new Book( 2,"Лев Толстой", "Война и мир", 4, LocalDate.of(2016,04,06), LocalDate.of(2012,05,20)));
        bookList.add(new Book(3, "Федор Достоевский", "Преступление и наказание", 3.5, LocalDate.of(2014,06,5), LocalDate.of(1996,05,25)));
        when(bookRepository.getAll()).thenReturn(bookList);
        assertDoesNotThrow(() -> bookService.exportBook());
    }

    @Test
    public void updateBookDoesNotThrow(){
        assertDoesNotThrow(() -> bookService.bookUpdate(any(Book.class)));
    }

}
