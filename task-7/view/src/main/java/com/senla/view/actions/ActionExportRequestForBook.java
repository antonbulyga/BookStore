package main.java.com.senla.view.actions;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.сontrollers.RequestForBookController;
import main.java.com.senla.view.api.IAction;

import java.util.List;
@Component
public class ActionExportRequestForBook implements IAction {
    @MyInject(key = "requestForBookFile")
    private String path;
    @Override
    public void execute(){
        List<RequestForBook> requestForBookList = RequestForBookController.getInstance().getListOfRequestForBook();
        ExportHelper.write(null, null, null, requestForBookList, path);
    }
}
