package com.senla.model.enumeration;

public enum SQLOrder {
    GET_ORDER("SELECT * FROM orders WHERE orders.id = (?)"),
    INSERT_ORDER("INSERT INTO orders (id, date_of_order, date_of_done_order, price_of_order,order_status,id_customer) " +
            "VALUES ( DEFAULT, (?) , (?), (?), (?), (?))"),
    DELETE_ORDER("DELETE FROM orders WHERE id_Customer = (?)"),
    UPDATE_ORDER("UPDATE orders SET date_of_order = (?), date_of_done_order = (?), price_of_order = (?), order_status = (?), id_customer = (?) WHERE id = (?)"),
    GET_ALL_ORDER("SELECT * FROM orders"),
    GET_BOOK_FROM_ORDER("SELECT * FROM book JOIN orders ON book.order_id = orders.id WHERE orders.id = (?)"),
    GET_CUSTOMER_FROM_ORDER("SELECT * FROM orders JOIN customer ON orders.id_customer = customer.id WHERE orders.id = (?)");

    public String query;
    SQLOrder(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
