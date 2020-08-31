package com.senla.model.repository;

import com.senla.config.annotations.Component;
import com.senla.model.DAO.MysqlConnect;
import com.senla.model.entity.Book;
import com.senla.model.enumeration.SQLBook;
import com.senla.model.repository.api.BookRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class BookRepositoryImpl implements BookRepository {

    @Override
    public boolean create(Book book) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLBook.INSERT_BOOK.query)) {
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
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Book book) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLBook.UPDATE_BOOK.query)) {
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
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Book book) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLBook.DELETE_BOOK.query)) {
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
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLBook.GET_BOOK.query)) {
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
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Book> getAll() {
        final List<Book> bookList = new ArrayList<>();
        try (Statement statement = MysqlConnect.getInstance().conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQLBook.GET_ALL_BOOKS.query);
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
            e.printStackTrace();
        }
        return bookList;
    }



}
