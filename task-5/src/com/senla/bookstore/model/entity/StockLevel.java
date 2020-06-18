package com.senla.bookstore.model.entity;

import com.senla.bookstore.model.entity.Book;

public class StockLevel {
   private Book book;
   private int count;

    public StockLevel(Book book, int count) {
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
