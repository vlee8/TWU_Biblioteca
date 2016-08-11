package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class BibliotecaApp {

    private PrintStream outputStream;
    private BufferedReader inputStream;

    public BibliotecaApp(PrintStream outputStream, BufferedReader inputStream) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    public void run() {
        outputStream.println("Welcome!");
        // TODO loop through list of options
        outputStream.println("[L]ist Books");
        outputStream.println("[Q]uit");
        // Wait for user input
        try {
            if ("L".equals(inputStream.readLine())) {
                outputStream.println("List of Books");
            }
        } catch (IOException e) {
            outputStream.println("[L]ist Books");
        }

    }
}
