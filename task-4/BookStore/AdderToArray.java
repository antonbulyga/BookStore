package task6;

import task2.ItSpecialist;

public class AdderToArray {
    public Book[] addBook(Book[] arrayOfBookInStore, Book book){
        Book[] copyOfArray = new Book[arrayOfBookInStore.length + 1];
        System.arraycopy(arrayOfBookInStore, 0, copyOfArray, 0, arrayOfBookInStore.length);
        copyOfArray[copyOfArray.length - 1] = book;
        return copyOfArray;
    }

    public Order[] addOrder(Order[] arrayOfOrder, Order order){
        Order[] copyOfArray = new Order[arrayOfOrder.length + 1];
        System.arraycopy(arrayOfOrder, 0, copyOfArray, 0, arrayOfOrder.length);
        copyOfArray[copyOfArray.length - 1] = order;
        return copyOfArray;
    }

    public Employee[] addEmployee(Employee[] arrayOfEmployee, Employee employee){
        Employee[] copyOfArray = new Employee[arrayOfEmployee.length + 1];
        System.arraycopy(arrayOfEmployee, 0, copyOfArray, 0, arrayOfEmployee.length);
        copyOfArray[copyOfArray.length - 1] = employee;
        return copyOfArray;
    }
    public Customer[] addCustomer(Customer[] arrayOfCustomer, Customer customer){
        Customer[] copyOfArray = new Customer[arrayOfCustomer.length + 1];
        System.arraycopy(arrayOfCustomer, 0, copyOfArray, 0, arrayOfCustomer.length);
        copyOfArray[copyOfArray.length - 1] = customer;
        return copyOfArray;
    }




}
