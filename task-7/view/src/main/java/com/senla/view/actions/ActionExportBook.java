package main.java.com.senla.view.actions;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.view.api.IAction;

import java.util.List;

public class ActionExportBook implements IAction {

    @Override
    public void execute() {
        List<Book> bookList = BookController.getInstance().getListOfBooksInStoreHouse();
        ExportHelper.write(null, bookList, null, null, "bookFile");
    }
}
