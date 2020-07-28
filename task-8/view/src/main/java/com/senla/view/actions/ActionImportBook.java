package main.java.com.senla.view.actions;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.model.сontrollers.StockLevelController;
import main.java.com.senla.view.api.IAction;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ActionImportBook implements IAction {
    @Override
    public void execute() {
        BookController.getInstance().importBook();
    }
}

