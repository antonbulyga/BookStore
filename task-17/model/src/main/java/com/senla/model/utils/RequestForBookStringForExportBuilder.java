package com.senla.model.utils;

import com.senla.model.entity.RequestForBook;

public class RequestForBookStringForExportBuilder {
    public static String requestForBookStringBuilder(RequestForBook requestForBook) {
        StringBuilder stringOfRequestForBook = new StringBuilder();
        stringOfRequestForBook.append(requestForBook.getId() + "," + requestForBook.getTitleOfBook()+ "," + requestForBook.getAuthorOfBook() +  "," + requestForBook.getRequestStatus() +
                "," + requestForBook.getOrder().getId());
        String s = stringOfRequestForBook.toString();
        return s;
    }
}
