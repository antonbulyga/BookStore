package main.java.com.senla.model.repository.api;

import main.java.com.senla.model.DAO.Dao;
import main.java.com.senla.model.entity.Book;

import java.util.List;

public interface BookRepository extends Dao<Book, Integer> {
    boolean create (Book book);
    boolean update (Book book);
    boolean delete (Book book);
    Book read(Integer id);
    List<Book> getAll();
}
