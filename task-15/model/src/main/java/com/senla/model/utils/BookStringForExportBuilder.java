package com.senla.model.utils;

import com.senla.model.entity.Book;


public class BookStringForExportBuilder {
    public static String bookStringBuilder(Book book){
        StringBuilder stringOfOrder = new StringBuilder();
        stringOfOrder.append(book.getId() + "," + book.getAuthor() + "," + book.getTitle()+ "," +
                book.getPrice() + "," + book.getArriveDate() + ","  + book.getPublicationDate());
        String s = stringOfOrder.toString();
        return s;
    }
}
