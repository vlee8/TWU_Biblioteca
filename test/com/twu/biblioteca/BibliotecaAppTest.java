package com.twu.biblioteca;

import org.junit.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by vlee on 11/08/2016.
 */
public class BibliotecaAppTest {
    @Test
    public void shouldDisplayWelcomeMessage() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(outputStream);
        bibliotecaApp.run();

        verify(outputStream).print("Welcome!");
    }
}