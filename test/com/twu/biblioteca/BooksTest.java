package com.twu.biblioteca;

import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by vlee on 12/08/2016.
 */
public class BooksTest {
    @Test
    public void shouldDisplayBooks() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        Books books = new Books(outputStream);
        books.addBook(new Book("This is a book title", "Vicky", 2016));
        books.addBook(new Book("This is still a book title", "Vicky Lee", 2017));
        books.displayList();

        verify(outputStream).println("Title | Author | Year");
        verify(outputStream).println("This is a book title | Vicky | 2016");
        verify(outputStream).println("This is still a book title | Vicky Lee | 2017");
    }
}