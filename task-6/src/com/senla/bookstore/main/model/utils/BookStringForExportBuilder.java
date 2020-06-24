package com.senla.bookstore.main.model.utils;

import com.senla.bookstore.main.model.entity.Book;


public class BookStringForExportBuilder {
    public static String bookStringBuilder(Book book){
        String string = null;
        StringBuilder stringOfOrder = new StringBuilder(string);
        stringOfOrder.append(book.getId() + " " + book.getAuthor() + " " + book.getTitle()+ " " +
                book.getPrice() + " " +  book.getBookStatus() + " " + book.getPublicationDate());
        return string;
    }
}
