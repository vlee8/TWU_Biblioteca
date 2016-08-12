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
                outputStream.println("List of Books");
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
