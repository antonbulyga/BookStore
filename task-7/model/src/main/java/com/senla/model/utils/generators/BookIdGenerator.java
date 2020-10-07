package main.java.com.senla.model.utils.generators;

public class BookIdGenerator {
    private static int bookId = 0;

    public static int getBookId(){
        bookId++;
        return bookId;
    }
}
