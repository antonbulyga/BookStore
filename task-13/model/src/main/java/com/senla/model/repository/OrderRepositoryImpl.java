package com.senla.model.repository;

import com.senla.model.DAO.MysqlConnect;
import com.senla.model.entity.Book;
import com.senla.model.entity.Customer;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.enumeration.OrderStatus;
import com.senla.model.enumeration.RequestForBookStatus;
import com.senla.model.enumeration.SQLOrder;
import com.senla.model.enumeration.SQLRequestForBook;
import com.senla.model.repository.api.OrderRepository;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    static final Logger logger = Logger.getLogger(OrderRepositoryImpl.class);
    @Override
    public boolean create(Order order) {
        try (PreparedStatement statement = MysqlConnect.getInstance().getConnection().prepareStatement(SQLOrder.INSERT_ORDER.getQuery())) {
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
            logger.error(e);
        }
        return false;
    }

    @Override
    public boolean update(Order order) {
        try (PreparedStatement statement = MysqlConnect.getInstance().getConnection().prepareStatement(SQLOrder.UPDATE_ORDER.getQuery())) {
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
            logger.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(Order order) {
        try (PreparedStatement statement = MysqlConnect.getInstance().getConnection().prepareStatement(SQLOrder.DELETE_ORDER.getQuery())) {
            statement.setInt(1, order.getId());
            int i = statement.executeUpdate();
            if (i >= 1) {
                return true;
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public Order read(Integer orderId) {
        final Order result = new Order();
        try (PreparedStatement statement = MysqlConnect.getInstance().getConnection().prepareStatement(SQLOrder.GET_ORDER.getQuery())) {
            statement.setInt(1, orderId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.setId(orderId);
                result.setDateOfOrder(LocalDate.parse(resultSet.getString("date_of_order")));
                result.setDateOfDoneOrder(LocalDate.parse(resultSet.getString("date_of_done_order")));
                result.setPriceOfOrder(resultSet.getDouble("price_of_order"));
                result.setOrderStatus(OrderStatus.valueOf(resultSet.getString("order_status")));
                result.setCustomer(getCustomer(orderId));
                result.setBooks(getBooksFromOrder(orderId));
                result.setListOfRequestForBooks(getListOfRequestForBookFromOrder(orderId));
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public List<Order> getAll() {
       final List<Order> orderList = new ArrayList<>();
        try (Statement statement = MysqlConnect.getInstance().getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQLOrder.GET_ALL_ORDER.getQuery());
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("order.id"));
                order.setDateOfOrder(LocalDate.parse(resultSet.getString("date_of_order")));
                order.setDateOfDoneOrder(LocalDate.parse(resultSet.getString("date_of_done_order")));
                order.setPriceOfOrder(resultSet.getDouble("price_of_order"));
                order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("order_status")));
                order.setCustomer(getCustomer(order.getId()));
                order.setBooks(getBooksFromOrder(order.getId()));
                order.setListOfRequestForBooks(getListOfRequestForBookFromOrder(order.getId()));
                orderList.add(order);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return orderList;
    }


    public List<Book> getBooksFromOrder(int orderId) {
        final List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = MysqlConnect.getInstance().getConnection().prepareStatement(SQLOrder.GET_BOOK_FROM_ORDER.getQuery())) {
            statement.setInt(1, orderId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("book.id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getDouble("price"));
                book.setArriveDate(LocalDate.parse(resultSet.getString("arrive_date")));
                book.setArriveDate(LocalDate.parse(resultSet.getString("publication_date")));
                books.add(book);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return books;
    }

    public Customer getCustomer(int orderId) throws SQLException {
        Customer customer = new Customer();
        try (PreparedStatement statement = MysqlConnect.getInstance().getConnection().prepareStatement(SQLOrder.GET_CUSTOMER_FROM_ORDER.getQuery())) {
            statement.setInt(1, orderId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer.setAge(resultSet.getInt("customer.age"));
                customer.setName(resultSet.getString("customer.name"));
                customer.setId(resultSet.getInt("customer.id"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }

        return customer;
    }

    public List<RequestForBook> getListOfRequestForBookFromOrder(int orderId) {
        final List<RequestForBook> requestForBooks = new ArrayList<>();
        try (PreparedStatement statement = MysqlConnect.getInstance().getConnection().prepareStatement(SQLRequestForBook.GET_REQUEST_FROM_BOOK_FROM_ORDER.getQuery())) {
            statement.setInt(1, orderId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RequestForBook requestForBook = new RequestForBook();
                requestForBook.setId(resultSet.getInt("request_for_book.id"));
                requestForBook.setTitleOfBook(resultSet.getString("request_for_book.title"));
                requestForBook.setAuthorOfBook(resultSet.getString("request_for_book.author"));
                requestForBook.setRequestStatus(RequestForBookStatus.valueOf(resultSet.getString("request_for_book_status")));
                requestForBooks.add(requestForBook);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return requestForBooks;
    }
}
