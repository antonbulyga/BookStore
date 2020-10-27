package com.senla.model.сontrollers;

import com.senla.model.dto.CustomerDto;
import com.senla.model.entity.Customer;
import com.senla.model.service.api.CustomerService;
import com.senla.model.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;
    private DtoConverter dtoConverter;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setDtoConverter(DtoConverter dtoConverter) {
        this.dtoConverter = dtoConverter;
    }

    @GetMapping("import")
    public ResponseEntity<String> importCustomer() {
        customerService.importCustomer();
        return new ResponseEntity<>(
                "Customers has been import successfully",
                HttpStatus.OK);
    }

    @GetMapping("export")
    public ResponseEntity<String> exportCustomer() {
        customerService.exportCustomer();
        return new ResponseEntity<>(
                "Customers has been export successfully",
                HttpStatus.OK);
    }

    @GetMapping("")
    public List<Customer> getListOfCustomers() {
        List<Customer> customers = customerService.getListOfCustomers();
        return customers;
    }

    @PostMapping("add")
    public CustomerDto addCustomerToListOfCustomers(@RequestBody CustomerDto customerDto) throws SQLException {
        Customer customer = dtoConverter.customerDtoToEntity(customerDto);
        customerService.addCustomerToListOfCustomers(customer);
        return customerDto;
    }

    @GetMapping("{id}")
    public CustomerDto getCustomerById(@PathVariable String id) {
        int customerId = Integer.parseInt(id);
        Customer customer = customerService.getCustomerById(customerId);
        CustomerDto customerDto = dtoConverter.customerEntityToDto(customer);
        return customerDto;
    }

    @PostMapping("update")
    public Customer updateCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = dtoConverter.customerDtoToEntity(customerDto);
        customerService.updateCustomer(customer);
        return customer;
    }

    @DeleteMapping("delete")
    public Customer deleteCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = dtoConverter.customerDtoToEntity(customerDto);
        customerService.deleteCustomer(customer);
        return customer;
    }
}
