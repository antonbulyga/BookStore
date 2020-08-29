package main.java.com.senla.model.utils;

import main.java.com.senla.model.entity.Book;


public class BookStringForExportBuilder {
    public static String bookStringBuilder(Book book){
        String string = null;
        StringBuilder stringOfOrder = new StringBuilder(string);
        stringOfOrder.append(book.getId() + " " + book.getAuthor() + " " + book.getTitle()+ " " +
                book.getPrice() + " " + book.getPublicationDate());
        return string;
    }
}
