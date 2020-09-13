package com.senla.model.repository;

import com.senla.model.DAO.MysqlConnect;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.enumeration.RequestForBookStatus;
import com.senla.model.enumeration.SQLRequestForBook;
import com.senla.model.repository.api.RequestForBookRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class RequestForBookRepositoryImpl implements RequestForBookRepository {
    static final Logger logger = Logger.getLogger(RequestForBookRepositoryImpl.class);
    @Override
    public boolean create(RequestForBook requestForBook) {
        try (PreparedStatement statement = MysqlConnect.getMySqlConnectBean().getConnection().prepareStatement(SQLRequestForBook.INSERT_REQUEST_FOR_BOOK.getQuery())) {
            statement.setString(1, requestForBook.getTitleOfBook());
            statement.setString(2, requestForBook.getAuthorOfBook());
            statement.setString(3, requestForBook.getRequestStatus().toString());
            statement.setInt(4, requestForBook.getOrder().getId());
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
    public boolean update(RequestForBook requestForBook) {
        try (PreparedStatement statement = MysqlConnect.getMySqlConnectBean().getConnection().prepareStatement(SQLRequestForBook.UPDATE_REQUEST_FOR_BOOK.getQuery())) {
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
            logger.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(RequestForBook requestForBook) {
        try (PreparedStatement statement = MysqlConnect.getMySqlConnectBean().getConnection().prepareStatement(SQLRequestForBook.DELETE_REQUEST_FOR_BOOK.getQuery())) {
            statement.setInt(1, requestForBook.getId());
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
    public RequestForBook read(Integer requestForBookId) {
        final RequestForBook result = new RequestForBook();
        Order order = new Order();
        try (PreparedStatement statement = MysqlConnect.getMySqlConnectBean().getConnection().prepareStatement(SQLRequestForBook.GET_REQUEST_FOR_BOOK.getQuery())) {
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
            logger.error(e);
        }
        return result;
    }

    @Override
    public List<RequestForBook> getAll() {
        final List<RequestForBook> requestForBookList = new ArrayList<>();
        Order order = new Order();
        try (Statement statement = MysqlConnect.getMySqlConnectBean().getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQLRequestForBook.GET_ALL_REQUEST_FOR_BOOK.getQuery());
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
            logger.error(e);
        }
        return requestForBookList;
    }

}
