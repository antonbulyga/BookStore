package task6;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        Book[] arrayOfBookInStorehouse = main.initBook();
        Book[] arrayOfBookForFirstOrder = main.addBookForFirstOrder(arrayOfBookInStorehouse);
        Book[] arrayOfBookForSecondOrder = main.addBookForSecondOrder(arrayOfBookInStorehouse);
        Customer[] arrayOfCustomer = main.initCustomer();
        Employee[] arrayOfEmployee = main.initEmployee();

       createOrder(arrayOfBookForFirstOrder,arrayOfCustomer,arrayOfEmployee, arrayOfBookInStorehouse, arrayOfBookForSecondOrder);
    }


    public Book[] initBook(){
        AdderToArray adderToArray = new AdderToArray();
        Book[] arrayOfBookInStorehouse = new Book[0];
        Book book1 = new Book(1, "Война и мир", "Лев Толстой", 4, "Art");
        arrayOfBookInStorehouse = adderToArray.addBook(arrayOfBookInStorehouse, book1);
        Book book2 = new Book(1, "Преступление и наказание", "Федор Достоевский", 3.5, "Art");
        arrayOfBookInStorehouse = adderToArray.addBook(arrayOfBookInStorehouse, book2);
        Book book3 = new Book(1, "Мертвые души", "Гоголь Николай", 4, "Art");
        arrayOfBookInStorehouse = adderToArray.addBook(arrayOfBookInStorehouse, book3);
        Book book4 = new Book(1, "Война и мир", "Лев Толстой", 4, "Art");
        arrayOfBookInStorehouse = adderToArray.addBook(arrayOfBookInStorehouse, book4);
        Book book5 = new Book(1, "Руслан и Людмила", "Александр Пушкин", 5, "Art");
        arrayOfBookInStorehouse = adderToArray.addBook(arrayOfBookInStorehouse, book5);

        Book book6 = new Book(1, "Введение в психоанализ", "Зигмунд Фрейд", 7, "Psychology");
        arrayOfBookInStorehouse = adderToArray.addBook(arrayOfBookInStorehouse, book6);
        Book book7 = new Book(1, "Психология влияния", "Роберт Чалдини", 6, "Psychology");
        arrayOfBookInStorehouse = adderToArray.addBook(arrayOfBookInStorehouse, book7);
        Book book8 = new Book(1, "Как перестать беспокоитьс и начать жить", "Дейл Карнеги", 7, "Psychology");
        arrayOfBookInStorehouse = adderToArray.addBook(arrayOfBookInStorehouse, book8);
        return arrayOfBookInStorehouse;
    }
    public Book[]  addBookForFirstOrder(Book[] arrayOfBookInStorehouse){
        Book[] arrayOfBookForFirstOrder = new Book[arrayOfBookInStorehouse.length];
        arrayOfBookForFirstOrder[0] = arrayOfBookInStorehouse[0];
        arrayOfBookForFirstOrder[1] = arrayOfBookInStorehouse[2];
       return arrayOfBookForFirstOrder;
    }

    public Book[]  addBookForSecondOrder(Book[] arrayOfBookInStorehouse){
        Book[] arrayOfBookForSecondOrder = new Book[arrayOfBookInStorehouse.length];
        arrayOfBookForSecondOrder[0] = arrayOfBookInStorehouse[0];
        arrayOfBookForSecondOrder[1] = arrayOfBookInStorehouse[2];
        return arrayOfBookForSecondOrder;
    }

    public  Customer[] initCustomer(){
        AdderToArray adderToArray = new AdderToArray();
        Customer[] arrayOfCustomer = new Customer[0];
        Customer customer1 = new Customer(1, 32, "Иван Петров");
        arrayOfCustomer = adderToArray.addCustomer(arrayOfCustomer, customer1);
        Customer customer2 = new Customer(2, 23, "Мария Чернобровина");
        arrayOfCustomer = adderToArray.addCustomer(arrayOfCustomer, customer2);
        Customer customer3 = new Customer(3, 33, "Антон Чабанов");
        arrayOfCustomer = adderToArray.addCustomer(arrayOfCustomer, customer3);
     return arrayOfCustomer;
    }

    public Employee[] initEmployee(){
        AdderToArray adderToArray = new AdderToArray();
        Employee[] arrayOfEmployee = new Employee[0];
        Employee employee1 = new Employee(1, 23, "Антон Булыга");
        arrayOfEmployee = adderToArray.addEmployee(arrayOfEmployee, employee1);
        Employee employee2 = new Employee(2, 28, "Иван Сидоров");
        arrayOfEmployee = adderToArray.addEmployee(arrayOfEmployee, employee2);
        Employee employee3 = new Employee(3, 43, "Роман Санкевич");
        arrayOfEmployee = adderToArray.addEmployee(arrayOfEmployee, employee3);

        return arrayOfEmployee;
    }

    public static Order[] createOrder(Book[] arrayOfBookForFirstOrder, Customer[] customer , Employee[] employee, Book[] arrayOfBookInStorehouse, Book[] arrayOfBookForSecondOrder){
        AdderToArray adderToArray = new AdderToArray();
        Date date = new Date();
        Order[] arrayOfOrders = new Order[0];
        Order order1 = new Order(customer[1], employee[1], null, null, arrayOfBookForFirstOrder);
        order1.setDateOfOrder(date);
        for (int i = 0; i < arrayOfBookForFirstOrder.length; i++) {
            for (int j = 0; j < arrayOfBookForFirstOrder.length-1; j++) {
                if(arrayOfBookForFirstOrder[i].equals(arrayOfBookInStorehouse[j])){
                    order1.setDateOfDoneOrder(date);
                }
            }

        }
       arrayOfOrders = adderToArray.addOrder(arrayOfOrders, order1);
        Order order2 = new Order(customer[2], employee[2],null,null,arrayOfBookForSecondOrder);
        order1.setDateOfOrder(date);
        for (int i = 0; i < arrayOfBookForSecondOrder.length; i++) {
            for (int j = 0; j < arrayOfBookForSecondOrder.length-1; j++) {
                if(arrayOfBookForSecondOrder[i].equals(arrayOfBookInStorehouse[j])){
                    order1.setDateOfDoneOrder(date);
                }
            }

        }
        arrayOfOrders = adderToArray.addOrder(arrayOfOrders, order2);
        return arrayOfOrders;

    }
}
