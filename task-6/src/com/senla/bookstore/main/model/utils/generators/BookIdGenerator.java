package com.senla.bookstore.main.model.utils.generators;

public class BookIdGenerator {
    private static int bookId = 0;

    public static int getBookId(){
        bookId++;
        return bookId;
    }
}
