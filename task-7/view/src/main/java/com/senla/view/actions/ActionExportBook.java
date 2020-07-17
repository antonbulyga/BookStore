package main.java.com.senla.view.actions;

import annotation.MyInject;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.view.api.IAction;

import java.util.List;

public class ActionExportBook implements IAction {
    @MyInject(key = "bookFile")
    private String path;

    @Override
    public void execute() throws IllegalAccessException {
        List<Book> bookList = BookController.getInstance().getListOfBooksInStoreHouse();
        ExportHelper.write(null, bookList, null, null, path);
    }
}
