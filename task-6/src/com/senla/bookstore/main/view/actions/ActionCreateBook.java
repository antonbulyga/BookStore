package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.utils.DoubleInput;
import com.senla.bookstore.main.model.utils.StringInput;
import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.IOException;



public class ActionCreateBook implements IAction {

    @Override
    public void execute() throws IOException {
        int id;
        String title = null;
        String author = null;
        double price = 0;
        String publicationDate = null;
        String bookStatus = "IN_STOCK";
        if(BookController.getInstance().getListOfBooksInStoreHouse() != null){
            id = BookController.getInstance().getListOfBooksInStoreHouse().size() + 1;
        }
        else {
            id = 0;
        }
        while (title == null) {
            System.out.println("Fill in the title of the book");
            title = StringInput.getStringInput();
            if(title.equals(" ")){
                title = null;
            }
        }
        while (author == null){
            System.out.println("Fill in the author of the book");
            author = StringInput.getStringInput();
            if(author.equals(" ")){
                author = null;
            }
        }
        while (price == 0){
            System.out.println("Fill in the price of the book");
            price = DoubleInput.getDoubleInput();
            if(price < 0){
                price = 0;
            }
        }
        while (publicationDate == null){
            System.out.println("Fill in the publication date");
            publicationDate = StringInput.getStringInput();
            if(publicationDate.equals(" ")){
                publicationDate = null;
            }
        }

        Book book = BookController.getInstance().createBook(id, title, author, price, bookStatus, publicationDate);
    }
}
