package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlee on 12/08/2016.
 */
public class Books {
    private final PrintStream outputStream;
    private List<Book> listOfBooks;

    public Books(PrintStream outputStream) {
        this.outputStream = outputStream;
        listOfBooks = new ArrayList<>();
    }

    public void displayList() {
        outputStream.println("Title | Author | Year");
        for (Book book : listOfBooks) {
            outputStream.println(book.getTitle() + " | " + book.getAuthor() + " | " + book.getYear());
        }
    }

    public void addBook(Book book) {
        listOfBooks.add(book);
    }
}
