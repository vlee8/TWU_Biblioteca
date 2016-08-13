package com.twu.biblioteca;

/**
 * Created by vlee on 12/08/2016.
 */
public class Commands {

    public class WelcomeScreen
    {
        public static final String WELCOME_SCREEN_MESSAGE = "Welcome to Biblioteca!";
    }

    public class ListOfBooksFormat
    {
        public static final String LIST_BOOK_AUTHOR_PREFIX = "Author: ";
        public static final String LIST_BOOK_YEAR_PUBLISHED_PREFIX = "Year Published: ";
    }

    public class MainMenu
    {
        public static final String MAIN_MENU_SELECTION_COMMAND = "Choose an option from the list: ";
        public static final String MAIN_MENU_INVALID_SELECTION_COMMAND = "Select a valid option!";
        public static final String LIST_BOOKS_COMMAND = "L";
        public static final String MAIN_MENU_QUIT_COMMAND = "Q";
        public static final String CHECKOUT_COMMAND = "C";
        public static final String RETURN_COMMAND = "R";
        public static final String LIST_MOVIES_COMMAND = "M";
        public static final String CHECKOUT_MOVIES_COMMAND = "CM";
        public static final String RETURN_MOVIES_COMMAND = "RM";
        public static final String LOGIN_COMMAND = "LA";
    }

    public class ErrorMessage
    {
        public static final String READ_LINE_IS_EMPTY = "Nothing was entered, please try again.";
    }

    public class BooksCommands {
        public static final String SUCCESSFUL_CHECKOUT_MESSAGE = "Thank you! Enjoy the book";
        public static final String UNSUCCESSFUL_CHECKOUT_MESSAGE = "That book is not available.";
        public static final String SUCCESSFUL_RETURN_MESSAGE = "Thank you for returning the book.";
        public static final String UNSUCCESSFUL_RETURN_MESSAGE = "That is not a valid book to return.";
    }

    public class MoviesCommands {
        public static final String SUCCESSFUL_CHECKOUT_MESSAGE = "Thank you! Enjoy the movie";
        public static final String UNSUCCESSFUL_CHECKOUT_MESSAGE = "That movie is not available.";
        public static final String SUCCESSFUL_RETURN_MESSAGE = "Thank you for returning the movie.";
        public static final String UNSUCCESSFUL_RETURN_MESSAGE = "That is not a valid movie to return.";
    }

    public class Login {
        public static final String SUCCESSFUL_LOGIN = "Successfully logged in.";
        public static final String UNSUCCESSFUL_LOGIN = "Invalid credentials.";
        public static final String NOT_LOGGED_IN_LIBRARY_NUMBER_PLACEHOLDER = "";
        public static final String NOT_LOGGED_IN_PASSWORD_PLACEHOLDER = "";
    }

}
