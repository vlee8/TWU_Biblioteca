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
        addPreexistingBooks();
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

    public void addPreexistingBooks()
    {
        Book book = new Book("This is a book title", "Author", 2016);
        addBook(book);
        book = new Book("This is still a book title", "Author Two", 2017);
        addBook(book);
    }

    public void checkOutBook(int bookID)
    {
        try {
            if (!listOfBooks.get(bookID).getCheckedOut()) {
                listOfBooks.get(bookID).setCheckedOut(true);
                outputStream.println("Thank you! Enjoy the book");
            } else {
                outputStream.println("That book is not available.");
            }
        }
        catch (IndexOutOfBoundsException e) {
            outputStream.println("That book is not available.");
        }
    }

}
