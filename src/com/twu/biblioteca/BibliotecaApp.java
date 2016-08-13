package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class BibliotecaApp {

    private final PrintStream outputStream;
    private final BufferedReader inputStream;
    private final Books books;
    private final Movies movies;
    private final Accounts accounts;
    private Account loggedInAccount;

    public BibliotecaApp(PrintStream outputStream, BufferedReader inputStream, Books books, Movies movies, Accounts accounts) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.books = books;
        this.movies = movies;
        this.accounts = accounts;
        this.loggedInAccount = new Account(Commands.Login.NOT_LOGGED_IN_LIBRARY_NUMBER_PLACEHOLDER,
                Commands.Login.NOT_LOGGED_IN_PASSWORD_PLACEHOLDER, "", "", "");
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
        outputStream.println("[M]: List Movies");
        outputStream.println("[CM <ID>]: Check Out Movie");
        outputStream.println("[RM <ID>]: Return Movie");

        if (loggedInAccount.getLibraryNumber().equals(Commands.Login.NOT_LOGGED_IN_LIBRARY_NUMBER_PLACEHOLDER) &&
                loggedInAccount.getPassword().equals(Commands.Login.NOT_LOGGED_IN_PASSWORD_PLACEHOLDER)) {
            outputStream.println("[LA <LIBRARY NUMBER> <PASSWORD>]: Login To Account");
        } else {
            outputStream.println("Logged in as " + loggedInAccount.getLibraryNumber());
            printDetails();
        }

        try {
            while (!inputStream.ready()) {
                Thread.sleep(100);
            }

            String userInput = inputStream.readLine();
            if (Commands.MainMenu.LIST_BOOKS_COMMAND.equals(userInput)) {
                books.displayList();
            } else if (Commands.MainMenu.LIST_MOVIES_COMMAND.equals(userInput)) {
                movies.displayList();
            } else if (Commands.MainMenu.MAIN_MENU_QUIT_COMMAND.equals(userInput)) {
                return;
            } else if (!checkIfLoginCommand(userInput).equals("")) {

                login(userInput);

            } else if (checkIfBookCommand(userInput).equals("")) {
                outputStream.println(Commands.MainMenu.MAIN_MENU_INVALID_SELECTION_COMMAND);
            } else if (!checkIfBookCommand(userInput).equals("")) {
                if (checkIfMovieCommand(userInput).equals("")) {
                    performBookCommand(userInput);
                } else {
                    performMovieCommand(userInput);
                }
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

    public String checkIfMovieCommand(String fullCommand) {
        int index = fullCommand.lastIndexOf(" ");
        if ((index > -1) && (fullCommand.substring(1, fullCommand.lastIndexOf(" ")).equals("M"))) {
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

    public int getMovieIDFromCommand(String fullCommand) {
        int movieID;
        fullCommand = fullCommand.substring(fullCommand.lastIndexOf(" ") + 1, fullCommand.length());
        try {
            movieID = Integer.parseInt(fullCommand);
        }
        catch (NumberFormatException e) {
            return -1;
        }
        return movieID;
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

    public void performMovieCommand(String userInput)
    {
        String command = checkIfMovieCommand(userInput);
        int movieID = getMovieIDFromCommand(userInput);

        switch (command)
        {
            case Commands.MainMenu.CHECKOUT_MOVIES_COMMAND:
                movies.checkOutMovie(movieID);
                break;
            case Commands.MainMenu.RETURN_MOVIES_COMMAND:
                movies.returnMovie(movieID);
                break;
            default:
                break;
        }
    }

    public String checkIfLoginCommand(String fullCommand) {
        if (fullCommand.substring(0, 3).equals(Commands.MainMenu.LOGIN_COMMAND + " ")) {
            return fullCommand.substring(3, fullCommand.length());
        }
        return "";
    }

    public void login(String userInput) {
        String libraryNumber = accounts.checkLoginCredentials(checkIfLoginCommand(userInput));

        if (!libraryNumber.equals("")) {
            outputStream.println(Commands.Login.SUCCESSFUL_LOGIN);
            loggedInAccount = accounts.getAccountFromLibraryNumber(libraryNumber);
        } else {
            outputStream.println(Commands.Login.UNSUCCESSFUL_LOGIN);
        }
    }

    public void setLoggedInAccount(Account account) {
        loggedInAccount = account;
    }

    public void printDetails() {
        outputStream.println("Name: " + loggedInAccount.getName());
        outputStream.println("Email Address: " + loggedInAccount.getEmail());
        outputStream.println("Phone Number: " + loggedInAccount.getPhoneNumber());
    }

}
