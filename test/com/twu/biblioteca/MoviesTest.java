package com.twu.biblioteca;

import com.twu.biblioteca.Books;
import com.twu.biblioteca.Movies;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by vlee on 12/08/2016.
 */
public class MoviesTest {
    @Test
    public void shouldDisplayMovies() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        Movies movies = new Movies(outputStream);
        //books.addBook(new Book("This is a movie title", 2016, "Director"));
        //books.addBook(new Book("This is still a movie title", 2017, "Director Two"));
        movies.displayList();

        verify(outputStream).println("ID | Name | Year | Director | Movie Rating");
        verify(outputStream).println("--------------------------------");
        verify(outputStream).println("0 | This is a movie title | 2016 | Director | Unrated");
        verify(outputStream).println("1 | This is still a movie title | 2017 | Director Two | Unrated");
    }

    @Test
    public void shouldNotDisplayMoviesWhenCheckedOut() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        Movies movies = new Movies(outputStream);
        movies.checkOutMovie(0);
        movies.displayList();

        verify(outputStream).println("Thank you! Enjoy the movie");
        verify(outputStream).println("ID | Name | Year | Director | Movie Rating");
        verify(outputStream).println("--------------------------------");
        verify(outputStream).println("1 | This is still a movie title | 2017 | Director Two | Unrated");
    }

    @Test
    public void shouldDisplaySuccessfulMessageForCheckingOutMovie() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        Movies movies = new Movies(outputStream);
        movies.checkOutMovie(0);

        verify(outputStream).println("Thank you! Enjoy the movie");
    }

    @Test
    public void shouldDisplayUnsuccessfulMessageForCheckingOutInvalidMovie() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        Movies movies = new Movies(outputStream);
        movies.checkOutMovie(0);
        movies.checkOutMovie(0);

        verify(outputStream).println("That movie is not available.");
    }

    @Test
    public void shouldDisplayUnsuccessfulMessageForCheckingOutNonExistentMovie() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        Movies movies = new Movies(outputStream);
        movies.checkOutMovie(2);

        verify(outputStream).println("That movie is not available.");
    }

}
