package main.java.com.senla.model.repository.api;

import main.java.com.senla.model.DAO.Dao;
import main.java.com.senla.model.entity.RequestForBook;

import java.util.List;

public interface RequestForBookRepository extends Dao<RequestForBook, Integer> {
    boolean create (RequestForBook requestForBook);
    boolean update (RequestForBook requestForBook);
    boolean delete (RequestForBook requestForBook);
    RequestForBook read(Integer id);
    List<RequestForBook> getAll();
}
