package main.java.com.senla.model.utils;

import main.java.com.senla.model.entity.RequestForBook;

public class RequestForBookStringForExportBuilder {
    public static String requestForBookStringBuilder(RequestForBook requestForBook) {
        String string = null;
        StringBuilder stringOfRequestForBook = new StringBuilder(string);
        stringOfRequestForBook.append(requestForBook.getId() + " " + requestForBook.getTitleOfBook()+ " " + requestForBook.getAuthorOfBook() +  " " + requestForBook.getRequestStatus() +
                " " + requestForBook.getOrder().getId());
        return string;
    }
}
