package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlee on 12/08/2016.
 */
public class Movies {
    private final PrintStream outputStream;
    private List<Movie> listOfMovies;

    public Movies(PrintStream outputStream) {
        this.outputStream = outputStream;
        listOfMovies = new ArrayList<>();
        addPreexistingMovies();
    }

    public void displayList() {
        outputStream.println("ID | Name | Year | Director | Movie Rating");
        outputStream.println("--------------------------------");
        int movieID = -1;
        for (Movie movie : listOfMovies) {
            movieID++;
            if (!movie.getCheckedOut()) {
                String movieRating = "Unrated";
                if (movie.getMovieRating() > -1)
                {
                    movieRating = Integer.toString(movie.getMovieRating());
                }
                outputStream.println(movieID + " | " + movie.getName() + " | " + movie.getYear() + " | " + movie.getDirector() + " | " + movieRating);
            }
        }
    }

    public void addMovie(Movie movie) {
        listOfMovies.add(movie);
    }

    public void addPreexistingMovies()
    {
        Movie movie = new Movie("This is a movie title", 2016, "Director");
        addMovie(movie);
        movie = new Movie("This is still a movie title", 2017, "Director Two");
        addMovie(movie);
    }

    public void checkOutMovie(int movieID)
    {
        try {
            if (!listOfMovies.get(movieID).getCheckedOut()) {
                listOfMovies.get(movieID).setCheckedOut(true);
                outputStream.println(Commands.MoviesCommands.SUCCESSFUL_CHECKOUT_MESSAGE);
            } else {
                outputStream.println(Commands.MoviesCommands.UNSUCCESSFUL_CHECKOUT_MESSAGE);
            }
        }
        catch (IndexOutOfBoundsException e) {
            outputStream.println(Commands.MoviesCommands.UNSUCCESSFUL_CHECKOUT_MESSAGE);
        }
    }

    public void returnMovie(int movieID)
    {
        try {
            if (listOfMovies.get(movieID).getCheckedOut()) {
                listOfMovies.get(movieID).setCheckedOut(false);
                outputStream.println(Commands.MoviesCommands.SUCCESSFUL_RETURN_MESSAGE);
            } else {
                outputStream.println(Commands.MoviesCommands.UNSUCCESSFUL_RETURN_MESSAGE);
            }
        }
        catch (IndexOutOfBoundsException e) {
            outputStream.println(Commands.MoviesCommands.UNSUCCESSFUL_RETURN_MESSAGE);
        }
    }
}
