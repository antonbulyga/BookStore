package main.java.com.senla.model.DAO;

import java.sql.SQLException;
import java.util.List;


public interface Dao<Entity, Key> {
    boolean create (Entity model) throws SQLException;
    boolean update (Entity model);
    boolean delete (Entity model);
    Entity read(Key id);
    List<Entity> getAll();





   /* Book createBook(int id, String title, String author, double price, LocalDate publicationDate);

    void addBookToListOfBookInTheStorehouse(Book book);

    void bookUpdate(Book book);

    void deleteBook(Book book);

    Book getBookById(int id);

    void setListOfBooksInStorehouse(List<Book> listOfBooksInStorehouse);

    List<Book> getListOfBooksInStorehouse();*/

}
