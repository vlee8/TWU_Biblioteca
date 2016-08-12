package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vlee on 12/08/2016.
 */
public class BooksTest {

    @Test
    public void shouldDisplayBooks() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        Books books = new Books(outputStream);
        //books.addBook(new Book("This is a book title", "Author", 2016));
        //books.addBook(new Book("This is still a book title", "Author Two", 2017));
        books.displayList();

        verify(outputStream).println("ID | Title | Author | Year");
        verify(outputStream).println("--------------------------------");
        verify(outputStream).println("0 | This is a book title | Author | 2016");
        verify(outputStream).println("1 | This is still a book title | Author Two | 2017");
    }

    @Test
    public void shouldNotDisplayBookWhenCheckedOut() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        Books books = new Books(outputStream);
        books.checkOutBook(0);
        books.displayList();

        verify(outputStream).println("Thank you! Enjoy the book");
        verify(outputStream).println("ID | Title | Author | Year");
        verify(outputStream).println("--------------------------------");
        verify(outputStream).println("1 | This is still a book title | Author Two | 2017");
    }

    @Test
    public void shouldDisplaySuccessfulMessageForCheckingOutBook() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        Books books = new Books(outputStream);
        books.checkOutBook(0);

        verify(outputStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldDisplayUnsuccessfulMessageForCheckingOutInvalidBook() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        Books books = new Books(outputStream);
        books.checkOutBook(0);
        books.checkOutBook(0);

        verify(outputStream).println("That book is not available.");
    }

    @Test
    public void shouldDisplayUnsuccessfulMessageForCheckingOutNonExistentBook() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        Books books = new Books(outputStream);
        books.checkOutBook(2);

        verify(outputStream).println("That book is not available.");
    }




}