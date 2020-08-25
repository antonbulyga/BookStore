package main.java.com.senla.model.repository.api;

import main.java.com.senla.model.DAO.Dao;
import main.java.com.senla.model.entity.Order;

import java.util.List;

public interface OrderRepository extends Dao<Order, Integer> {
    boolean create (Order order);
    boolean update (Order order);
    boolean delete (Order order);
    Order read(Integer id);
    List<Order> getAll();

}
