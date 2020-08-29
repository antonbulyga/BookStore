package main.java.com.senla.model.enumeration;

public enum SQLCustomer {
    GET_CUSTOMER("SELECT * FROM customer WHERE id = (?)"),
    INSERT_CUSTOMER("INSERT INTO customer (id, age , name) VALUES ( DEFAULT, (?) , (?))"),
    DELETE_CUSTOMER("DELETE FROM customer WHERE id = (?)"),
    UPDATE_CUSTOMER("UPDATE customer SET age = (?), name = (?) WHERE id = (?)"),
    GET_ALL_CUSTOMERS("SELECT * FROM customer");

    public String query;
    SQLCustomer(String query) {
        this.query = query;
    }
}
