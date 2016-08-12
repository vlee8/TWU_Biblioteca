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
        outputStream.println(Commands.WelcomeScreen.WELCOME_SCREEN_MESSAGE);
        parseMenuOption();
    }


    // To List Books, enter L
    // To Quit, enter Q
    // To Check Out Book, enter C followed by a space and its ID which is given by the List
    // To Return Book, enter R followed by a space and its ID which is given by the List
    private void parseMenuOption() {
        outputStream.println("[L]: List Books");
        outputStream.println("[Q]: Quit");
        outputStream.println("[C <ID>]: Check Out Book");
        outputStream.println("[R <ID>]: Return Book");

        try {
            while (!inputStream.ready()) {
                Thread.sleep(100);
            }

            String userInput = inputStream.readLine();
            if (Commands.MainMenu.LIST_BOOKS_COMMAND.equals(userInput)) {
                books.displayList();
            } else if (Commands.MainMenu.MAIN_MENU_QUIT_COMMAND.equals(userInput)) {
                return;
            } else if (checkIfBookCommand(userInput).equals("")) {
                outputStream.println(Commands.MainMenu.MAIN_MENU_INVALID_SELECTION_COMMAND);
            } else if (!checkIfBookCommand(userInput).equals("")) {
                performBookCommand(userInput);
            }

            parseMenuOption();
        } catch (IOException e) {
            parseMenuOption();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String checkIfBookCommand(String fullCommand) {
        int index = fullCommand.lastIndexOf(" ");
        if (index > -1) {
            return fullCommand.substring(0, fullCommand.lastIndexOf(" "));
        }
        return "";
    }

    public int getBookIDFromCommand(String fullCommand) {
        int bookID;
        fullCommand = fullCommand.substring(fullCommand.lastIndexOf(" ") + 1, fullCommand.length());
        try {
            bookID = Integer.parseInt(fullCommand);
        }
        catch (NumberFormatException e) {
            return -1;
        }
        return bookID;
    }

    public void performBookCommand(String userInput)
    {
        String command = checkIfBookCommand(userInput);
        int bookID = getBookIDFromCommand(userInput);

        switch (command)
        {
            case Commands.MainMenu.CHECKOUT_COMMAND:
                books.checkOutBook(bookID);
                break;
            case Commands.MainMenu.RETURN_COMMAND:
                books.returnBook(bookID);
                break;
            default:
                break;
        }
    }

}
