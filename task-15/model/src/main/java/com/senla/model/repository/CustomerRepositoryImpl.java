package com.senla.model.repository;

import com.senla.model.DAO.MysqlConnect;
import com.senla.model.entity.Customer;
import com.senla.model.enumeration.SQLCustomer;
import com.senla.model.repository.api.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    static final Logger logger = Logger.getLogger(CustomerRepositoryImpl.class);
    @Override
    public boolean create(Customer customer) {
        try (PreparedStatement statement = MysqlConnect.getMySqlConnectBean().getConnection().prepareStatement(SQLCustomer.INSERT_CUSTOMER.getQuery())) {
            statement.setInt(1, customer.getAge());
            statement.setString(2, customer.getName());
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
    public boolean update(Customer customer) {
        try (PreparedStatement statement = MysqlConnect.getMySqlConnectBean().getConnection().prepareStatement(SQLCustomer.UPDATE_CUSTOMER.getQuery())) {
            statement.setInt(1, customer.getAge());
            statement.setString(2, customer.getName());
            statement.setInt(3, customer.getId());
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
    public boolean delete(Customer customer) {
        try (PreparedStatement statement = MysqlConnect.getMySqlConnectBean().getConnection().prepareStatement(SQLCustomer.DELETE_CUSTOMER.getQuery())) {
            statement.setInt(1, customer.getId());
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
    public Customer read(Integer customerId) {
        final Customer result = new Customer();
        try (PreparedStatement statement = MysqlConnect.getMySqlConnectBean().getConnection().prepareStatement(SQLCustomer.GET_CUSTOMER.getQuery())) {
            statement.setInt(1, customerId);
            final ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                result.setId(customerId);
                result.setAge(Integer.parseInt(resultSet.getString("age")));
                result.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public List<Customer> getAll() {
        final List<Customer> listOfCustomers = new ArrayList<>();
        try (Statement statement = MysqlConnect.getMySqlConnectBean().getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(SQLCustomer.GET_ALL_CUSTOMERS.getQuery());
            while(resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setAge(resultSet.getInt("age"));
                customer.setName(resultSet.getString("name"));
                listOfCustomers.add(customer);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return listOfCustomers;
    }

}
