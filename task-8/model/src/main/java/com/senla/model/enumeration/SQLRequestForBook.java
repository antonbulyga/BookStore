package main.java.com.senla.model.enumeration;

public enum SQLRequestForBook {
    GET_REQUEST_FOR_BOOK("SELECT * FROM requestForBook WHERE id = (?)"),
    INSERT_REQUEST_FOR_BOOK("INSERT INTO requestForBook (id, id_book , requestForBook_status, id_order) VALUES ( DEFAULT, (?) , (?), (?))"),
    DELETE_REQUEST_FOR_BOOK("DELETE FROM requestForBook WHERE id = (?)"),
    UPDATE_REQUEST_FOR_BOOK("UPDATE requestForBook SET id_Book = (?), requestForBook_status = (?), id_order = (?) WHERE id = (?)"),
    GET_ALL_REQUEST_FOR_BOOK("SELECT * FROM requestForBook");

    public String QUERY;
    SQLRequestForBook(String QUERY) {
        this.QUERY = QUERY;
    }
}
