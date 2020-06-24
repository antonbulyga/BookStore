package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.RequestForBook;
import com.senla.bookstore.main.model.utils.RequestForBookStringForExportBuilder;
import com.senla.bookstore.main.model.сontrollers.RequestForBookController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ActionExportRequestForBook implements IAction {

    @Override
    public void execute(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
        List<RequestForBook> requestForBookList = RequestForBookController.getInstance().getListOfRequestForBook();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(property.getProperty("requestForBookFile")))) {
            for (int i = 0; i < requestForBookList.size(); i++) {
                writer.write(RequestForBookStringForExportBuilder.orderStringBuilder(requestForBookList.get(i)) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            System.err.println("We have no file");
        }
    }
}
