package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.RequestForBook;
import com.senla.bookstore.main.model.utils.ExportHelper;
import com.senla.bookstore.main.model.сontrollers.RequestForBookController;
import com.senla.bookstore.main.view.api.IAction;

import java.util.List;

public class ActionExportRequestForBook implements IAction {

    @Override
    public void execute(){
        List<RequestForBook> requestForBookList = RequestForBookController.getInstance().getListOfRequestForBook();
        ExportHelper.write(null, null, null, requestForBookList, "requestForBookFile");
    }
}
