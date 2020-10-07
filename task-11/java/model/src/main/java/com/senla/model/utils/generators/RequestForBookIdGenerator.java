package com.senla.model.utils.generators;

public class RequestForBookIdGenerator {
    private static int requestForBookId = 0;

    public static int getRequestForBookId(){
        requestForBookId++;
        return requestForBookId;
    }
}
