package com.senla.bookstore.main.model.entity;

public class StockLevel {
   private int id;
   private Book book;
   private int count;

    public StockLevel(int id, Book book, int count) {
        this.id = id;
        this.book = book;
        this.count = count;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
