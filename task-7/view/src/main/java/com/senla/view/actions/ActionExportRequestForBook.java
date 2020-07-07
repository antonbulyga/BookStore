package main.java.com.senla.view.actions;

import annotation.MyInject;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.сontrollers.RequestForBookController;
import main.java.com.senla.view.api.IAction;

import java.util.List;

public class ActionExportRequestForBook implements IAction {
    @MyInject(key = "requestForBookFile")
    private String path = null;
    @Override
    public void execute(){
        List<RequestForBook> requestForBookList = RequestForBookController.getInstance().getListOfRequestForBook();
        ExportHelper.write(null, null, null, requestForBookList, path);
    }
}
