package com.senla.model;

import com.senla.model.entity.Customer;
import com.senla.model.repository.api.CustomerRepository;
import com.senla.model.service.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(customerService, "path", "model/src/test/resources/files/Customer.csv");
    }
    private final Customer customer = new Customer (1, 23, "Антон Булыга");

    @Test
    public void getCustomerById(){
        when(customerRepository.read(customer.getId())).thenReturn(customer);
        Customer returnCustomer = customerService.getCustomerById(customer.getId());
        Assertions.assertEquals(customer, returnCustomer);
    }
    @Test
    public void shouldReturnListOfAllCustomers(){
         List<Customer> customers = new ArrayList<>();
         customers.add(new Customer(1, 23, "Антон Булыга"));
         customers.add(new Customer(2, 28, "Влад Легкий"));
         customers.add(new Customer(3, 38, "Вктория Азаренко"));
         when(customerRepository.getAll()).thenReturn(customers);
         List<Customer> expected = customerService.getListOfCustomers();
         Assertions.assertEquals(customers, expected);
    }

    @Test
    public void createCustomerTest() throws SQLException {
        customerService.addCustomerToListOfCustomers(customer);
        verify(customerRepository, times(1)).create(customer);
    }

    @Test
    public void shouldBeDelete(){
        customerService.deleteCustomer(customer);
        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    public void deleteCustomerDoesNotThrow(){
        assertDoesNotThrow(() -> customerService.deleteCustomer(any(Customer.class)));
    }

    @Test
    public void exportCustomerDoesNotThrow(){
        assertDoesNotThrow(() -> customerService.exportCustomer());
    }

    @Test
    public void updateCustomerDoesNotThrow(){
        assertDoesNotThrow(() -> customerService.updateCustomer(any(Customer.class)));
    }

}