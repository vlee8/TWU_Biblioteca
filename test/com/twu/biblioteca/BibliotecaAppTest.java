package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by vlee on 11/08/2016.
 */
public class BibliotecaAppTest {
    PrintStream outputStream;
    BufferedReader inputStream;
    BibliotecaApp bibliotecaApp;

    @Before
    public void setUp() throws Exception {
        outputStream = mock(PrintStream.class);
        inputStream = mock(BufferedReader.class);
        bibliotecaApp = new BibliotecaApp(outputStream, inputStream);
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
        // when the user inputs "L" into the menu prompt
        when(inputStream.readLine()).thenReturn("L");

        bibliotecaApp.run();

        verify(outputStream).println("List of Books");
    }

    @Test
    public void shouldRepeatMenuOptionsIfIoExceptionIsThrown() throws Exception {
        // when the user inputs "L" into the menu prompt
        when(inputStream.readLine()).thenThrow(IOException.class);
        bibliotecaApp.run();

        verify(outputStream, times(2)).println("[L]ist Books");
    }

    @Test
    public void shouldShowOptionToQuit() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("[Q]uit");
    }
}