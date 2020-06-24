package com.senla.bookstore.main.model.utils;

import com.senla.bookstore.main.model.entity.RequestForBook;

public class RequestForBookStringForExportBuilder {
    public static String orderStringBuilder(RequestForBook requestForBook) {
        String string = null;
        StringBuilder stringOfRequestForBook = new StringBuilder(string);
        stringOfRequestForBook.append(requestForBook.getId() + " " + requestForBook.getBook().getId() + " " + requestForBook.getRequestStatus() +
                " " + requestForBook.getOrder().getId());
        return string;
    }
}
