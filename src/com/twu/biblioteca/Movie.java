package com.twu.biblioteca;

/**
 * Created by vlee on 12/08/2016.
 */
public class Movie {

    private final String name;
    private final int year;
    private final String director;
    private boolean checkedOut;


    public Movie(String name, int year, String director) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.checkedOut = false;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public void setCheckedOut(boolean isCheckedOut) {
        checkedOut = isCheckedOut;
    }

    public boolean getCheckedOut() {
        return checkedOut;
    }
}
