package task6;

import java.util.Date;

public class Order {
    private Customer customer;
    private Employee employee;
    private Date dateOfOrder;
    private  Date dateOfDoneOrder;
    private Book[] books;

    public Order(Customer customer, Employee employee, Date dateOfOrder, Date dateOfDoneOrder, Book[] books) {
        this.customer = customer;
        this.employee = employee;
        this.dateOfOrder = dateOfOrder;
        this.dateOfDoneOrder = dateOfDoneOrder;
        this.books = books;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Date getDateOfDoneOrder() {
        return dateOfDoneOrder;
    }

    public void setDateOfDoneOrder(Date dateOfDoneOrder) {
        this.dateOfDoneOrder = dateOfDoneOrder;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }


}
