package com.senla.model.enumeration;

public enum SQLRequestForBook {
    GET_REQUEST_FOR_BOOK("SELECT * FROM request_for_book join orders on request_for_book.order_id = orders.id WHERE request_for_book.id = (?)"),
    INSERT_REQUEST_FOR_BOOK("INSERT INTO request_for_book (id, title, author, request_for_book_status, order_id) VALUES ( DEFAULT, (?) , (?), (?), (?))"),
    DELETE_REQUEST_FOR_BOOK("DELETE FROM request_for_book WHERE id = (?)"),
    UPDATE_REQUEST_FOR_BOOK("UPDATE request_for_book SET title = (?), author = (?), request_for_book_status = (?), order_id = (?) WHERE id = (?)"),
    GET_ALL_REQUEST_FOR_BOOK("SELECT * FROM request_for_book"),
    GET_REQUEST_FROM_BOOK_FROM_ORDER("SELECT * FROM request_for_book WHERE order_id = (?)");

    public String query;
    SQLRequestForBook(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
