package com.senla.bookstore.model;

import java.util.Arrays;

public class BookService {
    public Book[] addBook(Book[] arrayOfBookInStore, Book book){
        Book[] copyOfArray = Arrays.copyOf(arrayOfBookInStore, arrayOfBookInStore.length + 1);
        copyOfArray[copyOfArray.length - 1] = book;
        return copyOfArray;
    }

    public void sortBookByPrice(Book[] books) {
        BookPriceComparator bookPriceComparator = new BookPriceComparator();
        Arrays.sort(books, bookPriceComparator);
        System.out.println("Array of books sorted by price: ");
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getTitle() + " - " + books[i].getPrice());
        }
    }

    public void sortBookByAuthor(Book[] books) {
        BookAlphabeticalComparator bookAlphabeticalComparator = new BookAlphabeticalComparator();
        Arrays.sort(books, bookAlphabeticalComparator);
        System.out.println("Array of books sorted by autor: ");
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getTitle() + " - " +books[i].getAutor());
        }
    }

    public void sortBookByDateArrive(Book[] books) {
        BookArriveDataComparator bookDataComparator = new BookArriveDataComparator();
        Arrays.sort(books, bookDataComparator);
        System.out.println("Array of books sorted by date of arrive: ");
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getTitle() + " - " +books[i].getArriveDate());
        }
    }
    public void sortBookByAvailabilityInStock(Book[] books) {
        BookAvailabilityComparator bookAvailabilityComparator = new BookAvailabilityComparator();
        Arrays.sort(books, bookAvailabilityComparator);
        System.out.println("Array of books sorted by availability in stock: ");
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getTitle() + "-" + books[i].getBookStatus());
        }
    }

    public void sortBookByPublicationDate(Book[] books) {
        BookAvailabilityComparator bookAvailabilityComparator = new BookAvailabilityComparator();
        Arrays.sort(books, bookAvailabilityComparator);
        System.out.println("Array of books sorted by date of publication: ");
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getTitle() + " - " + books[i].getArriveDate());
        }
    }
}
