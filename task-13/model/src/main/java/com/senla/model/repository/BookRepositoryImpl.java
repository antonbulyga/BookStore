package com.senla.model.repository;

import com.senla.model.DAO.MysqlConnect;
import com.senla.model.entity.Book;
import com.senla.model.enumeration.SQLBook;
import com.senla.model.repository.api.BookRepository;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    static final Logger logger = Logger.getLogger(BookRepositoryImpl.class);

    @Override
    public boolean create(Book book) {
        try (PreparedStatement statement = MysqlConnect.getInstance().getConnection().prepareStatement(SQLBook.INSERT_BOOK.getQuery())) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3,book.getPrice());
            statement.setString(4, book.getArriveDate().toString());
            statement.setString(5, book.getPublicationDate().toString());
            int i = statement.executeUpdate();
            if(i >= 1) {
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public boolean update(Book book) {
        try (PreparedStatement statement = MysqlConnect.getInstance().getConnection().prepareStatement(SQLBook.UPDATE_BOOK.getQuery())) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3,book.getPrice());
            statement.setString(4, book.getArriveDate().toString());
            statement.setString(5, book.getPublicationDate().toString());
            statement.setInt(6, book.getId());
            int i = statement.executeUpdate();
            if(i >= 1) {
                return true;
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(Book book) {
        try (PreparedStatement statement = MysqlConnect.getInstance().getConnection().prepareStatement(SQLBook.DELETE_BOOK.getQuery())) {
            statement.setInt(1, book.getId());
            int i = statement.executeUpdate();
            if(i >= 1) {
                return true;
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }


    @Override
    public Book read(Integer bookId) {
        final Book result = new Book();
        try (PreparedStatement statement = MysqlConnect.getInstance().getConnection().prepareStatement(SQLBook.GET_BOOK.getQuery())) {
            statement.setInt(1, bookId);
            final ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                result.setId(bookId);
                result.setTitle(resultSet.getString("title"));
                result.setAuthor(resultSet.getString("author"));
                result.setPrice(resultSet.getDouble("price"));
                result.setArriveDate(LocalDate.parse(resultSet.getString("arrive_date")));
                result.setPublicationDate(LocalDate.parse(resultSet.getString("publication_date")));
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public List<Book> getAll() {
        final List<Book> bookList = new ArrayList<>();
        try (Statement statement = MysqlConnect.getInstance().getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(SQLBook.GET_ALL_BOOKS.getQuery());
            while(resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getDouble("price"));
                book.setArriveDate(LocalDate.parse(resultSet.getString("arrive_date")));
                book.setArriveDate(LocalDate.parse(resultSet.getString("publication_date")));
                bookList.add(book);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return bookList;
    }



}
