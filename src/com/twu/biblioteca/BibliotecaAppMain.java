package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by vlee on 11/08/2016.
 */
public class BibliotecaAppMain {
    public static void main(String[] args) throws IOException {
        PrintStream printStream = System.out;
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printStream, inputStream, new Books(printStream), new Movies(printStream));
        bibliotecaApp.run();
    }
}
