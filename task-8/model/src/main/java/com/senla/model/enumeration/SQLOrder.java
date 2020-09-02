package main.java.com.senla.model.enumeration;

public enum SQLOrder {
    GET_ORDER("SELECT * FROM `order` WHERE id = (?)"),
    INSERT_ORDER("INSERT INTO `order` (id, date_of_order, date_of_done_order, price_of_order,order_status,id_customer) " +
            "VALUES ( DEFAULT, (?) , (?), (?), (?), (?))"),
    DELETE_ORDER("DELETE FROM `order` WHERE id_Customer = (?)"),
    UPDATE_ORDER("UPDATE `order` SET date_of_order = (?), date_of_done_order = (?), price_of_order = (?), order_status = (?), id_customer = (?) WHERE id = (?)"),
    GET_ALL_ORDER("SELECT * FROM `order`"),
    GET_BOOK_FROM_ORDER("select book.id from `order` join book on book.order_id = `order`.id where `order`.id = (?)"),
    GET_REQUEST_FRO_BOOK_FROM_ORDER("select requestForBook.id from requestForBook join `order` on requestForBook.id_order = `order`.id where `order`.id = (?)");




    public String QUERY;
    SQLOrder(String QUERY) {
        this.QUERY = QUERY;
    }
}
