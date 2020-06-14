package com.senla.bookstore.view.Actions;

import com.senla.bookstore.controller.Test;
import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Store;
import com.senla.bookstore.service.BookService;
import com.senla.bookstore.view.IAction;

import java.util.List;

public class ActionSortBookByPrice implements IAction {
    BookService bookService = new BookService();
    Store store = new Store();

    @Override
    public void execute() {
        List<Book> arrayOfBooksInStore = store.getListOfBooksInStorehouse();
        bookService.sortBookByPrice(arrayOfBooksInStore);

    }
}
