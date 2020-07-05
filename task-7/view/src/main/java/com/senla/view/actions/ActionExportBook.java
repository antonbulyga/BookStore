package main.java.com.senla.view.actions;

import annotation.AnnotationAnalyzer;
import annotation.Config;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.utils.PropertyData;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.view.api.IAction;

import java.util.List;

public class ActionExportBook implements IAction {
    @Config()
    private String key;

    public ActionExportBook() {
    }

    @Override
    public void execute() throws IllegalAccessException {
        ActionExportBook actionExportBook = new ActionExportBook();
        AnnotationAnalyzer.setKeyFromAnnotation(actionExportBook);
        List<Book> bookList = BookController.getInstance().getListOfBooksInStoreHouse();
        String path = PropertyData.getProperty(key);
        ExportHelper.write(null, bookList, null, null, path);
    }
}
