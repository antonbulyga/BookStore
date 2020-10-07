package com.senla.model.сontrollers;

import com.senla.model.dto.BookDto;
import com.senla.model.entity.Book;
import com.senla.model.service.api.BookService;
import com.senla.model.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private DtoConverter dtoConverter;


    @GetMapping("books/export")
    public String exportBook() {
        bookService.exportBook();
        return "Books has been export successfully";
    }

    @GetMapping("books/check")
    public boolean bookInStockChecker(String titleBook, String authorBook) {
        boolean flag = bookService.bookInStockChecker(titleBook, authorBook);
        return flag;
    }

    @GetMapping("books/search/custom_search")
    public void customSearch(String author, LocalDate endDate) {
        bookService.customSearch(author, endDate);
    }

    @GetMapping("books/import")
    public String importBook() {
        bookService.importBook();
        return "Books has been import successfully";
    }

    @DeleteMapping("books/delete")
    public Book deleteBook(@RequestBody Book book) {
        bookService.deleteBook(book);
        return book;
    }

    @GetMapping("books/get_book_by_author_and_title")
    public Book getBookByAuthorAndTitle(@PathVariable String titleBook,@PathVariable String authorBook) {
        Book book = bookService.getBookByAuthorAndTitle(titleBook, authorBook);
        return book;
    }

    @GetMapping("books/show_books_more_than_six_month")
    public void showUnsoldBooksMoreThanSixMonth() {
        bookService.showUnsoldBooksMoreThanSixMonth();
    }

    @GetMapping("books")
    public List<BookDto> getListOfBooks() {
        List<Book> books = bookService.getListOfBooksInStoreHouse();
        List<BookDto> bookDtoList = new ArrayList<>();
        BookDto bookDto = new BookDto();
        for (int i = 0; i < books.size(); i++) {
            DtoConverter dtoConverter = new DtoConverter();
            BookDto bookDtoNew = dtoConverter.bookEntityToDto(books.get(i));
            bookDtoList.add(bookDtoNew);
        }
        return bookDtoList;
    }

    @PostMapping("books/create")
    public BookDto createBook(@RequestBody BookDto bookDto) throws SQLException {
        Book book = dtoConverter.bookDtoToEntity(bookDto);
        bookService.createBook(book);
        return bookDto;
    }

    @GetMapping("books/{id}")
    public BookDto getBookById(@PathVariable String id) {
        int bookId = Integer.parseInt(id);
        BookDto bookDto;
        try {
            Book book = bookService.getBookById(bookId);
            bookDto = dtoConverter.bookEntityToDto(book);
        }
        catch (NoResultException e){
            throw new NoResultException("No book with this ID");
        }
        return bookDto;
    }

    @PostMapping("books/update")
    public BookDto bookUpdate(@RequestBody BookDto bookDto) {
        Book book = dtoConverter.bookDtoToEntity(bookDto);
        bookService.bookUpdate(book);
        return bookDto;
    }

    @GetMapping("books/sort/price")
    public List<BookDto> sortBookByPrice() {
        List<Book> books = bookService.sortBookByPrice();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            BookDto bookDto = dtoConverter.bookEntityToDto(books.get(i));
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    @GetMapping("books/sort/author")
    public List<BookDto> sortBookByAuthor() {
        List<Book> books = bookService.sortBookByPrice();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            BookDto bookDto = dtoConverter.bookEntityToDto(books.get(i));
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    @GetMapping("books/sort/arrive_date")
    public List<BookDto> sortBookByDateArrive() {
        List<Book> books = bookService.sortBookByDateArrive();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            BookDto bookDto = dtoConverter.bookEntityToDto(books.get(i));
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    @GetMapping("books/sort/publication_date")
    public List<BookDto> sortBookByPublicationDate() {
        List<Book> books = bookService.sortBookByPublicationDate();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            BookDto bookDto = dtoConverter.bookEntityToDto(books.get(i));
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

}
