package main.java.com.senla.model.utils;

import main.java.com.senla.model.entity.RequestForBook;

public class RequestForBookStringForExportBuilder {
    public static String orderStringBuilder(RequestForBook requestForBook) {
        String string = null;
        StringBuilder stringOfRequestForBook = new StringBuilder(string);
        stringOfRequestForBook.append(requestForBook.getId() + " " + requestForBook.getBook().getId() + " " + requestForBook.getRequestStatus() +
                " " + requestForBook.getOrder().getId());
        return string;
    }
}
