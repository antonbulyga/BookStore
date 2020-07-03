package main.java.com.senla.model.utils.generators;

public class RequestForBookIdGenerator {
    private static int RequestForBookId = 0;

    public static int getRequestForBookId(){
        RequestForBookId++;
        return RequestForBookId;
    }
}
