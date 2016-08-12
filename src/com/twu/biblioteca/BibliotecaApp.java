package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class BibliotecaApp {

    private final PrintStream outputStream;
    private final BufferedReader inputStream;
    private final Books books;

    public BibliotecaApp(PrintStream outputStream, BufferedReader inputStream, Books books) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.books = books;
    }

    public void run() {
        outputStream.println("Welcome!");
        // TODO loop through list of options

        // Wait for user input
        parseMenuOption();
    }

    private void parseMenuOption() {
        outputStream.println("[L]ist Books");
        outputStream.println("[Q]uit");

        try {
            while (!inputStream.ready()) {
                Thread.sleep(100);
            }

            String userInput = inputStream.readLine();
            if ("L".equals(userInput)) {
                books.displayList();
            } else if ("Q".equals(userInput)) {
                return;
            } else {
                outputStream.println("Select a valid option!");
                parseMenuOption();
            }
        } catch (IOException e) {
            parseMenuOption();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
