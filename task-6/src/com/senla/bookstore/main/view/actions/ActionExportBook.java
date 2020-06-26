package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.utils.ExportHelper;
import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.view.api.IAction;

import java.util.List;

public class ActionExportBook implements IAction {

    @Override
    public void execute() {
        List<Book> bookList = BookController.getInstance().getListOfBooksInStoreHouse();
        ExportHelper.write(null, bookList, null, null, "bookFile");
    }
}
