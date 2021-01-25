package com.senla.model.сontrollers;

import com.senla.model.dto.BookDto;
import com.senla.model.entity.Book;
import com.senla.model.service.api.BookService;
import com.senla.model.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;
    private DtoConverter dtoConverter;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    @Autowired
    public void setDtoConverter(DtoConverter dtoConverter) {
        this.dtoConverter = dtoConverter;
    }

    @GetMapping("export")
    public ResponseEntity<String> exportBook() {
        bookService.exportBook();
        return new ResponseEntity<>(
                "Books has been export successfully",
                HttpStatus.OK);
    }

    @GetMapping("check/{titleBook}/{authorBook}")
    public boolean bookInStockChecker(@PathVariable ("titleBook") String titleBook,
                                      @PathVariable ("authorBook") String authorBook) {
        boolean flag = bookService.bookInStockChecker(titleBook, authorBook);
        return flag;
    }

    @PostMapping("search/custom")
    public List<BookDto> customSearch(@RequestParam (name = "author") String author,
                                      @RequestParam (name = "end_date") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
            {
        List<Book> books = bookService.customSearch(author, endDate);
        List<BookDto> bookDtoList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++){
        BookDto bookDto = dtoConverter.bookEntityToDto(books.get(i));
        bookDtoList.add(bookDto);
    }

        return bookDtoList;
    }

    @GetMapping("import")
    public ResponseEntity<String> importBook() {
        bookService.importBook();
        return new ResponseEntity<>(
                "Books has been import successfully",
                HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public BookDto deleteBook(@RequestBody BookDto bookDto) {
        Book book = dtoConverter.bookDtoToEntity(bookDto);
        bookService.deleteBook(book);
        return bookDto;
    }

    @GetMapping("author_title")
    public BookDto getBookByAuthorAndTitle(@PathVariable ("titleBook") String titleBook,
                                           @PathVariable ("authorBook") String authorBook) {
        Book book = bookService.getBookByAuthorAndTitle(titleBook, authorBook);
        BookDto bookDto = dtoConverter.bookEntityToDto(book);
        return bookDto;
    }

    @GetMapping("show/unsold/six_month")
    public List<BookDto> showUnsoldBooksMoreThanSixMonth() {
        List<Book> books = bookService.showUnsoldBooksMoreThanSixMonth();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            BookDto bookDto = dtoConverter.bookEntityToDto(books.get(i));
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    @GetMapping("")
    public List<BookDto> getListOfBooks() {
        List<Book> books = bookService.getListOfBooksInStoreHouse();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            BookDto bookDtoNew = dtoConverter.bookEntityToDto(books.get(i));
            bookDtoList.add(bookDtoNew);
        }
        return bookDtoList;
    }

    @PostMapping("add")
    public BookDto createBook(@RequestBody BookDto bookDto) throws SQLException {
        Book book = dtoConverter.bookDtoToEntity(bookDto);
        bookService.createBook(book);
        return bookDto;
    }

    @GetMapping("{id}")
    public BookDto getBookById(@PathVariable String id) {
        int bookId = Integer.parseInt(id);
        Book book = bookService.getBookById(bookId);
        BookDto bookDto = dtoConverter.bookEntityToDto(book);
        return bookDto;
    }

    @PostMapping("update")
    public BookDto bookUpdate(@RequestBody BookDto bookDto) {
        Book book = dtoConverter.bookDtoToEntity(bookDto);
        bookService.bookUpdate(book);
        return bookDto;
    }

    @GetMapping("sort/price")
    public List<BookDto> sortBookByPrice() {
        List<Book> books = bookService.sortBookByPrice();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            BookDto bookDto = dtoConverter.bookEntityToDto(books.get(i));
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    @GetMapping("sort/author")
    public List<BookDto> sortBookByAuthor() {
        List<Book> books = bookService.sortBookByPrice();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            BookDto bookDto = dtoConverter.bookEntityToDto(books.get(i));
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    @GetMapping("sort/arriveDate")
    public List<BookDto> sortBookByDateArrive() {
        List<Book> books = bookService.sortBookByDateArrive();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            BookDto bookDto = dtoConverter.bookEntityToDto(books.get(i));
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    @GetMapping("sort/publicationDate")
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
