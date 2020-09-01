package com.senla.model.enumeration;

public enum SQLBook {
    GET_BOOK("SELECT * FROM book WHERE id = (?)"),
    INSERT_BOOK("INSERT INTO book (id, title , author, price, arrive_date, publication_date) VALUES ( DEFAULT, (?) , (?), (?), (?), (?))"),
    DELETE_BOOK("DELETE FROM book WHERE id = (?)"),
    UPDATE_BOOK("UPDATE book SET title = (?), author = (?), price = (?), arrive_date = (?), publication_date = (?) WHERE id = (?)"),
    GET_ALL_BOOKS("select * from book");

    public String query;
    SQLBook(String query) {
        this.query = query;
    }
    public String getQuery() {
        return query;
    }
}
