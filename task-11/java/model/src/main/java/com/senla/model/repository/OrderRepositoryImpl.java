package main.java.com.senla.model.repository;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.model.DAO.MysqlConnect;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.enumeration.OrderStatus;
import main.java.com.senla.model.enumeration.SQLOrder;
import main.java.com.senla.model.repository.api.OrderRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class OrderRepositoryImpl implements OrderRepository {
    private static OrderRepositoryImpl instance;

    private OrderRepositoryImpl() {

    }

    public static OrderRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new OrderRepositoryImpl();
        }
        return instance;
    }

    @Override
    public boolean create(Order order) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLOrder.INSERT_ORDER.QUERY)) {
            statement.setString(1, order.getDateOfOrder().toString());
            if (order.getDateOfDoneOrder() == null) {
                statement.setDate(2, null);
            } else {
                statement.setString(2, order.getDateOfDoneOrder().toString());
            }
            statement.setDouble(3, order.getPriceOfOrder());
            statement.setString(4, order.getOrderStatus().toString());
            statement.setInt(5, order.getCustomer().getId());
            int i = statement.executeUpdate();
            if (i >= 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Order order) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLOrder.UPDATE_ORDER.QUERY)) {
            statement.setString(1, order.getDateOfOrder().toString());
            if (order.getDateOfDoneOrder() == null) {
                statement.setDate(2, null);
            } else {
                statement.setString(2, order.getDateOfDoneOrder().toString());
            }
            statement.setDouble(3, order.getPriceOfOrder());
            statement.setString(4, order.getOrderStatus().toString());
            statement.setInt(5, order.getCustomer().getId());
            statement.setInt(6, order.getId());
            int i = statement.executeUpdate();
            if (i >= 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Order order) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLOrder.DELETE_ORDER.QUERY)) {
            statement.setInt(1, order.getId());
            int i = statement.executeUpdate();
            if (i >= 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order read(Integer orderId) {
        final Order result = new Order();
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLOrder.GET_ORDER.QUERY)) {
            statement.setInt(1, orderId);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setId(orderId);
                result.setDateOfOrder(LocalDate.parse(resultSet.getString("date_of_order")));
                result.setDateOfDoneOrder(LocalDate.parse(resultSet.getString("date_of_done_order")));
                result.setPriceOfOrder(resultSet.getDouble("price_of_order"));
                result.setOrderStatus(OrderStatus.valueOf(resultSet.getString("order_status")));
                result.setCustomer(CustomerRepositoryImpl.getInstance().read(resultSet.getInt("id_customer")));
                result.setBooks(getBooksFromOrder(orderId));
                result.setListOfRequestForBooks(getRequestForBookFromOrder(orderId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orderList = new ArrayList<>();
        try (Statement statement = MysqlConnect.getInstance().conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQLOrder.GET_ALL_ORDER.QUERY);
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setDateOfOrder(LocalDate.parse(resultSet.getString("date_of_order")));
                order.setDateOfDoneOrder(LocalDate.parse(resultSet.getString("date_of_done_order")));
                order.setPriceOfOrder(resultSet.getDouble("price_of_order"));
                order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("order_status")));
                order.setCustomer(CustomerRepositoryImpl.getInstance().read(resultSet.getInt("id_customer")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<Book> getBooksFromOrder(int orderId) {
        final List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLOrder.GET_BOOK_FROM_ORDER.QUERY)) {
            statement.setInt(1, orderId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = BookRepositoryImpl.getInstance().read(resultSet.getInt("id"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;

    }

    public List<RequestForBook> getRequestForBookFromOrder(int orderId) {
        final List<RequestForBook> requestForBooks = new ArrayList<>();
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLOrder.GET_REQUEST_FRO_BOOK_FROM_ORDER.QUERY)) {
            statement.setInt(1, orderId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RequestForBook requestForBook = RequestForBookRepositoryImpl.getInstance().read(resultSet.getInt("id"));
                requestForBooks.add(requestForBook);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestForBooks;
    }


}
