package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.mockito.Mockito.*;

/**
 * Created by vlee on 11/08/2016.
 */
public class BibliotecaAppTest {
    PrintStream outputStream;
    BufferedReader inputStream;
    BibliotecaApp bibliotecaApp;
    Books books;

    @Before
    public void setUp() throws Exception {
        outputStream = mock(PrintStream.class);
        inputStream = mock(BufferedReader.class);
        books = mock(Books.class);
        bibliotecaApp = new BibliotecaApp(outputStream, inputStream, books);
        when(inputStream.ready()).thenReturn(true);
        when(inputStream.readLine()).thenReturn("Q");
    }

    @Test
    public void shouldDisplayWelcomeMessage() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("Welcome!");
    }

    @Test
    public void shouldShowOptionToListBooks() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("[L]ist Books");
    }

    @Test
    public void shouldListBooksWhenOptionIsSelected() throws Exception {
        when(inputStream.readLine())
                .thenReturn("L")
                .thenReturn("Q");

        bibliotecaApp.run();

        verify(books).displayList();
    }

    @Test
    public void shouldRepeatMenuOptionsIfInputIsInvalid() throws Exception {
        when(inputStream.readLine())
                .thenReturn("NotSupported")
                .thenReturn("L")
                .thenReturn("Q");

        bibliotecaApp.run();

        verify(outputStream, times(2)).println("[L]ist Books");
        verify(books).displayList();
        verify(outputStream).println("Select a valid option!");
    }

    @Test
    public void shouldRepeatMenuOptionsIfIoExceptionIsThrown() throws Exception {
        when(inputStream.readLine())
                .thenThrow(IOException.class)
                .thenReturn("L")
                .thenReturn("Q");
        bibliotecaApp.run();

        verify(outputStream, times(2)).println("[L]ist Books");
        verify(books).displayList();
    }

    @Test
    public void shouldShowOptionToQuit() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("[Q]uit");
    }

    @Test
    public void shouldQuitWhenOptionIsSelected() throws Exception {
        when(inputStream.readLine()).thenReturn("Q");

        bibliotecaApp.run();

        verify(outputStream, times(1)).println("[L]ist Books");
    }

    @Test
    public void shouldNotQuitStraightAway() throws Exception {
        when(inputStream.ready())
                .thenReturn(false)
                .thenReturn(true);

        bibliotecaApp.run();

        verify(inputStream, atLeast(2)).ready();
    }

}