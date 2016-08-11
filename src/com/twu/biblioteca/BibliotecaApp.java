package com.twu.biblioteca;

import java.io.OutputStream;
import java.io.PrintStream;

public class BibliotecaApp {

    private PrintStream outputStream;

    public BibliotecaApp(PrintStream outputStream) {
        this.outputStream = outputStream;
    }


    public void run() {
        outputStream.print("Welcome!");
    }
}
