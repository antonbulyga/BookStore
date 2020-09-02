package main.java.com.senla.model.enumeration;

public enum SQLBook {
    GET_BOOK("SELECT * FROM book WHERE id = (?)"),
    INSERT_BOOK("INSERT INTO book (id, title , author, price, book_status, arrive_date, publication_date) VALUES ( DEFAULT, (?) , (?), (?), (?), (?), (?))"),

    DELETE_BOOK("DELETE FROM book WHERE id = (?)"),
    UPDATE_BOOK("UPDATE book SET title = (?), author = (?), price = (?), book_status = (?), arrive_date = (?), publication_date = (?) WHERE id = (?)"),
    GET_ALL_BOOKS("SELECT * FROM book");

    public String QUERY;
    SQLBook(String QUERY) {
        this.QUERY = QUERY;
    }
}
