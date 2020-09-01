package com.senla.model.enumeration;

public enum SQLOrder {
    GET_ORDER("SELECT * FROM `order` WHERE `order`.id = (?)"),
    INSERT_ORDER("INSERT INTO `order` (id, date_of_order, date_of_done_order, price_of_order,order_status,id_customer) " +
            "VALUES ( DEFAULT, (?) , (?), (?), (?), (?))"),
    DELETE_ORDER("DELETE FROM `order` WHERE id_Customer = (?)"),
    UPDATE_ORDER("UPDATE `order` SET date_of_order = (?), date_of_done_order = (?), price_of_order = (?), order_status = (?), id_customer = (?) WHERE id = (?)"),
    GET_ALL_ORDER("SELECT * FROM request_for_book JOIN `order` ON request_for_book.order_id = `order`.id JOIN customer ON order.id_customer = customer.id"),
    GET_BOOK_FROM_ORDER("SELECT * FROM book JOIN `order` ON book.order_id = `order`.id WHERE `order`.id = (?)"),
    GET_CUSTOMER_FROM_ORDER("SELECT * FROM `order` JOIN customer ON `order`.id_customer = customer.id WHERE `order`.id = (?)");

    public String query;
    SQLOrder(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
