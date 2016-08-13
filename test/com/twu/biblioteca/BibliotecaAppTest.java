package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by vlee on 11/08/2016.
 */
public class BibliotecaAppTest {
    PrintStream outputStream;
    BufferedReader inputStream;
    BibliotecaApp bibliotecaApp;
    Books books;
    Movies movies;
    Accounts accounts;

    @Before
    public void setUp() throws Exception {
        outputStream = mock(PrintStream.class);
        inputStream = mock(BufferedReader.class);
        books = mock(Books.class);
        movies = mock(Movies.class);
        accounts = mock(Accounts.class);

        bibliotecaApp = new BibliotecaApp(outputStream, inputStream, books, movies, accounts);
        when(inputStream.ready()).thenReturn(true);
        when(inputStream.readLine()).thenReturn("Q");
    }

    @Test
    public void shouldDisplayWelcomeMessage() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("Welcome to Biblioteca!");
    }

    @Test
    public void shouldShowOptionToListBooks() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("[L]: List Books");
    }

    @Test
    public void shouldListBooksWhenOptionIsSelected() throws Exception {
        when(inputStream.readLine())
                .thenReturn("L")
                .thenReturn("Q");

        bibliotecaApp.run();

        verify(books).displayList();
    }

    @Test
    public void shouldRepeatMenuOptionsIfInputIsInvalid() throws Exception {
        when(inputStream.readLine())
                .thenReturn("NotSupported")
                .thenReturn("L")
                .thenReturn("Q");

        bibliotecaApp.run();

        verify(outputStream, times(3)).println("[L]: List Books");
        verify(books).displayList();
        verify(outputStream).println("Select a valid option!");
    }

    @Test
    public void shouldRepeatMenuOptionsIfIoExceptionIsThrown() throws Exception {
        when(inputStream.readLine())
                .thenThrow(IOException.class)
                .thenReturn("L")
                .thenReturn("Q");
        bibliotecaApp.run();

        verify(outputStream, times(3)).println("[L]: List Books");
        verify(books).displayList();
    }

    @Test
    public void shouldShowOptionToQuit() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("[Q]: Quit");
    }

    @Test
    public void shouldShowOptionForCheckOutBook() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("[C <ID>]: Check Out Book");
    }

    @Test
    public void shouldQuitWhenOptionIsSelected() throws Exception {
        when(inputStream.readLine()).thenReturn("Q");

        bibliotecaApp.run();

        verify(outputStream, times(1)).println("[L]: List Books");
    }

    @Test
    public void shouldNotQuitStraightAway() throws Exception {
        when(inputStream.ready())
                .thenReturn(false)
                .thenReturn(true);

        bibliotecaApp.run();
        verify(inputStream, atLeast(2)).ready();
    }

    @Test
    public void shouldReturnOnlyCommandForValidBookCommand() throws Exception {
        assertEquals("C", bibliotecaApp.checkIfBookCommand("C 0"));
    }

    @Test
    public void shouldReturnNothingForCommandWithoutBookID() throws Exception {
        assertEquals("", bibliotecaApp.checkIfBookCommand("C"));
    }

    @Test
    public void shouldReturnOnlyBookIDForValidBookCommand() throws Exception {
        assertEquals(0, bibliotecaApp.getBookIDFromCommand("C 0"));
    }

    @Test
    public void shouldReturnNegativeForNonIntegerBookID() throws Exception {
        assertEquals(-1, bibliotecaApp.getBookIDFromCommand("C a"));
    }

    @Test
    public void shouldPerformBookCommandGivenCCommand() throws Exception {
        bibliotecaApp.performBookCommand("C 0");
        verify(books).checkOutBook(0, bibliotecaApp.getLoggedInAccount());
    }

    @Test
    public void shouldPerformBookCommandGivenRCommand() throws Exception {
        bibliotecaApp.performBookCommand("R 0");
        verify(books).returnBook(0);
    }

    @Test
    public void shouldShowOptionToListMovies() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("[M]: List Movies");
    }

    @Test
    public void shouldListMoviesWhenOptionIsSelected() throws Exception {
        when(inputStream.readLine())
                .thenReturn("M")
                .thenReturn("Q");

        bibliotecaApp.run();

        verify(movies).displayList();
    }

    @Test
    public void shouldShowOptionToCheckOutMovies() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("[CM <ID>]: Check Out Movie");
    }

    @Test
    public void shouldShowOptionToReturnMovies() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("[RM <ID>]: Return Movie");
    }

    @Test
    public void shouldReturnOnlyCommandForValidMovieCommand() throws Exception {
        assertEquals("C", bibliotecaApp.checkIfBookCommand("C 0"));
    }

    @Test
    public void shouldReturnNothingForCommandWithoutMovieID() throws Exception {
        assertEquals("", bibliotecaApp.checkIfBookCommand("C"));
    }

    @Test
    public void shouldReturnOnlyBookIDForValidMovieCommand() throws Exception {
        assertEquals(0, bibliotecaApp.getBookIDFromCommand("CM 0"));
    }

    @Test
    public void shouldReturnNegativeForNonIntegerMovieID() throws Exception {
        assertEquals(-1, bibliotecaApp.getBookIDFromCommand("CM a"));
    }

    @Test
    public void shouldPerformMovieCommandGivenCMCommand() throws Exception {
        bibliotecaApp.performMovieCommand("CM 0");
        verify(movies).checkOutMovie(0);
    }

    @Test
    public void shouldPerformMovieCommandGivenRMCommand() throws Exception {
        bibliotecaApp.performMovieCommand("RM 0");
        verify(movies).returnMovie(0);
    }

    @Test
    public void shouldShowOptionForLoginToAccount() throws Exception {
        bibliotecaApp.run();

        verify(outputStream).println("[LA <LIBRARY NUMBER> <PASSWORD>]: Login To Account");
    }

    @Test
    public void shouldDetectCorrectLoginCommand() throws Exception {

        assertEquals("123-4567 password", bibliotecaApp.checkIfLoginCommand("LA 123-4567 password"));
    }

    @Test
    public void shouldDetectIncorrectLoginCommand() throws Exception {

        assertEquals("", bibliotecaApp.checkIfLoginCommand("LA123-4567 password"));
    }

    @Test
    public void shouldPrintDetailsWhenLoggedIn() throws Exception {
        Accounts newAccounts = new Accounts(outputStream);
        bibliotecaApp.setLoggedInAccount(newAccounts.getListOfAccounts().get(0));
        bibliotecaApp.printDetails();

        verify(outputStream).println("Name: Test User");
        verify(outputStream).println("Email Address: test@test.com");
        verify(outputStream).println("Phone Number: 07777777777");
    }

    @Test
    public void shouldNotLetCheckOutBookWithoutLogin() {
        Account account = new Account(Commands.Login.NOT_LOGGED_IN_LIBRARY_NUMBER_PLACEHOLDER,
                Commands.Login.NOT_LOGGED_IN_PASSWORD_PLACEHOLDER, "", "", "");
        Books newBooks = new Books(outputStream);
        newBooks.checkOutBook(0, account);
        assertEquals(false, newBooks.getListOfBooks().get(0).getCheckedOut());
    }

}