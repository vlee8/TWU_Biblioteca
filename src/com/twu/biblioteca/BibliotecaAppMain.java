package com.twu.biblioteca;

import java.io.PrintStream;

/**
 * Created by vlee on 11/08/2016.
 */
public class BibliotecaAppMain {
    public static void main(String[] args) {
        PrintStream printStream = System.out;
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printStream);
        bibliotecaApp.run();
    }
}
