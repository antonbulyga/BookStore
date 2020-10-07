package com.senla.model.сontrollers;

import com.senla.model.dto.CustomerDto;
import com.senla.model.entity.Customer;
import com.senla.model.service.api.CustomerService;
import com.senla.model.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DtoConverter dtoConverter;

    @GetMapping("customers/import")
    public String importCustomer() {
        customerService.importCustomer();
        return "Customers has been import successfully";
    }

    @GetMapping("customers/export")
    public String exportCustomer() {
        customerService.exportCustomer();
        return "Customers has been import successfully";
    }

    @GetMapping("customers")
    public List<Customer> getListOfCustomers() {
        List<Customer> customers = customerService.getListOfCustomers();
        return customers;
    }

    @PostMapping("customers/add")
    public CustomerDto addCustomerToListOfCustomers(@RequestBody CustomerDto customerDto) throws SQLException {
        Customer customer = dtoConverter.customerDtoToEntity(customerDto);
        customerService.addCustomerToListOfCustomers(customer);
        return customerDto;
    }

    @GetMapping("customers/{id}")
    public CustomerDto getCustomerById(@PathVariable String id) {
        int customerId = Integer.parseInt(id);
        CustomerDto customerDto;
        try {
            Customer customer = customerService.getCustomerById(customerId);
            customerDto = dtoConverter.customerEntityToDto(customer);
        }
        catch (NoResultException e){
            throw new NoResultException("No customer with this ID");
        }
        return customerDto;
    }

    @PostMapping("customers/update")
    public Customer updateCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = dtoConverter.customerDtoToEntity(customerDto);
        customerService.updateCustomer(customer);
        return customer;
    }

    @DeleteMapping("customers/delete")
    public Customer deleteCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = dtoConverter.customerDtoToEntity(customerDto);
        customerService.deleteCustomer(customer);
        return customer;
    }
}
