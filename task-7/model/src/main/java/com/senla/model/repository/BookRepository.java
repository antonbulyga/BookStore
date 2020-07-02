package main.java.com.senla.model.repository;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.entity.StockLevel;
import main.java.com.senla.model.enumeration.BookStatus;
import main.java.com.senla.model.service.BookService;
import main.java.com.senla.model.service.RequestForBookService;
import main.java.com.senla.model.сontrollers.RequestForBookController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static BookRepository instance;
    private List<Book> listOfBooksInStorehouse = new ArrayList<>();

    private BookRepository(){

    }

    public static BookRepository getInstance(){
        if(instance == null){
            instance = new BookRepository();
        }
        return instance;
    }

    public Book createBook(int id, String title, String author, double price, LocalDate publicationDate){
        List<RequestForBook> requestForBooks = new ArrayList<>();
        LocalDate arriveDate = LocalDate.now();
        Book book = new Book(id, title, author, price, BookStatus.IN_STOCK, requestForBooks, arriveDate, publicationDate);
        addBookToListOfBookInTheStorehouse(book);
        RequestForBookService.getInstance().closerRequestForBooksAfterArrivingBook(book);
        //BookService.getInstance().arriveBookToStock(book);
        //BookService.getInstance().completingRequestAfterArrivingNewBook(book);
        return book;
    }

    public void addBookToListOfBookInTheStorehouse(Book book){
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        books.add(book);
        BookRepository.getInstance().setListOfBooksInStorehouse(books);
    }

    public void bookUpdate(Book book) {
        List<StockLevel> stockLevels = StockRepository.getInstance().getListOfStockLevels();

        for (int i = 0; i < stockLevels.size(); i++) {
            if (stockLevels.get(i).getBook().getId() == book.getId()) {
                BookService.getInstance().arriveBookToStock(book);
            }
        }
    }

    public void setListOfBooksInStorehouse(List<Book> listOfBooksInStorehouse) {
        this.listOfBooksInStorehouse = listOfBooksInStorehouse;
    }

    public List<Book> getListOfBooksInStorehouse() {
        return listOfBooksInStorehouse;
    }
}
