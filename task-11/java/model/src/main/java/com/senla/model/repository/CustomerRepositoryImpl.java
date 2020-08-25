package main.java.com.senla.model.repository;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.model.DAO.MysqlConnect;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.enumeration.SQLCustomer;
import main.java.com.senla.model.DAO.Dao;
import main.java.com.senla.model.repository.api.CustomerRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerRepositoryImpl implements CustomerRepository {
    private static CustomerRepositoryImpl instance;

    private CustomerRepositoryImpl(){

    }
    public static CustomerRepositoryImpl getInstance(){
        if(instance == null){
            instance = new CustomerRepositoryImpl();
        }
        return instance;
    }

    @Override
    public boolean create(Customer customer) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLCustomer.INSERT_CUSTOMER.QUERY)) {
            statement.setInt(1, customer.getAge());
            statement.setString(2, customer.getName());
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
    public boolean update(Customer customer) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLCustomer.UPDATE_CUSTOMER.QUERY)) {
            statement.setInt(1, customer.getAge());
            statement.setString(2, customer.getName());
            statement.setInt(3, customer.getId());
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
    public boolean delete(Customer customer) {
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLCustomer.DELETE_CUSTOMER.QUERY)) {
            statement.setInt(1, customer.getId());
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
    public Customer read(Integer customerId) {
        final Customer result = new Customer();
        try (PreparedStatement statement = MysqlConnect.getInstance().conn.prepareStatement(SQLCustomer.GET_CUSTOMER.QUERY)) {
            statement.setInt(1, customerId);
            final ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                result.setId(customerId);
                result.setAge(Integer.parseInt(resultSet.getString("age")));
                result.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Customer> getAll() {
        final List<Customer> listOfCustomers = new ArrayList<>();
        try (Statement statement = MysqlConnect.getInstance().conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQLCustomer.GET_ALL_CUSTOMERS.QUERY);
            while(resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setAge(resultSet.getInt("age"));
                customer.setName(resultSet.getString("name"));
                listOfCustomers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfCustomers;
    }



















   /* @MyAutoWired
    private CustomerRepository customerRepository;
    public void addCustomerToListOfCustomers(Customer customer){
       listOfCustomers.add(customer);
    }

    public Customer createCustomer(int id, int age, String name){
        Customer customer = new Customer(id, age, name);
        return customer;
    }

    public void updateCustomer(Customer customer){
        List<Customer> customerList = getListOfCustomers();
        for (int i = 0; i < customerList.size(); i++) {
            if(customer.getId() == customerList.get(i).getId()){
                deleteCustomer(customerList.get(i));
                customerList.set(i, customer);
                setListOfCustomers(customerList);
            }
        }
    }

    public void deleteCustomer(Customer customer){
        List<Customer> customerList = getListOfCustomers();
        for (int i = 0; i < customerList.size(); i++) {
            if(customerList.get(i).getId() == customer.getId()){
                customerList.remove(customerList.get(i));
            }
            setListOfCustomers(customerList);
        }
    }

    public Customer getCustomerById(int id){
        List<Customer> customers = getListOfCustomers();
        Customer customer = null;
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getId() == id){
                customer = customers.get(i);
            }
        }
        return customer;
    }

    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(List<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }*/
}
