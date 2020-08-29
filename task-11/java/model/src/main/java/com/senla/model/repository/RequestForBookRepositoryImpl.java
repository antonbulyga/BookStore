package main.java.com.senla.model.repository;

import com.sun.jdi.Value;
import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.model.DAO.MysqlConnect;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.enumeration.OrderStatus;
import main.java.com.senla.model.enumeration.RequestForBookStatus;
import main.java.com.senla.model.enumeration.SQLOrder;
import main.java.com.senla.model.enumeration.SQLRequestForBook;
import main.java.com.senla.model.repository.api.RequestForBookRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class RequestForBookRepositoryImpl implements RequestForBookRepository {

    @Override
    public boolean create(RequestForBook requestForBook) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLRequestForBook.INSERT_REQUEST_FOR_BOOK.query)) {
            statement.setString(1, requestForBook.getTitleOfBook());
            statement.setString(2, requestForBook.getAuthorOfBook());
            statement.setString(3, requestForBook.getRequestStatus().toString());
            statement.setInt(4, requestForBook.getOrder().getId());
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
    public boolean update(RequestForBook requestForBook) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLRequestForBook.UPDATE_REQUEST_FOR_BOOK.query)) {
            statement.setString(1, requestForBook.getTitleOfBook());
            statement.setString(2, requestForBook.getAuthorOfBook());
            statement.setString(3, requestForBook.getRequestStatus().toString());
            statement.setInt(4, requestForBook.getOrder().getId());
            statement.setInt(5, requestForBook.getId());

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
    public boolean delete(RequestForBook requestForBook) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLRequestForBook.DELETE_REQUEST_FOR_BOOK.query)) {
            statement.setInt(1, requestForBook.getId());
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
    public RequestForBook read(Integer requestForBookId) {
        final RequestForBook result = new RequestForBook();
        Order order = new Order();
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLRequestForBook.GET_REQUEST_FOR_BOOK.query)) {
            statement.setInt(1, requestForBookId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setTitleOfBook(resultSet.getString("request_for_book.title"));
                result.setAuthorOfBook(resultSet.getString("request_for_book.author"));
                result.setRequestStatus(RequestForBookStatus.valueOf(resultSet.getString("request_for_book_status")));
                order.setId(resultSet.getInt("order_id"));
                result.setOrder(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<RequestForBook> getAll() {
        final List<RequestForBook> requestForBookList = new ArrayList<>();
        Order order = new Order();
        try (Statement statement = MysqlConnect.getInstance().conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQLRequestForBook.GET_ALL_REQUEST_FOR_BOOK.query);
            while (resultSet.next()) {
                RequestForBook requestForBook = new RequestForBook();
                requestForBook.setId(resultSet.getInt("id"));
                requestForBook.setTitleOfBook((resultSet.getString("request_for_book.title")));
                requestForBook.setAuthorOfBook((resultSet.getString("request_for_book.author")));
                requestForBook.setRequestStatus(RequestForBookStatus.valueOf(resultSet.getString("request_for_book_status")));
                order.setId(resultSet.getInt("order_id"));
                requestForBook.setOrder(order);
                requestForBookList.add(requestForBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestForBookList;
    }

}
