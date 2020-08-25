package main.java.com.senla.model.repository;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.model.DAO.MysqlConnect;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.enumeration.BookStatus;
import main.java.com.senla.model.enumeration.SQLBook;
import main.java.com.senla.model.repository.api.BookRepository;
import main.java.com.senla.model.сontrollers.BookController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class BookRepositoryImpl implements BookRepository {
    private static BookRepositoryImpl instance;

    private BookRepositoryImpl(){

    }
    public static BookRepositoryImpl getInstance(){
        if(instance == null){
            instance = new BookRepositoryImpl();
        }
        return instance;
    }

    @Override
    public boolean create(Book book) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLBook.INSERT_BOOK.QUERY)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3,book.getPrice());
            statement.setString(4, book.getBookStatus().toString());
            statement.setString(5, book.getArriveDate().toString());
            statement.setString(6, book.getPublicationDate().toString());
            int i = statement.executeUpdate();
            if(i >= 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Book book) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLBook.UPDATE_BOOK.QUERY)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3,book.getPrice());
            statement.setString(4, book.getBookStatus().toString());
            statement.setString(5, book.getArriveDate().toString());
            statement.setString(6, book.getPublicationDate().toString());
            statement.setInt(7, book.getId());
            int i = statement.executeUpdate();
            if(i >= 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Book book) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLBook.DELETE_BOOK.QUERY)) {
            statement.setInt(1, book.getId());
            int i = statement.executeUpdate();
            if(i >= 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Book read(Integer bookId) {
        final Book result = new Book();
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLBook.GET_BOOK.QUERY)) {
            statement.setInt(1, bookId);
            final ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                result.setId(bookId);
                result.setTitle(resultSet.getString("title"));
                result.setAuthor(resultSet.getString("author"));
                result.setPrice(resultSet.getDouble("price"));
                result.setBookStatus(BookStatus.valueOf(resultSet.getString("book_status")));
                result.setArriveDate(LocalDate.parse(resultSet.getString("arrive_date")));
                result.setPublicationDate(LocalDate.parse(resultSet.getString("publication_date")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Book> getAll() {
        final List<Book> listOfBooksInStorehouse = new ArrayList<>();
        try (Statement statement = MysqlConnect.getInstance().conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQLBook.GET_ALL_BOOKS.QUERY);
            while(resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getDouble("price"));
                book.setBookStatus(BookStatus.valueOf(resultSet.getString("book_status")));
                book.setArriveDate(LocalDate.parse(resultSet.getString("arrive_date")));
                book.setArriveDate(LocalDate.parse(resultSet.getString("publication_date")));
                listOfBooksInStorehouse.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfBooksInStorehouse;
    }

}
