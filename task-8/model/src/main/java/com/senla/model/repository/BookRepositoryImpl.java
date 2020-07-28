package main.java.com.senla.model.repository;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.enumeration.BookStatus;
import main.java.com.senla.model.repository.api.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class BookRepositoryImpl implements BookRepository {
    private List<Book> listOfBooksInStorehouse = new ArrayList<>();

    public Book createBook(int id, String title, String author, double price, LocalDate publicationDate){
        List<RequestForBook> requestForBooks = new ArrayList<>();
        LocalDate arriveDate = LocalDate.now();
        Book book = new Book(id, title, author, price, BookStatus.IN_STOCK, requestForBooks, arriveDate, publicationDate);
        addBookToListOfBookInTheStorehouse(book);
        return book;
    }

    public void addBookToListOfBookInTheStorehouse(Book book){
        listOfBooksInStorehouse.add(book);
    }

    public void bookUpdate(Book book) {
        List<Book> books = getListOfBooksInStorehouse();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                deleteBook(books.get(i));
                books.set(i, book);
                setListOfBooksInStorehouse(books);
            }
        }
    }

    public void deleteBook(Book book){
        List<Book> books = getListOfBooksInStorehouse();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                books.remove(books.get(i));
            }
        }
        setListOfBooksInStorehouse(books);
    }

    public Book getBookById(int id){
        List<Book> books = getListOfBooksInStorehouse();
        Book book = null;
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId() == id){
                book = books.get(i);
            }
        }
        return book;
    }

    public void setListOfBooksInStorehouse(List<Book> listOfBooksInStorehouse) {
        this.listOfBooksInStorehouse = listOfBooksInStorehouse;
    }

    public List<Book> getListOfBooksInStorehouse() {
        return listOfBooksInStorehouse;
    }
}
