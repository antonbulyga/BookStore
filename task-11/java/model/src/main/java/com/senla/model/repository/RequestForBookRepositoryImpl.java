package main.java.com.senla.model.repository;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.model.DAO.MysqlConnect;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.enumeration.RequestForBookStatus;
import main.java.com.senla.model.enumeration.SQLOrder;
import main.java.com.senla.model.enumeration.SQLRequestForBook;
import main.java.com.senla.model.repository.api.RequestForBookRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Component
public class RequestForBookRepositoryImpl implements RequestForBookRepository {
    private static RequestForBookRepositoryImpl instance;

    private RequestForBookRepositoryImpl(){

    }
    public static RequestForBookRepositoryImpl getInstance(){
        if(instance == null){
            instance = new RequestForBookRepositoryImpl();
        }
        return instance;
    }

    @Override
    public boolean create(RequestForBook requestForBook) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLRequestForBook.INSERT_REQUEST_FOR_BOOK.QUERY)) {
            statement.setInt(1, requestForBook.getBook().getId());
            statement.setString(2, requestForBook.getRequestStatus().toString());
            statement.setInt(3, requestForBook.getOrder().getId());
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
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLRequestForBook.UPDATE_REQUEST_FOR_BOOK.QUERY)) {
            statement.setInt(1, requestForBook.getBook().getId());
            statement.setString(2, requestForBook.getRequestStatus().toString());
            statement.setDouble(3, requestForBook.getOrder().getId());
            statement.setInt(4, requestForBook.getId());
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
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLRequestForBook.DELETE_REQUEST_FOR_BOOK.QUERY)) {
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
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLRequestForBook.GET_REQUEST_FOR_BOOK.QUERY)) {
            statement.setInt(1, requestForBookId);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setBook(BookRepositoryImpl.getInstance().read(resultSet.getInt("id_book")));
                result.setRequestStatus(RequestForBookStatus.valueOf(resultSet.getString("requestForBook_status")));
                result.setOrder(OrderRepositoryImpl.getInstance().read(resultSet.getInt("id_order")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<RequestForBook> getAll() {
        final List<RequestForBook> requestForBookList = new ArrayList<>();
        try (Statement statement = MysqlConnect.getInstance().conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQLRequestForBook.GET_ALL_REQUEST_FOR_BOOK.QUERY);
            while (resultSet.next()) {
                RequestForBook requestForBook = new RequestForBook();
                requestForBook.setId(resultSet.getInt("id"));
                requestForBook.setBook(BookRepositoryImpl.getInstance().read(resultSet.getInt("id_Book")));
                requestForBook.setRequestStatus(RequestForBookStatus.valueOf(resultSet.getString("requestForBook_status")));
                requestForBook.setOrder(OrderRepositoryImpl.getInstance().read(resultSet.getInt("id_order")));
                requestForBookList.add(requestForBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestForBookList;
    }

}
