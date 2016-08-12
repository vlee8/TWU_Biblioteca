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
        outputStream.println("ID | Title | Author | Year");
        outputStream.println("--------------------------------");
        int bookID = -1;
        for (Book book : listOfBooks) {
            bookID++;
            if (!book.getCheckedOut()) {
                outputStream.println(bookID + " | " + book.getTitle() + " | " + book.getAuthor() + " | " + book.getYear());
            }
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
                outputStream.println(Commands.BooksCommands.SUCCESSFUL_CHECKOUT_MESSAGE);
            } else {
                outputStream.println(Commands.BooksCommands.UNSUCCESSFUL_CHECKOUT_MESSAGE);
            }
        }
        catch (IndexOutOfBoundsException e) {
            outputStream.println(Commands.BooksCommands.UNSUCCESSFUL_CHECKOUT_MESSAGE);
        }
    }

    public void returnBook(int bookID)
    {
        try {
            if (listOfBooks.get(bookID).getCheckedOut()) {
                listOfBooks.get(bookID).setCheckedOut(false);
                outputStream.println(Commands.BooksCommands.SUCCESSFUL_RETURN_MESSAGE);
            } else {
                outputStream.println(Commands.BooksCommands.UNSUCCESSFUL_RETURN_MESSAGE);
            }
        }
        catch (IndexOutOfBoundsException e) {
            outputStream.println(Commands.BooksCommands.UNSUCCESSFUL_RETURN_MESSAGE);
        }
    }

}
